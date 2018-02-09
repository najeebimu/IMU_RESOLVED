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
import android.widget.LinearLayout;
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
import com.softorea.schoolsen.m_monthly.M_SanctionedPostList;
import com.softorea.schoolsen.m_monthly.M_SanctionedPostNonteachingList;
import com.softorea.schoolsen.m_monthly.MoreInfo;

/**
 * Created by Softorea on 10/2/2017.
 */

public class Y_OtherFacilities extends Activity {
    Button back, next;
    Spinner HostelExists, Office, Store, Lab, Entance, Building, Toilets;
    LinearLayout hostelcapacity;
    EditText hostelCapacity;
    String SLevel, officeStr, storeStr, labStr, EntraceStr, BuildingStr, toiletsStr;
    String schoolareaStr,constructionStr,buildindconditionStr,itlabStr,
            commodititesStr,ptcStr,infrastructureStr,guideStr,cleanlinessStr,stipendStr,SanctionedStr,indicatorStr,
            enrollmentageStr,enrollmentgrpStr,disabledStr,securityStr,ftbStr,furnitureStr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.y_otherfacilities);
        back = (Button) findViewById(R.id.ebtn_left);
        HostelExists = (Spinner) findViewById(R.id.hostel_exists);
        Office = (Spinner) findViewById(R.id.office_exists);
        Store = (Spinner) findViewById(R.id.store_exists);
        Lab = (Spinner) findViewById(R.id.economicslab_exists);
        Entance = (Spinner) findViewById(R.id.schoolentrance);
        Building = (Spinner) findViewById(R.id.schoolbuilding);
        Toilets = (Spinner) findViewById(R.id.toilets);
        hostelCapacity = (EditText) findViewById(R.id.hostelcapacity);
        hostelcapacity = (LinearLayout) findViewById(R.id.layouthostel);
        next = (Button) findViewById(R.id.ebtn_right);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Y_OtherFacilities.this);
                final String middle = preferences.getString("middle", "");
                final String high = preferences.getString("high", "");
                final String hsec = preferences.getString("highsecondary", "");

                final String boys = preferences.getString("boys", "");
                final String girls = preferences.getString("girls", "");
                SQLiteOpenHelper database = new DatabaseHandler(Y_OtherFacilities.this);
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

               if (enrollmentageStr.equals("True"))
                {
                    startActivity(new Intent(Y_OtherFacilities.this, Y_EnrollmentAgeBoys.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (enrollmentgrpStr.equals("True") && (middle.equals("MiddleSelected") || high.equals("HighSelected")
                        || hsec.equals("HighSecondarySelected")))
                {
                    startActivity(new Intent(Y_OtherFacilities.this, Y_EnrollmentByGroupSectionMain.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (disabledStr.equals("True"))
                {
                    startActivity(new Intent(Y_OtherFacilities.this, Y_SpecialBoysMain.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (securityStr.equals("True"))
                {
                    startActivity(new Intent(Y_OtherFacilities.this, Y_SecurityMeasures.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (ftbStr.equals("True"))
                {
                    startActivity(new Intent(Y_OtherFacilities.this, Y_ProvisionFreeBooksMain.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (furnitureStr.equals("True"))
                {
                    startActivity(new Intent(Y_OtherFacilities.this, Y_Furniture.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else
                {
                    startActivity(new Intent(Y_OtherFacilities.this, CompleteForm.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Y_OtherFacilities.this);
                final String middle = preferences.getString("middle", "");
                final String high = preferences.getString("high", "");
                final String hsec = preferences.getString("highsecondary", "");

                final String boys = preferences.getString("boys", "");
                final String girls = preferences.getString("girls", "");
                SQLiteOpenHelper database = new DatabaseHandler(Y_OtherFacilities.this);
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
                if (SanctionedStr.equals("True"))
                {
                    startActivity(new Intent(Y_OtherFacilities.this, M_SanctionedPostNonteachingList.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (cleanlinessStr.equals("True"))
                {
                    startActivity(new Intent(Y_OtherFacilities.this, B_Cleanliness.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (guideStr.equals("True"))
                {
                    startActivity(new Intent(Y_OtherFacilities.this, B_TeacherGuides.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (infrastructureStr.equals("True"))
                {
                    startActivity(new Intent(Y_OtherFacilities.this, B_InfraStructure.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (stipendStr.equals("True") && girls.equals("GirlsSelected") && (middle.equals("MiddleSelected") || high.equals("HighSelected")
                        || hsec.equals("HighSecondarySelected"))) {
                    startActivity(new Intent(Y_OtherFacilities.this, B_Stipend.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (ptcStr.equals("True"))
                {
                    startActivity(new Intent(Y_OtherFacilities.this, B_ParentTeacherCouncil.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (commodititesStr.equals("True"))
                {
                    startActivity(new Intent(Y_OtherFacilities.this, B_Commodities.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (itlabStr.equals("True"))
                {
                    startActivity(new Intent(Y_OtherFacilities.this, B_IT_LabExists.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (buildindconditionStr.equals("True"))
                {
                    startActivity(new Intent(Y_OtherFacilities.this, B_BuildingCondition.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (constructionStr.equals("True"))
                {
                    startActivity(new Intent(Y_OtherFacilities.this, B_Construction.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (schoolareaStr.equals("True"))
                {
                    startActivity(new Intent(Y_OtherFacilities.this, B_SchoolArea.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else {
                    startActivity(new Intent(Y_OtherFacilities.this, MoreInfo.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }

            }
        });

        String[] genderSpinner = {"No", "Yes"};
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(Y_OtherFacilities.this, android.R.layout.simple_spinner_dropdown_item, genderSpinner);
        HostelExists.setAdapter(genderAdapter);
        HostelExists.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                SLevel = HostelExists.getSelectedItem().toString();
                if (SLevel.equals("No")) {
                    hostelcapacity.setVisibility(View.GONE);
                    hostelCapacity.setText("");

                } else if (SLevel.equals("Yes")) {
                    hostelcapacity.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        String[] maritallL = getResources().getStringArray(R.array.ppc_working);
        ArrayAdapter<String> marrR = new ArrayAdapter<String>(Y_OtherFacilities.this, android.R.layout.simple_spinner_dropdown_item, maritallL);
        Office.setAdapter(marrR);
        Office.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                officeStr = Office.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        String[] maritall = getResources().getStringArray(R.array.ppc_working);
        ArrayAdapter<String> marr = new ArrayAdapter<String>(Y_OtherFacilities.this, android.R.layout.simple_spinner_dropdown_item, maritall);
        Store.setAdapter(marr);
        Store.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                storeStr = Store.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
        String[] marital = getResources().getStringArray(R.array.ppc_working);
        ArrayAdapter<String> mar = new ArrayAdapter<String>(Y_OtherFacilities.this, android.R.layout.simple_spinner_dropdown_item, marital);
        Lab.setAdapter(mar);
        Lab.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                labStr = Lab.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        String[] marita = getResources().getStringArray(R.array.ppc_working);
        ArrayAdapter<String> ma = new ArrayAdapter<String>(Y_OtherFacilities.this, android.R.layout.simple_spinner_dropdown_item, marita);
        Entance.setAdapter(ma);
        Entance.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                EntraceStr = Entance.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        String[] mara = getResources().getStringArray(R.array.ppc_working);
        ArrayAdapter<String> m = new ArrayAdapter<String>(Y_OtherFacilities.this, android.R.layout.simple_spinner_dropdown_item, mara);
        Building.setAdapter(m);
        Building.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                BuildingStr = Building.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
        String[] a = getResources().getStringArray(R.array.ppc_working);
        ArrayAdapter<String> am = new ArrayAdapter<String>(Y_OtherFacilities.this, android.R.layout.simple_spinner_dropdown_item, a);
        Toilets.setAdapter(am);
        Toilets.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                toiletsStr = Toilets.getSelectedItem().toString();

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
        editor.putString("Facilities_SLevel", SLevel);
        editor.putString("Facilities_officeStr", officeStr);
        editor.putString("Facilities_storeStr", storeStr);
        editor.putString("Facilities_labStr", labStr);
        editor.putString("Facilities_EntraceStr", EntraceStr);
        editor.putString("Facilities_BuildingStr", BuildingStr);
        editor.putString("Facilities_toiletsStr", toiletsStr);
        editor.putString("Facilities_hcapacity", hostelCapacity.getText().toString());
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
        String covered = preferences.getString("Facilities_SLevel", "");
        String uncovered = preferences.getString("Facilities_officeStr", "");
        String brooms = preferences.getString("Facilities_storeStr", "");
        String grooms = preferences.getString("Facilities_labStr", "");
        String srooms = preferences.getString("Facilities_EntraceStr", "");
        String trooms = preferences.getString("Facilities_BuildingStr", "");
        String SPINNER = preferences.getString("Facilities_toiletsStr", "");
        String cap = preferences.getString("Facilities_hcapacity", "");
        hostelCapacity.setText(cap);
        if (covered.equals("No")) {
            HostelExists.setSelection(0, true);
        } else if (covered.equals("Yes")) {
            HostelExists.setSelection(1, true);
        }
        if (uncovered.equals("Yes")) {
            Office.setSelection(0, true);
        } else if (uncovered.equals("No")) {
            Office.setSelection(1, true);
        }
        if (brooms.equals("Yes")) {
            Store.setSelection(0, true);
        } else if (brooms.equals("No")) {
            Store.setSelection(1, true);
        }
        if (grooms.equals("Yes")) {
            Lab.setSelection(0, true);
        } else if (grooms.equals("No")) {
            Lab.setSelection(1, true);
        }
        if (srooms.equals("Yes")) {
            Entance.setSelection(0, true);
        } else if (srooms.equals("No")) {
            Entance.setSelection(1, true);
        }
        if (trooms.equals("Yes")) {
            Building.setSelection(0, true);
        } else if (trooms.equals("No")) {
            Building.setSelection(1, true);
        }
        if (SPINNER.equals("Yes")) {
            Toilets.setSelection(0, true);
        } else if (SPINNER.equals("No")) {
            Toilets.setSelection(1, true);
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(Y_OtherFacilities.this);
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

