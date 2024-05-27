package com.s.shopapplication;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ClientInfoKeeper {
    private static final String SHARED_PREFS = "sharedPrefs";
    private static final String KEY_NAME = "clientName";
    private static final String KEY_NUMBER = "clientNumber";

    public static void saveClientInfo(Context context, String number, String name){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_NUMBER, number);
        editor.apply();
    }

    public static String getClientName(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        return sharedPreferences.getString(KEY_NAME, null);
    }

    public static String getClientNumber(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        return sharedPreferences.getString(KEY_NUMBER, null);
    }
}
