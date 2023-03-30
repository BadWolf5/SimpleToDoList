package com.example.simpletodolist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import io.objectbox.Box;

public class MyListAdapter extends ArrayAdapter<Task> {
    private String TAG = "BadWolf";
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
        CheckBox cb = convertView.findViewById(R.id.chkBox);

//        Populate the data
        tvTitle.setText(task.getTaskName());
        tvList.setText(task.getList());

        if(task.getCompleted()){
            cb.setChecked(false);
            notifyDataSetChanged();
        }

        if (!task.getCompleted()){
            task.setCompleted(true);

            System.out.print("name: "+task.getTaskName());
            System.out.print("name:"+task.getCompleted());
            notifyDataSetChanged();
        }
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        String date = dateFormat.format(task.getDueDate());
//        String notiDate = dateFormat.format(task.getRemindMe());
//        tvDue.setText(date);
//        tvNoti.setText(notiDate);

        return convertView;
    }

}
