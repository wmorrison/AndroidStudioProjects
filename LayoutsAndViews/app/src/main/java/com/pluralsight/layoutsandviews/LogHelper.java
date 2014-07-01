package com.pluralsight.layoutsandviews;

import android.app.Activity;
import android.util.Log;

/**
 * Created by Will on 6/20/2014.
 */
public class LogHelper {
    final static String LOG_TAG = "ACTIVITY_EVENT";

    public static void Log(Activity activity, String callbackName) {
        String logMsg = String.format("Activity : %s | Callback : %s", activity.getClass().getSimpleName(), callbackName);
        Log.d(LOG_TAG, logMsg);
    }
}
