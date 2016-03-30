(ns clojure-qt4-demo
  (:import (com.trolltech.qt.gui QApplication QPushButton QFont QFont$Weight)
           (com.trolltech.qt.core QCoreApplication)))

(defn init []
  (QApplication/initialize (make-array String 0)))

(defn exec []
  (QApplication/exec))

(defmacro qt4 [& rest]
  `(do
     (try (init) (catch RuntimeException e# (println e#)))
     ~@rest
     (exec)))

(defn hello-world []
  (qt4
   (let [app (QCoreApplication/instance)
         button (new QPushButton "Go Clojure Go")]
     (.. button clicked (connect app "quit()"))
     (doto button
       (.resize 250 100)
       (.setFont (new QFont "Deja Vu Sans" 18 (.. QFont$Weight Bold value)))
       (.setWindowTitle "Go Clojure Go")
       (.show)))))