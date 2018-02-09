package com.softorea.schoolsen.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.models.DetailsAppointedBy;

import java.util.ArrayList;

public class CustomAdapterFinazliedForms extends BaseAdapter {

    private static ArrayList<DetailsAppointedBy> searchArrayList;

    private LayoutInflater mInflater;

    public CustomAdapterFinazliedForms(Context context, ArrayList<DetailsAppointedBy> results) {
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
                    .inflate(R.layout.customlist_finalizedforms, null);
            holder = new ViewHolder();

            holder.name = (TextView) v.findViewById(R.id.subjectname);
            holder.upload = (TextView) v.findViewById(R.id.btn_upload);

            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        holder.name.setText(searchArrayList.get(p).getName());
        return v;
    }

    static class ViewHolder {
        TextView name;
        TextView upload;

    }

}