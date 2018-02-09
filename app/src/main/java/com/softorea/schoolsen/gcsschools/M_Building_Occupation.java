package com.softorea.schoolsen.gcsschools;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.lists.Roster_List;

/**
 * Created by Softorea on 9/15/2017.
 */

public class M_Building_Occupation extends Activity {
    RadioGroup statusGroup;
    LinearLayout yesLayout;
    LinearLayout noLayout;
    String buildingStatusValue = "Null";
    String buildingstatusdetail = "Null";
    RadioGroup yesGroup;
    RadioGroup noGroup;
    Button back, next;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.efragment_buildingavailibility);
        yesLayout = (LinearLayout) findViewById(R.id.elayout_status_yes);
        noLayout = (LinearLayout) findViewById(R.id.elayout_status_no);
        yesGroup = (RadioGroup) findViewById(R.id.estatus_yes_group);
        noGroup = (RadioGroup) findViewById(R.id.estatus_no_group);
        back = (Button) findViewById(R.id.back);
        next = (Button) findViewById(R.id.next);

        noGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.adjust_other) {
                    buildingstatusdetail = "Adjust";
                } else if (checkedId == R.id.shelter_less) {
                    buildingstatusdetail = "Shelter";
                }

            }
        });

        yesGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.government_status) {
                    buildingstatusdetail = "Community- Free Rented";
                } else if (checkedId == R.id.rented_status) {
                    buildingstatusdetail = "Rented";
                }

            }
        });

        statusGroup = (RadioGroup) findViewById(R.id.building_status_group);
        statusGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.status_yes_btn) {
                    buildingStatusValue = "Yes";
                    yesLayout.setVisibility(View.VISIBLE);
                    noLayout.setVisibility(View.GONE);

                } else if (checkedId == R.id.status_no_btn) {
                    buildingStatusValue = "No";
                    buildingstatusdetail = "NULL";
                    yesLayout.setVisibility(View.GONE);
                    // addImage.setVisibility(View.GONE);
                    // noLayout.setVisibility(rootView.VISIBLE);
                }

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(M_Building_Occupation.this, B_InfraStructure.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(M_Building_Occupation.this, com.softorea.schoolsen.gcsschools.M_School_Status.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("illegalkey", buildingStatusValue);
        editor.putString("occupation_occupied", buildingstatusdetail);
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
        String illegalvalue = preferences.getString("illegalkey", "");
        String occupationvalue = preferences.getString("occupation_occupied", "");

        if (illegalvalue.equals("Yes")) {
            statusGroup.check(R.id.status_yes_btn);
        } else if (illegalvalue.equals("No")) {
            statusGroup.check(R.id.status_no_btn);
        } else {

        }
        if (occupationvalue.equals("Community- Free Rented")) {
            yesGroup.check(R.id.government_status);
        } else if (occupationvalue.equals("Rented")) {
            yesGroup.check(R.id.rented_status);
        } else {

        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(M_Building_Occupation.this);
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
