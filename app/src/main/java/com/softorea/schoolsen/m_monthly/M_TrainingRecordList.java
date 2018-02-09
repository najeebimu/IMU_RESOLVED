package com.softorea.schoolsen.m_monthly;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.adapters.CustomAdapterTrainingRecord;
import com.softorea.schoolsen.databasehelper.DatabaseHandler;
import com.softorea.schoolsen.models.TrainingRecord;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Softorea on 11/3/2017.
 */

public class M_TrainingRecordList extends Activity {
    TrainingRecord details;
    TextView t1;
    Button left, right;
    ArrayList<TrainingRecord> addas = new ArrayList<TrainingRecord>();
    CustomAdapterTrainingRecord cusadapter;
    ArrayList<TrainingRecord> teacherList;
    DatabaseHandler databasehandle;
    String emis;
    EditText searchbox;
    private ListView listcontent = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_teachertraininglist);
        databasehandle = new DatabaseHandler(M_TrainingRecordList.this);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        emis = preferences.getString("emiscode", "");
        searchbox = (EditText) findViewById(R.id.searchbox);
        listcontent = (ListView) findViewById(R.id.teacher_list);
        teacherList = databasehandle.getTrainingRecord(emis);
        SavingInDb();

        t1 = (TextView) findViewById(R.id.addtraining);
        left = (Button) findViewById(R.id.btn_left);
        right = (Button) findViewById(R.id.btn_right);

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(M_TrainingRecordList.this, M_TeacherPresenceList.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(M_TrainingRecordList.this, NonTeacherWebserviceMainList.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(M_TrainingRecordList.this, M_TrainingRecord.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });

        /*searchbox.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                String text = searchbox.getText().toString().toLowerCase(Locale.getDefault());
                //cusadapter.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub
            }
        });*/

    }

    private void SavingInDb() {
        addas.clear();
        //final DatabaseHandler databaseHandler=new DatabaseHandler(this);
        //final ArrayList<ArrayList<Object>> data =  databaseHandler.abcTrainingRecord();

        for (int p = 0; p < teacherList.size(); p++) {
            details = new TrainingRecord();
            //ArrayList<Object> baris = data.get(p);
            //details.setBackCnic(teacherList.get(p).getBackCnic());
            //details.setTeacherid(teacherList.get(p).getTeacherid());
            details.setId(teacherList.get(p).getId());
            details.setName(teacherList.get(p).getName());
            details.setCnic(teacherList.get(p).getCnic());
            details.setTitle(teacherList.get(p).getTitle());
            details.setYear(teacherList.get(p).getYear());
            details.setDuration(teacherList.get(p).getDuration());
            details.setConductedby(teacherList.get(p).getConductedby());
            details.setAttendedas(teacherList.get(p).getAttendedas());
            addas.add(details);
        }


        Collections.sort(addas, new SortBasedOnName());
        cusadapter = new CustomAdapterTrainingRecord(M_TrainingRecordList.this, addas);
        listcontent.setAdapter(cusadapter);
    }


    public class SortBasedOnName implements Comparator {
        public int compare(Object o1, Object o2) {

            TrainingRecord dd1 = (TrainingRecord) o1;// where FBFriends_Obj is your object class
            TrainingRecord dd2 = (TrainingRecord) o2;
            return dd1.getName().compareToIgnoreCase(dd2.getName());//where uname is field name
        }

    }
}
