package com.example.module_usercenter.view;


import com.example.module_usercenter.base.IBaseCallback;
import com.example.module_usercenter.bean.RegisterBean;

public interface ILogoutCallback extends IBaseCallback {

    void onLogoutSuccess(RegisterBean registerBean);

    void onLogoutError();
}
