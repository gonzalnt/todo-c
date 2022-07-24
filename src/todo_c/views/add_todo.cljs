(ns todo-c.views.add-todo
  (:require
   [reagent.core :as r]
   [re-frame.core :as re-frame]
   [cuid.core :as c]))

(defn validate-todo [todo] 
  (re-frame/dispatch [:save-todo @todo]))

(defn page
  []
  (let [new-todo (r/atom {:id (c/cuid) :name "" :status :open})]
    (println "This is a test.")
    (fn []
      [:div.row
       [:div.twelve.column
        [:h4 "Add Todo"]
        [:form
           {:on-submit
            (fn [e]
              (.preventDefault e)
              (validate-todo new-todo))}
         [:div.row
          [:div.six.columns
           [:label {:for "item-name"} "Name:"]
           [:input#item_name.u-full-width
            {:placeholder "Item name", :type "text"
             :defaultValue (:name @new-todo)
             :on-change #(swap! new-todo assoc :name (-> % .-target .-value))}]]]
         [:input#save_button.button-primary
          {:value "Save Item", :type "submit"}]]
        [:a {:on-click #(re-frame/dispatch [:navigate :routes/list-todo])
             :href "#"} "Back to the list"]]])))