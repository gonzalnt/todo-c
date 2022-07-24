(ns todo-c.views.view-todo
  (:require [re-frame.core :as re-frame]))

(defn page
  []
  (let [todo (re-frame/subscribe [:todo])]
    [:div.row
     [:div.twelve.column
      [:h4 "View Todo "]
      [:form
       [:div.row
        [:div.six.columns
         [:label {:for "item-name"} "Name:"]
         [:input#item-name.u-full-width
          {:value (:name @todo) :type "text" :disabled true}]]]
       [:div.row
        [:div.three.columns 
         [:button.button-primary
          {:on-click #(re-frame/dispatch [:mark-as-done @todo])} "Mark as done"]]]]
      [:a {:on-click #(re-frame/dispatch [:navigate :routes/list-todo])
           :href "#"} "Back to the list"]]]))