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
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.text.Editable;
import android.text.TextWatcher;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Softorea on 10/2/2017.
 */

public class M_SchoolFunctioning extends Activity {
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    private static final String IMAGE_DIRECTORY_NAME = "Hello Camera";
    Button back, next;
    String electriAvailable = "Null";
    String electricFunction = "Null";
    String electricSource = "Null";
    String waterAvail = "Null";
    String waterFunction = "Null";
    String srcWater = "Null";
    String drinkable = "Null";
    String notDrinkAvail = "Null";
    String toiletAvail = "Null";
    String toiletFunction = "Null";
    String boundaryAvail = "Null";
    String boundaryFunction = "Null";
    M_ConditionalGrant grantFragment;
    ArrayList<Grant> grantList = new ArrayList<>();
    RadioButton electricity_available, electricity_notavailable, electricity_functioning, electricity_notfunctioning,
            drinking_available, drinking_notavailable, drinking_functioning, drinking_notfunctioning, drinkablee, notdrinkable,
            toiletavailable, toiletnotavailble, toiletfunctioning, toiletnotfunctioning, boundarywallavailable, boundarynotavailable,
            boundarywallpartially, boundarywallwholly, rdwapda, rdsolar, rdboth;
    EditText waterSource;
    EditText stdToilet;
    EditText TeachToilet;
    EditText comToilet;
    EditText stdToiletFunc;
    EditText teacherToiletFunc;
    EditText commonToiletFunction;
    ImageView imgView1;
    ImageView imgView2;
    String emis;
    Button btnLeft;
    Button btnRight;
    SharedPreferences prefs;
    FileOutputStream outputStream;
    int bolImage = 0;
    String monitorLogin;
    String time;
    DatabaseHandler databaseHandler;
    LinearLayout eleAvailable, WaterFunctionView, WaterSourceView,
            ToiletAvailView, WallAvailView, eleSrcLayout;
    RadioGroup rgElectricity, rgElecFunction, rgDrinkingWater, RgWaterFun,
            RgToilet, RgWall, waterDrinkable, RgToiletFun, RgWallFunc, rgElecSrc;
    int boleleavail = 0;
    int bolelefuc = 0;
    int bolelesrc = 0;
    int bolwateravail = 0;
    int bolwatersource = 0;
    int bolwaterdrink = 0;
    int boltoiletavail = 0;
    int boltoiletfunction = 0;
    int bolteachertoilet = 0;
    int bolstdtoilet = 0;
    int bolcommontoilet = 0;
    int bolwallavail = 0;
    int bolwallfunc = 0;
    String username, emiss, formId, currentDate, tim;
    SimpleDateFormat sdf;
    private Uri fileUri;

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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_school_functioning);
        databaseHandler = new DatabaseHandler(M_SchoolFunctioning.this);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        emiss = preferences.getString("emiscode", "");
        SQLiteOpenHelper database = new DatabaseHandler(this);
        SQLiteDatabase dbd = database.getReadableDatabase();
        Cursor cursorform = dbd.rawQuery("SELECT * FROM t_roster WHERE emis= " + emiss, null);
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
        rdwapda = (RadioButton) findViewById(R.id.rd_electricity_wapda);
        rdsolar = (RadioButton) findViewById(R.id.rd_electricity_solar);
        rdboth = (RadioButton) findViewById(R.id.rd_electricity_both);

        electricity_available = (RadioButton) findViewById(R.id.rd_electricity_available);
        electricity_notavailable = (RadioButton) findViewById(R.id.rd_electricity_notavailable);
        electricity_functioning = (RadioButton) findViewById(R.id.rd_electricity_functioning);
        electricity_notfunctioning = (RadioButton) findViewById(R.id.rd_electricity_notfunctioning);
        drinking_available = (RadioButton) findViewById(R.id.rd_wateravail);
        drinking_notavailable = (RadioButton) findViewById(R.id.rd_waternotavail);
        drinking_functioning = (RadioButton) findViewById(R.id.rd_water_functioning);
        drinking_notfunctioning = (RadioButton) findViewById(R.id.rd_water_notfunctioning);
        drinkablee = (RadioButton) findViewById(R.id.rd_water_dable);
        notdrinkable = (RadioButton) findViewById(R.id.rd_water_notdrinkable);
        toiletavailable = (RadioButton) findViewById(R.id.rd_toiletavail);
        toiletnotavailble = (RadioButton) findViewById(R.id.rd_toiletnotavail);
        toiletfunctioning = (RadioButton) findViewById(R.id.rd_toilet_functioning);
        toiletnotfunctioning = (RadioButton) findViewById(R.id.rd_toilets_notfunctioning);
        boundarywallavailable = (RadioButton) findViewById(R.id.rd_boundary_available);
        boundarynotavailable = (RadioButton) findViewById(R.id.rd_boundary_notavailable);
        boundarywallpartially = (RadioButton) findViewById(R.id.rd_wall_functioning);
        boundarywallwholly = (RadioButton) findViewById(R.id.rd_wall_notfunctioning);

        imgView1 = (ImageView) findViewById(R.id.img1);
        imgView2 = (ImageView) findViewById(R.id.im2);
        waterSource = (EditText) findViewById(R.id.water_source);
        stdToilet = (EditText) findViewById(R.id.stdtoilet);
        TeachToilet = (EditText) findViewById(R.id.stdteachertoilet);
        comToilet = (EditText) findViewById(R.id.comontoilet);
        stdToiletFunc = (EditText) findViewById(R.id.stdtoiletfunc);
        rgElecSrc = (RadioGroup) findViewById(R.id.rg_electricity_src);
        eleSrcLayout = (LinearLayout) findViewById(R.id.src_ele_layout);
        teacherToiletFunc = (EditText) findViewById(R.id.stdteachertoiletfunc);
        commonToiletFunction = (EditText) findViewById(R.id.comontoiletfunc);

        rgElectricity = (RadioGroup) findViewById(R.id.rg_electricity);
        rgElecFunction = (RadioGroup) findViewById(R.id.rg_electricity_fun);
        RgWaterFun = (RadioGroup) findViewById(R.id.rg_water_fun);
        RgToilet = (RadioGroup) findViewById(R.id.rg_toilet);
        RgToiletFun = (RadioGroup) findViewById(R.id.rg_toilet_fun);
        RgWall = (RadioGroup) findViewById(R.id.rg_boundarywall);
        waterDrinkable = (RadioGroup) findViewById(R.id.rg_water_drink);
        RgWallFunc = (RadioGroup) findViewById(R.id.rg_wall_fun);

        eleAvailable = (LinearLayout) findViewById(R.id.ele_available);
        WaterFunctionView = (LinearLayout) findViewById(R.id.water_view);
        WaterSourceView = (LinearLayout) findViewById(R.id.water_source_view);
        ToiletAvailView = (LinearLayout) findViewById(R.id.toilet_available);
        WallAvailView = (LinearLayout) findViewById(R.id.wall_available);
        rgDrinkingWater = (RadioGroup) findViewById(R.id.rg_drinkwater);

        comToilet.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (comToilet.getText().length() > 0) {
                    bolcommontoilet = 1;
                    //

                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }
        });
        TeachToilet.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (TeachToilet.getText().length() > 0) {
                    bolteachertoilet = 1;

                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }
        });

        stdToilet.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (stdToilet.getText().length() > 0) {
                    bolstdtoilet = 1;

                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }
        });

        waterSource.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (waterSource.getText().length() > 0) {
                    bolwatersource = 1;

                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }
        });

        RgWallFunc.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rd_wall_functioning) {
                    boundaryFunction = "Partially";
                    //bolwallfunc = 1;

                } else if (checkedId == R.id.rd_wall_notfunctioning) {
                    boundaryFunction = "Wholly";
                    //bolwallfunc = 1;

                }

            }
        });

        RgToiletFun.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rd_toilet_functioning) {
                    toiletFunction = "Toilet is Functioning";
                    boltoiletfunction = 1;

                } else if (checkedId == R.id.rd_toilets_notfunctioning) {
                    toiletFunction = "Toilet is not Functioning";
                    boltoiletfunction = 1;

                }

            }
        });

        waterDrinkable
                .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if (checkedId == R.id.rd_water_dable) {
                            drinkable = "Water is drinkable";
                            bolwaterdrink = 1;

                        } else if (checkedId == R.id.rd_water_notdrinkable) {
                            drinkable = "Water is not drinkable";
                            bolwaterdrink = 1;

                        }

                    }
                });

        rgElectricity.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rd_electricity_available) {
                    electriAvailable = "Electricity Available";
                    boleleavail = 1;
                    rgElecFunction.clearCheck();
                    eleAvailable.setVisibility(View.VISIBLE);
                    eleSrcLayout.setVisibility(View.VISIBLE);
                    bolelefuc = 0;
                    electricSource = "Null";

                } else if (checkedId == R.id.rd_electricity_notavailable) {
                    electriAvailable = "Electricity Not Available";
                    boleleavail = 1;
                    bolelefuc = 1;
                    bolelesrc = 1;
                    electricFunction = "Null";
                    rgElecFunction.clearCheck();
                    eleAvailable.setVisibility(View.GONE);
                    eleSrcLayout.setVisibility(View.GONE);
                    rdwapda.setChecked(false);
                    rdboth.setChecked(false);
                    rdsolar.setChecked(false);
                    electricSource = "Null";
                }

            }
        });

        rgElecSrc.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rd_electricity_wapda) {
                    electricSource = "Source Wapda";

                } else if (checkedId == R.id.rd_electricity_solar) {
                    electricSource = "Source Solar";
                }
                else if (checkedId == R.id.rd_electricity_both) {
                    electricSource = "Source Both";
                }
                else {
                    electricSource = "Null";
                }

            }
        });

        rgElecFunction
                .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if (checkedId == R.id.rd_electricity_functioning) {
                            electricFunction = "Electricity Functioning";
                            bolelefuc = 1;

                        } else if (checkedId == R.id.rd_electricity_notfunctioning) {
                            electricFunction = "Electricity Not Functioning";
                            bolelefuc = 1;

                        }

                    }
                });


        rgDrinkingWater
                .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if (checkedId == R.id.rd_wateravail) {
                            waterAvail = "Water Available";
                            bolwateravail = 1;
                            bolwaterdrink = 0;
                            bolwatersource = 0;
                            RgWaterFun.clearCheck();
                            WaterFunctionView.setVisibility(View.VISIBLE);
                        } else if (checkedId == R.id.rd_waternotavail) {
                            waterAvail = "Water is Not Available";
                            RgWaterFun.clearCheck();
                            WaterSourceView.setVisibility(View.GONE);
                            RgWaterFun.clearCheck();
                            bolwateravail = 1;
                            bolwaterdrink = 1;
                            bolwatersource = 1;
                            waterFunction = "Null";
                            srcWater = "Null";
                            waterSource.setText("");
                            drinkable = "Null";
                            notDrinkAvail = "Null";
                            //
                            //waterSource.setText("");
                            waterDrinkable.clearCheck();

                            WaterFunctionView.setVisibility(View.GONE);

                        }

                    }
                });
        RgWaterFun.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rd_water_functioning) {
                    waterFunction = "Water is Functioning";
                    bolwaterdrink = 1;
                    bolwatersource = 0;
                    //waterSource.setText("");
                    waterDrinkable.clearCheck();
                    WaterSourceView.setVisibility(View.VISIBLE);
                } else if (checkedId == R.id.rd_water_notfunctioning) {
                    waterFunction = "Water is not Functioning";
                    bolwaterdrink = 1;
                    bolwatersource = 1;
                    waterSource.setText("");
                    waterDrinkable.clearCheck();
                    WaterSourceView.setVisibility(View.GONE);
                }

            }
        });

        RgToilet.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rd_toiletavail) {
                    toiletAvail = "Toilet Available";
                    boltoiletavail = 1;
                    boltoiletfunction = 0;
                    bolteachertoilet = 0;
                    bolstdtoilet = 0;
                    bolcommontoilet = 0;
                    RgToiletFun.clearCheck();
                    ToiletAvailView.setVisibility(View.VISIBLE);
                } else if (checkedId == R.id.rd_toiletnotavail) {
                    toiletAvail = "Toilet Not Available";
                    boltoiletavail = 1;
                    boltoiletfunction = 1;
                    bolteachertoilet = 1;
                    bolstdtoilet = 1;
                    bolcommontoilet = 1;
                    RgToiletFun.clearCheck();
                    toiletFunction = "Null";
                    stdToiletFunc.setText("");
                    teacherToiletFunc.setText("");
                    commonToiletFunction.setText("");
                    stdToilet.setText("");
                    TeachToilet.setText("");
                    comToilet.setText("");

                    ToiletAvailView.setVisibility(View.GONE);
                }

            }
        });
        RgWall.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rd_boundary_available) {
                    boundaryAvail = "Boundary Available";
                    bolwallavail = 1;
                    bolwallfunc = 0;
                    RgWallFunc.clearCheck();

                    WallAvailView.setVisibility(View.VISIBLE);
                } else if (checkedId == R.id.rd_boundary_notavailable) {
                    bolwallavail = 1;
                    bolwallfunc = 1;
                    RgWallFunc.clearCheck();
                    boundaryAvail = "Boundary not Available";
                    boundaryFunction = "Null";
                    WallAvailView.setVisibility(View.GONE);
                }

            }
        });

        final int permission = PermissionChecker.checkSelfPermission(M_SchoolFunctioning.this, Manifest.permission.CAMERA);
        imgView1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //bolImage = 1;
                //captureImage();
                if (permission == PermissionChecker.PERMISSION_GRANTED) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 0);
                } else {
                    ActivityCompat.requestPermissions(M_SchoolFunctioning.this,
                            new String[]{Manifest.permission.CAMERA},
                            1);
                    //Toast.makeText(M_School_Status.this, "Permission not Given", Toast.LENGTH_SHORT).show();
                    // permission not granted, you decide what to do
                }

            }
        });

        imgView2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (permission == PermissionChecker.PERMISSION_GRANTED) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 1);
                } else {
                    ActivityCompat.requestPermissions(M_SchoolFunctioning.this,
                            new String[]{Manifest.permission.CAMERA},
                            1);
                    //Toast.makeText(M_School_Status.this, "Permission not Given", Toast.LENGTH_SHORT).show();
                    // permission not granted, you decide what to do
                }
                //bolImage = 2;
                //captureImage();

            }
        });


        back = (Button) findViewById(R.id.btn_left);
        next = (Button) findViewById(R.id.btn_right);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = databaseHandler.getReadableDatabase();
                String queryFac1 = "SELECT * FROM " + "Grantwebservice" + " WHERE " + "w_imei_code='" + emiss + "'"
                        + " AND " + "w_grant_id='" + "1" + "'";
                String queryFac2 = "SELECT * FROM " + "Grantwebservice" + " WHERE " + "w_imei_code='" + emiss + "'"
                        + " AND " + "w_grant_id='" + "2" + "'";
                String queryFac3 = "SELECT * FROM " + "Grantwebservice" + " WHERE " + "w_imei_code='" + emiss + "'"
                        + " AND " + "w_grant_id='" + "3" + "'";
                String queryFac4 = "SELECT * FROM " + "Grantwebservice" + " WHERE " + "w_imei_code='" + emiss + "'"
                        + " AND " + "w_grant_id='" + "4" + "'";
                String queryFac5 = "SELECT * FROM " + "Grantwebservice" + " WHERE " + "w_imei_code='" + emiss + "'"
                        + " AND " + "w_grant_id='" + "5" + "'";
                String queryFac6 = "SELECT * FROM " + "Grantwebservice" + " WHERE " + "w_imei_code='" + emiss + "'"
                        + " AND " + "w_grant_id='" + "6" + "'";
                String queryFac7 = "SELECT * FROM " + "Grantwebservice" + " WHERE " + "w_imei_code='" + emiss + "'"
                        + " AND " + "w_grant_id='" + "7" + "'";
                String queryFac8 = "SELECT * FROM " + "Grantwebservice" + " WHERE " + "w_imei_code='" + emiss + "'"
                        + " AND " + "w_grant_id='" + "8" + "'";

                Cursor cfac1 = db.rawQuery(queryFac1, null);
                Cursor cfac2 = db.rawQuery(queryFac2, null);
                Cursor cfac3 = db.rawQuery(queryFac3, null);
                Cursor cfac4 = db.rawQuery(queryFac4, null);
                Cursor cfac5 = db.rawQuery(queryFac5, null);
                Cursor cfac6 = db.rawQuery(queryFac6, null);
                Cursor cfac7 = db.rawQuery(queryFac7, null);
                Cursor cfac8 = db.rawQuery(queryFac8, null);
                if (electriAvailable.equals("Null"))
                {
                    Toast.makeText(M_SchoolFunctioning.this, "Fill electricity fields", Toast.LENGTH_SHORT).show();
                }
               else if (electriAvailable.equals("Electricity Available") && electricFunction.equals("Null"))
                {
                    Toast.makeText(M_SchoolFunctioning.this, "Fill electricity fields", Toast.LENGTH_SHORT).show();
                }
                else if (electriAvailable.equals("Electricity Available") && !electricFunction.equals("Null")
                && electricSource.equals("Null"))
                {
                    Toast.makeText(M_SchoolFunctioning.this, "Fill electricity fields", Toast.LENGTH_SHORT).show();
                }
                else if (waterAvail.equals("Null"))
                {
                    Toast.makeText(M_SchoolFunctioning.this, "Fill drinking water fields", Toast.LENGTH_SHORT).show();
                }
                else if (waterAvail.equals("Water Available") && waterFunction.equals("Null"))
                {
                    Toast.makeText(M_SchoolFunctioning.this, "Fill drinking water fields", Toast.LENGTH_SHORT).show();
                }
                else if (waterAvail.equals("Water Available") && waterFunction.equals("Water is Functioning")
                        && waterSource.getText().toString().equals(""))
                {
                    Toast.makeText(M_SchoolFunctioning.this, "Fill water source", Toast.LENGTH_SHORT).show();
                }
                else if (waterAvail.equals("Water Available") && waterFunction.equals("Water is Functioning")
                        && drinkable.equals("Null"))
                {
                    Toast.makeText(M_SchoolFunctioning.this, "Fill drinkable field", Toast.LENGTH_SHORT).show();
                }
                else if (toiletAvail.equals("Null"))
                {
                    Toast.makeText(M_SchoolFunctioning.this, "Fill toilet fields", Toast.LENGTH_SHORT).show();
                }
                else if (toiletAvail.equals("Toilet Available") && toiletFunction.equals("Null"))
                {
                    Toast.makeText(M_SchoolFunctioning.this, "Fill toilet fields", Toast.LENGTH_SHORT).show();
                }
                else if (!toiletAvail.equals("Toilet Not Available") && (stdToilet.getText().toString().equals("") || stdToiletFunc.getText().toString().equals("")
                        || TeachToilet.getText().toString().equals("") || teacherToiletFunc.getText().toString().equals("")
                        || comToilet.getText().toString().equals("") || commonToiletFunction.getText().toString().equals("")))
                {
                    Toast.makeText(M_SchoolFunctioning.this, "Fill toilets text fields", Toast.LENGTH_SHORT).show();
                }
                else if (boundaryAvail.equals("Null"))
                {
                    Toast.makeText(M_SchoolFunctioning.this, "Fill boundary fields", Toast.LENGTH_SHORT).show();
                }
                else if (boundaryAvail.equals("Boundary Available") && boundaryFunction.equals("Null"))
                {
                    Toast.makeText(M_SchoolFunctioning.this, "Fill boundary fields", Toast.LENGTH_SHORT).show();
                }
                else if (cfac1.moveToFirst()) {
                    startActivity(new Intent(M_SchoolFunctioning.this, M_NewGrant1.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                } else if (cfac2.moveToFirst()) {
                    startActivity(new Intent(M_SchoolFunctioning.this, M_NewGrant2.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                } else if (cfac3.moveToFirst()) {
                    startActivity(new Intent(M_SchoolFunctioning.this, M_NewGrant3.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                } else if (cfac4.moveToFirst()) {
                    startActivity(new Intent(M_SchoolFunctioning.this, M_NewGrant4.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                } else if (cfac5.moveToFirst()) {
                    startActivity(new Intent(M_SchoolFunctioning.this, M_NewGrant5.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                } else if (cfac6.moveToFirst()) {
                    startActivity(new Intent(M_SchoolFunctioning.this, M_NewGrant6.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                } else if (cfac7.moveToFirst()) {
                    startActivity(new Intent(M_SchoolFunctioning.this, M_NewGrant7.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (cfac8.moveToFirst()) {
                    startActivity(new Intent(M_SchoolFunctioning.this, M_NewGrant8.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else {
                    startActivity(new Intent(M_SchoolFunctioning.this, M_VacantPositionsList.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                //startActivity(new Intent(M_SchoolFunctioning.this, M_VacantPositionsList.class));
                //finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(M_SchoolFunctioning.this, M_SchoolVisitList.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });

        /*for (Grant grant:grantList) {

            grantFragment = new M_ConditionalGrant();
            grantFragment.setGrant(grant.getGrantType(),grant.getAmount(),grant.getYear(),grant.getFaceCode());

        }*/

    }

    private void captureImage() {
        final int permission = PermissionChecker.checkSelfPermission(M_SchoolFunctioning.this, Manifest.permission.CAMERA);
        if (permission == PermissionChecker.PERMISSION_GRANTED) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);

            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

            // start the image capture Intent
            startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
        } else {
            ActivityCompat.requestPermissions(M_SchoolFunctioning.this,
                    new String[]{Manifest.permission.CAMERA},
                    1);
            //Toast.makeText(M_School_Status.this, "Permission not Given", Toast.LENGTH_SHORT).show();
            // permission not granted, you decide what to do
        }
    }

    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // if the result is capturing Image
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                Bitmap capturedImage = (Bitmap) data.getExtras().get("data");
                imgView1.setImageBitmap(capturedImage);
                imgView1.setVisibility(View.VISIBLE);
                File file = new File(Environment
                        .getExternalStorageDirectory().getAbsolutePath() + "/IMU/pics/");
                if (!file.exists()) {
                    if (!file.mkdirs()) {
                        Log.e("TravellerLog :: ",
                                "Problem creating Image folder");

                    }
                }

                File imagename = new File(file, emiss + "_"
                        + username + "_" + formId + "_facility1.png");
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
                    Toast savedToast = Toast.makeText(M_SchoolFunctioning.this, "Image saved", Toast.LENGTH_SHORT);
                    savedToast.show();
                    startActivity(new Intent(M_SchoolFunctioning.this, M_SchoolFunctioning.class));
                } else {

                }
            }
        }
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Bitmap capturedImage = (Bitmap) data.getExtras().get("data");
                imgView2.setImageBitmap(capturedImage);
                imgView2.setVisibility(View.VISIBLE);
                File file = new File(Environment
                        .getExternalStorageDirectory().getAbsolutePath() + "/IMU/pics/");
                if (!file.exists()) {
                    if (!file.mkdirs()) {
                        Log.e("TravellerLog :: ",
                                "Problem creating Image folder");

                    }
                }

                File imagename = new File(file, emiss + "_"
                        + username + "_" + formId + "_facility2.png");
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
                            M_SchoolFunctioning.this,
                            "Image saved",
                            Toast.LENGTH_SHORT);
                    savedToast.show();
                    startActivity(new Intent(M_SchoolFunctioning.this, M_SchoolFunctioning.class));
                } else {

                }
            }
        } else if (resultCode == RESULT_CANCELED) {
            // user cancelled Image capture
        } else {
            // failed to capture image

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);

                    intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

                    // start the image capture Intent
                    startActivityForResult(intent, 0);
                    startActivityForResult(intent, 1);
                } else {

                    Toast.makeText(M_SchoolFunctioning.this, "Permission denied to use camera", Toast.LENGTH_SHORT).show();
                }
                return;
            }

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("electriAvailable", electriAvailable);
        editor.putString("electricFunction", electricFunction);
        editor.putString("electricSrc", electricSource);
        editor.putString("waterAvail", waterAvail);
        editor.putString("waterFunction", waterFunction);
        editor.putString("watersource", waterSource.getText().toString());
        editor.putString("drinkable", drinkable);
        editor.putString("toiletAvail", toiletAvail);
        editor.putString("toiletFunction", toiletFunction);

        editor.putString("boundaryAvail", boundaryAvail);
        editor.putString("boundaryFunction", boundaryFunction);
        editor.putString("stdToilet", stdToilet.getText().toString());
        editor.putString("TeachToilet", TeachToilet.getText().toString());
        editor.putString("comToilet", comToilet.getText().toString());
        editor.putString("stdToiletFunc", stdToiletFunc.getText().toString());
        editor.putString("teacherToiletFunc", teacherToiletFunc.getText().toString());
        editor.putString("commonToiletFunction", commonToiletFunction.getText().toString());
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/IMU/pics/" + emiss + "_"
                + username + "_" + formId + "_facility1.png";
        Bitmap bmp = BitmapFactory.decodeFile(path);
        if (bmp != null) {
            imgView1.setImageBitmap(bmp);
            imgView1.setVisibility(View.VISIBLE);
        } else {
            imgView1.setVisibility(View.VISIBLE);
        }
        String path1 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/IMU/pics/" + emiss + "_"
                + username + "_" + formId + "_facility2.png";
        Bitmap bmpm = BitmapFactory.decodeFile(path1);
        if (bmpm != null) {
            imgView2.setImageBitmap(bmpm);
            imgView2.setVisibility(View.VISIBLE);
        } else {
            imgView2.setVisibility(View.VISIBLE);
        }
        //image.setVisibility(View.VISIBLE);
        SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
        String srcw = preferences.getString("electricSrc", "");
        String a = preferences.getString("electriAvailable", "");
        String b = preferences.getString("electricFunction", "");
        String c = preferences.getString("waterAvail", "");
        String d = preferences.getString("waterFunction", "");
        String e = preferences.getString("watersource", "");
        String f = preferences.getString("drinkable", "");
        String g = preferences.getString("toiletAvail", "");
        String h = preferences.getString("toiletFunction", "");
        String i = preferences.getString("boundaryAvail", "");
        String j = preferences.getString("boundaryFunction", "");
        String k = preferences.getString("stdToilet", "");
        String l = preferences.getString("TeachToilet", "");
        String m = preferences.getString("comToilet", "");
        String n = preferences.getString("stdToiletFunc", "");
        String o = preferences.getString("teacherToiletFunc", "");
        String p = preferences.getString("commonToiletFunction", "");
        stdToilet.setText(k);
        TeachToilet.setText(l);
        comToilet.setText(m);
        stdToiletFunc.setText(n);
        teacherToiletFunc.setText(o);
        commonToiletFunction.setText(p);
        waterSource.setText(e);



        if (a.equals("Electricity Available")) {
            electricity_available.setChecked(true);
        } else if (a.equals("Electricity Not Available")) {
            electricity_notavailable.setChecked(true);
        }
        if (b.equals("Electricity Functioning")) {
            electricity_functioning.setChecked(true);
        } else if (b.equals("Electricity Not Functioning")) {
            electricity_notfunctioning.setChecked(true);
        }
        if (c.equals("Water Available")) {
            drinking_available.setChecked(true);
        } else if (c.equals("Water is Not Available")) {
            drinking_notavailable.setChecked(true);
        }
        if (d.equals("Water is Functioning")) {
            drinking_functioning.setChecked(true);
        } else if (d.equals("Water is not Functioning")) {
            drinking_notfunctioning.setChecked(true);
        }
        if (srcw.equals("Source Wapda")) {
            rdwapda.setChecked(true);
        } else if (srcw.equals("Source Solar")) {
            rdsolar.setChecked(true);
        } else if (srcw.equals("Source Both")) {
            rdboth.setChecked(true);
        } else {

        }
        if (f.equals("Water is drinkable")) {
            drinkablee.setChecked(true);
        } else if (f.equals("Water is not drinkable")) {
            notdrinkable.setChecked(true);
        }
        if (g.equals("Toilet Available")) {
            toiletavailable.setChecked(true);
        } else if (g.equals("Toilet Not Available")) {
            toiletnotavailble.setChecked(true);
        }
        if (h.equals("Toilet is Functioning")) {
            toiletfunctioning.setChecked(true);
        } else if (h.equals("Toilet is not Functioning")) {
            toiletnotfunctioning.setChecked(true);
        }
        if (i.equals("Boundary Available")) {
            boundarywallavailable.setChecked(true);
        } else if (i.equals("Boundary not Available")) {
            boundarynotavailable.setChecked(true);
        }
        if (j.equals("Partially")) {
            boundarywallpartially.setChecked(true);
        } else if (j.equals("Wholly")) {
            boundarywallwholly.setChecked(true);
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(M_SchoolFunctioning.this);
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
