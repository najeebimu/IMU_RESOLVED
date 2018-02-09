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
import com.softorea.schoolsen.lists.SchoolInformationDetails;

import java.util.ArrayList;

public class CustomAdapterSchoolInformation extends BaseAdapter {

    private static ArrayList<SchoolInformationDetails> searchArrayList;

    private LayoutInflater mInflater;

    public CustomAdapterSchoolInformation(Context context, ArrayList<SchoolInformationDetails> results) {
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
                    .inflate(R.layout.customlist_schoolinformation, null);
            holder = new ViewHolder();

            holder.emiscode = (TextView) v.findViewById(R.id.emiscode);
            holder.schoolname = (TextView) v.findViewById(R.id.schoolname);
            holder.gender = (TextView) v.findViewById(R.id.gender);
            holder.level = (TextView) v.findViewById(R.id.level);
            holder.ddocode = (TextView) v.findViewById(R.id.ddo_code);
            holder.district = (TextView) v.findViewById(R.id.district);
            holder.tehsil = (TextView) v.findViewById(R.id.tehsil);
            holder.ucname = (TextView) v.findViewById(R.id.ucname);
            holder.location = (TextView) v.findViewById(R.id.location);
            holder.zone = (TextView) v.findViewById(R.id.schoolzone);
            holder.nano = (TextView) v.findViewById(R.id.nano);
            holder.pkno = (TextView) v.findViewById(R.id.pkno);
            holder.circleofficename = (TextView) v.findViewById(R.id.circleofficename);

            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        holder.emiscode.setText(searchArrayList.get(p).getEmiscode());
        holder.schoolname.setText(searchArrayList.get(p).getSchoolname());
        holder.gender.setText(searchArrayList.get(p).getGender());
        holder.level.setText(searchArrayList.get(p).getLevel());
        holder.ddocode.setText(searchArrayList.get(p).getDdocode());
        holder.district.setText(searchArrayList.get(p).getDistrict());
        holder.tehsil.setText(searchArrayList.get(p).getTehsil());
        holder.ucname.setText(searchArrayList.get(p).getUcname());
        holder.location.setText(searchArrayList.get(p).getLocation());
        holder.zone.setText(searchArrayList.get(p).getSchoolzone());
        holder.nano.setText(searchArrayList.get(p).getNano());
        holder.pkno.setText(searchArrayList.get(p).getPkno());
        holder.circleofficename.setText(searchArrayList.get(p).getCircleofficename());
        return v;
    }

    static class ViewHolder {
        TextView emiscode, schoolname, gender, level, ddocode, district, tehsil, ucname, location, zone, nano, pkno, circleofficename;

    }

}