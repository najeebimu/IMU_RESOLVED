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

public class B_ParentTeacherCouncil extends Activity {
    TextView ptcLastElection, dateOfEstablishMent;
    Button back, next;
    Spinner isPTCestablished, PtcMemberstrained,bankStatementShown;
    EditText name, no, bankName, bankAccountNo, bankBranchcode, balance, amountReceived;
    EditText amountReceivedOthers,expenditures,presentbalance,lastmonthmeeting;
    String ptcEst, MemTrained,bankStatementStr;
    LinearLayout ptcestlayout;
    String schoolareaStr,constructionStr,buildindconditionStr,itlabStr,
            commodititesStr,ptcStr,infrastructureStr,guideStr,cleanlinessStr,stipendStr,SanctionedStr,indicatorStr,
            enrollmentageStr,enrollmentgrpStr,disabledStr,securityStr,ftbStr,furnitureStr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b_parentteachercouncil);
        ptcLastElection = (TextView) findViewById(R.id.ptclastelection);
        dateOfEstablishMent = (TextView) findViewById(R.id.dateofestbalishment);
        ptcestlayout = (LinearLayout) findViewById(R.id.abcde);

        isPTCestablished = (Spinner) findViewById(R.id.ptcestablishedspinner);
        PtcMemberstrained = (Spinner) findViewById(R.id.ptcmemberstrained);
        bankStatementShown = (Spinner) findViewById(R.id.bankstatement);
        name = (EditText) findViewById(R.id.ptc_chairpersonname);
        no = (EditText) findViewById(R.id.contactno);
        bankName = (EditText) findViewById(R.id.bankname);
        bankAccountNo = (EditText) findViewById(R.id.bankaccountno);
        bankBranchcode = (EditText) findViewById(R.id.branchcode);
        balance = (EditText) findViewById(R.id.balance_upto_october);
        amountReceived = (EditText) findViewById(R.id.amountreceived);

        amountReceivedOthers = (EditText) findViewById(R.id.amountreceivedothers);
        expenditures = (EditText) findViewById(R.id.expenditure);
        presentbalance = (EditText) findViewById(R.id.presentbalance);
        lastmonthmeeting = (EditText) findViewById(R.id.lastmonthmeeting);

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

                ptcLastElection.setText(sdf.format(myCalendar.getTime()));
            }

        };

        ptcLastElection.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DatePickerDialog dialog = new DatePickerDialog(B_ParentTeacherCouncil.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
                dialog.getDatePicker().setMaxDate(new Date().getTime());
                dialog.show();
            }
        });


        final Calendar ccc = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener dddd = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                ccc.set(Calendar.YEAR, year);
                ccc.set(Calendar.MONTH, monthOfYear);
                ccc.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                dateOfEstablishMent.setText(sdf.format(ccc.getTime()));
            }

        };

        dateOfEstablishMent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(B_ParentTeacherCouncil.this, dddd, ccc
                        .get(Calendar.YEAR), ccc.get(Calendar.MONTH),
                        ccc.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        String[] marital = getResources().getStringArray(R.array.checkconditions);

        ArrayAdapter<String> sAdapter = new ArrayAdapter<String>(B_ParentTeacherCouncil.this, android.R.layout.simple_spinner_dropdown_item, marital);
        isPTCestablished.setAdapter(sAdapter);
        isPTCestablished.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                ptcEst = isPTCestablished.getSelectedItem().toString();
                if (ptcEst.equals("No") || ptcEst.equals("None")) {
                    ptcestlayout.setVisibility(View.GONE);
                    dateOfEstablishMent.setText("");

                } else {
                    ptcestlayout.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        String[] mt = getResources().getStringArray(R.array.checkconditions);

        ArrayAdapter<String> mtAdapter = new ArrayAdapter<String>(B_ParentTeacherCouncil.this, android.R.layout.simple_spinner_dropdown_item, mt);
        PtcMemberstrained.setAdapter(mtAdapter);
        PtcMemberstrained.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                MemTrained = PtcMemberstrained.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });


        String[] bankstatemnt = getResources().getStringArray(R.array.checkconditions);

        ArrayAdapter<String> bankAdapter = new ArrayAdapter<String>(B_ParentTeacherCouncil.this, android.R.layout.simple_spinner_dropdown_item, bankstatemnt);
        bankStatementShown.setAdapter(bankAdapter);
        bankStatementShown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                bankStatementStr = bankStatementShown.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });


        back = (Button) findViewById(R.id.ebtn_left);
        next = (Button) findViewById(R.id.ebtn_right);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String middle = preferences.getString("middle", "");
        final String high = preferences.getString("high", "");
        final String hsec = preferences.getString("highsecondary", "");

        final String boys = preferences.getString("boys", "");
        final String girls = preferences.getString("girls", "");
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(B_ParentTeacherCouncil.this);
                final String middle = preferences.getString("middle", "");
                final String high = preferences.getString("high", "");
                final String hsec = preferences.getString("highsecondary", "");

                final String boys = preferences.getString("boys", "");
                final String girls = preferences.getString("girls", "");
                SQLiteOpenHelper database = new DatabaseHandler(B_ParentTeacherCouncil.this);
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
                        || hsec.equals("HighSecondarySelected")))
                {
                    startActivity(new Intent(B_ParentTeacherCouncil.this, B_Stipend.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (infrastructureStr.equals("True"))
                {
                    startActivity(new Intent(B_ParentTeacherCouncil.this, B_InfraStructure.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (guideStr.equals("True"))
                {
                    startActivity(new Intent(B_ParentTeacherCouncil.this, B_TeacherGuides.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (cleanlinessStr.equals("True"))
                {
                    startActivity(new Intent(B_ParentTeacherCouncil.this, B_Cleanliness.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (SanctionedStr.equals("True"))
                {
                    startActivity(new Intent(B_ParentTeacherCouncil.this, M_SanctionedPostList.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (indicatorStr.equals("True"))
                {
                    startActivity(new Intent(B_ParentTeacherCouncil.this, Y_OtherFacilities.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (enrollmentageStr.equals("True"))
                {
                    startActivity(new Intent(B_ParentTeacherCouncil.this, Y_EnrollmentAgeBoys.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (enrollmentgrpStr.equals("True") && (middle.equals("MiddleSelected") || high.equals("HighSelected")
                        || hsec.equals("HighSecondarySelected")))
                {
                    startActivity(new Intent(B_ParentTeacherCouncil.this, Y_EnrollmentByGroupSectionMain.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (disabledStr.equals("True"))
                {
                    startActivity(new Intent(B_ParentTeacherCouncil.this, Y_SpecialBoysMain.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (securityStr.equals("True"))
                {
                    startActivity(new Intent(B_ParentTeacherCouncil.this, Y_SecurityMeasures.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (ftbStr.equals("True"))
                {
                    startActivity(new Intent(B_ParentTeacherCouncil.this, Y_ProvisionFreeBooksMain.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (furnitureStr.equals("True"))
                {
                    startActivity(new Intent(B_ParentTeacherCouncil.this, Y_Furniture.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else
                {
                    startActivity(new Intent(B_ParentTeacherCouncil.this, CompleteForm.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(B_ParentTeacherCouncil.this);
                final String middle = preferences.getString("middle", "");
                final String high = preferences.getString("high", "");
                final String hsec = preferences.getString("highsecondary", "");

                final String boys = preferences.getString("boys", "");
                final String girls = preferences.getString("girls", "");
                SQLiteOpenHelper database = new DatabaseHandler(B_ParentTeacherCouncil.this);
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
                if (commodititesStr.equals("True"))
                {
                    startActivity(new Intent(B_ParentTeacherCouncil.this, B_Commodities.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (itlabStr.equals("True"))
                {
                    startActivity(new Intent(B_ParentTeacherCouncil.this, B_IT_LabExists.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (buildindconditionStr.equals("True"))
                {
                    startActivity(new Intent(B_ParentTeacherCouncil.this, B_BuildingCondition.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (constructionStr.equals("True"))
                {
                    startActivity(new Intent(B_ParentTeacherCouncil.this, B_Construction.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (schoolareaStr.equals("True"))
                {
                    startActivity(new Intent(B_ParentTeacherCouncil.this, B_SchoolArea.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else {
                    startActivity(new Intent(B_ParentTeacherCouncil.this, MoreInfo.class));
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
        editor.putString("PTC_lastelection", ptcLastElection.getText().toString());
        editor.putString("PTC_DOE", dateOfEstablishMent.getText().toString());
        editor.putString("PTC_name", name.getText().toString());
        editor.putString("PTC_no", no.getText().toString());
        editor.putString("PTC_bakname", bankName.getText().toString());
        editor.putString("PTC_acoountno", bankAccountNo.getText().toString());
        editor.putString("PTC_branchcode", bankBranchcode.getText().toString());
        editor.putString("PTC_balance", balance.getText().toString());
        editor.putString("PTC_amount", amountReceived.getText().toString());

        editor.putString("PTC_amountothers", amountReceivedOthers.getText().toString());
        editor.putString("PTC_expenditures", expenditures.getText().toString());
        editor.putString("PTC_presentbalance", presentbalance.getText().toString());
        editor.putString("PTC_lastmonthmeeting", lastmonthmeeting.getText().toString());

        editor.putString("PTC_EstablishSpinner", ptcEst);
        editor.putString("PTC_MembersTrainedSpinner", MemTrained);
        editor.putString("PTC_BankStatementStr", bankStatementStr);
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
        String last = preferences.getString("PTC_lastelection", "");
        String doe = preferences.getString("PTC_DOE", "");
        String namee = preferences.getString("PTC_name", "");
        String noo = preferences.getString("PTC_no", "");
        String banknamee = preferences.getString("PTC_bakname", "");
        String accno = preferences.getString("PTC_acoountno", "");
        String bcode = preferences.getString("PTC_branchcode", "");
        String blnc = preferences.getString("PTC_balance", "");
        String amnt = preferences.getString("PTC_amount", "");

        String amntothers = preferences.getString("PTC_amountothers", "");
        String expendituress = preferences.getString("PTC_expenditures", "");
        String presentbalancee = preferences.getString("PTC_presentbalance", "");
        String lastmnthmetng = preferences.getString("PTC_lastmonthmeeting", "");

        String estvalue = preferences.getString("PTC_EstablishSpinner", "");
        String trainedvalue = preferences.getString("PTC_MembersTrainedSpinner", "");
        String bankstatementshown = preferences.getString("PTC_BankStatementStr", "");
        ptcLastElection.setText(last);
        dateOfEstablishMent.setText(doe);
        name.setText(namee);
        no.setText(noo);
        bankName.setText(banknamee);
        bankAccountNo.setText(accno);
        bankBranchcode.setText(bcode);
        balance.setText(blnc);
        amountReceived.setText(amnt);

        amountReceivedOthers.setText(amntothers);
        expenditures.setText(expendituress);
        presentbalance.setText(presentbalancee);
        lastmonthmeeting.setText(lastmnthmetng);


        if (estvalue.equals("Yes")) {
            isPTCestablished.setSelection(1, true);
        }
        else if (estvalue.equals("No")) {
            isPTCestablished.setSelection(2, true);
        }
        else {
            isPTCestablished.setSelection(0, true);
        }
        if (trainedvalue.equals("Yes")) {
            PtcMemberstrained.setSelection(1, true);
        }
        else if (trainedvalue.equals("No")) {
            PtcMemberstrained.setSelection(2, true);
        }
        else {
            PtcMemberstrained.setSelection(0, true);
        }

        if (bankstatementshown.equals("Yes")) {
            bankStatementShown.setSelection(1, true);
        }
        else if (bankstatementshown.equals("No")) {
            bankStatementShown.setSelection(2, true);
        }
        else {
            bankStatementShown.setSelection(0, true);
        }

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(B_ParentTeacherCouncil.this);
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
