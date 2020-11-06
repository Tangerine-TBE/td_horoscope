package com.example.module_usercenter.wxapi;

import android.content.Intent;
import android.transition.Fade;
import android.view.KeyEvent;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.module_base.base.BaseActivity;
import com.example.module_base.base.BaseApplication;
import com.example.module_base.provider.ModuleProvider;
import com.example.module_base.util.LogUtils;
import com.example.module_base.util.MyStatusBarUtil;
import com.example.module_base.util.PackageUtil;
import com.example.module_usercenter.R;
import com.example.module_usercenter.bean.LoginBean;
import com.example.module_usercenter.bean.RegisterBean;
import com.example.module_usercenter.bean.ThirdlyRegisterBean;
import com.example.module_usercenter.bean.WeiXinBean;
import com.example.module_usercenter.present.impl.WeChatPresentImpl;
import com.example.module_usercenter.ui.activity.BuyVipActivity;
import com.example.module_usercenter.utils.Contents;
import com.example.module_usercenter.utils.SpUtil;
import com.example.module_usercenter.view.IWeChatCallback;
import com.tamsiree.rxui.view.dialog.RxDialogShapeLoading;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class WXEntryActivity extends BaseActivity implements IWXAPIEventHandler, IWeChatCallback {


    private Map<String, String> mUserInfo;
    private WeChatPresentImpl mWeChatPresent;
    private String mOpenid;
    private RxDialogShapeLoading mRxDialogLoading;


    @Override
    public int getLayoutView() {
        return R.layout.activity_wechat;
    }

    @Override
    public void initView() {

        //登陆类型和信息
        mUserInfo = new HashMap<>();

        mRxDialogLoading = new RxDialogShapeLoading(this);
        mRxDialogLoading.setLoadingText("正在登陆...");
        mRxDialogLoading.setCancelable(false);
        mRxDialogLoading.show();
        getWindow().setEnterTransition( new Fade().setDuration(500));

        IWXAPI wxapi = WXAPIFactory.createWXAPI(this, Contents.WECHAT_APP_ID, false);
        wxapi.handleIntent(getIntent(), WXEntryActivity.this);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        LogUtils.i("onNewIntent--------------------》");
    }




    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                String code = ((SendAuth.Resp) baseResp).code;
                //获取accesstoken
                mWeChatPresent = WeChatPresentImpl.getInstance();
                mWeChatPresent.registerCallback(this);
                getAccessToken(code);
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED://用户拒绝授权
                finish();
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL://用户取消
                finish();
                break;
            default:
                finish();
                break;
        }

    }

    private void getAccessToken(String code) {
        Map<String, String> map = new HashMap<>();
        map.put(Contents.WXAPPID,Contents.WECHAT_APP_ID);
        map.put(Contents.WXSECRET,Contents.WECHAT_SECRET);
        map.put(Contents.WXACODE,code);
        map.put(Contents.WXTYPE,"authorization_code");
        if (mWeChatPresent != null) {
            mWeChatPresent.toWxAccredit(map);
        }
    }


    @Override
    public void onLoading() {

    }

    @Override
    public void onError() {

    }

    @Override
    public void release() {
        if (mWeChatPresent != null) {
            mWeChatPresent.unregisterCallback(this);
        }
    }

    @Override
    public void onWxAccreditSuccess(WeiXinBean weiXinBean) {
        LogUtils.i("onWxAccreditSuccess--------------------->");
        if (weiXinBean != null) {
            mOpenid = weiXinBean.getOpenid();
            Map<String, String> userInfo= new TreeMap<>();
            userInfo.put(Contents.OPENID, mOpenid);
            userInfo.put(Contents.TYPE,Contents.WECHAT_TYPE);
            mWeChatPresent.checkWxRegister(userInfo);
        }



    }

    @Override
    public void onWxAccreditError() {

    }

    @Override
    public void onCheckWxRegisterSuccess(RegisterBean bean) {
        int ret = bean.getRet();
        if (ret == 200 && bean.getData().equals("1")) {
            doWxLogin();
        }if (ret == 200 && bean.getData().equals("0")){
            doWxRegister();
        }

    }

    private void doWxLogin() {
        if (mWeChatPresent != null) {
            Map<String, String> userInfo = new TreeMap<>();
            userInfo.put(Contents.OPENID, mOpenid);
            userInfo.put(Contents.TYPE,Contents.WECHAT_TYPE);
            mWeChatPresent.toWxLogin(userInfo);
        }
    }

    private void doWxRegister() {
        if (mWeChatPresent != null) {
            Map<String, String> userInfo= new TreeMap<>();
            userInfo.put(Contents.TYPE,Contents.WECHAT_TYPE);
            userInfo.put(Contents.OPENID,mOpenid);
            userInfo.put(Contents.PACKAGE,Contents.APP_PACKAGE);
            userInfo.put(Contents.PLATFORM,  PackageUtil.getAppMetaData(this, Contents.PLATFORM_KEY));
            mWeChatPresent.toWxRegister(userInfo);
        }
    }

    @Override
    public void onCheckWxError() {

    }

    @Override
    public void onWxRegisterSuccess(ThirdlyRegisterBean thirdlyRegisterBean) {
        int ret = thirdlyRegisterBean.getRet();
        LogUtils.i( "onThirdlyRegisterSuccess----------------------->：" + ret);
        if (ret == 200) {
            doWxLogin();
            LogUtils.i( "onThirdlyRegisterSuccess---------200-------------->：" + ret);
        }
        if (ret==700) {
            if (thirdlyRegisterBean.getMsg().equals("该帐号已经注册")) {
                doWxLogin();
                LogUtils.i( "onThirdlyRegisterSuccess--------------700--------->：" );
            }

        }
    }

    @Override
    public void onWxRegisterError() {
        mRxDialogLoading.dismiss();
    }

    @Override
    public void onWxLoginSuccess(LoginBean loginBean) {
        int ret = loginBean.getRet();
        if (ret == 200) {
            boolean isBuyPager = getSharedPreferences(Contents.BUY_PAGER_SP, MODE_PRIVATE).getBoolean(Contents.BUY_PAGER, false);
            mRxDialogLoading.dismiss();
            Map<String, String> userType = SpUtil.saveUserType(Contents.WECHAT_TYPE, "","",mOpenid);
            SpUtil.saveUserInfo(loginBean, userType);
            if (isBuyPager) {
                Intent intent = new Intent(this, BuyVipActivity.class);
                startActivity(intent);
                finish();
            } else {
                ARouter.getInstance().build(ModuleProvider.ROUTE_MAIN_ACTIVITY).withInt(ModuleProvider.FRAGMENT_ID,3).navigation();
                finish();
            }
            LogUtils.i( "----------------------->：" + loginBean.getMsg());
        }

    }

    @Override
    public void onWxLoginError() {
        mRxDialogLoading.dismiss();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode== KeyEvent.KEYCODE_BACK)
            return true;//不执行父类点击事件
        return super.onKeyDown(keyCode, event);//继续执行父类其他点击事件
    }


}