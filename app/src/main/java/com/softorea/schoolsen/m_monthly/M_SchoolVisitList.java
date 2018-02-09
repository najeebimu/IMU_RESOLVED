package com.softorea.schoolsen.m_monthly;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.adapters.CustomAdapterSchoolVisit;
import com.softorea.schoolsen.databasehelper.DatabaseHandler;
import com.softorea.schoolsen.lists.Roster_List;
import com.softorea.schoolsen.models.DetailsSchoolVisit;

import java.util.ArrayList;

/**
 * Created by arsla on 09/10/2017.
 */

public class M_SchoolVisitList extends Activity {
    Button back, next;
    DetailsSchoolVisit details;

    TextView addSchoolVisitByTextView;

    ArrayList<DetailsSchoolVisit> addas = new ArrayList<DetailsSchoolVisit>();
    CustomAdapterSchoolVisit cusadapter;
    DatabaseHandler databasehandler;
    String emis;
    ArrayList<DetailsSchoolVisit> teacherList;
    private ListView listcontent = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_schoolvisitlist);
        databasehandler = new DatabaseHandler(M_SchoolVisitList.this);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        emis = preferences.getString("emiscode", "");
        listcontent = (ListView) findViewById(R.id.schoolvisitby);
        teacherList = databasehandler.getVisitBy(emis);
        SavingInDb();

        addSchoolVisitByTextView = (TextView) findViewById(R.id.addschoolvisitby);

        addSchoolVisitByTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(M_SchoolVisitList.this, M_SchoolVisits.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });

        back = (Button) findViewById(R.id.btn_left);
        next = (Button) findViewById(R.id.btn_right);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(M_SchoolVisitList.this, M_SchoolFunctioning.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(M_SchoolVisitList.this, M_Enrollment_AttendanceGap.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.schoolvisitby, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.schoolvisitby:


                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

    private void SavingInDb() {
        addas.clear();
        //final DatabaseHandler databaseHandler=new DatabaseHandler(this);
        //final ArrayList<ArrayList<Object>> data =  databaseHandler.abcSchoolVisitBy();

        for (int p = 0; p < teacherList.size(); p++) {
            details = new DetailsSchoolVisit();
            //ArrayList<Object> baris = data.get(p);
            details.setId(teacherList.get(p).getId());
            details.setVisitdate(teacherList.get(p).getVisitdate());
            details.setVisitby(teacherList.get(p).getVisitby());
            details.setDesignationother(teacherList.get(p).getDesignationother());
            addas.add(details);
        }
        cusadapter = new CustomAdapterSchoolVisit(M_SchoolVisitList.this, addas);
        listcontent.setAdapter(cusadapter);
        /*listcontent.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final String visitdate = addas.get(position).getVisitdate();
                final String visitby = addas.get(position).getVisitby();
                AlertDialog.Builder builder1 = new AlertDialog.Builder(M_SchoolVisitList.this);
                builder1.setMessage("Are you sure to delete?");
                builder1.setCancelable(false);

                builder1.setPositiveButton(
                        "Delete",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                databasehandler.removeSchoolvisit(visitdate,visitby);
                                finish();
                                startActivity(new Intent(M_SchoolVisitList.this,M_SchoolVisitList.class));
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
              /*  Intent i = new Intent(M_SchoolVisitList.this,M_SchoolVisitsUpdate.class);
                i.putExtra("visitby",addas.get(position).getVisitby());
                i.putExtra("visitdate",addas.get(position).getVisitdate());
                i.putExtra("other",addas.get(position).getDesignationother());
                startActivity(i);*/


            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(M_SchoolVisitList.this);
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

}

