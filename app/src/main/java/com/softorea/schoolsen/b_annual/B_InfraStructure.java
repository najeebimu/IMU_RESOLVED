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
import android.widget.Spinner;

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

public class B_InfraStructure extends Activity {
    Button back, next;
    Spinner HToffice, sciencelab, itLab, Staffroom, library, clerkroom, examinationhall, playground, onescreen, alternaivesource;
    String htStr, scienceStr, itLabStr, staffStr, libStr, clerkStr, examStr, playStr, oneStr, alternateStr;
    String schoolareaStr,constructionStr,buildindconditionStr,itlabStr,
            commodititesStr,ptcStr,infrastructureStr,guideStr,cleanlinessStr,stipendStr,SanctionedStr,indicatorStr,
            enrollmentageStr,enrollmentgrpStr,disabledStr,securityStr,ftbStr,furnitureStr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b_infrastructure);
        HToffice = (Spinner) findViewById(R.id.htoffice);
        sciencelab = (Spinner) findViewById(R.id.sciencelab);
        itLab = (Spinner) findViewById(R.id.itlab);
        Staffroom = (Spinner) findViewById(R.id.staffroom);
        library = (Spinner) findViewById(R.id.library);
        clerkroom = (Spinner) findViewById(R.id.clerkroom);
        examinationhall = (Spinner) findViewById(R.id.examinationahall);
        playground = (Spinner) findViewById(R.id.playground);
        onescreen = (Spinner) findViewById(R.id.whiteboard);
        alternaivesource = (Spinner) findViewById(R.id.alternativesource);

        String[] ht = getResources().getStringArray(R.array.ppc_working);
        ArrayAdapter<String> hts = new ArrayAdapter<String>(B_InfraStructure.this, android.R.layout.simple_spinner_dropdown_item, ht);
        HToffice.setAdapter(hts);
        HToffice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                htStr = HToffice.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
        String[] science = getResources().getStringArray(R.array.infrastructure_options2);
        ArrayAdapter<String> sciencee = new ArrayAdapter<String>(B_InfraStructure.this, android.R.layout.simple_spinner_dropdown_item, science);
        sciencelab.setAdapter(sciencee);
        sciencelab.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                scienceStr = sciencelab.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
        String[] it = getResources().getStringArray(R.array.infrastructure_options2);
        ArrayAdapter<String> its = new ArrayAdapter<String>(B_InfraStructure.this, android.R.layout.simple_spinner_dropdown_item, it);
        itLab.setAdapter(its);
        itLab.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                itLabStr = itLab.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        String[] staffrom = getResources().getStringArray(R.array.ppc_working);
        ArrayAdapter<String> sr = new ArrayAdapter<String>(B_InfraStructure.this, android.R.layout.simple_spinner_dropdown_item, staffrom);
        Staffroom.setAdapter(sr);
        Staffroom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                staffStr = Staffroom.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        String[] lib = getResources().getStringArray(R.array.infrastructure_options2);
        ArrayAdapter<String> lb = new ArrayAdapter<String>(B_InfraStructure.this, android.R.layout.simple_spinner_dropdown_item, lib);
        library.setAdapter(lb);
        library.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                libStr = library.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        String[] clerk = getResources().getStringArray(R.array.ppc_working);
        ArrayAdapter<String> crk = new ArrayAdapter<String>(B_InfraStructure.this, android.R.layout.simple_spinner_dropdown_item, clerk);
        clerkroom.setAdapter(crk);
        clerkroom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                clerkStr = clerkroom.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
        String[] exam = getResources().getStringArray(R.array.ppc_working);
        ArrayAdapter<String> exa = new ArrayAdapter<String>(B_InfraStructure.this, android.R.layout.simple_spinner_dropdown_item, exam);
        examinationhall.setAdapter(exa);
        examinationhall.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                examStr = examinationhall.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
        String[] play = getResources().getStringArray(R.array.infrastructure_options);
        ArrayAdapter<String> pla = new ArrayAdapter<String>(B_InfraStructure.this, android.R.layout.simple_spinner_dropdown_item, play);
        playground.setAdapter(pla);
        playground.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                playStr = playground.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
        String[] screen = getResources().getStringArray(R.array.infrastructure_options);
        ArrayAdapter<String> scree = new ArrayAdapter<String>(B_InfraStructure.this, android.R.layout.simple_spinner_dropdown_item, screen);
        onescreen.setAdapter(scree);
        onescreen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                oneStr = onescreen.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
        String[] alter = getResources().getStringArray(R.array.alternaativesource);
        ArrayAdapter<String> alte = new ArrayAdapter<String>(B_InfraStructure.this, android.R.layout.simple_spinner_dropdown_item, alter);
        alternaivesource.setAdapter(alte);
        alternaivesource.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                alternateStr = alternaivesource.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        back = (Button) findViewById(R.id.btn_left);
        next = (Button) findViewById(R.id.btn_right);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String primary = preferences.getString("primary", "");
        final String middle = preferences.getString("middle", "");
        final String high = preferences.getString("high", "");
        final String hsec = preferences.getString("highsecondary", "");
        final String girls = preferences.getString("girls", "");
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(B_InfraStructure.this);
                final String middle = preferences.getString("middle", "");
                final String high = preferences.getString("high", "");
                final String hsec = preferences.getString("highsecondary", "");

                final String boys = preferences.getString("boys", "");
                final String girls = preferences.getString("girls", "");
                SQLiteOpenHelper database = new DatabaseHandler(B_InfraStructure.this);
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
               if (guideStr.equals("True"))
                {
                    startActivity(new Intent(B_InfraStructure.this, B_TeacherGuides.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (cleanlinessStr.equals("True"))
                {
                    startActivity(new Intent(B_InfraStructure.this, B_Cleanliness.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (SanctionedStr.equals("True"))
                {
                    startActivity(new Intent(B_InfraStructure.this, M_SanctionedPostList.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (indicatorStr.equals("True"))
                {
                    startActivity(new Intent(B_InfraStructure.this, Y_OtherFacilities.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (enrollmentageStr.equals("True"))
                {
                    startActivity(new Intent(B_InfraStructure.this, Y_EnrollmentAgeBoys.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (enrollmentgrpStr.equals("True") && (middle.equals("MiddleSelected") || high.equals("HighSelected")
                        || hsec.equals("HighSecondarySelected")))
                {
                    startActivity(new Intent(B_InfraStructure.this, Y_EnrollmentByGroupSectionMain.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (disabledStr.equals("True"))
                {
                    startActivity(new Intent(B_InfraStructure.this, Y_SpecialBoysMain.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (securityStr.equals("True"))
                {
                    startActivity(new Intent(B_InfraStructure.this, Y_SecurityMeasures.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (ftbStr.equals("True"))
                {
                    startActivity(new Intent(B_InfraStructure.this, Y_ProvisionFreeBooksMain.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (furnitureStr.equals("True"))
                {
                    startActivity(new Intent(B_InfraStructure.this, Y_Furniture.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else
                {
                    startActivity(new Intent(B_InfraStructure.this, CompleteForm.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(B_InfraStructure.this);
                final String middle = preferences.getString("middle", "");
                final String high = preferences.getString("high", "");
                final String hsec = preferences.getString("highsecondary", "");

                final String boys = preferences.getString("boys", "");
                final String girls = preferences.getString("girls", "");
                SQLiteOpenHelper database = new DatabaseHandler(B_InfraStructure.this);
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
                if (stipendStr.equals("True") && girls.equals("GirlsSelected") && (middle.equals("MiddleSelected") || high.equals("HighSelected")
                        || hsec.equals("HighSecondarySelected"))) {
                    startActivity(new Intent(B_InfraStructure.this, B_Stipend.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (ptcStr.equals("True"))
                {
                    startActivity(new Intent(B_InfraStructure.this, B_ParentTeacherCouncil.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (commodititesStr.equals("True"))
                {
                    startActivity(new Intent(B_InfraStructure.this, B_Commodities.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (itlabStr.equals("True"))
                {
                    startActivity(new Intent(B_InfraStructure.this, B_IT_LabExists.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (buildindconditionStr.equals("True"))
                {
                    startActivity(new Intent(B_InfraStructure.this, B_BuildingCondition.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (constructionStr.equals("True"))
                {
                    startActivity(new Intent(B_InfraStructure.this, B_Construction.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (schoolareaStr.equals("True"))
                {
                    startActivity(new Intent(B_InfraStructure.this, B_SchoolArea.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else {
                    startActivity(new Intent(B_InfraStructure.this, MoreInfo.class));
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
        editor.putString("htStr", htStr);
        editor.putString("scienceStr", scienceStr);
        editor.putString("itLabStr", itLabStr);
        editor.putString("staffStr", staffStr);
        editor.putString("libStr", libStr);
        editor.putString("clerkStr", clerkStr);
        editor.putString("examStr", examStr);
        editor.putString("playStr", playStr);
        editor.putString("oneStr", oneStr);
        editor.putString("alternateStr", alternateStr);

        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
        String covered = preferences.getString("htStr", "");
        String uncovered = preferences.getString("scienceStr", "");
        String brooms = preferences.getString("itLabStr", "");
        String grooms = preferences.getString("staffStr", "");
        String srooms = preferences.getString("libStr", "");
        String trooms = preferences.getString("clerkStr", "");
        String SPINNER = preferences.getString("examStr", "");
        String sroomss = preferences.getString("playStr", "");
        String troomss = preferences.getString("oneStr", "");
        String SPINNERr = preferences.getString("alternateStr", "");
        if (covered.equals("Yes")) {
            HToffice.setSelection(0, true);
        } else {
            HToffice.setSelection(1, true);
        }
        if (uncovered.equals("Not Available")) {
            sciencelab.setSelection(6, true);
        } else if (uncovered.equals("Available")) {
            sciencelab.setSelection(1, true);
        } else if (uncovered.equals("Available/Equipped")) {
            sciencelab.setSelection(2, true);
        } else if (uncovered.equals("Available/NotEquipped")) {
            sciencelab.setSelection(3, true);
        } else if (uncovered.equals("Available/Equipped/Functional")) {
            sciencelab.setSelection(4, true);
        } else if (uncovered.equals("Available/Equipped/NonFunctional")) {
            sciencelab.setSelection(5, true);
        } else {
            sciencelab.setSelection(0, true);
        }

        /*if (brooms.equals("None"))
        {
            itLab.setSelection(0,true);
        }
        else if (brooms.equals("Available/Functional"))
        {
            itLab.setSelection(1,true);
        }
        else if (brooms.equals("Available/NonFunctional"))
        {
            itLab.setSelection(2,true);
        }
        else if (brooms.equals("Not Available"))
        {
            itLab.setSelection(3,true);
        }*/
        if (grooms.equals("Yes")) {
            Staffroom.setSelection(0, true);
        } else {
            Staffroom.setSelection(1, true);
        }
        if (srooms.equals("Not Available")) {
            library.setSelection(6, true);
        } else if (srooms.equals("Available")) {
            library.setSelection(1, true);
        } else if (srooms.equals("Available/Equipped")) {
            library.setSelection(2, true);
        } else if (srooms.equals("Available/NotEquipped")) {
            library.setSelection(3, true);
        } else if (srooms.equals("Available/Equipped/Functional")) {
            library.setSelection(4, true);
        } else if (srooms.equals("Available/Equipped/NonFunctional")) {
            library.setSelection(5, true);
        } else {
            library.setSelection(0, true);
        }
        if (trooms.equals("Yes")) {
            clerkroom.setSelection(0, true);
        } else {
            clerkroom.setSelection(1, true);
        }
        if (sroomss.equals("Not Available")) {
            playground.setSelection(3, true);
        } else if (sroomss.equals("Available/Functional")) {
            playground.setSelection(1, true);
        } else if (sroomss.equals("Available/NonFunctional")) {
            playground.setSelection(2, true);
        } else {
            playground.setSelection(0, true);
        }
        if (troomss.equals("Not Available")) {
            onescreen.setSelection(3, true);
        } else if (troomss.equals("Available/Functional")) {
            onescreen.setSelection(1, true);
        } else if (troomss.equals("Available/NonFunctional")) {
            onescreen.setSelection(2, true);
        } else {
            onescreen.setSelection(0, true);
        }
        if (SPINNER.equals("Yes")) {
            examinationhall.setSelection(0, true);
        } else {
            examinationhall.setSelection(1, true);
        }


        if (SPINNERr.equals("Solar/UPS/Generator")) {
            alternaivesource.setSelection(7, true);
        } else if (SPINNERr.equals("Solar")) {
            alternaivesource.setSelection(1, true);
        } else if (SPINNERr.equals("UPS")) {
            alternaivesource.setSelection(2, true);
        } else if (SPINNERr.equals("Generator")) {
            alternaivesource.setSelection(3, true);
        } else if (SPINNERr.equals("Solar/UPS")) {
            alternaivesource.setSelection(4, true);
        } else if (SPINNERr.equals("Solar/Generator")) {
            alternaivesource.setSelection(5, true);
        } else if (SPINNERr.equals("UPS/Generator")) {
            alternaivesource.setSelection(6, true);
        } else {
            alternaivesource.setSelection(0, true);
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
