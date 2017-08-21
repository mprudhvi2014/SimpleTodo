package com.codepath.simpletodo;

import java.util.ArrayList;


public class TodoList {
    private static final TodoList ourInstance = new TodoList();
    public static ArrayList<TodoItem> todoItemsList = new ArrayList<TodoItem>();

    public static TodoList getInstance() {
        return ourInstance;
    }

    public ArrayList<TodoItem> getTodoItemsList() {
        return todoItemsList;
    }

    public void setTodoItemsList(ArrayList<TodoItem> todoItemsList) {
        this.todoItemsList = todoItemsList;
    }


}
