package com.softorea.schoolsen.gcsschools;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.lists.GPSTracker;
import com.softorea.schoolsen.lists.Roster_List;

/**
 * Created by Softorea on 10/2/2017.
 */

public class B_InfraStructure extends Activity {
    String classroominput;
    Button back, next;
    Spinner classRoomInput;
    EditText varandaInput;
    RadioGroup rgWashRoomAvailable;
    RadioGroup rgWahroomFunctional;
    RadioGroup rgDrinkingWaterAvailable;
    RadioGroup rgDrinkingWaterFunctional;
    RadioGroup rgElectricityAvailable;
    RadioGroup rgElectricityFunctional;
    RadioGroup rgClass;
    LinearLayout ClassLayout;
    String WashRoomAvailable = "Null";
    String WashroomFunctional = "Null";
    String DrinkingWaterAvailable = "Null";
    String DrinkingWaterFunctional = "Null";
    String ElectricityAvailable = "Null";
    String ElectricityFunctional = "Null";
    String Class = "Null";
    GPSTracker gps;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.efragment_infrastructure);
        gps = new GPSTracker(B_InfraStructure.this);
        rgWahroomFunctional = (RadioGroup) findViewById(R.id.rg_washroom_func);
        rgWashRoomAvailable = (RadioGroup) findViewById(R.id.rg_washroom_avail);
        rgElectricityAvailable = (RadioGroup) findViewById(R.id.rg_elec_avail);
        rgElectricityFunctional = (RadioGroup) findViewById(R.id.rg_elec_func);
        rgDrinkingWaterAvailable = (RadioGroup) findViewById(R.id.rg_drinking_avail);
        rgDrinkingWaterFunctional = (RadioGroup) findViewById(R.id.rg_drinking_func);
        classRoomInput = (Spinner) findViewById(R.id.classroom_edit);
        varandaInput = (EditText) findViewById(R.id.varanda_edit);
        ClassLayout = (LinearLayout) findViewById(R.id.classroom_layout);
        rgClass = (RadioGroup) findViewById(R.id.rd_class);

        if (gps.canGetLocation()) {

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            double accuracy = gps.getAccuracy();
            String lat = String.valueOf(latitude);
            String lng = String.valueOf(longitude);
            String acc = String.valueOf(accuracy);

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("lat2", lat);
            editor.putString("lng2", lng);
            editor.putString("acc2", acc);
            editor.apply();

            // \n is for new line
            //Toast.makeText(getApplicationContext(), "Lat: " + lat + "\nLong: " + lng + " " + acc, Toast.LENGTH_LONG).show();
        } else {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            //gps.showSettingsAlert();
        }

        back = (Button) findViewById(R.id.back);
        next = (Button) findViewById(R.id.next);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(B_InfraStructure.this, M_Building_Occupation.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(B_InfraStructure.this, GcsteacherList.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });

        String[] ht = getResources().getStringArray(R.array.e_infra_entries);
        ArrayAdapter<String> hts = new ArrayAdapter<String>(B_InfraStructure.this, android.R.layout.simple_spinner_dropdown_item, ht);
        classRoomInput.setAdapter(hts);
        classRoomInput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                classroominput = classRoomInput.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        rgClass.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int chechId) {

                if (chechId == R.id.class_yes) {
                    Class = "Yes";
                    ClassLayout.setVisibility(View.VISIBLE);
                } else if (chechId == R.id.class_no) {
                    Class = "No";
                    ClassLayout.setVisibility(View.GONE);
                }

            }
        });

        rgDrinkingWaterAvailable
                .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if (checkedId == R.id.rd_drinking_yes) {
                            DrinkingWaterAvailable = "Yes";
                            rgDrinkingWaterFunctional.setVisibility(View.VISIBLE);
                        } else if (checkedId == R.id.rd_drinking_no) {
                            DrinkingWaterAvailable = "No";
                            rgDrinkingWaterFunctional.setVisibility(View.GONE);
                            rgDrinkingWaterFunctional.clearCheck();
                        }

                    }
                });

        rgDrinkingWaterFunctional.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rd_drinking_func) {
                    DrinkingWaterFunctional = "Functional";
                } else if (checkedId == R.id.rd_drinking_nonfunc) {
                    DrinkingWaterFunctional = "Non Functional";
                }

            }
        });

        rgElectricityAvailable
                .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if (checkedId == R.id.rd_elec_yes) {
                            ElectricityAvailable = "Yes";
                            rgElectricityFunctional.setVisibility(View.VISIBLE);
                        } else if (checkedId == R.id.rd_elec_no) {
                            ElectricityAvailable = "No";
                            rgElectricityFunctional.setVisibility(View.GONE);
                            rgElectricityFunctional.clearCheck();
                        }

                    }
                });

        rgElectricityFunctional
                .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if (checkedId == R.id.rd_elec_func) {
                            ElectricityFunctional = "Functional";
                        } else if (checkedId == R.id.rd_elec_nonfunc) {
                            ElectricityFunctional = "Non Functional";
                        }

                    }
                });


        rgWashRoomAvailable
                .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if (checkedId == R.id.rd_wash_yes) {
                            WashRoomAvailable = "Yes";
                            rgWahroomFunctional.setVisibility(View.VISIBLE);
                        } else if (checkedId == R.id.rd_wash_no) {
                            WashRoomAvailable = "No";
                            rgWahroomFunctional.setVisibility(View.GONE);
                            rgWahroomFunctional.clearCheck();
                        }

                    }
                });

        rgWahroomFunctional
                .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if (checkedId == R.id.rd_wash_func) {
                            WashroomFunctional = "Functional";
                        } else if (checkedId == R.id.rd_wash_nonfunc) {
                            WashroomFunctional = "Non Functional";
                        }

                    }
                });


    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("classroominput", classroominput);
        editor.putString("classavailable", Class);
        editor.putString("toiletAvail", WashRoomAvailable);
        editor.putString("toiletFunction", WashroomFunctional);
        editor.putString("electriAvailable", ElectricityAvailable);
        editor.putString("electricFunction", ElectricityFunctional);
        editor.putString("waterAvail", DrinkingWaterAvailable);
        editor.putString("waterFunction", DrinkingWaterFunctional);
        editor.putString("varandainput", varandaInput.getText().toString());

        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
        String classroominput = preferences.getString("classroominput", "");
        String classavailable = preferences.getString("classavailable", "");
        String WashRoomAvailable = preferences.getString("toiletAvail", "");
        String WashroomFunctional = preferences.getString("toiletFunction", "");
        String ElectricityAvailable = preferences.getString("electriAvailable", "");
        String ElectricityFunctional = preferences.getString("electricFunction", "");
        String DrinkingWaterAvailable = preferences.getString("waterAvail", "");
        String DrinkingWaterFunctional = preferences.getString("waterFunction", "");
        String varandainputt = preferences.getString("varandainput", "");
        varandaInput.setText(varandainputt);
        if (classroominput != null) {
            if (classroominput.equals("4")) {
                classRoomInput.setSelection(3, true);
            } else if (classroominput.equals("2")) {
                classRoomInput.setSelection(1, true);
            } else if (classroominput.equals("3")) {
                classRoomInput.setSelection(2, true);
            } else {
                classRoomInput.setSelection(0, true);
            }
        } else {

        }

        if (classavailable.equals("Yes")) {
            rgClass.check(R.id.class_yes);
        } else if (classavailable.equals("No")) {
            rgClass.check(R.id.class_no);
        } else {

        }

        if (WashRoomAvailable.equals("Yes")) {
            rgWashRoomAvailable.check(R.id.rd_wash_yes);
        } else if (WashRoomAvailable.equals("No")) {
            rgWashRoomAvailable.check(R.id.rd_wash_no);
        } else {

        }

        if (WashroomFunctional.equals("Functional")) {
            rgWahroomFunctional.check(R.id.rd_wash_func);
        } else if (WashroomFunctional.equals("Non Functional")) {
            rgWahroomFunctional.check(R.id.rd_wash_nonfunc);
        } else {

        }

        if (ElectricityAvailable.equals("Yes")) {
            rgElectricityAvailable.check(R.id.rd_elec_yes);
        } else if (ElectricityAvailable.equals("No")) {
            rgElectricityAvailable.check(R.id.rd_elec_no);
        } else {

        }

        if (ElectricityFunctional.equals("Functional")) {
            rgElectricityFunctional.check(R.id.rd_elec_func);
        } else if (ElectricityFunctional.equals("Non Functional")) {
            rgElectricityFunctional.check(R.id.rd_elec_nonfunc);
        } else {

        }

        if (DrinkingWaterAvailable.equals("Yes")) {
            rgDrinkingWaterAvailable.check(R.id.rd_drinking_yes);
        } else if (DrinkingWaterAvailable.equals("No")) {
            rgDrinkingWaterAvailable.check(R.id.rd_drinking_no);
        } else {

        }

        if (DrinkingWaterFunctional.equals("Functional")) {
            rgDrinkingWaterFunctional.check(R.id.rd_drinking_func);
        } else if (DrinkingWaterFunctional.equals("Non Functional")) {
            rgDrinkingWaterFunctional.check(R.id.rd_drinking_nonfunc);
        } else {

        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(B_InfraStructure.this);
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
