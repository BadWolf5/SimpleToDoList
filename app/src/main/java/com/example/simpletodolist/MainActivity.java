package com.example.simpletodolist;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import io.objectbox.Box;

public class MainActivity extends AppCompatActivity {
    private MyListAdapter adapter;
    private String TAG = "BadWolf";
    private int callNumber= 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ObjectBox.init(this);
        DisplayData();


        //Set the function to the Add button
        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), TaskInput.class);
            view.getContext().startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        DisplayData();
    }

    private void DisplayData(){
        Box<Task> taskBox = ObjectBox.getBoxStore().boxFor(Task.class);

//        taskBox.removeAll();
//        Construct the data source
        ArrayList<Task> tasks = new ArrayList<Task>();
        tasks.addAll(taskBox.getAll());
        Log.i(TAG, "The size of the list is " + tasks.size());

        Log.i(TAG, "Number method was called: "+ callNumber);
        callNumber++;
//        Create the adapter to covert the array to views
        adapter = new MyListAdapter(this, (ArrayList<Task>) tasks);
        adapter.notifyDataSetChanged();
//        Attach the adapter to Listview
        ListView listView = findViewById(R.id.rList);
        listView.setAdapter(adapter);



    }


}