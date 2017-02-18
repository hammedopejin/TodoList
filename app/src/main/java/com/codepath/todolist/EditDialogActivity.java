package com.codepath.todolist;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v4.app.FragmentManager;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;

        import java.util.ArrayList;

/**
 * Created by hammedopejin on 2/8/17.
 */

public class EditDialogActivity extends AppCompatActivity {
    // REQUEST_CODE can be any value we like, used to determine the result type later
    private final int REQUEST_CODE = 20;


    ArrayList<String> items;
    private ListView lvItems;
    public String task;
    public String date;
    public String[] data = new String[5];
    public String note;
    public String priority;
    public String status;
    ArrayAdapter<String> itemsAdapter;
    TodoItemDatabase td;
    ArrayList<String> dataFromDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_dialog);

        items = new ArrayList<>();
        td = new TodoItemDatabase(this);
        dataFromDB = td.getAll(getIntent().getStringExtra("it"));
        task = dataFromDB.get(0);
        date = (dataFromDB.get(2) + ", " + dataFromDB.get(1)) + ", " + dataFromDB.get(3);
        note = dataFromDB.get(4);
        priority = dataFromDB.get(5);
        status = dataFromDB.get(6);
        data[0] = ("Task Name :           " + task).toString();
        data[1] = ("Due Date :               "+ date).toString();
        data[2] = ("Notes :                    " + note).toString();
        data[3] = ("Priority Level :        " + priority).toString();
        data[4] = ("Status :                   " + status).toString();
        // Create the adapter to convert the array to views
        // cAdapter = new CustomUserAdapter(this, items, 2);
        // Attach the adapter to a ListView
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        lvItems = (ListView)findViewById(R.id.listPreview);
        lvItems.setAdapter(itemsAdapter);
        items.add(data[0]);
        items.add(data[1]);
        items.add(data[2]);
        items.add(data[3]);
        items.add(data[4]);




    }
    void setTd(TodoItemDatabase td){
        this.td = td;
    }
    TodoItemDatabase getTd(){
        return td;
    }

    // launching an activity for a result
    public void onClickEdit(View view) {
        Intent i = new Intent(EditDialogActivity.this, EditItemActivity.class);
        i.putExtra("task", task);
        startActivityForResult(i, REQUEST_CODE);
    }

    public void onClickDelete(View view) {
        FragmentManager fm = getSupportFragmentManager();
        setTd(td);
        MyDeleteDialogFragment deleteDialog = MyDeleteDialogFragment.newInstance("ATTENTION !!!", task);
        //Bundle args = new Bundle();
        //args.putString("task", task);
        //deleteDialog.setArguments(args);
        deleteDialog.show(fm, "fragment_alert");
    }

}
