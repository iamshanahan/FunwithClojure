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

