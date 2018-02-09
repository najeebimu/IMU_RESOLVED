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
import com.softorea.schoolsen.models.DetailsVacant;

import java.util.ArrayList;

/**
 * Created by arsla on 09/10/2017.
 */

public class M_VacantPositionsList extends Activity {
    Button back;
    DetailsVacant details;

    ArrayList<DetailsVacant> addas = new ArrayList<DetailsVacant>();
    DialogAdapterVacant cusadapter;
    DatabaseHandler databasehandler;
    String emis;
    ArrayList<DetailsVacant> teacherList;
    private ListView listcontent = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialogvacant);
        this.setFinishOnTouchOutside(false);
        databasehandler = new DatabaseHandler(M_VacantPositionsList.this);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        emis = preferences.getString("emiscode", "");
        listcontent = (ListView) findViewById(R.id.vacantposition);
        teacherList = databasehandler.getVacantPosition(emis);
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
        //final ArrayList<ArrayList<Object>> data =  databaseHandler.abcVacant();

        for (int p = 0; p < teacherList.size(); p++) {
            details = new DetailsVacant();
            //ArrayList<Object> baris = data.get(p);
            details.setId(teacherList.get(p).getId());
            details.setVacantdesignation(teacherList.get(p).getVacantdesignation());
            details.setVacantseats(teacherList.get(p).getVacantseats());
            addas.add(details);
        }
        cusadapter = new DialogAdapterVacant(M_VacantPositionsList.this, addas);
        listcontent.setAdapter(cusadapter);
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

}

