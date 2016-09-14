package com.sujian.lines.base.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.inputmethod.InputMethodManager;


import com.sujian.lines.R;
import com.sujian.lines.view.layout.LoadingPage;

import java.lang.reflect.Field;


public class ViewUtil {

    /**
     * activity自动findview
     */
    public static void autoFind(Activity activity) {
        try {
            Class<?> clazz = activity.getClass();
            Field[] fields = clazz.getDeclaredFields();// 获得Activity中声明的字段
            for (Field field : fields) {
                if (field.getGenericType().toString().contains("widget")
                        || field.getGenericType().toString().contains("view")
                        || field.getGenericType().toString()
                        .contains("WebView")) {// 找到所有的view和widget,WebView
                    try {
                        String name = field.getName();
                        Field idfield = R.id.class.getField(name);
                        int id = idfield.getInt(new R.id());// 获得view的id
                        field.setAccessible(true);
                        field.set(activity, activity.findViewById(id));// 给我们要找的字段设置值
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * Fragment以及ViewHolder等自动findview
     */

    public static void autoFind(Object obj, View view) {
        try {
            Class<?> clazz = obj.getClass();
            Field[] fields = clazz.getDeclaredFields();// 获得Activity中声明的字段
            for (Field field : fields) {
                if (field.getGenericType().toString().contains("widget")
                        || field.getGenericType().toString().contains("view")
                        || field.getGenericType().toString()
                        .contains("WebView")) {// 找到所有的view和widget
                    try {
                        String name = field.getName();
                        Field idfield = R.id.class.getField(name);
                        int id = idfield.getInt(new R.id());
                        field.setAccessible(true);
                        field.set(obj, view.findViewById(id));// 给我们要找的字段设置值
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * 隐藏软键盘
     */
    public static void hideKeyboard(Activity c) {
        try {
            InputMethodManager imm = (InputMethodManager) c
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(c.getCurrentFocus().getWindowToken(), 0);
        } catch (NullPointerException e) {
        }
    }

    public static void removeParent(View v) {
        //  先找到爹 在通过爹去移除孩子
        ViewParent parent = v.getParent();
        //所有的控件 都有爹  爹一般情况下 就是ViewGoup
        if (parent instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) parent;
            group.removeView(v);
        }
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
