package com.softorea.schoolsen.dialogs;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.databasehelper.DatabaseHandler;
import com.softorea.schoolsen.models.DetailsProxyTeacher;

import java.util.ArrayList;

/**
 * Created by arsla on 09/10/2017.
 */

public class M_ProxyTeacherList extends Activity {
    Button back;
    DetailsProxyTeacher details;

    ArrayList<DetailsProxyTeacher> addas = new ArrayList<DetailsProxyTeacher>();
    DialogAdapterProxy cusadapter;
    DatabaseHandler databasehandler;
    String emis;
    ArrayList<DetailsProxyTeacher> teacherList;
    private ListView listcontent = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialogproxyteacher);
        this.setFinishOnTouchOutside(false);
        databasehandler = new DatabaseHandler(M_ProxyTeacherList.this);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        emis = preferences.getString("emiscode", "");
        listcontent = (ListView) findViewById(R.id.proxyteacherlist);
        teacherList = databasehandler.getProxyTeacher(emis);
        SavingInDb();
        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


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
        cusadapter = new DialogAdapterProxy(M_ProxyTeacherList.this, addas);
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


}
