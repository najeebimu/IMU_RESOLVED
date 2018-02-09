package com.softorea.schoolsen.dialogs;

import android.app.Activity;
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

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.adapters.CustomAdapterApointedBy;
import com.softorea.schoolsen.databasehelper.DatabaseHandler;
import com.softorea.schoolsen.m_monthly.M_TeacherAppointedBy;
import com.softorea.schoolsen.models.DetailsAppointedBy;

import java.util.ArrayList;

/**
 * Created by arsla on 09/10/2017.
 */

public class M_TeacherAppointedByList extends Activity {
    Button back;
    DetailsAppointedBy details;

    ArrayList<DetailsAppointedBy> addas = new ArrayList<DetailsAppointedBy>();
    DialogAdapterAppointed cusadapter;
    DatabaseHandler databasehandler;
    String emis;
    ArrayList<DetailsAppointedBy> teacherList;

    private ListView listcontent = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialogappointedby);
        this.setFinishOnTouchOutside(false);
        databasehandler = new DatabaseHandler(M_TeacherAppointedByList.this);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        emis = preferences.getString("emiscode", "");
        listcontent = (ListView) findViewById(R.id.teacherappointedbylist);
        teacherList = databasehandler.getAppointedBy(emis);
        SavingInDb();
        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.teacherappointedby, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.teacherappointedby:
                startActivity(new Intent(M_TeacherAppointedByList.this, M_TeacherAppointedBy.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void SavingInDb() {
        addas.clear();
        //final DatabaseHandler databaseHandler=new DatabaseHandler(this);
        //final ArrayList<ArrayList<Object>> data =  databaseHandler.abcAppointedBy();

        for (int p = 0; p < teacherList.size(); p++) {
            details = new DetailsAppointedBy();
            //ArrayList<Object> baris = data.get(p);
            details.setId(teacherList.get(p).getId());
            details.setName(teacherList.get(p).getName());
            details.setCnic(teacherList.get(p).getCnic());
            details.setAppointedby(teacherList.get(p).getAppointedby());
            details.setAppointedDate(teacherList.get(p).getAppointedDate());
            addas.add(details);
        }
        cusadapter = new DialogAdapterAppointed(M_TeacherAppointedByList.this, addas);
        listcontent.setAdapter(cusadapter);
        /*listcontent.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final String visitdate = addas.get(position).getName();
                final String visitby = addas.get(position).getAppointedby();
                AlertDialog.Builder builder1 = new AlertDialog.Builder(M_TeacherAppointedByList.this);
                builder1.setMessage("Are you sure to delete?");
                builder1.setCancelable(false);

                builder1.setPositiveButton(
                        "Delete",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                databasehandler.removeAppointedBy(visitdate,visitby);
                                finish();
                                startActivity(new Intent(M_TeacherAppointedByList.this,M_TeacherAppointedByList.class));
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

                /*Intent i = new Intent(M_TeacherAppointedByList.this,M_TeacherAppointedByUpdate.class);
                i.putExtra("nme",addas.get(position).getName());
                i.putExtra("cnc",addas.get(position).getCnic());
                i.putExtra("apintby",addas.get(position).getAppointedby());
                i.putExtra("apintdate",addas.get(position).getAppointedDate());
                startActivity(i);*/


            }
        });
    }
}

