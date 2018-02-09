package com.softorea.schoolsen.lists;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Arslan on 10/28/2017.
 */

public class EnrollmentAttStrgcs {

    public static String unadmit_1, unadmit_2, unadmit_3, unadmit_4, unadmit_5, kachi1, kachi2, kachi3, kachi4,
            kachi5, One1, One2, One3, One4, One5, two1, two2, two3, two4, two5, three1, three2, three3, three4, three5, four1, four2, four3, four4, four5,
            five1, five2, five3, five4, five5;

    public void execute(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("STOREDVALUES", Activity.MODE_PRIVATE);
        unadmit_1 = preferences.getString("stdregistered_unadmitted", "");
        unadmit_2 = preferences.getString("stdpresent_unadmitted", "");
        unadmit_3 = preferences.getString("enrollment_girlsinboys_unadmitted", "");
        unadmit_4 = preferences.getString("enrollment_boysingirls_unadmitted", "");

        kachi1 = preferences.getString("stdregistered_kachi", "");
        kachi2 = preferences.getString("stdpresent_kachi", "");
        kachi3 = preferences.getString("enrollment_girlsinboys_kachi", "");
        kachi4 = preferences.getString("enrollment_boysingirls_kachi", "");

        One1 = preferences.getString("stdregistered_one", "");
        One2 = preferences.getString("stdpresent_one", "");
        One3 = preferences.getString("enrollment_girlsinboys_one", "");
        One4 = preferences.getString("enrollment_boysingirls_one", "");

        two1 = preferences.getString("stdregistered_two", "");
        two2 = preferences.getString("stdpresent_two", "");
        two3 = preferences.getString("enrollment_girlsinboys_two", "");
        two4 = preferences.getString("enrollment_boysingirls_two", "");

        three1 = preferences.getString("stdregistered_three", "");
        three2 = preferences.getString("stdpresent_three", "");
        three3 = preferences.getString("enrollment_girlsinboys_three", "");
        three4 = preferences.getString("enrollment_boysingirls_three", "");

        four1 = preferences.getString("stdregistered_four", "");
        four2 = preferences.getString("stdpresent_four", "");
        four3 = preferences.getString("enrollment_girlsinboys_four", "");
        four4 = preferences.getString("enrollment_boysingirls_four", "");

        five1 = preferences.getString("stdregistered_five", "");
        five2 = preferences.getString("stdpresent_five", "");
        five3 = preferences.getString("enrollment_girlsinboys_five", "");
        five4 = preferences.getString("enrollment_boysingirls_five", "");

    }
}
