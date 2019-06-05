package pop.hl.com.poplibrary;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import pop.hl.com.poplibrary.base.BasePop;
import pop.hl.com.poplibrary.utils.DensityUtil;
import pop.hl.com.poplibrary.utils.ShapeUtil;

/*
 *@Description: 评论弹窗
 *@Author: hl
 *@Time: 2019/6/4 16:52
 */
public class CommentView {
    /**
     * 显示评论弹窗
     * @param _context
     * @param _texthint - 输入框提示文本
     * @param _hisMsg - 输入框历史输入文本(需要自己保存之前的输入)
     * @param _allColor - 主题颜色(主要是发送按钮的颜色)
     * @param _bShowProgress - 点击发送是否显示系统的进度条(可以自己定义进度条以及显示逻辑)
     *                         - 关闭弹窗内部会处理进度弹窗消失
     * @param _sendBackListener - 点击发送回调事件(返回文本信息)
     * @return
     */
    public static BasePop.Builder showComment(Context _context,
                                              String _texthint, String _hisMsg,
                                              String _allColor, boolean _bShowProgress,
                                              OnEventListenner.SendBackListener _sendBackListener) {
        return new Builder(_context)
                .create(_texthint, _hisMsg, _allColor, null)
                .showComment(_bShowProgress, _sendBackListener);
    }

    /*
     *@Description: 建造器
     *@Author: hl
     *@Time: 2019/2/19 17:02
     */
    public static class Builder {
        private WeakReference<Context> contextWeakReference;
        private BasePop.Builder builder = null;
        private BasePopView.SIMPLE_GRAVITY simple_gravity;

        private ProgressDialog progressDialog = null;
        private String texthint;
        private EditText inputDlg;
        private String hisMsg = "";

        private GradientDrawable gradientDrawable;

        public Builder(Context _context) {
            this.contextWeakReference = new WeakReference<>(_context);
        }

        public Builder create(String _texthint, String _hisMsg, String _allColor, View _achor) {
            this.texthint = _texthint;
            this.hisMsg = _hisMsg;
            this.gradientDrawable = ShapeUtil.createShape(-1,
                    DensityUtil.dip2px(contextWeakReference.get(), 5),
                    -1, null, _allColor);
            ///< 创建弹窗视图
            this.builder = new BasePop.Builder(contextWeakReference.get())
                    .create(null == _achor ? new View(contextWeakReference.get()) : _achor)
                    .setView(R.layout.pop_comment)
                    .setAnimation(BasePopView.ANIMATION.TRANSLATE)
                    .setOutsideTouchable(true)
                    .setWidthAndHeight(LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
            this.simple_gravity = BasePopView.SIMPLE_GRAVITY.FROM_BOTTOM;
            return this;
        }

        /**
         * 显示评论弹窗
         * @param _sendBackListener
         * @return
         */
        public BasePop.Builder showComment(final boolean _bShowProgress, final OnEventListenner.SendBackListener _sendBackListener) {
            ///< 获取弹窗视图
            View popView = builder.getView();

            ///< 防止PopupWindow被软件盘挡住（可能只要下面一句，可能需要这两句）
            // builder.getPop().setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
            builder.getPop().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            ///< 使其聚焦
            builder.getPop().setFocusable(true);

            ///< 评论输入框
            inputDlg = popView.findViewById(R.id.dialog_comment_content);
            ///< 输入提示
            inputDlg.setHint(texthint);
            ///< 使其聚焦
            inputDlg.setFocusable(true);
            ///< 设置允许在外点击消失
            inputDlg.setFocusableInTouchMode(true);
            ///< 请求获取焦点
            inputDlg.requestFocus();

            final TextView tv_send = popView.findViewById(R.id.dialog_comment_send);
            TextView tv_cancel= popView.findViewById(R.id.dialog_comment_cancel);
            if (null != hisMsg && !hisMsg.equals("")){
                inputDlg.setText(hisMsg);
                inputDlg.setSelection(hisMsg.length());
                tv_send.setBackgroundDrawable(gradientDrawable);
            }
            inputDlg.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (s.length() > 0) {
                        tv_send.setBackgroundDrawable(gradientDrawable);
                    } else {
                        tv_send.setBackgroundResource(R.drawable.corners_review_send);
                    }
                }
            });

            ///< 评论、取消评论
            tv_send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (TextUtils.isEmpty(inputDlg.getText().toString())) {
                        return;
                    }
                    else {
                        if (_bShowProgress){
                            progressDialog = new ProgressDialog(contextWeakReference.get());
                            ///< 点击外部不消失
                            progressDialog.setCancelable(false);
                            progressDialog.setCanceledOnTouchOutside(false);
                            progressDialog.show();
                        }
                        _sendBackListener.sendBack(inputDlg.getText().toString());
                    }
                }
            });

            tv_cancel.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    builder.dissmiss();
                }
            });

            ///< 键盘事件延迟处理
            final Handler mHanler = new Handler();
            builder.setOnDissmiss(new OnEventListenner.OnBaseListenner() {
                @Override
                public void onDissmiss() {
                    if (null != progressDialog){
                        progressDialog.cancel();
                    }
                    mHanler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            hideSoftkeyboard(contextWeakReference.get());
                        }
                    }, 200);
                }
            });
            ///< 弹窗软键盘 - 做点延迟(不做也可以)
            mHanler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    showSoftkeyboard(contextWeakReference.get());
                }
            }, 100);
            ///< 这样弹出输入法也行
            ///< showSoftkeyboard();
            builder.show(simple_gravity);
            return builder;
        }

        public boolean bIsShow(){
            return this.bIsShow();
        }

        /**
         * 弹出软键盘
         * @param context
         */
        private void showSoftkeyboard(Context context) {
            try {
                ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE))
                        .toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            } catch (NullPointerException e) {

            }
        }

        /**
         * 隐藏软键盘
         * @param context
         */
        private void hideSoftkeyboard(Context context) {
            try {
                ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE))
                        .hideSoftInputFromWindow(((Activity)context).getCurrentFocus().getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);
            } catch (NullPointerException e) {

            }
        }
    }
}
