(ns todo-c.events
  (:require
   [re-frame.core :as re-frame]
   [todo-c.db :as db]))

(re-frame/reg-event-fx
 ::init
 (fn [_]
   {:db db/initial-db}))

(re-frame/reg-event-db
 :set-todo
 (fn [db [_ id]]
   (let [list-found
         (filter #(= (:id %) id) (:todo-list db))
         found-todo (first list-found)]
     (assoc db :todo found-todo))))

(re-frame/reg-event-db
 :mark-as-done
 (fn [db [_ todo]]
   (let [new-todo-list
         (filter #(not= (:id %) (:id todo)) (:todo-list db)) 
         new-todo
         (assoc todo :status :closed)
         todo-list (conj new-todo-list new-todo)]
     (assoc db :todo-list todo-list))))

(re-frame/reg-event-db
 :save-todo
 (fn [db [_ new-todo]]
   (let [todo-list (:todo-list db)
         new-todo-list (conj todo-list new-todo)]
     (assoc db :todo-list new-todo-list))))