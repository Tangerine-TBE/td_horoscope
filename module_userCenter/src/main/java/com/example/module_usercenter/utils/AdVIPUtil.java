package com.example.module_usercenter.utils;


import com.example.module_base.base.BaseApplication;

public class AdVIPUtil {

    public static boolean isVIP() {
        int vipLevel = BaseApplication.Companion.getAppContext().getSharedPreferences(Contents.USER_INFO, BaseApplication.Companion.getAppContext().MODE_PRIVATE).getInt(Contents.USER_VIP_LEVEL, 0);
        return vipLevel>0?true:false;
    }
}
