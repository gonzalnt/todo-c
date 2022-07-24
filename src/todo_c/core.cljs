(ns todo-c.core
  (:require
   [reagent.dom :as rdom]
   [re-frame.core :as re-frame]
   [todo-c.events :as events]
   [todo-c.routes :as routes]
   [todo-c.subs]
   [todo-c.views :as views]
   [todo-c.config :as config]))

(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (re-frame/clear-subscription-cache!)
  (routes/init-routes!)
  (let [root-el (.getElementById js/document "app")]
    (rdom/unmount-component-at-node root-el)
    (rdom/render [views/root] root-el)))

(defn init []
  (re-frame/dispatch-sync [::events/init])
  (dev-setup)
  (mount-root))
