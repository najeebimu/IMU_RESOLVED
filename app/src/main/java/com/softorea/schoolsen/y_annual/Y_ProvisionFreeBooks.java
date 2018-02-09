package com.softorea.schoolsen.y_annual;

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
 * Created by arsla on 09/10/2017.
 */

public class Y_ProvisionFreeBooks extends Activity {

    Button back;
    EditText demanded, received, withoutftb, surplus, booksReturn;
    String classname, subjectname;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.y_provisionfreebooks);
        preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
        editor = preferences.edit();
        demanded = (EditText) findViewById(R.id.booksdemanded);
        received = (EditText) findViewById(R.id.booksreceived);
        withoutftb = (EditText) findViewById(R.id.withoutftb);
        surplus = (EditText) findViewById(R.id.surplus);
        booksReturn = (EditText) findViewById(R.id.booksreturn);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            classname = extras.getString("classnameo");
            subjectname = extras.getString("subjectname");
            // and get whatever type user account id is
        }

        //Toast.makeText(this, classname + " " + subjectname, Toast.LENGTH_SHORT).show();
        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(Y_ProvisionFreeBooks.this);
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
