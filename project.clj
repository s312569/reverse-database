(defproject reverse-database "0.1.0-SNAPSHOT"
  :description "Takes a fasta file and produces a concatenated database with reversed sequences."
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [clj-fasta "0.1.7"]
                 [fs "1.3.3"]
                 [org.clojure/tools.cli "0.3.1"]]
  :main ^:skip-aot reverse-database.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
