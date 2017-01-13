package com.xiong.tbs_x5.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import com.xiong.tbs_x5.BaseApplication;


/**
 * Created by llbt on 2016/4/25.
 */
public class ToastUtil {

    public static void show(Context context, Object object) {
        Toast toast = Toast.makeText(context, object + "", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void show(Object object) {
        Toast toast = Toast.makeText(BaseApplication.getInstance(), object + "", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

}
