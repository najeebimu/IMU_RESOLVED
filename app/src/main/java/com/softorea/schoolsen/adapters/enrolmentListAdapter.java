package com.softorea.schoolsen.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.lists.FormService;
import com.softorea.schoolsen.models.Enrolment;
import com.softorea.schoolsen.models.EnrolmentDialog;

import java.util.ArrayList;

public class enrolmentListAdapter extends BaseAdapter {

    ArrayList<Enrolment> myList = new ArrayList<Enrolment>();
    LayoutInflater inflater;
    Context context;
    int counter = 0;
    ListView enList;

    public enrolmentListAdapter(Context context, ArrayList<Enrolment> mylist, ListView enrolmentListView) {
        this.context = context;
        this.myList = mylist;
        this.enList = enrolmentListView;
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewBinder mViewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.enrolment_view, null);
            mViewHolder = new ViewBinder();
            convertView.setTag(mViewHolder);

            if (FormService.objform.getEnrolmentListComplete() == 1) {
                TextView tv = (TextView) convertView
                        .findViewById(R.id.class_name);
                tv.setTextColor(Color.BLACK);
            }

        } else {
            mViewHolder = (ViewBinder) convertView.getTag();
        }

        if (myList.get(position).getClassName() == null) {
            mViewHolder.txtName = detail(convertView, R.id.class_name, "-");
        } else {
            mViewHolder.txtName = detail(convertView, R.id.class_name, myList
                    .get(position).getClassName());

        }


        mViewHolder.btnInfo = (ImageView) convertView
                .findViewById(R.id.btn_info);

        mViewHolder.btnInfo.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                counter++;

                if (counter < myList.size()) {
                    //vp.setPagingEnabled(true);
                } else if (counter == myList.size()) {
                    //vp.setPagingEnabled(true);
                    FormService.objform.setEnrolmentListComplete(1);
                }

                String classNo = mViewHolder.txtName.getText().toString();
                EnrolmentDialog dialog = new EnrolmentDialog(context, classNo,
                        position, myList.size(), enList);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                dialog.setCancelable(false);
                dialog.show();

                dialog.setOnDismissListener(new OnDismissListener() {

                    @Override
                    public void onDismiss(DialogInterface dialog) {

                        hideKeyboard();
                        int firstVisibleItem = enList.getFirstVisiblePosition();
                        int visibleItemCount = (enList.getLastVisiblePosition() - enList.getFirstVisiblePosition());
                        int totalItemCount = enList.getChildCount();

                        Log.d(Integer.toString(firstVisibleItem), "First");
                        Log.d(Integer.toString(visibleItemCount), "Visible");
                        Log.d(Integer.toString(totalItemCount), "Total");
                        for (int i = firstVisibleItem; i < totalItemCount; i++) {
                            Log.d("I", Integer.toString(i));
                            try {
                                Boolean found = false;
                                for (int j = 0; j < FormService.objform.itemSelectedList
                                        .size(); j++) {
                                    Log.d("J", Integer.toString(j));
                                    Log.d("..",
                                            Integer.toString(FormService.objform.itemSelectedList
                                                    .get(j).getPosition()
                                                    - firstVisibleItem)
                                                    + "=" + Integer.toString(i));
                                    if (FormService.objform.itemSelectedList
                                            .get(j).getPosition()
                                            - firstVisibleItem == i) {
                                        Log.d("Found", "Yes");
                                        found = true;
                                        break;
                                    }
                                }

                                try {
                                    if (found) {
                                        enList.getChildAt(i)
                                                .setBackgroundColor(
                                                        Color.parseColor("#CEF6F5"));
                                    } else {
                                        enList.getChildAt(i)
                                                .setBackgroundColor(Color.WHITE);
                                    }
                                } catch (Exception ex) {
                                }
                            } catch (Exception ex) {
                            }
                        }

                    }
                });
            }
        });
        return convertView;
    }

    private void hideKeyboard() {
        // Check if no view has focus:
        InputMethodManager imm = (InputMethodManager) context.getSystemService(context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
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
