(ns my-first-cloj.core-test
  (:require [clojure.test :refer :all]
            [my-first-cloj.core :refer :all]))

(deftest type-test
  (testing "type"
    (is (= java.lang.Long (type 1)))
    (is (= java.lang.String (type "one")))
    (is (= clojure.lang.Ratio (type 1/5)))
    (is (= clojure.lang.Keyword (type :my_keyword)))
    (is (= clojure.lang.PersistentVector (type [1 2 3])))
    (is (= clojure.lang.PersistentVector (type ["one" 1/5 + :my_keyword])))
))

(deftest equality-test  
  (testing "equality"
    (is (= 3 3))
    (is (= "string" "string"))
    (is (= ["string" "vector"] ["string" "vector"]))
    ; I don't understand how ratios work
    (is (= 3 6/2))
    (is (= 21/35 3/5))
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
    (is (= nil (or false nil)))
    (is (= 3 (or 3)))
    (is (= 3 (or nil 3 false)))
    (is (= 3 (or 3 4 5)))

    (is (= true (and)))
    (is (= false (and false nil)))
    (is (= nil (and nil false)))
    (is (= 3 (and 3)))
    (is (= 0 (and "" true 0)))
    (is (= nil (and 0 "" true nil)))
    (is (= nil (and 3 nil "string")))
    (is (= "string" (and 3 "string")))
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
  (testing "basic dev"
    (def my-string "string value")
    (is (= "string value" my-string))
    (def my-func +)
    (is (= 7 (my-func 2 5)))
))

(deftest def-map
  (testing "map behavior"
    (is (= nil ({} :a_key)))
    (is (= nil (get {} :a_key)))
    (is (= nil (:a_key {})))
    (is (= nil ({:b_key "b value"} :a_key)))
    (is (= nil (get {:b_key "b value"} :a_key)))
    (is (= nil (:a_key {:b_key "b value"})))
    (is (= "a value" ({:a_key "a value"} :a_key)))
    (is (= "a value" (get {:a_key "a value"} :a_key)))
    (is (= "a value" (:a_key {:a_key "a value"})))
    (is (= "default value" ({} :a_key "default value")))
    (is (= "default value" (get {} :a_key "default value")))
    (is (= "default value" (:a_key {} "default value")))
    (is (= {:a 1 :b 2} (hash-map :a 1 :b 2)))
))

(deftest def-vector
  (testing "vector behavior"
    (is (= nil (get [] 0)))
    (is (= nil (get ["0-index"] 1)))
    (is (= "0-index" (get ["0-index"] 0)))
    (is (= [ 17 3/8 "my string" +] (vector 17 3/8 "my string" +)))
    (is (= [1 2] (conj [1] 2)))
    (is (= [1 2 3 4] (conj [1 2] 3 4)))
    (is (= [1 [2]] (conj [1] [2])))
))

(deftest def-list
  (testing "list behavior"
    (is (= '(1 "two" 6/5) (list 1 "two" 6/5)))
    ; Note that this does not work and I don't know why not
    ; (is (= '(+) (list +) ))
    ; TODO exception (nth '() 0) etc
    (is (= (nth '(3) 0) 3))
    (is (= '( 4 1 2 3) (conj '(1 2 3) 4)))
))

(deftest def-set
  (testing "set behavior"
    (is (= #{} (hash-set)))
    (is (= #{"zero" 0} (hash-set "zero" 0)))
    ; Must learn how to test for exceptions #{0 0}
    (is (= #{0} (set [0 0])))
    (is (= #{0} (set '(0 0))))
    (is (= #{0 1} #{1 0}))
    (is (contains? #{"string"} "string"))
    (is (not (contains? #{"string"} "different string")))
    (is (contains? #{nil} nil))
    (is (not (contains? #{"string"} nil)))
    (is (= :a (:a #{:a :b})))
    (is (= nil (:a #{:b :c})))
    (is (= nil (get #{"a" "b"} "c")))
    (is (= nil (get #{"a" "b"} nil)))
))

(deftest def-map
  (testing "map behavior" 
    (is (= '(1 2 3 4) (map inc [0 1 2 3])))
    (is (= '(1 2 3 4) (map inc '(0 1 2 3))))
))

(deftest def-func
  (testing "function behavior"
    (defn myfunc
      []
      17)
    (is (= 17 (myfunc)))
    (defn myfunc 
      "doc string"
      []
      17)
    (is (= 17 (myfunc)))
    (defn myfunc
      [a-param]
      (str "Param: " a-param))
    (is (= "Param: 17" (myfunc 17)))
    (defn myfunc
      ([]
       0)
      ([one-arg]
       (+ 1 one-arg))
      ([one-arg two-arg three-arg]
       (+ 10000 one-arg two-arg three-arg))
      ([one-arg two-arg]
       (+ 100 one-arg two-arg))
      )
    (is (= 0 (myfunc)))
    (is (= 8 (myfunc 7)))
    (is (= 109 (myfunc 4 5)))
    (is (= 10017 (myfunc 5 6 6)))
    (defn func-default-param
      ([]
       (func-default-param "world"))
      ([name]
       (str "Hello, " name "!"))
      )
    (is (= "Hello, world!" (func-default-param)))
    (is (= "Hello, Clyde!" (func-default-param "Clyde")))
    
    (defn func-rest-param
      ([& rest-params]
       rest-params))
    (is (= nil (func-rest-param)))
    (is (= '("param") (func-rest-param "param")))
    (is (= '("param1" "param2") (func-rest-param "param1" "param2")))

    (defn func-mixed-params
      [first-param & rest-params]
      (map #(str first-param ":" %) rest-params))
    (is (= '() (func-mixed-params "first-param")))

    (defn func-destructured
      [[first-thing second-thing other-things]]
      0)
    (is (= 0 (func-destructured [] )))
))
