package com.softorea.schoolsen.b_annual;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Softorea on 10/2/2017.
 */

public class B_Stipend extends Activity {
    Button back, next;
    String schoolareaStr,constructionStr,buildindconditionStr,itlabStr,
            commodititesStr,ptcStr,infrastructureStr,guideStr,cleanlinessStr,stipendStr,SanctionedStr,indicatorStr,
            enrollmentageStr,enrollmentgrpStr,disabledStr,securityStr,ftbStr,furnitureStr;
    String SLevel;
    LinearLayout middleLayout, highLayout;
    Spinner recordShown;
    TextView selectyear;
    EditText SchemeName6, eligibleStudents6, EligibleNoStipend6, class6remarks,
            SchemeName7, eligibleStudents7, EligibleNoStipend7, class7remarks,
            SchemeName8, eligibleStudents8, EligibleNoStipend8, class8remarks,
            SchemeName9, eligibleStudents9, EligibleNoStipend9, class9remarks,
            SchemeName10, eligibleStudents10, EligibleNoStipend10, total10, class10remarks;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b_stipend);
        recordShown = (Spinner) findViewById(R.id.recordshownn);
        SchemeName6 = (EditText) findViewById(R.id.six_scheme);
        eligibleStudents6 = (EditText) findViewById(R.id.six_eligible_students);
        EligibleNoStipend6 = (EditText) findViewById(R.id.six_notreceive_stipend);
        class6remarks = (EditText) findViewById(R.id.class6remarks);
        SchemeName7 = (EditText) findViewById(R.id.seven_scheme);
        eligibleStudents7 = (EditText) findViewById(R.id.seven_eligible_students);
        EligibleNoStipend7 = (EditText) findViewById(R.id.seven_notreceive_stipend);
        class7remarks = (EditText) findViewById(R.id.class7remarks);
        SchemeName8 = (EditText) findViewById(R.id.eight_scheme);
        eligibleStudents8 = (EditText) findViewById(R.id.eight_eligible_students);
        EligibleNoStipend8 = (EditText) findViewById(R.id.eight_notreceive_stipend);
        class8remarks = (EditText) findViewById(R.id.class8remarks);
        SchemeName9 = (EditText) findViewById(R.id.nine_scheme);
        eligibleStudents9 = (EditText) findViewById(R.id.nine_eligible_students);
        EligibleNoStipend9 = (EditText) findViewById(R.id.nine_notreceive_stipend);
        class9remarks = (EditText) findViewById(R.id.class9remarks);
        SchemeName10 = (EditText) findViewById(R.id.ten_scheme);
        eligibleStudents10 = (EditText) findViewById(R.id.ten_eligible_students);
        EligibleNoStipend10 = (EditText) findViewById(R.id.ten_notreceive_stipend);
        total10 = (EditText) findViewById(R.id.tentotal);
        class10remarks = (EditText) findViewById(R.id.class10remarks);
        selectyear = (TextView) findViewById(R.id.year);

        final Calendar myCalendarr = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener datee = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendarr.set(Calendar.YEAR, year);
                myCalendarr.set(Calendar.MONTH, monthOfYear);
                myCalendarr.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                int year = myCalendarr.get(Calendar.YEAR);
                selectyear.setText("" + year);
            }

        };

        selectyear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DatePickerDialog dialog = new DatePickerDialog(B_Stipend.this, datee, myCalendarr
                        .get(Calendar.YEAR), myCalendarr.get(Calendar.MONTH), myCalendarr.get(Calendar.DAY_OF_MONTH));
                dialog.getDatePicker().setMaxDate(new Date().getTime());
                dialog.show();

            }
        });

        //total10.setEnabled(false);


        middleLayout = (LinearLayout) findViewById(R.id.middleLayout);
        highLayout = (LinearLayout) findViewById(R.id.highLayout);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String middle = preferences.getString("middle", "");
        String high = preferences.getString("high", "");
        if (middle.equals("MiddleSelected")) {
            highLayout.setVisibility(View.GONE);
        } else if (high.equals("HighSelected")) {


        }
        back = (Button) findViewById(R.id.ebtn_left);
        next = (Button) findViewById(R.id.ebtn_right);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(B_Stipend.this);
                final String middle = preferences.getString("middle", "");
                final String high = preferences.getString("high", "");
                final String hsec = preferences.getString("highsecondary", "");

                final String boys = preferences.getString("boys", "");
                final String girls = preferences.getString("girls", "");
                SQLiteOpenHelper database = new DatabaseHandler(B_Stipend.this);
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
                if (infrastructureStr.equals("True"))
                {
                    startActivity(new Intent(B_Stipend.this, B_InfraStructure.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (guideStr.equals("True"))
                {
                    startActivity(new Intent(B_Stipend.this, B_TeacherGuides.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (cleanlinessStr.equals("True"))
                {
                    startActivity(new Intent(B_Stipend.this, B_Cleanliness.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (SanctionedStr.equals("True"))
                {
                    startActivity(new Intent(B_Stipend.this, M_SanctionedPostList.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (indicatorStr.equals("True"))
                {
                    startActivity(new Intent(B_Stipend.this, Y_OtherFacilities.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (enrollmentageStr.equals("True"))
                {
                    startActivity(new Intent(B_Stipend.this, Y_EnrollmentAgeBoys.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (enrollmentgrpStr.equals("True") && (middle.equals("MiddleSelected") || high.equals("HighSelected")
                        || hsec.equals("HighSecondarySelected")))
                {
                    startActivity(new Intent(B_Stipend.this, Y_EnrollmentByGroupSectionMain.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (disabledStr.equals("True"))
                {
                    startActivity(new Intent(B_Stipend.this, Y_SpecialBoysMain.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (securityStr.equals("True"))
                {
                    startActivity(new Intent(B_Stipend.this, Y_SecurityMeasures.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (ftbStr.equals("True"))
                {
                    startActivity(new Intent(B_Stipend.this, Y_ProvisionFreeBooksMain.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (furnitureStr.equals("True"))
                {
                    startActivity(new Intent(B_Stipend.this, Y_Furniture.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else
                {
                    startActivity(new Intent(B_Stipend.this, CompleteForm.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(B_Stipend.this);
                final String middle = preferences.getString("middle", "");
                final String high = preferences.getString("high", "");
                final String hsec = preferences.getString("highsecondary", "");

                final String boys = preferences.getString("boys", "");
                final String girls = preferences.getString("girls", "");
                SQLiteOpenHelper database = new DatabaseHandler(B_Stipend.this);
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
                if (ptcStr.equals("True"))
                {
                    startActivity(new Intent(B_Stipend.this, B_ParentTeacherCouncil.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (commodititesStr.equals("True"))
                {
                    startActivity(new Intent(B_Stipend.this, B_Commodities.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (itlabStr.equals("True"))
                {
                    startActivity(new Intent(B_Stipend.this, B_IT_LabExists.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (buildindconditionStr.equals("True"))
                {
                    startActivity(new Intent(B_Stipend.this, B_BuildingCondition.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (constructionStr.equals("True"))
                {
                    startActivity(new Intent(B_Stipend.this, B_Construction.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (schoolareaStr.equals("True"))
                {
                    startActivity(new Intent(B_Stipend.this, B_SchoolArea.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else {
                    startActivity(new Intent(B_Stipend.this, MoreInfo.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
            }
        });

        String[] mt = getResources().getStringArray(R.array.ppc_working);

        ArrayAdapter<String> mtAdapter = new ArrayAdapter<String>(B_Stipend.this, android.R.layout.simple_spinner_dropdown_item, mt);
        recordShown.setAdapter(mtAdapter);
        recordShown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                SLevel = recordShown.getSelectedItem().toString();

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
        editor.putString("stipend_recordshown", SLevel);
        editor.putString("stipend_year", selectyear.getText().toString());
        editor.putString("stipend_SchemeName6", SchemeName6.getText().toString());
        editor.putString("stipend_eligibleStudents6", eligibleStudents6.getText().toString());
        editor.putString("stipend_EligibleNoStipend6", EligibleNoStipend6.getText().toString());
        editor.putString("stipend_total6", class6remarks.getText().toString());
        editor.putString("stipend_SchemeName7", SchemeName7.getText().toString());
        editor.putString("stipend_eligibleStudents7", eligibleStudents7.getText().toString());
        editor.putString("stipend_EligibleNoStipend7", EligibleNoStipend7.getText().toString());
        editor.putString("stipend_total7", class7remarks.getText().toString());
        editor.putString("stipend_SchemeName8", SchemeName8.getText().toString());
        editor.putString("stipend_eligibleStudents8", eligibleStudents8.getText().toString());
        editor.putString("stipend_EligibleNoStipend8", EligibleNoStipend8.getText().toString());
        editor.putString("stipend_total8", class8remarks.getText().toString());
        editor.putString("stipend_SchemeName9", SchemeName9.getText().toString());
        editor.putString("stipend_eligibleStudents9", eligibleStudents9.getText().toString());
        editor.putString("stipend_EligibleNoStipend9", EligibleNoStipend9.getText().toString());
        editor.putString("stipend_total9", class9remarks.getText().toString());
        editor.putString("stipend_SchemeName10", SchemeName10.getText().toString());
        editor.putString("stipend_eligibleStudents10", eligibleStudents10.getText().toString());
        editor.putString("stipend_EligibleNoStipend10", EligibleNoStipend10.getText().toString());
        editor.putString("stipend_total10", total10.getText().toString());
        editor.putString("stipend_remarks", class10remarks.getText().toString());
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
        String rs = preferences.getString("stipend_recordshown", "");
        String sy = preferences.getString("stipend_year", "");
        String namee = preferences.getString("stipend_SchemeName6", "");
        String noo = preferences.getString("stipend_eligibleStudents6", "");
        String banknamee = preferences.getString("stipend_EligibleNoStipend6", "");
        String accno = preferences.getString("stipend_total6", "");
        String bcode = preferences.getString("stipend_SchemeName7", "");
        String blnc = preferences.getString("stipend_eligibleStudents7", "");
        String amnt = preferences.getString("stipend_EligibleNoStipend7", "");
        String estvalue = preferences.getString("stipend_total7", "");
        String trainedvalue = preferences.getString("stipend_SchemeName8", "");
        String lastt = preferences.getString("stipend_eligibleStudents8", "");
        String doee = preferences.getString("stipend_EligibleNoStipend8", "");
        String nameee = preferences.getString("stipend_total8", "");
        String nooo = preferences.getString("stipend_SchemeName9", "");
        String bankneamee = preferences.getString("stipend_eligibleStudents9", "");
        String accnoo = preferences.getString("stipend_EligibleNoStipend9", "");
        String bcodee = preferences.getString("stipend_total9", "");
        String blncc = preferences.getString("stipend_SchemeName10", "");
        String amnte = preferences.getString("stipend_eligibleStudents10", "");
        String estvaluee = preferences.getString("stipend_EligibleNoStipend10", "");
        String trainedvaluee = preferences.getString("stipend_total10", "");
        String trainedvalueee = preferences.getString("stipend_remarks", "");
        selectyear.setText(sy);
        SchemeName6.setText(namee);
        eligibleStudents6.setText(noo);
        EligibleNoStipend6.setText(banknamee);
        class6remarks.setText(accno);
        SchemeName7.setText(bcode);
        eligibleStudents7.setText(blnc);
        EligibleNoStipend7.setText(amnt);
        class7remarks.setText(estvalue);
        SchemeName8.setText(trainedvalue);
        eligibleStudents8.setText(lastt);
        EligibleNoStipend8.setText(doee);
        class8remarks.setText(nameee);
        SchemeName9.setText(nooo);
        eligibleStudents9.setText(bankneamee);
        EligibleNoStipend9.setText(accnoo);
        class9remarks.setText(bcodee);
        SchemeName10.setText(blncc);
        eligibleStudents10.setText(amnte);
        EligibleNoStipend10.setText(estvaluee);
        total10.setText(trainedvaluee);
        class10remarks.setText(trainedvalueee);
        if (rs.equals("Yes")) {
            recordShown.setSelection(0, true);
        } else {
            recordShown.setSelection(1, true);
        }

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(B_Stipend.this);
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
