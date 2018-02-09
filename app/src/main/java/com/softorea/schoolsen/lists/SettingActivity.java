package com.softorea.schoolsen.lists;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.adapters.Monitor;
import com.softorea.schoolsen.databasehelper.DatabaseHandler;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class SettingActivity extends Activity {
    public static final String MyPREFERENCES = "MyPrefs";
    private static final int SELECT_PICTURE = 1;
    int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    DatabaseHandler db;
    Monitor monitor;
    String username;
    String monitorId;
    String monitorUserName;
    String monitorPassword;
    String age;
    String sex;
    String education;
    String Name;
    String cnic;
    String email;
    String Phone;
    String gender = "null";
    Button imageLoad;
    ImageView ivImage;
    EditText nameEdit;
    EditText rolidEdit;
    EditText ageEdit;
    EditText sexEdit;
    EditText educationEdit;
    EditText cnicEdit;
    EditText passwordEdit;
    EditText phoneEdit;
    EditText emailEdit;
    EditText usernameedit;
    SharedPreferences prefs;
    Context context;
    Monitor monitorProfile;
    Monitor profile;
    RadioGroup genderGroup;
    RadioButton rdMale;
    RadioButton rdFemale;
    int genderCheck = 0;
    private String selectedImagePath;
    private ProgressDialog pDialog;

    /*
     * Check if internet is available */
    public static boolean haveInternet(Context ctx) {

        NetworkInfo info = (NetworkInfo) ((ConnectivityManager) ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE))
                .getActiveNetworkInfo();

        if (info == null || !info.isConnected()) {
            return false;
        }
        if (info.isRoaming()) {
            return false;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        context = SettingActivity.this;
        db = new DatabaseHandler(getApplicationContext());
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        usernameedit = (EditText) findViewById(R.id.username_edit);
        nameEdit = (EditText) findViewById(R.id.name_edit);
        ageEdit = (EditText) findViewById(R.id.age_edit);
        sexEdit = (EditText) findViewById(R.id.sex_edit);
        educationEdit = (EditText) findViewById(R.id.education_edit);
        cnicEdit = (EditText) findViewById(R.id.cnic_edit);
        passwordEdit = (EditText) findViewById(R.id.password_edit);
        emailEdit = (EditText) findViewById(R.id.email_edit);
        phoneEdit = (EditText) findViewById(R.id.Phone_edit);
        genderGroup = (RadioGroup) findViewById(R.id.gender_group);
        rdMale = (RadioButton) findViewById(R.id.rd_male);
        rdFemale = (RadioButton) findViewById(R.id.rd_female);
        genderGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rd_male) {
                    gender = "Male";
                    genderCheck = 1;
                } else if (checkedId == R.id.rd_female) {
                    gender = "Female";
                    genderCheck = 1;
                }

            }
        });

        ivImage = (ImageView) findViewById(R.id.ivimage);
        prefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        profile = new Monitor();
        username = prefs.getString("username", null);
        profile = db.getMonitor(username);

        if (!profile.getName().matches("")
                && !profile.getName().matches("null")) {
            nameEdit.setText(profile.getName());
        }
        if (!profile.getAge().matches("") && !profile.getAge().matches("null")) {
            ageEdit.setText(profile.getAge());
        }
        String gend = profile.getSex();

        if (!profile.getSex().toString().equals(null)) {
            if (!profile.getSex().equals("")
                    && !profile.getSex().equals("null")) {
                if (profile.getSex().equals("Male")) {
                    rdMale.setChecked(true);
                } else if (profile.getSex().equals("Female")) {
                    rdFemale.setChecked(true);
                }
            }
        }
        if (!profile.getEducation().matches("")
                && !profile.getEducation().matches("null")) {
            educationEdit.setText(profile.getEducation());
        }
        if (!profile.getCnic().matches("")
                && !profile.getCnic().matches("null")) {
            cnicEdit.setText(profile.getCnic());
        }
        if (!profile.getEmail().matches("")
                && !profile.getEmail().matches("null")) {
            emailEdit.setText(profile.getEmail());
        }
        if (!profile.getContactNo().matches("")
                && !profile.getContactNo().matches("null")) {
            phoneEdit.setText(profile.getContactNo());
        }
        if (!profile.getPassword().matches("")
                && !profile.getPassword().matches("null")) {
            passwordEdit.setText(profile.getPassword());
        }

        usernameedit.setText(username);
        String getPath = prefs.getString("path", null);
        if (getPath != null) {
            loadImageFromStorage(getPath);
        }

        imageLoad = (Button) findViewById(R.id.btnimage);

        imageLoad.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(
                        Intent.createChooser(intent, "Select Picture"),
                        SELECT_PICTURE);

            }
        });

        Button save = (Button) findViewById(R.id.save_btn);

        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // new Thread(new Task()).start();

                if (!passwordEdit.getText().toString().matches("")) {
                    if (phoneEdit.getText().toString().matches("")) {
                        Toast.makeText(context, "Mobile number is mandatory",
                                Toast.LENGTH_SHORT).show();
                    } else if (phoneEdit.getText().length() < 11) {
                        Toast.makeText(context,
                                "Please enter 11 digit mobile number",
                                Toast.LENGTH_SHORT).show();

                    } else if (cnicEdit.getText().toString().matches("")) {
                        Toast.makeText(context, "Cnic number is mandatory",
                                Toast.LENGTH_SHORT).show();
                    } else if (cnicEdit.getText().length() < 13) {
                        Toast.makeText(context,
                                "Please enter 13 digit cnic number",
                                Toast.LENGTH_SHORT).show();

                    } else if (nameEdit.getText().toString().matches("")) {
                        Toast.makeText(context, "Name is mandatory",
                                Toast.LENGTH_LONG).show();
                    } else if (genderCheck == 0) {
                        Toast.makeText(context, "Gender is mandatory",
                                Toast.LENGTH_LONG).show();
                    } else {

                        boolean isInternet = haveInternet(context);
                        if (isInternet) {
                            if (usernameedit.getText() != null
                                    && nameEdit.getText() != null
                                    && ageEdit.getText() != null
                                    && sexEdit.getText() != null
                                    && educationEdit.getText() != null
                                    && cnicEdit.getText() != null
                                    && passwordEdit.getText() != null
                                    && emailEdit.getText() != null
                                    && phoneEdit.getText() != null) {
                                username = usernameedit.getText().toString();
                                Name = nameEdit.getText().toString();
                                age = ageEdit.getText().toString();
                                sex = gender;
                                education = educationEdit.getText().toString();
                                cnic = cnicEdit.getText().toString();
                                monitorPassword = passwordEdit.getText()
                                        .toString();
                                email = emailEdit.getText().toString();
                                Phone = phoneEdit.getText().toString();
                                Monitor monitor = new Monitor();
                                monitor.setLoginName(username);
                                monitor.setName(Name);
                                monitor.setAge(age);
                                monitor.setSex(sex);
                                monitor.setEducation(education);
                                monitor.setCnic(cnic);
                                monitor.setPassword(monitorPassword);
                                monitor.setEmail(email);
                                monitor.setContactNo(Phone);
                                monitorProfile = new Monitor();

                                db.updateMonitor(monitor, username);
                                monitorProfile = db.getMonitor(username);


                                sendUserProfile task = new sendUserProfile();
                                task.execute();

                            }
                            username = usernameedit.getText().toString();
                            Bitmap bitmap = ((BitmapDrawable) ivImage
                                    .getDrawable()).getBitmap();
                            saveAsyntask task = new saveAsyntask();
                            task.execute(bitmap);
                            Editor edit = prefs.edit();
                            edit.putString("firsttime", "false");
                            edit.putString("Profileset", "1");
                            edit.commit();

                        } else {
                            Toast.makeText(context, "Internet is not availble",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                } else {
                    Toast.makeText(context, "Password is mandatory",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                selectedImagePath = getPath(selectedImageUri);
                System.out.println("Image Path : " + selectedImagePath);
                ivImage.setImageURI(selectedImageUri);

            }
        }
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    private void loadImageFromStorage(String path) {
        try {
            File f = new File(path, "profile.jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            ivImage.setImageBitmap(b);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


	/* Update profile on server */

    private String saveToInternalSorage(Bitmap bitmapImage) {
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("Obdc", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath = new File(directory, "profile.jpg");

        FileOutputStream fos = null;
        try {

            fos = new FileOutputStream(mypath);
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return directory.getAbsolutePath();
    }

    /* Save to internal storage */

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    public class sendUserProfile extends AsyncTask<Void, Void, String> {


        String jsonStr;
        Bitmap bitmap;
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            String stringToStore = "";
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            try {
                bitmap.compress(Bitmap.CompressFormat.PNG, 5, stream);
                int numBytesByRow = bitmap.getRowBytes() * bitmap.getHeight();
                int k = bitmap.getByteCount();
                byte[] byteArray = stream.toByteArray();
                stringToStore = Base64.encodeToString(byteArray, 0);

                bitmap = ((BitmapDrawable) ivImage.getDrawable())
                        .getBitmap();
            } catch (Exception e) {

            }
            pairs.add(new BasicNameValuePair("id", monitorProfile.getId()));
            pairs.add(new BasicNameValuePair("name", nameEdit.getText()
                    .toString()));
            pairs.add(new BasicNameValuePair("role_id", "1"));
            pairs.add(new BasicNameValuePair("age", ageEdit.getText()
                    .toString()));
            pairs.add(new BasicNameValuePair("picture", stringToStore));
            pairs.add(new BasicNameValuePair("sex", gender));
            pairs.add(new BasicNameValuePair("education", educationEdit
                    .getText().toString()));
            pairs.add(new BasicNameValuePair("imei1", monitorProfile.getiMEI1()));
            pairs.add(new BasicNameValuePair("imei2", monitorProfile.getiMEI2()));
            pairs.add(new BasicNameValuePair("status", monitorProfile
                    .getStatus()));
            pairs.add(new BasicNameValuePair("cnic", cnicEdit.getText()
                    .toString()));
            pairs.add(new BasicNameValuePair("loginname", monitorProfile
                    .getLoginName()));
            pairs.add(new BasicNameValuePair("password", monitorProfile
                    .getPassword()));
            pairs.add(new BasicNameValuePair("email", monitorProfile.getEmail()));

            pairs.add(new BasicNameValuePair("recoverycode", monitorProfile
                    .getRecoveryCode()));
            pairs.add(new BasicNameValuePair("contactno", phoneEdit.getText()
                    .toString()));
            String sess = monitorProfile.getSession().toString();
            pairs.add(new BasicNameValuePair("Sessionid", monitorProfile
                    .getSession().toString()));
        }

        @Override
        protected String doInBackground(Void... params) {

            ServiceHandler sh = new ServiceHandler();

            jsonStr = sh.makeServiceCall(Globals.Post_profile_url, ServiceHandler.POST, pairs);

            if (jsonStr != null) {
                try {
                    Log.d("Response", jsonStr);
                } catch (Exception e) {
                    Log.d("Error", e.getMessage().toString());
                }
            }
            return null;
        }

    }

    public class saveAsyntask extends AsyncTask<Bitmap, Void, String> {
        String path;
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            pd = new ProgressDialog(SettingActivity.this);
            pd.setMessage("loading");
            pd.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Bitmap... params) {
            path = saveToInternalSorage(params[0]);
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            Editor edit = prefs.edit();
            edit.putString("path", path);

            edit.commit();
            if (pd != null) {
                pd.dismiss();
            }
            Intent intent = new Intent(SettingActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            super.onPostExecute(result);
        }

    }

}
