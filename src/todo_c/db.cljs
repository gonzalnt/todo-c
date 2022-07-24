(ns todo-c.db
  (:require [cuid.core :as c]))

(def initial-db
  {:current-page :home
   :todo-list 
   [{:id (c/cuid) :name "Item 1" :status :open}
    {:id (c/cuid) :name "Item 2" :status :open}
    {:id (c/cuid) :name "Item 3" :status :closed}
    {:id (c/cuid) :name "Item 4" :status :open}
    {:id (c/cuid) :name "Item 5" :status :open}]})
