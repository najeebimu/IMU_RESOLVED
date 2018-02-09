package com.softorea.schoolsen.m_monthly;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.databasehelper.DatabaseHandler;
import com.softorea.schoolsen.lists.Globals;
import com.softorea.schoolsen.lists.Roster_List;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Arslan on 1/25/2018.
 */

public class M_Assembly extends Activity {

    String id, username, timee;
    SimpleDateFormat sdf;
    String currentDate, tim;
    Bitmap bmp;
    String absPath2;
    String emis;
    ImageView image;
    FileOutputStream outputStream;
    String formId;
    int REQUEST_IMAGE = 200;
    LinearLayout typeLay,oneLay;
    Button next,back;
    String ethicAvail = "Null";
    String ethicType = "Null";
    RadioGroup rgEthicAvailability, rgEthicType;
    RadioButton ethicYes,ethicNo,ethicNotAssemblyTime,typeSpeech,typeSkit;
    EditText topic;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_assembly);
        timee = new SimpleDateFormat("HH:mm:ss",
                Locale.getDefault()).format(new Date());
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
        sdf = new SimpleDateFormat("ddMMyyyy");
        currentDate = sdf.format(new Date());
        tim = new SimpleDateFormat("HHmmss",
                Locale.getDefault()).format(new Date());
        SharedPreferences prefs = getSharedPreferences(Globals.MyPREFERENCES, Context.MODE_PRIVATE);
        username = prefs.getString("username", "");
        image = (ImageView) findViewById(R.id.image1);
        typeLay = (LinearLayout) findViewById(R.id.typelayout);
        oneLay = (LinearLayout) findViewById(R.id.layoutone);
        next = (Button) findViewById(R.id.next);
        back = (Button) findViewById(R.id.back);
        rgEthicType = (RadioGroup) findViewById(R.id.rg_ethic_type);
        rgEthicAvailability = (RadioGroup) findViewById(R.id.rg_ethic_availability);
        ethicYes = (RadioButton) findViewById(R.id.rd_yes);
        ethicNo = (RadioButton) findViewById(R.id.rd_no);
        ethicNotAssemblyTime = (RadioButton)findViewById(R.id.rd_notMorningAssemblyTime);
        typeSpeech = (RadioButton) findViewById(R.id.rd_speech);
        typeSkit = (RadioButton) findViewById(R.id.rd_skit);
        topic = (EditText) findViewById(R.id.topicname);

        final int permission = PermissionChecker.checkSelfPermission(M_Assembly.this, Manifest.permission.CAMERA);

        image.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (permission == PermissionChecker.PERMISSION_GRANTED) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_IMAGE);
                } else {
                    ActivityCompat.requestPermissions(M_Assembly.this,
                            new String[]{Manifest.permission.CAMERA},
                            1);
                    //Toast.makeText(M_School_Status.this, "Permission not Given", Toast.LENGTH_SHORT).show();
                    // permission not granted, you decide what to do
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(M_Assembly.this, M_School_Status.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ethicAvail.equals("Null"))
                {
                    Toast.makeText(M_Assembly.this, "Please select ethic availability", Toast.LENGTH_SHORT).show();
                }
                else if (ethicAvail.equals("No"))
                {
                    startActivity(new Intent(M_Assembly.this, M_Building_Occupation.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (ethicAvail.equals("Not Morning Assembly Time"))
                {
                    startActivity(new Intent(M_Assembly.this, M_Building_Occupation.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (ethicAvail.equals("Yes") && ethicType.equals("Null"))
                {
                    Toast.makeText(M_Assembly.this, "Please select ethic type", Toast.LENGTH_SHORT).show();
                }
                else if (ethicAvail.equals("Yes") && !ethicType.equals("Null") && topic.getText().toString().equals(""))
                {
                    Toast.makeText(M_Assembly.this, "Write the name of topic", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    startActivity(new Intent(M_Assembly.this, M_Building_Occupation.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
            }
        });

        rgEthicAvailability.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rd_yes) {
                    ethicAvail = "Yes";
                    typeLay.setVisibility(View.VISIBLE);
                    oneLay.setVisibility(View.VISIBLE);

                } else if(checkedId == R.id.rd_no) {
                    ethicAvail = "No";
                    typeLay.setVisibility(View.GONE);
                    oneLay.setVisibility(View.GONE);
                    rgEthicType.clearCheck();
                    ethicType = "Null";
                    topic.setText("");
                }
                else
                {
                    ethicAvail = "Not Morning Assembly Time";
                    typeLay.setVisibility(View.GONE);
                    oneLay.setVisibility(View.GONE);
                    rgEthicType.clearCheck();
                    ethicType = "Null";
                    topic.setText("");
                }


            }
        });

        rgEthicType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rd_speech) {
                    ethicType = "Speech";

                } else {
                    ethicType = "Skit";
                }

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_IMAGE);
                } else {

                    Toast.makeText(M_Assembly.this, "Permission denied to use camera", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    public void onActivityResult(int requestcCode, int resultCode, Intent data) {


        /*if (requestcCode == RESULT_CANCELED && resultCode == Activity.RESULT_CANCELED)
        {
            Toast.makeText(this, "Picture is mandatory!", Toast.LENGTH_SHORT).show();
        }*/

        if (requestcCode == REQUEST_IMAGE && resultCode == Activity.RESULT_OK) {

            Bitmap capturedImage = (Bitmap) data.getExtras().get("data");
            image.setImageBitmap(capturedImage);
            image.setVisibility(View.VISIBLE);
            File file = new File(Environment
                    .getExternalStorageDirectory().getAbsolutePath() + "/IMU/pics/");
            if (!file.exists()) {
                if (!file.mkdirs()) {
                    Log.e("TravellerLog :: ",
                            "Problem creating Image folder");

                }
            }
            File imagename = new File(file, emis + "_"
                    + username + "_" + currentDate + "_" + tim + "_" + formId + "_ethicactivity.png");
            try {

                outputStream = new FileOutputStream(
                        imagename);
                capturedImage.compress(
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

            if (capturedImage != null) {
                Toast savedToast = Toast.makeText(
                        M_Assembly.this,
                        "Image saved",
                        Toast.LENGTH_SHORT);
                savedToast.show();
                startActivity(new Intent(M_Assembly.this, M_Assembly.class));
            } else {
                Toast unsavedToast = Toast.makeText(
                        M_Assembly.this,
                        "Oops! Image could not be saved.",
                        Toast.LENGTH_SHORT);
                unsavedToast.show();
            }

        }

    }
    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("ethicAvail", ethicAvail);
        editor.putString("ethicType", ethicType);
        editor.putString("topicName", topic.getText().toString());
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
        String ethicavail_ = preferences.getString("ethicAvail", "");
        String ethictype_ = preferences.getString("ethicType", "");
        String topicname = preferences.getString("topicName", "");
        topic.setText(topicname);
        if (ethicavail_.equals("Yes")) {
            ethicYes.setChecked(true);
            ethicNo.setChecked(false);
        } else if (ethicavail_.equals("No")) {
            ethicNo.setChecked(true);
            ethicYes.setChecked(false);
        } else {
            ethicYes.setChecked(false);
            ethicNo.setChecked(false);
        }
        if (ethictype_.equals("Speech")) {
            typeSpeech.setChecked(true);
            typeSkit.setChecked(false);
        } else if (ethictype_.equals("Skit")) {
            typeSkit.setChecked(true);
            typeSpeech.setChecked(false);
        } else {
            typeSpeech.setChecked(false);
            typeSkit.setChecked(false);
        }

        String p2 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/IMU/pics";
        File d = new File(p2);
        final File[] filel = d.listFiles();
        if (filel != null) {
            for (File f : filel) {
                if (f.getName().contains(emis) && f.getName().contains("ethicactivity")) {
                    absPath2 = f.getAbsolutePath();
                }
            }
        }
        //String path = Environment.getExternalStorageDirectory().getAbsolutePath()+ "/IMU/pics/" +emis + "_"
        //      + username + "_" + currentDate + "_" + tim + "_" + formId + "_status.png";
        try {
            bmp = BitmapFactory.decodeFile(absPath2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (bmp != null) {
            image.setImageBitmap(bmp);
            image.setVisibility(View.VISIBLE);
        } else {
            image.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(M_Assembly.this);
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
