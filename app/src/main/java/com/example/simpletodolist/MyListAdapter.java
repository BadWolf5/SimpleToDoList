package com.example.simpletodolist;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import io.objectbox.Box;

public class MyListAdapter extends ArrayAdapter<Task> {
    private String TAG = "BadWolf";

    public MyListAdapter(Context context, ArrayList<Task> tasks) {
        super(context, 0, tasks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        Get the data item for this position
        Task task = getItem(position);

        Box<Task> taskBox = ObjectBox.getBoxStore().boxFor(Task.class);
        Task nTask = taskBox.get(task.getId());
//        Check if view is being reused
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row, parent, false);
        }
//        Lookup view for data population
        TextView tvTitle = convertView.findViewById(R.id.mainText);
        TextView tvDue = convertView.findViewById(R.id.dateLabel);
        TextView tvNoti = convertView.findViewById(R.id.notiLabel);
        TextView tvList = convertView.findViewById(R.id.listLabel);
        TextView repLabel = convertView.findViewById(R.id.repeatLabel);
        ImageView iv = convertView.findViewById(R.id.chkBox);

        ImageView dueImg = convertView.findViewById(R.id.dueLabel);
        ImageView notiIcon = convertView.findViewById(R.id.notiIcon);
        ImageView repImg = convertView.findViewById(R.id.repeatImg);

//        Set up date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

//        Populate the data
        tvTitle.setText(task.getTaskName());
        tvList.setText(task.getList());

        // Dates setup
        if (task.getDueDate() != null) {
            tvDue.setText(dateFormat.format(task.getDueDate()));
        } else {
            tvDue.setVisibility(View.GONE);
            dueImg.setVisibility(View.GONE);
        }

        if (task.getRemindMe() != null) {
            tvNoti.setText(dateFormat.format(task.getRemindMe()));
        } else {
            tvNoti.setVisibility(View.GONE);
            notiIcon.setVisibility(View.GONE);
        }

        if (!task.getRepeat().isEmpty()){
            repLabel.setText(task.getRepeat());
        } else {
            repLabel.setVisibility(View.GONE);
            repImg.setVisibility(View.GONE);
        }

//        Checkbox functionality
        if (!task.getCompleted()) {
            iv.setVisibility(View.INVISIBLE);
            notifyDataSetChanged();
        } else {
            iv.setVisibility(View.VISIBLE);
            notifyDataSetChanged();

        }
        return convertView;
    }

}
