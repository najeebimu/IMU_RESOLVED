package com.softorea.schoolsen.m_monthly;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.adapters.CustomAdapterProxyTeacher;
import com.softorea.schoolsen.databasehelper.DatabaseHandler;
import com.softorea.schoolsen.models.DetailsProxyTeacher;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Arslan on 10/2/2017.
 */

public class M_ProxyTeacherUpdate extends Activity {
    DetailsProxyTeacher details;
    String SLevel = "";
    EditText name, proxyforname, cnic, cnicfor, forpersonalno;
    Spinner designation;
    String idd;
    int identity;
    String cnicc, emiscodeee;

    ArrayList<DetailsProxyTeacher> addas = new ArrayList<DetailsProxyTeacher>();
    CustomAdapterProxyTeacher cusadapter;
    DatabaseHandler databasehandler;
    String emis;
    TextView timesince;
    Button add, clear;
    private ListView listcontent = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_proxy_teacherupdate);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        emis = preferences.getString("emiscode", "");
        databasehandler = new DatabaseHandler(M_ProxyTeacherUpdate.this);
        timesince = (TextView) findViewById(R.id.pteacherfor_timesince);
        name = (EditText) findViewById(R.id.pteacher_name);
        cnic = (EditText) findViewById(R.id.pteacher_cnic);
        cnicfor = (EditText) findViewById(R.id.pteacherfor_cnic);
        forpersonalno = (EditText) findViewById(R.id.pteacherfor_perno);
        proxyforname = (EditText) findViewById(R.id.pteacherfor_name);
        designation = (Spinner) findViewById(R.id.pteacherfor_designation);
        //listcontent = (ListView) findViewById(R.id.proxyteacherlist);
        clear = (Button) findViewById(R.id.clear);
        //SavingInDb();
        add = (Button) findViewById(R.id.add);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setText("");
                cnic.setText("");
                cnicfor.setText("");
                proxyforname.setText("");
                forpersonalno.setText("");
                designation.setSelection(0, true);
            }
        });
        final Calendar myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                timesince.setText(sdf.format(myCalendar.getTime()));
            }

        };

        timesince.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DatePickerDialog dialog = new DatePickerDialog(M_ProxyTeacherUpdate.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
                dialog.getDatePicker().setMaxDate(new Date().getTime());
                dialog.show();
            }
        });
        String[] BuildingIllegalSpinner = getResources().getStringArray(R.array.proxyteacherdesignation);
        ArrayAdapter<String> schoolLevelAdapter = new ArrayAdapter<String>(M_ProxyTeacherUpdate.this, android.R.layout.simple_spinner_dropdown_item, BuildingIllegalSpinner);
        designation.setAdapter(schoolLevelAdapter);
        designation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                SLevel = designation.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cnicId = cnic.getText().toString();
                String cnicIdfor = cnicfor.getText().toString();
                if (name.getText().toString().equals("") || proxyforname.getText().toString().equals("")) {
                    Toast.makeText(M_ProxyTeacherUpdate.this, "Please enter name and proxy name", Toast.LENGTH_SHORT).show();
                } else if (cnicId == null || cnicId.length() < 13 || cnicIdfor == null || cnicIdfor.length() < 13) {
                    Toast.makeText(M_ProxyTeacherUpdate.this, "Cnic is not valid", Toast.LENGTH_SHORT).show();

                } else {
                    //checkAlreadyExist(cnicId);
                    DetailsProxyTeacher schoolinfo = new DetailsProxyTeacher();
                    schoolinfo.setName(name.getText().toString());
                    schoolinfo.setCnic(cnic.getText().toString());
                    schoolinfo.setProxyName(proxyforname.getText().toString());
                    schoolinfo.setProxycnic(cnicfor.getText().toString());
                    schoolinfo.setProxyno(forpersonalno.getText().toString());
                    schoolinfo.setDesignation(SLevel);
                    schoolinfo.setProxytimeSince(timesince.getText().toString());
                    databasehandler.updateProxyTeacher(schoolinfo, emis, identity);
                    Toast.makeText(M_ProxyTeacherUpdate.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                    //finish();
                    startActivity(new Intent(M_ProxyTeacherUpdate.this, M_ProxyTeacherList.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                    /*DetailsProxyTeacher schoolinfo = new DetailsProxyTeacher();
                    schoolinfo.setName(name.getText().toString());
                    schoolinfo.setCnic(cnic.getText().toString());
                    schoolinfo.setProxyName(proxyforname.getText().toString());
                    schoolinfo.setProxycnic(cnicfor.getText().toString());
                    schoolinfo.setProxyno(forpersonalno.getText().toString());
                    schoolinfo.setDesignation(SLevel);
                    schoolinfo.setProxytimeSince(timesince.getText().toString());
                    databasehandler.updateProxyTeacher(schoolinfo,emis,identity);
                    Toast.makeText(M_ProxyTeacherUpdate.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                    //finish();
                    startActivity(new Intent(M_ProxyTeacherUpdate.this,M_ProxyTeacherList.class));
                    finish();*/

                    /*name.setText("");
                    cnic.setText("");
                    proxyforname.setText("");
                    cnicfor.setText("");
                    timesince.setText("");
                    forpersonalno.setText("");
                    designation.setSelection(0);
                    SLevel = designation.getSelectedItem().toString();*/

                }
            }

           /* public void checkAlreadyExist(String email)
            {
                SQLiteDatabase db = databasehandler.getReadableDatabase();
                Cursor c=db.rawQuery("SELECT * FROM t_proxyteacher WHERE cnic='"+email+"'", null);
                if (c.moveToFirst()&& !cnic.getText().toString().equals(cnicc))
                {
                    Toast.makeText(M_ProxyTeacherUpdate.this, "CNIC already exists!", Toast.LENGTH_SHORT).show();
                    //return false;
                }
                else
                {
                    DetailsProxyTeacher schoolinfo = new DetailsProxyTeacher();
                    schoolinfo.setName(name.getText().toString());
                    schoolinfo.setCnic(cnic.getText().toString());
                    schoolinfo.setProxyName(proxyforname.getText().toString());
                    schoolinfo.setProxycnic(cnicfor.getText().toString());
                    schoolinfo.setProxyno(forpersonalno.getText().toString());
                    schoolinfo.setDesignation(SLevel);
                    schoolinfo.setProxytimeSince(timesince.getText().toString());
                    databasehandler.updateProxyTeacher(schoolinfo,emis,identity);
                    Toast.makeText(M_ProxyTeacherUpdate.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                    //finish();
                    startActivity(new Intent(M_ProxyTeacherUpdate.this,M_ProxyTeacherList.class));
                    finish();
                    //return true;
                }
                    
            }*/
        });
    }


    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            identity = extras.getInt("ID");
            String namee = extras.getString("name");
            cnicc = extras.getString("cnic");
            emiscodeee = extras.getString("emiscode");
            String pname = extras.getString("pname");
            String pcnic = extras.getString("pcnic");
            String pno = extras.getString("ppno");
            String proxydesig = extras.getString("des");
            String timesincee = extras.getString("date");

            //Toast.makeText(this, "ID is:" + identity, Toast.LENGTH_SHORT).show();

            name.setText(namee);
            cnic.setText(cnicc);
            proxyforname.setText(pname);
            forpersonalno.setText(pno);
            cnicfor.setText(pcnic);
            timesince.setText(timesincee);

            if (proxydesig.equals("SET")) {
                designation.setSelection(32, true);
            } else if (proxydesig.equals("AT")) {
                designation.setSelection(1, true);
            } else if (proxydesig.equals("Chowkidar")) {
                designation.setSelection(2, true);
            } else if (proxydesig.equals("CT")) {
                designation.setSelection(3, true);
            } else if (proxydesig.equals("D.P.E")) {
                designation.setSelection(4, true);
            } else if (proxydesig.equals("DM")) {
                designation.setSelection(5, true);
            } else if (proxydesig.equals("Driver")) {
                designation.setSelection(6, true);
            } else if (proxydesig.equals("Head Master/Mistress")) {
                designation.setSelection(7, true);
            } else if (proxydesig.equals("Cook")) {
                designation.setSelection(8, true);
            } else if (proxydesig.equals("Caller")) {
                designation.setSelection(9, true);
            } else if (proxydesig.equals("Hostel Warden")) {
                designation.setSelection(10, true);
            } else if (proxydesig.equals("Work Shop Attendant")) {
                designation.setSelection(11, true);
            } else if (proxydesig.equals("IT Teacher")) {
                designation.setSelection(12, true);
            } else if (proxydesig.equals("J/Clerk")) {
                designation.setSelection(13, true);
            } else if (proxydesig.equals("Lab Assistant")) {
                designation.setSelection(14, true);
            } else if (proxydesig.equals("Lab Attendant")) {
                designation.setSelection(15, true);
            } else if (proxydesig.equals("Librarian")) {
                designation.setSelection(16, true);
            } else if (proxydesig.equals("Mali")) {
                designation.setSelection(17, true);
            } else if (proxydesig.equals("Naib Qasid")) {
                designation.setSelection(18, true);
            } else if (proxydesig.equals("PET")) {
                designation.setSelection(19, true);
            } else if (proxydesig.equals("Principal")) {
                designation.setSelection(20, true);
            } else if (proxydesig.equals("PSHT")) {
                designation.setSelection(21, true);
            } else if (proxydesig.equals("PST")) {
                designation.setSelection(22, true);
            } else if (proxydesig.equals("Qari/Qaria")) {
                designation.setSelection(23, true);
            } else if (proxydesig.equals("S/Clerk")) {
                designation.setSelection(24, true);
            } else if (proxydesig.equals("D.M")) {
                designation.setSelection(25, true);
            } else if (proxydesig.equals("SS")) {
                designation.setSelection(26, true);
            } else if (proxydesig.equals("Sweeper")) {
                designation.setSelection(27, true);
            } else if (proxydesig.equals("TT")) {
                designation.setSelection(28, true);
            } else if (proxydesig.equals("Vice Principal")) {
                designation.setSelection(29, true);
            } else if (proxydesig.equals("Water Carrier")) {
                designation.setSelection(30, true);
            } else if (proxydesig.equals("SST")) {
                designation.setSelection(31, true);
            } else {
                designation.setSelection(0, true);
            }

        }
    }
}
