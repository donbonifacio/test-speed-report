(ns leiningen.test-speed-report
  (:require [donbonifacio.test-speed-report.core :as core]
            [leiningen.core.project :as project]
            [leinjacker.deps :as deps]
            [leinjacker.eval :as eval]))

(defn- clojure-test-directories [project]
  (vec (concat (:test-path project [])
               (:test-paths project []))))

(defn- add-deps
  "Adds this project as a dependency, if that is not done already"
  [project]
  (let [test-speed-report-plugin (first (filter (fn [[name version]] (= name 'donbonifacio/test-speed-report)) (:plugins project)))]
    (deps/add-if-missing project test-speed-report-plugin)))

(defn test-speed-report
  "Runs lein test and prints a table with tests duration time."
  [project & args]
  (let [test-paths (clojure-test-directories project)]
    (eval/eval-in-project (-> project
                              (project/merge-profiles [:test])
                              add-deps)
                          `(donbonifacio.test-speed-report.core/load-and-run-tests ~test-paths)
                          `(require 'donbonifacio.test-speed-report.core))))
