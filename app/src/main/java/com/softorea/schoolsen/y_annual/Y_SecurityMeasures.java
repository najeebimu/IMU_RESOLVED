package com.softorea.schoolsen.y_annual;

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
import android.widget.Spinner;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.b_annual.B_BuildingCondition;
import com.softorea.schoolsen.b_annual.B_Cleanliness;
import com.softorea.schoolsen.b_annual.B_Commodities;
import com.softorea.schoolsen.b_annual.B_Construction;
import com.softorea.schoolsen.b_annual.B_IT_LabExists;
import com.softorea.schoolsen.b_annual.B_InfraStructure;
import com.softorea.schoolsen.b_annual.B_ParentTeacherCouncil;
import com.softorea.schoolsen.b_annual.B_SchoolArea;
import com.softorea.schoolsen.b_annual.B_Stipend;
import com.softorea.schoolsen.b_annual.B_TeacherGuides;
import com.softorea.schoolsen.databasehelper.DatabaseHandler;
import com.softorea.schoolsen.lists.Roster_List;
import com.softorea.schoolsen.m_monthly.M_SanctionedPostNonteachingList;
import com.softorea.schoolsen.m_monthly.MoreInfo;

/**
 * Created by Softorea on 10/2/2017.
 */

public class Y_SecurityMeasures extends Activity {
    Button back, next;
    EditText boundarywall;
    EditText HEIGHT, GUARDS, CHOWKIDAR, WEAPONS, METAL;
    Spinner barbed, glass, entrance, sos;
    String BARBED, GLASS, ENTRANCE, SOS;
    String schoolareaStr,constructionStr,buildindconditionStr,itlabStr,
            commodititesStr,ptcStr,infrastructureStr,guideStr,cleanlinessStr,stipendStr,SanctionedStr,indicatorStr,
            enrollmentageStr,enrollmentgrpStr,disabledStr,securityStr,ftbStr,furnitureStr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.y_securitymeasures);
        HEIGHT = (EditText) findViewById(R.id.boundarywallheight);
        GUARDS = (EditText) findViewById(R.id.securityguards);
        CHOWKIDAR = (EditText) findViewById(R.id.chowkidars);
        WEAPONS = (EditText) findViewById(R.id.weapons);
        METAL = (EditText) findViewById(R.id.metaldetectors);
        barbed = (Spinner) findViewById(R.id.barbedwire);
        glass = (Spinner) findViewById(R.id.glass_spikes);
        entrance = (Spinner) findViewById(R.id.entranceblocks);
        sos = (Spinner) findViewById(R.id.sos);

        String[] marital = getResources().getStringArray(R.array.ppc_working);
        ArrayAdapter<String> mar = new ArrayAdapter<String>(Y_SecurityMeasures.this, android.R.layout.simple_spinner_dropdown_item, marital);
        barbed.setAdapter(mar);
        barbed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                BARBED = barbed.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        String[] marita = getResources().getStringArray(R.array.ppc_working);
        ArrayAdapter<String> ar = new ArrayAdapter<String>(Y_SecurityMeasures.this, android.R.layout.simple_spinner_dropdown_item, marita);
        glass.setAdapter(ar);
        glass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                GLASS = glass.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        String[] marit = getResources().getStringArray(R.array.ppc_working);
        ArrayAdapter<String> aAr = new ArrayAdapter<String>(Y_SecurityMeasures.this, android.R.layout.simple_spinner_dropdown_item, marit);
        entrance.setAdapter(aAr);
        entrance.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                ENTRANCE = entrance.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        String[] rit = getResources().getStringArray(R.array.ppc_working);
        ArrayAdapter<String> r = new ArrayAdapter<String>(Y_SecurityMeasures.this, android.R.layout.simple_spinner_dropdown_item, rit);
        sos.setAdapter(r);
        sos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                SOS = sos.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        back = (Button) findViewById(R.id.ebtn_left);
        boundarywall = (EditText) findViewById(R.id.boundarywallheight);
        next = (Button) findViewById(R.id.ebtn_right);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Y_SecurityMeasures.this);
                final String middle = preferences.getString("middle", "");
                final String high = preferences.getString("high", "");
                final String hsec = preferences.getString("highsecondary", "");

                final String boys = preferences.getString("boys", "");
                final String girls = preferences.getString("girls", "");
                SQLiteOpenHelper database = new DatabaseHandler(Y_SecurityMeasures.this);
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

               if (ftbStr.equals("True"))
                {
                    startActivity(new Intent(Y_SecurityMeasures.this, Y_ProvisionFreeBooksMain.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (furnitureStr.equals("True"))
                {
                    startActivity(new Intent(Y_SecurityMeasures.this, Y_Furniture.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else
                {
                    startActivity(new Intent(Y_SecurityMeasures.this, CompleteForm.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Y_SecurityMeasures.this);
                final String middle = preferences.getString("middle", "");
                final String high = preferences.getString("high", "");
                final String hsec = preferences.getString("highsecondary", "");

                final String boys = preferences.getString("boys", "");
                final String girls = preferences.getString("girls", "");
                SQLiteOpenHelper database = new DatabaseHandler(Y_SecurityMeasures.this);
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
                if (disabledStr.equals("True"))
                {
                    startActivity(new Intent(Y_SecurityMeasures.this, Y_SpecialGirlsMain.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (enrollmentgrpStr.equals("True") && hsec.equals("HighSecondarySelected")) {
                    startActivity(new Intent(Y_SecurityMeasures.this, Y_EnrollmentElevenTwelve.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (enrollmentgrpStr.equals("True") && (middle.equals("MiddleSelected") || high.equals("HighSelected") || hsec.equals("HighSecondarySelected"))) {
                    startActivity(new Intent(Y_SecurityMeasures.this, Y_EnrollmentByGroupSectionMain.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }

                else if (enrollmentageStr.equals("True"))
                {
                    startActivity(new Intent(Y_SecurityMeasures.this, Y_EnrollmentAgeGirls.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (indicatorStr.equals("True"))
                {
                    startActivity(new Intent(Y_SecurityMeasures.this, Y_OtherFacilities.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (SanctionedStr.equals("True"))
                {
                    startActivity(new Intent(Y_SecurityMeasures.this, M_SanctionedPostNonteachingList.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (cleanlinessStr.equals("True"))
                {
                    startActivity(new Intent(Y_SecurityMeasures.this, B_Cleanliness.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (guideStr.equals("True"))
                {
                    startActivity(new Intent(Y_SecurityMeasures.this, B_TeacherGuides.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (infrastructureStr.equals("True"))
                {
                    startActivity(new Intent(Y_SecurityMeasures.this, B_InfraStructure.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (stipendStr.equals("True") && girls.equals("GirlsSelected") && (middle.equals("MiddleSelected") || high.equals("HighSelected")
                        || hsec.equals("HighSecondarySelected"))) {
                    startActivity(new Intent(Y_SecurityMeasures.this, B_Stipend.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (ptcStr.equals("True"))
                {
                    startActivity(new Intent(Y_SecurityMeasures.this, B_ParentTeacherCouncil.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (commodititesStr.equals("True"))
                {
                    startActivity(new Intent(Y_SecurityMeasures.this, B_Commodities.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (itlabStr.equals("True"))
                {
                    startActivity(new Intent(Y_SecurityMeasures.this, B_IT_LabExists.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (buildindconditionStr.equals("True"))
                {
                    startActivity(new Intent(Y_SecurityMeasures.this, B_BuildingCondition.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (constructionStr.equals("True"))
                {
                    startActivity(new Intent(Y_SecurityMeasures.this, B_Construction.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (schoolareaStr.equals("True"))
                {
                    startActivity(new Intent(Y_SecurityMeasures.this, B_SchoolArea.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else {
                    startActivity(new Intent(Y_SecurityMeasures.this, MoreInfo.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("SM_HEIGHT", HEIGHT.getText().toString());
        editor.putString("SM_GUARDS", GUARDS.getText().toString());
        editor.putString("SM_CHOWKIDAR", CHOWKIDAR.getText().toString());
        editor.putString("SM_WEAPONS", WEAPONS.getText().toString());
        editor.putString("SM_METAL", METAL.getText().toString());
        editor.putString("SM_BARBED", BARBED);
        editor.putString("SM_GLASS", GLASS);
        editor.putString("SM_ENTRANCE", ENTRANCE);
        editor.putString("SM_SOS", SOS);
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
        String a = preferences.getString("SM_HEIGHT", "");
        String b = preferences.getString("SM_GUARDS", "");
        String c = preferences.getString("SM_CHOWKIDAR", "");
        String d = preferences.getString("SM_WEAPONS", "");
        String E = preferences.getString("SM_METAL", "");
        String F = preferences.getString("SM_BARBED", "");
        String G = preferences.getString("SM_GLASS", "");
        String H = preferences.getString("SM_ENTRANCE", "");
        String I = preferences.getString("SM_SOS", "");

        HEIGHT.setText(a);
        GUARDS.setText(b);
        CHOWKIDAR.setText(c);
        WEAPONS.setText(d);
        METAL.setText(E);
        if (F.equals("No")) {
            barbed.setSelection(1, true);
        } else {
            barbed.setSelection(0, true);
        }
        if (G.equals("No")) {
            glass.setSelection(1, true);
        } else {
            glass.setSelection(0, true);
        }
        if (H.equals("No")) {
            entrance.setSelection(1, true);
        } else {
            entrance.setSelection(0, true);
        }
        if (I.equals("No")) {
            sos.setSelection(1, true);
        } else {
            sos.setSelection(0, true);
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(Y_SecurityMeasures.this);
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
