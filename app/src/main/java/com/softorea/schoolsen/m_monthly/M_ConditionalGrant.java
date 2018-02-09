package com.softorea.schoolsen.m_monthly;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.databasehelper.DatabaseHandler;
import com.softorea.schoolsen.lists.Roster_List;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Softorea on 10/2/2017.
 */

public class M_ConditionalGrant extends Activity /*implements View.OnClickListener,RadioGroup.OnCheckedChangeListener,LabelledSpinner.OnItemChosenListener*/ {
    Button back, next;
    String grantType = "null";
    String grantAmount = "null";
    String grantYear = "null";
    Button btnNext;
    Button btnLeft;
    TextView Label;
    TextView btnCalender;
    TextView txtDate;
    String dateSelected;
    Date dateSelectedobj;
    Date datecurrentobj;
    int datebol = 0;
    String currentDate;
    SimpleDateFormat sdf;
    Spinner spType;
    EditText editFinancial;
    TextView txtFinancial;
    RadioGroup rgWorkComleted;
    LinearLayout gradingLayout;
    RadioGroup rgWorkGrading;
    String startDate = "null";
    String layoutType = "null";
    //String financial = "null";
    String workComplted = "null";
    String workGrading = "null";
    //String recordShown = "Null";
    String Remarks = "Null";
    int index = -1;
    int id;
    TextView txtAmountGranted;
    TextView txtGrantYear;
    RadioGroup rgRecordShown;
    EditText edit_remarks;

    TextView maintitle;
    TextView textdate;
    EditText finanicial, remarks;
    RadioGroup recordShown, WorkedComplete, workgrading;
    RadioButton rdshown, rdnotshown, workcomplete, worknotcomplete, a, b, c, d;
    String RECORSH = "Null";
    String WORKC = "Null";
    String grading = "Null";
    Grant grant;
    String emis;
    //Button back,next;
    DatabaseHandler databasehandler;
    ArrayList<Grant> grantlist = new ArrayList<>();
    /*public void setGrant(String type,String amount,String year,int id) {
        this.grantType = type;
        this.grantAmount = amount;
        this.grantYear = year;
        this.id = id;
    }*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_conditional_grant1);
        spType = (Spinner) findViewById(R.id.sp_type);
        txtGrantYear = (TextView) findViewById(R.id.txt_grant_year);
        txtAmountGranted = (TextView) findViewById(R.id.txt_amount);
        next = (Button) findViewById(R.id.btn_right);
        back = (Button) findViewById(R.id.btn_left);
        workgrading = (RadioGroup) findViewById(R.id.rg_work_grading);
        gradingLayout = (LinearLayout) findViewById(R.id.grading_layout);
        maintitle = (TextView) findViewById(R.id.grant_title);
        textdate = (TextView) findViewById(R.id.btn_grant_date);
        finanicial = (EditText) findViewById(R.id.edit_financial);
        remarks = (EditText) findViewById(R.id.remarks);
        recordShown = (RadioGroup) findViewById(R.id.rg_record_shown);
        WorkedComplete = (RadioGroup) findViewById(R.id.rg_work_completed);
        rdshown = (RadioButton) findViewById(R.id.rd_record_shown);
        rdnotshown = (RadioButton) findViewById(R.id.rg_record_not_shown);
        workcomplete = (RadioButton) findViewById(R.id.rd_yes);
        worknotcomplete = (RadioButton) findViewById(R.id.rd_no);
        a = (RadioButton) findViewById(R.id.rd_a);
        b = (RadioButton) findViewById(R.id.rd_b);
        c = (RadioButton) findViewById(R.id.rd_c);
        d = (RadioButton) findViewById(R.id.rd_d);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        emis = preferences.getString("emiscode", "");
        databasehandler = new DatabaseHandler(M_ConditionalGrant.this);
        //grant =  databasehandler.getgrantt(emis);


        String textamount = grant.getAmount();
        final String textyear = grant.getYear();
        String type = grant.getGrantType();
        int idd = grant.getFaceCode();
        String recordshown = grant.getRecordStatus();
        String remarkss = grant.getRemarks();
        String workcompletee = grant.getWorkStatus();
        String workgradingg = grant.getWorkGrading();
        String afna = grant.getFinancial();
        String physicalt = grant.getType();

        edit_remarks.setText(remarkss);

        if (recordshown.equals("shown")) {
            rdshown.setChecked(true);
        } else {
            rdnotshown.setChecked(true);
        }

        if (workcompletee.equals("Completed")) {
            workcomplete.setChecked(true);
        } else {
            worknotcomplete.setChecked(true);
        }
        finanicial.setText(afna);
        if (workgradingg.equals("A")) {
            a.setChecked(true);
        } else if (workgradingg.equals("B")) {
            b.setChecked(true);
        }
        if (workgradingg.equals("C")) {
            c.setChecked(true);
        } else if (workgradingg.equals("D")) {
            d.setChecked(true);
        } else {
            a.setChecked(false);
            b.setChecked(false);
            c.setChecked(false);
            d.setChecked(false);
        }


        if (idd == 1) {
            maintitle.setText("Additional Classroom");
        } else if (idd == 2 && idd == 6) {
            maintitle.setText("Boundary Wall");
        } else if (idd == 3) {
            maintitle.setText("Group Laterine");
        } else if (idd == 4) {
            maintitle.setText("Water Supply");
        } else if (idd == 5) {
            maintitle.setText("Electricity");
        } else if (idd == 7) {
            maintitle.setText("Solar Panel");
        } else {
            maintitle.setText("Conditional Grant");
        }
        //maintitle.setText(grantType);

        try {
            txtAmountGranted.setText(textamount);
        } catch (Exception e) {

        }

        try {
            txtGrantYear.setText(textyear);
        } catch (Exception e) {

        }
        if (type.toLowerCase().contains("laterine") || grantType.toLowerCase().contains("class")) {
            String[] science = getResources().getStringArray(R.array.class_toilet);
            ArrayAdapter<String> sciencee = new ArrayAdapter<String>(M_ConditionalGrant.this, android.R.layout.simple_spinner_dropdown_item, science);
            spType.setAdapter(sciencee);
        } else if (type.toLowerCase().contains("wall")) {
            String[] science = getResources().getStringArray(R.array.boundary_wall);
            ArrayAdapter<String> sciencee = new ArrayAdapter<String>(M_ConditionalGrant.this, android.R.layout.simple_spinner_dropdown_item, science);
            spType.setAdapter(sciencee);
        } else {
            String[] science = getResources().getStringArray(R.array.grant_other);
            ArrayAdapter<String> sciencee = new ArrayAdapter<String>(M_ConditionalGrant.this, android.R.layout.simple_spinner_dropdown_item, science);
            spType.setAdapter(sciencee);
        }
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

                textdate.setText(sdf.format(myCalendar.getTime()));
            }

        };

        textdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DatePickerDialog dialog = new DatePickerDialog(M_ConditionalGrant.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
                dialog.getDatePicker().setMaxDate(new Date().getTime());
                dialog.show();
            }
        });

        recordShown
                .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if (checkedId == R.id.rd_record_shown) {
                            RECORSH = "shown";
                            gradingLayout.setVisibility(View.GONE);
                        } else {
                            RECORSH = "not shown";
                            gradingLayout.setVisibility(View.GONE);
                        }

                    }
                });

        WorkedComplete
                .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if (checkedId == R.id.rd_yes) {
                            WORKC = "Completed";
                            gradingLayout.setVisibility(View.VISIBLE);
                        } else {
                            WORKC = "not Completed";
                            gradingLayout.setVisibility(View.GONE);

                        }

                    }
                });

        workgrading
                .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if (checkedId == R.id.rd_a) {
                            workGrading = "A";
                        } else if (checkedId == R.id.rd_b) {
                            workGrading = "B";
                        } else if (checkedId == R.id.rd_c) {
                            workGrading = "C";
                        } else if (checkedId == R.id.rd_d) {
                            workGrading = "D";
                        }

                    }
                });
        /*Label = (TextView)findViewById(R.id.grant_title);
        //txtDate = (TextView)findViewById(R.id.txt_selected_date);
        btnCalender = (TextView)findViewById(R.id.btn_grant_date);
        Label.setText("Grant " + WordUtils.capitalize(grantType.toLowerCase()));
        spType = (LabelledSpinner)findViewById(R.id.sp_type);
        editFinancial = (EditText)findViewById(R.id.edit_financial);
        txtFinancial = (TextView)findViewById(R.id.txt_financial);
        gradingLayout = (LinearLayout)findViewById(R.id.grading_layout);
        rgWorkGrading = (RadioGroup)findViewById(R.id.rg_work_grading);
        txtAmountGranted = (TextView)findViewById(R.id.txt_amount);
        edit_remarks = (EditText)findViewById(R.id.remarks);
        rgRecordShown = (RadioGroup)findViewById(R.id.rg_record_shown);
        try{
            txtAmountGranted.setText(grantAmount);
        }catch (Exception e){

        }

        try{
            txtGrantYear.setText(grantYear);
        }catch (Exception e){

        }
        if(grantType.toLowerCase().contains("laterine") || grantType.toLowerCase().contains("class")){
            spType.setItemsArray(R.array.class_toilet);
        }else if(grantType.toLowerCase().contains("wall")){
            spType.setItemsArray(R.array.boundary_wall);
        }else{
            spType.setItemsArray(R.array.grant_other);
        }
        rgWorkComleted = (RadioGroup)findViewById(R.id.rg_work_completed);
        rgWorkComleted.setOnCheckedChangeListener(this);
        btnNext.setOnClickListener(this);
        btnLeft.setOnClickListener(this);
        btnCalender.setOnClickListener(this);
        spType.setOnItemChosenListener(this);

        sdf = new SimpleDateFormat("dd-MM-yyyy");
        currentDate = sdf.format(new Date());
        try {
            datecurrentobj = sdf.parse(currentDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        editFinancial.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ValueConverters moneyConverters = ValueConverters.ENGLISH_INTEGER;
                try {
                    txtFinancial.setText(moneyConverters.asWords(Integer.parseInt(s.toString())));
                }catch (Exception e){
                    txtFinancial.setText("");
                }
            }
        });


        */
        back = (Button) findViewById(R.id.btn_left);
        next = (Button) findViewById(R.id.btn_right);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(M_ConditionalGrant.this, M_VacantPositionsList.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                grant.setEmisCode(emis);
                grant.setAmount(txtAmountGranted.getText().toString());
                grant.setGrantType(grantType);
                grant.setYear(txtGrantYear.getText().toString());
                grant.setStartDate(textdate.getText().toString());
                grant.setFinancial(finanicial.getText().toString());
                grant.setType(workGrading);
                grant.setRemarks(remarks.getText().toString());
                grant.setWorkStatus(WORKC);
                grant.setRecordStatus(RECORSH);
                grant.setWorkGrading(workGrading);


                databasehandler.saveGrant(grant);
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(M_ConditionalGrant.this, M_SchoolFunctioning.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                grant.setEmisCode(emis);
                grant.setAmount(txtAmountGranted.getText().toString());
                grant.setStartDate(textdate.getText().toString());
                //grant.setType(workGrading);
                grant.setFinancial(finanicial.getText().toString());
                grant.setWorkStatus(WORKC);
                grant.setWorkGrading(workGrading);
                grant.setRecordStatus(RECORSH);
                grant.setRemarks(remarks.getText().toString());


                databasehandler.saveGrant(grant);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(M_ConditionalGrant.this);
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



    /*@Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_left:
                saveObject(FormService.objform);
                index = 0;
                startDate = txtDate.getText().toString();
                financial = editFinancial.getText().toString();
                Remarks = edit_remarks.getText().toString();
                if(rgRecordShown.indexOfChild(findViewById(rgRecordShown.getCheckedRadioButtonId())) == 0){
                    recordShown = "yes";
                }else if(rgRecordShown.indexOfChild(findViewById(rgRecordShown.getCheckedRadioButtonId())) == 1){
                    recordShown = "No";
                }


                if(rgWorkComleted.indexOfChild(findViewById(rgWorkComleted.getCheckedRadioButtonId())) == 0){
                    workComplted = "yes";
                }else if(rgWorkComleted.indexOfChild(findViewById(rgWorkComleted.getCheckedRadioButtonId())) == 1){
                    workComplted = "No";
                }

                if(rgWorkGrading.indexOfChild(findViewById(rgWorkGrading.getCheckedRadioButtonId())) == 0){
                    workGrading = "a";
                }else if (rgWorkGrading.indexOfChild(findViewById(rgWorkGrading.getCheckedRadioButtonId())) == 1){
                    workGrading = "b";
                }else if(rgWorkGrading.indexOfChild(findViewById(rgWorkGrading.getCheckedRadioButtonId())) == 2){
                    workGrading = "c";
                }else if (rgWorkGrading.indexOfChild(findViewById(rgWorkGrading.getCheckedRadioButtonId())) == 3){
                    workGrading = "d";
                }

                SetupValues setupValues = new SetupValues();
                setupValues.execute();


                break;
            case R.id.btn_right:
                index = 1;
                saveObject(FormService.objform);
                startDate = txtDate.getText().toString();
                financial = editFinancial.getText().toString();
                Remarks = edit_remarks.getText().toString();

                if(rgRecordShown.indexOfChild(findViewById(rgRecordShown.getCheckedRadioButtonId())) == 0){
                    recordShown = "yes";
                }else if(rgRecordShown.indexOfChild(findViewById(rgRecordShown.getCheckedRadioButtonId())) == 1){
                    recordShown = "No";
                }

                if(rgWorkComleted.indexOfChild(findViewById(rgWorkComleted.getCheckedRadioButtonId())) == 0){
                    workComplted = "yes";
                }else if(rgWorkComleted.indexOfChild(findViewById(rgWorkComleted.getCheckedRadioButtonId())) == 1){
                    workComplted = "No";
                }

                if(rgWorkGrading.indexOfChild(findViewById(rgWorkGrading.getCheckedRadioButtonId())) == 0){
                    workGrading = "a";
                }else if (rgWorkGrading.indexOfChild(findViewById(rgWorkGrading.getCheckedRadioButtonId())) == 1){
                    workGrading = "b";
                }else if(rgWorkGrading.indexOfChild(findViewById(rgWorkGrading.getCheckedRadioButtonId())) == 2){
                    workGrading = "c";
                }else if (rgWorkGrading.indexOfChild(findViewById(rgWorkGrading.getCheckedRadioButtonId())) == 3){
                    workGrading = "d";
                }

                SetupValues setupValues2 = new SetupValues();
                setupValues2.execute();

                break;
            case R.id.btn_grant_date:
                showDatePicker();
                break;
        }
    }




    private void showDatePicker() {
        DatePickerFragment date = new DatePickerFragment();

        Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        int month = calender.get(calender.MONTH);
        month = month - 1;
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", month);
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);
        date.setCallBack(ondate);
        //date.show(getApplicationContext(), "Date Picker");
    }

    DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            int month = Integer.parseInt(String.valueOf(monthOfYear));
            month = month + 1;
            btnCalender.setText(String.valueOf(dayOfMonth) + "-" + month
                    + "-" + String.valueOf(year));
            dateSelected = String.valueOf(dayOfMonth) + "-" + month + "-"
                    + String.valueOf(year);

            SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
            try {
                dateSelectedobj = sdf2.parse(dateSelected);

                if (datecurrentobj.compareTo(dateSelectedobj) > 0) {
                    datebol = 1;
                    // validate();
                } else {
                    datebol = 0;
                    // validate();
                }
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    };

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rd_yes:
                gradingLayout.setVisibility(View.VISIBLE);

                break;
            case R.id.rd_no:
                gradingLayout.setVisibility(View.GONE);

                break;
        }
    }

    @Override
    public void onItemChosen(View view, AdapterView<?> adapterView, View view1, int position, long id) {
        String selectedtext = adapterView.getItemAtPosition(position).toString();
        switch (spType.getId()){
            case R.id.sp_type:
                layoutType = selectedtext;
        }
    }

    @Override
    public void onNothingChosen(View view, AdapterView<?> adapterView) {

    }

    private class SetupValues extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            for (int i = 0; i <FormService.objform.grantList.size() ; i++)
            {
                if(id == FormService.objform.getGrantList().get(i).getFaceCode()){

                    FormService.objform.getGrantList().get(i).setStartDate(startDate);
                    FormService.objform.getGrantList().get(i).setType(layoutType);
                    FormService.objform.getGrantList().get(i).setWorkStatus(workComplted);
                    FormService.objform.getGrantList().get(i).setWorkGrading(workGrading);
                    FormService.objform.getGrantList().get(i).setFinancial(financial);
                    FormService.objform.getGrantList().get(i).setYear(grantYear);
                    FormService.objform.getGrantList().get(i).setAmount(grantAmount);
                    FormService.objform.getGrantList().get(i).setGrantType(grantType);
                    FormService.objform.getGrantList().get(i).setRemarks(Remarks);
                    FormService.objform.getGrantList().get(i).setRecordStatus(recordShown);
                }
            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {

        }

        @Override
        protected void onPreExecute() {


        }

        @Override
        protected void onProgressUpdate(Void... values) {}
    }*/

    @Override
    protected void onPause() {
        super.onPause();

        /*SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("recrdshown", RECORSH);
        editor.putString("workcmpl", WORKC);
        editor.putString("FINANICIAL", finanicial.getText().toString());
        editor.putString("seltcdate", textdate.getText().toString());
        editor.putString("rmarks", remarks.getText().toString());
        editor.apply();*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
        String schCleanN = preferences.getString("recrdshown", "");
        String stdCleanN = preferences.getString("workcmpl", "");
        String fnc = preferences.getString("FINANICIAL", "");
        finanicial.setText(fnc);
        String rmarks = preferences.getString("rmarks", "");
        remarks.setText(rmarks);
        String seltcdate = preferences.getString("seltcdate", "");
        textdate.setText(seltcdate);
        if (schCleanN.equals("shown"))
        {
            rdshown.setChecked(true);
        }
        else
        {
            rdnotshown.setChecked(true);
        }
        if (stdCleanN.equals("Completed"))
        {
            workcomplete.setChecked(true);
        }
        else
        {
            worknotcomplete.setChecked(true);
        }*/


    }
}
