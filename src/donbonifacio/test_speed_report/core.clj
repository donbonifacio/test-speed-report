(ns ^{:added "0.1.0" :author "Pedro Pereira Santos"}
  donbonifacio.test-speed-report.core
  "Runs project tests and adds a sorted table with the time each test took.
  It works by intercepting the `begin-test-var` and `end-test-var` events,
  and timing the execution."
  (:require [clojure.test]
            [clojure.pprint]
            clojure.tools.namespace.find))

(def data
  "This atom stores the test results. Each test is run sequentially, and
  the results will be placed here"
  (atom {:tests [] :current nil}))

(defn- calc-average
  [total-tests total-time-spent]
  (if (zero? total-tests)
    "-"
    (int (/ total-time-spent total-tests))))

(defn- calc-median [elapseds total-tests]
  (if (zero? total-tests)
    "-"
    (let [middle (int (/ total-tests 2))]
      (int (nth elapseds middle)))))

;;
;; clojure.test re-definitions
;;

(defmethod clojure.test/report :begin-test-var [m]
  (let [test-sym (:var m)
        test-data {:started-at (. System (nanoTime)) :test test-sym}]
    (swap! data assoc :current test-data)))

(defmethod clojure.test/report :end-test-var [m]
  (let [test-sym (:var m)
        test-data (get @data :current)
        started-at (:started-at test-data)
        elapsed (/ (double (- (. System (nanoTime)) started-at)) 1000000.0)
        test-data {:elapsed elapsed :test test-sym}]
    (swap! data update :tests conj test-data)))

(defmethod clojure.test/report :summary [m]
  (let [tests (sort-by :elapsed (get @data :tests))
        elapseds (map :elapsed tests)
        sum (reduce + elapseds)
        total (count tests)]

    (println "\n-----------------")
    (println "Test Speed Report")
    (println "-----------------")

    (clojure.pprint/print-table [:test :elapsed] tests)

    (println "\nAverage:" (calc-average total sum) "msecs, "
             "Median: " (calc-median elapseds total)) "msecs"

    (println "\nRan" (:test m) "tests containing"
             (+ (:pass m) (:fail m) (:error m)) "assertions.")
    (println (:fail m) "failures," (:error m) "errors.")))

;;
;; Loads and runs tests
;;

(defn- namespaces-in-directories [dirs]
  (let [as-files (map clojure.java.io/file dirs)]
    (flatten (for [file as-files]
               (clojure.tools.namespace.find/find-namespaces-in-dir file)))))

(defn load-and-run-tests [test-paths]
  (let [namespaces (namespaces-in-directories test-paths)]
    (run! require namespaces)
    (apply clojure.test/run-tests namespaces)
    (shutdown-agents)))
