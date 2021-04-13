package com.home.tv;

import android.app.Application;

/**
 * @ClassName App
 * @Description TODO
 * @Author Administrator
 * @Date 2021/4/12 17:20
 * @Version 1.0
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JCrash.init(this, null, BuildConfig.APPLICATION_ID, BuildConfig.VERSION_NAME);
    }
}
