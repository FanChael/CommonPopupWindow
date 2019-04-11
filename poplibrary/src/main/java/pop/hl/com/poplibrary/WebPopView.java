package pop.hl.com.poplibrary;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.lang.ref.WeakReference;

import pop.hl.com.poplibrary.base.BasePop;
import pop.hl.com.poplibrary.utils.ScreenUtil;
import pop.hl.com.poplibrary.utils.WebviewUtil;

/*
 *@Description: Webview弹窗
 *@Author: hl
 *@Time: 2019/4/11 9:39
 */
public class WebPopView {
    /**
     * 显示Webview承载内容的弹窗
     *
     * @param _context
     * @param _urlOrStr- html文本字符串
     * @return
     */
    public static BasePop.Builder showWebview(Context _context, String _urlOrStr) {
        return new Builder(_context)
                .create(null, -1, false)
                .showWebview(_urlOrStr);
    }

    /**
     * 显示Webview承载内容的弹窗
     *
     * @param _context
     * @param _urlOrStr- html文本字符串
     * @param _closeResourceId - 支持关闭按钮背景资源自定义
     * @return
     */
    public static BasePop.Builder showWebview(Context _context, String _urlOrStr, int _closeResourceId) {
        return new Builder(_context)
                .create(null, _closeResourceId, false)
                .showWebview(_urlOrStr);
    }

    /**
     * 显示Webview承载内容的弹窗
     *
     * @param _context
     * @param _urlOrStr- html文本字符串
     * @param _closeResourceId- 支持关闭按钮背景资源自定义
     * @param _bScale - 是否带缩放动画
     * @return
     */
    public static BasePop.Builder showWebview(Context _context, String _urlOrStr,
                                              int _closeResourceId, boolean _bScale) {
        return new Builder(_context)
                .create(null, _closeResourceId, _bScale)
                .showWebview(_urlOrStr);
    }

    /**
     * 显示Webview承载内容的弹窗
     *
     * @param _context
     * @param _urlOrStr- html文本字符串
     * @param _bScale- 是否带缩放动画
     * @return
     */
    public static BasePop.Builder showWebview(Context _context, String _urlOrStr, boolean _bScale) {
        return new Builder(_context)
                .create(null, -1, _bScale)
                .showWebview(_urlOrStr);
    }

    /**
     * 显示Webview承载内容的弹窗
     *
     * @param _context
     * @param _achor
     * @param _urlOrStr- html文本字符串
     * @param _closeResourceId- 支持关闭按钮背景资源自定义
     * @return
     */
    public static BasePop.Builder showWebview(Context _context, View _achor,
                                              String _urlOrStr, int _closeResourceId,
                                              boolean _bScale) {
        return new Builder(_context)
                .create(_achor, _closeResourceId, _bScale)
                .showWebview(_urlOrStr);
    }

    /*
     *@Description: 建造器
     *@Author: hl
     *@Time: 2019/2/19 17:02
     */
    public static class Builder {
        private WeakReference<Context> contextWeakReference;
        private BasePop.Builder builder = null;
        private Drawable closeDrawable = null;
        private BasePopView.SIMPLE_GRAVITY simple_gravity;

        public Builder(Context _context) {
            this.contextWeakReference = new WeakReference<>(_context);
        }

        public Builder create(View _achor, int _closeResourceId, boolean _bScale) {
            ///< 创建弹窗视图
            this.builder = new BasePop.Builder(contextWeakReference.get())
                    .create(null == _achor ? new View(contextWeakReference.get()) : _achor)
                    .setView(R.layout.pop_webview)
                    .setAnimation(_bScale ? BasePopView.ANIMATION.SCALE : null)
                    .setOutsideTouchable(false)
                    .setWidthAndHeight(LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.MATCH_PARENT);
            if (_closeResourceId > 0) {
                this.closeDrawable = contextWeakReference.get().getResources().getDrawable(_closeResourceId);
            }
            this.simple_gravity = BasePopView.SIMPLE_GRAVITY.CENTER_IN_PARENT;
            return this;
        }

        /**
         * 显示WebView弹窗
         *
         * @param _urlOrStr
         * @return
         */
        public BasePop.Builder showWebview(String _urlOrStr) {
            ///< 获取弹窗视图
            View popView = builder.getView();

            ConstraintLayout contentRoot = popView.findViewById(R.id.pw_contentRoot);
            ScreenUtil.setConstraintLayoutWH(contentRoot, ScreenUtil.getScreenW(contextWeakReference.get()) * 3 / 4,
                    (int) (ScreenUtil.getScreenH(contextWeakReference.get()) * (3.6f / 8f)));

            WebView contentWv = popView.findViewById(R.id.pw_contentWv);
            ImageView closeIv = popView.findViewById(R.id.pw_closeIv);
            WebviewUtil.initWebView(contentWv, null, "");

            contentWv.loadDataWithBaseURL(null, _urlOrStr, "text/html", "utf-8", null);
            if (null != closeDrawable) {
                closeIv.setImageDrawable(closeDrawable);
            }
            closeIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != builder) {
                        builder.dissmiss();
                    }
                }
            });
            builder.show(simple_gravity);
            return builder;
        }
    }
}
