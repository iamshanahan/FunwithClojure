(ns my-first-cloj.core-test
  (:require [clojure.test :refer :all]
            [my-first-cloj.core :refer :all]))

(deftest type-test
  (testing "type"
    (is (= java.lang.Long (type 1)))
    (is (= java.lang.String (type "one")))
    (is (= clojure.lang.Ratio (type 1/5)))
    (is (= clojure.lang.PersistentVector (type [1 2 3])))
    (is (= clojure.lang.PersistentVector (type ["one" "two" "three"])))
))

(deftest basic-test  
  (testing "equality"
    (is (= 3 3))
    (is (= "string" "string"))
    (is (= ["string" "vector"] ["string" "vector"]))
  )

  (testing "addition"
    (is (= 3(+ 2 1)))
))

(deftest truth-test
  (testing "truth values"
    (is true)
    (is 0)
    (is "")
    (is (not false))
    (is (not nil))
    
    (is (= nil (or)))
    (is (= false (or nil false)))
    (is (= 3 (or nil false 3)))
    (is (= 0 (and "" true 0)))
    (is (= nil (and 0 "" true nil)))
    (is (= false (and false)))
    (is (= nil (and 3 nil "string")))
    (is (= "string" (and 3 "string")))
    (is (= 3 (or nil 3 nil)))
    (is (= false (or nil false)))
))

(deftest if-test
  (testing "if behavior")
    (is (= "yes" (if true "yes")))
    (is (= "yes" (if true "yes" false)))
    (is (= nil (if false true)))
    (is (= "no" (if false false "no"))
))

(deftest do-test
  (testing "do behavior")
    (is (= nil (do)))
    (is (= "yes" (do "yes")))
    (is (= "yes" (do "no" nil "yes")))
    (is (= nil (do "yes" nil))
))

(deftest when-test
  (testing "when behavior")
    (is (= nil (when true)))
    (is (= "yes" (when true "yes")))
    (is (= "yes" (when true "no" nil "yes")))
    (is (= nil (when true "yes" nil))
    (is (= nil (when false "yes")))
))

(deftest def-test
  (testing "def"
    (def my-string "string value")
    (is (= "string value" my-string))
))



