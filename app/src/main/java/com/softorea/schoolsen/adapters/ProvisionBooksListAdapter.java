package com.softorea.schoolsen.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.models.ProvisionBooks;

import java.util.ArrayList;

public class ProvisionBooksListAdapter extends BaseAdapter {

    ArrayList<ProvisionBooks> myList = new ArrayList<ProvisionBooks>();
    LayoutInflater inflater;
    Context context;
    ListView proList;

    public ProvisionBooksListAdapter(Context context, ArrayList<ProvisionBooks> mylist, ListView provisionClassListView) {
        this.context = context;
        this.myList = mylist;
        this.proList = provisionClassListView;

        inflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return myList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return myList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewBinder mViewHolder;
        if (convertView == null) {

            convertView = inflater.inflate(R.layout.enrolment_view, null);
            mViewHolder = new ViewBinder();
            convertView.setTag(mViewHolder);

        } else {

            mViewHolder = (ViewBinder) convertView.getTag();

        }

        if (myList.get(position).getClassName() == null) {
            mViewHolder.txtName = detail(convertView, R.id.class_name, "-");
        } else {
            mViewHolder.txtName = detail(convertView, R.id.class_name, myList
                    .get(position).getClassName());
        }

        return convertView;
    }


    private TextView detail(View v, int resId, String text) {
        TextView tv = (TextView) v.findViewById(resId);
        tv.setText(text);
        return tv;
    }

    private class ViewBinder {

        TextView txtName, PhoneNo, cnic;
        ImageView btnInfo;

    }

}
