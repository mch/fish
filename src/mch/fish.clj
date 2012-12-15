(ns mch.fish)

(def VRs [{:vr "AE" :name "Application Entity" :definition "" :repertoire "" :length ""}
          {:vr "AS" :name ""}
          {:vr "AT" :name ""}
          {:vr "CS" :name ""}
          {:vr "DA" :name ""}
          {:vr "DS" :name ""}
          {:vr "DT" :name ""}
          {:vr "FL" :name ""}
          {:vr "FD" :name ""}
          {:vr "IS" :name ""}
          {:vr "LO" :name ""}
          {:vr "LT" :name ""}
          {:vr "OB" :name ""}
          {:vr "OF" :name ""}
          {:vr "OW" :name ""}
          {:vr "PN" :name ""}
          {:vr "SH" :name ""}
          {:vr "SL" :name ""}
          {:vr "SQ" :name ""}
          {:vr "SS" :name ""}
          {:vr "ST" :name ""}
          {:vr "TM" :name ""}
          {:vr "UI" :name ""}
          {:vr "UL" :name ""}
          {:vr "UN" :name ""}
          {:vr "US" :name ""}
          {:vr "UT" :name ""}])



(defn generate-tag-map [line]
  (let [vrs "AE|AS|AT|CS|DA|DS|DT|FL|FD|IS|LO|LT|OB|OF|OW|PN|SH|SL|SQ|SS|ST|TM|UI|UL|UN|US|UT"
        pattern (re-pattern (str "\\(([0-9a-fA-Fx]{4},[0-9a-fA-Fx]{4})\\) +(.*) +(" vrs ") +([0-9\\-n]+)"))
        keys [:tag :name :vr :vm]
        matches (re-find pattern line)]
    (if (nil? matches)
      (do (println (format "Failed to match \"%s\"..." line))
          {})
      (apply hash-map (interleave keys (rest (re-find pattern line)))))))

(defn fix [m]
  (if (nil? (:name m)) m
      (let [[all name keyword] (re-find #"(.*) (.*)$" (:name m))]
        (assoc m :name name :keyword keyword))))
