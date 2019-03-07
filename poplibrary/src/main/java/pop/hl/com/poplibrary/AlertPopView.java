package pop.hl.com.poplibrary;

import android.content.Context;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
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
     * @param _context
     * @param _achor
     * @param _title - 标题
     * @param _message - 提示消息
     * @param _nagative - 左侧文本框按钮内容
     * @param _positive - 右侧文本框按钮内容
     * @param _allColor - 主题颜色
     * @param _bCancelDismiss - 点击取消是否弹窗直接消失
     * @param _onAlertClickListenner
     * @return
     */
    public static BasePop.Builder showALertTypeA(Context _context, View _achor,
                                                 String _title, String _message,
                                                 String _nagative, String _positive,
                                                 String _allColor, boolean _bCancelDismiss,
                                                 OnEventListenner.OnAlertClickListenner _onAlertClickListenner) {
        return new Builder(_context)
                .create(_title, _message, _nagative, _positive, _allColor, _bCancelDismiss, _achor)
                .showAlertA(_onAlertClickListenner);
    }

    /**
     * 显示警告弹窗(A类型警告弹窗)
     * @param _context
     * @param _achor
     * @param _title - 标题
     * @param _message - 提示消息
     * @param _allColor - 主题颜色
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
                        _allColor, true, _achor)
                .showAlertA(_onAlertClickListenner);
    }

    /*
     *@Description: 警告弹窗建造器
     *@Author: hl
     *@Time: 2019/3/7 11:52
     */
    public static class Builder {
        private WeakReference<Context> contextWeakReference;
        private BasePop.Builder builder = null;
        private String titleS, messageS;
        private String nagativeS, positiveS;
        private String allColor;
        private boolean bCancelDismiss;

        public Builder(Context _context) {
            this.contextWeakReference = new WeakReference<>(_context);
        }

        public Builder create(String _title, String _message,
                              String _nagative, String _positive,
                              String _allColor, boolean _bCancelDismis,
                              View _achor) {
            this.allColor = _allColor;
            this.titleS = _title;
            this.messageS = _message;
            this.positiveS = _positive;
            this.nagativeS = _nagative;
            this.bCancelDismiss = _bCancelDismis;
            this.builder = new BasePop.Builder(contextWeakReference.get())
                    .create(_achor)
                    .setOutsideTouchable(false)
                    .setWidthAndHeight(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
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
            TextView positiveTv = popView.findViewById(R.id.pa_positiveTv);

            int windowW = ScreenUtil.getScreenW(contextWeakReference.get()) * 3 / 4;
            ///< 内容宽度为屏幕的3/4
            ScreenUtil.setConstraintLayoutWH(alertContentRoot, windowW, -1);

            ///< 标题按钮
            title.setText(titleS);
            message.setText(messageS);
            negativeTv.setText(nagativeS);
            positiveTv.setText(positiveS);

            ///< 设置主题
            negativeTv.setTextColor(Color.parseColor(allColor));
            positiveTv.setTextColor(Color.parseColor(allColor));


            ///< 取消
            negativeTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (bCancelDismiss) {
                        builder.dissmiss();
                    }else{
                        if (null != _onAlertClickListenner){
                            _onAlertClickListenner.onClick(v, CALLBACK_TYPE.CNACEL);
                        }
                    }
                }
            });

            ///< 确定
            positiveTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != _onAlertClickListenner){
                        _onAlertClickListenner.onClick(v, CALLBACK_TYPE.OK);
                    }
                }
            });
            builder.show(BasePopView.SIMPLE_GRAVITY.CENTER_IN_PARENT);

            return builder;
        }
    }
}
