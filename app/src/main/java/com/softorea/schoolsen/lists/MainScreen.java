package com.softorea.schoolsen.lists;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.databasehelper.DatabaseHandler;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by arsla on 10/10/2017.
 */

public class MainScreen extends Activity {

    public static ProgressDialog pDialog;
    String url = "http://175.107.63.45:18080/IMU_WebService/IMU_Servlet?_f=getRoaster";
    String urlteacher = "http://175.107.63.45:18080/IMU_WebService/IMU_Servlet?_f=getTeacherInformation_new";
    String urlnonteacher = "http://175.107.63.45:18080/IMU_WebService/IMU_Servlet?_f=getNonTeacherInformation_new";
    //String urlschoolinformation = "http://175.107.63.45:18080/IMU_WebService/IMU_Servlet?_f=getSchoolInformation";
    String urlschoolinformation = "http://175.107.63.45:18080/IMU_WebService/IMU_Servlet?_f=getSchoolInformation" + "waleed.test" + "/" + "9" + "/" + "2017";
    DatabaseHandler databaseHandler;
    TextView versiontextt;
    TextView logout;
    Button download_rosterbutton, profile, finalizedform, upload;
    //TextView downloadroster;
    int count = 0;
    int teachercount = 0;
    int nonteachercount = 0;
    int school_information_list = 0;
    String version,currentmonth;
    SimpleDateFormat sdf;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maindashboard);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 10);

        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 10);
        }

        versiontextt = (TextView) findViewById(R.id.versiontext);
        try {
            version = MainScreen.this.getPackageManager().getPackageInfo(
                    MainScreen.this.getPackageName(), 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }


        versiontextt.setText("Version " + version);
        sdf = new SimpleDateFormat("MM");
        currentmonth = sdf.format(new Date());
        logout = (TextView) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder prompt = new AlertDialog.Builder(MainScreen.this);
                prompt.setTitle("Confirmation");
                prompt.setMessage("Are you sure to Logout?");
                prompt.setCancelable(true);
                prompt.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("EXITT", true);
                        startActivity(intent);
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        finishAffinity();
                    }
                });

                prompt.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });
                prompt.show();
            }
        });
        databaseHandler = new DatabaseHandler(MainScreen.this);
        //databaseHandler.removeRoster(currentmonth);
        upload = (Button) findViewById(R.id.btn_uploaded);
        download_rosterbutton = (Button) findViewById(R.id.download_roster);
        finalizedform = (Button) findViewById(R.id.btn_savedforms);
        profile = (Button) findViewById(R.id.btn_profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainScreen.this, SettingActivity.class));
            }
        });
        final int permission = PermissionChecker.checkSelfPermission(MainScreen.this, Manifest.permission.READ_EXTERNAL_STORAGE);
        //final int permission1 = PermissionChecker.checkSelfPermission(MainScreen.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (permission == PermissionChecker.PERMISSION_GRANTED) {
                    startActivity(new Intent(MainScreen.this, UploadedForms.class));
                } else {
                    Toast.makeText(MainScreen.this, "Please allow storage permission", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(MainScreen.this, MainScreen.class));

                    // permission not granted, you decide what to do
                }
            }
        });
        finalizedform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (permission == PermissionChecker.PERMISSION_GRANTED) {
                    startActivity(new Intent(MainScreen.this, FinalizedForms.class));
                } else {
                    ActivityCompat.requestPermissions(MainScreen.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            1);
                    // permission not granted, you decide what to do
                }
            }
        });
        //downloadroster = (TextView) findViewById(R.id.textView14);


        /*downloadroster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                final String monitorid = "softorea.test";
                final String month = "9";
                final String year = "2017";
                final String appver = "3.2";

                AlertDialog.Builder prompt = new AlertDialog.Builder(MainScreen.this);
                prompt.setTitle("Confirmation");
                prompt.setMessage("Are you sure to download roster? It may take some time!");
                prompt.setCancelable(true);
                prompt.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new PostDataAsyncTask(monitorid, month, year, appver).execute();
                    }
                });

                prompt.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });
                prompt.show();
            }
        });*/

        download_rosterbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainScreen.this, Roster_List.class));
            }
        });

        /*download_rosterbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final String monitorid = "waleed.test";
                final String month = "9";
                final String year = "2017";
                final String appver = "3.2";
                new PostDataAsyncTaskForTeachers(monitorid, month, year).execute();
                new PostDataAsyncTaskForNonTeachers(monitorid, month, year).execute();
                new PostDataAsyncTaskSchoolInformation(monitorid, month, year).execute();

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainScreen.this);
                int countvalue = preferences.getInt("countno", 0);

                if (countvalue == 0) {
                    AlertDialog.Builder prompt = new AlertDialog.Builder(MainScreen.this);
                    prompt.setTitle("Confirmation");
                    prompt.setMessage("Are you sure to download roster? It may take some time please be patient!");
                    prompt.setCancelable(true);
                    prompt.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            count++;
                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainScreen.this);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putInt("countno", count);
                            editor.apply();
                            new PostDataAsyncTask(monitorid, month, year, appver).execute();

                        }
                    });

                    prompt.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();
                        }
                    });
                    prompt.show();
                } else {

                    startActivity(new Intent(MainScreen.this, Roster_List.class));
                }
            }
        });*/

        /*teacherinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final String monitorid = "waleed.test";
                final String month = "9";
                final String year = "2017";
                //final String appver = "3.2";

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainScreen.this);
                int countvalue = preferences.getInt("teachercountno", 0);

                if (countvalue == 0) {
                    AlertDialog.Builder prompt = new AlertDialog.Builder(MainScreen.this);
                    prompt.setTitle("Confirmation");
                    prompt.setMessage("Are you sure to download teacher information? It may take some time!");
                    prompt.setCancelable(true);
                    prompt.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            teachercount++;
                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainScreen.this);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putInt("teachercountno", teachercount);
                            editor.apply();
                            new PostDataAsyncTaskForTeachers(monitorid, month, year).execute();

                        }
                    });

                    prompt.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();
                        }
                    });
                    prompt.show();
                } else {

                    startActivity(new Intent(MainScreen.this, TeacherNew_List.class));
                }
            }
        });

        nonteacherinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final String monitorid = "waleed.test";
                final String month = "9";
                final String year = "2017";
                //final String appver = "3.2";

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainScreen.this);
                int countvalue = preferences.getInt("nonteachercountno", 0);

                if (countvalue == 0) {
                    AlertDialog.Builder prompt = new AlertDialog.Builder(MainScreen.this);
                    prompt.setTitle("Confirmation");
                    prompt.setMessage("Are you sure to download non teacher information? It may take some time!");
                    prompt.setCancelable(true);
                    prompt.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            nonteachercount++;
                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainScreen.this);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putInt("nonteachercountno", nonteachercount);
                            editor.apply();
                            new PostDataAsyncTaskForNonTeachers(monitorid, month, year).execute();

                        }
                    });

                    prompt.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();
                        }
                    });
                    prompt.show();
                } else {

                    startActivity(new Intent(MainScreen.this, NonTeacherNew_List.class));
                }
            }
        });

        schoolinformationlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final String monitorid = "waleed.test";
                final String month = "9";
                final String year = "2017";
                final String appver = "3.2";

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainScreen.this);
                int countvalue = preferences.getInt("school_information_list", 0);

                if (countvalue == 0) {
                    AlertDialog.Builder prompt = new AlertDialog.Builder(MainScreen.this);
                    prompt.setTitle("Confirmation");
                    prompt.setMessage("Are you sure to download school information? It may take some time!");
                    prompt.setCancelable(true);
                    prompt.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            school_information_list++;
                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainScreen.this);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putInt("school_information_list", school_information_list);
                            editor.apply();
                            new PostDataAsyncTaskSchoolInformation(monitorid, month, year).execute();

                        }
                    });

                    prompt.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();
                        }
                    });
                    prompt.show();
                } else {

                    startActivity(new Intent(MainScreen.this, SchoolInformation_List.class));
                }
            }
        });*/


    }


    /*public class PostDataAsyncTask extends AsyncTask<String, String, String> {
        private String data, data1, data2, data3;

        public PostDataAsyncTask(String textone, String texttwo, String textthree, String textfour) {
            data = textone;
            data1 = texttwo;
            data2 = textthree;
            data3 = textfour;

        }

        protected void onPreExecute() {
            super.onPreExecute();
            showpDialog();

        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                String postReceiverUrl = url;
                HttpClient httpClient = new DefaultHttpClient();

                HttpPost httpPost = new HttpPost(postReceiverUrl);

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                nameValuePairs.add(new BasicNameValuePair("monitorID", data));
                nameValuePairs.add(new BasicNameValuePair("month", data1));
                nameValuePairs.add(new BasicNameValuePair("year", data2));
                nameValuePairs.add(new BasicNameValuePair("appVersion", data3));
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = httpClient.execute(httpPost);
                HttpEntity resEntity = response.getEntity();

                if (resEntity != null) {
                    String emiscode;
                    String visittype;
                    String schoolname;

                    String responseStr = EntityUtils.toString(resEntity).trim();
                    JSONObject json = new JSONObject(responseStr);
                    JSONArray ja_data = json.getJSONArray("resultDesc");
                    if (json.getJSONArray("resultDesc").length() == 0) {
                        //Toast.makeText(MainScreen.this, "No data Found", Toast.LENGTH_LONG).show();
                        Log.d("Data", "No Data Found");

                    } else {
                        try {
                            for (int i = 0; i < ja_data.length(); i++) {
                                JSONObject jsonas = ja_data.getJSONObject(i);
                                emiscode = jsonas.getString("emiscode");
                                visittype = jsonas.getString("VisitType");
                                schoolname = jsonas.getString("schoolname");
                                Log.d("Data", "Emis code is " + emiscode + " Visit type are " + visittype + " School names are " + schoolname);
                                databaseHandler.saveRoster(new RosterDetails(emiscode, visittype, schoolname));
                            }
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block

                            e.printStackTrace();
                        }
                    }
                    startActivity(new Intent(MainScreen.this, Roster_List.class));
                }

            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            hidepDialog();
            Log.v("SuccesS", "Response: " + s);
        }

    }

    private void showpDialog() {
        pDialog = new ProgressDialog(MainScreen.this);
        pDialog.setCancelable(false);
        pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pDialog.setMax(100);
        //pDialog.setIndeterminate(true);
        pDialog = ProgressDialog.show(MainScreen.this, "", "Downloading information..");
    }

    private void hidepDialog() {
        try {
            pDialog.dismiss();
            pDialog = null;
        } catch (Exception e) {
            pDialog = new ProgressDialog(MainScreen.this);
            pDialog.dismiss();

        }
    }



    public class PostDataAsyncTaskForTeachers extends AsyncTask<String, String, String> {
        private String data, data1, data2;

        public PostDataAsyncTaskForTeachers(String textone, String texttwo, String textthree) {
            data = textone;
            data1 = texttwo;
            data2 = textthree;

        }

        protected void onPreExecute() {
            super.onPreExecute();
            //showpDialog();

        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                String postReceiverUrl = urlteacher;
                HttpClient httpClient = new DefaultHttpClient();

                HttpPost httpPost = new HttpPost(postReceiverUrl);

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                nameValuePairs.add(new BasicNameValuePair("monitorLoginName", data));
                nameValuePairs.add(new BasicNameValuePair("month", data1));
                nameValuePairs.add(new BasicNameValuePair("year", data2));
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = httpClient.execute(httpPost);
                HttpEntity resEntity = response.getEntity();

                if (resEntity != null) {
                    String tname,emis;
                    String tcnic;
                    String tno;
                    String desination;

                    String responseStr = EntityUtils.toString(resEntity).trim();
                    JSONObject json = new JSONObject(responseStr);
                    JSONArray ja_data = json.getJSONArray("resultDesc");
                    if (json.getJSONArray("resultDesc").length() == 0) {
                        //Toast.makeText(MainScreen.this, "No data Found", Toast.LENGTH_LONG).show();
                        Log.d("Data", "No Data Found");

                    } else {
                        try {
                            for (int i = 0; i < ja_data.length(); i++) {
                                JSONObject jsonas = ja_data.getJSONObject(i);
                                emis = jsonas.getString("Emis_Code");
                                tname = jsonas.getString("TeacherName");
                                tcnic = jsonas.getString("CNIC");
                                tno = jsonas.getString("Personal_No");
                                desination = jsonas.getString("designation");
                                //Log.d("Data", "Emis code is " + emiscode + " Visit type are " + visittype + " School names are " + schoolname);
                                databaseHandler.saveTeacherNew(new TeacherNewDetails(emis,tname, tcnic, tno,desination));
                            }
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block

                            e.printStackTrace();
                        }
                    }
                    //startActivity(new Intent(MainScreen.this, TeacherNew_List.class));
                }

            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            hidepDialog();
            Log.v("SuccesS", "Response: " + s);
        }
    }

    public class PostDataAsyncTaskForNonTeachers extends AsyncTask<String, String, String> {
        private String data, data1, data2;

        public PostDataAsyncTaskForNonTeachers(String textone, String texttwo, String textthree) {
            data = textone;
            data1 = texttwo;
            data2 = textthree;

        }

        protected void onPreExecute() {
            super.onPreExecute();
            //showpDialog();

        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                String postReceiverUrl = urlnonteacher;
                HttpClient httpClient = new DefaultHttpClient();

                HttpPost httpPost = new HttpPost(postReceiverUrl);

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                nameValuePairs.add(new BasicNameValuePair("monitorLoginName", data));
                nameValuePairs.add(new BasicNameValuePair("month", data1));
                nameValuePairs.add(new BasicNameValuePair("year", data2));
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = httpClient.execute(httpPost);
                HttpEntity resEntity = response.getEntity();

                if (resEntity != null) {
                    String tname;
                    String tcnic;
                    String tno,emis;
                    String desination;

                    String responseStr = EntityUtils.toString(resEntity).trim();
                    JSONObject json = new JSONObject(responseStr);
                    JSONArray ja_data = json.getJSONArray("resultDesc");
                    if (json.getJSONArray("resultDesc").length() == 0) {
                        //Toast.makeText(MainScreen.this, "No data Found", Toast.LENGTH_LONG).show();
                        Log.d("Data", "No Data Found");

                    } else {
                        try {
                            for (int i = 0; i < ja_data.length(); i++) {
                                JSONObject jsonas = ja_data.getJSONObject(i);
                                emis = jsonas.getString("Emis_Code");
                                tname = jsonas.getString("TeacherName");
                                tcnic = jsonas.getString("CNIC");
                                tno = jsonas.getString("Personal_No");
                                desination = jsonas.getString("designation");
                                //Log.d("Data", "Emis code is " + emiscode + " Visit type are " + visittype + " School names are " + schoolname);
                                databaseHandler.saveNonTeacherNew(new NonTeacherNewDetails(emis,tname, tcnic, tno,desination));
                            }
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block

                            e.printStackTrace();
                        }
                    }
                    //startActivity(new Intent(MainScreen.this, NonTeacherNew_List.class));
                }

            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            hidepDialog();
            Log.v("SuccesS", "Response: " + s);
        }
    }

    public class PostDataAsyncTaskSchoolInformation extends AsyncTask<String, String, String> {
        private String data, data1, data2;
        String jsonStr;
        String loginId = "waleed.test";
        String month = "9";
        String year="2017";
        private String url = Globals.urlGetSchoolInfoTask + loginId + "/" + month + "/" + year;

        public PostDataAsyncTaskSchoolInformation(String textone, String texttwo, String textthree) {
            data = textone;
            data1 = texttwo;
            data2 = textthree;

        }

        protected void onPreExecute() {
            super.onPreExecute();
            //showpDialog();

        }

        @Override
        protected String doInBackground(String... strings) {
            /*try {
                String postReceiverUrl = urlschoolinformation;
                HttpClient httpClient = new DefaultHttpClient();

                HttpPost httpPost = new HttpPost(postReceiverUrl);

                /*List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                nameValuePairs.add(new BasicNameValuePair("monitorLoginName", data));
                nameValuePairs.add(new BasicNameValuePair("month", data1));
                nameValuePairs.add(new BasicNameValuePair("year", data2));
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = httpClient.execute(httpPost);
                HttpEntity resEntity = response.getEntity();

                if (resEntity != null) {
                    String emis;
                    String name;
                    String gender;
                    String level;
                    String ddo;
                    String district;
                    String tehsil;
                    String ucname;
                    String location;
                    String zone;
                    String nano;
                    String pkno;
                    String circleofficename;

                    String responseStr = EntityUtils.toString(resEntity).trim();
                    JSONObject json = new JSONObject(responseStr);
                    JSONArray ja_data = json.getJSONArray("GetSchoolInformationResult");
                    if (json.getJSONArray("GetSchoolInformationResult").length() == 0) {
                        //Toast.makeText(MainScreen.this, "No data Found", Toast.LENGTH_LONG).show();
                        Log.d("Data", "No Data Found");

                    } else {
                        try {
                            for (int i = 0; i < ja_data.length(); i++) {
                                JSONObject jsonas = ja_data.getJSONObject(i);
                                String CicleSchoolCode = jsonas
                                        .getString("CircleOfficeCode");
                                String CircleOfficeName = jsonas
                                        .getString("CircleOfficeName");
                                String District = jsonas.getString("District");
                                String EMIS_CODE = jsonas.getString("EmisCode");
                                String Gender = jsonas.getString("Gender");
                                String Level = jsonas.getString("Level");
                                String Location = jsonas.getString("Location");
                                String Na_No = jsonas.getString("NaNo");
                                String Pk_No = jsonas.getString("PkNo");
                                String School_name = jsonas.getString("SchoolName");
                                String Tehsil = jsonas.getString("Tehsil");
                                String UC_Name = jsonas.getString("UCName");
                                String WINTERSUMMER = jsonas.getString("WinterSummer");
                                String ADOContactNo = jsonas.getString("ADOContactNo");
                                String AreaOfSchool = jsonas.getString("AreaOfSchool");
                                String CoverdAreaOfSchool = jsonas.getString("CoverdAreaOfSchool");
                                String Upgradedate = jsonas.getString("Upgradedate");
                                String YearOfEstablishment = jsonas.getString("YearOfEstablishment");
                                String NameOfADO = jsonas.getString("NameOfADO");
                                //Log.d("Data", "Emis code is " + emiscode + " Visit type are " + visittype + " School names are " + schoolname);
                                databaseHandler.saveSchoolInformation(new SchoolInformationDetails(EMIS_CODE, School_name,
                                        Gender,Level, CicleSchoolCode, District, Tehsil,UC_Name,
                                        Location, WINTERSUMMER, Na_No,Pk_No,CircleOfficeName));
                            }
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block

                            e.printStackTrace();
                        }
                    }
                    //startActivity(new Intent(MainScreen.this, SchoolInformation_List.class));
                }

            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }*/
            /*ServiceHandler sh = new ServiceHandler();
            jsonStr = sh.makeServiceCall(url, ServiceHandler.POST);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    JSONArray jsonArray = jsonObj.getJSONArray("GetSchoolInformationResult");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jObject = jsonArray.getJSONObject(i);

                        String CicleSchoolCode = jObject
                                .getString("CircleOfficeCode");
                        String CircleOfficeName = jObject
                                .getString("CircleOfficeName");
                        String District = jObject.getString("District");
                        String EMIS_CODE = jObject.getString("EmisCode");
                        String Gender = jObject.getString("Gender");
                        String Level = jObject.getString("Level");
                        String Location = jObject.getString("Location");
                        String Na_No = jObject.getString("NaNo");
                        String Pk_No = jObject.getString("PkNo");
                        String School_name = jObject.getString("SchoolName");
                        String Tehsil = jObject.getString("Tehsil");
                        String UC_Name = jObject.getString("UCName");
                        String WINTERSUMMER = jObject.getString("WinterSummer");
                        String ADOContactNo = jObject.getString("ADOContactNo");
                        String AreaOfSchool = jObject.getString("AreaOfSchool");
                        String CoverdAreaOfSchool = jObject.getString("CoverdAreaOfSchool");
                        String Upgradedate = jObject.getString("Upgradedate");
                        String YearOfEstablishment = jObject.getString("YearOfEstablishment");
                        String NameOfADO = jObject.getString("NameOfADO");
                        databaseHandler.saveSchoolInformation(new SchoolInformationDetails(EMIS_CODE, School_name,
                                Gender,Level, CicleSchoolCode, District, Tehsil,UC_Name,
                                Location, WINTERSUMMER, Na_No,Pk_No,CircleOfficeName,NameOfADO,ADOContactNo));


                    }
                    Log.d("Response", jsonStr);
                } catch (Exception e) {
                    Log.d("Error", e.getMessage().toString());
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            hidepDialog();
            Log.v("SuccesS", "Response: " + s);
        }
    }*/

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startActivity(new Intent(MainScreen.this, FinalizedForms.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                } else {

                    Toast.makeText(MainScreen.this, "Please allow storage permission", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder prompt = new AlertDialog.Builder(MainScreen.this);
        prompt.setTitle("Confirmation");
        prompt.setMessage("Are you sure to Logout?");
        prompt.setCancelable(true);
        prompt.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();

            }
        });

        prompt.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });
        prompt.show();

    }
}


