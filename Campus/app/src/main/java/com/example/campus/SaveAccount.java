package com.example.campus;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

public class SaveAccount {

    public static boolean saveUserInfo(Context context, String account,
                                       String password) {
        SharedPreferences sp = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("userName", account);
        edit.putString("password", password);
        edit.commit();
        return true;
    }

    public static Map<String, String> getUserInfo(Context context) {
        SharedPreferences sp = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        String account = sp.getString("userName", null);
        String password = sp.getString("password", null);
        Map<String, String> userMap = new HashMap<>();
        userMap.put("userName", account);
        userMap.put("password", password);
        return userMap;
    }
}
