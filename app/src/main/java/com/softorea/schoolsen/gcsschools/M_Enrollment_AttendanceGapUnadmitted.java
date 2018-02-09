package com.softorea.schoolsen.gcsschools;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.lists.Roster_List;

/**
 * Created by arsla on 05/10/2017.
 */

public class M_Enrollment_AttendanceGapUnadmitted extends Activity {
    Button back;
    EditText stdregisterred, stdpresent, girlsinboys, boysingirls;
    TextView classtitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eenrolment_dialog);
        classtitle = (TextView) findViewById(R.id.title);
        classtitle.setText("Class Unadmitted");
        girlsinboys = (EditText) findViewById(R.id.grlsinboys);
        boysingirls = (EditText) findViewById(R.id.boysingils);
        stdregisterred = (EditText) findViewById(R.id.stdregister);
        stdpresent = (EditText) findViewById(R.id.stdpresent);


        back = (Button) findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(M_Enrollment_AttendanceGapUnadmitted.this, M_Enrollment_AttendanceGap.class));
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
        editor.putString("stdregistered_unadmitted", stdregisterred.getText().toString());
        editor.putString("stdpresent_unadmitted", stdpresent.getText().toString());
        editor.putString("enrollment_girlsinboys_unadmitted", girlsinboys.getText().toString());
        editor.putString("enrollment_boysingirls_unadmitted", boysingirls.getText().toString());
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
        String enrolled = preferences.getString("stdregistered_unadmitted", "");
        String presenthead = preferences.getString("stdpresent_unadmitted", "");
        String GinBoys = preferences.getString("enrollment_girlsinboys_unadmitted", "");
        String BinGirls = preferences.getString("enrollment_boysingirls_unadmitted", "");
        stdregisterred.setText(enrolled);
        stdpresent.setText(presenthead);
        girlsinboys.setText(GinBoys);
        boysingirls.setText(BinGirls);

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(M_Enrollment_AttendanceGapUnadmitted.this);
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
