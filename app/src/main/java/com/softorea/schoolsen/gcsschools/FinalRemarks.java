package com.softorea.schoolsen.gcsschools;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.lists.FormSaving;
import com.softorea.schoolsen.y_annual.MonitorSignature;

/**
 * Created by Softorea on 10/26/2017.
 */

public class FinalRemarks extends Activity {
    Button next, back;

    EditText et1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finalremarks);

        et1 = (EditText) findViewById(R.id.remarks);
        next = (Button) findViewById(R.id.btn_right);
        back = (Button) findViewById(R.id.btn_left);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FinalRemarks.this, FormSavingGcs.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FinalRemarks.this, HeadSignature.class));
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("finalremarksgcs", et1.getText().toString());
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences("STOREDVALUES", MODE_PRIVATE);
        String s = preferences.getString("finalremarksgcs", " ");
        et1.setText(s);
    }
}
