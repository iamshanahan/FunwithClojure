(ns my-first-cloj.core-test
  (:require [clojure.test :refer :all]
            [my-first-cloj.core :refer :all]))

(deftest type-test
  (testing "type"
    (is (= java.lang.Long (type 1)))
    (is (= java.lang.String (type "one")))
    (is (= clojure.lang.PersistentVector (type [1 2 3])))
    (is (= clojure.lang.PersistentVector (type ["one" "two" "three"])))
))

(deftest type-test
  (testing "basic"
    (is (= 3(+ 2 1)))
))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 0 1))))

