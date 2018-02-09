package com.softorea.schoolsen.gcsschools;

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
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.databasehelper.DatabaseHandler;
import com.softorea.schoolsen.lists.Roster_List;
import com.softorea.schoolsen.m_monthly.M_HumanResourceTeacherPresence;
import com.softorea.schoolsen.m_monthly.NonTeacherWebserviceMainList;
import com.softorea.schoolsen.models.GcsTeacherDetails;

import java.util.ArrayList;

/**
 * Created by Softorea on 10/3/2017.
 */

public class GcsteacherList extends Activity {
    Button back, next;
    //DetailsStaff details;
    int attentedncemarkedCount = 0;
    TextView addteacher;
    //ArrayList<DetailsStaff> addas = new ArrayList<DetailsStaff>();
    //CustomAdapterStaff cusadapter;
    DatabaseHandler databasehandler;
    GcsTeacherDetails details;
    String emis;

    ArrayList<GcsTeacherDetails> addas = new ArrayList<GcsTeacherDetails>();
    CustomAdapterGcsTeacher cusadapter;
    ArrayList<GcsTeacherDetails> teacherList;
    private ListView listcontent = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_teacher_presence_listitems );
        addteacher = (TextView) findViewById(R.id.addteachermenu);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        emis = preferences.getString("emiscode", "");

        databasehandler = new DatabaseHandler(GcsteacherList.this);
        listcontent = (ListView) findViewById(R.id.teacher_list);
        teacherList = databasehandler.Gcslistteacher(emis);
        Rsults();
        back = (Button) findViewById(R.id.btn_left);
        next = (Button) findViewById(R.id.btn_right);
        addteacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GcsteacherList.this, M_HumanResourceTeacherPresence.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);

                finish();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (attentedncemarkedCount==teacherList.size()) {
                    Intent view_order_intent = new Intent(GcsteacherList.this, com.softorea.schoolsen.gcsschools.M_HeadInfo.class);

                    //view_order_intent.putExtra("data", emis );
                    startActivity(view_order_intent);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else
                {
                    Toast.makeText(GcsteacherList.this, "Please Mark Attendance", Toast.LENGTH_SHORT).show();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(GcsteacherList.this, B_InfraStructure.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });

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

    private void Rsults() {
        addas.clear();
        //DatabaseHandler databaseHandler=new DatabaseHandler(this);
        //ArrayList<ArrayList<Object>> data =  databaseHandler.abcTeacherNew();

        for (int p = 0; p < teacherList.size(); p++) {
            details = new GcsTeacherDetails();
            //ArrayList<Object> baris = data.get(p);
            details.setId(teacherList.get(p).getId());
            details.setTeachername(teacherList.get(p).getTeachername());
            details.setTeachergender(teacherList.get(p).getTeachergender());
            details.setTeachercnic(teacherList.get(p).getTeachercnic());
            details.setTeacherno(teacherList.get(p).getTeacherno());
            details.setAttendance(teacherList.get(p).getAttendance());
            details.setTeacherattendancedetails(teacherList.get(p).getTeacherattendancedetails());
            details.setReplacementavailable(teacherList.get(p).getReplacementavailable());
            details.setReplacementname(teacherList.get(p).getReplacementname());
            details.setReplacementgender(teacherList.get(p).getReplacementgender());
            addas.add(details);
        }
        cusadapter = new CustomAdapterGcsTeacher(GcsteacherList.this, addas);
        listcontent.setAdapter(cusadapter);
        listcontent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {


            }
        });
    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(GcsteacherList.this);
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
        SQLiteDatabase db = databasehandler.getReadableDatabase();
        String selectQuery = "SELECT * " + " FROM " + "t_teacherInfo"
                + " WHERE " + "emis" + " = " + "'" + emis + "'";

        //Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        int size = c.getCount();
        if (c != null) {

            if (c.moveToFirst()) {
                do {
                    String status = c.getString(c.getColumnIndex("teacher_attendance"));
                    Log.e("VALUERETUREND", status);
                   /* if (status.equals("Present"))
                    {
                        listcontent.getChildAt(2).setBackgroundColor(Color.GRAY);
                    }*/
                    if (status.equals("Absent")) {

                    }
                    /*if (status.equals("Trasnfer Out"))
                    {
                        listcontent.getChildAt(3).setBackgroundColor(Color.BLUE);
                    }*/
                } while (c.moveToNext());
            }
        }
    }
}
