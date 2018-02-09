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
import com.softorea.schoolsen.adapters.CustomAdapterProxyTeacher;
import com.softorea.schoolsen.databasehelper.DatabaseHandler;
import com.softorea.schoolsen.lists.Roster_List;
import com.softorea.schoolsen.models.DetailsProxyTeacher;

import java.util.ArrayList;

/**
 * Created by arsla on 09/10/2017.
 */

public class M_ProxyTeacherList extends Activity {
    Button back, next;
    DetailsProxyTeacher details;
    TextView add_proxy_teacher;

    ArrayList<DetailsProxyTeacher> addas = new ArrayList<DetailsProxyTeacher>();
    CustomAdapterProxyTeacher cusadapter;
    DatabaseHandler databasehandler;
    String emis;
    ArrayList<DetailsProxyTeacher> teacherList;
    private ListView listcontent = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_proxyteacherlist);
        databasehandler = new DatabaseHandler(M_ProxyTeacherList.this);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        emis = preferences.getString("emiscode", "");
        listcontent = (ListView) findViewById(R.id.proxyteacherlist);
        teacherList = databasehandler.getProxyTeacher(emis);

        add_proxy_teacher = (TextView) findViewById(R.id.addproxyteacher);
        SavingInDb();
        back = (Button) findViewById(R.id.btn_left);
        next = (Button) findViewById(R.id.btn_right);

        add_proxy_teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(M_ProxyTeacherList.this, M_ProxyTeacher.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
                String value = preferences.getString("case4", "");
                if (value.equals("case4found")) {
                    startActivity(new Intent(M_ProxyTeacherList.this, M_Enrollment_AttendanceGap.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                } else {
                    startActivity(new Intent(M_ProxyTeacherList.this, M_TeacherAppointedByList.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(M_ProxyTeacherList.this, M_NonTeacherPresenceList.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.addproxyteacher, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
           // case R.id.menuproxyteacher:


               // return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
*/
    private void SavingInDb() {
        addas.clear();
        //final DatabaseHandler databaseHandler=new DatabaseHandler(this);
        //final ArrayList<ArrayList<Object>> data =  databaseHandler.abc();

        for (int p = 0; p < teacherList.size(); p++) {
            details = new DetailsProxyTeacher();
            //ArrayList<Object> baris = teacherList.get(p);
            details.setId(teacherList.get(p).getId());
            details.setName(teacherList.get(p).getName());
            details.setCnic(teacherList.get(p).getCnic());
            details.setProxyName(teacherList.get(p).getProxyName());
            details.setProxycnic(teacherList.get(p).getProxycnic());
            details.setProxyno(teacherList.get(p).getProxyno());
            details.setDesignation(teacherList.get(p).getDesignation());
            details.setProxytimeSince(teacherList.get(p).getProxytimeSince());
            addas.add(details);
        }
        cusadapter = new CustomAdapterProxyTeacher(M_ProxyTeacherList.this, addas);
        listcontent.setAdapter(cusadapter);
        /*listcontent.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final String name = addas.get(position).getName();
                final String proxyname = addas.get(position).getProxyName();
                //databasehandler.removeSingleContact(name);
                //cusadapter.notifyDataSetChanged();
                AlertDialog.Builder builder1 = new AlertDialog.Builder(M_ProxyTeacherList.this);
                builder1.setMessage("Are you sure to delete?");
                builder1.setCancelable(false);

                builder1.setPositiveButton(
                        "Delete",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                databasehandler.removeProxyTeacher(name,proxyname);
                                finish();
                                startActivity(new Intent(M_ProxyTeacherList.this,M_ProxyTeacherList.class));
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
                /*Intent i = new Intent(M_ProxyTeacherList.this,M_ProxyTeacherUpdate.class);
                i.putExtra("name",addas.get(position).getName());
                i.putExtra("cnic",addas.get(position).getCnic());
                i.putExtra("pname",addas.get(position).getProxyName());
                i.putExtra("pcnic",addas.get(position).getProxycnic());
                i.putExtra("ppno",addas.get(position).getProxyno());
                i.putExtra("des",addas.get(position).getDesignation());
                i.putExtra("date",addas.get(position).getProxytimeSince());
                startActivity(i);*/


            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(M_ProxyTeacherList.this);
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
