package com.softorea.schoolsen.m_monthly;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.adapters.CustomAdapterTeacherWebservice;
import com.softorea.schoolsen.databasehelper.DatabaseHandler;
import com.softorea.schoolsen.gcsschools.B_InfraStructure;
import com.softorea.schoolsen.gcsschools.CustomAdapterGcsTeacher;
import com.softorea.schoolsen.lists.Roster_List;
import com.softorea.schoolsen.models.DetailsTeacherwebservice;
import com.softorea.schoolsen.models.GcsTeacherDetails;

import java.util.ArrayList;

/**
 * Created by Softorea on 10/3/2017.
 */

public class TeacherWebserviceMainList extends Activity {
    Button back, next;
    //DetailsStaff details;
    int attentedncemarkedCount = 0;
    TextView addteacher;
    //ArrayList<DetailsStaff> addas = new ArrayList<DetailsStaff>();
    //CustomAdapterStaff cusadapter;
    DatabaseHandler databasehandler;
    DetailsTeacherwebservice details;
    String emis;

    ArrayList<DetailsTeacherwebservice> addas = new ArrayList<DetailsTeacherwebservice>();
    CustomAdapterTeacherWebservice cusadapter;
    ArrayList<DetailsTeacherwebservice> teacherList;
    private ListView listcontent = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacherwebservicemainlist );
        addteacher = (TextView) findViewById(R.id.addteachermenu);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        emis = preferences.getString("emiscode", "");

        databasehandler = new DatabaseHandler(TeacherWebserviceMainList.this);
        listcontent = (ListView) findViewById(R.id.teacher_list);
        teacherList = databasehandler.teacherwebserviceList(emis);
        Rsults();
        back = (Button) findViewById(R.id.btn_left);
        next = (Button) findViewById(R.id.btn_right);
        addteacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TeacherWebserviceMainList.this, M_HumanResourceTeacherPresence.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (attentedncemarkedCount==teacherList.size()) {
                    Intent view_order_intent = new Intent(TeacherWebserviceMainList.this, M_TeacherPresenceList.class);
                    startActivity(view_order_intent);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else
                {
                    Toast.makeText(TeacherWebserviceMainList.this, "Please Mark Attendance", Toast.LENGTH_SHORT).show();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
                String value4 = preferences.getString("case4", "");
                if (value4.equals("case4found")) {
                    startActivity(new Intent(TeacherWebserviceMainList.this, M_Building_Occupation.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else
                {
                    startActivity(new Intent(TeacherWebserviceMainList.this, M_HeadInfo.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
            }
        });


    }

    private void Rsults() {
        boolean notifydschg_needed = false;
        if (cusadapter != null) {
            notifydschg_needed = true;
        }

        teacherList = databasehandler.teacherwebserviceList(emis);
        addas.clear();
        for (int p = 0; p < teacherList.size(); p++) {
            details = new DetailsTeacherwebservice();
            //ArrayList<Object> baris = data.get(p);
            details.setId(teacherList.get(p).getId());
            details.setTeachername(teacherList.get(p).getTeachername());
            details.setTeachercnic(teacherList.get(p).getTeachercnic());
            details.setTeacherno(teacherList.get(p).getTeacherno());
            details.setTeachergender(teacherList.get(p).getTeachergender());
            details.setAttendance(teacherList.get(p).getAttendance());
            details.setTeacherattendancedetails(teacherList.get(p).getTeacherattendancedetails());
            details.setAttendancedatesince(teacherList.get(p).getAttendancedatesince());
            details.setAttendancetrasnferschool(teacherList.get(p).getAttendancetrasnferschool());
            addas.add(details);
        }

        if (cusadapter == null) {
            cusadapter = new CustomAdapterTeacherWebservice(TeacherWebserviceMainList.this, addas);
            listcontent.setAdapter(cusadapter);
        }

        if(notifydschg_needed) {
            cusadapter.notifyDataSetChanged();
        }

    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(TeacherWebserviceMainList.this);
        builder1.setMessage("Are you sure to go to roster screen?");
        builder1.setCancelable(false);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(getApplicationContext(), Roster_List.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("EXIT", true);
                        startActivity(intent);
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    }
                });

        builder1.setNegativeButton(
                "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Rsults();
        attentedncemarkedCount = 0;

        ArrayList<Boolean> isAllMarked = new ArrayList<>();
        for (int p = 0; p < teacherList.size(); p++) {
            if (teacherList.get(p).getAttendance().equals("") || teacherList.get(p).getAttendance().equals("Null")) {
                isAllMarked.add(false);
            } else {
                isAllMarked.add(true);
            }
        }

        for (int i = 0; i < isAllMarked.size(); i++) {
            if (isAllMarked.get(i)) {  //if marked true
                attentedncemarkedCount = attentedncemarkedCount + 1;
            }
        }
    }
}
