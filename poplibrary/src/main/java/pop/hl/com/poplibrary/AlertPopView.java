package pop.hl.com.poplibrary;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import pop.hl.com.poplibrary.base.BasePop;
import pop.hl.com.poplibrary.utils.ScreenUtil;

/*
 *@Description: 警告类弹窗View总类 + 包含点击事件回调
 *@Author: hl
 *@Time: 2019/2/20 10:45
 */
public class AlertPopView {
    /**
     * 点击事件回调类型
     */
    public enum CALLBACK_TYPE {
        OK, CNACEL
    }

    /**
     * 显示警告弹窗(A类型警告弹窗)
     *
     * @param _context
     * @param _achor
     * @param _title                 - 标题
     * @param _message               - 提示消息
     * @param _nagative              - 左侧文本框按钮内容
     * @param _positive              - 右侧文本框按钮内容
     * @param _allColor              - 主题颜色 - 目前针对左右按钮
     * @param _bCancelDismiss        - 点击取消是否弹窗直接消失
     * @param _onAlertClickListenner
     * @return
     */
    public static BasePop.Builder showALertTypeA(Context _context, View _achor,
                                                 String _title, String _message,
                                                 String _nagative, String _positive,
                                                 String _allColor, boolean _bCancelDismiss,
                                                 OnEventListenner.OnAlertClickListenner _onAlertClickListenner) {
        return new Builder(_context)
                .create(_title, _message, _nagative,
                        _positive, _allColor, _bCancelDismiss,
                        false, _achor)
                .showAlertA(_onAlertClickListenner);
    }

    /**
     * 显示警告弹窗(A类型警告弹窗)
     *
     * @param _context
     * @param _achor
     * @param _title                 - 标题
     * @param _message               - 提示消息
     * @param _allColor              - 主题颜色 - 目前针对左右按钮
     * @param _onAlertClickListenner
     * @return
     */
    public static BasePop.Builder showALertTypeA(Context _context, View _achor,
                                                 String _title, String _message,
                                                 String _allColor,
                                                 OnEventListenner.OnAlertClickListenner _onAlertClickListenner) {
        return new Builder(_context)
                .create(_title, _message,
                        _context.getResources().getString(R.string.cancel),
                        _context.getResources().getString(R.string.ok),
                        _allColor, true,
                        false, _achor)
                .showAlertA(_onAlertClickListenner);
    }

    /**
     * 显示警告弹窗(A类型警告弹窗) - 只有确定按钮
     *
     * @param _context
     * @param _achor
     * @param _title                 - 标题
     * @param _message               - 提示消息
     * @param _allColor              - 主题颜色 - 目前针对左右按钮
     * @param _bOkDismiss            - 点击确定是否消失弹窗
     * @param _onAlertClickListenner
     * @return
     */
    public static BasePop.Builder showALertTypeA(Context _context, View _achor,
                                                 String _title, String _message,
                                                 String _allColor, boolean _bOkDismiss,
                                                 OnEventListenner.OnAlertClickListenner _onAlertClickListenner) {
        return new Builder(_context)
                .create(_title, _message,
                        "",
                        _context.getResources().getString(R.string.ok),
                        _allColor, true,
                        _bOkDismiss, _achor)
                .showAlertA(_onAlertClickListenner);
    }

    /**
     * 显示原生简洁弹窗
     *
     * @param _context
     * @param _title                  - 标题
     * @param _message                - 提示消息
     * @param _nagative               - 左侧文本框按钮内容 - 可以传null，默认不显示左侧按钮
     * @param _positive               - 右侧文本框按钮内容- 可以传null，默认不显示右侧按钮
     * @param _allColor               - 统一主题颜色 - 可以传null，默认系统颜色
     * @param bCanceledOnTouchOutside - 点击外部是否消失
     * @param _onAlertClickListenner
     * @return
     */
    public static AlertDialog showOriginAlert(Context _context,
                                              String _title, String _message,
                                              String _nagative, String _positive,
                                              String _allColor, boolean bCanceledOnTouchOutside,
                                              OnEventListenner.OnAlertClickListenner _onAlertClickListenner) {
        return new Builder(_context)
                .create(_title, _message, _nagative, _positive, _allColor, bCanceledOnTouchOutside)
                .showOrigin(_onAlertClickListenner);
    }

    /*
     *@Description: 警告弹窗建造器
     *@Author: hl
     *@Time: 2019/3/7 11:52
     */
    public static class Builder {
        private WeakReference<Context> contextWeakReference;
        private BasePop.Builder builder = null;
        private AlertDialog.Builder adBuilder;
        private String titleS, messageS;
        private String nagativeS = "", positiveS;
        private String allColor;
        private boolean bCancelDismiss;
        private boolean bCanceledOnTouchOutside = false;
        private boolean bOkDismiss;

        public Builder(Context _context) {
            this.contextWeakReference = new WeakReference<>(_context);
        }

        public Builder create(String _title, String _message,
                              String _nagative, String _positive,
                              String _allColor, boolean _bCancelDismis,
                              boolean _bOkDismiss,
                              View _achor) {
            this.allColor = _allColor;
            this.titleS = _title;
            this.messageS = _message;
            this.positiveS = _positive;
            this.nagativeS = _nagative;
            this.bCancelDismiss = _bCancelDismis;
            this.bOkDismiss = _bOkDismiss;
            this.builder = new BasePop.Builder(contextWeakReference.get())
                    .create(_achor)
                    .setOutsideTouchable(false)
                    .setWidthAndHeight(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            return this;
        }

        public Builder create(String _title, String _message,
                              String _nagative, String _positive,
                              String _allColor, boolean _bCanceledOnTouchOutside) {
            this.allColor = _allColor;
            this.titleS = _title;
            this.messageS = _message;
            this.positiveS = _positive;
            this.nagativeS = _nagative;
            this.bCanceledOnTouchOutside = _bCanceledOnTouchOutside;
            this.adBuilder = new AlertDialog.Builder(contextWeakReference.get());
            return this;
        }

        /*
         *@Description: 显示样式A的警告弹窗
         *@Author: hl
         *@Time: 2019/3/7 13:44
         */
        public BasePop.Builder showAlertA(final OnEventListenner.OnAlertClickListenner _onAlertClickListenner) {
            ///< 加载视图
            builder.setView(R.layout.pop_alert);

            ///< 获取弹窗视图
            View popView = builder.getView();

            ConstraintLayout alertContentRoot = popView.findViewById(R.id.pa_alertContentRoot);
            TextView title = popView.findViewById(R.id.pa_title);
            TextView message = popView.findViewById(R.id.pa_message);
            TextView negativeTv = popView.findViewById(R.id.pa_negativeTv);
            ConstraintLayout negative = popView.findViewById(R.id.pa_negative);
            TextView positiveTv = popView.findViewById(R.id.pa_positiveTv);

            int windowW;
            if (null == nagativeS || nagativeS.equals("")) {
                windowW = ScreenUtil.getScreenW(contextWeakReference.get()) * 1 / 2;
            } else {
                windowW = ScreenUtil.getScreenW(contextWeakReference.get()) * 3 / 4;
            }
            ///< 内容宽度为屏幕的3/4
            ScreenUtil.setConstraintLayoutWH(alertContentRoot, windowW, -1);

            ///< 标题按钮
            title.setText(titleS);
            message.setText(messageS);
            negativeTv.setText(nagativeS);
            positiveTv.setText(positiveS);

            if (null == nagativeS || nagativeS.equals("")) {
                negative.setVisibility(View.GONE);
            }

            ///< 设置主题
            negativeTv.setTextColor(Color.parseColor(allColor));
            positiveTv.setTextColor(Color.parseColor(allColor));


            ///< 取消
            negativeTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (bCancelDismiss) {
                        builder.dissmiss();
                    } else {
                        if (null != _onAlertClickListenner) {
                            _onAlertClickListenner.onClick(v, CALLBACK_TYPE.CNACEL);
                        }
                    }
                }
            });

            ///< 确定
            positiveTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != _onAlertClickListenner) {
                        _onAlertClickListenner.onClick(v, CALLBACK_TYPE.OK);
                    }

                    if (bOkDismiss && (null == nagativeS || nagativeS.equals(""))) {
                        builder.dissmiss();
                    }
                }
            });
            builder.show(BasePopView.SIMPLE_GRAVITY.CENTER_IN_PARENT);

            return builder;
        }

        /**
         * 显示原始简洁弹窗
         *
         * @param _onAlertClickListenner
         * @return
         */
        public AlertDialog showOrigin(final OnEventListenner.OnAlertClickListenner _onAlertClickListenner) {
            adBuilder.setTitle(titleS == null ? "标题" : titleS)
                    .setMessage(messageS == null ? "消息内容" : messageS);
            if (null != nagativeS && !nagativeS.equals("")) {
                adBuilder.setNegativeButton(nagativeS, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (null != _onAlertClickListenner) {
                            _onAlertClickListenner.onClick(null, CALLBACK_TYPE.CNACEL);
                        }
                    }
                });
            }
            if (null != positiveS && !positiveS.equals("")) {
                adBuilder.setPositiveButton(positiveS, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (null != _onAlertClickListenner) {
                            _onAlertClickListenner.onClick(null, CALLBACK_TYPE.OK);
                        }
                    }
                });
            }
            AlertDialog dialog = adBuilder.create();
            dialog.setCancelable(bCanceledOnTouchOutside);
            dialog.setCanceledOnTouchOutside(bCanceledOnTouchOutside);
            dialog.show();
            ///< show之后设置主题颜色
            if (null != allColor && !allColor.equals("")) {
                dialog.getButton(dialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor(allColor));
                dialog.getButton(dialog.BUTTON_POSITIVE).setTextColor(Color.parseColor(allColor));
            }
            return dialog;
        }
    }
}
