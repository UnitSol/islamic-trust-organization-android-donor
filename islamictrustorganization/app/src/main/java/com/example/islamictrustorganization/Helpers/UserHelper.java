package com.example.islamictrustorganization.Helpers;

import android.content.Context;
import android.content.SharedPreferences;

public class UserHelper {
    private static final String USER_HELPER = "USER_HELPER";

    // properties
    private static final String LOGGED_IN_USER_DATA = "LOGGED_IN_USER_DATA";
    private static final String LOGGED_IN_USER_ID = "LOGGED_IN_USER_ID";
    private static final String LOGGED_IN_USER_TYPE = "LOGGED_IN_USER_TYPE";
    private static final String LOGGED_IN_USER_MOBILE = "LOGGED_IN_USER_MOBILE";
    private static final String LOGGED_IN_USER_PHONE = "LOGGED_IN_USER_PHONE";
    private static final String LOGGED_IN_USER_EMAIL = "LOGGED_IN_USER_EMAIL";

    private UserHelper() {
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(USER_HELPER, Context.MODE_PRIVATE);
    }

    public static String getLoggedInUserData(Context context) {
        return getSharedPreferences(context).getString(LOGGED_IN_USER_DATA, null);
    }

    public static void setLoggedInUserData(Context context, String newValue) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(LOGGED_IN_USER_DATA, newValue);
        editor.commit();
    }

    public static String getLoggedInUserID(Context context) {
        return getSharedPreferences(context).getString(LOGGED_IN_USER_ID, null);
    }

    public static void setLoggedInUserID(Context context, String userID) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(LOGGED_IN_USER_ID, userID);
        editor.commit();
    }

    public static String getLoggedInUserType(Context context) {
        return getSharedPreferences(context).getString(LOGGED_IN_USER_TYPE, null);
    }

    public static void setLoggedInUserType(Context context, String userID) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(LOGGED_IN_USER_TYPE, userID);
        editor.commit();
    }

    public static String getLoggedInUserEmail(Context context) {
        return getSharedPreferences(context).getString(LOGGED_IN_USER_EMAIL, null);
    }

    public static void setLoggedInUserEmail(Context context, String userID) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(LOGGED_IN_USER_EMAIL, userID);
        editor.commit();
    }

    public static String getLoggedInUserMobile(Context context) {
        return getSharedPreferences(context).getString(LOGGED_IN_USER_MOBILE, null);
    }

    public static void setLoggedInUserMobile(Context context, String userID) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(LOGGED_IN_USER_MOBILE, userID);
        editor.commit();
    }

    public static String getLoggedInUserPhone(Context context) {
        return getSharedPreferences(context).getString(LOGGED_IN_USER_PHONE, null);
    }

    public static void setLoggedInUserPhone(Context context, String userID) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(LOGGED_IN_USER_PHONE, userID);
        editor.commit();
    }
}
