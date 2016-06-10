(defproject donbonifacio/test-speed-report "0.1.0-SNAPSHOT"
  :description "Runs clojure.test tests and prints a speed summary report"
  :url "https://github.com/donbonifacio/test-speed-report"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[leinjacker "0.4.2" :exclusions [org.clojure/clojure]]
                 [org.clojure/tools.namespace "0.2.11"]]
  :profiles {:dev {:dependencies [[org.clojure/clojure "1.8.0"]]}}
  :eval-in-leiningen true)
