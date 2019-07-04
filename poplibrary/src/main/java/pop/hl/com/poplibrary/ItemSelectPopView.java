package pop.hl.com.poplibrary;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.List;

import pop.hl.com.poplibrary.adapter.ItemSelectAdapter;
import pop.hl.com.poplibrary.adapter.SpacesItemDecoration;
import pop.hl.com.poplibrary.base.BasePop;
import pop.hl.com.poplibrary.utils.DensityUtil;
import pop.hl.com.poplibrary.utils.ScreenUtil;

/*
 *@Description: 单项选择弹窗
 *@Author: hl
 *@Time: 2019/4/11 9:39
 */
public class ItemSelectPopView {
    /**
     * 显示单项选择的弹窗
     * @param _context
     * @param _title  - 抬头标题
     * @param _bottomTip - 底部提示
     * @param _itemList - 单项列表选项
     * @param _onItemSelectClickListenner
     * @return
     */
    public static BasePop.Builder showSelectPopView(Context _context, String _title, String _bottomTip, List<String> _itemList, OnEventListenner.OnItemSelectClickListenner _onItemSelectClickListenner) {
        return new Builder(_context)
                .create(null, -1, false)
                .showSelectPopView(_title, _bottomTip, _itemList, -1, "", "", _onItemSelectClickListenner);
    }

    /**
     * 显示单项选择的弹窗
     * @param _context
     * @param _achor
     * @param _title - 抬头标题
     * @param _bottomTip - 底部提示
     * @param _itemList - 单项列表选项
     * @param _titleBgId - 抬头背景图片 - 左上角，右上角，需要圆角处理
     * @param _fillColor - 选项背景颜色
     * @param _fontColor - 选项字体颜色
     * @param _closeResourceId - 关闭按钮资源背景图片
     * @param _bScale - 是否显示缩放动画
     * @param _onItemSelectClickListenner
     * @return
     */
    public static BasePop.Builder showSelectPopView(Context _context, View _achor,
                                                    String _title, String _bottomTip, List<String> _itemList,
                                                    int _titleBgId, String _fillColor, String _fontColor,
                                                    int _closeResourceId, boolean _bScale, OnEventListenner.OnItemSelectClickListenner _onItemSelectClickListenner) {
        return new Builder(_context)
                .create(_achor, _closeResourceId, _bScale)
                .showSelectPopView(_title, _bottomTip, _itemList, _titleBgId, _fillColor, _fontColor, _onItemSelectClickListenner);
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
                    .setView(R.layout.pop_item_select)
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
         * 显示单选列表弹窗
         *
         * @param _title
         * @param _bottomTip
         * @param _itemList
         * @return
         */
        public BasePop.Builder showSelectPopView(String _title, String _bottomTip, List<String> _itemList, int _titleBgId, String _fillColor, String _fontColor, OnEventListenner.OnItemSelectClickListenner _onItemSelectClickListenner) {
            ///< 获取弹窗视图
            View popView = builder.getView();

            ConstraintLayout contentRoot = popView.findViewById(R.id.pis_contentRoot);
            if (_titleBgId > -1) {
                ScreenUtil.setConstraintLayoutWH(contentRoot, ScreenUtil.getScreenW(contextWeakReference.get()) * 3 / 4,
                        (int) (ScreenUtil.getScreenH(contextWeakReference.get()) * (3.6f / 12f)) + (int) ((ScreenUtil.getScreenH(contextWeakReference.get()) * (3.6f / 12f)) / 4));
            }
            else {
                ScreenUtil.setConstraintLayoutWH(contentRoot, ScreenUtil.getScreenW(contextWeakReference.get()) * 3 / 4,
                        (int) (ScreenUtil.getScreenH(contextWeakReference.get()) * (3.6f / 12f)));
            }

            if (_titleBgId > -1) {
                ConstraintLayout tipRoot = popView.findViewById(R.id.pis_tipRoot);
                ScreenUtil.setConstraintLayoutWH(tipRoot, -1,
                        (int) ((ScreenUtil.getScreenH(contextWeakReference.get()) * (3.6f / 12f)) / 4));

                Drawable drawable;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    drawable = contextWeakReference.get().getDrawable(_titleBgId);
                } else {
                    drawable = ContextCompat.getDrawable(contextWeakReference.get(), _titleBgId);
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    tipRoot.setBackground(drawable);
                } else {
                    tipRoot.setBackgroundDrawable(drawable);
                }
            }

            RecyclerView contentRv = popView.findViewById(R.id.pis_contentRv);
            ScreenUtil.setConstraintLayoutWH(contentRv, -1, (int) ((ScreenUtil.getScreenH(contextWeakReference.get()) * (3.6f / 12f)) * 2.5 / 5));

            GridLayoutManager gridLayoutManager = new GridLayoutManager(contextWeakReference.get(), 2);
            contentRv.setLayoutManager(gridLayoutManager);
            contentRv.addItemDecoration(new SpacesItemDecoration(contextWeakReference.get(), _itemList.size(), 20));
            ItemSelectAdapter itemSelectAdapter = new ItemSelectAdapter(contextWeakReference.get(), _itemList, _fillColor, _fontColor, _onItemSelectClickListenner);
            contentRv.setAdapter(itemSelectAdapter);

            ImageView closeIv = popView.findViewById(R.id.pis_closeIv);
            ScreenUtil.setMargin(closeIv, -10000, -10000, ScreenUtil.getScreenW(contextWeakReference.get()) * 1 / 8, -10000);

            TextView tipTv = popView.findViewById(R.id.pis_tipTv);
            TextView bottomTv = popView.findViewById(R.id.pis_bottomTv);
            if (_titleBgId < 0) {
                ScreenUtil.setMargin(tipTv, -10000, DensityUtil.dip2px(contextWeakReference.get(), 10), -10000, -10000);
            }

            if (null != _title && !_title.equals("")) {
                tipTv.setText(_title);
            } else {
                tipTv.setVisibility(View.GONE);
            }

            if (null != _bottomTip && !_bottomTip.equals("")) {
                bottomTv.setText(_bottomTip);
            } else {
                bottomTv.setVisibility(View.GONE);
            }

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
