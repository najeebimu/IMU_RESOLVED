package com.softorea.schoolsen.lists;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.adapters.BooksDialog;
import com.softorea.schoolsen.adapters.SubjectListAdapter;
import com.softorea.schoolsen.databasehelper.DatabaseHandler;
import com.softorea.schoolsen.models.FormModel;
import com.softorea.schoolsen.models.SubjectClassModel;

import java.util.ArrayList;

public class SubjectActivity extends Activity {
    String classNumber;
    SubjectClassModel classsubject;
    ArrayList<SubjectClassModel> subjectList;
    ListView subjectListView;
    FormModel objform;
    android.content.Context Context = SubjectActivity.this;
    DatabaseHandler db;
    String subjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle dataBundle = getIntent().getExtras();
        classNumber = dataBundle.getString("classNumber");
        setContentView(R.layout.activity_classsubjects);
        objform = new FormModel();
        Context = getApplicationContext();
        db = new DatabaseHandler(Context);
        String subject = db.getSubject(classNumber);
        String subjectArray[] = subject.split(",");
        subjectListView = (ListView) findViewById(R.id.subject_list);
        subjectList = new ArrayList<SubjectClassModel>();

        for (int i = 0; i < subjectArray.length; i++) {
            classsubject = new SubjectClassModel();
            classsubject.setSubjectName(subjectArray[i]);
            subjectList.add(classsubject);
        }

        subjectListView.setOnScrollListener(new OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // TODO Auto-generated method stub
                if (scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
                    int firstVisibleItem = subjectListView
                            .getFirstVisiblePosition();
                    int visibleItemCount = subjectListView.getChildCount(); // (teacherListView.getLastVisiblePosition()
                    // -
                    // teacherListView.getFirstVisiblePosition());
                    int totalItemCount = subjectListView.getCount();

                    Log.i(Integer.toString(firstVisibleItem), "First");
                    Log.i(Integer.toString(visibleItemCount), "Visible");
                    Log.i(Integer.toString(totalItemCount), "Total");
                    for (int i = 0; i < totalItemCount; i++) {
                        // Log.i("I", Integer.toString(i));
                        try {
                            Boolean found = false;
                            for (int j = 0; j < objform.subjectSelectedList
                                    .size(); j++) {
                                Log.i("ClassNo",
                                        objform.subjectSelectedList
                                                .get(j).getClassNo());
                                Log.i("ClassNo Variable", classNumber);

                                if (classNumber
                                        .equals(objform.subjectSelectedList
                                                .get(j).getClassNo())) {
                                    Log.i("..",
                                            Integer.toString(objform.subjectSelectedList
                                                    .get(j).getPosition()
                                                    - firstVisibleItem)
                                                    + "=" + Integer.toString(i));
                                    if (objform.subjectSelectedList
                                            .get(j).getPosition()
                                            - firstVisibleItem == i) {
                                        Log.i(i + "Found", "Yes");
                                        found = true;
                                        break;
                                    }
                                }
                            }

                            try {
                                if (found) {

                                    subjectListView
                                            .getChildAt(i)
                                            .setBackgroundColor(
                                                    Color.parseColor("#CEF6F5"));

                                    // }
                                } else {
                                    subjectListView
                                            .getChildAt(i)
                                            .setBackgroundColor(
                                                    Color.parseColor("#FFFFCC"));
                                }
                            } catch (Exception ex) {
                                Log.i("Exception", ex.toString());
                            }
                        } catch (Exception ex) {
                            Log.i("Exception", ex.toString());
                        }
                    }

                }
            }

            @Override
            public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub

            }
        });

        subjectListView.setAdapter(new SubjectListAdapter(this, subjectList));

        subjectListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                String subjectName = (String) ((TextView) view
                        .findViewById(R.id.subject_name)).getText();
                BooksDialog dialog = new BooksDialog(SubjectActivity.this,
                        classNumber, subjectName, position);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                dialog.setCancelable(true);
                dialog.show();

                dialog.setOnDismissListener(new OnDismissListener() {

                    @Override
                    public void onDismiss(DialogInterface arg0) {
                        int firstVisibleItem = subjectListView
                                .getFirstVisiblePosition();
                        int visibleItemCount = subjectListView.getChildCount(); // (teacherListView.getLastVisiblePosition()
                        // -
                        // teacherListView.getFirstVisiblePosition());
                        int totalItemCount = subjectListView.getCount();

                        Log.i(Integer.toString(firstVisibleItem), "First");
                        Log.i(Integer.toString(visibleItemCount), "Visible");
                        Log.i(Integer.toString(totalItemCount), "Total");
                        for (int i = 0; i < totalItemCount; i++) {
                            // Log.i("I", Integer.toString(i));
                            try {
                                Boolean found = false;
                                for (int j = 0; j < objform.subjectSelectedList
                                        .size(); j++) {
                                    Log.i("ClassNo",
                                            objform.subjectSelectedList
                                                    .get(j).getClassNo());
                                    Log.i("ClassNo Variable", classNumber);

                                    if (classNumber
                                            .equals(objform.subjectSelectedList
                                                    .get(j).getClassNo())) {
                                        Log.i("..",
                                                Integer.toString(objform.subjectSelectedList
                                                        .get(j).getPosition()
                                                        - firstVisibleItem)
                                                        + "="
                                                        + Integer.toString(i));
                                        if (objform.subjectSelectedList
                                                .get(j).getPosition()
                                                - firstVisibleItem == i) {
                                            Log.i(i + "Found", "Yes");
                                            found = true;
                                            break;
                                        }
                                    }
                                }

                                try {
                                    if (found) {

                                        subjectListView
                                                .getChildAt(i)
                                                .setBackgroundColor(
                                                        Color.parseColor("#CEF6F5"));

                                        // }
                                    } else {
                                        subjectListView
                                                .getChildAt(i)
                                                .setBackgroundColor(
                                                        Color.parseColor("#FFFFCC"));
                                    }
                                } catch (Exception ex) {
                                    Log.i("Exception", ex.toString());
                                }
                            } catch (Exception ex) {
                                Log.i("Exception", ex.toString());
                            }
                        }

                    }
                });

            }
        });


        super.onCreate(savedInstanceState);
    }

}
