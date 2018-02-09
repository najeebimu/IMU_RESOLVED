package com.softorea.schoolsen.y_annual;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.adapters.ProvisionBooksListAdapter;
import com.softorea.schoolsen.b_annual.B_BuildingCondition;
import com.softorea.schoolsen.b_annual.B_Cleanliness;
import com.softorea.schoolsen.b_annual.B_Commodities;
import com.softorea.schoolsen.b_annual.B_Construction;
import com.softorea.schoolsen.b_annual.B_IT_LabExists;
import com.softorea.schoolsen.b_annual.B_InfraStructure;
import com.softorea.schoolsen.b_annual.B_ParentTeacherCouncil;
import com.softorea.schoolsen.b_annual.B_SchoolArea;
import com.softorea.schoolsen.b_annual.B_Stipend;
import com.softorea.schoolsen.b_annual.B_TeacherGuides;
import com.softorea.schoolsen.databasehelper.DatabaseHandler;
import com.softorea.schoolsen.lists.Roster_List;
import com.softorea.schoolsen.lists.SubjectActivity;
import com.softorea.schoolsen.m_monthly.M_SanctionedPostNonteachingList;
import com.softorea.schoolsen.m_monthly.MoreInfo;
import com.softorea.schoolsen.models.FormModel;
import com.softorea.schoolsen.models.ItemSelectedList;
import com.softorea.schoolsen.models.ProvisionBooks;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by Softorea on 10/2/2017.
 */

public class Y_ProvisionFreeBooksMain extends Activity {
    public static final String MyPREFERENCES = "MyPrefs";
    Button back, next;
    FormModel objform;
    ArrayList<ProvisionBooks> provisionClassList;
    ProvisionBooks provisionBooks;
    ListView provisionClassListView;
    DatabaseHandler db;
    String level;
    int pos;
    Button btnLeft;
    Button btnRight;
    int counter = 0;
    SharedPreferences prefs;
    String emis;
    String schoolareaStr,constructionStr,buildindconditionStr,itlabStr,
            commodititesStr,ptcStr,infrastructureStr,guideStr,cleanlinessStr,stipendStr,SanctionedStr,indicatorStr,
            enrollmentageStr,enrollmentgrpStr,disabledStr,securityStr,ftbStr,furnitureStr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_provisionfreebooks);
        objform = new FormModel();
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        emis = prefs.getString("emiscode", "");

        level = prefs.getString("schoollevel", "");
        db = new DatabaseHandler(Y_ProvisionFreeBooksMain.this);
        provisionClassListView = (ListView) findViewById(R.id.provisionalclasslist_list);
        back = (Button) findViewById(R.id.btn_left);
        next = (Button) findViewById(R.id.btn_right);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    saveObject(objform);

                } catch (NullPointerException e) {
                    saveObject(objform);
                }
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Y_ProvisionFreeBooksMain.this);
                final String middle = preferences.getString("middle", "");
                final String high = preferences.getString("high", "");
                final String hsec = preferences.getString("highsecondary", "");

                final String boys = preferences.getString("boys", "");
                final String girls = preferences.getString("girls", "");
                SQLiteOpenHelper database = new DatabaseHandler(Y_ProvisionFreeBooksMain.this);
                SQLiteDatabase dbd = database.getReadableDatabase();
                Cursor cursorform = dbd.rawQuery("SELECT * FROM t_screenconfig", null);
                if (cursorform.moveToFirst()) {
                    do {
                        schoolareaStr = cursorform.getString(cursorform.getColumnIndex("Bi_SchoolArea"));
                        constructionStr = cursorform.getString(cursorform.getColumnIndex("Bi_NatureOfConstruction"));
                        buildindconditionStr = cursorform.getString(cursorform.getColumnIndex("Bi_BuildingCondition"));
                        itlabStr = cursorform.getString(cursorform.getColumnIndex("Bi_ITLab"));
                        commodititesStr = cursorform.getString(cursorform.getColumnIndex("Bi_Commodities"));
                        ptcStr = cursorform.getString(cursorform.getColumnIndex("Bi_PTC"));
                        infrastructureStr = cursorform.getString(cursorform.getColumnIndex("Bi_Infrastructure"));
                        guideStr = cursorform.getString(cursorform.getColumnIndex("Bi_Guides"));
                        cleanlinessStr = cursorform.getString(cursorform.getColumnIndex("Bi_Cleanliness"));
                        stipendStr = cursorform.getString(cursorform.getColumnIndex("Bi_Stipend"));
                        disabledStr = cursorform.getString(cursorform.getColumnIndex("An_DisabledStudent"));
                        enrollmentageStr = cursorform.getString(cursorform.getColumnIndex("An_EnrollmentByAge"));
                        enrollmentgrpStr = cursorform.getString(cursorform.getColumnIndex("An_EnrollmentByGroup"));
                        ftbStr = cursorform.getString(cursorform.getColumnIndex("An_FTB"));
                        furnitureStr = cursorform.getString(cursorform.getColumnIndex("An_Furniture"));
                        indicatorStr = cursorform.getString(cursorform.getColumnIndex("An_Indicator"));
                        SanctionedStr = cursorform.getString(cursorform.getColumnIndex("An_SantionedPosts"));
                        securityStr = cursorform.getString(cursorform.getColumnIndex("An_SecurityMeasures"));
                    } while (cursorform.moveToNext());
                }
                cursorform.close();
                dbd.close();

               if (furnitureStr.equals("True"))
                {
                    startActivity(new Intent(Y_ProvisionFreeBooksMain.this, Y_Furniture.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else
                {
                    startActivity(new Intent(Y_ProvisionFreeBooksMain.this, CompleteForm.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
            }
        });

        provisionClassListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {



                        /*pos = position;
                        counter++;

                        if (counter < provisionClassList.size()) {

                        } else if (counter == provisionClassList.size()) {

                        }*/

                ItemSelectedList item = new ItemSelectedList();
                Log.d("Position", Integer.toString(pos));
                item.setPosition(pos);
                item.setIsItemSelected(true);

                objform.provisionSelectedList.add(item);
                String classname = provisionClassList.get(position).getClassName();
                Intent intent = (new Intent(Y_ProvisionFreeBooksMain.this, SubjectActivity.class));
                intent.putExtra("classNumber", classname);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);

                        /*String ClassNumber = (String) ((TextView) view
                                .findViewById(R.id.class_name)).getText();
                        Intent intentt = new Intent(Y_ProvisionFreeBooksMain.this,
                                SubjectActivity.class);
                        intentt.putExtra("classNumber", ClassNumber);

                        startActivity(intent);*/

            }
        });
        fillArray();
        ProvisionBooksListAdapter adpater = new ProvisionBooksListAdapter(
                Y_ProvisionFreeBooksMain.this, provisionClassList,
                provisionClassListView);
        provisionClassListView.setAdapter(adpater);

        /*provisionClassListView.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view,
                                             int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {

                Log.d(Integer.toString(firstVisibleItem), "First");
                Log.d(Integer.toString(visibleItemCount), "Visible");
                Log.d(Integer.toString(totalItemCount), "Total");
                for (int i = firstVisibleItem; i < totalItemCount; i++) {
                    Log.d("I", Integer.toString(i));
                    try {
                        Boolean found = false;
                        for (int j = 0; j < objform.provisionSelectedList
                                .size(); j++) {
                            Log.d("J", Integer.toString(j));
                            Log.d("..",
                                    Integer.toString(objform.provisionSelectedList
                                            .get(j).getPosition()
                                            - firstVisibleItem)
                                            + "=" + Integer.toString(i));
                            if (objform.provisionSelectedList
                                    .get(j).getPosition()
                                    - firstVisibleItem == i) {
                                Log.d("Found", "Yes");
                                found = true;
                                break;
                            }
                        }

                        try {
                            if (found) {
                                provisionClassListView
                                        .getChildAt(i)
                                        .setBackgroundColor(
                                                Color.parseColor("#CEF6F5"));
                            } else {
                                provisionClassListView.getChildAt(i)
                                        .setBackgroundColor(Color.WHITE);
                            }
                        } catch (Exception ex) {
                        }
                    } catch (Exception ex) {
                    }
                }
                // Toast.makeText(getActivity(), firstVisibleItem,
                // Toast.LENGTH_SHORT).show();
                // TODO Auto-generated method stub

            }
        });

        /*if (objform.provisionFreeBooksList.size() > 0) {
            // this.vp.setPagingEnabled(true);
        } else {
            // this.vp.setPagingEnabled(true);
        */


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Y_ProvisionFreeBooksMain.this);
                final String middle = preferences.getString("middle", "");
                final String high = preferences.getString("high", "");
                final String hsec = preferences.getString("highsecondary", "");

                final String boys = preferences.getString("boys", "");
                final String girls = preferences.getString("girls", "");
                SQLiteOpenHelper database = new DatabaseHandler(Y_ProvisionFreeBooksMain.this);
                SQLiteDatabase dbd = database.getReadableDatabase();
                Cursor cursorform = dbd.rawQuery("SELECT * FROM t_screenconfig", null);
                if (cursorform.moveToFirst()) {
                    do {
                        schoolareaStr = cursorform.getString(cursorform.getColumnIndex("Bi_SchoolArea"));
                        constructionStr = cursorform.getString(cursorform.getColumnIndex("Bi_NatureOfConstruction"));
                        buildindconditionStr = cursorform.getString(cursorform.getColumnIndex("Bi_BuildingCondition"));
                        itlabStr = cursorform.getString(cursorform.getColumnIndex("Bi_ITLab"));
                        commodititesStr = cursorform.getString(cursorform.getColumnIndex("Bi_Commodities"));
                        ptcStr = cursorform.getString(cursorform.getColumnIndex("Bi_PTC"));
                        infrastructureStr = cursorform.getString(cursorform.getColumnIndex("Bi_Infrastructure"));
                        guideStr = cursorform.getString(cursorform.getColumnIndex("Bi_Guides"));
                        cleanlinessStr = cursorform.getString(cursorform.getColumnIndex("Bi_Cleanliness"));
                        stipendStr = cursorform.getString(cursorform.getColumnIndex("Bi_Stipend"));
                        disabledStr = cursorform.getString(cursorform.getColumnIndex("An_DisabledStudent"));
                        enrollmentageStr = cursorform.getString(cursorform.getColumnIndex("An_EnrollmentByAge"));
                        enrollmentgrpStr = cursorform.getString(cursorform.getColumnIndex("An_EnrollmentByGroup"));
                        ftbStr = cursorform.getString(cursorform.getColumnIndex("An_FTB"));
                        furnitureStr = cursorform.getString(cursorform.getColumnIndex("An_Furniture"));
                        indicatorStr = cursorform.getString(cursorform.getColumnIndex("An_Indicator"));
                        SanctionedStr = cursorform.getString(cursorform.getColumnIndex("An_SantionedPosts"));
                        securityStr = cursorform.getString(cursorform.getColumnIndex("An_SecurityMeasures"));
                    } while (cursorform.moveToNext());
                }
                cursorform.close();
                dbd.close();
                if (securityStr.equals("True"))
                {
                    startActivity(new Intent(Y_ProvisionFreeBooksMain.this, Y_SecurityMeasures.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }

                else if (disabledStr.equals("True"))
                {
                    startActivity(new Intent(Y_ProvisionFreeBooksMain.this, Y_SpecialGirlsMain.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (enrollmentgrpStr.equals("True") && hsec.equals("HighSecondarySelected")) {
                    startActivity(new Intent(Y_ProvisionFreeBooksMain.this, Y_EnrollmentElevenTwelve.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (enrollmentgrpStr.equals("True") && (middle.equals("MiddleSelected") || high.equals("HighSelected") || hsec.equals("HighSecondarySelected"))) {
                    startActivity(new Intent(Y_ProvisionFreeBooksMain.this, Y_EnrollmentByGroupSectionMain.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }

                else if (enrollmentageStr.equals("True"))
                {
                    startActivity(new Intent(Y_ProvisionFreeBooksMain.this, Y_EnrollmentAgeGirls.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (indicatorStr.equals("True"))
                {
                    startActivity(new Intent(Y_ProvisionFreeBooksMain.this, Y_OtherFacilities.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (SanctionedStr.equals("True"))
                {
                    startActivity(new Intent(Y_ProvisionFreeBooksMain.this, M_SanctionedPostNonteachingList.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (cleanlinessStr.equals("True"))
                {
                    startActivity(new Intent(Y_ProvisionFreeBooksMain.this, B_Cleanliness.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (guideStr.equals("True"))
                {
                    startActivity(new Intent(Y_ProvisionFreeBooksMain.this, B_TeacherGuides.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (infrastructureStr.equals("True"))
                {
                    startActivity(new Intent(Y_ProvisionFreeBooksMain.this, B_InfraStructure.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (stipendStr.equals("True") && girls.equals("GirlsSelected") && (middle.equals("MiddleSelected") || high.equals("HighSelected")
                        || hsec.equals("HighSecondarySelected"))) {
                    startActivity(new Intent(Y_ProvisionFreeBooksMain.this, B_Stipend.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (ptcStr.equals("True"))
                {
                    startActivity(new Intent(Y_ProvisionFreeBooksMain.this, B_ParentTeacherCouncil.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (commodititesStr.equals("True"))
                {
                    startActivity(new Intent(Y_ProvisionFreeBooksMain.this, B_Commodities.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (itlabStr.equals("True"))
                {
                    startActivity(new Intent(Y_ProvisionFreeBooksMain.this, B_IT_LabExists.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (buildindconditionStr.equals("True"))
                {
                    startActivity(new Intent(Y_ProvisionFreeBooksMain.this, B_BuildingCondition.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (constructionStr.equals("True"))
                {
                    startActivity(new Intent(Y_ProvisionFreeBooksMain.this, B_Construction.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else if (schoolareaStr.equals("True"))
                {
                    startActivity(new Intent(Y_ProvisionFreeBooksMain.this, B_SchoolArea.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
                else {
                    startActivity(new Intent(Y_ProvisionFreeBooksMain.this, MoreInfo.class));
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    finish();
                }
            }
        });

    }

    public void saveObject(FormModel p) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(new File("/sdcard/save_object.bin")));
            oos.writeObject(p);
            oos.flush();

            oos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case Activity.RESULT_OK:
        }
    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(Y_ProvisionFreeBooksMain.this);
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
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            level = objform.getLevel();
            fillArray();
            ProvisionBooksListAdapter adpater = new ProvisionBooksListAdapter(
                    Y_ProvisionFreeBooksMain.this, provisionClassList,
                    provisionClassListView);
            provisionClassListView.setAdapter(adpater);

            provisionClassListView.setOnScrollListener(new AbsListView.OnScrollListener() {

                @Override
                public void onScrollStateChanged(AbsListView view,
                                                 int scrollState) {

                }

                @Override
                public void onScroll(AbsListView view, int firstVisibleItem,
                                     int visibleItemCount, int totalItemCount) {

                    Log.d(Integer.toString(firstVisibleItem), "First");
                    Log.d(Integer.toString(visibleItemCount), "Visible");
                    Log.d(Integer.toString(totalItemCount), "Total");
                    for (int i = firstVisibleItem; i < totalItemCount; i++) {
                        Log.d("I", Integer.toString(i));
                        try {
                            Boolean found = false;
                            for (int j = 0; j < objform.provisionSelectedList
                                    .size(); j++) {
                                Log.d("J", Integer.toString(j));
                                Log.d("..",
                                        Integer.toString(objform.provisionSelectedList
                                                .get(j).getPosition()
                                                - firstVisibleItem)
                                                + "=" + Integer.toString(i));
                                if (objform.provisionSelectedList
                                        .get(j).getPosition()
                                        - firstVisibleItem == i) {
                                    Log.d("Found", "Yes");
                                    found = true;
                                    break;
                                }
                            }

                            try {
                                if (found) {
                                    provisionClassListView
                                            .getChildAt(i)
                                            .setBackgroundColor(
                                                    Color.parseColor("#CEF6F5"));
                                } else {
                                    provisionClassListView.getChildAt(i)
                                            .setBackgroundColor(Color.WHITE);
                                }
                            } catch (Exception ex) {
                            }
                        } catch (Exception ex) {
                        }
                    }
                    // Toast.makeText(getActivity(), firstVisibleItem,
                    // Toast.LENGTH_SHORT).show();
                    // TODO Auto-generated method stub

                }
            });

            if (objform.provisionFreeBooksList.size() > 0) {
                // this.vp.setPagingEnabled(true);
            } else {
                // this.vp.setPagingEnabled(true);
            }

        }

    }

    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }*/

    @Override
    public void onResume() {

        /*for (int i = provisionClassListView.getFirstVisiblePosition(); i < provisionClassListView
                .getCount(); i++) {
            Log.d("I", Integer.toString(i));
            try {
                Boolean found = false;
                for (int j = 0; j < objform.provisionSelectedList
                        .size(); j++) {
                    Log.d("J", Integer.toString(j));
                    Log.d("..",
                            Integer.toString(objform.provisionSelectedList
                                    .get(j).getPosition()
                                    - provisionClassListView
                                    .getFirstVisiblePosition())
                                    + "=" + Integer.toString(i));
                    if (objform.provisionSelectedList.get(j)
                            .getPosition()
                            - provisionClassListView.getFirstVisiblePosition() == i) {
                        Log.d("Found", "Yes");
                        found = true;
                        break;
                    }
                }

                try {
                    if (found) {
                        provisionClassListView
                                .getChildAt(i)
                                .setBackgroundColor(Color.parseColor("#CEF6F5"));
                    } else {
                        provisionClassListView.getChildAt(i)
                                .setBackgroundColor(Color.WHITE);
                    }
                } catch (Exception ex) {
                }
            } catch (Exception ex) {
            }
        }*/
        // Toast.makeText(getActivity(), firstVisibleItem,
        // Toast.LENGTH_SHORT).show();
        // TODO Auto-generated method stub

        super.onResume();
    }

    public void fillArray() {
        provisionClassList = new ArrayList<ProvisionBooks>();

        provisionBooks = new ProvisionBooks();
        provisionBooks.setClassName("Class k");
        provisionClassList.add(provisionBooks);

        provisionBooks = new ProvisionBooks();
        provisionBooks.setClassName("Class 1");
        provisionClassList.add(provisionBooks);

        provisionBooks = new ProvisionBooks();
        provisionBooks.setClassName("Class 2");
        provisionClassList.add(provisionBooks);

        provisionBooks = new ProvisionBooks();
        provisionBooks.setClassName("Class 3");
        provisionClassList.add(provisionBooks);

        provisionBooks = new ProvisionBooks();
        provisionBooks.setClassName("Class 4");
        provisionClassList.add(provisionBooks);

        provisionBooks = new ProvisionBooks();
        provisionBooks.setClassName("Class 5");
        provisionClassList.add(provisionBooks);
        if (!level.equals("Primary") && !level.equals("Mosque")) {
            provisionBooks = new ProvisionBooks();
            provisionBooks.setClassName("Class 6");
            provisionClassList.add(provisionBooks);

            provisionBooks = new ProvisionBooks();
            provisionBooks.setClassName("Class 7");
            provisionClassList.add(provisionBooks);

            provisionBooks = new ProvisionBooks();
            provisionBooks.setClassName("Class 8");
            provisionClassList.add(provisionBooks);
            if (!level.equals("Middle")) {
                provisionBooks = new ProvisionBooks();
                provisionBooks.setClassName("Class 9");
                provisionClassList.add(provisionBooks);

                provisionBooks = new ProvisionBooks();
                provisionBooks.setClassName("Class 10");
                provisionClassList.add(provisionBooks);
                if (!level.equals("High")) {
                    provisionBooks = new ProvisionBooks();
                    provisionBooks.setClassName("Class 11");
                    provisionClassList.add(provisionBooks);

                    provisionBooks = new ProvisionBooks();
                    provisionBooks.setClassName("Class 12");
                    provisionClassList.add(provisionBooks);
                }
            }
        }
    }
}
