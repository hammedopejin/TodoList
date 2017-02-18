package com.codepath.todolist;

    import android.content.Intent;
    import android.os.Bundle;
    import android.support.v7.app.AppCompatActivity;
    import android.view.View;
    import android.widget.AdapterView;
    import android.widget.DatePicker;
    import android.widget.EditText;
    import android.widget.Spinner;

    /**
     * Created by hammedopejin on 2/9/17.
     */

    public class NewAddActivity extends AppCompatActivity {
        // REQUEST_CODE can be any value we like, used to determine the result type later
        private final int REQUEST_CODE = 20;





        TodoItemDatabase td;

        private String task;
        private String day;
        private String month;
        private String year;
        private String note;
        private String iPriority;
        private String iStatus;
        DatePicker eDate;

        private Spinner priority;
        private Spinner status;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add_new);
            eDate = (DatePicker) findViewById(R.id.dueDate);
            eDate.setMinDate(System.currentTimeMillis() - 1000);
            td = new TodoItemDatabase(this);
            addListenerOnPriorityItemSelection();
            addListenerOnStatusItemSelection();
        }


        public void onAddItem(View v) {
            EditText eTask = (EditText)findViewById(R.id.task);
            task = eTask.getText().toString();
            day = String.valueOf(eDate.getDayOfMonth());
            month = String.valueOf(eDate.getMonth()+ 1);
            year = String.valueOf(eDate.getYear());

            EditText eNote = (EditText)findViewById(R.id.note);
            note = eNote.getText().toString();









            td.addItem(task, day, month, year, note, iPriority, iStatus);//writeItems();


            Intent i = new Intent(NewAddActivity.this, TodoList.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }

        public void onCancel(View view){
            Intent i = new Intent(NewAddActivity.this, TodoList.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }

        private void addListenerOnPriorityItemSelection(){
            priority = (Spinner) findViewById(R.id.priority);
            priority.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    iPriority   = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

        private void addListenerOnStatusItemSelection(){
            status = (Spinner) findViewById(R.id.status);
            status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    iStatus   = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

    }

