package com.example.td_horoscope.ui.activity

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.view.View
import android.webkit.*
import android.widget.Toast
import com.example.module_base.base.BaseActivity
import com.example.module_base.util.PackageUtil
import com.example.module_base.widget.MyToolbar
import com.example.td_horoscope.R
import com.example.td_horoscope.base.MainBaseActivity
import com.example.td_horoscope.util.Contents
import kotlinx.android.synthetic.main.activity_deal.*

class DealActivity : BaseActivity()  {

    private var com="深圳市天王星互娱科技有限公司"
    private val umUrl = "https://www.umeng.com/page/policy?spm=a211eg.10560647.0.0.547034dcafEUZJ"
    private val gdtUrl = "https://imgcache.qq.com/gdt/cdn/adn/uniondoc/ylh_sdk_privacy_statement.html"
    private val ttUrl = "https://partner.oceanengine.com/privacy"
    private val loginUrl = "https://terms.aliyun.com/legal-agreement/terms/suit_bu1_ali_cloud/suit_bu1_ali_cloud202012111723_19126.html?spm=a2c4g.11186623.2.2.2665974el1mtM9"
    private var count = 10
    private val email = "2681706890@qq.com"

    override fun getLayoutView(): Int =R.layout.activity_deal
    var mTitleMsg="用户协议"
    var mContent=R.string.user
    override fun initView() {
        when (intent.getIntExtra(Contents.SET_Deal1, 0)) {
            0 -> {
                mTitleMsg="用户协议"
                mContent=R.string.user
                text.text = PackageUtil.difPlatformName(this,mContent)
            }
            1 -> {
                mTitleMsg="隐私协议"
//                text.text = getYinsi()
//                text.movementMethod = LinkMovementMethod()
                getYinsi()
            }
        }
        privacy_toolbar.setTitle(mTitleMsg)

    }

    override fun initEvent() {
        privacy_toolbar.setOnBackClickListener(object :MyToolbar.OnBackClickListener{
            override fun onBack() {
                if (webView.canGoBack()){
                    webView.goBack()
                }else{
                    finish()
                }
            }

            override fun onRightTo() {

            }
        })
    }

    private fun initWebView() {
        webView.webChromeClient = WebChromeClient()
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true
//        webSettings.useWideViewPort = true
//        webSettings.loadWithOverviewMode = true

        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
            webSettings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }
        webSettings.setSupportZoom(true)
        webSettings.builtInZoomControls = true
        webSettings.displayZoomControls = false

        webSettings.domStorageEnabled = true//不加这句有些h5登陆窗口出不来 H5页面使用DOM storage API导致的页面加载问题
        webSettings.cacheMode = WebSettings.LOAD_DEFAULT
        webSettings.allowFileAccess = true
        webSettings.javaScriptCanOpenWindowsAutomatically = true
        webSettings.loadsImagesAutomatically = true
        webSettings.defaultTextEncodingName = "utf-8"
        webView.webViewClient = object : WebViewClient(){
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return false
            }
//            override fun onPageFinished(view: WebView?, url: String?) {
//                super.onPageFinished(view, url)
//                webView.loadUrl("javascript:androidSetAppName('${packageManager.getApplicationLabel(applicationInfo)}')")
//                webView.loadUrl("javascript:androidSetCompanyName('${com}')")
//                webView.loadUrl("javascript:androidSetEmail('${email}')")
//            }
        }
        //加载网络资源
        webView.loadUrl("http://test.aisou.club/privacy_policy/privacy_policy.html?app_name=${packageManager.getApplicationLabel(applicationInfo)}&pack_name=${this.packageName}")
    }

    override fun onResume() {
        super.onResume()
        webView.onResume()
    }

    override fun onPause() {
        super.onPause()
        webView.onPause()
    }

    override fun onBackPressed() {
        if (webView.canGoBack()){
            webView.goBack()
        }else{
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        webView.destroy()
    }

    private fun getYinsi() {

        webView.visibility = View.VISIBLE
        initWebView()
    }

}