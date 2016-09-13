package com.sujian.lines.base.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.sujian.lines.C;
import com.sujian.lines.base.BaseActivity;
import com.sujian.lines.data.entity._User;


/**
 * SharedPreferences工具
 */
public class SpUtil {
    static SharedPreferences prefs;

    private static final int DEFAULT_CURRENT_ITEM = C.TYPE_ZHIHU;

    public static void init(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static boolean isNight() {
        return prefs.getBoolean("isNight", false);
    }

    public static void setNight(Context context, boolean isNight) {
        prefs.edit().putBoolean("isNight", isNight).commit();
        if (context instanceof BaseActivity)
            ((BaseActivity) context).reload();
    }

    public static _User getUser() {
        return new Gson().fromJson(prefs.getString("user", ""), _User.class);
    }

    public static void setUser(_User user) {
        prefs.edit().putString("user", new Gson().toJson(user)).commit();
    }

    public static void setFirst(){
        prefs.edit().putBoolean("isFirst",false).commit();
    }

    public static boolean getFirst(){
        return prefs.getBoolean("isFirst",true);
    }

    public static int getCurrentItem() {
        return prefs.getInt(C.SP_CURRENT_ITEM, DEFAULT_CURRENT_ITEM);
    }

    public static void setCurrentItem(int item) {
        prefs.edit().putInt(C.SP_CURRENT_ITEM, item).commit();
    }
}
