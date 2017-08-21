package com.codepath.simpletodo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class TodoAdapter extends BaseAdapter {
    ArrayList<TodoItem> todoItemsList;
    Context context;

    public TodoAdapter(Context context, ArrayList<TodoItem> todoItemsList) {
        this.context = context;
        this.todoItemsList = todoItemsList;
    }

    @Override
    public int getCount() {
        return todoItemsList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
// inflate the layout for each list row
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.todo_item_row, parent, false);
        }

        // get current item to be displayed
        TodoItem currentItem = todoItemsList.get(position);

        // get the TextView for item name and item description
        TextView textViewItemName = (TextView)
                convertView.findViewById(R.id.text_view_item_name);

        //sets the text for item name and item description from the current item object
        textViewItemName.setText(currentItem.getTodoValue());

        // returns the view for the current row
        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }
}
