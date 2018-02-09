package com.softorea.schoolsen.dialogs;

/**
 * Created by Softorea on 5/24/2017.
 */

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
import com.softorea.schoolsen.m_monthly.M_TrainingRecordUpdate;
import com.softorea.schoolsen.models.TrainingRecord;

import java.util.ArrayList;

public class DialogAdapterTraining extends BaseAdapter {

    private static ArrayList<TrainingRecord> searchArrayList;
    DatabaseHandler databaseHandler;
    private Context context;
    private LayoutInflater mInflater;

    public DialogAdapterTraining(Context context, ArrayList<TrainingRecord> results) {
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
                    .inflate(R.layout.dialogadaptertraining, null);
            holder = new ViewHolder();

            holder.title = (TextView) v.findViewById(R.id.title);
            holder.year = (TextView) v.findViewById(R.id.year);
            holder.duration = (TextView) v.findViewById(R.id.duration);
            holder.conductedby = (TextView) v.findViewById(R.id.conductedby);
            holder.attendedas = (TextView) v.findViewById(R.id.attendedas);
            holder.edit = (ImageView) v.findViewById(R.id.edit);
            holder.delete = (ImageView) v.findViewById(R.id.delete);
            holder.name = (TextView) v.findViewById(R.id.teachername);
            holder.cnic = (TextView) v.findViewById(R.id.teachercnic);

            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        holder.name.setText(searchArrayList.get(p).getName());
        holder.cnic.setText(searchArrayList.get(p).getCnic());
        holder.title.setText(searchArrayList.get(p).getTitle());
        holder.year.setText(searchArrayList.get(p).getYear());
        holder.duration.setText(searchArrayList.get(p).getDuration());
        holder.conductedby.setText(searchArrayList.get(p).getConductedby());
        holder.attendedas.setText(searchArrayList.get(p).getAttendedas());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, M_TrainingRecordUpdate.class);
                i.putExtra("ID", searchArrayList.get(p).getId());
                i.putExtra("namee", searchArrayList.get(p).getName());
                i.putExtra("cnicc", searchArrayList.get(p).getCnic());
                i.putExtra("a", searchArrayList.get(p).getTitle());
                i.putExtra("b", searchArrayList.get(p).getYear());
                i.putExtra("c", searchArrayList.get(p).getDuration());
                i.putExtra("d", searchArrayList.get(p).getConductedby());
                i.putExtra("e", searchArrayList.get(p).getAttendedas());
                context.startActivity(i);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int idno = searchArrayList.get(p).getId();
                final String visitdate = searchArrayList.get(p).getTitle();
                final String visitby = searchArrayList.get(p).getYear();
                final String duration = searchArrayList.get(p).getDuration();
                final String conductedby = searchArrayList.get(p).getConductedby();
                final String attendedas = searchArrayList.get(p).getAttendedas();
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Are you sure to delete?");
                builder1.setCancelable(false);

                builder1.setPositiveButton(
                        "Delete",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                databaseHandler.removeTrainingRecord(idno);
                                //((Activity)context).finish();
                                //context.startActivity(new Intent(context,M_HumanResourceTeacherPresence.class));
                                searchArrayList.remove(p);
                                notifyDataSetChanged();
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
        TextView name, cnic, title, year, duration, conductedby, attendedas;
        ImageView edit, delete;


    }

}