package com.lim.duckpond;

import android.app.Activity;
import android.content.Intent;

public class ActivityHelper {

    // Method to open a new activity and close the current one
    public static void openNewActivity(Activity currentActivity, Class<?> targetActivity) {
        Intent intent = new Intent(currentActivity, targetActivity);
        currentActivity.startActivity(intent);
        currentActivity.finish();
    }
}
