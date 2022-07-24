(ns todo-c.subs
  (:require
   [re-frame.core :refer [reg-sub]]))

(reg-sub
 :todo-list
 (fn [db _]
   (:todo-list db)))

(reg-sub
 :todo 
 (fn [db _]
   (:todo db)))


