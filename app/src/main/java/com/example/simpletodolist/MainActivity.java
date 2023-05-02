package com.example.simpletodolist;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import io.objectbox.Box;
import io.objectbox.query.Query;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "BadWolf";

    private Box<Task> taskBox;

    //    Sorting it by completion. The completed ones will be at the bottom
    private Query<Task> query;

    //    Construct the data source
    private ArrayList<Task> tasks;

    private MyListAdapter adapter;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ObjectBox.init(this);
//        Display Data
        taskBox = ObjectBox.getBoxStore().boxFor(Task.class);
        query = taskBox.query().order(Task_.completed, 0).build();
        tasks = (ArrayList<Task>) query.find();
        adapter = new MyListAdapter(this, tasks);
        listView = findViewById(R.id.rList);
        listView.setAdapter(adapter);

//        Set the function to the Add button
        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), TaskInput.class);
            view.getContext().startActivity(intent);
        });

//        Check the list
        listView.setOnItemClickListener((parent, view, position, id) -> {
//                Tap to mark the cell as Completed.
            getDataCell(position);
            DisplayData();

        });
//        Clear the database
//        taskBox.removeAll();
    }

    @Override
    protected void onResume() {
        super.onResume();
        DisplayData();
    }

    private void DisplayData() {
//        Construct the data source
        Query<Task> query = taskBox.query().order(Task_.completed, 0).build();
        tasks.clear();
        tasks = (ArrayList<Task>) query.find();
//        Create the adapter to covert the array to views
        MyListAdapter adapter = new MyListAdapter(this, tasks);

//        Attach the adapter to Listview
        listView = findViewById(R.id.rList);
        listView.setAdapter(adapter);

    }

    private void getDataCell(int position) {
//        Get value from StoreBox from the cell clicked
        Task task = taskBox.get(tasks.get(position).getId());
        Log.i(TAG, "Actual task name: " + task.getTaskName() + " id: " + task.getId() + " completed : " + task.getCompleted());

//        Change to True or false on tap
        if (task.getCompleted()) {
            task.setCompleted(false);
            taskBox.put(task);
        } else {
            task.setCompleted(true);
            taskBox.put(task);
        }

        adapter = new MyListAdapter(this, tasks);

//        Attach the adapter to Listview
        listView = findViewById(R.id.rList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.getItem(position);
        query.close();

    }

}