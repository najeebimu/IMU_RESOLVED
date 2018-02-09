package com.softorea.schoolsen.lists;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.softorea.schoolsen.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
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
 * Created by Softorea on 10/26/2017.
 */

public class FinalizedForms extends Activity {
    private static final String TAG = "MainActivity";
    String uploadedschools = "http://175.107.63.45:18080/IMU_WebService/IMU_Servlet?_f=updateUploadSchools_v1";
    String savedschools = "http://175.107.63.45:18080/IMU_WebService/IMU_Servlet?_f=updateUploadSchools_v1";
    String absPath, absPath1, absPath2, absPath3, absPath4,absEthic, s1, s2, s3, s4, s5,s6Ethic;
    TextView emptyt;
    String path;
    String key = "67E5DE12EC86119F";
    ListView listView;
    ArrayAdapter<String> adapters;
    String username;
    String edtHostName, edtUserName, edtPassword;
    // private static final String TEMP_FILENAME = "17438_Softorea.test_26-10-2017_10:31:03_18123.xml";
    private Context cntx = null;
    private FTPClass ftpclient = null;
    private Button btnLoginFtp, btnUploadFile;
    private ProgressDialog pd;

    private String[] fileList;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {

        public void handleMessage(android.os.Message msg) {

            if (pd != null && pd.isShowing()) {
                pd.dismiss();
            }
            if (msg.what == 0) {
                getFTPFileList();
            } else if (msg.what == 1) {
                //showCustomDialog(fileList);
            } else if (msg.what == 2) {
                Toast.makeText(FinalizedForms.this, "Uploaded Successfully!",
                        Toast.LENGTH_LONG).show();
            } else if (msg.what == 3) {
                Toast.makeText(FinalizedForms.this, "Disconnected Successfully!",
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(FinalizedForms.this, "Unable to Perform Action!",
                        Toast.LENGTH_LONG).show();
            }

        }

    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finalizedforms);
        cntx = this.getBaseContext();

        SharedPreferences prefs = getSharedPreferences(Globals.MyPREFERENCES, Context.MODE_PRIVATE);
        username = prefs.getString("username", null);

        emptyt = (TextView) findViewById(R.id.empty);
        edtHostName = "175.107.63.45";
        edtUserName = "SurveyFTP";
        edtPassword = "SuRvEyFtPNoMoreABC@12436";


//        createDummyFile();

        ftpclient = new FTPClass();
        listView = (ListView) findViewById(R.id.formlist);
        path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/IMU/data";
        File dir = new File(path);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                Log.e("TravellerLog :: ",
                        "Problem creating Image folder");

            }
        }
        final File[] filelist = dir.listFiles();

        String[] theNamesOfFiles = new String[filelist.length];
        for (int i = 0; i < theNamesOfFiles.length; i++) {
            if (theNamesOfFiles != null) {
                theNamesOfFiles[i] = filelist[i].getName();
            }
        }

        adapters = new ArrayAdapter<String>(this, R.layout.customm, R.id.simpletes, theNamesOfFiles);
        if (adapters.getCount() != 0) {
            listView.setAdapter(adapters);
            emptyt.setVisibility(View.GONE);
        } else {
            emptyt.setVisibility(View.VISIBLE);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String selectedFromList = (String) listView.getItemAtPosition(position);
                final String s = selectedFromList.substring(selectedFromList.lastIndexOf("_") + 1);
                final String finalfile = s.substring(0, s.lastIndexOf("."));

                String[] parts = selectedFromList.split("_");
                final String emiscode = parts[0];

                String p = Environment.getExternalStorageDirectory().getAbsolutePath() + "/IMU/sign";

                File dir = new File(p);
                final File[] filelistt = dir.listFiles();
                if (filelistt != null) {
                    for (File f : filelistt) {
                        if (f.getName().contains(emiscode) && f.getName().contains("monitorsign")) {
                            absPath = f.getAbsolutePath();
                            s1 = absPath.substring(absPath.lastIndexOf("/") + 1);
                            //Toast.makeText(FinalizedForms.this, s1, Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                String p1 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/IMU/sign";
                File dirr = new File(p1);
                final File[] filelisttt = dirr.listFiles();
                if (filelisttt != null) {
                    for (File f : filelisttt) {
                        if (f.getName().contains(emiscode) && f.getName().contains("headsign")) {
                            absPath1 = f.getAbsolutePath();
                            s2 = absPath1.substring(absPath1.lastIndexOf("/") + 1);
                        }
                    }
                }

                String p2 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/IMU/pics";
                File d = new File(p2);
                final File[] filel = d.listFiles();
                if (filel != null) {
                    for (File f : filel) {
                        if (f.getName().contains(emiscode) && f.getName().contains("status")) {
                            absPath2 = f.getAbsolutePath();
                            s3 = absPath2.substring(absPath2.lastIndexOf("/") + 1);
                        }
                    }
                }

                String facilty1 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/IMU/pics";
                File dfacility = new File(facilty1);
                final File[] filelfac = dfacility.listFiles();
                if (filelfac != null) {
                    for (File f : filelfac) {
                        if (f.getName().contains(emiscode) && f.getName().contains("facility1")) {
                            absPath3 = f.getAbsolutePath();
                            s4 = absPath3.substring(absPath3.lastIndexOf("/") + 1);
                        }
                    }
                }

                String facilty2 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/IMU/pics";
                File dfacility2 = new File(facilty2);
                final File[] filelfac2 = dfacility2.listFiles();
                if (filelfac2 != null) {
                    for (File f : filelfac2) {
                        if (f.getName().contains(emiscode) && f.getName().contains("facility2")) {
                            absPath4 = f.getAbsolutePath();
                            s5 = absPath4.substring(absPath4.lastIndexOf("/") + 1);
                        }
                    }
                }

                String abs6 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/IMU/pics";
                File dethic = new File(abs6);
                final File[] filelethic = dethic.listFiles();
                if (filelethic != null) {
                    for (File f : filelethic) {
                        if (f.getName().contains(emiscode) && f.getName().contains("ethicactivity")) {
                            absEthic = f.getAbsolutePath();
                            s6Ethic = absEthic.substring(absEthic.lastIndexOf("/") + 1);
                        }
                    }
                }
                //if (s.lastIndexOf(".") > 0)
                //  s = s.substring(0, s.lastIndexOf("."));
                //Toast.makeText(cntx, s, Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(FinalizedForms.this);
                builder.setMessage("Upload form?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {

                                connectToFTPAddress();

                                final String finalS = s;
                                new Handler().postDelayed(new Runnable() {

                                    @Override
                                    public void run() {

                                        pd = ProgressDialog.show(FinalizedForms.this, "", "Uploading..",
                                                true, false);
                                        new Thread(new Runnable() {
                                            public void run() {
                                                boolean status = false;
                                                File decryptfile = new File(
                                                        Environment.getExternalStorageDirectory()
                                                                + "/IMU/data/" + selectedFromList);

                                                try {
                                                    byte[] encryptedText = CryptoUtils.decrypt(key, decryptfile);
                                                    //decryptfile.createNewFile();

                                                    FileOutputStream fileStream = new FileOutputStream(decryptfile);
                                                    fileStream.write(encryptedText);
                                                    //decryptfile.delete();
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                                status = ftpclient.ftpUpload(
                                                        Environment.getExternalStorageDirectory()
                                                                + "/IMU/data/" + selectedFromList, selectedFromList, "/", cntx);
                                                try {
                                                    ftpclient.ftpUpload(absPath, s1, "/", cntx);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }

                                                try {
                                                    ftpclient.ftpUpload(absPath2, s3, "/", cntx);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }

                                                try {
                                                    ftpclient.ftpUpload(absPath1, s2, "/", cntx);

                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                                try {
                                                    ftpclient.ftpUpload(absPath3, s4, "/", cntx);

                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                                try {
                                                    ftpclient.ftpUpload(absPath4, s5, "/", cntx);

                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }

                                                try {
                                                    ftpclient.ftpUpload(absEthic, s6Ethic, "/", cntx);

                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                                /*try {
                                                    ftpclient.ftpUpload(
                                                            Environment.getExternalStorageDirectory()
                                                                    + "/IMU/pics/"+ finalfile +"_" + username+"_" + "facility1.png", finalfile +"_" + username+"_" + "facility1.png","/", cntx);
                                                }
                                                catch(Exception e)
                                                {
                                                    e.printStackTrace();
                                                }
                                                try {
                                                    ftpclient.ftpUpload(
                                                            Environment.getExternalStorageDirectory()
                                                                    + "/IMU/pics/" + finalfile + "_" + username + "_" + "facility2.png", finalfile + "_" + username + "_" + "facility2.png", "/", cntx);
                                                }
                                                catch(Exception e)
                                                {
                                                    e.printStackTrace();
                                                }*/
                                                if (status == true) {
                                                    Log.d(TAG, "Upload success");
                                                    handler.sendEmptyMessage(2);
                                                    new PostDataAsyncTaskq(finalS).execute();
                                                    String source = Environment.getExternalStorageDirectory().getAbsolutePath() + "/IMU/data/";
                                                    String dest = Environment.getExternalStorageDirectory().getAbsolutePath() + "/IMU/uploaded/";
                                                    copyFile(source, selectedFromList, dest);
                                                    //File source = new File(Environment.getExternalStorageDirectory(), "/IMU/data/" + selectedFromList);
                                                    //File dest = new File(Environment.getExternalStorageDirectory(), "/IMU/uploaded");
                                                    //copyFile("/IMU/data/", s, "/IMU/uploaded");

                                                    File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                                                            + "/IMU/data/" + selectedFromList);
                                                    file.delete();
                                                    finish();
                                                    startActivity(new Intent(FinalizedForms.this, FinalizedForms.class));
                                    /*File mydir = new File(Environment.getExternalStorageDirectory() + "/uploaded/");
                                    if(!mydir.exists())
                                        mydir.mkdirs();
                                    else
                                        Log.d("error", "dir. already exists");*/
                                                } else {
                                                    Log.d(TAG, "Upload failed");
                                                    handler.sendEmptyMessage(-1);
                                                }
                                            }

                                        }).start();


                                    }
                                }, 2 * 1000);

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                                dialog.dismiss();
                            }

                            public void close() {

                                finish();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });


    }

    private void connectToFTPAddress() {

        //pd = ProgressDialog.show(FinalizedForms.this, "", "Connecting...",
        //      true, false);

        new Thread(new Runnable() {
            public void run() {
                boolean status = false;
                status = ftpclient.ftpConnect(edtHostName, edtUserName, edtPassword, 21);
                if (status == true) {
                    Log.d(TAG, "Connection Success");
                    handler.sendEmptyMessage(0);
                } else {
                    Log.d(TAG, "Connection failed");
                    handler.sendEmptyMessage(-1);
                }
            }
        }).start();

    }

    private void copyFile(String inputPath, String inputFile, String outputPath) {

        InputStream in = null;
        OutputStream out = null;
        try {

            //create output directory if it doesn't exist
            File dir = new File(outputPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }


            in = new FileInputStream(inputPath + inputFile);
            out = new FileOutputStream(outputPath + inputFile);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            in = null;

            // write the output file (You have now copied the file)
            out.flush();
            out.close();
            out = null;

        } catch (FileNotFoundException fnfe1) {
            Log.e("tag", fnfe1.getMessage());
        } catch (Exception e) {
            Log.e("tag", e.getMessage());
        }

    }

    private void getFTPFileList() {
       /* pd = ProgressDialog.show(FinalizedForms.this, "", "Getting Files...",
                true, false);

        new Thread(new Runnable() {

            @Override
            public void run() {
                fileList = ftpclient.ftpPrintFilesList("/");
                handler.sendEmptyMessage(1);
            }
        }).start();*/
    }

    /*public void createDummyFile() {

        try {
            File root = new File(Environment.getExternalStorageDirectory(),
                    "TAGFtp");
            if (!root.exists()) {
                root.mkdirs();
            }
            File gpxfile = new File(root, TEMP_FILENAME);
            FileWriter writer = new FileWriter(gpxfile);
            writer.append("");
            writer.flush();
            writer.close();
            Toast.makeText(this, "", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    private boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            return true;
        }
        return false;
    }

   /* private void showCustomDialog(String[] fileList) {
        // custom dialog
        final Dialog dialog = new Dialog(FinalizedForms.this);
        dialog.setContentView(R.layout.ftpviewere);
        dialog.setTitle("/ Directory File List");

        TextView tvHeading = (TextView) dialog.findViewById(R.id.tvListHeading);
        tvHeading.setText(":: File List ::");

        if (fileList != null && fileList.length > 0) {
            ListView listView = (ListView) dialog
                    .findViewById(R.id.lstItemList);
            ArrayAdapter<String> fileListAdapter = new ArrayAdapter<String>(
                    this, android.R.layout.simple_list_item_1, fileList);
            listView.setAdapter(fileListAdapter);
        } else {
            tvHeading.setText(":: No Files ::");
        }

        Button dialogButton = (Button) dialog.findViewById(R.id.btnOK);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }*/

    public class PostDataAsyncTask extends AsyncTask<String, String, String> {
        private String data;

        public PostDataAsyncTask(String textone) {
            data = textone;


        }

        protected void onPreExecute() {
            super.onPreExecute();
            //showpDialog();

        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                String postReceiverUrl = uploadedschools;
                HttpClient httpClient = new DefaultHttpClient();

                HttpPost httpPost = new HttpPost(postReceiverUrl);

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                nameValuePairs.add(new BasicNameValuePair("id", data));
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = httpClient.execute(httpPost);
                HttpEntity resEntity = response.getEntity();

                if (resEntity != null) {

                    String responseStr = EntityUtils.toString(resEntity).trim();
                    JSONObject json = new JSONObject(responseStr);
                    JSONArray ja_data = json.getJSONArray("resultDesc");
                    if (json.getJSONArray("resultDesc").length() == 0) {
                        Log.d("Data", "No Data Found");

                    } else {
                        try {

                        } catch (Exception e) {
                            Log.d("Error", e.getMessage().toString());
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
            //hidepDialog();
            Log.v("SuccesS", "Response: " + s);
            //finish();
            //startActivity(new Intent(Roster_List.this,Roster_List.class))

        }

    }

    public class PostDataAsyncTaskq extends AsyncTask<String, String, String> {
        private String data;

        public PostDataAsyncTaskq(String textone) {
            data = textone;


        }

        protected void onPreExecute() {
            super.onPreExecute();
            //showpDialog();

        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                String postReceiverUrl = uploadedschools;
                HttpClient httpClient = new DefaultHttpClient();

                HttpPost httpPost = new HttpPost(postReceiverUrl);

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                nameValuePairs.add(new BasicNameValuePair("id", data));
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = httpClient.execute(httpPost);
                HttpEntity resEntity = response.getEntity();

                if (resEntity != null) {

                    String responseStr = EntityUtils.toString(resEntity).trim();
                    JSONObject json = new JSONObject(responseStr);
                    JSONArray ja_data = json.getJSONArray("resultDesc");
                    if (json.getJSONArray("resultDesc").length() == 0) {
                        Log.d("Data", "No Data Found");

                    } else {
                        try {

                        } catch (Exception e) {
                            Log.d("Error", e.getMessage().toString());
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
            //hidepDialog();
            Log.v("SuccesS", "Response: " + s);
            //finish();
            //startActivity(new Intent(Roster_List.this,Roster_List.class))

        }

    }


}
