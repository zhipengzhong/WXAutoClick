package com.young.wxautoclick.util;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;

import com.young.wxautoclick.app.WXAutoClickApplication;

import java.util.List;

/**
 * Created by 钟志鹏 on 2017/8/23.
 */

public class ClickUtil {

    private static final String TAG = "ClickUtil";

    /**
     * 通过Text查找View
     *
     * @param as
     * @param text 查找的Text
     */
    public static AccessibilityNodeInfo findText(AccessibilityService as, String text) {
        AccessibilityNodeInfo activeWindow = as.getRootInActiveWindow();
        if (activeWindow != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                List<AccessibilityNodeInfo> infos = activeWindow.findAccessibilityNodeInfosByText(text);
                if (infos != null && !infos.isEmpty()) {
                    for (AccessibilityNodeInfo nodeInfo : infos) {
                        if ((nodeInfo != null && (text.equals(nodeInfo.getText())) || text.equals(nodeInfo.getContentDescription()))) {
                            return nodeInfo;
                        }
                    }
                }
            }
        }
        return activeWindow;
    }

    /**
     * 通过ID查找View
     *
     * @param as
     * @param id 查找的ID
     */
    public static AccessibilityNodeInfo findId(AccessibilityService as, String id) {
        AccessibilityNodeInfo activeWindow = as.getRootInActiveWindow();
        if (activeWindow != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                List<AccessibilityNodeInfo> infos = activeWindow.findAccessibilityNodeInfosByViewId(id);
                if (infos != null && !infos.isEmpty()) {
                    for (AccessibilityNodeInfo info : infos) {
                        return info;
                    }
                }
            }
        }
        return activeWindow;
    }

    /**
     * 往上查找可点击按钮
     *
     * @param nodeInfo
     */

    public static void performClick(AccessibilityNodeInfo nodeInfo) {
        if (nodeInfo == null) {
            return;
        }
//        for (int i = 0; i < nodeInfo.getChildCount(); i++) {
//            AccessibilityNodeInfo child = nodeInfo.getChild(i);
//            if (child.isClickable()) {
//                nodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
//            }
//        }

        if (nodeInfo.isClickable()) {
            nodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
        } else {
            performClick(nodeInfo.getParent());
        }
    }

    public static void performInput(AccessibilityNodeInfo nodeInfo, String str) {
        if (nodeInfo == null) {
            return;
        }
        Log.d(TAG, "performInput: " + nodeInfo.getClassName());
        if ("android.widget.EditText".equals(nodeInfo.getClassName())) {
            ClipboardManager clipboardManager = (ClipboardManager) WXAutoClickApplication.sContext.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("scb", str);
            clipboardManager.setPrimaryClip(clipData);
            nodeInfo.performAction(AccessibilityNodeInfo.ACTION_PASTE);
        } else {
            performInput(nodeInfo.getParent(), str);
        }
    }
}
