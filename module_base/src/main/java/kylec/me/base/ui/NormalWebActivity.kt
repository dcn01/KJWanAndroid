package kylec.me.base.ui

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Build
import android.view.View.GONE
import android.view.View.VISIBLE
import android.webkit.WebChromeClient
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import kotlinx.android.synthetic.main.activity_web.*
import kylec.me.base.R
import kylec.me.base.RoutePath
import kylec.me.base.Router
import kylec.me.base.extend.STRING_BLANK

/**
 * override `webTitle` field to show web title (if need)
 * override `url` field to load specific url
 *
 * Created by KYLE on 2019/5/14 - 22:34
 */
@Route(path = RoutePath.TO_WEB)
class NormalWebActivity(
    override val layoutId: Int = R.layout.activity_web
) : BaseActivity() {

    @Autowired(name = Router.KEY_TITLE)
    @JvmField
    var webTitle: String = STRING_BLANK

    @Autowired(name = Router.KEY_URL)
    @JvmField
    var url: String = STRING_BLANK

    override fun initView() {
        ARouter.getInstance().inject(this)

        header.title = webTitle
        if (webTitle.isBlank()) header.visibility = GONE

        with(web_view.settings) {
            // 使支持JavaScript脚本
            @SuppressLint("SetJavaScriptEnabled")
            javaScriptEnabled = true
            // 不使用缓存 只从网络读取
            cacheMode = WebSettings.LOAD_NO_CACHE
            // 支持屏幕缩放
            setSupportZoom(true)
            builtInZoomControls = true
            // 不显式缩放按钮
            displayZoomControls = false
        }

        with(web_view) {
            // 使目标网页仍在当前WebView显示 而不是打开系统浏览器
            webViewClient = mWebViewClient
            // WEB VIEW 辅助类 处理网站标题 加载进度等等
            webChromeClient = mWebChromeClient
            // 加载URL
            loadUrl(this@NormalWebActivity.url)
        }
    }

    /**
     * WEB VIEW 相关处理
     */
    private val mWebViewClient = object : WebViewClient() {

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            progress_bar.visibility = VISIBLE
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            progress_bar.visibility = GONE
        }

        /** 处理出错 新 ANDROID 6 以上 */
        override fun onReceivedError(
            view: WebView?,
            request: WebResourceRequest?,
            error: WebResourceError?
        ) {
            super.onReceivedError(view, request, error)
            request?.let {
                if (it.isForMainFrame) {
                    // 显式自定义错误页
                    tv_load_failed.visibility = VISIBLE
                    web_view.visibility = GONE
                }
            }
        }

        /** 处理出错 旧 ANDROID 6 以下 */
        @Suppress("OverridingDeprecatedMember", "DEPRECATION")
        override fun onReceivedError(
            view: WebView?,
            errorCode: Int,
            description: String?,
            failingUrl: String?
        ) {
            super.onReceivedError(view, errorCode, description, failingUrl)
            // 防止新版本重复调用
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) return

            tv_load_failed.visibility = VISIBLE
            web_view.visibility = GONE
        }

        /** 拦截处理 */
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            request?.let {
                if (it.toString() == "http://www.google.com/") return true
            }
            return super.shouldOverrideUrlLoading(view, request)
        }
    }

    /**
     * WEB CHROME CLIENT 辅助处理Javascript对话框 网站图标 网站TITLE 加载进度等
     */
    private val mWebChromeClient = object : WebChromeClient() {

        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            progress_bar.progress = if (newProgress > 100) 100 else newProgress
        }
    }
}
