package com.softorea.schoolsen.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.models.SubjectClassModel;

import java.util.ArrayList;


public class SubjectListAdapter extends BaseAdapter {

    ArrayList<SubjectClassModel> myList = new ArrayList<SubjectClassModel>();
    LayoutInflater inflater;
    Context context;

    public SubjectListAdapter(Context context, ArrayList<SubjectClassModel> mylist) {
        this.context = context;
        this.myList = mylist;
        inflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {

        return myList.size();
    }

    @Override
    public Object getItem(int position) {

        return myList.get(position);
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewBinder mViewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.subjectlist_view, null);
            mViewHolder = new ViewBinder();
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewBinder) convertView.getTag();
        }

        if (myList.get(position).getSubjectName() == null) {
            mViewHolder.txtName = detail(convertView, R.id.subject_name, "-");
        } else {
            mViewHolder.txtName = detail(convertView, R.id.subject_name, myList
                    .get(position).getSubjectName());
        }

        return convertView;
    }

    private TextView detail(View v, int resId, String text) {
        TextView tv = (TextView) v.findViewById(resId);
        tv.setText(text);
        return tv;
    }

    private class ViewBinder {

        TextView txtName;
        ImageView btnInfo;

    }

}
