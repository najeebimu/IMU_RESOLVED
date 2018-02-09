package com.softorea.schoolsen.y_annual;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.databasehelper.DatabaseHandler;
import com.softorea.schoolsen.lists.Globals;
import com.softorea.schoolsen.lists.Roster_List;
import com.softorea.schoolsen.m_monthly.M_NonTeacherPresenceList;
import com.softorea.schoolsen.m_monthly.M_School_Status;
import com.softorea.schoolsen.m_monthly.NonTeacherWebserviceMainList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by arsla on 18/10/2017.
 */

public class MonitorSignature extends Activity {

    Button newButton, saveButton;
    FileOutputStream outputStream;
    String time;
    String monitorLogin;
    int position;
    Button btnLeft;
    Button btnRight;
    ImageButton btnClear;
    String emis, username, timee;
    SimpleDateFormat sdf;
    String currentDate;
    String formId;
    private DrawingView drawView;
    private float smallBrush, mediumBrush, largeBrush;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monitor_signature);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        emis = preferences.getString("emiscode", "");
        SQLiteOpenHelper database = new DatabaseHandler(this);
        SQLiteDatabase dbd = database.getReadableDatabase();
        Cursor cursorform = dbd.rawQuery("SELECT * FROM t_roster WHERE emis= " + emis, null);
        if (cursorform.moveToFirst()) {
            do {
                formId = cursorform.getString(cursorform.getColumnIndex("RosterformId"));
            } while (cursorform.moveToNext());
        }
        cursorform.close();
        dbd.close();
        SharedPreferences prefs = getSharedPreferences(Globals.MyPREFERENCES, Context.MODE_PRIVATE);
        username = prefs.getString("username", "");
        sdf = new SimpleDateFormat("ddMMyyyy");
        currentDate = sdf.format(new Date());
        smallBrush = getResources().getInteger(R.integer.small_size);
        timee = new SimpleDateFormat("HHmmss",
                Locale.getDefault()).format(new Date());
        mediumBrush = getResources().getInteger(R.integer.medium_size);
        largeBrush = getResources().getInteger(R.integer.large_size);
        btnClear = (ImageButton) findViewById(R.id.erase);
        newButton = (Button) findViewById(R.id.btn_clear);
        drawView = (DrawingView) findViewById(R.id.drawing);
        saveButton = (Button) findViewById(R.id.btn_save);

        // set initial size
        drawView.setBrushSize(smallBrush);

        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder saveDialog = new AlertDialog.Builder(
                        MonitorSignature.this);
                saveDialog.setTitle("Save signature");
                saveDialog.setMessage("Save Monitor signature?");
                saveDialog.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                // save drawing
                                drawView.setDrawingCacheEnabled(true);

                                Bitmap bitmapImage = drawView.getDrawingCache();

                                File file = new File(Environment
                                        .getExternalStorageDirectory().getAbsolutePath() + "/IMU/sign/");
                                if (!file.exists()) {
                                    if (!file.mkdirs()) {
                                        Log.e("TravellerLog :: ",
                                                "Problem creating Image folder");

                                    }
                                }
                                File imagename = new File(file, emis + "_"
                                        + username + "_" + currentDate + "_" + timee + "_" + formId + "_monitorsign.png");
                                try {

                                    outputStream = new FileOutputStream(
                                            imagename);
                                    bitmapImage.compress(
                                            Bitmap.CompressFormat.PNG, 100,
                                            outputStream);
                                    /* 100 to keep full quality of the image */

                                    outputStream.flush();
                                    outputStream.close();

                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                if (bitmapImage != null) {
                                    Toast savedToast = Toast.makeText(
                                            MonitorSignature.this,
                                            "Drawing saved to Gallery!",
                                            Toast.LENGTH_SHORT);
                                    savedToast.show();
                                    startActivity(new Intent(MonitorSignature.this, FinalRemarks.class));
                                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                                } else {
                                    Toast unsavedToast = Toast.makeText(
                                            MonitorSignature.this,
                                            "Oops! Image could not be saved.",
                                            Toast.LENGTH_SHORT);
                                    unsavedToast.show();
                                }
                                drawView.destroyDrawingCache();
                            }
                        });
                saveDialog.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.cancel();
                            }
                        });
                saveDialog.show();

            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                drawView.startNew();


            }
        });
        newButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Toast.makeText(MonitorSignature.this, "Head sign saved, cannot move back!", Toast.LENGTH_SHORT).show();
                SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
                String value = preferences.getString("case1", "");
                String valuee = preferences.getString("case2", "");
                String valueee = preferences.getString("case3", "");
                if (value.equals("case1found")) {
                    startActivity(new Intent(MonitorSignature.this, M_School_Status.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                } else if (valuee.equals("case2found")) {
                    startActivity(new Intent(MonitorSignature.this, M_NonTeacherPresenceList.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                } else if (valueee.equals("case3found")) {
                    startActivity(new Intent(MonitorSignature.this, NonTeacherWebserviceMainList.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                } else {
                    Toast.makeText(MonitorSignature.this, "Head sign saved, cannot move back!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(MonitorSignature.this);
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
