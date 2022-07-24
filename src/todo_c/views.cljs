(ns todo-c.views
  (:require
   [re-frame.core :as re-frame]))

(defn main-page
  []
  (let [current-route @(re-frame/subscribe [:current-route])]
    [:div.container
     {:style {:margin "10px"}}
     (when current-route
       [(-> current-route :data :view)])]))

(defn root []
  [:div#root 
   [main-page]])
