package com.example.module_ad.advertisement;

import android.app.Activity;
import android.widget.FrameLayout;
import com.example.module_ad.base.IShowAdCallback;
import com.example.module_ad.bean.AdBean;
import com.example.module_ad.utils.AdProbabilityUtil;
import com.example.module_ad.utils.AdMsgUtil;
import com.example.module_usercenter.utils.SpUtil;

public class FeedHelper {

    private Activity mActivity;
    private FrameLayout mFeedAdContainer;
    private boolean mAddToutiaoAdError=false;
    private boolean mAddTengxunAdError=false;
    private TTFeedAd mTTFeedAd;
    private TXFeedAd mTxFeedAd;

    public FeedHelper(Activity activity,FrameLayout frameLayout) {
        this.mActivity=activity;
        this.mFeedAdContainer=frameLayout;
    }


    public void showAd() {
        if (SpUtil.isVIP()) {
            return;}
        //拿到缓存接口信息
        if (AdMsgUtil.isHaveAdData()) {
            AdBean.DataBean.MyPageBean.NativeAdvertisingBean native_advertising = AdMsgUtil.getAdState().getMy_page().getNative_advertising();
            //判断时候展示广告
            if (native_advertising.isStatus()) {
                if (Math.random() >AdProbabilityUtil.showAdProbability( native_advertising.getAd_percent())) {
                    //初始化广告
                    showTTFeedAd();
                } else {
                    showTXFeedAd();
                }
            }
        }
    }



    //头条
    private void showTTFeedAd() {
        mTTFeedAd = new TTFeedAd(mActivity, mFeedAdContainer);
        mTTFeedAd.showAd();
        mTTFeedAd.setOnShowError(new IShowAdCallback() {
            @Override
            public void onShowError() {
                if (!mAddToutiaoAdError) {
                    showTXFeedAd();
                }
                mAddToutiaoAdError=true;
            }
        });

    }

    //腾讯
    private void showTXFeedAd() {
        mTxFeedAd = new TXFeedAd(mActivity, mFeedAdContainer);
        mTxFeedAd.showAd();
        mTxFeedAd.setOnShowError(new IShowAdCallback() {
            @Override
            public void onShowError() {
                if (!mAddTengxunAdError) {
                    showTTFeedAd();
                }
                mAddTengxunAdError=true;
            }
        });

    }

    public void releaseAd() {
        if (mTTFeedAd != null) {
            mTTFeedAd.releaseAd();
        }

        if (mTxFeedAd!=null) {
            mTxFeedAd.releaseAd();
        }
    }


}
