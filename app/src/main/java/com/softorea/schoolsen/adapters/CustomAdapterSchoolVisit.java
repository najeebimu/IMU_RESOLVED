package com.softorea.schoolsen.adapters;

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
import com.softorea.schoolsen.m_monthly.M_SchoolVisitList;
import com.softorea.schoolsen.m_monthly.M_SchoolVisitsUpdate;
import com.softorea.schoolsen.models.DetailsSchoolVisit;

import java.util.ArrayList;

public class CustomAdapterSchoolVisit extends BaseAdapter {

    private static ArrayList<DetailsSchoolVisit> searchArrayList;
    DatabaseHandler databaseHandler;
    private Context context;
    private LayoutInflater mInflater;

    public CustomAdapterSchoolVisit(Context context, ArrayList<DetailsSchoolVisit> results) {
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
                    .inflate(R.layout.customlist_schoolvisit, null);
            holder = new ViewHolder();

            holder.visitdate = (TextView) v.findViewById(R.id.visit_date);
            holder.visitby = (TextView) v.findViewById(R.id.visit_by);
            holder.edit = (ImageView) v.findViewById(R.id.edit);
            holder.delete = (ImageView) v.findViewById(R.id.delete);
            holder.delete.setTag(searchArrayList.get(p).getId());

            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        holder.visitdate.setText(searchArrayList.get(p).getVisitdate());
        holder.visitby.setText(searchArrayList.get(p).getVisitby());


        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, M_SchoolVisitsUpdate.class);
                i.putExtra("ID", searchArrayList.get(p).getId());
                i.putExtra("visitby", searchArrayList.get(p).getVisitby());
                i.putExtra("visitdate", searchArrayList.get(p).getVisitdate());
                i.putExtra("other", searchArrayList.get(p).getDesignationother());
                context.startActivity(i);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final int idno = searchArrayList.get(p).getId();
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Are you sure to delete?");
                builder1.setCancelable(false);

                builder1.setPositiveButton(
                        "Delete",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //searchArrayList.remove(p);
                                databaseHandler.removeSchoolvisit(idno);
                                ((Activity) context).finish();
                                context.startActivity(new Intent(context, M_SchoolVisitList.class));
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
        TextView visitdate, visitby;
        ImageView edit, delete;

    }

}