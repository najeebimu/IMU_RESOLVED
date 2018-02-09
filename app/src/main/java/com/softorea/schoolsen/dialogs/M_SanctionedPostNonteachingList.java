package com.softorea.schoolsen.dialogs;

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

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.databasehelper.DatabaseHandler;
import com.softorea.schoolsen.lists.Roster_List;
import com.softorea.schoolsen.m_monthly.M_SanctionedPostNonTeaching;
import com.softorea.schoolsen.models.DetailsSanctionedNonteachingPosts;

import java.util.ArrayList;

/**
 * Created by Softorea on 10/3/2017.
 */

public class M_SanctionedPostNonteachingList extends Activity {
    Button back;
    DetailsSanctionedNonteachingPosts details;

    ArrayList<DetailsSanctionedNonteachingPosts> addas = new ArrayList<DetailsSanctionedNonteachingPosts>();
    DialogAdapterSanctionednon cusadapter;
    DatabaseHandler databasehandler;
    String emis;
    ArrayList<DetailsSanctionedNonteachingPosts> teacherList;
    private ListView listcontent = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialogsanctionednon);
        this.setFinishOnTouchOutside(false);
        databasehandler = new DatabaseHandler(M_SanctionedPostNonteachingList.this);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        emis = preferences.getString("emiscode", "");
        listcontent = (ListView) findViewById(R.id.teacher_list);
        teacherList = databasehandler.getNonTeachingSanctionedPost(emis);
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
        menuInflater.inflate(R.menu.sanctionedfor_nonteaching, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menuteacher:
                startActivity(new Intent(M_SanctionedPostNonteachingList.this, M_SanctionedPostNonTeaching.class));
                finish();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void SavingInDb() {
        addas.clear();
        //final DatabaseHandler databaseHandler=new DatabaseHandler(this);
        //final ArrayList<ArrayList<Object>> data =  databaseHandler.abcSanctionedNonTeaching();

        for (int p = 0; p < teacherList.size(); p++) {
            details = new DetailsSanctionedNonteachingPosts();
            //ArrayList<Object> baris = t.get(p);
            details.setId(teacherList.get(p).getId());
            details.setPositioncode(teacherList.get(p).getPositioncode());
            details.setDesignation(teacherList.get(p).getDesignation());
            details.setBPS(teacherList.get(p).getBPS());
            details.setSpecifyothers(teacherList.get(p).getSpecifyothers());
            details.setNoOfSanctionedPosts(teacherList.get(p).getNoOfSanctionedPosts());
            addas.add(details);
        }
        cusadapter = new DialogAdapterSanctionednon(M_SanctionedPostNonteachingList.this, addas);
        listcontent.setAdapter(cusadapter);
        /*listcontent.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final String positioncode = addas.get(position).getPositioncode();
                final String designation = addas.get(position).getDesignation();
                final String bps = addas.get(position).getBPS();
                final String noOfSanctioned = addas.get(position).getNoOfSanctionedPosts();
                //databasehandler.removeSingleContact(name);
                //cusadapter.notifyDataSetChanged();
                AlertDialog.Builder builder1 = new AlertDialog.Builder(M_SanctionedPostNonteachingList.this);
                builder1.setMessage("Are you sure to delete?");
                builder1.setCancelable(false);

                builder1.setPositiveButton(
                        "Delete",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                databasehandler.removeSanctionedPostNon(positioncode,designation,bps,noOfSanctioned);
                                finish();
                                startActivity(new Intent(M_SanctionedPostNonteachingList.this,M_SanctionedPostNonteachingList.class));
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

                /*Intent i = new Intent(M_SanctionedPostNonteachingList.this,M_SanctionedPostNonTeachingUpdate.class);
                i.putExtra("noOf",addas.get(position).getNoOfSanctionedPosts());
                i.putExtra("bps",addas.get(position).getBPS());
                i.putExtra("other",addas.get(position).getSpecifyothers());
                i.putExtra("Des",addas.get(position).getDesignation());
                i.putExtra("pos",addas.get(position).getPositioncode());
                startActivity(i);*/


            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(M_SanctionedPostNonteachingList.this);
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
