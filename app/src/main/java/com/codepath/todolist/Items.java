package com.codepath.todolist;

/**
 * Created by hammedopejin on 2/17/17.
 */
    public class Items {
        public  String todo;
        public  String pri;

        public Items(){
            super();
        }

        public Items(String todo, String pri){
            super();
            this.todo = todo;
            this.pri = pri;
        }

        public Items(Items combo){
            this.todo = combo.todo;
            this.pri = combo.pri;
        }
    }


