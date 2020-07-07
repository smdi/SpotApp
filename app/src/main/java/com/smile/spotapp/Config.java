package com.smile.spotapp;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Melon on 10/10/2017.
 */

public class Config {

    // global topic to receive app wide push notifications
    public static final String TOPIC_GLOBAL = "global";

    // broadcast receiver intent filters
    public static final String REGISTRATION_COMPLETE = "registrationComplete";
    public static final String PUSH_NOTIFICATION = "pushNotification";

    public static final int NOTIFICATION_ID_BIG_IMAGE = 101;

    public static final String SHARED_PREF = "ah_firebase";
    public static int NOTIFICATION_ID = 0;

    public static final String TIME_KEY = "time";
    public static final String PREFS_NAME = "USER_DETAILS";
    public static final String PREFS_TOKEN = "token";
    public static long AD_TIMER = 30000;
    public static String TOOLBAR_TITLE = "सरकारी नौकरी";
    static Context context;
    static SharedPreferences settings;
    static SharedPreferences.Editor editor;

    public static String AD_CODE = "ca-app-pub-8801321225174646/6523467679";

    public static void setTime(Context context, long position) {
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //1
        editor = settings.edit();
        editor.putLong(TIME_KEY, position);
        editor.commit();
    }

    public static long getTime(Context context) {
        long time;
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        time = settings.getLong(TIME_KEY, AD_TIMER);
        return time;
    }

    public static void clearTime(Context context) {
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();
        editor.remove(TIME_KEY);
        editor.commit();
    }

    public static void setPrefToken(Context context, String token) {
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //1
        editor = settings.edit();
        editor.putString(PREFS_TOKEN, token);
        editor.apply();
    }

    public static String getPrefToken(Context context) {
        String token;
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        token = settings.getString(PREFS_TOKEN, null);
        return token;
    }

}
