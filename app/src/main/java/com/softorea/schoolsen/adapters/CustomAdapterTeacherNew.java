package com.softorea.schoolsen.adapters;

/**
 * Created by Softorea on 5/24/2017.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.softorea.schoolsen.m_monthly.M_HumanResourceTeacherPresenceUpdate;
import com.softorea.schoolsen.m_monthly.M_TeacherPresenceList;
import com.softorea.schoolsen.models.TeacherNewDetails;

import java.util.ArrayList;

public class CustomAdapterTeacherNew extends BaseAdapter {

    private static ArrayList<TeacherNewDetails> searchArrayList;
    DatabaseHandler databaseHandler;
    private Context context;
    private LayoutInflater mInflater;

    public CustomAdapterTeacherNew(Context context, ArrayList<TeacherNewDetails> results) {
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

        return 1;
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
            v = mInflater
                    .inflate(R.layout.customlist_teacher, null);
            holder = new ViewHolder();

            holder.name = (TextView) v.findViewById(R.id.teacher_name);
            holder.cnic = (TextView) v.findViewById(R.id.teacher_cnic);
            holder.no = (TextView) v.findViewById(R.id.teacher_phone);
            holder.designation = (TextView) v.findViewById(R.id.designation);
            holder.attendance = (TextView) v.findViewById(R.id.status);
            holder.l1 = (LinearLayout) v.findViewById(R.id.main);
            holder.edit = (ImageView) v.findViewById(R.id.edit);
            holder.delete = (ImageView) v.findViewById(R.id.delete);


            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        holder.name.setText(searchArrayList.get(p).getTeachername());
        holder.cnic.setText(searchArrayList.get(p).getTeachercnic());
        holder.no.setText(searchArrayList.get(p).getTeacheraccountno());
        holder.designation.setText(searchArrayList.get(p).getDesignation());
        holder.attendance.setText(searchArrayList.get(p).getAttendance());
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
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent view_order_intent = new Intent(context, M_HumanResourceTeacherPresenceUpdate.class);
                view_order_intent.putExtra("ID", searchArrayList.get(p).getId());
                view_order_intent.putExtra("tname", searchArrayList.get(p).getTeachername());
                view_order_intent.putExtra("tfather", searchArrayList.get(p).getTeacherfathername());
                view_order_intent.putExtra("tgender", searchArrayList.get(p).getTeachergender());
                view_order_intent.putExtra("tmarital", searchArrayList.get(p).getTeachermarital());
                view_order_intent.putExtra("tbps", searchArrayList.get(p).getTeacherbps());
                view_order_intent.putExtra("tcnic", searchArrayList.get(p).getTeachercnic());
                view_order_intent.putExtra("tno", searchArrayList.get(p).getTeacherno());
                view_order_intent.putExtra("tacc", searchArrayList.get(p).getTeacheraccountno());
                view_order_intent.putExtra("tdob", searchArrayList.get(p).getTeacherdob());
                view_order_intent.putExtra("tlevel", searchArrayList.get(p).getTeacherhighestlevel());
                view_order_intent.putExtra("tsubject", searchArrayList.get(p).getTeacherhigestsubject());
                view_order_intent.putExtra("tdateoffirst", searchArrayList.get(p).getTeacherdateoffirst());
                view_order_intent.putExtra("tdistrict", searchArrayList.get(p).getTeacherdistrict());
                view_order_intent.putExtra("thighest", searchArrayList.get(p).getTeacherhighestqualification());
                view_order_intent.putExtra("tdesigfirst", searchArrayList.get(p).getTeacherdesigasfirst());
                view_order_intent.putExtra("tdesig", searchArrayList.get(p).getDesignation());
                view_order_intent.putExtra("tuc", searchArrayList.get(p).getTeacheruc());
                view_order_intent.putExtra("tanydisab", searchArrayList.get(p).getTeacheranydisablity());
                view_order_intent.putExtra("ttypedisab", searchArrayList.get(p).getTeachertypedisablity());
                view_order_intent.putExtra("tatt", searchArrayList.get(p).getAttendance());
                view_order_intent.putExtra("tattTrasnferIn", searchArrayList.get(p).getAttendanceTrasnferIn());
                view_order_intent.putExtra("tattdetailss", searchArrayList.get(p).getTeacherattendancedetails());
                view_order_intent.putExtra("tdatesince", searchArrayList.get(p).getAttendancedatesince());
                view_order_intent.putExtra("ttrasnfer", searchArrayList.get(p).getAttendancetrasnferschool());
                context.startActivity(view_order_intent);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int idno = searchArrayList.get(p).getId();
                final String tname = searchArrayList.get(p).getTeachername();
                final String tcnic = searchArrayList.get(p).getTeachercnic();
                final String tno = searchArrayList.get(p).getTeacherno();
                final String tdesignation = searchArrayList.get(p).getDesignation();
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Are you sure to delete?");
                builder1.setCancelable(false);

                builder1.setPositiveButton(
                        "Delete",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                databaseHandler.removeTeacher(idno);
                                ((Activity) context).finish();
                                context.startActivity(new Intent(context, M_TeacherPresenceList.class));
                            }
                        });

                builder1.setNegativeButton(
                        "Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });
        return v;
    }

    static class ViewHolder {
        TextView name, cnic, no, designation, attendance;
        ImageView edit, delete;
        LinearLayout l1;

    }

}