package com.softorea.schoolsen.b_annual;

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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.databasehelper.DatabaseHandler;
import com.softorea.schoolsen.lists.Roster_List;
import com.softorea.schoolsen.m_monthly.M_SanctionedPostList;
import com.softorea.schoolsen.m_monthly.MoreInfo;
import com.softorea.schoolsen.y_annual.CompleteForm;
import com.softorea.schoolsen.y_annual.Y_EnrollmentAgeBoys;
import com.softorea.schoolsen.y_annual.Y_EnrollmentByGroupSectionMain;
import com.softorea.schoolsen.y_annual.Y_Furniture;
import com.softorea.schoolsen.y_annual.Y_OtherFacilities;
import com.softorea.schoolsen.y_annual.Y_ProvisionFreeBooksMain;
import com.softorea.schoolsen.y_annual.Y_SecurityMeasures;
import com.softorea.schoolsen.y_annual.Y_SpecialBoysMain;

/**
 * Created by Softorea on 10/2/2017.
 */

public class B_IT_LabExists extends Activity {
    Button back, next;
    Spinner ItLabExists, LabFunctional, LabEstablished, InternetAvailable;
    EditText noOfComputers, nameOfFirm, noOfFunctionalComputers;
    LinearLayout one, two, three, four, five, six;
    String SLevel, LabFun, LabEst, InternetFun;
    String bolvalue;
    String schoolareaStr,constructionStr,buildindconditionStr,itlabStr,
            commodititesStr,ptcStr,infrastructureStr,guideStr,cleanlinessStr,stipendStr,SanctionedStr,indicatorStr,
            enrollmentageStr,enrollmentgrpStr,disabledStr,securityStr,ftbStr,furnitureStr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b_it_labexist);

        LabFunctional = (Spinner) findViewById(R.id.labfunctionalspinner);
        LabEstablished = (Spinner) findViewById(R.id.lab_establishedby);
        InternetAvailable = (Spinner) findViewById(R.id.internet_available);
        noOfComputers = (EditText) findViewById(R.id.no_ofcomputersinlab);
        nameOfFirm = (EditText) findViewById(R.id.firmname);
        noOfFunctionalComputers = (EditText) findViewById(R.id.no_offunctional_computers);

        one = (LinearLayout) findViewById(R.id.layoutone);
        two = (LinearLayout) findViewById(R.id.layouttwo);
        three = (LinearLayout) findViewById(R.id.layoutthree);
        four = (LinearLayout) findViewById(R.id.layoutfour);
        five = (LinearLayout) findViewById(R.id.layoutfive);
        six = (LinearLayout) findViewById(R.id.layoutsix);
        ItLabExists = (Spinner) findViewById(R.id.labexistspinner);
        back = (Button) findViewById(R.id.btn_left);
        next = (Button) findViewById(R.id.btn_right);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(B_IT_LabExists.this);
                final String middle = preferences.getString("middle", "");
                final String high = preferences.getString("high", "");
                final String hsec = preferences.getString("highsecondary", "");

                final String boys = preferences.getString("boys", "");
                final String girls = preferences.getString("girls", "");
                SQLiteOpenHelper database = new DatabaseHandler(B_IT_LabExists.this);
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
                String s = noOfComputers.getText().toString();
                String s1 = noOfFunctionalComputers.getText().toString();
                /*SQLiteOpenHelper database = new DatabaseHandler(B_IT_LabExists.this);
                SQLiteDatabase dbd = database.getReadableDatabase();
                Cursor cursorform = dbd.rawQuery("SELECT * FROM t_screenconfig", null);
                if (cursorform.moveToFirst()) {
                    do {
                        bolvalue = cursorform.getString(cursorform.getColumnIndex("Bi_Commodities"));
                    } while (cursorform.moveToNext());
                }
                cursorform.close();
                dbd.close();
                if (bolvalue.equals("False"))
                {
                    startActivity(new Intent(B_IT_LabExists.this, B_ParentTeacherCouncil.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else*/
                if (s1.equals("") || s.equals("")) {
                    if (commodititesStr.equals("True"))
                    {
                        startActivity(new Intent(B_IT_LabExists.this, B_Commodities.class));
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        finish();
                    }
                    else if (ptcStr.equals("True"))
                    {
                        startActivity(new Intent(B_IT_LabExists.this, B_ParentTeacherCouncil.class));
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        finish();
                    }
                    else if (stipendStr.equals("True") && girls.equals("GirlsSelected") && (middle.equals("MiddleSelected") || high.equals("HighSelected")
                            || hsec.equals("HighSecondarySelected")))
                    {
                        startActivity(new Intent(B_IT_LabExists.this, B_Stipend.class));
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        finish();
                    }
                    else if (infrastructureStr.equals("True"))
                    {
                        startActivity(new Intent(B_IT_LabExists.this, B_InfraStructure.class));
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        finish();
                    }
                    else if (guideStr.equals("True"))
                    {
                        startActivity(new Intent(B_IT_LabExists.this, B_TeacherGuides.class));
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        finish();
                    }
                    else if (cleanlinessStr.equals("True"))
                    {
                        startActivity(new Intent(B_IT_LabExists.this, B_Cleanliness.class));
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        finish();
                    }
                    else if (SanctionedStr.equals("True"))
                    {
                        startActivity(new Intent(B_IT_LabExists.this, M_SanctionedPostList.class));
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        finish();
                    }
                    else if (indicatorStr.equals("True"))
                    {
                        startActivity(new Intent(B_IT_LabExists.this, Y_OtherFacilities.class));
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        finish();
                    }
                    else if (enrollmentageStr.equals("True"))
                    {
                        startActivity(new Intent(B_IT_LabExists.this, Y_EnrollmentAgeBoys.class));
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        finish();
                    }
                    else if (enrollmentgrpStr.equals("True") && (middle.equals("MiddleSelected") || high.equals("HighSelected")
                            || hsec.equals("HighSecondarySelected")))
                    {
                        startActivity(new Intent(B_IT_LabExists.this, Y_EnrollmentByGroupSectionMain.class));
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        finish();
                    }
                    else if (disabledStr.equals("True"))
                    {
                        startActivity(new Intent(B_IT_LabExists.this, Y_SpecialBoysMain.class));
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        finish();
                    }
                    else if (securityStr.equals("True"))
                    {
                        startActivity(new Intent(B_IT_LabExists.this, Y_SecurityMeasures.class));
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        finish();
                    }
                    else if (ftbStr.equals("True"))
                    {
                        startActivity(new Intent(B_IT_LabExists.this, Y_ProvisionFreeBooksMain.class));
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        finish();
                    }
                    else if (furnitureStr.equals("True"))
                    {
                        startActivity(new Intent(B_IT_LabExists.this, Y_Furniture.class));
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        finish();
                    }
                    else
                    {
                        startActivity(new Intent(B_IT_LabExists.this, CompleteForm.class));
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        finish();
                    }
                } else if (Integer.parseInt(s1) <= Integer.parseInt(s)) {
                    if (commodititesStr.equals("True"))
                    {
                        startActivity(new Intent(B_IT_LabExists.this, B_Commodities.class));
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        finish();
                    }
                    else if (ptcStr.equals("True"))
                    {
                        startActivity(new Intent(B_IT_LabExists.this, B_ParentTeacherCouncil.class));
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        finish();
                    }
                    else if (stipendStr.equals("True") && girls.equals("GirlsSelected") && (middle.equals("MiddleSelected") || high.equals("HighSelected")
                            || hsec.equals("HighSecondarySelected")))
                    {
                        startActivity(new Intent(B_IT_LabExists.this, B_Stipend.class));
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        finish();
                    }
                    else if (infrastructureStr.equals("True"))
                    {
                        startActivity(new Intent(B_IT_LabExists.this, B_InfraStructure.class));
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        finish();
                    }
                    else if (guideStr.equals("True"))
                    {
                        startActivity(new Intent(B_IT_LabExists.this, B_TeacherGuides.class));
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        finish();
                    }
                    else if (cleanlinessStr.equals("True"))
                    {
                        startActivity(new Intent(B_IT_LabExists.this, B_Cleanliness.class));
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        finish();
                    }
                    else if (SanctionedStr.equals("True"))
                    {
                        startActivity(new Intent(B_IT_LabExists.this, M_SanctionedPostList.class));
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        finish();
                    }
                    else if (indicatorStr.equals("True"))
                    {
                        startActivity(new Intent(B_IT_LabExists.this, Y_OtherFacilities.class));
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        finish();
                    }
                    else if (enrollmentageStr.equals("True"))
                    {
                        startActivity(new Intent(B_IT_LabExists.this, Y_EnrollmentAgeBoys.class));
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        finish();
                    }
                    else if (enrollmentgrpStr.equals("True") && (middle.equals("MiddleSelected") || high.equals("HighSelected")
                            || hsec.equals("HighSecondarySelected")))
                    {
                        startActivity(new Intent(B_IT_LabExists.this, Y_EnrollmentByGroupSectionMain.class));
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        finish();
                    }
                    else if (disabledStr.equals("True"))
                    {
                        startActivity(new Intent(B_IT_LabExists.this, Y_SpecialBoysMain.class));
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        finish();
                    }
                    else if (securityStr.equals("True"))
                    {
                        startActivity(new Intent(B_IT_LabExists.this, Y_SecurityMeasures.class));
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        finish();
                    }
                    else if (ftbStr.equals("True"))
                    {
                        startActivity(new Intent(B_IT_LabExists.this, Y_ProvisionFreeBooksMain.class));
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        finish();
                    }
                    else if (furnitureStr.equals("True"))
                    {
                        startActivity(new Intent(B_IT_LabExists.this, Y_Furniture.class));
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        finish();
                    }
                    else
                    {
                        startActivity(new Intent(B_IT_LabExists.this, CompleteForm.class));
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        finish();
                    }
                } else {
                    Toast.makeText(B_IT_LabExists.this, "Functional Computers cannot be greater than no of computers", Toast.LENGTH_SHORT).show();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(B_IT_LabExists.this);
                final String middle = preferences.getString("middle", "");
                final String high = preferences.getString("high", "");
                final String hsec = preferences.getString("highsecondary", "");

                final String boys = preferences.getString("boys", "");
                final String girls = preferences.getString("girls", "");
                SQLiteOpenHelper database = new DatabaseHandler(B_IT_LabExists.this);
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
                if (buildindconditionStr.equals("True"))
                {
                    startActivity(new Intent(B_IT_LabExists.this, B_BuildingCondition.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (constructionStr.equals("True"))
                {
                    startActivity(new Intent(B_IT_LabExists.this, B_Construction.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (schoolareaStr.equals("True"))
                {
                    startActivity(new Intent(B_IT_LabExists.this, B_SchoolArea.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else {
                    startActivity(new Intent(B_IT_LabExists.this, MoreInfo.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
            }
        });

        String[] marital = getResources().getStringArray(R.array.itlb);
        ArrayAdapter<String> mar = new ArrayAdapter<String>(B_IT_LabExists.this, android.R.layout.simple_spinner_dropdown_item, marital);
        LabFunctional.setAdapter(mar);
        LabFunctional.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                LabFun = LabFunctional.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        String[] maritall = getResources().getStringArray(R.array.lab_establishby);
        ArrayAdapter<String> marr = new ArrayAdapter<String>(B_IT_LabExists.this, android.R.layout.simple_spinner_dropdown_item, maritall);
        LabEstablished.setAdapter(marr);
        LabEstablished.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                LabEst = LabEstablished.getSelectedItem().toString();
                if (LabEst.equals("Private Firm")) {
                    three.setVisibility(View.VISIBLE);
                } else {
                    three.setVisibility(View.GONE);
                    nameOfFirm.setText("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        String[] maritallL = getResources().getStringArray(R.array.itlb);
        ArrayAdapter<String> marrR = new ArrayAdapter<String>(B_IT_LabExists.this, android.R.layout.simple_spinner_dropdown_item, maritallL);
        InternetAvailable.setAdapter(marrR);
        InternetAvailable.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                InternetFun = InternetAvailable.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        String[] genderSpinner = {"Yes", "No"};
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(B_IT_LabExists.this, android.R.layout.simple_spinner_dropdown_item, genderSpinner);
        ItLabExists.setAdapter(genderAdapter);
        ItLabExists.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                SLevel = ItLabExists.getSelectedItem().toString();
                if (SLevel.equals("No")) {
                    one.setVisibility(View.GONE);
                    two.setVisibility(View.GONE);
                    three.setVisibility(View.GONE);
                    four.setVisibility(View.GONE);
                    five.setVisibility(View.GONE);
                    six.setVisibility(View.GONE);
                    LabFunctional.setSelection(0);
                    LabFun = LabFunctional.getSelectedItem().toString();
                    LabEstablished.setSelection(0);
                    LabEst = LabEstablished.getSelectedItem().toString();
                    noOfComputers.setText("");
                    noOfFunctionalComputers.setText("");
                    InternetAvailable.setSelection(0);
                    InternetFun = InternetAvailable.getSelectedItem().toString();
                    nameOfFirm.setText("");

                } else if (SLevel.equals("Yes")) {
                    one.setVisibility(View.VISIBLE);
                    two.setVisibility(View.VISIBLE);
                    //three.setVisibility(View.VISIBLE);
                    four.setVisibility(View.VISIBLE);
                    five.setVisibility(View.VISIBLE);
                    six.setVisibility(View.VISIBLE);
                }

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
        editor.putString("SLevelll", SLevel);
        editor.putString("InternetFun", InternetFun);
        editor.putString("LabEst", LabEst);
        editor.putString("LabFun", LabFun);
        editor.putString("noOfComputers", noOfComputers.getText().toString());
        editor.putString("noOfFunctionalComputers", noOfFunctionalComputers.getText().toString());
        editor.putString("nameOfFirm", nameOfFirm.getText().toString());
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
        String covered = preferences.getString("SLevelll", "");
        String uncovered = preferences.getString("InternetFun", "");
        String brooms = preferences.getString("LabEst", "");
        String grooms = preferences.getString("LabFun", "");
        String srooms = preferences.getString("noOfComputers", "");
        String trooms = preferences.getString("noOfFunctionalComputers", "");
        String SPINNER = preferences.getString("nameOfFirm", "");
        noOfComputers.setText(srooms);
        noOfFunctionalComputers.setText(trooms);
        nameOfFirm.setText(SPINNER);
        if (covered.equals("No")) {
            ItLabExists.setSelection(1, true);
            three.setVisibility(View.GONE);
        } else {
            ItLabExists.setSelection(0, true);
        }
        if (uncovered.equals("Yes")) {
            InternetAvailable.setSelection(1, true);
        }
        else if (uncovered.equals("No")) {
            InternetAvailable.setSelection(2, true);
        }
        else {
            InternetAvailable.setSelection(0, true);
        }
        if (brooms.equals("Government")) {
            LabEstablished.setSelection(1, true);
        }
        else if (brooms.equals("Private Firm")) {
            LabEstablished.setSelection(2, true);
        }
        else {
            LabEstablished.setSelection(0, true);
        }
        if (grooms.equals("Yes")) {
            LabFunctional.setSelection(1, true);
        }
        else if (grooms.equals("No")) {
            LabFunctional.setSelection(2, true);
        }
        else {
            LabFunctional.setSelection(0, true);
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(B_IT_LabExists.this);
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
