package com.softorea.schoolsen.dialogs;

/**
 * Created by Softorea on 5/24/2017.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.databasehelper.DatabaseHandler;
import com.softorea.schoolsen.m_monthly.M_HumanResourceTeacherPresenceUpdate;
import com.softorea.schoolsen.m_monthly.M_TeacherPresenceList;
import com.softorea.schoolsen.models.TeacherNewDetails;

import java.util.ArrayList;

public class DialogAdpaterTeacher extends BaseAdapter {

    private static ArrayList<TeacherNewDetails> searchArrayList;
    DatabaseHandler databaseHandler;
    private Context context;
    private LayoutInflater mInflater;

    public DialogAdpaterTeacher(Context context, ArrayList<TeacherNewDetails> results) {
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
    public View getView(final int p, View v, ViewGroup parent) {
        ViewHolder holder;
        context = parent.getContext();
        if (v == null) {
            v = mInflater
                    .inflate(R.layout.dialogadapterteacher, null);
            holder = new ViewHolder();

            holder.name = (TextView) v.findViewById(R.id.teacher_name);
            holder.cnic = (TextView) v.findViewById(R.id.teacher_cnic);
            holder.no = (TextView) v.findViewById(R.id.teacher_phone);
            holder.designation = (TextView) v.findViewById(R.id.designation);
            holder.attendance = (TextView) v.findViewById(R.id.status);
            holder.attdetails = (TextView) v.findViewById(R.id.statusdetails);
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
        holder.attdetails.setText(searchArrayList.get(p).getTeacherattendancedetails());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*SharedPreferences preferences = context.getSharedPreferences("valuesiop", context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("toname", searchArrayList.get(p).getTeachername());
                editor.putString("tofather", searchArrayList.get(p).getTeachername());
                editor.putString("togender", searchArrayList.get(p).getTeachergender());
                editor.putString("tomarital", searchArrayList.get(p).getTeachermarital());
                editor.putString("tobps", searchArrayList.get(p).getTeacherbps());
                editor.putString("tono", searchArrayList.get(p).getTeacherno());
                editor.putString("toaccno", searchArrayList.get(p).getTeacheraccountno());
                editor.putString("tocnic", searchArrayList.get(p).getTeachercnic());
                editor.putString("todob", searchArrayList.get(p).getTeacherdob());
                editor.putString("tolevel", searchArrayList.get(p).getTeacherhighestlevel());
                editor.putString("tosubject", searchArrayList.get(p).getTeacherhigestsubject());
                editor.putString("todatefirst", searchArrayList.get(p).getTeacherdateoffirst());
                editor.putString("todistrict", searchArrayList.get(p).getTeacherdistrict());
                editor.putString("toqualification", searchArrayList.get(p).getTeacherhighestqualification());
                editor.putString("todesigfrist", searchArrayList.get(p).getTeacherdesigasfirst());
                editor.putString("todesig", searchArrayList.get(p).getDesignation());
                editor.putString("touc", searchArrayList.get(p).getTeacheruc());
                editor.putString("toany", searchArrayList.get(p).getTeacheranydisablity());
                editor.putString("totype", searchArrayList.get(p).getTeachertypedisablity());
                editor.putString("toattendance", searchArrayList.get(p).getAttendance());
                editor.putString("todetails", searchArrayList.get(p).getTeacherattendancedetails());
                editor.putString("todatesince", searchArrayList.get(p).getAttendancedatesince());
                editor.putString("totrasnfer", searchArrayList.get(p).getAttendancetrasnferschool());
                editor.apply();*/
                //context.startActivity(new Intent(context,M_HumanResourceTeacherPresenceUpdate.class));
                Intent view_order_intent = new Intent(context, M_HumanResourceTeacherPresenceUpdate.class);
                //view_order_intent.putExtra("clicked","ab3");
                context.startActivity(view_order_intent);
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
                //databasehandler.removeSingleContact(name);
                //cusadapter.notifyDataSetChanged();
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
        TextView name, cnic, no, designation, attendance,attdetails;
        ImageView edit, delete;

    }

}