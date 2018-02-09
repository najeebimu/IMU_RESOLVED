package com.softorea.schoolsen.m_monthly;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.lists.Roster_List;

/**
 * Created by Arslan on 10/2/2017.
 */

public class M_HeadInfo extends Activity {
    Button back, next;
    EditText InstitutionHead, HeadCellNo;
    Spinner designation;
    String id, Slevel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_head_information);
        InstitutionHead = (EditText) findViewById(R.id.head_of_institution);
        HeadCellNo = (EditText) findViewById(R.id.head_cellno);
        designation = (Spinner) findViewById(R.id.visit_designation);
        back = (Button) findViewById(R.id.btn_left);
        next = (Button) findViewById(R.id.btn_right);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noLength = HeadCellNo.getText().toString();
                if (InstitutionHead.getText().toString().equals(""))
                {
                    Toast.makeText(M_HeadInfo.this, "Please enter Head Name!", Toast.LENGTH_SHORT).show();
                }
                else if (Slevel.equals("None"))
                {
                    Toast.makeText(M_HeadInfo.this, "Please select designation!", Toast.LENGTH_SHORT).show();
                }
                else if (noLength.length() > 0 && noLength.length() < 10)
                {
                    Toast.makeText(M_HeadInfo.this, "Please enter valid number", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent view_order_intent = new Intent(M_HeadInfo.this, TeacherWebserviceMainList.class);
                    //view_order_intent.putExtra("data", id );
                    startActivity(view_order_intent);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                //startActivity(new Intent(M_HeadInfo.this, M_TeacherPresenceList.class));
                //finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(M_HeadInfo.this, M_Building_Occupation.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });

        String[] occupiedspiner = getResources().getStringArray(R.array.headoptions);

        ArrayAdapter<String> occupiedAdapter = new ArrayAdapter<String>(M_HeadInfo.this, android.R.layout.simple_spinner_dropdown_item, occupiedspiner);
        designation.setAdapter(occupiedAdapter);
        designation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Slevel = designation.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("headofinstitution", InstitutionHead.getText().toString());
        editor.putString("headdesignation", Slevel);
        editor.putString("headcellno", HeadCellNo.getText().toString());
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
        String illegalvalue = preferences.getString("headofinstitution", "");
        String typevalue = preferences.getString("headcellno", "");
        String occupationvalue = preferences.getString("headdesignation", "");
        HeadCellNo.setText(typevalue);
        InstitutionHead.setText(illegalvalue);

        if (occupationvalue.equals("Senior TT")) {
            designation.setSelection(26, true);
        } else if (occupationvalue.equals("AT")) {
            designation.setSelection(1, true);
        } else if (occupationvalue.equals("CT")) {
            designation.setSelection(2, true);
        } else if (occupationvalue.equals("D.P.E")) {
            designation.setSelection(3, true);
        } else if (occupationvalue.equals("DM")) {
            designation.setSelection(4, true);
        } else if (occupationvalue.equals("Head Master/Mistress")) {
            designation.setSelection(5, true);
        } else if (occupationvalue.equals("IT Teacher")) {
            designation.setSelection(6, true);
        } else if (occupationvalue.equals("Senior IT Teacher")) {
            designation.setSelection(7, true);
        } else if (occupationvalue.equals("Librarian")) {
            designation.setSelection(8, true);
        } else if (occupationvalue.equals("PET")) {
            designation.setSelection(9, true);
        } else if (occupationvalue.equals("Principal")) {
            designation.setSelection(10, true);
        } else if (occupationvalue.equals("PSHT")) {
            designation.setSelection(11, true);
        } else if (occupationvalue.equals("PST")) {
            designation.setSelection(12, true);
        } else if (occupationvalue.equals("Qari/Qaria")) {
            designation.setSelection(13, true);
        } else if (occupationvalue.equals("SS")) {
            designation.setSelection(14, true);
        } else if (occupationvalue.equals("TT")) {
            designation.setSelection(15, true);
        } else if (occupationvalue.equals("Vice Principal")) {
            designation.setSelection(16, true);
        } else if (occupationvalue.equals("computer lab in-charge")) {
            designation.setSelection(17, true);
        } else if (occupationvalue.equals("SST")) {
            designation.setSelection(18, true);
        } else if (occupationvalue.equals("SET")) {
            designation.setSelection(19, true);
        } else if (occupationvalue.equals("Senior AT")) {
            designation.setSelection(20, true);
        } else if (occupationvalue.equals("Senior CT")) {
            designation.setSelection(21, true);
        } else if (occupationvalue.equals("Senior DM")) {
            designation.setSelection(22, true);
        } else if (occupationvalue.equals("Senior PET")) {
            designation.setSelection(23, true);
        } else if (occupationvalue.equals("Senior PST")) {
            designation.setSelection(24, true);
        } else if (occupationvalue.equals("Senior Qari")) {
            designation.setSelection(25, true);
        } else {
            designation.setSelection(0, true);
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(M_HeadInfo.this);
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
