package com.example.module_ad.advertisement;

import android.app.Activity;
import android.content.Intent;
import android.widget.FrameLayout;
import com.example.module_base.base.BaseApplication;
import com.example.module_base.util.LogUtils;
import com.example.module_base.util.NetworkUtils;
import com.qq.e.ads.splash.SplashAD;
import com.qq.e.ads.splash.SplashADListener;
import com.qq.e.comm.managers.GDTAdSdk;
import com.qq.e.comm.util.AdError;

public class TXSplashAd extends AdWatcher{



    private SplashAD mSplashAD;
    private Activity mActivity;
    private boolean mIsClose;
    private Class mClass;
    private FrameLayout mSplashContainer;
    private int outTime=2000;


    public TXSplashAd(Activity activity, FrameLayout frameLayout, boolean isClose, Class aClass) {
        this.mActivity=activity;
        this.mSplashContainer=frameLayout;
        this.mIsClose=isClose;
        this.mClass=aClass;
        GDTAdSdk.init(activity, mKgdtMobSDKAppKey);
    }


    @Override
    public void showAd() {
        if (!NetworkUtils.isConnected(BaseApplication.Companion.getAppContext())) {
            return;
        }
        mSplashAD = new SplashAD(mActivity, mKgdtMobSDKKaiPingKey, new SplashADListener() {
            @Override
            public void onADDismissed() {
                LogUtils.i( "广告显示完毕---------------------->");
                goToMainActivity(mIsClose);
            }

            @Override
            public void onNoAD(AdError adError) {
                LogUtils.i( "广告加载失败---------------------->");
                if (mIShowAdCallback != null) {
                    mIShowAdCallback.onShowError();
                }

            }

            @Override
            public void onADPresent() {
                LogUtils.i("广告加载成功---------------------->");

            }

            @Override
            public void onADClicked() {
                LogUtils.i("广告被点击---------------------->");
            }

            @Override
            public void onADTick(long millisUntilFinished) {
                LogUtils.i( "广告倒计时---------------------->" + millisUntilFinished);
            }

            @Override
            public void onADExposure() {

            }

            @Override
            public void onADLoaded(long l) {

            }
        }, outTime);
        mSplashAD.setDownloadConfirmListener(GdtUtils.DOWNLOAD_CONFIRM_LISTENER);
        mSplashAD.fetchAndShowIn(mSplashContainer);
    }


    /**
     * 跳转到主页面
     */
    private void goToMainActivity(boolean b) {
        if (b) {
            mActivity.startActivity(new Intent(mActivity,mClass));
        }
        mActivity.finish();
    }
}
