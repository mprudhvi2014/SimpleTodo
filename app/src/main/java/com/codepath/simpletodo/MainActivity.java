package com.codepath.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter<String> Itemsadapter;
    ListView lvItems;
    ArrayList<TodoItem> todoList;
    TodoAdapter todoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        todoList = TodoList.getInstance().getTodoItemsList();
        lvItems = (ListView) findViewById(R.id.lvItems);
        // checkAndUpdateFile();
        todoAdapter = new TodoAdapter(this, todoList);
        lvItems.setAdapter(todoAdapter);
        //  setupListViewListener();
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openDetailsActivity(position, lvItems.getItemAtPosition(position).toString());
            }
        });
        registerForContextMenu(lvItems);

    }


    public void onAddItem(View v) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        TodoItem todoItem = new TodoItem();
        todoItem.setTodoValue(itemText);
        todoList.add(todoItem);
        etNewItem.setText("");
        TodoList.getInstance().setTodoItemsList(todoList);
        todoAdapter.notifyDataSetChanged();

    }

    /*private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View Item, int pos, long id) {
                Items.remove(pos);
                Itemsadapter.notifyDataSetChanged();
                checkAndUpdateFile();
                return true;
            }
        });
    }

    private void readItems() {
        File files = getFilesDir();
        File todoFile = new File(files, "todo.txt");
        try {
            Items = new ArrayList<String>(FileUtils.readLines(todoFile));
        } catch (IOException e) {
            Items = new ArrayList<String>();
        }
    }

    private void checkAndUpdateFile() {
        File file = new File(Environment.getExternalStorageDirectory() + "/todo.txt");
        if (file.exists()) {
            try {
                Items = new ArrayList<String>(FileUtils.readLines(file));
            } catch (IOException e) {
                Items = new ArrayList<String>();
            }
        } else {

            File dir = Environment.getExternalStorageDirectory();
            File todoFile = new File(dir, "todo.txt");
            try {
                FileUtils.writeLines(todoFile, Items);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void writeItems() {
        File Files = getFilesDir();
        File todoFile = new File(Files, "todo.txt");
        try {
            FileUtils.writeLines(todoFile, Items);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/


    public void openDetailsActivity(int position, String data) {
        Intent i = new Intent(MainActivity.this, EditItemActivity.class);
        i.putExtra("position", position);
        startActivityForResult(i, 9999);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_OK) {
            Itemsadapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        todoAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Select The Action");
        menu.add(0, v.getId(), 0, "Delete");//groupId, itemId, order, title

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
        if (item.getTitle() == "Delete") {
            if (position != -1) {
                Toast.makeText(getApplicationContext(), "Deleting Item", Toast.LENGTH_LONG).show();
                todoList.remove(position);
                todoAdapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "Item deleted successfully!", Toast.LENGTH_LONG).show();
            }
        } else {
            return false;
        }
        return true;
    }
}

