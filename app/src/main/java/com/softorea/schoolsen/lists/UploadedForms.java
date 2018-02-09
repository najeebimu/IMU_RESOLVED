package com.softorea.schoolsen.lists;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.softorea.schoolsen.R;

import java.io.File;

/**
 * Created by Softorea on 10/26/2017.
 */

public class UploadedForms extends Activity {
    String path;
    ListView listView;
    TextView emptyt;
    ArrayAdapter<String> adapters;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uploadedforms);
        listView = (ListView) findViewById(R.id.uploadedlist);
        emptyt = (TextView) findViewById(R.id.empty);
        path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/IMU/uploaded";
        File dir = new File(path);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                Log.e("TravellerLog :: ",
                        "Problem creating Image folder");

            }
        }
        File[] filelist = dir.listFiles();
        String[] theNamesOfFiles = new String[filelist.length];
        for (int i = 0; i < theNamesOfFiles.length; i++) {
            if (theNamesOfFiles != null) {
                theNamesOfFiles[i] = filelist[i].getName().split("_")[0];
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
                String s = selectedFromList.substring(selectedFromList.lastIndexOf("_") + 1);

            }
        });
    }
}
