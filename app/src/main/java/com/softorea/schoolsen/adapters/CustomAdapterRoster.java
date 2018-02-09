package com.softorea.schoolsen.adapters;

/**
 * Created by Softorea on 5/24/2017.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.models.RosterDetails;

import java.util.ArrayList;

public class CustomAdapterRoster extends BaseAdapter {

    private static ArrayList<RosterDetails> searchArrayList;

    private LayoutInflater mInflater;

    public CustomAdapterRoster(Context context, ArrayList<RosterDetails> results) {
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
                    .inflate(R.layout.customlist_getroaster, null);
            holder = new ViewHolder();

            holder.emis = (TextView) v.findViewById(R.id.emiscode);
            holder.visittype = (TextView) v.findViewById(R.id.visittype);
            holder.school = (TextView) v.findViewById(R.id.schoolname);

            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        holder.emis.setText(searchArrayList.get(p).getEmis());
        holder.visittype.setText(searchArrayList.get(p).getVisit());
        holder.school.setText(searchArrayList.get(p).getSchool());
        return v;
    }

    static class ViewHolder {
        TextView emis, visittype, school;

    }

}