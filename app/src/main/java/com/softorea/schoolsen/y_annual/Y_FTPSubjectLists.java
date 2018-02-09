package com.softorea.schoolsen.y_annual;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.adapters.ProvisionBooksListAdapter;
import com.softorea.schoolsen.adapters.SubjectListAdapter;
import com.softorea.schoolsen.databasehelper.DatabaseHandler;
import com.softorea.schoolsen.models.ProvisionBooks;
import com.softorea.schoolsen.models.SubjectClassModel;

import java.util.ArrayList;

/**
 * Created by Arslan on 10/29/2017.
 */

public class Y_FTPSubjectLists extends Activity {
    ProvisionBooks details;
    ArrayList<SubjectClassModel> subjectList;
    SubjectClassModel classsubject;
    ArrayList<ProvisionBooks> addas = new ArrayList<ProvisionBooks>();
    ProvisionBooksListAdapter cusadapter;
    DatabaseHandler databasehandler;
    String emis;
    String classNumber;
    ArrayList<ProvisionBooks> teacherList;
    private ListView listcontent = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classsubjects);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            classNumber = extras.getString("valueofclass");
            // and get whatever type user account id is
        }
        databasehandler = new DatabaseHandler(Y_FTPSubjectLists.this);
        listcontent = (ListView) findViewById(R.id.subject_list);
        final String subject = databasehandler.getSubject(classNumber);
        String subjectArray[] = subject.split(",");
        subjectList = new ArrayList<SubjectClassModel>();

        for (int i = 0; i < subjectArray.length; i++) {
            classsubject = new SubjectClassModel();
            classsubject.setSubjectName(subjectArray[i]);
            subjectList.add(classsubject);
        }

        listcontent.setAdapter(new SubjectListAdapter(this, subjectList));

        listcontent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String subjectName = subjectList.get(position).getSubjectName();
                Intent i = new Intent(Y_FTPSubjectLists.this, Y_ProvisionFreeBooks.class);
                i.putExtra("subjectname", subjectName);
                i.putExtra("classnameo", classNumber);
                startActivity(i);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        });
    }
}
