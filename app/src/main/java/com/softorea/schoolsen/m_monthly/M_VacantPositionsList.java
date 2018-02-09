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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.adapters.CustomAdapterVacantPosition;
import com.softorea.schoolsen.databasehelper.DatabaseHandler;
import com.softorea.schoolsen.lists.Roster_List;
import com.softorea.schoolsen.models.DetailsVacant;

import java.util.ArrayList;

/**
 * Created by arsla on 09/10/2017.
 */

public class M_VacantPositionsList extends Activity {
    Button back, next;
    DetailsVacant details;

    TextView addVacantPositionTextView;

    ArrayList<DetailsVacant> addas = new ArrayList<DetailsVacant>();
    CustomAdapterVacantPosition cusadapter;
    DatabaseHandler databasehandler;
    String emis;
    ArrayList<DetailsVacant> teacherList;
    private ListView listcontent = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_vacant_positionslist);
        databasehandler = new DatabaseHandler(M_VacantPositionsList.this);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        emis = preferences.getString("emiscode", "");
        listcontent = (ListView) findViewById(R.id.vacantposition);
        teacherList = databasehandler.getVacantPosition(emis);
        addVacantPositionTextView = (TextView) findViewById(R.id.addvacantposition);

        SavingInDb();

        addVacantPositionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(M_VacantPositionsList.this, M_VacantPositions.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();

            }
        });
        back = (Button) findViewById(R.id.btn_left);
        next = (Button) findViewById(R.id.btn_right);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(M_VacantPositionsList.this, MoreInfo.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = databasehandler.getReadableDatabase();

                String queryFac8 = "SELECT * FROM " + "Grantwebservice" + " WHERE " + "w_imei_code='" + emis + "'"
                        + " AND " + "w_grant_id='" + "8" + "'";
                String queryFac7 = "SELECT * FROM " + "Grantwebservice" + " WHERE " + "w_imei_code='" + emis + "'"
                        + " AND " + "w_grant_id='" + "7" + "'";
                String queryFac6 = "SELECT * FROM " + "Grantwebservice" + " WHERE " + "w_imei_code='" + emis + "'"
                        + " AND " + "w_grant_id='" + "6" + "'";
                String queryFac5 = "SELECT * FROM " + "Grantwebservice" + " WHERE " + "w_imei_code='" + emis + "'"
                        + " AND " + "w_grant_id='" + "5" + "'";
                String queryFac4 = "SELECT * FROM " + "Grantwebservice" + " WHERE " + "w_imei_code='" + emis + "'"
                        + " AND " + "w_grant_id='" + "4" + "'";
                String queryFac3 = "SELECT * FROM " + "Grantwebservice" + " WHERE " + "w_imei_code='" + emis + "'"
                        + " AND " + "w_grant_id='" + "3" + "'";
                String queryFac2 = "SELECT * FROM " + "Grantwebservice" + " WHERE " + "w_imei_code='" + emis + "'"
                        + " AND " + "w_grant_id='" + "2" + "'";
                String queryFac1 = "SELECT * FROM " + "Grantwebservice" + " WHERE " + "w_imei_code='" + emis + "'"
                        + " AND " + "w_grant_id='" + "1" + "'";

                Cursor cfac8 = db.rawQuery(queryFac8, null);
                Cursor cfac7 = db.rawQuery(queryFac7, null);
                Cursor cfac6 = db.rawQuery(queryFac6, null);
                Cursor cfac5 = db.rawQuery(queryFac5, null);
                Cursor cfac4 = db.rawQuery(queryFac4, null);
                Cursor cfac3 = db.rawQuery(queryFac3, null);
                Cursor cfac2 = db.rawQuery(queryFac2, null);
                Cursor cfac1 = db.rawQuery(queryFac1, null);

                if (cfac8.moveToFirst()) {
                    startActivity(new Intent(M_VacantPositionsList.this, M_NewGrant8.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (cfac7.moveToFirst()) {
                    startActivity(new Intent(M_VacantPositionsList.this, M_NewGrant7.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                } else if (cfac6.moveToFirst()) {
                    startActivity(new Intent(M_VacantPositionsList.this, M_NewGrant6.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                } else if (cfac5.moveToFirst()) {
                    startActivity(new Intent(M_VacantPositionsList.this, M_NewGrant5.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                } else if (cfac4.moveToFirst()) {
                    startActivity(new Intent(M_VacantPositionsList.this, M_NewGrant4.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                } else if (cfac3.moveToFirst()) {
                    startActivity(new Intent(M_VacantPositionsList.this, M_NewGrant3.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                } else if (cfac2.moveToFirst()) {
                    startActivity(new Intent(M_VacantPositionsList.this, M_NewGrant2.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                } else if (cfac1.moveToFirst()) {
                    startActivity(new Intent(M_VacantPositionsList.this, M_NewGrant1.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                } else {

                    startActivity(new Intent(M_VacantPositionsList.this, M_SchoolFunctioning.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
            }
        });
    }

  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.vacant, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.vacantposition:


                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

    private void SavingInDb() {
        addas.clear();
        //final DatabaseHandler databaseHandler=new DatabaseHandler(this);
        //final ArrayList<ArrayList<Object>> data =  databaseHandler.abcVacant();

        for (int p = 0; p < teacherList.size(); p++) {
            details = new DetailsVacant();
            //ArrayList<Object> baris = data.get(p);
            details.setId(teacherList.get(p).getId());
            details.setVacantdesignation(teacherList.get(p).getVacantdesignation());
            details.setVacantseats(teacherList.get(p).getVacantseats());
            addas.add(details);
        }
        cusadapter = new CustomAdapterVacantPosition(M_VacantPositionsList.this, addas);
        listcontent.setAdapter(cusadapter);

        /*listcontent.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final String visitdate = addas.get(position).getVacantdesignation();
                final String visitby = addas.get(position).getVacantseats();
                AlertDialog.Builder builder1 = new AlertDialog.Builder(M_VacantPositionsList.this);
                builder1.setMessage("Are you sure to delete?");
                builder1.setCancelable(false);

                builder1.setPositiveButton(
                        "Delete",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                databasehandler.removeVacant(visitdate,visitby);
                                finish();
                                startActivity(new Intent(M_VacantPositionsList.this,M_VacantPositionsList.class));
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

                /*Intent i = new Intent(M_VacantPositionsList.this,M_VacantPositionsUpdate.class);
                i.putExtra("vacantdesign",addas.get(position).getVacantdesignation());
                i.putExtra("vacantseats",addas.get(position).getVacantseats());
                startActivity(i);*/


            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(M_VacantPositionsList.this);
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

