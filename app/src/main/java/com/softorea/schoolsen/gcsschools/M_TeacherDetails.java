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

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.lists.Roster_List;

/**
 * Created by Arslan on 10/2/2017.
 */

public class M_TeacherDetails extends Activity {
    Button back, next;
    EditText school_register, physical_present;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.efragment_teachercount);
        school_register = (EditText) findViewById(R.id.school_register_edit);
        physical_present = (EditText) findViewById(R.id.physical_present_edit);
        back = (Button) findViewById(R.id.btn_left);
        next = (Button) findViewById(R.id.btn_right);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent view_order_intent = new Intent(M_TeacherDetails.this, M_SchoolVisitList.class);
                //view_order_intent.putExtra("data", id );
                startActivity(view_order_intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
                //startActivity(new Intent(M_HeadInfo.this, M_TeacherPresenceList.class));
                //finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(M_TeacherDetails.this, M_HeadInfo.class));
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
        editor.putString("school_register", school_register.getText().toString());
        editor.putString("physical_present", physical_present.getText().toString());
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
        String illegalvalue = preferences.getString("school_register", "");
        String typevalue = preferences.getString("physical_present", "");
        school_register.setText(illegalvalue);
        physical_present.setText(typevalue);

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(M_TeacherDetails.this);
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
