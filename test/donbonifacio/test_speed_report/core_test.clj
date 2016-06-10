(ns donbonifacio.test-speed-report.core-test
  (:require [clojure.test :refer :all]
            [donbonifacio.test-speed-report.core :as core]))

(deftest calc-average-test
  (is (= "-" (core/calc-average 0 0)))
  (is (= 1 (core/calc-average 1 1)))
  (is (= 2 (core/calc-average 5 10))))

(deftest calc-median-test
  (is (= "-" (core/calc-median [] 0)))
  (is (= 2 (core/calc-median [1 2 3] 3))))

(deftest namespaces-in-directories-test
  (is (some #{'donbonifacio.test-speed-report.core-test}
            (core/namespaces-in-directories ["."]))))
