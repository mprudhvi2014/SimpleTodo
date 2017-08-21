package com.codepath.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class EditItemActivity extends AppCompatActivity {
    ArrayList<TodoItem> todoItemArrayList;
    int position;
    private EditText etNewItem;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        etNewItem = (EditText) findViewById(R.id.edtHere);
        btnSave = (Button) findViewById(R.id.btnSave);
        todoItemArrayList = TodoList.getInstance().getTodoItemsList();
        if (getIntent().getIntExtra("position", -1) != -1) {
            position = getIntent().getIntExtra("position", -1);
            if (todoItemArrayList != null && todoItemArrayList.get(position) != null) {
                if (todoItemArrayList.get(position).getTodoValue() != null) {
                    etNewItem.setText(todoItemArrayList.get(position).getTodoValue());

                }
            }
        }
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position != -1 && etNewItem.getText() != null && etNewItem.getText().toString() != null) {
                    Intent data = new Intent();
                    TodoItem todoItem = new TodoItem();
                    todoItem.setTodoValue(etNewItem.getText().toString());
                    todoItemArrayList.set(position, todoItem);
                    setResult(RESULT_OK, data);
                    finish();
                }

            }
        });
    }
}
