package com.example.simpletodolist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

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
        CheckBox cb = convertView.findViewById(R.id.chkBox);

//        Add functionality to the checkbox

//        Populate the data
        tvTitle.setText(task.getTaskName());
        tvList.setText(task.getList());

        if (task.getCompleted() == false) {
            cb.setChecked(false);
            notifyDataSetChanged();
        } else {
            cb.setChecked(true);
            notifyDataSetChanged();

        }
        /*@SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = dateFormat.format(task.getDueDate());
        String notiDate = dateFormat.format(task.getRemindMe());
        tvDue.setText(date);
        tvNoti.setText(notiDate);
*/
        return convertView;
    }

}
