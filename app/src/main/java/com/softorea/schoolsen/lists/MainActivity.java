package com.softorea.schoolsen.lists;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.adapters.Monitor;
import com.softorea.schoolsen.databasehelper.DatabaseHandler;

public class MainActivity extends Activity {

    DatabaseHandler db;
    String monitorId;
    String monitorUserName;
    String monitorPassword;
    Monitor monitor;
    String username;
    String firstTime;
    EditText passwordEdit;
    TextView wrongpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
        wrongpass = (TextView) findViewById(R.id.invalid);
        Button changeBtn = (Button) findViewById(R.id.btn_change);
        LinearLayout loginButton = (LinearLayout) findViewById(R.id.login_btn);
        Button Update = (Button) findViewById(R.id.update_btn);
        SharedPreferences prefs = getSharedPreferences(Globals.MyPREFERENCES,
                Context.MODE_PRIVATE);
        if (getIntent().getBooleanExtra("EXITT", false)) {

        }
        //DynamicPermissionCheck();
        username = prefs.getString("username", null);
        firstTime = prefs.getString("firsttime", null);

        EditText userEdit = (EditText) findViewById(R.id.username_edit);
        userEdit.clearFocus();
        passwordEdit = (EditText) findViewById(R.id.password_edit);
        passwordEdit.setFocusable(true);
        if (username != null) {
            userEdit.setText(username);
        }

        changeBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,
                        SettingActivity.class);
                startActivity(intent);

            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String password = passwordEdit.getText().toString();
                Monitor monitor = new Monitor();
                db = new DatabaseHandler(getApplicationContext());
                monitor = db.getMonitor(username);
                String dbusername = monitor.getLoginName();
                String dbpassword = monitor.getPassword();
                if (password.equals(""))
                {
                    Toast.makeText(MainActivity.this, "Password cannot be Empty!", Toast.LENGTH_SHORT).show();
                }
                else if (dbusername.equals(username) && dbpassword.equals(password)) {
                    if (firstTime == null) {
                        Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                        startActivity(intent);
                    } else {

                        Intent intent = new Intent(MainActivity.this, MainScreen.class);
                        passwordEdit.setText("");
                        startActivity(intent);
                        finish();

                    }
                } else {
                    wrongpass.setVisibility(View.VISIBLE);
                    //Toast.makeText(getApplicationContext(), "LoginFailed", Toast.LENGTH_LONG).show();
                    passwordEdit.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.wrongpas, 0);
                }

            }
        });

        Update.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


            }
        });


    }

    private void DynamicPermissionCheck() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_NETWORK_STATE}, 1);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
        }
    }
}
