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
import android.widget.Button;
import android.widget.EditText;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.databasehelper.DatabaseHandler;
import com.softorea.schoolsen.lists.Roster_List;

/**
 * Created by Softorea on 10/2/2017.
 */

public class Y_EnrollmentElevenTwelve extends Activity {
    Button back, next;
    EditText enrol_11_Medical, sec_11_Medical, enrol_11_Eng, sec_11_Eng, enrol_11_Arts, sec_11_Arts,
            enrol_11_Inter, sec_11_Inter, enrol_12_Medical, sec_12_Medical, enrol_12_Eng, sec_12_Eng, enrol_12_Arts, sec_12_Arts,
            enrol_12_Inter, sec_12_Inter;
    String schoolareaStr,constructionStr,buildindconditionStr,itlabStr,
            commodititesStr,ptcStr,infrastructureStr,guideStr,cleanlinessStr,stipendStr,SanctionedStr,indicatorStr,
            enrollmentageStr,enrollmentgrpStr,disabledStr,securityStr,ftbStr,furnitureStr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.y_enrollment_inclass_eleventwelve);
        enrol_11_Medical = (EditText) findViewById(R.id.class_eleven_medical_totalenrol);
        sec_11_Medical = (EditText) findViewById(R.id.class_eleven_medical_sections);
        enrol_11_Eng = (EditText) findViewById(R.id.class_eleven_eng_totalenrol);
        sec_11_Eng = (EditText) findViewById(R.id.class_eleven_eng_sections);
        enrol_11_Arts = (EditText) findViewById(R.id.class_eleven_arts_totalenrol);
        sec_11_Arts = (EditText) findViewById(R.id.class_eleven_arts_sections);
        enrol_11_Inter = (EditText) findViewById(R.id.class_eleven_interscience_totalenrol);
        sec_11_Inter = (EditText) findViewById(R.id.class_eleven_interscience_sections);
        enrol_12_Medical = (EditText) findViewById(R.id.class_twelve_medical_totalenrol);
        sec_12_Medical = (EditText) findViewById(R.id.class_twelve_medical_sections);
        enrol_12_Eng = (EditText) findViewById(R.id.class_twelve_eng_totalenrol);
        sec_12_Eng = (EditText) findViewById(R.id.class_twelve_eng_sections);
        enrol_12_Arts = (EditText) findViewById(R.id.class_twelve_arts_totalenrol);
        sec_12_Arts = (EditText) findViewById(R.id.class_twelve_arts_sections);
        enrol_12_Inter = (EditText) findViewById(R.id.class_twelve_interscience_totalenrol);
        sec_12_Inter = (EditText) findViewById(R.id.class_twelve_interscience_sections);

        back = (Button) findViewById(R.id.ebtn_left);
        next = (Button) findViewById(R.id.ebtn_right);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Y_EnrollmentElevenTwelve.this);
                final String middle = preferences.getString("middle", "");
                final String high = preferences.getString("high", "");
                final String hsec = preferences.getString("highsecondary", "");

                final String boys = preferences.getString("boys", "");
                final String girls = preferences.getString("girls", "");
                SQLiteOpenHelper database = new DatabaseHandler(Y_EnrollmentElevenTwelve.this);
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
                    startActivity(new Intent(Y_EnrollmentElevenTwelve.this, Y_SpecialBoysMain.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (securityStr.equals("True"))
                {
                    startActivity(new Intent(Y_EnrollmentElevenTwelve.this, Y_SecurityMeasures.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (ftbStr.equals("True"))
                {
                    startActivity(new Intent(Y_EnrollmentElevenTwelve.this, Y_ProvisionFreeBooksMain.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (furnitureStr.equals("True"))
                {
                    startActivity(new Intent(Y_EnrollmentElevenTwelve.this, Y_Furniture.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else
                {
                    startActivity(new Intent(Y_EnrollmentElevenTwelve.this, CompleteForm.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Y_EnrollmentElevenTwelve.this, Y_EnrollmentByGroupSectionMain.class));
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
        editor.putString("enrol_11_Medical", enrol_11_Medical.getText().toString());
        editor.putString("sec_11_Medical", sec_11_Medical.getText().toString());
        editor.putString("enrol_11_Eng", enrol_11_Eng.getText().toString());
        editor.putString("sec_11_Eng", sec_11_Eng.getText().toString());
        editor.putString("enrol_11_Arts", enrol_11_Arts.getText().toString());
        editor.putString("sec_11_Arts", sec_11_Arts.getText().toString());
        editor.putString("enrol_11_Inter", enrol_11_Inter.getText().toString());
        editor.putString("sec_11_Inter", sec_11_Inter.getText().toString());
        editor.putString("enrol_12_Medical", enrol_12_Medical.getText().toString());
        editor.putString("sec_12_Medical", sec_12_Medical.getText().toString());
        editor.putString("enrol_12_Eng", enrol_12_Eng.getText().toString());
        editor.putString("sec_12_Eng", sec_12_Eng.getText().toString());
        editor.putString("enrol_12_Arts", enrol_12_Arts.getText().toString());
        editor.putString("sec_12_Arts", sec_12_Arts.getText().toString());
        editor.putString("enrol_12_Inter", enrol_12_Inter.getText().toString());
        editor.putString("sec_12_Inter", sec_12_Inter.getText().toString());
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
        String a = preferences.getString("enrol_11_Medical", "");
        String b = preferences.getString("sec_11_Medical", "");
        String c = preferences.getString("enrol_11_Eng", "");
        String d = preferences.getString("sec_11_Eng", "");
        String e = preferences.getString("enrol_11_Arts", "");
        String f = preferences.getString("sec_11_Arts", "");
        String g = preferences.getString("enrol_11_Inter", "");
        String h = preferences.getString("sec_11_Inter", "");
        String i = preferences.getString("enrol_12_Medical", "");
        String j = preferences.getString("sec_12_Medical", "");
        String k = preferences.getString("enrol_12_Eng", "");
        String l = preferences.getString("sec_12_Eng", "");
        String m = preferences.getString("enrol_12_Arts", "");
        String n = preferences.getString("sec_12_Arts", "");
        String o = preferences.getString("enrol_12_Inter", "");
        String p = preferences.getString("sec_12_Inter", "");

        enrol_11_Medical.setText(a);
        sec_11_Medical.setText(b);
        enrol_11_Eng.setText(c);
        sec_11_Eng.setText(d);
        enrol_11_Arts.setText(e);
        sec_11_Arts.setText(f);
        enrol_11_Inter.setText(g);
        sec_11_Inter.setText(h);
        enrol_12_Medical.setText(i);
        sec_12_Medical.setText(j);
        enrol_12_Eng.setText(k);
        sec_12_Eng.setText(l);
        enrol_12_Arts.setText(m);
        sec_12_Arts.setText(n);
        enrol_12_Inter.setText(o);
        sec_12_Inter.setText(p);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(Y_EnrollmentElevenTwelve.this);
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
