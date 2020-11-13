package com.example.module_ad.bean;

import com.example.module_ad.base.IBaseAdBean;
import com.example.module_ad.base.IBaseXXBean;

/**
 * @author wujinming QQ:1245074510
 * @name td_horoscope
 * @class name：com.example.module_ad.bean
 * @class describe
 * @time 2020/11/10 19:21
 * @class describe
 */
public class AdBean {

    /**
     * code : 0
     * message : 成功
     * data : {"start_page":{"spread_screen":{"status":true,"ad_origin":"gdt_toutiao","times":0,"change_times":300,"ad_percent":"0_100"}},"my_page":{"native_advertising":{"status":true,"ad_origin":"gdt_toutiao","times":0,"change_times":10,"ad_percent":"0_100"}},"exit_page":{"native_screen":{"status":true,"ad_origin":"gdt_toutiao","times":5,"change_times":10,"ad_percent":"0_100"}},"setting_page":{"incentive_video":{"status":false,"ad_origin":"gdt_toutiao","times":60,"change_times":10,"ad_percent":"0_100"}},"Advertisement":{"kTouTiaoAppKey":"5116466","kTouTiaoKaiPing":"887398493","kTouTiaoBannerKey":"945588019","kTouTiaoChaPingKey":"945588023","kTouTiaoJiLiKey":"945588026","kTouTiaoSeniorKey":"945588018","kTouTiaoSeniorSmallKey":"","ktouTiaoFullscreenvideoKey":"","kGDTMobSDKAppKey":"1111082723","kGDTMobSDKChaPingKey":"8081732914986379","kGDTMobSDKKaiPingKey":"6011138974187374","kGDTMobSDKBannerKey":"6031836984585378","kGDTMobSDKNativeKey":"2061536964584386","kGDTMobSDKJiLiKey":"7081738924880307"}}
     */

    private int code;
    private String message;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * start_page : {"spread_screen":{"status":true,"ad_origin":"gdt_toutiao","times":0,"change_times":300,"ad_percent":"0_100"}}
         * my_page : {"native_advertising":{"status":true,"ad_origin":"gdt_toutiao","times":0,"change_times":10,"ad_percent":"0_100"}}
         * exit_page : {"native_screen":{"status":true,"ad_origin":"gdt_toutiao","times":5,"change_times":10,"ad_percent":"0_100"}}
         * setting_page : {"incentive_video":{"status":false,"ad_origin":"gdt_toutiao","times":60,"change_times":10,"ad_percent":"0_100"}}
         * Advertisement : {"kTouTiaoAppKey":"5116466","kTouTiaoKaiPing":"887398493","kTouTiaoBannerKey":"945588019","kTouTiaoChaPingKey":"945588023","kTouTiaoJiLiKey":"945588026","kTouTiaoSeniorKey":"945588018","kTouTiaoSeniorSmallKey":"","ktouTiaoFullscreenvideoKey":"","kGDTMobSDKAppKey":"1111082723","kGDTMobSDKChaPingKey":"8081732914986379","kGDTMobSDKKaiPingKey":"6011138974187374","kGDTMobSDKBannerKey":"6031836984585378","kGDTMobSDKNativeKey":"2061536964584386","kGDTMobSDKJiLiKey":"7081738924880307"}
         */

        private StartPageBean start_page;
        private MyPageBean my_page;
        private ExitPageBean exit_page;
        private SettingPageBean setting_page;
        private AdvertisementBean Advertisement;

        public StartPageBean getStart_page() {
            return start_page;
        }

        public void setStart_page(StartPageBean start_page) {
            this.start_page = start_page;
        }

        public MyPageBean getMy_page() {
            return my_page;
        }

        public void setMy_page(MyPageBean my_page) {
            this.my_page = my_page;
        }

        public ExitPageBean getExit_page() {
            return exit_page;
        }

        public void setExit_page(ExitPageBean exit_page) {
            this.exit_page = exit_page;
        }

        public SettingPageBean getSetting_page() {
            return setting_page;
        }

        public void setSetting_page(SettingPageBean setting_page) {
            this.setting_page = setting_page;
        }

        public AdvertisementBean getAdvertisement() {
            return Advertisement;
        }

        public void setAdvertisement(AdvertisementBean Advertisement) {
            this.Advertisement = Advertisement;
        }

        public static class StartPageBean {
            /**
             * spread_screen : {"status":true,"ad_origin":"gdt_toutiao","times":0,"change_times":300,"ad_percent":"0_100"}
             */

            private SpreadScreenBean spread_screen;

            public SpreadScreenBean getSpread_screen() {
                return spread_screen;
            }

            public void setSpread_screen(SpreadScreenBean spread_screen) {
                this.spread_screen = spread_screen;
            }

            public static class SpreadScreenBean {
                /**
                 * status : true
                 * ad_origin : gdt_toutiao
                 * times : 0
                 * change_times : 300
                 * ad_percent : 0_100
                 */

                private boolean status;
                private String ad_origin;
                private int times;
                private int change_times;
                private String ad_percent;

                public boolean isStatus() {
                    return status;
                }

                public void setStatus(boolean status) {
                    this.status = status;
                }

                public String getAd_origin() {
                    return ad_origin;
                }

                public void setAd_origin(String ad_origin) {
                    this.ad_origin = ad_origin;
                }

                public int getTimes() {
                    return times;
                }

                public void setTimes(int times) {
                    this.times = times;
                }

                public int getChange_times() {
                    return change_times;
                }

                public void setChange_times(int change_times) {
                    this.change_times = change_times;
                }

                public String getAd_percent() {
                    return ad_percent;
                }

                public void setAd_percent(String ad_percent) {
                    this.ad_percent = ad_percent;
                }
            }
        }

        public static class MyPageBean implements IBaseAdBean {
            /**
             * native_advertising : {"status":true,"ad_origin":"gdt_toutiao","times":0,"change_times":10,"ad_percent":"0_100"}
             */

            private NativeAdvertisingBean native_advertising;

            public NativeAdvertisingBean getNative_advertising() {
                return native_advertising;
            }

            public void setNative_advertising(NativeAdvertisingBean native_advertising) {
                this.native_advertising = native_advertising;
            }

            @Override
            public IBaseXXBean getBaseBanner_screen() {
                return native_advertising;
            }

            @Override
            public IBaseXXBean getBaseNative_screen() {
                return native_advertising;
            }

            public static class NativeAdvertisingBean implements IBaseXXBean{
                /**
                 * status : true
                 * ad_origin : gdt_toutiao
                 * times : 0
                 * change_times : 10
                 * ad_percent : 0_100
                 */

                private boolean status;
                private String ad_origin;
                private int times;
                private int change_times;
                private String ad_percent;

                public boolean isStatus() {
                    return status;
                }

                public void setStatus(boolean status) {
                    this.status = status;
                }

                public String getAd_origin() {
                    return ad_origin;
                }

                public void setAd_origin(String ad_origin) {
                    this.ad_origin = ad_origin;
                }

                public int getTimes() {
                    return times;
                }

                public void setTimes(int times) {
                    this.times = times;
                }

                public int getChange_times() {
                    return change_times;
                }

                public void setChange_times(int change_times) {
                    this.change_times = change_times;
                }

                public String getAd_percent() {
                    return ad_percent;
                }

                public void setAd_percent(String ad_percent) {
                    this.ad_percent = ad_percent;
                }

                @Override
                public String getBaseAd_percent() {
                    return ad_percent;
                }

                @Override
                public boolean isBaseStatus() {
                    return status;
                }
            }
        }

        public static class ExitPageBean {
            /**
             * native_screen : {"status":true,"ad_origin":"gdt_toutiao","times":5,"change_times":10,"ad_percent":"0_100"}
             */

            private NativeScreenBean native_screen;

            public NativeScreenBean getNative_screen() {
                return native_screen;
            }

            public void setNative_screen(NativeScreenBean native_screen) {
                this.native_screen = native_screen;
            }

            public static class NativeScreenBean {
                /**
                 * status : true
                 * ad_origin : gdt_toutiao
                 * times : 5
                 * change_times : 10
                 * ad_percent : 0_100
                 */

                private boolean status;
                private String ad_origin;
                private int times;
                private int change_times;
                private String ad_percent;

                public boolean isStatus() {
                    return status;
                }

                public void setStatus(boolean status) {
                    this.status = status;
                }

                public String getAd_origin() {
                    return ad_origin;
                }

                public void setAd_origin(String ad_origin) {
                    this.ad_origin = ad_origin;
                }

                public int getTimes() {
                    return times;
                }

                public void setTimes(int times) {
                    this.times = times;
                }

                public int getChange_times() {
                    return change_times;
                }

                public void setChange_times(int change_times) {
                    this.change_times = change_times;
                }

                public String getAd_percent() {
                    return ad_percent;
                }

                public void setAd_percent(String ad_percent) {
                    this.ad_percent = ad_percent;
                }
            }
        }

        public static class SettingPageBean implements IBaseAdBean {
            /**
             * incentive_video : {"status":false,"ad_origin":"gdt_toutiao","times":60,"change_times":10,"ad_percent":"0_100"}
             */

            private IncentiveVideoBean incentive_video;

            public IncentiveVideoBean getIncentive_video() {
                return incentive_video;
            }

            public void setIncentive_video(IncentiveVideoBean incentive_video) {
                this.incentive_video = incentive_video;
            }

            @Override
            public IBaseXXBean getBaseBanner_screen() {
                return incentive_video;
            }

            @Override
            public IBaseXXBean getBaseNative_screen() {
                return incentive_video;
            }

            public static class IncentiveVideoBean implements IBaseXXBean{
                /**
                 * status : false
                 * ad_origin : gdt_toutiao
                 * times : 60
                 * change_times : 10
                 * ad_percent : 0_100
                 */

                private boolean status;
                private String ad_origin;
                private int times;
                private int change_times;
                private String ad_percent;

                public boolean isStatus() {
                    return status;
                }

                public void setStatus(boolean status) {
                    this.status = status;
                }

                public String getAd_origin() {
                    return ad_origin;
                }

                public void setAd_origin(String ad_origin) {
                    this.ad_origin = ad_origin;
                }

                public int getTimes() {
                    return times;
                }

                public void setTimes(int times) {
                    this.times = times;
                }

                public int getChange_times() {
                    return change_times;
                }

                public void setChange_times(int change_times) {
                    this.change_times = change_times;
                }

                public String getAd_percent() {
                    return ad_percent;
                }

                public void setAd_percent(String ad_percent) {
                    this.ad_percent = ad_percent;
                }

                @Override
                public String getBaseAd_percent() {
                    return ad_percent;
                }

                @Override
                public boolean isBaseStatus() {
                    return status;
                }
            }
        }

        public static class AdvertisementBean {
            /**
             * kTouTiaoAppKey : 5116466
             * kTouTiaoKaiPing : 887398493
             * kTouTiaoBannerKey : 945588019
             * kTouTiaoChaPingKey : 945588023
             * kTouTiaoJiLiKey : 945588026
             * kTouTiaoSeniorKey : 945588018
             * kTouTiaoSeniorSmallKey :
             * ktouTiaoFullscreenvideoKey :
             * kGDTMobSDKAppKey : 1111082723
             * kGDTMobSDKChaPingKey : 8081732914986379
             * kGDTMobSDKKaiPingKey : 6011138974187374
             * kGDTMobSDKBannerKey : 6031836984585378
             * kGDTMobSDKNativeKey : 2061536964584386
             * kGDTMobSDKJiLiKey : 7081738924880307
             */

            private String kTouTiaoAppKey;
            private String kTouTiaoKaiPing;
            private String kTouTiaoBannerKey;
            private String kTouTiaoChaPingKey;
            private String kTouTiaoJiLiKey;
            private String kTouTiaoSeniorKey;
            private String kTouTiaoSeniorSmallKey;
            private String ktouTiaoFullscreenvideoKey;
            private String kGDTMobSDKAppKey;
            private String kGDTMobSDKChaPingKey;
            private String kGDTMobSDKKaiPingKey;
            private String kGDTMobSDKBannerKey;
            private String kGDTMobSDKNativeKey;
            private String kGDTMobSDKJiLiKey;

            public String getKTouTiaoAppKey() {
                return kTouTiaoAppKey;
            }

            public void setKTouTiaoAppKey(String kTouTiaoAppKey) {
                this.kTouTiaoAppKey = kTouTiaoAppKey;
            }

            public String getKTouTiaoKaiPing() {
                return kTouTiaoKaiPing;
            }

            public void setKTouTiaoKaiPing(String kTouTiaoKaiPing) {
                this.kTouTiaoKaiPing = kTouTiaoKaiPing;
            }

            public String getKTouTiaoBannerKey() {
                return kTouTiaoBannerKey;
            }

            public void setKTouTiaoBannerKey(String kTouTiaoBannerKey) {
                this.kTouTiaoBannerKey = kTouTiaoBannerKey;
            }

            public String getKTouTiaoChaPingKey() {
                return kTouTiaoChaPingKey;
            }

            public void setKTouTiaoChaPingKey(String kTouTiaoChaPingKey) {
                this.kTouTiaoChaPingKey = kTouTiaoChaPingKey;
            }

            public String getKTouTiaoJiLiKey() {
                return kTouTiaoJiLiKey;
            }

            public void setKTouTiaoJiLiKey(String kTouTiaoJiLiKey) {
                this.kTouTiaoJiLiKey = kTouTiaoJiLiKey;
            }

            public String getKTouTiaoSeniorKey() {
                return kTouTiaoSeniorKey;
            }

            public void setKTouTiaoSeniorKey(String kTouTiaoSeniorKey) {
                this.kTouTiaoSeniorKey = kTouTiaoSeniorKey;
            }

            public String getKTouTiaoSeniorSmallKey() {
                return kTouTiaoSeniorSmallKey;
            }

            public void setKTouTiaoSeniorSmallKey(String kTouTiaoSeniorSmallKey) {
                this.kTouTiaoSeniorSmallKey = kTouTiaoSeniorSmallKey;
            }

            public String getKtouTiaoFullscreenvideoKey() {
                return ktouTiaoFullscreenvideoKey;
            }

            public void setKtouTiaoFullscreenvideoKey(String ktouTiaoFullscreenvideoKey) {
                this.ktouTiaoFullscreenvideoKey = ktouTiaoFullscreenvideoKey;
            }

            public String getKGDTMobSDKAppKey() {
                return kGDTMobSDKAppKey;
            }

            public void setKGDTMobSDKAppKey(String kGDTMobSDKAppKey) {
                this.kGDTMobSDKAppKey = kGDTMobSDKAppKey;
            }

            public String getKGDTMobSDKChaPingKey() {
                return kGDTMobSDKChaPingKey;
            }

            public void setKGDTMobSDKChaPingKey(String kGDTMobSDKChaPingKey) {
                this.kGDTMobSDKChaPingKey = kGDTMobSDKChaPingKey;
            }

            public String getKGDTMobSDKKaiPingKey() {
                return kGDTMobSDKKaiPingKey;
            }

            public void setKGDTMobSDKKaiPingKey(String kGDTMobSDKKaiPingKey) {
                this.kGDTMobSDKKaiPingKey = kGDTMobSDKKaiPingKey;
            }

            public String getKGDTMobSDKBannerKey() {
                return kGDTMobSDKBannerKey;
            }

            public void setKGDTMobSDKBannerKey(String kGDTMobSDKBannerKey) {
                this.kGDTMobSDKBannerKey = kGDTMobSDKBannerKey;
            }

            public String getKGDTMobSDKNativeKey() {
                return kGDTMobSDKNativeKey;
            }

            public void setKGDTMobSDKNativeKey(String kGDTMobSDKNativeKey) {
                this.kGDTMobSDKNativeKey = kGDTMobSDKNativeKey;
            }

            public String getKGDTMobSDKJiLiKey() {
                return kGDTMobSDKJiLiKey;
            }

            public void setKGDTMobSDKJiLiKey(String kGDTMobSDKJiLiKey) {
                this.kGDTMobSDKJiLiKey = kGDTMobSDKJiLiKey;
            }
        }
    }
}
