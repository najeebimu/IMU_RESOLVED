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

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.databasehelper.DatabaseHandler;
import com.softorea.schoolsen.lists.GPSTracker;
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

public class B_BuildingCondition extends Activity {
    LinearLayout one, two, three, four;
    Spinner building_condition_spinner;
    Button back, next;
    EditText noOfClassrooms, noOfOtherRooms, noOfClassroomsMajor, noOfOtherroomsMajor;
    String SLevel;
    GPSTracker gps;
    String schoolareaStr,constructionStr,buildindconditionStr,itlabStr,
            commodititesStr,ptcStr,infrastructureStr,guideStr,cleanlinessStr,stipendStr,SanctionedStr,indicatorStr,
            enrollmentageStr,enrollmentgrpStr,disabledStr,securityStr,ftbStr,furnitureStr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b_buildingcondition);
        gps = new GPSTracker(B_BuildingCondition.this);

        // check if GPS enabled
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

           } else {
        }
        building_condition_spinner = (Spinner) findViewById(R.id.building_construction_spinner);
        one = (LinearLayout) findViewById(R.id.layoutone);
        two = (LinearLayout) findViewById(R.id.layouttwo);
        three = (LinearLayout) findViewById(R.id.layoutthree);
        four = (LinearLayout) findViewById(R.id.layoutfour);

        noOfClassrooms = (EditText) findViewById(R.id.no_ofclassrooms_for_recons);
        noOfOtherRooms = (EditText) findViewById(R.id.no_ofotherrooms_for_recons);
        noOfClassroomsMajor = (EditText) findViewById(R.id.no_ofclassrooms_requiremajor);
        noOfOtherroomsMajor = (EditText) findViewById(R.id.no_ofotherrooms_requiremajor);


        String[] BuildingIllegalSpinner = {"Yes", "No"};

        ArrayAdapter<String> schoolLevelAdapter = new ArrayAdapter<String>(B_BuildingCondition.this, android.R.layout.simple_spinner_dropdown_item, BuildingIllegalSpinner);
        building_condition_spinner.setAdapter(schoolLevelAdapter);
        building_condition_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                SLevel = building_condition_spinner.getSelectedItem().toString();
                if (SLevel.equals("Yes")) {
                    one.setVisibility(View.GONE);
                    two.setVisibility(View.GONE);
                    three.setVisibility(View.GONE);
                    four.setVisibility(View.GONE);
                    noOfClassrooms.setText("");
                    noOfOtherRooms.setText("");
                    noOfOtherroomsMajor.setText("");
                    noOfClassroomsMajor.setText("");
                } else if (SLevel.equals("No")) {
                    one.setVisibility(View.VISIBLE);
                    two.setVisibility(View.VISIBLE);
                    three.setVisibility(View.VISIBLE);
                    four.setVisibility(View.VISIBLE);

                }

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
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(B_BuildingCondition.this);
                final String middle = preferences.getString("middle", "");
                final String high = preferences.getString("high", "");
                final String hsec = preferences.getString("highsecondary", "");

                final String boys = preferences.getString("boys", "");
                final String girls = preferences.getString("girls", "");
                SQLiteOpenHelper database = new DatabaseHandler(B_BuildingCondition.this);
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
                if (itlabStr.equals("True"))
                {
                    startActivity(new Intent(B_BuildingCondition.this, B_IT_LabExists.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }

                else if (commodititesStr.equals("True"))
                {
                    startActivity(new Intent(B_BuildingCondition.this, B_Commodities.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (ptcStr.equals("True"))
                {
                    startActivity(new Intent(B_BuildingCondition.this, B_ParentTeacherCouncil.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (stipendStr.equals("True") && girls.equals("GirlsSelected") && (middle.equals("MiddleSelected") || high.equals("HighSelected")
                        || hsec.equals("HighSecondarySelected")))
                {
                    startActivity(new Intent(B_BuildingCondition.this, B_Stipend.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (infrastructureStr.equals("True"))
                {
                    startActivity(new Intent(B_BuildingCondition.this, B_InfraStructure.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (guideStr.equals("True"))
                {
                    startActivity(new Intent(B_BuildingCondition.this, B_TeacherGuides.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (cleanlinessStr.equals("True"))
                {
                    startActivity(new Intent(B_BuildingCondition.this, B_Cleanliness.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (SanctionedStr.equals("True"))
                {
                    startActivity(new Intent(B_BuildingCondition.this, M_SanctionedPostList.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (indicatorStr.equals("True"))
                {
                    startActivity(new Intent(B_BuildingCondition.this, Y_OtherFacilities.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (enrollmentageStr.equals("True"))
                {
                    startActivity(new Intent(B_BuildingCondition.this, Y_EnrollmentAgeBoys.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (enrollmentgrpStr.equals("True") && (middle.equals("MiddleSelected") || high.equals("HighSelected")
                        || hsec.equals("HighSecondarySelected")))
                {
                    startActivity(new Intent(B_BuildingCondition.this, Y_EnrollmentByGroupSectionMain.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (disabledStr.equals("True"))
                {
                    startActivity(new Intent(B_BuildingCondition.this, Y_SpecialBoysMain.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (securityStr.equals("True"))
                {
                    startActivity(new Intent(B_BuildingCondition.this, Y_SecurityMeasures.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (ftbStr.equals("True"))
                {
                    startActivity(new Intent(B_BuildingCondition.this, Y_ProvisionFreeBooksMain.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (furnitureStr.equals("True"))
                {
                    startActivity(new Intent(B_BuildingCondition.this, Y_Furniture.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else
                {
                    startActivity(new Intent(B_BuildingCondition.this, CompleteForm.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(B_BuildingCondition.this);
                final String middle = preferences.getString("middle", "");
                final String high = preferences.getString("high", "");
                final String hsec = preferences.getString("highsecondary", "");

                final String boys = preferences.getString("boys", "");
                final String girls = preferences.getString("girls", "");
                SQLiteOpenHelper database = new DatabaseHandler(B_BuildingCondition.this);
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
                if (constructionStr.equals("True"))
                {
                    startActivity(new Intent(B_BuildingCondition.this, B_Construction.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (schoolareaStr.equals("True"))
                {
                    startActivity(new Intent(B_BuildingCondition.this, B_SchoolArea.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else {
                    startActivity(new Intent(B_BuildingCondition.this, MoreInfo.class));
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
        editor.putString("noOfClassrooms", noOfClassrooms.getText().toString());
        editor.putString("noOfOtherRooms", noOfOtherRooms.getText().toString());
        editor.putString("noOfClassroomsMajor", noOfClassroomsMajor.getText().toString());
        editor.putString("noOfOtherroomsMajor", noOfOtherroomsMajor.getText().toString());
        editor.putString("WHOLENEEDS", SLevel);
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
        String covered = preferences.getString("noOfClassrooms", "");
        String uncovered = preferences.getString("noOfOtherRooms", "");
        String brooms = preferences.getString("noOfClassroomsMajor", "");
        String grooms = preferences.getString("noOfOtherroomsMajor", "");
        String wholeneeds = preferences.getString("WHOLENEEDS", "");
        noOfClassrooms.setText(covered);
        noOfOtherRooms.setText(uncovered);
        noOfClassroomsMajor.setText(brooms);
        noOfOtherroomsMajor.setText(grooms);
        if (wholeneeds.equals("Yes")) {
            building_condition_spinner.setSelection(0, true);
        } else {
            building_condition_spinner.setSelection(1, true);
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(B_BuildingCondition.this);
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
