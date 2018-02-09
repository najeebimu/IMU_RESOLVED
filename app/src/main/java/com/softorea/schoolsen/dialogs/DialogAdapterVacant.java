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
import com.softorea.schoolsen.m_monthly.M_VacantPositionsList;
import com.softorea.schoolsen.m_monthly.M_VacantPositionsUpdate;
import com.softorea.schoolsen.models.DetailsVacant;

import java.util.ArrayList;

public class DialogAdapterVacant extends BaseAdapter {

    private static ArrayList<DetailsVacant> searchArrayList;
    DatabaseHandler databaseHandler;
    private Context context;
    private LayoutInflater mInflater;

    public DialogAdapterVacant(Context context, ArrayList<DetailsVacant> results) {
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
                    .inflate(R.layout.dialogadaptervacant, null);
            holder = new ViewHolder();

            holder.vacantdesignation = (TextView) v.findViewById(R.id.vacantdesignation);
            holder.vacantseats = (TextView) v.findViewById(R.id.vacantseats);
            holder.edit = (ImageView) v.findViewById(R.id.edit);
            holder.delete = (ImageView) v.findViewById(R.id.delete);

            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        holder.vacantdesignation.setText(searchArrayList.get(p).getVacantdesignation());
        holder.vacantseats.setText(searchArrayList.get(p).getVacantseats());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, M_VacantPositionsUpdate.class);
                i.putExtra("ID", searchArrayList.get(p).getId());
                i.putExtra("vacantdesign", searchArrayList.get(p).getVacantdesignation());
                i.putExtra("vacantseats", searchArrayList.get(p).getVacantseats());
                context.startActivity(i);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int idno = searchArrayList.get(p).getId();
                final String visitdate = searchArrayList.get(p).getVacantdesignation();
                final String visitby = searchArrayList.get(p).getVacantseats();
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Are you sure to delete?");
                builder1.setCancelable(false);

                builder1.setPositiveButton(
                        "Delete",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                databaseHandler.removeVacant(idno);
                                notifyDataSetChanged();
                                ((Activity) context).finish();
                                context.startActivity(new Intent(context, M_VacantPositionsList.class));
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
        TextView vacantdesignation, vacantseats;
        ImageView edit, delete;

    }

}