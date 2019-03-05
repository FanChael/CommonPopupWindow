package pop.hl.com.poplibrary;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import pop.hl.com.poplibrary.adapter.VListAdapter;
import pop.hl.com.poplibrary.base.BasePop;

/*
*@Description: 垂直列表弹窗API + 包含点击事件回调
*@Author: hl
*@Time: 2019/2/12 14:38
*/
public class VListPopView {
    /*
     *@Description: 垂直列表弹窗建造器
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
         * 垂直列表弹窗显示
         * @param _anchor
         * @return
         */
        public VListPopView.Builder create(View _anchor, int _popH) {
            ///< 创建弹窗视图
            builder = new BasePop.Builder(contextWeakReference.get())
                    .create(_anchor)
                    .setView(R.layout.pop_vlist)
                    .setAnimation(BasePopView.ANIMATION.FOLD)
                    .setBackgroundDrawable(0xffffffff)
                    .setOutsideTouchable(true)
                    .setWidthAndHeight(LinearLayout.LayoutParams.MATCH_PARENT, _popH);
            ///< 背景弹窗丫丫呀
            bg_builder = new BasePop.Builder(contextWeakReference.get())
                    .create(_anchor)
                    .setView(new View(contextWeakReference.get()))
                    .setBackgroundDrawable(BasePop.bgColor)
                    .setWidthAndHeight(LinearLayout.LayoutParams.MATCH_PARENT,  -10000);
            return this;
        }

        /**
         * 显示垂直列表弹窗
         * @param _vListData
         * @param _onVListClickListenner
         * @return
         */
        public BasePop.Builder show(List<String> _vListData,
                                         OnEventListenner.OnVListClickListenner _onVListClickListenner) {
            ///< 获取弹窗视图
            View popView = builder.getView();
            RecyclerView vListRv = popView.findViewById(R.id.pv_vListRv);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(contextWeakReference.get());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            vListRv.setLayoutManager(linearLayoutManager);
            ///< 填充数据
            List<String> vListData = new ArrayList<>();
            vListData.addAll(_vListData);
            VListAdapter vListAdapter = new VListAdapter(contextWeakReference.get(), vListData, _onVListClickListenner);
            vListRv.setAdapter(vListAdapter);
            ///< 背景弹窗走一走
            bg_builder.show(BasePopView.GRAVITY.LEFTTOP_TO_LEFTBOTTOM);
            builder.setOnDissmiss(new OnEventListenner.OnBaseListenner() {
                @Override
                public void onDissmiss() {
                    bg_builder.dissmiss();
                }
            });
            ///< 显示弹窗
            builder.show(BasePopView.GRAVITY.LEFTTOP_TO_LEFTBOTTOM);
            return builder;
        }
    }
}
