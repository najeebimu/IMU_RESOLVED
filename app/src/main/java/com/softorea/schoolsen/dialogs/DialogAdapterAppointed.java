package com.softorea.schoolsen.dialogs;

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
import com.softorea.schoolsen.m_monthly.M_TeacherAppointedByList;
import com.softorea.schoolsen.m_monthly.M_TeacherAppointedByUpdate;
import com.softorea.schoolsen.models.DetailsAppointedBy;

import java.util.ArrayList;

public class DialogAdapterAppointed extends BaseAdapter {

    private static ArrayList<DetailsAppointedBy> searchArrayList;
    DatabaseHandler databaseHandler;

    private LayoutInflater mInflater;
    private Context context;

    public DialogAdapterAppointed(Context context, ArrayList<DetailsAppointedBy> results) {
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
                    .inflate(R.layout.dialogadapterappointed, null);
            holder = new ViewHolder();

            holder.name = (TextView) v.findViewById(R.id.name);
            holder.appointedby = (TextView) v.findViewById(R.id.appointedby);
            holder.appointdate = (TextView) v.findViewById(R.id.appointeddate);
            holder.cnic = (TextView) v.findViewById(R.id.cnic);
            holder.edit = (ImageView) v.findViewById(R.id.edit);
            holder.delete = (ImageView) v.findViewById(R.id.delete);


            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        holder.name.setText(searchArrayList.get(p).getName());
        holder.appointedby.setText(searchArrayList.get(p).getAppointedby());
        holder.appointdate.setText(searchArrayList.get(p).getAppointedDate());
        holder.cnic.setText(searchArrayList.get(p).getCnic());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, M_TeacherAppointedByUpdate.class);
                i.putExtra("ID", searchArrayList.get(p).getId());
                i.putExtra("nme", searchArrayList.get(p).getName());
                i.putExtra("cnc", searchArrayList.get(p).getCnic());
                i.putExtra("apintby", searchArrayList.get(p).getAppointedby());
                i.putExtra("apintdate", searchArrayList.get(p).getAppointedDate());
                context.startActivity(i);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int idno = searchArrayList.get(p).getId();
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Are you sure to delete?");
                builder1.setCancelable(false);

                builder1.setPositiveButton(
                        "Delete",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                databaseHandler.removeAppointedBy(idno);
                                ((Activity) context).finish();
                                context.startActivity(new Intent(context, M_TeacherAppointedByList.class));
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
        TextView name, appointedby, appointdate, cnic;
        ImageView edit, delete;


    }

}