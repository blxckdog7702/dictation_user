package com.cbnu.sweng.randombox.dictation_user.dictation_user;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {

    private static Context context;
    private static Context mContext;

    public void onCreate() {
        super.onCreate();
        context = MyApplication.this;
        mContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return context;
    }

    public static Context getRootContext(){
        return mContext;
    }

}