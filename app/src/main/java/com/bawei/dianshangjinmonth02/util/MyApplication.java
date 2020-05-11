package com.bawei.dianshangjinmonth02.util;

import android.app.Application;
import android.content.Context;

/**
 * 全局对象
 */
public class MyApplication extends Application {
    private static Context context;
    public static Context getContext() {
        return context;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
