package com.softorea.schoolsen.adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.databasehelper.DatabaseHandler;
import com.softorea.schoolsen.models.FormModel;
import com.softorea.schoolsen.models.ProvisionFreeTextBookModel;


public class BooksDialog extends Dialog {

    Button save;
    ProvisionFreeTextBookModel provisionBooks;
    DatabaseHandler databaseHandler;

    FormModel objform;
    String className;
    String subjectNa;
    EditText booksDemand;
    EditText booksRecieved;
    EditText stdOutFtb;
    EditText surplusBooks;
    EditText booksReturn;
    int position;
    String tStdEnrolled;
    int totalstdenroll;
    Context context;
    String formId;
    int boladd = 0;



    public BooksDialog(final Context context, String classNo, String subjectName,
                       int pos) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.books_dialog);
        setCancelable(true);
        this.position = pos;
        this.className = classNo;
        this.subjectNa = subjectName;
        this.context = context;
        booksDemand = (EditText) findViewById(R.id.booksdemand);
        booksRecieved = (EditText) findViewById(R.id.booksrecieved);
        stdOutFtb = (EditText) findViewById(R.id.stdfb);
        surplusBooks = (EditText) findViewById(R.id.surplusbooks);
        booksReturn = (EditText) findViewById(R.id.bookreturnoffice);
        objform = new FormModel();
        databaseHandler = new DatabaseHandler(getContext());
        SQLiteDatabase db = databaseHandler.getReadableDatabase();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String emis = preferences.getString("emiscode", "");
        Cursor cursorform = db.rawQuery("SELECT * FROM t_roster WHERE emis= " + emis, null);
        if (cursorform.moveToFirst()) {
            do {
                formId = cursorform.getString(cursorform.getColumnIndex("RosterformId"));
            } while (cursorform.moveToNext());
        }
        cursorform.close();
        db.close();

        ProvisionFreeTextBookModel pbooks = databaseHandler.getftb(formId, className, subjectName);


        try {
            String bkdemand = pbooks.getBookDemand();
            String bkrecieved = pbooks.getBookRecieved();
            String stdoutftb = pbooks.getStdWithFTB();
            String surplsbk = pbooks.getSurplusBooks();
            String bkreturn = pbooks.getBooksReturnToOffice();

            if (!bkdemand.equals("Null") && !bkdemand.equals("")) {
                booksDemand.setText(bkdemand);
            }
            if (!bkrecieved.equals("Null")
                    && !bkrecieved.equals("")) {
                booksRecieved.setText(bkrecieved);
            }
            if (!stdoutftb.equals("Null") && !stdoutftb.equals("Null")) {
                stdOutFtb.setText(stdoutftb);
            }
            if (!surplsbk.equals("Null") && !surplsbk.equals("Null")) {
                surplusBooks.setText(surplsbk);
            }
            if (!bkreturn.equals("Null") && !bkreturn.equals("")) {
                booksReturn.setText(bkreturn);
            }

        } catch (IndexOutOfBoundsException e) {

        }
        save = (Button) findViewById(R.id.btn_savedialoge);
        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (booksDemand.getText().toString().equals("")
                        || booksRecieved.getText().toString().equals("")
                        || stdOutFtb.getText().toString().equals("")
                        || surplusBooks.getText().toString().equals("")
                        || booksReturn.getText().toString().equals("")) {
                    Toast.makeText(BooksDialog.this.context,
                            "Please fill all the values", Toast.LENGTH_SHORT)
                            .show();
                } else {

                    provisionBooks = new ProvisionFreeTextBookModel();
                    provisionBooks.setFormiD(formId);
                    provisionBooks.setClassNo(className.toString());
                    provisionBooks.setSubjectName(subjectNa.toString());
                    provisionBooks.setBookDemand(booksDemand.getText()
                            .toString());
                    provisionBooks.setBookRecieved(booksRecieved.getText()
                            .toString());
                    provisionBooks
                            .setStdWithFTB(stdOutFtb.getText().toString());
                    provisionBooks.setSurplusBooks(surplusBooks.getText()
                            .toString());
                    provisionBooks.setBooksReturnToOffice(booksReturn.getText()
                            .toString());


                    int stdftb = Integer.parseInt(stdOutFtb.getText()
                            .toString());
                    int bkrecieved = Integer.parseInt(booksRecieved.getText()
                            .toString());
                    int surbk = Integer.parseInt(surplusBooks.getText()
                            .toString());
                    int returnbk = Integer.parseInt(booksReturn.getText()
                            .toString());

                    databaseHandler.savefTP(provisionBooks);
                    dismiss();
                }

            }
        });
    }

}
