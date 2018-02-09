package com.softorea.schoolsen.gcsschools;

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
import android.net.Uri;
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
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Softorea on 9/15/2017.
 */

public class M_School_Status extends Activity {
    public static final int RequestPermissionCode = 1;
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    private static final String IMAGE_DIRECTORY_NAME = "Hello Camera";
    LinearLayout permanentlayout;
    Bitmap bmp;
    String absPath2;
    String TAG = "Write_Permission";
    int REQUEST_IMAGE = 200;
    RadioGroup permanentGroup;
    File imagename;
    RadioButton rdnonFunctional;
    RadioButton rdillegalOccupiedIndividual;
    RadioButton rdillegalOccupiedOrginazation;
    RadioButton rdstdAndStaffAbsent;
    //RadioButton rdstaffAbsent;
    RadioButton rdstdAbsent;
    RadioButton rdTeachingAbsent;
    RadioButton rdMerged;
    RadioButton rdOthers;
    int bolImage = 0;
    String emis;
    String monitorLogin;
    String time;
    Intent intent;
    EditText permanentOtherEdit;
    String statusVlaueDetail = "Open";
    Button takePicture;
    ImageView image;
    FileOutputStream outputStream;
    int bolradio = 0;
    Button back, next;
    EditText reason;
    String id, username, timee, chkvisit;
    String formId;
    SimpleDateFormat sdf;
    String currentDate, tim;
    private Uri fileUri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.e_m_school_status);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat df3 = new SimpleDateFormat("HH:mm:ss");
        String formattedDate3 = df3.format(calendar.getTime());
        //String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        String startTime = formattedDate3;
        SharedPreferences newsf = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = newsf.edit();
        editor.putString("starting_time", startTime);
        editor.apply();
        timee = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
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
        rdnonFunctional = (RadioButton) findViewById(R.id.nonfuntional_btn);
        rdillegalOccupiedIndividual = (RadioButton) findViewById(R.id.ill_indivisual_btn);
        rdillegalOccupiedOrginazation = (RadioButton) findViewById(R.id.ill_organization_btn);
        rdstdAndStaffAbsent = (RadioButton) findViewById(R.id.std_stf_absnt_btn);
        rdTeachingAbsent = (RadioButton) findViewById(R.id.teach_absnt_btn);
        rdstdAbsent = (RadioButton) findViewById(R.id.std_absnt_btn);
        //rdstaffAbsent = (RadioButton) findViewById(R.id.stf_absnt_btn);
        reason = (EditText) findViewById(R.id.permanent_others_edit);
        Intent intent = getIntent();
        id = intent.getStringExtra("data");
        //chkvisit = intent.getStringExtra("chkvisittype");


        rdMerged = (RadioButton) findViewById(R.id.merged_btn);

        rdOthers = (RadioButton) findViewById(R.id.temp_others_btn);

        rdOthers.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        M_School_Status.this);
                alertDialogBuilder.setMessage("Are you sure to clear checkbox");
                alertDialogBuilder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                permanentGroup.clearCheck();
                                takePicture.setVisibility(View.GONE);
                                permanentOtherEdit.setVisibility(View.GONE);
                                image.setVisibility(View.GONE);
                                bolradio = 0;
                                statusVlaueDetail = "Open";
                                SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.remove("checkvalue");
                                editor.remove("reasonOthers");
                                editor.apply();

                            }
                        });
                alertDialogBuilder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {

                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                return false;

            }
        });


        rdTeachingAbsent.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(M_School_Status.this);
                alertDialogBuilder.setMessage("Are you sure to clear checkbox");
                alertDialogBuilder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                permanentGroup.clearCheck();
                                takePicture.setVisibility(View.GONE);
                                permanentOtherEdit.setVisibility(View.GONE);
                                image.setVisibility(View.GONE);
                                bolradio = 0;
                                statusVlaueDetail = "Open";
                                SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.remove("checkvalue");
                                editor.remove("reasonOthers");
                                editor.apply();

                            }
                        });
                alertDialogBuilder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {

                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                return false;


            }
        });

        /*rdstaffAbsent.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        M_School_Status.this);
                alertDialogBuilder.setMessage("Are you sure to clear checkbox");
                alertDialogBuilder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                permanentGroup.clearCheck();
                                takePicture.setVisibility(View.GONE);
                                permanentOtherEdit.setVisibility(View.GONE);
                                bolradio = 0;
                                statusVlaueDetail = "Open";
                            }
                        });
                alertDialogBuilder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                return false;

            }
        });*/

        rdstdAbsent.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        M_School_Status.this);
                alertDialogBuilder.setMessage("Are you sure to clear checkbox");
                alertDialogBuilder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                permanentGroup.clearCheck();
                                takePicture.setVisibility(View.GONE);
                                permanentOtherEdit.setVisibility(View.GONE);
                                image.setVisibility(View.GONE);
                                bolradio = 0;
                                statusVlaueDetail = "Open";
                                SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.remove("checkvalue");
                                editor.remove("reasonOthers");
                                editor.apply();
                                // validate();
                            }
                        });
                alertDialogBuilder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                // Toast.makeText(context, "NO",
                                // Toast.LENGTH_SHORT).show();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                return false;

            }
        });

        rdMerged.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        M_School_Status.this);
                alertDialogBuilder.setMessage("Are you sure to clear checkbox");
                alertDialogBuilder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                permanentGroup.clearCheck();
                                //	rdstdAndStaffAbsent.setChecked(false);
                                // vp.setPagingEnabled(true);
                                takePicture.setVisibility(View.GONE);
                                permanentOtherEdit.setVisibility(View.GONE);
                                image.setVisibility(View.GONE);
                                bolradio = 0;
                                statusVlaueDetail = "Open";
                                SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.remove("checkvalue");
                                editor.remove("reasonOthers");
                                editor.apply();
                                // validate();
                            }
                        });
                alertDialogBuilder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                // Toast.makeText(context, "NO",
                                // Toast.LENGTH_SHORT).show();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                return false;

            }
        });


        rdstdAndStaffAbsent.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        M_School_Status.this);
                alertDialogBuilder.setMessage("Are you sure to clear checkbox");
                alertDialogBuilder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                permanentGroup.clearCheck();
                                //	rdstdAndStaffAbsent.setChecked(false);
                                // vp.setPagingEnabled(true);
                                takePicture.setVisibility(View.GONE);
                                permanentOtherEdit.setVisibility(View.GONE);
                                image.setVisibility(View.GONE);
                                bolradio = 0;
                                statusVlaueDetail = "Open";
                                SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.remove("checkvalue");
                                editor.remove("reasonOthers");
                                editor.apply();
                                // validate();
                            }
                        });
                alertDialogBuilder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                // Toast.makeText(context, "NO",
                                // Toast.LENGTH_SHORT).show();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                return false;

            }
        });

        rdillegalOccupiedOrginazation
                .setOnLongClickListener(new View.OnLongClickListener() {

                    @Override
                    public boolean onLongClick(View v) {

                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                M_School_Status.this);
                        alertDialogBuilder
                                .setMessage("Are you sure to clear checkbox");
                        alertDialogBuilder.setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface arg0,
                                                        int arg1) {
                                        permanentGroup.clearCheck();
//										rdillegalOccupiedOrginazation
//												.setChecked(false);
                                        // vp.setPagingEnabled(true);
                                        takePicture.setVisibility(View.GONE);
                                        permanentOtherEdit
                                                .setVisibility(View.GONE);
                                        image.setVisibility(View.GONE);
                                        bolradio = 0;
                                        statusVlaueDetail = "Open";
                                        SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
                                        SharedPreferences.Editor editor = preferences.edit();
                                        editor.remove("checkvalue");
                                        editor.remove("reasonOthers");
                                        editor.apply();
                                        // validate();
                                    }
                                });
                        alertDialogBuilder.setNegativeButton("No",
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        // Toast.makeText(context, "NO",
                                        // Toast.LENGTH_SHORT).show();
                                    }
                                });

                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                        return false;

                    }
                });

        rdillegalOccupiedIndividual
                .setOnLongClickListener(new View.OnLongClickListener() {

                    @Override
                    public boolean onLongClick(View v) {

                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                M_School_Status.this);
                        alertDialogBuilder
                                .setMessage("Are you sure to clear checkbox");
                        alertDialogBuilder.setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface arg0,
                                                        int arg1) {
                                        permanentGroup.clearCheck();
                                        takePicture.setVisibility(View.GONE);
                                        permanentOtherEdit
                                                .setVisibility(View.GONE);
                                        image.setVisibility(View.GONE);
                                        bolradio = 0;
                                        statusVlaueDetail = "Open";
                                        SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
                                        SharedPreferences.Editor editor = preferences.edit();
                                        editor.remove("checkvalue");
                                        editor.remove("reasonOthers");
                                        editor.apply();
                                        // validate();
                                    }
                                });
                        alertDialogBuilder.setNegativeButton("No",
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        // Toast.makeText(context, "NO",
                                        // Toast.LENGTH_SHORT).show();
                                    }
                                });

                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                        return false;

                    }
                });
        rdnonFunctional.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        M_School_Status.this);
                alertDialogBuilder.setMessage("Are you sure to clear checkbox");
                alertDialogBuilder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                permanentGroup.clearCheck();
                                takePicture.setVisibility(View.GONE);
                                permanentOtherEdit.setVisibility(View.GONE);
                                image.setVisibility(View.GONE);
                                bolradio = 0;
                                statusVlaueDetail = "Open";
                                SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.remove("checkvalue");
                                editor.remove("reasonOthers");
                                editor.apply();

                            }
                        });
                alertDialogBuilder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {

                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                return false;
            }
        });

        takePicture = (Button) findViewById(R.id.btncamera);
        final int permission = PermissionChecker.checkSelfPermission(M_School_Status.this, Manifest.permission.CAMERA);

        takePicture.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (permission == PermissionChecker.PERMISSION_GRANTED) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_IMAGE);
                } else {
                    ActivityCompat.requestPermissions(M_School_Status.this,
                            new String[]{Manifest.permission.CAMERA},
                            1);
                    //Toast.makeText(M_School_Status.this, "Permission not Given", Toast.LENGTH_SHORT).show();
                    // permission not granted, you decide what to do
                }
            }
        });


        image = (ImageView) findViewById(R.id.statusimage);

        permanentOtherEdit = (EditText) findViewById(R.id.permanent_others_edit);
        permanentlayout = (LinearLayout) findViewById(R.id.permanent_layout);
        permanentGroup = (RadioGroup) findViewById(R.id.permanent_group);
        permanentGroup
                .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {

                        if (checkedId == R.id.nonfuntional_btn) {
                            statusVlaueDetail = "Permanent Non Functional";
                            takePicture.setVisibility(View.VISIBLE);
                            reason.setText("");
                            image.setVisibility(View.VISIBLE);
                            reason.setVisibility(View.GONE);
                            bolradio = 1;

                        } else if (checkedId == R.id.ill_indivisual_btn) {
                            statusVlaueDetail = "Permanent Illegal occupied by Individual";
                            takePicture.setVisibility(View.VISIBLE);
                            reason.setText("");
                            image.setVisibility(View.VISIBLE);
                            reason.setVisibility(View.GONE);
                            bolradio = 1;


                        } else if (checkedId == R.id.ill_organization_btn) {
                            statusVlaueDetail = "Permanent Illegal Occupied by Organization";
                            takePicture.setVisibility(View.VISIBLE);
                            reason.setText("");
                            image.setVisibility(View.VISIBLE);
                            reason.setVisibility(View.GONE);
                            bolradio = 1;


                        } else if (checkedId == R.id.std_stf_absnt_btn) {
                            statusVlaueDetail = "Student and teaching Staff Absent";
                            takePicture.setVisibility(View.VISIBLE);
                            reason.setText("");
                            image.setVisibility(View.VISIBLE);
                            reason.setVisibility(View.GONE);
                            bolradio = 1;


                        } else if (checkedId == R.id.stf_absnt_btn) {
                            statusVlaueDetail = "Non teaching Staff Absent";
                            takePicture.setVisibility(View.VISIBLE);
                            reason.setText("");
                            image.setVisibility(View.VISIBLE);
                            reason.setVisibility(View.GONE);
                            bolradio = 1;


                        } else if (checkedId == R.id.temp_others_btn) {
                            statusVlaueDetail = "Others";
                            takePicture.setVisibility(View.VISIBLE);
                            image.setVisibility(View.VISIBLE);
                            reason.setVisibility(View.VISIBLE);
                            permanentOtherEdit.setVisibility(View.VISIBLE);
                            bolradio = 1;


                        } else if (checkedId == R.id.std_absnt_btn) {
                            statusVlaueDetail = "Student Absent";
                            reason.setText("");
                            reason.setVisibility(View.GONE);
                            image.setVisibility(View.VISIBLE);
                            takePicture.setVisibility(View.VISIBLE);
                            bolradio = 1;

                        } else if (checkedId == R.id.teach_absnt_btn) {

                            statusVlaueDetail = "Teaching Staff Absent";
                            reason.setText("");
                            reason.setVisibility(View.GONE);
                            image.setVisibility(View.VISIBLE);
                            takePicture.setVisibility(View.VISIBLE);
                            bolradio = 1;

                        } else if (checkedId == R.id.merged_btn) {

                            statusVlaueDetail = "merged";
                            reason.setText("");
                            reason.setVisibility(View.GONE);
                            image.setVisibility(View.VISIBLE);
                            takePicture.setVisibility(View.VISIBLE);
                            bolradio = 1;

                        }

                    }
                });

        back = (Button) findViewById(R.id.btn_left);
        next = (Button) findViewById(R.id.btn_right);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(M_School_Status.this);
                //String value = preferences.getString("checkvalue", "");

                if (statusVlaueDetail.equals("Permanent Non Functional") || (statusVlaueDetail.equals("Permanent Illegal occupied by Individual"))
                        || statusVlaueDetail.equals("Permanent Illegal Occupied by Organization") || statusVlaueDetail.equals("merged")
                        || statusVlaueDetail.equals("Others") || statusVlaueDetail.equals("Student and teaching Staff Absent")
                        || statusVlaueDetail.equals("Teaching Staff Absent") || (statusVlaueDetail.equals("Student Absent"))) {
                    SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("case1gcs", "case1found");
                    editor.remove("case2");
                    editor.remove("case3");
                    editor.apply();
                    if (image.getDrawable() == null) {
                        Toast.makeText(M_School_Status.this, "Picture is mandatory!", Toast.LENGTH_SHORT).show();
                    } else {
                        startActivity(new Intent(M_School_Status.this, com.softorea.schoolsen.gcsschools.HeadSignature.class));
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        finish();
                    }
                }
                /*else if(statusVlaueDetail.equals("Student and teaching Staff Absent")|| statusVlaueDetail.equals("Teaching Staff Absent"))
                {
                    SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("case2", "case2found");
                    editor.remove("case1");
                    editor.remove("case3");
                    editor.apply();
                    if (image.getDrawable()==null)
                    {
                        Toast.makeText(M_School_Status.this, "Picture is mandatory!", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        startActivity(new Intent(M_School_Status.this, M_NonTeacherPresenceList.class));
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        finish();
                    }

                }*/
                /*else if(statusVlaueDetail.equals("Student Absent"))
                {
                    SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("case3", "case3found");
                    editor.remove("case2");
                    editor.remove("case1");
                    editor.apply();
                    startActivity(new Intent(M_School_Status.this, M_TeacherPresenceList.class));
                    finish();
                }*/
                else {
                    SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.remove("case1gcs");
                    editor.remove("case2");
                    editor.remove("case3");
                    editor.apply();
                    Intent view_order_intent = new Intent(M_School_Status.this, com.softorea.schoolsen.gcsschools.M_Building_Occupation.class);
                    startActivity(view_order_intent);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                //startActivity(new Intent(M_School_Status.this, M_Building_Occupation.class));
                //finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(M_School_Status.this, MBY_SchoolInformation.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });
    }

    /*@Override

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                captureImage();

            } else {

                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();

            }

        }
    }

    private void captureImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        // start the image capture Intent
        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
    }

    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    private static File getOutputMediaFile(int type) {

        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("", "Oops! Failed create " + "" + " directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".jpg");
        } else if (type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "VID_" + timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // if the result is capturing Image
        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // successfully captured the image
                // display it in image view
                previewCapturedImage();
            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled Image capture
                Toast.makeText(M_School_Status.this, "User cancelled image capture",
                        Toast.LENGTH_SHORT).show();
            } else {
                // failed to capture image
                Toast.makeText(M_School_Status.this, "Sorry! Failed to capture image",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void previewCapturedImage() {
        try {

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 8;

            final Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(),
                    options);

            final Bitmap stampedBitmap = timestampItAndSave(bitmap);

            File file = new File(Environment.getExternalStorageDirectory(),
                    "/.sampledata/" + emis + "");
            if (!file.exists()) {
                if (!file.mkdirs()) {
                    Log.e("TravellerLog :: ", "Problem creating Image folder");

                }
            }
            File imagename;

            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                    Locale.getDefault()).format(new Date());

            if (bolImage == 1) {

                imagename = new File(file, emis + "_" + monitorLogin + "_"
                        + time + "_ficility1.png");
            } else {
                imagename = new File(file, emis + "_" + monitorLogin + "_"
                        + time + "_ficility2.png");
            }

            try {

                outputStream = new FileOutputStream(imagename);
                stampedBitmap.compress(Bitmap.CompressFormat.PNG, 100,
                        outputStream);

                outputStream.flush();
                outputStream.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (bolImage == 1) {
                image.setImageBitmap(bitmap);
                bolImage = 0;
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private Bitmap timestampItAndSave(Bitmap toEdit) {
        Bitmap dest = Bitmap.createBitmap(toEdit.getWidth(),
                toEdit.getHeight(), Bitmap.Config.ARGB_8888);

        String dateTime = new SimpleDateFormat("HH:mm:ss:dd:MM:yyyy",
                Locale.getDefault()).format(new Date());
        Canvas cs = new Canvas(dest);

        Paint tPaint = new Paint();
        tPaint.setTextSize(20);
        tPaint.setColor(Color.RED);
        tPaint.setStyle(Paint.Style.FILL);
        float height = tPaint.measureText("yY");

        cs.drawBitmap(toEdit, 0, 0, tPaint);
        cs.drawText(dateTime, 20f, height + 15f, tPaint);

        return dest;
    }*/

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

                    Toast.makeText(M_School_Status.this, "Permission denied to use camera", Toast.LENGTH_SHORT).show();
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
                    + username + "_" + currentDate + "_" + tim + "_" + formId + "_status.png");
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
                        M_School_Status.this,
                        "Image saved",
                        Toast.LENGTH_SHORT);
                savedToast.show();
                startActivity(new Intent(M_School_Status.this, M_School_Status.class));
            } else {
                Toast unsavedToast = Toast.makeText(
                        M_School_Status.this,
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
        editor.putString("checkvalue", statusVlaueDetail);
        editor.putString("reasonOthers", reason.getText().toString());
        editor.apply();
    }


    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
        String reasonvalue = preferences.getString("reasonOthers", "");
        reason.setText(reasonvalue);
        String value = preferences.getString("checkvalue", "");
        String valuee = preferences.getString("checkvalue", "");
        String p2 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/IMU/pics";
        File d = new File(p2);
        final File[] filel = d.listFiles();
        if (filel != null) {
            for (File f : filel) {
                if (f.getName().contains(emis) && f.getName().contains("status")) {
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
        if (valuee.equals("Permanent Non Functional") || (valuee.equals("Permanent Illegal occupied by Individual"))
                || valuee.equals("Permanent Illegal Occupied by Organization") || valuee.equals("merged")
                || valuee.equals("Others") || value.equals("Student and teaching Staff Absent")
                || valuee.equals("Teaching Staff Absent") || (valuee.equals("Student Absent"))) {
            image.setImageBitmap(bmp);
            image.setVisibility(View.VISIBLE);
        } else {
            takePicture.setVisibility(View.GONE);
            image.setVisibility(View.GONE);
        }
        if (value.equals("Permanent Non Functional")) {
            rdnonFunctional.setChecked(true);
            reason.setVisibility(View.GONE);
        } else if (value.equals("Permanent Illegal occupied by Individual")) {
            rdillegalOccupiedIndividual.setChecked(true);
            reason.setVisibility(View.GONE);
        } else if (value.equals("Permanent Illegal Occupied by Organization")) {
            rdillegalOccupiedOrginazation.setChecked(true);
            reason.setVisibility(View.GONE);
        } else if (value.equals("Student and teaching Staff Absent")) {
            rdstdAndStaffAbsent.setChecked(true);
            reason.setVisibility(View.GONE);
        } else if (value.equals("Teaching Staff Absent")) {
            rdTeachingAbsent.setChecked(true);
            reason.setVisibility(View.GONE);
        } else if (value.equals("Student Absent")) {
            rdstdAbsent.setChecked(true);
            reason.setVisibility(View.GONE);
        } else if (value.equals("merged")) {
            rdMerged.setChecked(true);
            reason.setVisibility(View.GONE);
        } else if (value.equals("Others")) {
            rdOthers.setChecked(true);
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(M_School_Status.this);
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

   /* @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                Toast.makeText(this, "Picture is mandatory!", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onKeyDown(keyCode, event);
        }
    }*/
}