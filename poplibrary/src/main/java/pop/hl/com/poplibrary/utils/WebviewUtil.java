package pop.hl.com.poplibrary.utils;

import android.annotation.SuppressLint;
import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebviewUtil {
    /**
     * 初始化Webview
     */
    @SuppressLint("JavascriptInterface")
    public static WebView initWebView(WebView detialWv, Object obj, String interfaceName) {
        WebSettings settings = detialWv.getSettings();
        ///< 开启JavaScript支持
        detialWv.getSettings().setJavaScriptEnabled(true);
        ///< 加入JS接口
        if (null != obj && null != interfaceName && !interfaceName.equals("")) {
            detialWv.addJavascriptInterface(obj, interfaceName);
        }
        // 设置WebView是否支持使用屏幕控件或手势进行缩放，默认是true，支持缩放
        detialWv.getSettings().setSupportZoom(false);
        ///< 设置WebView是否使用其内置的变焦机制，该机制结合屏幕缩放控件使用，默认是false，不使用内置变焦机制。
        detialWv.getSettings().setBuiltInZoomControls(false);
        ///< 设置是否开启DOM存储API权限，默认false，未开启，设置为true，WebView能够使用DOM storage API
        detialWv.getSettings().setDomStorageEnabled(true);
        ///< 触摸焦点起作用.如果不设置，则在点击网页文本输入框时，不能弹出软键盘及不响应其他的一些事件。
        detialWv.requestFocus();
        ///< 设置此属性,可任意比例缩放,设置webview推荐使用的窗口
        detialWv.getSettings().setUseWideViewPort(false);
        ///< 设置webview加载的页面的模式,缩放至屏幕的大小
        detialWv.getSettings().setLoadWithOverviewMode(true);
        ///< 允许webview对文件的操作
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            detialWv.getSettings().setAllowUniversalAccessFromFileURLs(true);
        }
        detialWv.getSettings().setAllowFileAccess(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            detialWv.getSettings().setAllowFileAccessFromFileURLs(true);
        }
        ///< 设置加载状态
        // android 5.0以上默认不支持Mixed Content
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            detialWv.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        return detialWv;
    }
}
