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
import com.softorea.schoolsen.adapters.CustomAdapterSanctionedPosts;
import com.softorea.schoolsen.b_annual.B_BuildingCondition;
import com.softorea.schoolsen.b_annual.B_Cleanliness;
import com.softorea.schoolsen.b_annual.B_Commodities;
import com.softorea.schoolsen.b_annual.B_Construction;
import com.softorea.schoolsen.b_annual.B_IT_LabExists;
import com.softorea.schoolsen.b_annual.B_InfraStructure;
import com.softorea.schoolsen.b_annual.B_ParentTeacherCouncil;
import com.softorea.schoolsen.b_annual.B_SchoolArea;
import com.softorea.schoolsen.b_annual.B_Stipend;
import com.softorea.schoolsen.b_annual.B_TeacherGuides;
import com.softorea.schoolsen.databasehelper.DatabaseHandler;
import com.softorea.schoolsen.lists.Roster_List;
import com.softorea.schoolsen.models.DetailsSanctionedPosts;
import com.softorea.schoolsen.y_annual.Y_SpecialBoysMain;

import java.util.ArrayList;

/**
 * Created by Softorea on 10/3/2017.
 */

public class M_SanctionedPostList extends Activity {
    Button back, next;
    DetailsSanctionedPosts details;

    TextView addSanctionedPosts;

    ArrayList<DetailsSanctionedPosts> addas = new ArrayList<DetailsSanctionedPosts>();
    CustomAdapterSanctionedPosts cusadapter;
    DatabaseHandler databasehandler;
    String emis;
    ArrayList<DetailsSanctionedPosts> teacherList;
    String schoolareaStr,constructionStr,buildindconditionStr,itlabStr,
            commodititesStr,ptcStr,infrastructureStr,guideStr,cleanlinessStr,stipendStr,SanctionedStr,indicatorStr,
            enrollmentageStr,enrollmentgrpStr,disabledStr,securityStr,ftbStr,furnitureStr;

    private ListView listcontent = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_sanctionedpost_list);
        databasehandler = new DatabaseHandler(M_SanctionedPostList.this);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        emis = preferences.getString("emiscode", "");
        listcontent = (ListView) findViewById(R.id.teacher_list);
        teacherList = databasehandler.getSanctionedPost(emis);

        addSanctionedPosts = (TextView) findViewById(R.id.addsanctionedpost);

        SavingInDb();
        addSanctionedPosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(M_SanctionedPostList.this, M_SanctionedPost.class));
                finish();
            }
        });
        back = (Button) findViewById(R.id.btn_left);
        next = (Button) findViewById(R.id.btn_right);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(M_SanctionedPostList.this, M_SanctionedPostNonteachingList.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(M_SanctionedPostList.this);
                final String middle = preferences.getString("middle", "");
                final String high = preferences.getString("high", "");
                final String hsec = preferences.getString("highsecondary", "");

                final String boys = preferences.getString("boys", "");
                final String girls = preferences.getString("girls", "");
                SQLiteOpenHelper database = new DatabaseHandler(M_SanctionedPostList.this);
                SQLiteDatabase dbd = database.getReadableDatabase();
                Cursor cursorform = dbd.rawQuery("SELECT * FROM t_screenconfig", null);
                if (cursorform.moveToFirst()) {
                    do {
                        schoolareaStr = cursorform.getString(cursorform.getColumnIndex("Bi_SchoolArea"));
                        constructionStr = cursorform.getString(cursorform.getColumnIndex("Bi_NatureOfConstruction"));
                        buildindconditionStr = cursorform.getString(cursorform.getColumnIndex("Bi_BuildingCondition"));
                        itlabStr = cursorform.getString(cursorform.getColumnIndex("Bi_ITLab"));
                        commodititesStr = cursorform.getString(cursorform.getColumnIndex("Bi_Commodities"));
                        ptcStr = cursorform.getString(cursorform.getColumnIndex("Bi_PTC"));
                        infrastructureStr = cursorform.getString(cursorform.getColumnIndex("Bi_Infrastructure"));
                        guideStr = cursorform.getString(cursorform.getColumnIndex("Bi_Guides"));
                        cleanlinessStr = cursorform.getString(cursorform.getColumnIndex("Bi_Cleanliness"));
                        stipendStr = cursorform.getString(cursorform.getColumnIndex("Bi_Stipend"));
                        disabledStr = cursorform.getString(cursorform.getColumnIndex("An_DisabledStudent"));
                        enrollmentageStr = cursorform.getString(cursorform.getColumnIndex("An_EnrollmentByAge"));
                        enrollmentgrpStr = cursorform.getString(cursorform.getColumnIndex("An_EnrollmentByGroup"));
                        ftbStr = cursorform.getString(cursorform.getColumnIndex("An_FTB"));
                        furnitureStr = cursorform.getString(cursorform.getColumnIndex("An_Furniture"));
                        indicatorStr = cursorform.getString(cursorform.getColumnIndex("An_Indicator"));
                        SanctionedStr = cursorform.getString(cursorform.getColumnIndex("An_SantionedPosts"));
                        securityStr = cursorform.getString(cursorform.getColumnIndex("An_SecurityMeasures"));
                    } while (cursorform.moveToNext());
                }
                cursorform.close();
                dbd.close();
                if (cleanlinessStr.equals("True"))
                {
                    startActivity(new Intent(M_SanctionedPostList.this, B_Cleanliness.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (guideStr.equals("True"))
                {
                    startActivity(new Intent(M_SanctionedPostList.this, B_TeacherGuides.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (infrastructureStr.equals("True"))
                {
                    startActivity(new Intent(M_SanctionedPostList.this, B_InfraStructure.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (stipendStr.equals("True") && girls.equals("GirlsSelected") && (middle.equals("MiddleSelected") || high.equals("HighSelected")
                        || hsec.equals("HighSecondarySelected"))) {
                    startActivity(new Intent(M_SanctionedPostList.this, B_Stipend.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (ptcStr.equals("True"))
                {
                    startActivity(new Intent(M_SanctionedPostList.this, B_ParentTeacherCouncil.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (commodititesStr.equals("True"))
                {
                    startActivity(new Intent(M_SanctionedPostList.this, B_Commodities.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (itlabStr.equals("True"))
                {
                    startActivity(new Intent(M_SanctionedPostList.this, B_IT_LabExists.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (buildindconditionStr.equals("True"))
                {
                    startActivity(new Intent(M_SanctionedPostList.this, B_BuildingCondition.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (constructionStr.equals("True"))
                {
                    startActivity(new Intent(M_SanctionedPostList.this, B_Construction.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (schoolareaStr.equals("True"))
                {
                    startActivity(new Intent(M_SanctionedPostList.this, B_SchoolArea.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else {
                    startActivity(new Intent(M_SanctionedPostList.this, MoreInfo.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
            }
        });
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.sanctionedforteaching, menu);
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
    }
*/
    private void SavingInDb() {
        addas.clear();
        //final DatabaseHandler databaseHandler=new DatabaseHandler(this);
        //final ArrayList<ArrayList<Object>> data =  databaseHandler.abcSanctionedTeaching();

        for (int p = 0; p < teacherList.size(); p++) {
            details = new DetailsSanctionedPosts();
            details.setId(teacherList.get(p).getId());
            //ArrayList<Object> baris = data.get(p);
            details.setPositioncode(teacherList.get(p).getPositioncode());
            details.setSpecifyothers(teacherList.get(p).getSpecifyothers());
            details.setDesignation(teacherList.get(p).getDesignation());
            details.setSubject(teacherList.get(p).getSubject());
            details.setBPS(teacherList.get(p).getBPS());
            details.setNoOfSanctionedPosts(teacherList.get(p).getNoOfSanctionedPosts());
            addas.add(details);
        }
        cusadapter = new CustomAdapterSanctionedPosts(M_SanctionedPostList.this, addas);
        listcontent.setAdapter(cusadapter);
       /* listcontent.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final String positioncode = addas.get(position).getPositioncode();
                final String designation = addas.get(position).getDesignation();
                final String bps = addas.get(position).getBPS();
                final String noOfSanctioned = addas.get(position).getNoOfSanctionedPosts();
                //databasehandler.removeSingleContact(name);
                //cusadapter.notifyDataSetChanged();
                AlertDialog.Builder builder1 = new AlertDialog.Builder(M_SanctionedPostList.this);
                builder1.setMessage("Are you sure to delete?");
                builder1.setCancelable(false);

                builder1.setPositiveButton(
                        "Delete",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                databasehandler.removeSanctionedPost(positioncode,designation,bps,noOfSanctioned);
                                finish();
                                startActivity(new Intent(M_SanctionedPostList.this,M_SanctionedPostList.class));
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

                /*Intent i = new Intent(M_SanctionedPostList.this,M_SanctionedPostUpdate.class);
                i.putExtra("a",addas.get(position).getNoOfSanctionedPosts());
                i.putExtra("b",addas.get(position).getBPS());
                i.putExtra("c",addas.get(position).getSubject());
                i.putExtra("d",addas.get(position).getSpecifyothers());
                i.putExtra("e",addas.get(position).getDesignation());
                i.putExtra("f",addas.get(position).getPositioncode());
                startActivity(i);*/


            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(M_SanctionedPostList.this);
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
