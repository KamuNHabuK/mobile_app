package com.s.shopapplication;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CartKeeper {

    private static final String SHARED_PREFS = "sharedPrefs";
    private static final String KEY = "cartKey";

    public static void saveProductToList(Context context, Product product){
        List<Product> products = getProductList(context);
        products.add(product);
        saveProductList(context, products);
    }

    public static void saveProductList(Context context, List<Product> products){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String jsonProductList = gson.toJson(products);
        editor.putString(KEY, jsonProductList);
        editor.apply();
    }

    public static List<Product> getProductList(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String jsonProductList = sharedPreferences.getString(KEY, null);
        List<Product> products = new ArrayList<Product>();
        if (jsonProductList != null){
            Gson gson = new Gson();
            Type type = new TypeToken<List<Product>>(){}.getType();
            products = gson.fromJson(jsonProductList, type);
            return products;
        } else {
            return new ArrayList<Product>();
        }
    }

    public static void removeProductList(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(KEY);
        editor.apply();
    }
}
