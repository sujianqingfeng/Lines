package com.sujian.lines;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.sujian.lines.base.util.SpUtil;

import java.util.HashSet;
import java.util.Set;


public class App extends Application {
    private static App mApp;
    private Set<Activity> allActivities;
    public static int SCREEN_WIDTH = -1;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        SpUtil.init(this);
        //初始化log
        Logger
                .init("SuJian Lines") // default PRETTYLOGGER or use just init()
                .methodCount(0)                 // default 2
                //.hideThreadInfo()               // default shown
                .logLevel(LogLevel.FULL)        // default LogLevel.FULL
                .methodOffset(2)  ;              // default 0

    }

    public synchronized static App getAppContext() {
        return mApp;
    }

    public static Resources getAppResources() {
        return mApp.getResources();
    }

    public  void addActivity(Activity act) {
        if (allActivities == null) {
            allActivities = new HashSet<Activity>();
        }
        allActivities.add(act);
    }

    public void removeActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
    }

    public void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity act : allActivities) {
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

}
