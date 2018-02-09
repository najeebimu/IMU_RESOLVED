package com.softorea.schoolsen.lists;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.softorea.schoolsen.R;
import com.softorea.schoolsen.adapters.CustomAdapterRoster;
import com.softorea.schoolsen.databasehelper.DatabaseHandler;
import com.softorea.schoolsen.m_monthly.Grant;
import com.softorea.schoolsen.models.DetailsNonTeacherwebservice;
import com.softorea.schoolsen.models.DetailsTeacherwebservice;
import com.softorea.schoolsen.models.GcsTeacherDetails;
import com.softorea.schoolsen.models.RosterDetails;
import com.softorea.schoolsen.models.ScreenConfig;
import com.softorea.schoolsen.models.SubjectModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.message.BasicNameValuePair;
import cz.msebera.android.httpclient.util.EntityUtils;

/**
 * Created by arsla on 10/10/2017.
 */

public class Roster_List extends Activity {
    public static ProgressDialog pDialog;
    String url = "http://175.107.63.45:18080/IMU_WebService/IMU_Servlet?_f=getRoaster";
    String urlteacher = "http://175.107.63.45:18080/IMU_WebService/IMU_Servlet?_f=getTeacherInformation_new";
    String urlnonteacher = "http://175.107.63.45:18080/IMU_WebService/IMU_Servlet?_f=getNonTeacherInformation_new";
    //String urlschoolinformation = "http://175.107.63.45:18080/IMU_WebService/IMU_Servlet?_f=getSchoolInformation";
    String urlschoolinformation = "http://175.107.63.45:18080/IMU_WebService/IMU_Servlet?_f=getSchoolInformation" + "waleed.test" + "/" + "9" + "/" + "2017";
    DatabaseHandler databaseHandler;
    RosterDetails details;
    String username;
    ArrayList<RosterDetails> addas = new ArrayList<RosterDetails>();
    ArrayList<SubjectModel> subjectModelList;
    ArrayList<Grant> GrantList;
    CustomAdapterRoster cusadapter;
    SimpleDateFormat sdf, sdff;
    String currentyear, currentmonth;
    Button download;
    String version;
    private ListView listcontent = null;

    public static boolean isLocationEnabled(Context context) {
        int locationMode = 0;
        String locationProviders;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);

            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
                return false;
            }

            return locationMode != Settings.Secure.LOCATION_MODE_OFF;

        } else {
            locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            return !TextUtils.isEmpty(locationProviders);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.rosterlist);
        SharedPreferences prefs = getSharedPreferences(Globals.MyPREFERENCES,
                Context.MODE_PRIVATE);
        username = prefs.getString("username", null);
        databaseHandler = new DatabaseHandler(Roster_List.this);
        sdf = new SimpleDateFormat("MM");
        currentmonth ="1";
        //currentmonth = sdf.format(new Date());
        sdff = new SimpleDateFormat("yyyy");
        currentyear = sdff.format(new Date());
        if (getIntent().getBooleanExtra("EXIT", false)) {

        }
        try {
            version = Roster_List.this.getPackageManager().getPackageInfo(
                    Roster_List.this.getPackageName(), 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        download = (Button) findViewById(R.id.download);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String monitorid = username;
                final String month = currentmonth;
                final String year = currentyear;
                final String appver = version;
                AlertDialog.Builder prompt = new AlertDialog.Builder(Roster_List.this);
                prompt.setTitle("Confirmation");
                prompt.setMessage("Are you sure to download roster? It may take some time please be patient!");
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
        });
        //checkGps();
        listcontent = (ListView) findViewById(R.id.list);
        Rsults();
        Resources res = getResources();
    }

    private void checkGps() {

        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
        }
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.gps)
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.dismiss();
                        close();
                    }

                    public void close() {

                        finish();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    private void Rsults() {
        addas.clear();
        DatabaseHandler databaseHandler = new DatabaseHandler(this);
        ArrayList<ArrayList<Object>> data = databaseHandler.abcRoster();

        for (int p = 0; p < data.size(); p++) {
            details = new RosterDetails();
            ArrayList<Object> baris = data.get(p);
            details.setEmis(baris.get(0).toString());
            details.setVisit(baris.get(1).toString());
            details.setSchool(baris.get(2).toString());
            details.setFormId(baris.get(3).toString());
            addas.add(details);
        }
        Collections.sort(addas, new SortBasedOnName());
        cusadapter = new CustomAdapterRoster(Roster_List.this, addas);
        listcontent.setAdapter(cusadapter);
        listcontent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position,
                                    long id) {
                String emiscodelenght = addas.get(position).getEmis();
                String visittype = addas.get(position).getVisit();
                SharedPreferences preferencess = getSharedPreferences("abcd", MODE_PRIVATE);
                SharedPreferences.Editor editorrr = preferencess.edit();
                editorrr.putString("visitycheck", visittype);
                editorrr.apply();

                //if (emiscodelenght.length() != 5 || visittype.equals("Revisit")) {
                if (canGetLocation() == false) {
                    showSettingsAlert();
                } else if (emiscodelenght.length() > 5) {
                    //Toast.makeText(Roster_List.this, "Not assigned to emis code > 5 digit", Toast.LENGTH_SHORT).show();
                    Intent view_order_intent = new Intent(Roster_List.this, com.softorea.schoolsen.gcsschools.MBY_SchoolInformation.class);
                    startActivity(view_order_intent);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Roster_List.this);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("emiscode", addas.get(position).getEmis());
                    editor.apply();
                } else {
                    Intent view_order_intent = new Intent(Roster_List.this, MBY_SchoolInformation.class);
                    startActivity(view_order_intent);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Roster_List.this);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("emiscode", addas.get(position).getEmis());
                    editor.apply();
                }


            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public boolean canGetLocation() {
        boolean result = true;
        LocationManager lm = null;
        boolean gps_enabled = false;
        boolean network_enabled = false;
        if (lm == null)

            lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // exceptions will be thrown if provider is not permitted.
        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {

        }
        try {
            network_enabled = lm
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
        }
        if (gps_enabled == false || network_enabled == false) {
            result = false;
        } else {
            result = true;
        }

        return result;
    }

    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);


        // Setting Dialog Message
        alertDialog.setMessage(R.string.gps);
        alertDialog.setCancelable(true);

        // On pressing Settings button
        alertDialog.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(intent);
                    }
                });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface paramDialogInterface, int paramInt) {


            }
        });

        alertDialog.show();
    }

    public void getGrant() {
        RequestQueue queue = Volley.newRequestQueue(Roster_List.this);
        final String emis = ArrayToString();
        StringRequest myReq = new StringRequest(Request.Method.POST,
                Globals.urlGrant,
                createMyReqSuccessListener(),
                createMyReqErrorListener()) {

            @Override
            public byte[] getBody() throws com.android.volley.AuthFailureError {
                String str = "{\"ModelSchool\":[{\"EmisCode\":\"" + emis + "\"}]}";
                return str.getBytes();
            }

            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

        };
        queue.add(myReq);

    }

    private Response.Listener<String> createMyReqSuccessListener() {
        return new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //bolgrant = true;
                    GrantList = new ArrayList<Grant>();
                    JSONObject jObj = new JSONObject(response);
                    String msg = jObj.getString("ReturnMsg");
                    if (msg.equalsIgnoreCase("Success")) {
                        JSONArray jArray = jObj.getJSONArray("Modelschool");
                        if (jArray != null) {
                            Grant grant;
                            for (int i = 0; i < jArray.length(); i++) {
                                grant = new Grant();
                                String emiscode = jArray.getJSONObject(i).getString("EmisCode");
                                String grantType = jArray.getJSONObject(i).getString("GrantType");
                                String year = jArray.getJSONObject(i).getString("Year");
                                String amount = jArray.getJSONObject(i).getString("Amount");
                                int grantId = jArray.getJSONObject(i).getInt("FacCode");

                                grant.setEmisCode(emiscode);
                                grant.setGrantType(grantType);
                                grant.setYear(year);
                                grant.setAmount(amount);
                                grant.setFaceCode(grantId);

                                GrantList.add(grant);
                                databaseHandler.createTableGrantt(grant);
                                Log.e("Grantlist is", "emis" + emiscode + "granttype" + grantType + "year" + year + "amount"
                                        + amount + "grantid" + grantId);
                            }
                        }

                        hidepDialog();
                        finish();
                        startActivity(new Intent(Roster_List.this, Roster_List.class));
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    } else {
                        Toast.makeText(Roster_List.this, "Error while downloading", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    int k = 0;
                    k++;
                }
            }
        };
    }

    private Response.ErrorListener createMyReqErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        };
    }

    private String ArrayToString() {

        StringBuilder commaSepValueBuilder = new StringBuilder();
        ArrayList<String> DA = new ArrayList<>();
        RosterDetails ab;
        RosterDetails abb = new RosterDetails();
        SQLiteOpenHelper database = new DatabaseHandler(this);
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM t_roster", null);
        if (c.moveToFirst()) {
            do {
                DA.add(c.getString(c.getColumnIndex("emis")));

            } while (c.moveToNext());
        }
        c.close();
        db.close();
        for (int i = 0; i < DA.size(); i++) {

            //ab = DA.get(i);
            commaSepValueBuilder.append(DA.get(i));


            if (i != DA.size() - 1) {
                commaSepValueBuilder.append(",");
            }
        }

        return commaSepValueBuilder.toString();
    }

    private void showpDialog() {
        pDialog = new ProgressDialog(Roster_List.this);
        pDialog.setCancelable(false);
        pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pDialog.setMax(100);
        //pDialog.setIndeterminate(true);
        pDialog = ProgressDialog.show(Roster_List.this, "", "Downloading information..");
    }

    private void hidepDialog() {
        try {
            pDialog.dismiss();
            pDialog = null;
        } catch (Exception e) {
            pDialog = new ProgressDialog(Roster_List.this);
            pDialog.dismiss();

        }
    }


    @Override
    public void onBackPressed() {

        startActivity(new Intent(Roster_List.this, MainScreen.class));
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();

    }

    public class PostDataAsyncTask extends AsyncTask<String, String, String> {
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
                    String schoolname, SURVEYDONE, SURVEYUPLAODED, formid,monthofdow;

                    String responseStr = EntityUtils.toString(resEntity).trim();
                    JSONObject json = new JSONObject(responseStr);
                    JSONArray ja_data = json.getJSONArray("resultDesc");
                    if (json.getJSONArray("resultDesc").length() == 0) {
                        Log.d("Data", "No Data Found");

                    } else {
                        try {
                            for (int i = 0; i < ja_data.length(); i++) {
                                JSONObject jsonas = ja_data.getJSONObject(i);
                                emiscode = jsonas.getString("emiscode");
                                visittype = jsonas.getString("VisitType");
                                schoolname = jsonas.getString("schoolname");
                                monthofdow = jsonas.getString("month");
                                SURVEYDONE = jsonas.getString("surveydone");
                                formid = jsonas.getString("id");
                                SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("formId", formid);
                                editor.apply();
                                SURVEYUPLAODED = jsonas.getString("surveyuploaded");
                                Log.d("Data", "Emis code is " + emiscode + " Visit type are " + visittype + " School names are " + schoolname);
                                if (SURVEYDONE.equals("1") || SURVEYUPLAODED.equals("1")) {
                                } else {
                                    databaseHandler.saveRoster(new RosterDetails(emiscode, visittype, schoolname, formid, monthofdow));

                                }
                            }
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block

                            e.printStackTrace();
                        }
                    }
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
            final String monitorid = username;
            final String month = currentmonth;
            final String year = currentyear;
            final String appver = version;
            Log.v("SuccesS", "Response: " + s);
            new PostDataAsyncTaskSchoolInformation(monitorid, month, year).execute();

        }

    }

    public class PostDataAsyncTaskSchoolInformation extends AsyncTask<String, String, String> {
        String jsonStr;
        String loginId = username;
        String month = currentmonth;
        String year = currentyear;
        private String data, data1, data2;
        private String url = Globals.urlGetSchoolInfoTask + loginId + "/" + month + "/" + year;

        public PostDataAsyncTaskSchoolInformation(String textone, String texttwo, String textthree) {
            data = textone;
            data1 = texttwo;
            data2 = textthree;

        }

        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... strings) {
            ServiceHandler sh = new ServiceHandler();
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
                                Gender, Level, CicleSchoolCode, District, Tehsil, UC_Name,
                                Location, WINTERSUMMER, Na_No, Pk_No, CircleOfficeName, NameOfADO, ADOContactNo));


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
            final String monitorid = username;
            final String month = currentmonth;
            final String year = currentyear;
            new PostDataAsyncTaskGetGcsTeacher(monitorid, month, year).execute();
            //GetSubject task = new GetSubject();
            //task.execute();
        }
    }

    public class PostDataAsyncTaskGetGcsTeacher extends AsyncTask<String, String, String> {
        String jsonStr;
        String loginId = username;
        String month = currentmonth;
        String year = currentyear;
        private String data, data1, data2;
        private String url = Globals.urlGCSTeacherInfo + loginId + "/" + month + "/" + year;

        public PostDataAsyncTaskGetGcsTeacher(String textone, String texttwo, String textthree) {
            data = textone;
            data1 = texttwo;
            data2 = textthree;

        }

        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... strings) {
            ServiceHandler sh = new ServiceHandler();
            jsonStr = sh.makeServiceCall(url, ServiceHandler.POST);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    JSONArray jsonArray = jsonObj.getJSONArray("GetTeacherEEFResult");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jObject = jsonArray.getJSONObject(i);

                        String emiscode = jObject.getString("Emis_Code");
                        String PersonalNumber = jObject.getString("Contact_No");
                        String cnic = jObject.getString("CNIC");
                        String gender = jObject.getString("gender");
                        String teacherName = jObject.getString("TeacherName");
                        databaseHandler.savegcsteachers(new GcsTeacherDetails(emiscode, teacherName,
                                gender, PersonalNumber, cnic, "", "", "",
                                "", ""));
                    }
                    Log.d("GCS", jsonStr);
                } catch (Exception e) {
                    Log.d("Error", e.getMessage().toString());
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            GetSubject task = new GetSubject();
            task.execute();
        }
    }

    public class SortBasedOnName implements Comparator {
        public int compare(Object o1, Object o2) {

            RosterDetails dd1 = (RosterDetails) o1;// where FBFriends_Obj is your object class
            RosterDetails dd2 = (RosterDetails) o2;
            return dd1.getEmis().compareToIgnoreCase(dd2.getEmis());//where uname is field name
        }

    }

    public class GetScreenConfig extends AsyncTask<String, String, String> {
        String jsonStr;
        private String data, data1, data2;
        private String url = Globals.urlScreenConfig + currentmonth + "/" + currentyear;

        public GetScreenConfig(String textone, String texttwo) {
            data = textone;
            data1 = texttwo;

        }

        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... strings) {
            ServiceHandler sh = new ServiceHandler();
            jsonStr = sh.makeServiceCall(url, ServiceHandler.POST);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    JSONArray jsonArray = jsonObj.getJSONArray("GetScreenConfigurationResult");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jObject = jsonArray.getJSONObject(i);

                        String An_DisabledStudent = jObject.getString("An_DisabledStudent");
                        String An_EnrollmentByAge = jObject.getString("An_EnrollmentByAge");
                        String An_EnrollmentByGroup = jObject.getString("An_EnrollmentByGroup");
                        String An_FTB = jObject.getString("An_FTB");
                        String An_Furniture = jObject.getString("An_Furniture");
                        String An_Indicator = jObject.getString("An_Indicator");
                        String An_SantionedPosts = jObject.getString("An_SantionedPosts");
                        String An_SecurityMeasures = jObject.getString("An_SecurityMeasures");
                        String Bi_BuildingCondition = jObject.getString("Bi_BuildingCondition");
                        String Bi_Cleanliness = jObject.getString("Bi_Cleanliness");
                        String Bi_Commodities = jObject.getString("Bi_Commodities");
                        String Bi_Guides = jObject.getString("Bi_Guides");
                        String Bi_ITLab = jObject.getString("Bi_ITLab");
                        String Bi_Infrastructure = jObject.getString("Bi_Infrastructure");
                        String Bi_NatureOfConstruction = jObject.getString("Bi_NatureOfConstruction");
                        String Bi_PTC = jObject.getString("Bi_PTC");
                        String Bi_SchoolArea = jObject.getString("Bi_SchoolArea");
                        String Bi_Stipend = jObject.getString("Bi_Stipend");
                        String SchoolInformation = jObject.getString("SchoolInformation");
                        databaseHandler.saveScreenConfig(new ScreenConfig(An_DisabledStudent, An_EnrollmentByAge,
                                An_EnrollmentByGroup, An_FTB, An_Furniture, An_Indicator,
                                An_SantionedPosts, An_SecurityMeasures,
                                Bi_BuildingCondition, Bi_Cleanliness, Bi_Commodities, Bi_Guides,
                                Bi_ITLab, Bi_Infrastructure, Bi_NatureOfConstruction, "True",
                                Bi_SchoolArea,Bi_Stipend ,SchoolInformation));

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
            GetTeacherInfo task = new GetTeacherInfo();
            task.execute();
        }
    }

    public class GetTeacherInfo extends AsyncTask<Void, Void, Void> {
        DetailsTeacherwebservice teacherInfo;
        String jsonStr;

        @Override
        protected void onPostExecute(Void result) {
            GetNonTeacherInfo task = new GetNonTeacherInfo();
            task.execute();
            super.onPostExecute(result);
        }

        @Override
        protected void onPreExecute() {
            //teacherList = new ArrayList<TeacherInfo>();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {

            ServiceHandler sh = new ServiceHandler();
            ArrayList<org.apache.http.NameValuePair> pairs = new ArrayList<org.apache.http.NameValuePair>();
            pairs.add(new org.apache.http.message.BasicNameValuePair("monitorLoginName", username));
            pairs.add(new org.apache.http.message.BasicNameValuePair("month", currentmonth));
            pairs.add(new org.apache.http.message.BasicNameValuePair("year", currentyear + ""));
            jsonStr = sh.makeServiceCall(Globals.urlGetTeacherInfo, ServiceHandler.POST, pairs);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    JSONArray jsonArray = jsonObj.getJSONArray("resultDesc");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        teacherInfo = new DetailsTeacherwebservice();
                        JSONObject jObject = jsonArray.getJSONObject(i);
                        String emiscode = jObject.getString("Emis_Code");
                        String PersonalNumber = jObject
                                .getString("Personal_No");
                        String cnic = jObject.getString("CNIC");
                        String designation = jObject.getString("designation");
                        String teacherName = jObject.getString("TeacherName");

                        databaseHandler.saveteacherwebservice(new DetailsTeacherwebservice
                                (emiscode,teacherName,designation,PersonalNumber,cnic,"","","",""));
                    }

                    Log.d("Response", jsonStr);
                } catch (Exception e) {
                    Log.d("Error", e.getMessage().toString());
                }
            }
            return null;
        }

    }

    public class GetNonTeacherInfo extends AsyncTask<Void, Void, Void> {
        DetailsNonTeacherwebservice teacherInfo;
        String jsonStr;

        @Override
        protected void onPostExecute(Void result) {
            getGrant();
            super.onPostExecute(result);
        }

        @Override
        protected void onPreExecute() {
            //teacherList = new ArrayList<TeacherInfo>();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {

            ServiceHandler sh = new ServiceHandler();
            ArrayList<org.apache.http.NameValuePair> pairs = new ArrayList<org.apache.http.NameValuePair>();
            pairs.add(new org.apache.http.message.BasicNameValuePair("monitorLoginName", username));
            pairs.add(new org.apache.http.message.BasicNameValuePair("month", currentmonth));
            pairs.add(new org.apache.http.message.BasicNameValuePair("year", currentyear + ""));
            jsonStr = sh.makeServiceCall(Globals.urlGetNonTeacherInfo, ServiceHandler.POST, pairs);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    JSONArray jsonArray = jsonObj.getJSONArray("resultDesc");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        teacherInfo = new DetailsNonTeacherwebservice();
                        JSONObject jObject = jsonArray.getJSONObject(i);
                        String emiscode = jObject.getString("Emis_Code");
                        String PersonalNumber = jObject
                                .getString("Personal_No");
                        String cnic = jObject.getString("CNIC");
                        String designation = jObject.getString("designation");
                        String teacherName = jObject.getString("TeacherName");

                        databaseHandler.saveNonteacherwebservice(new DetailsNonTeacherwebservice
                                (emiscode,teacherName,designation,PersonalNumber,cnic,"","","",""));
                    }

                    Log.d("Response", jsonStr);
                } catch (Exception e) {
                    Log.d("Error", e.getMessage().toString());
                }
            }
            return null;
        }

    }

    public class GetSubject extends AsyncTask<Void, Void, Void> {
        SubjectModel subjectModel;
        String jsonStr;

        @Override
        protected void onPreExecute() {
            subjectModelList = new ArrayList<SubjectModel>();
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            pDialog.setProgress(90);
        }

        @Override
        protected void onPostExecute(Void result) {

            GetScreenConfig task = new GetScreenConfig(currentmonth,currentyear);
            task.execute();
            super.onPostExecute(result);
        }

        @Override
        protected Void doInBackground(Void... params) {
            ServiceHandler sh = new ServiceHandler();
            jsonStr = sh.makeServiceCall(Globals.urlSubject, ServiceHandler.GET);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    JSONArray jsonArray = jsonObj.getJSONArray("resultDesc");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        subjectModel = new SubjectModel();
                        JSONObject jObject = jsonArray.getJSONObject(i);
                        String Subjects = jObject.getString("Subjects");
                        String ClassName = jObject.getString("Class");

                        subjectModel.setClassName(ClassName);
                        subjectModel.setSubjectName(Subjects);
                        subjectModelList.add(subjectModel);
                        databaseHandler.createSubjectConfig(subjectModel);
                    }

                    Log.d("ResponseForSubjeccts", jsonStr);
                } catch (Exception e) {
                    Log.d("Error", e.getMessage().toString());
                }
            }
            return null;
        }

    }
}

