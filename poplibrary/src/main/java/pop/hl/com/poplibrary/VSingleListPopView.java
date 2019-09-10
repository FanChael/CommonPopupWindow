package pop.hl.com.poplibrary;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import pop.hl.com.poplibrary.adapter.VSingleListAdapter;
import pop.hl.com.poplibrary.base.BasePop;

/*
 *@Description: 垂直单列表弹窗API + 包含点击事件回调
 *@Author: hl
 *@Time: 2019/2/12 14:38
 */
public class VSingleListPopView {
    /**
     * 显示单列表弹窗
     * @param _context
     * @param _anchor
     * @param _vListData
     * @param _itemTextColor
     * @param _onVListClickListenner
     * @return
     */
    public static BasePop.Builder showVSingleListPop(Context _context, View _anchor, List<String> _vListData,
                                                     int _itemTextColor,
                                                     OnEventListenner.OnVListClickListenner _onVListClickListenner) {
        return new Builder(_context).create(_anchor).show(_vListData, _itemTextColor, _onVListClickListenner);
    }

    /*
     *@Description: 垂直单列表弹窗建造器
     *@Author: hl
     *@Time: 2019/2/15 16:04
     */
    public static class Builder {
        private WeakReference<Context> contextWeakReference;
        private BasePop.Builder builder = null;
        private BasePop.Builder bg_builder = null;

        public Builder(Context _context) {
            this.contextWeakReference = new WeakReference<>(_context);
        }


        /**
         * 垂直单列表弹窗显示
         *
         * @param _anchor
         * @return
         */
        public Builder create(View _anchor) {
            ///< 创建弹窗视图
            builder = new BasePop.Builder(contextWeakReference.get())
                    .create(_anchor)
                    .setView(R.layout.pop_vsingle_list)
                    .setAnimation(BasePopView.ANIMATION.TRANSLATE)
                    .setBackgroundDrawable(0xffffffff)
                    .setOutsideTouchable(false)
                    .setWidthAndHeight(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            ///< 背景弹窗丫丫呀
            bg_builder = new BasePop.Builder(contextWeakReference.get())
                    .create(_anchor)
                    .setView(new View(contextWeakReference.get()))
                    .setBackgroundDrawable(BasePop.bgColor)
                    .setWidthAndHeight(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            return this;
        }

        /**
         * 显示单垂直列表弹窗
         *
         * @param _vListData
         * @param _itemTextColor
         * @param _onVListClickListenner
         * @return
         */
        public BasePop.Builder show(List<String> _vListData,
                                    int _itemTextColor,
                                    final OnEventListenner.OnVListClickListenner _onVListClickListenner) {
            ///< 获取弹窗视图
            View popView = builder.getView();
            RecyclerView vListRv = popView.findViewById(R.id.psv_vListRv);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(contextWeakReference.get());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            vListRv.setLayoutManager(linearLayoutManager);
            View canceItem = popView.findViewById(R.id.psv_canceItem);
            TextView titleTv = canceItem.findViewById(R.id.pvi_titleTv);
            titleTv.setTextColor(0x9e9e9e9e);
            ///< 填充数据
            List<String> vListData = new ArrayList<>();
            vListData.addAll(_vListData);
            VSingleListAdapter vSingleListAdapter = new VSingleListAdapter(contextWeakReference.get(), vListData, _itemTextColor, _onVListClickListenner);
            vListRv.setAdapter(vSingleListAdapter);
            ///< 背景弹窗走一走
            bg_builder.show(BasePopView.SIMPLE_GRAVITY.CENTER_IN_PARENT);
            builder.setOnDissmiss(new OnEventListenner.OnBaseListenner() {
                @Override
                public void onDissmiss() {
                    if (null != bg_builder){
                        bg_builder.dissmiss();
                    }
                    bg_builder.dissmiss();
                }
            });
            canceItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != builder){
                        builder.dissmiss();
                    }
                }
            });
            ///< 显示弹窗
            builder.show(BasePopView.SIMPLE_GRAVITY.FROM_BOTTOM);
            return builder;
        }
    }
}
