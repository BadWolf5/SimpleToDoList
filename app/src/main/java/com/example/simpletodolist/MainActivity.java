package com.example.simpletodolist;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import io.objectbox.Box;
import io.objectbox.query.Query;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "BadWolf";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ObjectBox.init(this);
        DisplayData();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.YEAR, 1111);
        Log.i(TAG, "The Date is :" + dateFormat.format(calendar.getTime()));


//        Set the function to the Add button
        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), TaskInput.class);
            view.getContext().startActivity(intent);
        });



//       Make the ListView clickable
        ListView lv = findViewById(R.id.rList);
        lv.setClickable(true);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "onItemClick: The id is " + position);




            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        DisplayData();
    }

    private void DisplayData() {
        Box<Task> taskBox = ObjectBox.getBoxStore().boxFor(Task.class);

//        Sorting it by completion. The completed ones will be at the bottom
        Query<Task> query = taskBox.query().order(Task_.completed, 0).build();

        // taskBox.removeAll();
//        Construct the data source
        ArrayList<Task> tasks = (ArrayList<Task>) query.find();
//        Create the adapter to covert the array to views
        MyListAdapter adapter = new MyListAdapter(this,tasks);

//        Attach the adapter to Listview
        ListView listView = findViewById(R.id.rList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }

}