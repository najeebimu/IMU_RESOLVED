package com.softorea.schoolsen.models;


import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.softorea.schoolsen.R;

public class EnrolmentDialog extends Dialog {

    Button save;

    String className = "Null";
    EditText stdPerRegisterInput;
    EditText stdPeresentInput;
    EditText grlsInBoysInput;
    EditText boysIngirlsInput;

    FormModel objform;
    TextView girlsinboystext;
    TextView boysingirlstext;
    TextView titleView;
    String gender;
    Context context;
    int position;
    int inList = 0;
    ListView listView;

    public EnrolmentDialog(Context context, String classNo, int pos, int size,
                           ListView enList) {
        super(context);
        this.position = pos;
        this.className = classNo;
        this.context = context;
        this.listView = enList;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.enrolment_dialog);
        setCancelable(true);
        objform = new FormModel();
        stdPerRegisterInput = (EditText) findViewById(R.id.stdregister);
        stdPeresentInput = (EditText) findViewById(R.id.stdpresent);
        grlsInBoysInput = (EditText) findViewById(R.id.grlsinboys);
        boysIngirlsInput = (EditText) findViewById(R.id.boysingils);
        titleView = (TextView) findViewById(R.id.title);
        girlsinboystext = (TextView) findViewById(R.id.girlsinboystxt);
        boysingirlstext = (TextView) findViewById(R.id.boysingirlstext);

        titleView.setText(className);

        if (objform.enrollmentList.size() > 0) {

            for (int i = 0; i < objform.enrollmentList.size(); i++) {

                if (className.equals(objform.enrollmentList.get(i)
                        .getClassNo())) {

                    try {
                        stdPerRegisterInput
                                .setText(objform.enrollmentList
                                        .get(i).getStudentAsPerRegister());
                        stdPeresentInput
                                .setText(objform.enrollmentList
                                        .get(i).getStudentPresentHeadCount());
                        if (objform.enrollmentList.get(i)
                                .getGirlsInBoysSchool().equals("")) {
                            grlsInBoysInput.setText("_");
                        } else {
                            grlsInBoysInput
                                    .setText(objform.enrollmentList
                                            .get(i).getGirlsInBoysSchool());
                        }
                        if (objform.enrollmentList.get(i)
                                .getBoysInBoysSchool().equals("")) {
                            boysIngirlsInput.setText("");
                        } else {
                            boysIngirlsInput
                                    .setText(objform.enrollmentList
                                            .get(i).getBoysInBoysSchool());
                        }

                    } catch (IndexOutOfBoundsException e) {

                    }
                }
            }
        }

        gender = objform.getGenderSchool();
        if (gender.toLowerCase().equals("girls")) {
            girlsinboystext.setVisibility(View.GONE);
            grlsInBoysInput.setVisibility(View.GONE);
            boysIngirlsInput.setVisibility(View.VISIBLE);
            boysingirlstext.setVisibility(View.VISIBLE);

        } else {
            boysIngirlsInput.setVisibility(View.GONE);
            boysingirlstext.setVisibility(View.GONE);
            girlsinboystext.setVisibility(View.VISIBLE);
            grlsInBoysInput.setVisibility(View.VISIBLE);
        }

        save = (Button) findViewById(R.id.btn_savedialoge);
        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String stdpresent = stdPeresentInput.getText().toString();
                String stdenrolled = stdPerRegisterInput.getText().toString();
                String bing = boysIngirlsInput.getText().toString();
                String ginb = grlsInBoysInput.getText().toString();

                if (!stdpresent.matches("") && !stdenrolled.matches("")) {

                    int present = Integer.parseInt(stdpresent);
                    int total = Integer.parseInt(stdenrolled);
                    int bg = 0;
                    int gb = 0;

                    if (!bing.equals("_") && !bing.equals("")) {
                        bg = Integer.parseInt(bing);
                    } else {
                        if (!ginb.equals("_") && !ginb.equals("")) {
                            gb = Integer.parseInt(ginb);
                        }
                    }
                    if (gender.toLowerCase().equals("girls")) {

                        if (present > total) {
                            Toast.makeText(
                                    EnrolmentDialog.this.context,
                                    "Present student cannot be graeter than enroled",
                                    Toast.LENGTH_SHORT).show();
                        } else if (bg > total) {

                            Toast.makeText(
                                    EnrolmentDialog.this.context,
                                    "Boys in girls School must be less than total number of student",
                                    Toast.LENGTH_SHORT).show();
                        } else {

                            EnrollmentModel enrollment = new EnrollmentModel();
                            enrollment.setClassNo(className);
                            enrollment
                                    .setStudentAsPerRegister(stdPerRegisterInput
                                            .getText().toString());
                            enrollment
                                    .setStudentPresentHeadCount(stdPeresentInput
                                            .getText().toString());
                            enrollment.setGirlsInBoysSchool("");
                            enrollment.setBoysInBoysSchool(boysIngirlsInput
                                    .getText().toString());

                            try {

                                if (objform.enrollmentList
                                        .size() > 0) {
                                    for (int i = 0; i < objform.enrollmentList
                                            .size(); i++) {
                                        if (className
                                                .equals(objform.enrollmentList
                                                        .get(i)
                                                        .getClassNo())) {
                                            inList = 1;
                                            objform.enrollmentList
                                                    .set(i, enrollment);
                                        }
                                    }
                                }
                                if (inList == 0) {
                                    objform.enrollmentList
                                            .add(enrollment);
                                    ItemSelectedList item = new ItemSelectedList();
                                    Log.d("Position",
                                            Integer.toString(position));
                                    item.setPosition(position);
                                    item.setIsItemSelected(true);
                                    objform.itemSelectedList
                                            .add(item);
                                }


                            } catch (IndexOutOfBoundsException e) {

                            }


                            dismiss();
                        }

                    } else {
                        if (present > total) {
                            Toast.makeText(
                                    EnrolmentDialog.this.context,
                                    "Present student cannot be graeter than enroled",
                                    Toast.LENGTH_SHORT).show();
                        } else if (gb > total) {

                            Toast.makeText(
                                    EnrolmentDialog.this.context,
                                    "girls in boys School must be less than total number of student",
                                    Toast.LENGTH_SHORT).show();
                        } else {

                            EnrollmentModel enrollment = new EnrollmentModel();
                            enrollment.setClassNo(className);
                            enrollment
                                    .setStudentAsPerRegister(stdPerRegisterInput
                                            .getText().toString());
                            enrollment
                                    .setStudentPresentHeadCount(stdPeresentInput
                                            .getText().toString());
                            enrollment.setGirlsInBoysSchool(grlsInBoysInput
                                    .getText().toString());
                            enrollment.setBoysInBoysSchool("");

                            try {

                                if (objform.enrollmentList
                                        .size() > 0) {
                                    for (int i = 0; i < objform.enrollmentList
                                            .size(); i++) {
                                        if (className
                                                .equals(objform.enrollmentList
                                                        .get(i)
                                                        .getClassNo())) {
                                            inList = 1;
                                            objform.enrollmentList
                                                    .set(i, enrollment);
                                        }
                                    }
                                }
                                if (inList == 0) {
                                    objform.enrollmentList
                                            .add(enrollment);
                                    ItemSelectedList item = new ItemSelectedList();
                                    Log.d("Position",
                                            Integer.toString(position));
                                    item.setPosition(position);
                                    item.setIsItemSelected(true);
                                    objform.itemSelectedList
                                            .add(item);
                                }

                            } catch (IndexOutOfBoundsException e) {

                            }

                            dismiss();
                        }
                    }
                } else {
                    Toast.makeText(getContext(),
                            "Please fill the detail first", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }

}
