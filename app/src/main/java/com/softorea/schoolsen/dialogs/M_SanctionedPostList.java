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
import com.softorea.schoolsen.models.DetailsSanctionedPosts;

import java.util.ArrayList;

/**
 * Created by Softorea on 10/3/2017.
 */

public class M_SanctionedPostList extends Activity {
    Button back;
    DetailsSanctionedPosts details;

    ArrayList<DetailsSanctionedPosts> addas = new ArrayList<DetailsSanctionedPosts>();
    DialogAdapterSanctioned cusadapter;
    DatabaseHandler databasehandler;
    String emis;
    ArrayList<DetailsSanctionedPosts> teacherList;

    private ListView listcontent = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialogsacntionedpost);
        this.setFinishOnTouchOutside(false);
        databasehandler = new DatabaseHandler(M_SanctionedPostList.this);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        emis = preferences.getString("emiscode", "");
        listcontent = (ListView) findViewById(R.id.teacher_list);
        teacherList = databasehandler.getSanctionedPost(emis);
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
        cusadapter = new DialogAdapterSanctioned(M_SanctionedPostList.this, addas);
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


}
