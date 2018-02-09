package com.softorea.schoolsen.m_monthly;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.lists.Roster_List;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Softorea on 9/15/2017.
 */

public class M_Building_Occupation extends Activity {
    LinearLayout typeLayout, occupiedBYLayout, occupiedSinceLayout;
    Button back, next;
    TextView ocupiedSince;
    Spinner typespinner, occupiedbyspinner, illegalspinner;
    String id;
    String SLevel, Sleveltwo, Slevelthree;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_school_occupation);
        illegalspinner = (Spinner) findViewById(R.id.building_under_illegal);
        ocupiedSince = (TextView) findViewById(R.id.occupiedsince);
        typespinner = (Spinner) findViewById(R.id.typeof_illegal);
        occupiedbyspinner = (Spinner) findViewById(R.id.occupied_by);
        typeLayout = (LinearLayout) findViewById(R.id.typelayout);
        occupiedBYLayout = (LinearLayout) findViewById(R.id.occupied_bylayout);
        occupiedSinceLayout = (LinearLayout) findViewById(R.id.occupied_sincelayout);
        Intent intent = getIntent();
        id = intent.getStringExtra("data");

        final Calendar myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                ocupiedSince.setText(sdf.format(myCalendar.getTime()));
            }

        };

        ocupiedSince.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DatePickerDialog dialog = new DatePickerDialog(M_Building_Occupation.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
                dialog.getDatePicker().setMaxDate(new Date().getTime());
                dialog.show();
            }
        });

        String[] BuildingIllegalSpinner = {"No", "Wholly", "Partially"};

        ArrayAdapter<String> schoolLevelAdapter = new ArrayAdapter<String>(M_Building_Occupation.this, android.R.layout.simple_spinner_dropdown_item, BuildingIllegalSpinner);
        illegalspinner.setAdapter(schoolLevelAdapter);
        illegalspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                SLevel = illegalspinner.getSelectedItem().toString();
                if (SLevel.equals("Wholly") || SLevel.equals("Partially")) {
                    //typeLayout.setVisibility(View.VISIBLE);
                    occupiedBYLayout.setVisibility(View.VISIBLE);
                    occupiedSinceLayout.setVisibility(View.VISIBLE);


                } else if (SLevel.equals("No")) {
                    //typeLayout.setVisibility(View.GONE);
                    occupiedBYLayout.setVisibility(View.GONE);
                    occupiedSinceLayout.setVisibility(View.GONE);
                    //typespinner.setSelection(0,true);
                    occupiedbyspinner.setSelection(0, true);
                    ocupiedSince.setText("");

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        String[] typespinnerr = getResources().getStringArray(R.array.illegal_occupationtype);

        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(M_Building_Occupation.this, android.R.layout.simple_spinner_dropdown_item, typespinnerr);
        typespinner.setAdapter(typeAdapter);
        typespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Sleveltwo = typespinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        String[] occupiedspiner = getResources().getStringArray(R.array.illegal_occupation_occupiedby);

        ArrayAdapter<String> occupiedAdapter = new ArrayAdapter<String>(M_Building_Occupation.this, android.R.layout.simple_spinner_dropdown_item, occupiedspiner);
        occupiedbyspinner.setAdapter(occupiedAdapter);
        occupiedbyspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Slevelthree = occupiedbyspinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        back = (Button) findViewById(R.id.ebtn_left);
        next = (Button) findViewById(R.id.ebtn_right);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
                String value = preferences.getString("case4", "");
                if ((SLevel.equals("Wholly") || SLevel.equals("Partially")) && Slevelthree.equals("None") )
                {
                    Toast.makeText(M_Building_Occupation.this, "Please select occupied by!", Toast.LENGTH_SHORT).show();
                }
                else if ((SLevel.equals("Wholly") || SLevel.equals("Partially"))
                        && ocupiedSince.getText().toString().equals("") )
                {
                    Toast.makeText(M_Building_Occupation.this, "Please select occupied since!", Toast.LENGTH_SHORT).show();
                }
                else if (value.equals("case4found")) {
                    startActivity(new Intent(M_Building_Occupation.this, TeacherWebserviceMainList.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                } else {
                    Intent view_order_intent = new Intent(M_Building_Occupation.this, M_HeadInfo.class);
                    //view_order_intent.putExtra("data", id );
                    startActivity(view_order_intent);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                //startActivity(new Intent(M_Building_Occupation.this, M_HeadInfo.class));
                //finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(M_Building_Occupation.this, M_Assembly.class));
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
        /*if (SLevel.equals("No"))
        {
            typespinner.setSelection(0,true);
            occupiedbyspinner.setSelection(0,true);
            ocupiedSince.setText("");
            editor.putString("illegalkey", SLevel);
        }
        else {
            editor.putString("illegalkey", SLevel);
        }*/
        editor.putString("illegalkey", SLevel);
        //editor.putString("occupation_type", Sleveltwo);
        editor.putString("occupation_occupied", Slevelthree);
        editor.putString("occupation_date", ocupiedSince.getText().toString());
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
        String illegalvalue = preferences.getString("illegalkey", "");
        //String typevalue = preferences.getString("occupation_type", "");
        String occupationvalue = preferences.getString("occupation_occupied", "");
        String date = preferences.getString("occupation_date", "");
        ocupiedSince.setText(date);

        if (illegalvalue.equals("Partially")) {
            illegalspinner.setSelection(2, true);
        } else if (illegalvalue.equals("Wholly")) {
            illegalspinner.setSelection(1, true);
        } else {
            illegalspinner.setSelection(0, true);
        }
        /*if (typevalue.equals("Wholly")) {
            typespinner.setSelection(0,true);
        } else if (typevalue.equals("Partially")) {
            typespinner.setSelection(1,true);
        }*/

        if (occupationvalue.equals("Land Owner")) {
            occupiedbyspinner.setSelection(6, true);
        } else if (occupationvalue.equals("Private Person")) {
            occupiedbyspinner.setSelection(2, true);
        } else if (occupationvalue.equals("Any Organization")) {
            occupiedbyspinner.setSelection(3, true);
        } else if (occupationvalue.equals("Law Enforcement Agency")) {
            occupiedbyspinner.setSelection(4, true);
        } else if (occupationvalue.equals("IDPs")) {
            occupiedbyspinner.setSelection(5, true);
        }
        else if (occupationvalue.equals("School Staff")) {
            occupiedbyspinner.setSelection(1, true);
        } 
        else {
            occupiedbyspinner.setSelection(0, true);
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
