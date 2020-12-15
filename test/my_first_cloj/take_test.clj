(ns my-first-cloj.take-test
  (:require [clojure.test :refer :all]
            [my-first-cloj.core :refer :all]))

(deftest take-test
  (testing "take"
    (is (= '() (take 3 [])))
    (is (= '() (take 0 [1 2 3])))
    (is (= '() (take -1 [1 2 3])))
    (is (= '(1 2) (take 2 [1 2 3 4 5])))
    (is (= '(1 2) (take 5 [1 2])))
))

(deftest let-test
  (testing "let"
    (is (= 6 (let [x 3] (* 2 x))))
    (is (= 6 (let [[x y] [2 3]] (* x y))))
    (is (= 9 (let [x 2 [y z] [3 4]] (+ x y z))))
    (is (= 6 (let [[x y] [2 3 4]] (* x y))))
    (is (= 6 (let [x (* 2 3)] x)))
))

(deftest first-test
  (testing "first"
    (is (= "first" (first ["first" "second" "third"])))
    (is (= ["second" "third"] (rest ["first" "second" "third"])))
    (is (= ["new" "first" "second"] (cons "new" ["first" "second"])))
))

(deftest map-test
  (testing "map"
    (is (= '(3 4 5) (map (fn [x] (+ 1 x)) '(2 3 4) )))
    (is (= '(3 4 5) (map (fn [x] (+ 1 x)) [2 3 4] )))
    (is (= '(3 4 5) (map (fn [x] (+ 1 x)) #{2 3 4} )))
))

(deftest into-test
  (testing "into"
))
