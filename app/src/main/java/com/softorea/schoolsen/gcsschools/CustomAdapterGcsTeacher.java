package com.softorea.schoolsen.gcsschools;

/**
 * Created by Softorea on 5/24/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.databasehelper.DatabaseHandler;
import com.softorea.schoolsen.models.GcsTeacherDetails;

import java.util.ArrayList;

public class CustomAdapterGcsTeacher extends BaseAdapter {

    private static ArrayList<GcsTeacherDetails> searchArrayList;
    DatabaseHandler databaseHandler;
    private Context context;
    private LayoutInflater mInflater;

    public CustomAdapterGcsTeacher(Context context, ArrayList<GcsTeacherDetails> results) {
        searchArrayList = results;
        mInflater = LayoutInflater.from(context);
        databaseHandler = new DatabaseHandler(context);
    }

    @Override
    public int getCount() {
        return searchArrayList.size();
    }

    @Override
    public Object getItem(int p) {
        return searchArrayList.get(p);
    }

    @Override
    public long getItemId(int p) {
        return p;
    }

    @Override
    public int getViewTypeCount() {

        return 500;
    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public View getView(final int p, View v, ViewGroup parent) {
        ViewHolder holder;
        context = parent.getContext();
        if (v == null) {
            v = mInflater.inflate(R.layout.gcsteacherlistitem, null);
            holder = new ViewHolder();

            holder.name = (TextView) v.findViewById(R.id.teacher_name);
            holder.cnic = (TextView) v.findViewById(R.id.teacher_cnic);
            holder.no = (TextView) v.findViewById(R.id.teacher_phone);
            holder.gender = (TextView) v.findViewById(R.id.gender);
            holder.status = (TextView) v.findViewById(R.id.status);
            holder.info = (ImageView) v.findViewById(R.id.edit);
            holder.l1 = (LinearLayout) v.findViewById(R.id.main);


            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        holder.name.setText(searchArrayList.get(p).getTeachername());
        holder.cnic.setText(searchArrayList.get(p).getTeachercnic());
        holder.no.setText(searchArrayList.get(p).getTeacherno());
        holder.gender.setText(searchArrayList.get(p).getTeachergender());
        holder.status.setText(searchArrayList.get(p).getAttendance());
        if (searchArrayList.get(p).getAttendance().equals("Absent"))
        {
            holder.l1.setBackgroundColor(Color.parseColor("#DB674D"));
        }
        if (searchArrayList.get(p).getAttendance().equals("Present"))
        {
            holder.l1.setBackgroundColor(Color.parseColor("#7EB674"));
        }
        if (searchArrayList.get(p).getAttendance().equals("Transfer Out"))
        {
            holder.l1.setBackgroundColor(Color.parseColor("#FBE87C"));
        }
        if (searchArrayList.get(p).getAttendance().equals("Resigned"))
        {
            holder.l1.setBackgroundColor(Color.parseColor("#4FC3F7"));
        }
        holder.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent view_order_intent = new Intent(context, GcsTeacherUpdate.class);
                view_order_intent.putExtra("ID", searchArrayList.get(p).getId());
                view_order_intent.putExtra("tname", searchArrayList.get(p).getTeachername());
                view_order_intent.putExtra("tgender", searchArrayList.get(p).getTeachergender());
                view_order_intent.putExtra("tcnic", searchArrayList.get(p).getTeachercnic());
                view_order_intent.putExtra("tno", searchArrayList.get(p).getTeacherno());
                view_order_intent.putExtra("tatt", searchArrayList.get(p).getAttendance());
                view_order_intent.putExtra("tattdetails", searchArrayList.get(p).getTeacherattendancedetails());
                view_order_intent.putExtra("treplacement", searchArrayList.get(p).getReplacementavailable());
                view_order_intent.putExtra("trepname", searchArrayList.get(p).getReplacementname());
                view_order_intent.putExtra("trepgender", searchArrayList.get(p).getReplacementgender());
                context.startActivity(view_order_intent);
            }
        });
        return v;
    }

    static class ViewHolder {
        TextView name, cnic, no, gender,status;
        ImageView info;
        LinearLayout l1;
    }

}