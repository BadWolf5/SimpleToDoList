package com.example.simpletodolist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MyListAdapter extends ArrayAdapter<Task> {
    public MyListAdapter(Context context, ArrayList<Task> tasks){
        super(context,0, tasks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        Get the data item for this position
        Task task = getItem(position);
//        Check if view is being reused
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row, parent, false);
        }
//        Lookup view for data population
        TextView tvTitle = convertView.findViewById(R.id.mainText);
        TextView tvDue = convertView.findViewById(R.id.dateLabel);
        TextView tvNoti = convertView.findViewById(R.id.notiLabel);
        TextView tvList = convertView.findViewById(R.id.listLabel);

//        Populate the data
        tvTitle.setText(task.getTaskName());
        tvList.setText(task.getList());

        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        String date = dateFormat.format(task.getDueDate());
//        String notiDate = dateFormat.format(task.getRemindMe());
//        tvDue.setText(date);
//        tvNoti.setText(notiDate);

        return convertView;
    }

}
