package com.ppui.calculator;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreferences {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    final static String APP_THEME = "appTheme";
    final static String APP_FIRST_LAUNCH = "AppFirstLaunch";
    final static String APP_ANSWER_PRECISION = "precision";
    final static String APP_ANGLE = "angle";
    final static String APP_HISTORY = "history";
    final static String APP_EQUATION_STRING = "app.equation.string";
    final static String APP_NUMBER_FORMATTER = "app.number.formatter";
    final static String APP_SMART_CALCULATIONS = "app.smart.calculations";
    final static String APP_HISTORY_SET = "app.is.history.set";
    final static String APP_HISTORY_EQUATION = "app.history.equation";
    final static String APP_MEMORY_VALUE = "app.memory.value";
    final static String APP_SCIENTIFIC_RESULT = "app.scientific.string";

    public AppPreferences(Context context) {
        String SHARED_PREF_STRING = "com.ppui.calculator";
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_STRING, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public String getStringPreference(String key) {
        return sharedPreferences.getString(key, "");
    }

    public void setStringPreference(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public static AppPreferences getInstance(Context context) {
        return new AppPreferences(context);
    }

    public boolean getBooleanPreference(String key) {
        return sharedPreferences.getBoolean(key, true);
    }

    public void setBooleanPreference(String key, Boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

}
