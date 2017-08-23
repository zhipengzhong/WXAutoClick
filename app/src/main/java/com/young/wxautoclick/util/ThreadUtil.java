package com.young.wxautoclick.util;

import android.os.Handler;

import com.young.wxautoclick.app.WXAutoClickApplication;

/**
 * Created by 钟志鹏 on 2017/8/23.
 */

public class ThreadUtil {

    public static void sleepRun(Runnable runnable, int sleep) {
        WXAutoClickApplication.sHandler.postDelayed(runnable, sleep);
    }
}
