package com.softorea.schoolsen.m_monthly;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.databasehelper.DatabaseHandler;
import com.softorea.schoolsen.models.DetailsVacant;

/**
 * Created by Softorea on 10/2/2017.
 */

public class M_VacantPositions extends Activity {
    Button add, clear;
    Spinner visitDesignation;
    EditText noOfSeats;
    DatabaseHandler databasehandler;
    String SLevel = "";
    String emis;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_vacant_positions);
        databasehandler = new DatabaseHandler(M_VacantPositions.this);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        emis = preferences.getString("emiscode", "");
        noOfSeats = (EditText) findViewById(R.id.seats);
        visitDesignation = (Spinner) findViewById(R.id.visit_designation);
        add = (Button) findViewById(R.id.add);
        clear = (Button) findViewById(R.id.clear);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (noOfSeats.getText().toString().equals("")) {
                    Toast.makeText(M_VacantPositions.this, "Please Select No. of Seats", Toast.LENGTH_SHORT).show();
                } else {
                    //checkAlreadyExist(SLevel);
                    String Visitdate = noOfSeats.getText().toString();
                    databasehandler.saveVacant(new DetailsVacant(emis, SLevel, Visitdate));
                    Toast.makeText(M_VacantPositions.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(M_VacantPositions.this, M_VacantPositionsList.class));
                    finish();
                    noOfSeats.setText("");
                    visitDesignation.setSelection(0);
                    SLevel = visitDesignation.getSelectedItem().toString();

                }
                /*else {
                    String Visitdate = noOfSeats.getText().toString();
                    databasehandler.saveVacant(new DetailsVacant(emis,SLevel, Visitdate));
                    Toast.makeText(M_VacantPositions.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(M_VacantPositions.this,M_VacantPositionsList.class));
                    finish();
                    noOfSeats.setText("");
                    visitDesignation.setSelection(0);
                    SLevel = visitDesignation.getSelectedItem().toString();

                }*/
            }

            /*private void checkAlreadyExist(String sLevel) {
                SQLiteDatabase db = databasehandler.getReadableDatabase();
                Cursor c = db.rawQuery("SELECT * FROM t_vacant WHERE vacantdesig='" + sLevel + "'", null);
                if (c.moveToFirst()) {
                    Toast.makeText(M_VacantPositions.this, "Vacant post already exists!", Toast.LENGTH_SHORT).show();
                    //return false;
                } else {
                    String Visitdate = noOfSeats.getText().toString();
                    databasehandler.saveVacant(new DetailsVacant(emis, SLevel, Visitdate));
                    Toast.makeText(M_VacantPositions.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(M_VacantPositions.this, M_VacantPositionsList.class));
                    finish();
                    noOfSeats.setText("");
                    visitDesignation.setSelection(0);
                    SLevel = visitDesignation.getSelectedItem().toString();
                }
            }*/
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noOfSeats.setText("");
                visitDesignation.setSelection(0, true);

            }
        });

        String[] BuildingIllegalSpinner = getResources().getStringArray(R.array.proxyteacherdesignation);
        ArrayAdapter<String> schoolLevelAdapter = new ArrayAdapter<String>(M_VacantPositions.this, android.R.layout.simple_spinner_dropdown_item, BuildingIllegalSpinner);
        visitDesignation.setAdapter(schoolLevelAdapter);
        visitDesignation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                SLevel = visitDesignation.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });


    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(M_VacantPositions.this, M_VacantPositionsList.class));
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("vacantkeydesignation", SLevel);
        editor.putString("vacantkeyseats", noOfSeats.getText().toString());
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
        String namee = preferences.getString("vacantkeydesignation", "");
        String seats = preferences.getString("vacantkeyseats", "");
        noOfSeats.setText(seats);
        if (namee.equals("SET")) {
            visitDesignation.setSelection(32, true);
        } else if (namee.equals("AT")) {
            visitDesignation.setSelection(1, true);
        } else if (namee.equals("Chowkidar")) {
            visitDesignation.setSelection(2, true);
        } else if (namee.equals("CT")) {
            visitDesignation.setSelection(3, true);
        } else if (namee.equals("D.P.E")) {
            visitDesignation.setSelection(4, true);
        } else if (namee.equals("DM")) {
            visitDesignation.setSelection(5, true);
        } else if (namee.equals("Driver")) {
            visitDesignation.setSelection(6, true);
        } else if (namee.equals("Head Master/Mistress")) {
            visitDesignation.setSelection(7, true);
        } else if (namee.equals("Cook")) {
            visitDesignation.setSelection(8, true);
        } else if (namee.equals("Caller")) {
            visitDesignation.setSelection(9, true);
        } else if (namee.equals("Hostel Warden")) {
            visitDesignation.setSelection(10, true);
        } else if (namee.equals("Work Shop Attendant")) {
            visitDesignation.setSelection(11, true);
        } else if (namee.equals("IT Teacher")) {
            visitDesignation.setSelection(12, true);
        } else if (namee.equals("J/Clerk")) {
            visitDesignation.setSelection(13, true);
        } else if (namee.equals("Lab Assistant")) {
            visitDesignation.setSelection(14, true);
        } else if (namee.equals("Lab Attendant")) {
            visitDesignation.setSelection(15, true);
        } else if (namee.equals("Librarian")) {
            visitDesignation.setSelection(16, true);
        } else if (namee.equals("Mali")) {
            visitDesignation.setSelection(17, true);
        } else if (namee.equals("Naib Qasid")) {
            visitDesignation.setSelection(18, true);
        } else if (namee.equals("PET")) {
            visitDesignation.setSelection(19, true);
        } else if (namee.equals("Principal")) {
            visitDesignation.setSelection(20, true);
        } else if (namee.equals("PSHT")) {
            visitDesignation.setSelection(21, true);
        } else if (namee.equals("PST")) {
            visitDesignation.setSelection(22, true);
        } else if (namee.equals("Qari/Qaria")) {
            visitDesignation.setSelection(23, true);
        } else if (namee.equals("S/Clerk")) {
            visitDesignation.setSelection(24, true);
        } else if (namee.equals("DM")) {
            visitDesignation.setSelection(25, true);
        } else if (namee.equals("SS")) {
            visitDesignation.setSelection(26, true);
        } else if (namee.equals("Sweeper")) {
            visitDesignation.setSelection(27, true);
        } else if (namee.equals("TT")) {
            visitDesignation.setSelection(28, true);
        } else if (namee.equals("Vice Principal")) {
            visitDesignation.setSelection(29, true);
        } else if (namee.equals("Water Carrier")) {
            visitDesignation.setSelection(30, true);
        } else if (namee.equals("SST")) {
            visitDesignation.setSelection(31, true);
        } else
            visitDesignation.setSelection(0, true);

    }
}
