(ns todo-c.routes
  (:require
   [re-frame.core :as re-frame]
   [reitit.coercion.malli]
   [reitit.frontend]
   [reitit.frontend.easy :as rfe]
   [reitit.frontend.controllers :as rfc]
   [todo-c.views.list-todo :as list-todo]
   [todo-c.views.add-todo :as add-todo]
   [todo-c.views.view-todo :as view-todo]))

;;; Subs

(re-frame/reg-sub
 :current-route
 (fn [db]
   (:current-route db)))

;;; Events

(re-frame/reg-event-fx
 :navigate
 (fn [_cofx [_ & route]]
   {:navigate! route}))

;; Triggering navigation from events.
(re-frame/reg-fx
 :navigate!
 (fn [route]
   (apply rfe/push-state route)))

(re-frame/reg-event-db
 :navigated
 (fn [db [_ new-match]]
   (let [old-match   (:current-route db)
         controllers (rfc/apply-controllers (:controllers old-match) new-match)]
     (assoc db :current-route (assoc new-match :controllers controllers)))))

(def routes
  ["/" 
   [""
    {:name      :routes/list-todo
     :view      list-todo/page
     :link-text "List Todo"
     :controllers
     [{:start (fn []
                (println "Entering Todo List page"))
       :stop  (fn []
                (println "Leaving Todo List page"))}]}]
   ["add"
    {:name      :routes/add-todo
     :view      add-todo/page
     :link-text "Add Todo"
     :controllers
     [{:start (fn []
                (println "Entering Add Todo page"))
       :stop  (fn []
                (println "Leaving Add Todo page"))}]}]
   ["todo/:id"
    {:name      :routes/view-todo
     :view      view-todo/page
     :link-text "View Todo"
     :coercion  reitit.coercion.malli/coercion
     :params    {:path [:map [:id string?]]}
     :controllers
     [{:parameters {:path [:id]}
       :start (fn [{:keys [path]}]
                (let [todo-id (:id path)]
                  (re-frame/dispatch [:set-todo todo-id])))
       :stop  (fn []
                (println "Leaving Todo View page"))}]}]])

(def router
  (reitit.frontend/router
   routes
   {:data {:coercion reitit.coercion.malli/coercion}}))

(defn on-navigate [new-match]
  (when new-match
    (re-frame/dispatch [:navigated new-match])))

(defn init-routes! []
  (js/console.log "initializing routes")
  (rfe/start!
   router
   on-navigate
   {:use-fragment true}))