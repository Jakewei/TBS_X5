package com.xiong.tbs_x5;

import android.app.Application;

import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsListener;
import com.xiong.tbs_x5.utils.LogUtil;

/**
 * Created by xiongwenwei@aliyun.com
 * CreateTime: 2017/1/12
 * Note:BaseApplication
 */
public class BaseApplication extends Application {

    private static BaseApplication instance;
    private String TAG = "BaseApplication";

    @Override
    public void onCreate() {
        super.onCreate();

        LogUtil.i(TAG + ".onCreate()");
        instance = this;
        initTbs();
    }

    private void initTbs() {
        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                LogUtil.i("View是否初始化完成:" + arg0);
            }

            @Override
            public void onCoreInitFinished() {
                LogUtil.i("X5内核初始化完成");
            }
        };

        QbSdk.setTbsListener(new TbsListener() {
            @Override
            public void onDownloadFinish(int i) {
                LogUtil.i("腾讯X5内核 下载结束");
            }

            @Override
            public void onInstallFinish(int i) {
                LogUtil.i("腾讯X5内核 安装完成");
            }

            @Override
            public void onDownloadProgress(int i) {
                LogUtil.i("腾讯X5内核 下载进度:%" + i);
            }
        });

        QbSdk.initX5Environment(getApplicationContext(), cb);
    }

    public static BaseApplication getInstance() {
        return instance;
    }
}
