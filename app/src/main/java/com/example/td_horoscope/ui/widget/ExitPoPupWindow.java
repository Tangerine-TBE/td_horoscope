package com.example.td_horoscope.ui.widget;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.PopupWindow;

import com.example.module_ad.advertisement.AdType;
import com.example.module_ad.advertisement.FeedHelper;
import com.example.module_ad.utils.BaseBackstage;
import com.example.module_ad.utils.DeviceUtils;
import com.example.module_base.util.MyActivityManager;
import com.example.td_horoscope.R;



public class ExitPoPupWindow extends PopupWindow {

    private final View mView;
    private Button mCancelBt;
    private Button mSureBt;
    private final Activity mActivity;
    private FrameLayout mAdContainer;


    public ExitPoPupWindow(Activity activity) {
        super(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        this.mActivity=activity;
        int screenHeight = DeviceUtils.getScreenHeight(activity);
        mView = LayoutInflater.from(activity).inflate(R.layout.diy_exit_popup_window, null);
        setContentView(mView);
        setHeight(screenHeight);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setFocusable(true);
        setOutsideTouchable(false);




        setAnimationStyle(R.style.ExitPopup);
        intBgAnimation();
        initView();
        initEvent();

    }

    private void initEvent() {
        mCancelBt.setOnClickListener(view -> {
            mOutValueAnimator.start();
            dismiss();
            mAdContainer.removeAllViews();
        });

        mSureBt.setOnClickListener(view -> {

            dismiss();
            BaseBackstage.isExit=true;
            MyActivityManager.removeAllActivity();
            if (mFeedHelper != null) {
                mFeedHelper.releaseAd();
            }
        });

    }

    private void initView( ) {
        mCancelBt = mView.findViewById(R.id.cancel);
        mSureBt = mView.findViewById(R.id.sure);
        mAdContainer = mView.findViewById(R.id.exitAd_container);
    }

    private FeedHelper mFeedHelper;

    public void popupShowAd(Activity activity) {
          mInValueAnimator.start();
        mFeedHelper = new FeedHelper(activity, mAdContainer);
        mFeedHelper.showAd(AdType.EXIT_PAGE);
    }

    //设置窗口渐变
    private void updateBgWindowAlpha(float alpha) {
        Window window = mActivity.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.alpha = alpha;
        window.setAttributes(attributes);
    }


    private ValueAnimator mInValueAnimator;
    private ValueAnimator mOutValueAnimator;

    private void intBgAnimation() {
        mInValueAnimator = ValueAnimator.ofFloat(1.0f, 0.5f);
        mInValueAnimator.setDuration(300);
        mInValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                updateBgWindowAlpha((Float) animation.getAnimatedValue());
            }
        });

        mOutValueAnimator = ValueAnimator.ofFloat(0.5f, 1.0f);
        mOutValueAnimator.setDuration(300);
        mOutValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                updateBgWindowAlpha((Float) animation.getAnimatedValue());
            }
        });
    }

}