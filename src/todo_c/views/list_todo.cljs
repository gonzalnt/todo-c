(ns todo-c.views.list-todo
  (:require
   [re-frame.core :as re-frame]))



(defn page
  []
  (let [todo-list (re-frame/subscribe [:todo-list])]
    [:div.row
     [:div.twelve.column
      [:h4 "Todo List"]
      [:ul
       (for [todo @todo-list]
         ^{:key (:id todo)}
         [:li
          (if (= :open (:status todo))
            [:a {:on-click #(re-frame/dispatch [:navigate :routes/view-todo todo])
                 :href "#"} (:name todo)]
            [:strike (:name todo)])])]
      [:a {:on-click #(re-frame/dispatch [:navigate :routes/add-todo])
           :href "#"}
       "Add Item"]]]))


