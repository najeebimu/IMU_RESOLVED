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
import com.softorea.schoolsen.m_monthly.M_SanctionedPostNonTeachingUpdate;
import com.softorea.schoolsen.m_monthly.M_SanctionedPostNonteachingList;
import com.softorea.schoolsen.models.DetailsSanctionedNonteachingPosts;

import java.util.ArrayList;

public class CustomAdapterSanctionedNonteachingPosts extends BaseAdapter {

    private static ArrayList<DetailsSanctionedNonteachingPosts> searchArrayList;
    DatabaseHandler databaseHandler;
    private Context context;
    private LayoutInflater mInflater;

    public CustomAdapterSanctionedNonteachingPosts(Context context, ArrayList<DetailsSanctionedNonteachingPosts> results) {
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
                    .inflate(R.layout.customlist_nonsanctionedposts, null);
            holder = new ViewHolder();

            holder.postcode = (TextView) v.findViewById(R.id.postcode);
            holder.desigation = (TextView) v.findViewById(R.id.designation);
            holder.bps = (TextView) v.findViewById(R.id.bps);
            holder.sanctionedposts = (TextView) v.findViewById(R.id.noofsanctioned);
            holder.edit = (ImageView) v.findViewById(R.id.edit);
            holder.delete = (ImageView) v.findViewById(R.id.delete);

            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        holder.postcode.setText(searchArrayList.get(p).getPositioncode());
        holder.desigation.setText(searchArrayList.get(p).getDesignation());
        holder.bps.setText(searchArrayList.get(p).getBPS());
        holder.sanctionedposts.setText(searchArrayList.get(p).getNoOfSanctionedPosts());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, M_SanctionedPostNonTeachingUpdate.class);
                i.putExtra("ID", searchArrayList.get(p).getId());
                i.putExtra("noOf", searchArrayList.get(p).getNoOfSanctionedPosts());
                i.putExtra("bps", searchArrayList.get(p).getBPS());
                i.putExtra("other", searchArrayList.get(p).getSpecifyothers());
                i.putExtra("Des", searchArrayList.get(p).getDesignation());
                i.putExtra("pos", searchArrayList.get(p).getPositioncode());
                context.startActivity(i);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int idno = searchArrayList.get(p).getId();
                final String positioncode = searchArrayList.get(p).getPositioncode();
                final String designation = searchArrayList.get(p).getDesignation();
                final String bps = searchArrayList.get(p).getBPS();
                final String noOfSanctioned = searchArrayList.get(p).getNoOfSanctionedPosts();
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Are you sure to delete?");
                builder1.setCancelable(false);

                builder1.setPositiveButton(
                        "Delete",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                databaseHandler.removeSanctionedPostNon(idno);
                                ((Activity) context).finish();
                                context.startActivity(new Intent(context, M_SanctionedPostNonteachingList.class));
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
        TextView postcode, desigation, bps, sanctionedposts;
        ImageView edit, delete;

    }

}