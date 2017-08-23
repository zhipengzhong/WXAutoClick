package com.young.wxautoclick.app;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * Created by 钟志鹏 on 2017/8/23.
 */

public class WXAutoClickApplication extends Application {

    public static Context sContext;
    public static Handler sHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        sHandler = new Handler(WXAutoClickApplication.sContext.getMainLooper());
    }
}
