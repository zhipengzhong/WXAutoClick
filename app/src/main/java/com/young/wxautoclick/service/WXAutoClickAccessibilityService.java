package com.young.wxautoclick.service;

import android.accessibilityservice.AccessibilityService;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import com.young.wxautoclick.util.ClickUtil;
import com.young.wxautoclick.util.ThreadUtil;

import java.util.List;

/**
 * Created by 钟志鹏 on 2017/8/23.
 */

public class WXAutoClickAccessibilityService extends AccessibilityService {

    private static final String TAG = "WXAutoClickAccessibilit";

    public WXAutoClickAccessibilityService() {
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        CharSequence className = event.getClassName();
        switch (event.getEventType()) {
            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:
                Log.d(TAG, "onAccessibilityEvent: container_changed|" + event.getClassName());
                break;
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
                Log.d(TAG, "onAccessibilityEvent: state_changed|" + event.getClassName());
                break;
            case AccessibilityEvent.TYPE_WINDOWS_CHANGED:
                Log.d(TAG, "onAccessibilityEvent: windows_changed|" + event.getClassName());
                break;
        }
//        ClickUtil.findTextAndClick(this,"更多功能按钮");
//        Log.d(TAG, "onAccessibilityEvent: " + event.getAction() + "|" + className.toString());
        if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            if ("com.tencent.mm.ui.LauncherUI".equals(className)) {
                ThreadUtil.sleepRun(new Runnable() {
                    @Override
                    public void run() {
                        AccessibilityNodeInfo nodeInfo = ClickUtil.findText(WXAutoClickAccessibilityService.this, "搜索");
                        ClickUtil.performClick(nodeInfo);
                    }
                }, 100);
            } else if ("com.tencent.mm.plugin.search.ui.FTSMainUI".equals(className)) {
                ThreadUtil.sleepRun(new Runnable() {
                    @Override
                    public void run() {
                        AccessibilityNodeInfo nodeInfo = ClickUtil.findId(WXAutoClickAccessibilityService.this, "com.tencent.mm:id/h2");
                        ClickUtil.performInput(nodeInfo, "903531550");
                        ThreadUtil.sleepRun(new Runnable() {
                            @Override
                            public void run() {
                                AccessibilityNodeInfo nodeInfo1 = ClickUtil.findId(WXAutoClickAccessibilityService.this, "com.tencent.mm:id/b20");
                                ClickUtil.performClick(nodeInfo1);
                            }
                        }, 500);
                    }
                }, 100);
            } else if ("com.tencent.mm.plugin.profile.ui.ContactInfoUI".equals(className)) {
                ThreadUtil.sleepRun(new Runnable() {
                    @Override
                    public void run() {
                        AccessibilityNodeInfo nodeInfo1 = ClickUtil.findText(WXAutoClickAccessibilityService.this, "添加到通讯录");
                        ClickUtil.performClick(nodeInfo1);
                    }
                }, 100);
            } else if ("com.tencent.mm.plugin.profile.ui.SayHiWithSnsPermissionUI".equals(className)) {
                ThreadUtil.sleepRun(new Runnable() {
                    @Override
                    public void run() {
                        AccessibilityNodeInfo nodeInfo = ClickUtil.findText(WXAutoClickAccessibilityService.this, "发送");
                        ClickUtil.performClick(nodeInfo);
                    }
                }, 100);
            }
        }

    }


    @Override
    public void onInterrupt() {

    }
}
