package com.example.module_ad.utils;
import android.text.TextUtils;

import com.example.module_ad.bean.AdBean;
import com.example.module_base.util.SPUtil;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;


/**
 * @author: Administrator
 * @date: 2020/7/11 0011
 */
public class AdMsgUtil {
    public static AdBean.DataBean getAdState() {
        String ad = SPUtil.getInstance().getString(Contents.AD_INFO, "");
        if (!TextUtils.isEmpty(ad)) {
            AdBean.DataBean dataBean =new Gson().fromJson(ad, AdBean.DataBean.class);
            return dataBean;
        }
        return  null;
    }

    public static  Map<String, String>  getADKey() {
        Map<String, String> map = new HashMap<>();
        String ad = SPUtil.getInstance().getString(Contents.AD_INFO, "");
        if (!TextUtils.isEmpty(ad)) {
            AdBean.DataBean dataBean = new Gson().fromJson(ad, AdBean.DataBean.class);
            //广告信息
            AdBean.DataBean.AdvertisementBean advertisement = dataBean.getAdvertisement();
            if (advertisement!=null) {
            //穿山甲广告
            String kTouTiaoAppKey = advertisement.getKTouTiaoAppKey();
            String kTouTiaoKaiPing = advertisement.getKTouTiaoKaiPing();
            String kTouTiaoBannerKey = advertisement.getKTouTiaoBannerKey();
            String kTouTiaoChaPingKey = advertisement.getKTouTiaoChaPingKey();
            String kTouTiaoJiLiKey = advertisement.getKTouTiaoJiLiKey();
            String kTouTiaoSeniorKey = advertisement.getKTouTiaoSeniorKey();
            map.put(Contents.KT_OUTIAO_APPKEY, kTouTiaoAppKey);
            map.put(Contents.KT_OUTIAO_KAIPING, kTouTiaoKaiPing);
            map.put(Contents.KT_OUTIAO_BANNERKEY, kTouTiaoBannerKey);
            map.put(Contents.KT_OUTIAO_CHAPINGKEY, kTouTiaoChaPingKey);
            map.put(Contents.KT_OUTIAO_JILIKEY, kTouTiaoJiLiKey);
            map.put(Contents.KT_OUTIAO_SENIORKEY, kTouTiaoSeniorKey);

            //广点通广告
            String kgdtMobSDKAppKey = advertisement.getKGDTMobSDKAppKey();
            String kgdtMobSDKChaPingKey = advertisement.getKGDTMobSDKChaPingKey();
            String kgdtMobSDKKaiPingKey = advertisement.getKGDTMobSDKKaiPingKey();
            String kgdtMobSDKBannerKey = advertisement.getKGDTMobSDKBannerKey();
            String kgdtMobSDKNativeKey = advertisement.getKGDTMobSDKNativeKey();
            String kgdtMobSDKJiLiKey = advertisement.getKGDTMobSDKJiLiKey();

            map.put(Contents.KGDT_MOBSDK_APPKEY, kgdtMobSDKAppKey);
            map.put(Contents.KGDT_MOBSDK_CHAPINGKEY, kgdtMobSDKChaPingKey);
            map.put(Contents.KGDT_MOBSDK_KAIPINGKEY, kgdtMobSDKKaiPingKey);
            map.put(Contents.KGDT_MOBSDK_BANNERKEY, kgdtMobSDKBannerKey);
            map.put(Contents.KGDT_MOBSDK_NATIVEKEY, kgdtMobSDKNativeKey);
            map.put(Contents.KGDT_MOBSDK_JILIKEY, kgdtMobSDKJiLiKey);

            return map;
        }
        }
        return null;
    }


    public static boolean isHaveAdData() {
        AdBean.DataBean dataBean =getAdState();
        Map<String, String> adKey = getADKey();
        if (dataBean != null&adKey!=null) {
            return true;
        }
        return false;
    }




}
