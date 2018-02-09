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
import android.widget.Button;
import android.widget.EditText;

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


public class B_Commodities extends Activity {
    Button back, next;
    EditText anyothername, anyothername2, anyotheruseable2, anyotherunuseable2, anyothernumberOfnew;
    EditText twoUseable, twoUnUseable, twoNewRequired, threeUseable, threeUnUseable, threeNewRequired,
            benchesUseable, benchesUnUseable, benchesNewRequired, chairsUseable, chairsUnUseable, chairsNewRequired,
            tabletchairsUseable, tabletchairsUnUseable, tabletchairsNewRequired, tatsUseable,
            tatsUnUseable, tatsNewRequired, fansUseable, fansUnUseable, fansNewRequired, otherUseable, otherUnUseable, otherNewRequired;
    String schoolareaStr,constructionStr,buildindconditionStr,itlabStr,
            commodititesStr,ptcStr,infrastructureStr,guideStr,cleanlinessStr,stipendStr,SanctionedStr,indicatorStr,
            enrollmentageStr,enrollmentgrpStr,disabledStr,securityStr,ftbStr,furnitureStr;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b_student_commodities);
        anyothername2 = (EditText) findViewById(R.id.anyother2name);
        anyotheruseable2 = (EditText) findViewById(R.id.useable_any2other);
        anyotherunuseable2 = (EditText) findViewById(R.id.unuseable_any2other);
        anyothernumberOfnew = (EditText) findViewById(R.id.newrequired_any2other);

        twoUseable = (EditText) findViewById(R.id.useable_twoseats);
        twoUnUseable = (EditText) findViewById(R.id.unuseable_twoseats);
        twoNewRequired = (EditText) findViewById(R.id.newrequired);
        threeUseable = (EditText) findViewById(R.id.useable_threeseats);
        threeUnUseable = (EditText) findViewById(R.id.unuseable_threeseats);
        threeNewRequired = (EditText) findViewById(R.id.newrequiredthreeseats);
        benchesUseable = (EditText) findViewById(R.id.useable_benches);
        benchesUnUseable = (EditText) findViewById(R.id.unuseable_benches);
        benchesNewRequired = (EditText) findViewById(R.id.newrequiredbenches);
        chairsUseable = (EditText) findViewById(R.id.useable_chairs);
        chairsUnUseable = (EditText) findViewById(R.id.unuseable_chairs);
        chairsNewRequired = (EditText) findViewById(R.id.newrequiredchairs);
        tabletchairsUseable = (EditText) findViewById(R.id.useable_tablet);
        tabletchairsUnUseable = (EditText) findViewById(R.id.unuseable_tablet);
        tabletchairsNewRequired = (EditText) findViewById(R.id.newrequiredtablet);
        tatsUseable = (EditText) findViewById(R.id.useable_jute);
        tatsUnUseable = (EditText) findViewById(R.id.unuseable_jute);
        tatsNewRequired = (EditText) findViewById(R.id.newrequiredjute);
        fansUseable = (EditText) findViewById(R.id.useable_fans);
        fansUnUseable = (EditText) findViewById(R.id.unuseable_fans);
        fansNewRequired = (EditText) findViewById(R.id.newrequiredfans);
        anyothername = (EditText) findViewById(R.id.anyothername);
        otherUseable = (EditText) findViewById(R.id.useable_anyother);
        otherUnUseable = (EditText) findViewById(R.id.unuseable_anyother);
        otherNewRequired = (EditText) findViewById(R.id.newrequired_anyother);

        back = (Button) findViewById(R.id.ebtn_left);
        next = (Button) findViewById(R.id.ebtn_right);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(B_Commodities.this);
                final String middle = preferences.getString("middle", "");
                final String high = preferences.getString("high", "");
                final String hsec = preferences.getString("highsecondary", "");

                final String boys = preferences.getString("boys", "");
                final String girls = preferences.getString("girls", "");
                SQLiteOpenHelper database = new DatabaseHandler(B_Commodities.this);
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
                    startActivity(new Intent(B_Commodities.this, B_ParentTeacherCouncil.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (stipendStr.equals("True") && girls.equals("GirlsSelected") && (middle.equals("MiddleSelected") || high.equals("HighSelected")
                        || hsec.equals("HighSecondarySelected")))
                {
                    startActivity(new Intent(B_Commodities.this, B_Stipend.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (infrastructureStr.equals("True"))
                {
                    startActivity(new Intent(B_Commodities.this, B_InfraStructure.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (guideStr.equals("True"))
                {
                    startActivity(new Intent(B_Commodities.this, B_TeacherGuides.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (cleanlinessStr.equals("True"))
                {
                    startActivity(new Intent(B_Commodities.this, B_Cleanliness.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (SanctionedStr.equals("True"))
                {
                    startActivity(new Intent(B_Commodities.this, M_SanctionedPostList.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (indicatorStr.equals("True"))
                {
                    startActivity(new Intent(B_Commodities.this, Y_OtherFacilities.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (enrollmentageStr.equals("True"))
                {
                    startActivity(new Intent(B_Commodities.this, Y_EnrollmentAgeBoys.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (enrollmentgrpStr.equals("True") && (middle.equals("MiddleSelected") || high.equals("HighSelected")
                        || hsec.equals("HighSecondarySelected")))
                {
                    startActivity(new Intent(B_Commodities.this, Y_EnrollmentByGroupSectionMain.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (disabledStr.equals("True"))
                {
                    startActivity(new Intent(B_Commodities.this, Y_SpecialBoysMain.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (securityStr.equals("True"))
                {
                    startActivity(new Intent(B_Commodities.this, Y_SecurityMeasures.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (ftbStr.equals("True"))
                {
                    startActivity(new Intent(B_Commodities.this, Y_ProvisionFreeBooksMain.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (furnitureStr.equals("True"))
                {
                    startActivity(new Intent(B_Commodities.this, Y_Furniture.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else
                {
                    startActivity(new Intent(B_Commodities.this, CompleteForm.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(B_Commodities.this);
                final String middle = preferences.getString("middle", "");
                final String high = preferences.getString("high", "");
                final String hsec = preferences.getString("highsecondary", "");

                final String boys = preferences.getString("boys", "");
                final String girls = preferences.getString("girls", "");
                SQLiteOpenHelper database = new DatabaseHandler(B_Commodities.this);
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
                    startActivity(new Intent(B_Commodities.this, B_IT_LabExists.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (buildindconditionStr.equals("True"))
                {
                    startActivity(new Intent(B_Commodities.this, B_BuildingCondition.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (constructionStr.equals("True"))
                {
                    startActivity(new Intent(B_Commodities.this, B_Construction.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (schoolareaStr.equals("True"))
                {
                    startActivity(new Intent(B_Commodities.this, B_SchoolArea.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else {
                    startActivity(new Intent(B_Commodities.this, MoreInfo.class));
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
        editor.putString("twoUseable", twoUseable.getText().toString());
        editor.putString("twoUnUseable", twoUnUseable.getText().toString());
        editor.putString("twoNewRequired", twoNewRequired.getText().toString());
        editor.putString("threeUseable", threeUseable.getText().toString());
        editor.putString("threeUnUseable", threeUnUseable.getText().toString());
        editor.putString("threeNewRequired", threeNewRequired.getText().toString());
        editor.putString("benchesUseable", benchesUseable.getText().toString());
        editor.putString("benchesUnUseable", benchesUnUseable.getText().toString());
        editor.putString("benchesNewRequired", benchesNewRequired.getText().toString());
        editor.putString("chairsUseable", chairsUseable.getText().toString());
        editor.putString("chairsUnUseable", chairsUnUseable.getText().toString());
        editor.putString("chairsNewRequired", chairsNewRequired.getText().toString());
        editor.putString("tabletchairsUseable", tabletchairsUseable.getText().toString());
        editor.putString("tabletchairsUnUseable", tabletchairsUnUseable.getText().toString());
        editor.putString("tabletchairsNewRequired", tabletchairsNewRequired.getText().toString());
        editor.putString("tatsUseable", tatsUseable.getText().toString());
        editor.putString("tatsUnUseable", tatsUnUseable.getText().toString());
        editor.putString("tatsNewRequired", tatsNewRequired.getText().toString());
        editor.putString("fansUseable", fansUseable.getText().toString());
        editor.putString("fansUnUseable", fansUnUseable.getText().toString());
        editor.putString("fansNewRequired", fansNewRequired.getText().toString());
        editor.putString("anyothername", anyothername.getText().toString());
        editor.putString("anyothername2", anyothername2.getText().toString());
        editor.putString("otherUseable2", anyotheruseable2.getText().toString());
        editor.putString("otherUnUseable2", anyotherunuseable2.getText().toString());
        editor.putString("otherNewRequired2", anyothernumberOfnew.getText().toString());
        editor.putString("otherUseable", otherUseable.getText().toString());
        editor.putString("otherUnUseable", otherUnUseable.getText().toString());
        editor.putString("otherNewRequired", otherNewRequired.getText().toString());
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
        String hq = preferences.getString("anyothername2", "");
        String hw = preferences.getString("otherUseable2", "");
        String he = preferences.getString("otherUnUseable2", "");
        String hr = preferences.getString("otherNewRequired2", "");

        String twouse = preferences.getString("twoUseable", "");
        String twounuse = preferences.getString("twoUnUseable", "");
        String twonew = preferences.getString("twoNewRequired", "");
        String threeuse = preferences.getString("threeUseable", "");
        String threeunuse = preferences.getString("threeUnUseable", "");
        String threenew = preferences.getString("threeNewRequired", "");
        String benchuse = preferences.getString("benchesUseable", "");
        String benchunuse = preferences.getString("benchesUnUseable", "");
        String benchnew = preferences.getString("benchesNewRequired", "");
        String chairuse = preferences.getString("chairsUseable", "");
        String chairunuse = preferences.getString("chairsUnUseable", "");
        String chairnew = preferences.getString("chairsNewRequired", "");
        String tabletuse = preferences.getString("tabletchairsUseable", "");
        String tabletunuse = preferences.getString("tabletchairsUnUseable", "");
        String tabletnew = preferences.getString("tabletchairsNewRequired", "");
        String tatsuse = preferences.getString("tatsUseable", "");
        String tatsunuse = preferences.getString("tatsUnUseable", "");
        String tatsnew = preferences.getString("tatsNewRequired", "");
        String fansuse = preferences.getString("fansUseable", "");
        String fansunuse = preferences.getString("fansUnUseable", "");
        String fansnew = preferences.getString("fansNewRequired", "");
        String ayother = preferences.getString("anyothername", "");
        String otheruse = preferences.getString("otherUseable", "");
        String otherunuse = preferences.getString("otherUnUseable", "");
        String othernew = preferences.getString("otherNewRequired", "");
        twoUseable.setText(twouse);
        twoUnUseable.setText(twounuse);
        twoNewRequired.setText(twonew);
        threeUseable.setText(threeuse);
        threeUnUseable.setText(threeunuse);
        threeNewRequired.setText(threenew);
        benchesUseable.setText(benchuse);
        benchesUnUseable.setText(benchunuse);
        benchesNewRequired.setText(benchnew);
        chairsUseable.setText(chairuse);
        chairsUnUseable.setText(chairunuse);
        chairsNewRequired.setText(chairnew);
        tabletchairsUseable.setText(tabletuse);
        tabletchairsUnUseable.setText(tabletunuse);
        tabletchairsNewRequired.setText(tabletnew);
        tatsUseable.setText(tatsuse);
        tatsUnUseable.setText(tatsunuse);
        tatsNewRequired.setText(tatsnew);
        fansUseable.setText(fansuse);
        fansUnUseable.setText(fansunuse);
        fansNewRequired.setText(fansnew);
        anyothername.setText(ayother);
        otherUseable.setText(otheruse);
        otherUnUseable.setText(otherunuse);
        otherNewRequired.setText(othernew);
        anyothername2.setText(hq);
        anyotheruseable2.setText(hw);
        anyotherunuseable2.setText(he);
        anyothernumberOfnew.setText(hr);

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(B_Commodities.this);
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
