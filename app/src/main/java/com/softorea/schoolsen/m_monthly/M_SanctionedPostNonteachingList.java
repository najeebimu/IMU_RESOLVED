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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.adapters.CustomAdapterSanctionedNonteachingPosts;
import com.softorea.schoolsen.b_annual.B_Cleanliness;
import com.softorea.schoolsen.databasehelper.DatabaseHandler;
import com.softorea.schoolsen.lists.Roster_List;
import com.softorea.schoolsen.models.DetailsSanctionedNonteachingPosts;
import com.softorea.schoolsen.y_annual.CompleteForm;
import com.softorea.schoolsen.y_annual.Y_EnrollmentAgeBoys;
import com.softorea.schoolsen.y_annual.Y_EnrollmentByGroupSectionMain;
import com.softorea.schoolsen.y_annual.Y_Furniture;
import com.softorea.schoolsen.y_annual.Y_OtherFacilities;
import com.softorea.schoolsen.y_annual.Y_ProvisionFreeBooksMain;
import com.softorea.schoolsen.y_annual.Y_SecurityMeasures;
import com.softorea.schoolsen.y_annual.Y_SpecialBoysMain;

import java.util.ArrayList;

/**
 * Created by Softorea on 10/3/2017.
 */

public class M_SanctionedPostNonteachingList extends Activity {
    Button back, next;
    DetailsSanctionedNonteachingPosts details;
    TextView addNonTeachingSanctionedTextView;

    String schoolareaStr,constructionStr,buildindconditionStr,itlabStr,
            commodititesStr,ptcStr,infrastructureStr,guideStr,cleanlinessStr,stipendStr,SanctionedStr,indicatorStr,
            enrollmentageStr,enrollmentgrpStr,disabledStr,securityStr,ftbStr,furnitureStr;

    ArrayList<DetailsSanctionedNonteachingPosts> addas = new ArrayList<DetailsSanctionedNonteachingPosts>();
    CustomAdapterSanctionedNonteachingPosts cusadapter;
    DatabaseHandler databasehandler;
    String emis;
    ArrayList<DetailsSanctionedNonteachingPosts> teacherList;
    private ListView listcontent = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_sanctionedpostnonteaching_list);
        databasehandler = new DatabaseHandler(M_SanctionedPostNonteachingList.this);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        emis = preferences.getString("emiscode", "");
        listcontent = (ListView) findViewById(R.id.teacher_list);
        teacherList = databasehandler.getNonTeachingSanctionedPost(emis);
        addNonTeachingSanctionedTextView = (TextView) findViewById(R.id.addnonteachersanctionedpost);
        SavingInDb();
        back = (Button) findViewById(R.id.btn_left);
        next = (Button) findViewById(R.id.btn_right);
        addNonTeachingSanctionedTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(M_SanctionedPostNonteachingList.this, M_SanctionedPostNonTeaching.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(M_SanctionedPostNonteachingList.this);
                final String middle = preferences.getString("middle", "");
                final String high = preferences.getString("high", "");
                final String hsec = preferences.getString("highsecondary", "");

                final String boys = preferences.getString("boys", "");
                final String girls = preferences.getString("girls", "");
                SQLiteOpenHelper database = new DatabaseHandler(M_SanctionedPostNonteachingList.this);
                SQLiteDatabase dbd = database.getReadableDatabase();
                Cursor cursorform = dbd.rawQuery("SELECT * FROM t_screenconfig", null);
                if (cursorform.moveToFirst()) {
                    do {
                        disabledStr = cursorform.getString(cursorform.getColumnIndex("An_DisabledStudent"));
                        enrollmentageStr = cursorform.getString(cursorform.getColumnIndex("An_EnrollmentByAge"));
                        enrollmentgrpStr = cursorform.getString(cursorform.getColumnIndex("An_EnrollmentByGroup"));
                        ftbStr = cursorform.getString(cursorform.getColumnIndex("An_FTB"));
                        furnitureStr = cursorform.getString(cursorform.getColumnIndex("An_Furniture"));
                        indicatorStr = cursorform.getString(cursorform.getColumnIndex("An_Indicator"));
                        securityStr = cursorform.getString(cursorform.getColumnIndex("An_SecurityMeasures"));
                    } while (cursorform.moveToNext());
                }
                cursorform.close();
                dbd.close();

                if (indicatorStr.equals("True"))
                {
                    startActivity(new Intent(M_SanctionedPostNonteachingList.this, Y_OtherFacilities.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (enrollmentageStr.equals("True"))
                {
                    startActivity(new Intent(M_SanctionedPostNonteachingList.this, Y_EnrollmentAgeBoys.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (enrollmentgrpStr.equals("True") && (middle.equals("MiddleSelected") || high.equals("HighSelected")
                        || hsec.equals("HighSecondarySelected")))
                {
                    startActivity(new Intent(M_SanctionedPostNonteachingList.this, Y_EnrollmentByGroupSectionMain.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (disabledStr.equals("True"))
                {
                    startActivity(new Intent(M_SanctionedPostNonteachingList.this, Y_SpecialBoysMain.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (securityStr.equals("True"))
                {
                    startActivity(new Intent(M_SanctionedPostNonteachingList.this, Y_SecurityMeasures.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (ftbStr.equals("True"))
                {
                    startActivity(new Intent(M_SanctionedPostNonteachingList.this, Y_ProvisionFreeBooksMain.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (furnitureStr.equals("True"))
                {
                    startActivity(new Intent(M_SanctionedPostNonteachingList.this, Y_Furniture.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else
                {
                    startActivity(new Intent(M_SanctionedPostNonteachingList.this, CompleteForm.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(M_SanctionedPostNonteachingList.this, M_SanctionedPostList.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.sanctionedfor_nonteaching, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menuteacher:


                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

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
        cusadapter = new CustomAdapterSanctionedNonteachingPosts(M_SanctionedPostNonteachingList.this, addas);
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
