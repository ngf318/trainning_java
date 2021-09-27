package com.test.neil.kavayi;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by neil on  2020/3/6
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {

        super.onCreate();
        initWebView();

        Log.d("nie", "=============");
    }

    private void initWebView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            Log.d("nie", "======1111=======");
            //判断不等于默认进程名称
            if (!isMainProcess()) {
                Toast.makeText(getApplicationContext(), "aaaaaaa", Toast.LENGTH_LONG).show();
                WebView.setDataDirectorySuffix(getProcessName(this, android.os.Process.myPid()));
            }
        }
    }

    protected boolean isMainProcess() {
        String processName = getProcessName(this, android.os.Process.myPid());
        Log.d("nie", "BaseApp isMainProcess progressName:" + processName +
                ", pid:" + android.os.Process.myPid() +
                ", this packageName:" + this.getPackageName());
        return processName != null && processName.equals(this.getPackageName());
    }

    public static String getProcessName(Context cxt, int pid) {
        ActivityManager am = (ActivityManager) cxt.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps != null) {
            for (ActivityManager.RunningAppProcessInfo info : runningApps) {
                if (info.pid == pid) {
                    return info.processName;
                }
            }
        }
        return null;
    }
}
