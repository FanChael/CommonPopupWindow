package pop.hl.com.poplibrary;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import pop.hl.com.poplibrary.base.BasePop;
import pop.hl.com.poplibrary.utils.ProgressUtil;
import pop.hl.com.poplibrary.utils.ScreenUtil;
import pop.hl.com.poplibrary.utils.ShapeUtil;

/*
 *@Description: 更新弹窗View总类 + 包含点击事件回调
 *@Author: hl
 *@Time: 2019/2/20 10:45
 */
public class UpdatePopView {
    /**
     * 经典更新弹窗
     * @param _context
     * @param _achor
     * @param _titleBgId - 置顶背景图片
     * @param _h_dived_w - 资源图片存在的情况下，图片的高度/宽度
     * @param _allColor - 按钮、进度等主体颜色
     * @param _bforce - 是否强制更新
     * @param _updateMessage - 更新信息
     * @param _onUpdateClickListenner
     * @return
     */
    public static BasePop.Builder showNormalUpdate(Context _context, View _achor,
                                                   int _titleBgId, int _h_dived_w,
                                                   String _allColor, boolean _bforce,
                                                   String _updateMessage,
                                                   OnEventListenner.OnUpdateClickListenner _onUpdateClickListenner) {
        return new Builder(_context)
                .create(_titleBgId, _allColor, _h_dived_w, _bforce, _updateMessage, _achor)
                .showNormalUpdate(_onUpdateClickListenner);
    }

    /*
    *@Description: 更新弹窗建造器
    *@Author: hl
    *@Time: 2019/2/20 10:51
    */
    public static class Builder {
        private WeakReference<Context> contextWeakReference;
        private BasePop.Builder builder = null;
        private int titleBgId;
        private String allColor;
        private int h_dived_w;
        private boolean bforce;
        private String updateMessage;
        private GradientDrawable gradientDrawable;

        public Builder(Context _context) {
            this.contextWeakReference = new WeakReference<>(_context);
        }

        public Builder create(int _titleBgId, String _allColor,
                              int _h_dived_w, boolean _bforce,
                              String _updateMessage, View _achor) {
            this.allColor = _allColor;
            this.titleBgId = _titleBgId;
            this.h_dived_w = _h_dived_w;
            this.bforce = _bforce;
            this.updateMessage = _updateMessage;
            this.gradientDrawable = ShapeUtil.createShape(-1,
                    6,
                    -1, null, allColor);
            this.builder = new BasePop.Builder(contextWeakReference.get())
                    .create(_achor)
                    .setOutsideTouchable(true)
                    .setWidthAndHeight(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            return this;
        }

        /**
         * 显示经典更新弹窗
         * @param _onUpdateClickListenner
         * @return
         */
        public BasePop.Builder showNormalUpdate(final OnEventListenner.OnUpdateClickListenner _onUpdateClickListenner) {
            ///< 加载视图
            builder.setView(R.layout.pop_normal_update);

            ///< 获取弹窗视图
            View popView = builder.getView();

            ConstraintLayout updateContentRoot = popView.findViewById(R.id.pnu_updateContentRoot);
            ImageView topBgIv = popView.findViewById(R.id.pnu_topBgIv);
            final ProgressBar progress = popView.findViewById(R.id.pnu_progress);

            TextView updateBtn = popView.findViewById(R.id.pnu_positiveButton);
            TextView negativeTv = popView.findViewById(R.id.pnu_negativeTv);
            TextView message = popView.findViewById(R.id.pnu_message);

            ///< 设置内容布局高度(屏幕宽度的7/10)
            if (!bforce){
                progress.setVisibility(View.GONE);
            }else{
                negativeTv.setVisibility(View.GONE);
            }
            int windowW = ScreenUtil.getScreenW(contextWeakReference.get())* 7/10;
            ScreenUtil.setConstraintLayoutWH(updateContentRoot, windowW, -1);
            if (h_dived_w > 0){
                ScreenUtil.setConstraintLayoutWH(topBgIv, windowW, windowW * h_dived_w);
            }
            message.setText(updateMessage);

            ///< 设置主题
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                updateBtn.setBackground(gradientDrawable);
            }else{
                updateBtn.setBackgroundDrawable(gradientDrawable);
            }
            ProgressUtil.setColors(progress, BasePop.bgColor, Color.parseColor(allColor));
            if (-1 != titleBgId){
                topBgIv.setImageDrawable(contextWeakReference.get().getResources().getDrawable(titleBgId));
            }
            ///< 取消更新
            negativeTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    builder.dissmiss();
                }
            });
            ///< 确定更新
            updateBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    _onUpdateClickListenner.onClick(v, progress);
                }
            });
            builder.show(BasePopView.SIMPLE_GRAVITY.CENTER_IN_PARENT);

            return builder;
        }
    }
}
