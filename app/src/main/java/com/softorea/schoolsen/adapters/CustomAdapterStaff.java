package com.softorea.schoolsen.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.models.DetailsStaff;

import java.util.ArrayList;

public class CustomAdapterStaff extends BaseAdapter {

    private static ArrayList<DetailsStaff> searchArrayList;

    private LayoutInflater mInflater;

    public CustomAdapterStaff(Context context, ArrayList<DetailsStaff> results) {
        searchArrayList = results;
        mInflater = LayoutInflater.from(context);
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
    public View getView(int p, View v, ViewGroup parent) {
        ViewHolder holder;

        if (v == null) {
            v = mInflater
                    .inflate(R.layout.customlist_humanresource, null);
            holder = new ViewHolder();

            holder.staffname = (TextView) v.findViewById(R.id.name);
            holder.staffno = (TextView) v.findViewById(R.id.personalno);
            holder.staffcnic = (TextView) v.findViewById(R.id.cnic);
            holder.staffdesignation = (TextView) v.findViewById(R.id.designation);

            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        holder.staffname.setText(searchArrayList.get(p).getStaffname());
        holder.staffno.setText(searchArrayList.get(p).getStaffno());
        holder.staffcnic.setText(searchArrayList.get(p).getStaffcnic());
        holder.staffdesignation.setText(searchArrayList.get(p).getStaffdesignation());
        return v;
    }

    static class ViewHolder {
        TextView staffname, staffno, staffcnic, staffdesignation;

    }

}