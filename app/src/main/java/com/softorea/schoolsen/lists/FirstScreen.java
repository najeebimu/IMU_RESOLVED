package com.softorea.schoolsen.lists;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.softorea.schoolsen.R;
import com.softorea.schoolsen.adapters.Monitor;
import com.softorea.schoolsen.databasehelper.DatabaseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Softorea on 10/20/2017.
 */

public class FirstScreen extends Activity {

    public static final String MyPREFERENCES = "MyPrefs";
    final int REQUEST_READ_PHONE_STATE = 0;
    Button update;
    EditText userName;
    Context context = FirstScreen.this;
    DatabaseHandler db;
    SharedPreferences prefs;
    ArrayList<Monitor> monitorList;
    Monitor monitor;
    String loginName;
    String IMEI_Number;
    private ProgressDialog pDialog;
    private long storeIndb = -2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstscreen);
        checkRunTimePermissions();
        prefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        db = new DatabaseHandler(context);
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        update = (Button) findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                makeJsonArrayRequest();
            }
        });
    }

    @Override
    public void onDestroy() {
        try {
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.dismiss();
                pDialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_READ_PHONE_STATE:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    TelephonyManager telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
                    IMEI_Number = telephonyManager.getDeviceId();
                }
                break;

            default:
                break;
        }
    }

	/* Make for request to get profile */

    private void makeJsonArrayRequest() {
        final String URL = Globals.Profile + "&imei1=" + IMEI_Number + "&imei2=" + IMEI_Number;
        showpDialog();
        monitorList = new ArrayList<Monitor>();
        JsonObjectRequest req = new JsonObjectRequest(URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int resultCode = response.getInt("resultCode");
                            if (resultCode == 1) {
                                JSONArray resultDesp = response
                                        .getJSONArray("resultDesc");
                                if (resultDesp.length() > 0) {
                                    for (int i = 0; i < resultDesp.length(); i++) {
                                        monitor = new Monitor();
                                        JSONObject person = (JSONObject) resultDesp.get(i);
                                        String status = person.getString("Status");
                                        String imei1 = person.getString("IMEI1");
                                        loginName = person.getString("LoginName");
                                        String imei2 = person.getString("IMEI2");
                                        String email = person.getString("Email");
                                        String recoveryCode = person
                                                .getString("RecoveryCode");
                                        String sex = person.getString("Sex");
                                        String roleid = person.getString("Role_id");
                                        String name = person.getString("Name");
                                        String contactNo = person
                                                .getString("ContactNo");
                                        String education = person
                                                .getString("Education");
                                        String cnic = person.getString("CNIC");
                                        String id = person.getString("ID");
                                        String session = person
                                                .getString("SessionID");
                                        String Age = person.getString("Age");
                                        String password = person
                                                .getString("Password");

                                        monitor.setStatus(status);
                                        monitor.setiMEI1(imei1);
                                        monitor.setLoginName(loginName);
                                        monitor.setiMEI2(imei2);
                                        monitor.setEmail(email);
                                        monitor.setRecoveryCode(recoveryCode);
                                        monitor.setSex(sex);
                                        monitor.setRoleId(roleid);
                                        monitor.setName(name);
                                        monitor.setContactNo(contactNo);
                                        monitor.setEducation(education);
                                        monitor.setCnic(cnic);
                                        monitor.setId(id);
                                        monitor.setSession(session);
                                        monitor.setAge(Age);
                                        monitor.setPassword(password);
                                        storeIndb = db.createMonitor(monitor);
                                    }
                                } else {
                                    Toast.makeText(context, "No monitor found please contact to your dmo ", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            hidepDialog();
                        }
                        hidepDialog();
                        if (storeIndb > -1) {
                            SharedPreferences.Editor edit = prefs.edit();
                            edit.putString("username", loginName);
                            edit.commit();
                            hidepDialog();
                            Intent intent = new Intent(FirstScreen.this, SettingActivity.class);

                            startActivity(intent);
                            finish();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        });
        req.setShouldCache(false);
        AppController.getInstance().addToRequestQueue(req);
    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    private void checkRunTimePermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
        } else {
            TelephonyManager telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
            IMEI_Number = telephonyManager.getDeviceId();
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_NETWORK_STATE}, 1);
        }


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
        }
    }

    public interface PostCommentResponseListener {
        public void requestStarted();

        public void requestCompleted();

        public void requestEndedWithError(VolleyError error);
    }
}
