package com.xiong.tbs_x5.utils;

import android.util.Log;

/**
 * Created by xiongwenwei@aliyun.com
 * CreateTime: 2017/1/12
 * Note:
 */
public class LogUtil {

    private static String TAG = "打印日志";

    public static void i(Object object) {
        Log.i(TAG, object + "");
    }
}
