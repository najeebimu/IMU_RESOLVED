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
import com.softorea.schoolsen.m_monthly.M_HumanResourceNonTeacherPresenceUpdate;
import com.softorea.schoolsen.m_monthly.M_NonTeacherPresenceList;
import com.softorea.schoolsen.models.NonTeacherNewDetails;

import java.util.ArrayList;

public class CustomAdapterNonTeacherNew extends BaseAdapter {

    private static ArrayList<NonTeacherNewDetails> searchArrayList;
    DatabaseHandler databaseHandler;
    private Context context;
    private LayoutInflater mInflater;

    public CustomAdapterNonTeacherNew(Context context, ArrayList<NonTeacherNewDetails> results) {
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
    public View getView(final int position, View v, ViewGroup parent) {
        ViewHolder holder;
        context = parent.getContext();

        if (v == null) {
            v = mInflater
                    .inflate(R.layout.customlist_nonteacher, null);
            holder = new ViewHolder();

            holder.name = (TextView) v.findViewById(R.id.teacher_name);
            holder.cnic = (TextView) v.findViewById(R.id.teacher_cnic);
            holder.no = (TextView) v.findViewById(R.id.teacher_phone);
            holder.designation = (TextView) v.findViewById(R.id.designation);
            holder.status = (TextView) v.findViewById(R.id.status);
            holder.l1 = (LinearLayout) v.findViewById(R.id.main);
            holder.edit = (ImageView) v.findViewById(R.id.edit);
            holder.delete = (ImageView) v.findViewById(R.id.delete);


            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        holder.name.setText(searchArrayList.get(position).getTeachername());
        holder.cnic.setText(searchArrayList.get(position).getTeachercnic());
        holder.no.setText(searchArrayList.get(position).getTeacheraccountno());
        holder.designation.setText(searchArrayList.get(position).getDesignation());
        holder.status.setText(searchArrayList.get(position).getAttendance());
        if (searchArrayList.get(position).getAttendance().equals("Absent"))
        {
            holder.l1.setBackgroundColor(Color.parseColor("#DB674D"));
        }
        if (searchArrayList.get(position).getAttendance().equals("Present"))
        {
            holder.l1.setBackgroundColor(Color.parseColor("#7EB674"));
        }
        if (searchArrayList.get(position).getAttendance().equals("Transfer Out"))
        {
            holder.l1.setBackgroundColor(Color.parseColor("#FBE87C"));
        }
        if (searchArrayList.get(position).getAttendance().equals("Resigned"))
        {
            holder.l1.setBackgroundColor(Color.parseColor("#4FC3F7"));
        }
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent view_order_intent = new Intent(context, M_HumanResourceNonTeacherPresenceUpdate.class);
                view_order_intent.putExtra("ID", searchArrayList.get(position).getId());
                view_order_intent.putExtra("tname", searchArrayList.get(position).getTeachername());
                view_order_intent.putExtra("tfather", searchArrayList.get(position).getTeacherfathername());
                view_order_intent.putExtra("tgender", searchArrayList.get(position).getTeachergender());
                view_order_intent.putExtra("tmarital", searchArrayList.get(position).getTeachermarital());
                view_order_intent.putExtra("tbps", searchArrayList.get(position).getTeacherbps());
                view_order_intent.putExtra("tcnic", searchArrayList.get(position).getTeachercnic());
                view_order_intent.putExtra("tno", searchArrayList.get(position).getTeacherno());
                view_order_intent.putExtra("tacc", searchArrayList.get(position).getTeacheraccountno());
                view_order_intent.putExtra("tdob", searchArrayList.get(position).getTeacherdob());
                view_order_intent.putExtra("tlevel", searchArrayList.get(position).getTeacherhighestlevel());
                view_order_intent.putExtra("tsubject", searchArrayList.get(position).getTeacherhigestsubject());
                view_order_intent.putExtra("tdateoffirst", searchArrayList.get(position).getTeacherdateoffirst());
                view_order_intent.putExtra("tdistrict", searchArrayList.get(position).getTeacherdistrict());
                view_order_intent.putExtra("thighest", searchArrayList.get(position).getTeacherhighestqualification());
                view_order_intent.putExtra("tdesigfirst", searchArrayList.get(position).getTeacherdesigasfirst());
                view_order_intent.putExtra("tdesig", searchArrayList.get(position).getDesignation());
                view_order_intent.putExtra("tuc", searchArrayList.get(position).getTeacheruc());
                view_order_intent.putExtra("tanydisab", searchArrayList.get(position).getTeacheranydisablity());
                view_order_intent.putExtra("ttypedisab", searchArrayList.get(position).getTeachertypedisablity());
                view_order_intent.putExtra("tatt", searchArrayList.get(position).getAttendance());
                view_order_intent.putExtra("tattTrasnferIn", searchArrayList.get(position).getAttendancetransferIn());
                view_order_intent.putExtra("tattdetailss", searchArrayList.get(position).getTeacherattendancedetails());
                view_order_intent.putExtra("tdatesince", searchArrayList.get(position).getAttendancedatesince());
                view_order_intent.putExtra("ttrasnfer", searchArrayList.get(position).getAttendancetrasnferschool());
                context.startActivity(view_order_intent);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int idno = searchArrayList.get(position).getId();
                final String tname = searchArrayList.get(position).getTeachername();
                final String tcnic = searchArrayList.get(position).getTeachercnic();
                final String tno = searchArrayList.get(position).getTeacherno();
                final String tdesignation = searchArrayList.get(position).getDesignation();
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Are you sure to delete?");
                builder1.setCancelable(false);

                builder1.setPositiveButton(
                        "Delete",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                databaseHandler.removeNonTeacher(idno);
                                ((Activity) context).finish();
                                context.startActivity(new Intent(context, M_NonTeacherPresenceList.class));
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
        TextView name, cnic, no, designation, status;
        ImageView edit, delete;
        LinearLayout l1;

    }

}