package com.example.module_usercenter.utils;

import android.content.SharedPreferences;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.module_base.base.BaseApplication;
import com.example.module_base.util.SPUtil;
import com.example.module_usercenter.R;
import com.example.module_usercenter.bean.LoginBean;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SpUtil {


    public static Map<String, String> saveUserType(String type, String number, String pwd, String openId) {
        Map<String, String> mUserInfo = new HashMap<>();
        mUserInfo.put(Contents.USER_ID_TYPE, type);
        mUserInfo.put(Contents.USER_ACCOUNT, number);
        mUserInfo.put(Contents.USER_PWD, pwd);
        mUserInfo.put(Contents.USER_THIRDLY_OPENID, openId);
        return mUserInfo;
    }


    public static void saveUserInfo(LoginBean loginBean, Map<String, String> userInfo) {
        SPUtil.getInstance().putBoolean(Contents.USER_IS_LOGIN, true)
                .putString(Contents.USER_ID, loginBean.getData().getId() + "")
                .putInt(Contents.USER_VIP_LEVEL, loginBean.getData().getVip())
                .putString(Contents.USER_VIP_TIME, loginBean.getData().getVipexpiretime())
                .putString(Contents.USER_ID_TYPE, userInfo.get(Contents.USER_ID_TYPE))
                .putString(Contents.USER_ACCOUNT, userInfo.get(Contents.USER_ACCOUNT))
                .putString(Contents.USER_PWD, userInfo.get(Contents.USER_PWD))
                .putString(Contents.USER_THIRDLY_OPENID, userInfo.get(Contents.USER_THIRDLY_OPENID))
                .putLong(Contents.USER_LOGIN_TIME, System.currentTimeMillis());

    }

    public static void deleteUserInfo() {
        SPUtil.getInstance().putString(Contents.USER_ID, "")
                .putInt(Contents.USER_VIP_LEVEL, 0)
                .putString(Contents.USER_VIP_TIME, "")
                .putString(Contents.USER_ID_TYPE, "")
                .putString(Contents.USER_ACCOUNT, "")
                .putString(Contents.USER_PWD, "")
                .putString(Contents.USER_THIRDLY_OPENID, "")
                .putBoolean(Contents.USER_IS_LOGIN, false);
    }


    private static final int sLoginTime = 30;

    public static boolean loginTimeOut() {
        long startTime = SPUtil.getInstance().getLong(Contents.USER_LOGIN_TIME, 0);
        boolean isLogin = SPUtil.getInstance().getBoolean(Contents.USER_IS_LOGIN, false);
        if (isLogin & startTime != 0) {
            Date startDate = new Date(startTime);
            Date stopDate = new Date(System.currentTimeMillis());
            // 这样得到的差值是微秒级别
            long diff = stopDate.getTime() - startDate.getTime();
            long days = diff / (1000 * 60 * 60 * 24);
            if (days > sLoginTime) {
                deleteUserInfo();
                return true;
            }
        }
        return false;
    }

    public static void changePwdShow(EditText mPassWord_edit, ImageView mShowPassWord_image, boolean isShow) {
        if (isShow) {
            mShowPassWord_image.setImageResource(R.mipmap.pwd_show_select);
            mPassWord_edit.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            mShowPassWord_image.setImageResource(R.mipmap.pwd_show_normal);
            mPassWord_edit.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }

    public static void changePwdShow2(EditText mPassWord_edit, ImageView mShowPassWord_image, boolean isShow) {
        if (isShow) {
            //mShowPassWord_image.setImageResource(R.mipmap.login_pwd_show_select);
            mPassWord_edit.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            //  mShowPassWord_image.setImageResource(R.mipmap.login_pwd_show_normal);
            mPassWord_edit.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }

}
