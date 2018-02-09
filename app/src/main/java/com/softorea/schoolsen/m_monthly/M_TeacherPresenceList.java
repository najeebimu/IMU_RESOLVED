package com.softorea.schoolsen.m_monthly;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.adapters.CustomAdapterTeacherNew;
import com.softorea.schoolsen.b_annual.B_BuildingCondition;
import com.softorea.schoolsen.databasehelper.DatabaseHandler;
import com.softorea.schoolsen.lists.Roster_List;
import com.softorea.schoolsen.models.TeacherNewDetails;

import java.util.ArrayList;

/**
 * Created by Softorea on 10/3/2017.
 */

public class M_TeacherPresenceList extends Activity {
    Button back, next;
    //DetailsStaff details;
    String SanctionedStr;

    TextView addteacher;
    //ArrayList<DetailsStaff> addas = new ArrayList<DetailsStaff>();
    //CustomAdapterStaff cusadapter;
    DatabaseHandler databasehandler;
    TeacherNewDetails details;
    String emis;

    ArrayList<TeacherNewDetails> addas = new ArrayList<TeacherNewDetails>();
    CustomAdapterTeacherNew cusadapter;
    ArrayList<TeacherNewDetails> teacherList;
    private ListView listcontent = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_teacherlistnormal);
        addteacher = (TextView) findViewById(R.id.addteachermenu);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        emis = preferences.getString("emiscode", "");

        databasehandler = new DatabaseHandler(M_TeacherPresenceList.this);
        listcontent = (ListView) findViewById(R.id.teacher_list);
        teacherList = databasehandler.getGcsTeacher(emis);
        Rsults();
        back = (Button) findViewById(R.id.btn_left);
        next = (Button) findViewById(R.id.btn_right);
        addteacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(M_TeacherPresenceList.this, M_HumanResourceTeacherPresence.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);

                finish();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteOpenHelper database = new DatabaseHandler(M_TeacherPresenceList.this);
                SQLiteDatabase dbd = database.getReadableDatabase();
                Cursor cursorform = dbd.rawQuery("SELECT * FROM t_screenconfig", null);
                if (cursorform.moveToFirst()) {
                    do {
                        SanctionedStr = cursorform.getString(cursorform.getColumnIndex("An_SantionedPosts"));
                    } while (cursorform.moveToNext());
                }
                cursorform.close();
                dbd.close();
                if (SanctionedStr.equals("True")) {
                    Intent view_order_intent = new Intent(M_TeacherPresenceList.this, M_TrainingRecordList.class);
                    //view_order_intent.putExtra("data", emis );
                    startActivity(view_order_intent);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else
                {
                    Intent view_order_intent = new Intent(M_TeacherPresenceList.this, NonTeacherWebserviceMainList.class);
                    //view_order_intent.putExtra("data", emis );
                    startActivity(view_order_intent);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();

                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
                String value = preferences.getString("case3", "");
                String value4 = preferences.getString("case4", "");
                if (value.equals("case3found")) {
                    startActivity(new Intent(M_TeacherPresenceList.this, M_School_Status.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                } else if (value4.equals("case4found")) {
                    startActivity(new Intent(M_TeacherPresenceList.this, TeacherWebserviceMainList.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                } else {
                    startActivity(new Intent(M_TeacherPresenceList.this, TeacherWebserviceMainList.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
            }
        });
    }

    private void Rsults() {
        addas.clear();
        //DatabaseHandler databaseHandler=new DatabaseHandler(this);
        //ArrayList<ArrayList<Object>> data =  databaseHandler.abcTeacherNew();

        for (int p = 0; p < teacherList.size(); p++) {
            details = new TeacherNewDetails();
            //ArrayList<Object> baris = data.get(p);
            details.setId(teacherList.get(p).getId());
            details.setTeachername(teacherList.get(p).getTeachername());
            details.setTeacherfathername(teacherList.get(p).getTeacherfathername());
            details.setTeachergender(teacherList.get(p).getTeachergender());
            details.setTeachermarital(teacherList.get(p).getTeachermarital());
            details.setTeacherbps(teacherList.get(p).getTeacherbps());
            details.setTeachercnic(teacherList.get(p).getTeachercnic());
            details.setTeacherno(teacherList.get(p).getTeacherno());
            details.setTeacheraccountno(teacherList.get(p).getTeacheraccountno());
            details.setTeacherdob(teacherList.get(p).getTeacherdob());
            details.setTeacherhighestlevel(teacherList.get(p).getTeacherhighestlevel());
            details.setTeacherhigestsubject(teacherList.get(p).getTeacherhigestsubject());
            details.setTeacherdateoffirst(teacherList.get(p).getTeacherdateoffirst());
            details.setTeacherdistrict(teacherList.get(p).getTeacherdistrict());
            details.setTeacherhighestqualification(teacherList.get(p).getTeacherhighestqualification());
            details.setTeacherdesigasfirst(teacherList.get(p).getTeacherdesigasfirst());
            details.setDesignation(teacherList.get(p).getDesignation());
            details.setTeacheruc(teacherList.get(p).getTeacheruc());
            details.setTeacheranydisablity(teacherList.get(p).getTeacheranydisablity());
            details.setTeachertypedisablity(teacherList.get(p).getTeachertypedisablity());
            details.setAttendance(teacherList.get(p).getAttendance());
            details.setAttendanceTrasnferIn(teacherList.get(p).getAttendanceTrasnferIn());
            details.setTeacherattendancedetails(teacherList.get(p).getTeacherattendancedetails());
            details.setAttendancedatesince(teacherList.get(p).getAttendancedatesince());
            details.setAttendancetrasnferschool(teacherList.get(p).getAttendancetrasnferschool());
            addas.add(details);
        }
        cusadapter = new CustomAdapterTeacherNew(M_TeacherPresenceList.this, addas);
        listcontent.setAdapter(cusadapter);
        /*listcontent.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final String tname = addas.get(position).getTeachername();
                final String tcnic = addas.get(position).getTeachercnic();
                final String tno = addas.get(position).getTeacherno();
                final String tdesignation = addas.get(position).getDesignation();
                //databasehandler.removeSingleContact(name);
                //cusadapter.notifyDataSetChanged();
                AlertDialog.Builder builder1 = new AlertDialog.Builder(M_TeacherPresenceList.this);
                builder1.setMessage("Are you sure to delete?");
                builder1.setCancelable(false);

                builder1.setPositiveButton(
                        "Delete",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                databasehandler.removeTeacher(tname,tcnic,tno);
                                finish();
                                startActivity(new Intent(M_TeacherPresenceList.this,M_TeacherPresenceList.class));
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
                return true;
            }
        });*/
        listcontent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                /*String value = parent.getItemAtPosition(position).toString();
                Intent view_order_intent = new Intent(Roster_List.this, SchoolInformation.class);
                view_order_intent.putExtra("data", addas.get(position).getEmis()   );
                startActivity(view_order_intent);*/
                /*Intent view_order_intent = new Intent(M_TeacherPresenceList.this, M_HumanResourceTeacherPresenceUpdate.class);
                view_order_intent.putExtra("tname", addas.get(position).getTeachername());
                view_order_intent.putExtra("tfather", addas.get(position).getTeacherfathername());
                view_order_intent.putExtra("tgender", addas.get(position).getTeachergender());
                view_order_intent.putExtra("tmarital", addas.get(position).getTeachermarital());
                view_order_intent.putExtra("tbps", addas.get(position).getTeacherbps());
                view_order_intent.putExtra("tcnic", addas.get(position).getTeachercnic());
                view_order_intent.putExtra("tno", addas.get(position).getTeacherno());
                view_order_intent.putExtra("tacc", addas.get(position).getTeacheraccountno());
                view_order_intent.putExtra("tdob", addas.get(position).getTeacherdob());
                view_order_intent.putExtra("tlevel", addas.get(position).getTeacherhighestlevel());
                view_order_intent.putExtra("tsubject", addas.get(position).getTeacherhigestsubject());
                view_order_intent.putExtra("tdateoffirst", addas.get(position).getTeacherdateoffirst());
                view_order_intent.putExtra("tdistrict", addas.get(position).getTeacherdistrict());
                view_order_intent.putExtra("thighest", addas.get(position).getTeacherhighestqualification());
                view_order_intent.putExtra("tdesigfirst", addas.get(position).getTeacherdesigasfirst());
                view_order_intent.putExtra("tdesig", addas.get(position).getDesignation());
                view_order_intent.putExtra("tuc", addas.get(position).getTeacheruc());
                view_order_intent.putExtra("tanydisab", addas.get(position).getTeacheranydisablity());
                view_order_intent.putExtra("ttypedisab", addas.get(position).getTeachertypedisablity());
                view_order_intent.putExtra("tatt", addas.get(position).getAttendance());
                view_order_intent.putExtra("tattdetailss", addas.get(position).getTeacherattendancedetails());
                view_order_intent.putExtra("tdatesince", addas.get(position).getAttendancedatesince());
                view_order_intent.putExtra("ttrasnfer", addas.get(position).getAttendancetrasnferschool());
                startActivity(view_order_intent);*/

            }
        });
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.addteacher, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menuteacher:
                SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
                startActivity(new Intent(M_TeacherPresenceList.this,M_HumanResourceTeacherPresence.class));
                finish();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }*/
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(M_TeacherPresenceList.this);
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
