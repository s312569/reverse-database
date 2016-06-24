(ns reverse-database.core
  (:require [fs.core :as fs]
            [clojure.java.io :refer [reader resource file writer]]
            [clojure.string :refer [join]]
            [clj-fasta.core :as fa]
            [clojure.tools.cli :refer [parse-opts]])
  (:gen-class))

(def cli-options
  [["-i" "--input PATH" "Path to file containing full paths of mzML or mgf files to be searched."
    :parse-fn #(fs/absolute-path (file %))
    :validate [#(fs/exists? %)
               "Input file list does not exist."]]
   ["-o" "--output PATH" "Path to file containing X! Tandem parameters."
    :parse-fn #(fs/absolute-path (file %))]
   ["-h" "--help" "Print help message."]])

(defn usage [options-summary]
  (->> ["Takes a fasta file as input and writes a new fasta file with
  the original sequences and reversed sequences. The accession numbers
  of reversed sequences are prepended with 'rev_'."
        ""
        "Usage: sg-protein-search [options]"
        ""
        "Options:"
        options-summary
        ""]
       (join \newline)))

(defn error-msg [errors]
  (str "Error:\n"
       (->> errors
            (interpose \newline)
            (apply str))))

(defn exit [status msg]
  (println msg)
  (System/exit status))

(defn reverse-seq
  [bs]
  {:accession (str "rev_" (:accession bs))
   :description (str "REVERSED: " (:description bs))
   :sequence (-> (:sequence bs) reverse vec)})

(defn -main [& args]
  (let [{:keys [options arguments errors summary]} (parse-opts args cli-options)]
    (cond 
      (:help options)
      (exit 0 (usage summary))
      errors
      (exit 1 (error-msg errors))
      (not (:input options))
      (exit 1 "Must specify an input fasta file (-i)")
      (not (:output options))
      (exit 1 "Must specify an output file (-o)"))
    (with-open [r (reader (:input options))]
      (fa/fasta->file (mapcat #(list % (reverse-seq %))
                              (fa/fasta-seq r))
                      (:output options)
                      :append false))))
