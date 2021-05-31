package com.example.td_horoscope.util.top

import android.content.Intent
import android.graphics.Color
import androidx.fragment.app.FragmentActivity
import com.example.td_horoscope.util.ColorUtil
import com.permissionx.guolindev.PermissionX

/**
 * @name td_horoscope
 * @class name：com.example.td_horoscope.util.top
 * @class describe
 * @author wujinming QQ:1245074510
 * @time 2020/11/2 17:54
 * @class describe
 */


inline fun <reified T> toOtherActivity(act: FragmentActivity? ,isFinish:Boolean=false, block: Intent.() -> Unit) {
    val intent = Intent(act, T::class.java)
    intent.block()
    act?.startActivity(intent)
    if (isFinish) {
        act?.finish()
    }
}


 fun checkRuntimePermission(act: FragmentActivity?,permissions:ArrayList<String>,isShow:Boolean,goActivity: ()->Unit) {
    PermissionX.init(act)
        .permissions(permissions)
        .setDialogTintColor(
            Color.parseColor(ColorUtil.THEME_COLOR_STR),
            Color.parseColor(ColorUtil.THEME_COLOR_STR)
        ).onExplainRequestReason { scope, deniedList, beforeRequest ->
            if (isShow) {
                val msg = "即将申请的权限是程序必须依赖的权限"
                scope.showRequestReasonDialog(deniedList, msg, "开启", "取消")
            } else {
                goActivity()
            }
        }
        .onForwardToSettings { scope, deniedList ->
            if (isShow) {
                val msg = "您需要去应用程序设置当中手动开启权限"
                scope.showForwardToSettingsDialog(deniedList, msg, "开启", "取消")
            } else {
                goActivity()
            }
        }
        .request { allGranted, grantedList, deniedList ->
            if (allGranted) goActivity()
        }
}




