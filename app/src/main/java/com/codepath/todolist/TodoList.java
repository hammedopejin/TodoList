package com.codepath.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.codepath.todolist.Activity.EditDialogActivity;
import com.codepath.todolist.Activity.NewAddActivity;
import com.codepath.todolist.Fragment.MyDeleteDialogFragment;
import com.codepath.todolist.Adapter.CustomUserAdapter;
import com.codepath.todolist.Helper.TodoItemsDbHelper;

import java.util.ArrayList;

/**
 * Created by hammedopejin on 2/22/17.
 */

public class TodoList extends AppCompatActivity implements MyDeleteDialogFragment.OnCompleteListener{

    // REQUEST_CODE can be any value we like, used to determine the result type later
    private final int REQUEST_CODE = 20;

    CustomUserAdapter cAdapter;
    ListView lvItems;
    String iValue;
    TodoItemsDbHelper td;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

        td = new TodoItemsDbHelper(this);
        ArrayList<String> baseData = td.getItem();
        String todo[] = new String[baseData.size()];
        String pri[] = new String[baseData.size()];
        Items[] data = new Items[baseData.size()];


        for (int i = 0, j = 0; j < baseData.size(); i++, j=j+2) {
            todo[i] = baseData.get(j);

        }
        for (int i = 1, j = 0; i < baseData.size();  j++, i=i+2) {
            pri[j] = baseData.get(i);
        }
        for (int i = 0; i < baseData.size(); i++) {
            data[i] = new Items(todo[i], pri[i]);
        }
        // Create the adapter to convert the array to views
        cAdapter = new CustomUserAdapter(this, R.layout.item_user, data);
        // Attach the adapter to a ListView
        lvItems = (ListView) findViewById(R.id.lvItems);
        lvItems.setAdapter(cAdapter);

        //setupListViewListener();
        setupListViewCListener();
    }

    // Switch to new cursor and update contents of ListView
    //todoAdapter.changeCursor(newCursor);

    public void onComplete(String task){
        cAdapter.notifyDataSetChanged();
    }


    public void onAddItem(View v) {
        Intent i = new Intent(TodoList.this, NewAddActivity.class);
        startActivity(i);

    }

    private void setupListViewCListener(){
        lvItems.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapter, View view, int pos,
                                            long id) {
                        Object it = adapter.getItemAtPosition(pos);
                        Items we = (Items)it;
                        iValue = we.todo;
                        onClick(view);

                        return;
                    }
                });
    }

    // launching an activity for a result
    public void onClick(View view) {
        Intent i = new Intent(TodoList.this, EditDialogActivity.class);
        i.putExtra("it", iValue);
        startActivityForResult(i, REQUEST_CODE);

    }
}
