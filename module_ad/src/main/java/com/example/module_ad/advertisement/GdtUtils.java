package com.example.module_ad.advertisement;

import static com.example.module_ad.DownloadApkConfirmDialog.getApkJsonInfoUrl;

import android.app.Activity;

import com.example.module_ad.DownloadApkConfirmDialog;
import com.qq.e.comm.compliance.DownloadConfirmCallBack;
import com.qq.e.comm.compliance.DownloadConfirmListener;

public class GdtUtils {

    static DownloadConfirmListener DOWNLOAD_CONFIRM_LISTENER = new DownloadConfirmListener() {
        @Override
        public void onDownloadConfirm(Activity activity, int i, String s, DownloadConfirmCallBack downloadConfirmCallBack) {
            new DownloadApkConfirmDialog(activity, getApkJsonInfoUrl(s), downloadConfirmCallBack).show();
        }
    };
//    val DOWNLOAD_CONFIRM_LISTENER =
//            DownloadConfirmListener { context, scenes, infoUrl, callBack ->
//            DownloadApkConfirmDialog(context, getApkJsonInfoUrl(infoUrl), callBack).show()
//    }
}
