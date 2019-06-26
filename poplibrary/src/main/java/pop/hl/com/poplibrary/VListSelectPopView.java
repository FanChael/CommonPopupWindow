package pop.hl.com.poplibrary;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import pop.hl.com.poplibrary.adapter.VListAdapter;
import pop.hl.com.poplibrary.base.BasePop;
import pop.hl.com.poplibrary.utils.ScreenUtil;

/*
 *@Description: 地区选择弹窗API + 包含点击事件回调
 *@Author: hl
 *@Time: 2019/2/12 14:38
 */
public class VListSelectPopView {

    /**
     * 地区弹窗列表显示 - 默认高度是屏幕高度的1/7 - 从底部弹出
     *
     * @param _context
     * @param _anchor
     * @param _dataLeft - 左侧列表数据
     * @param _dataList - 列表数据
     * @param _dataRight - 右侧列表数据
     * @param _titleBgColor - 标题背景颜色
     * @param _titleColor - 标题字体颜色
     * @param _onLocationClickListenner
     * @return
     */
    public static BasePop.Builder showFromBottom(Context _context,
                                                 View _anchor,
                                                 List<String> _dataLeft,
                                                 List<String> _dataList,
                                                 List<String> _dataRight,
                                                 String _titleBgColor, String _titleColor,
                                                 OnEventListenner.OnLocationClickListenner _onLocationClickListenner) {
        return new VListSelectPopView.Builder(_context)
                .create(_anchor, 1, _titleBgColor, _titleColor)
                .show(_dataLeft, _dataList, _dataRight, _onLocationClickListenner);
    }

    /**
     * 弹窗的高度【弹窗高度的1/3作为item的高度】 - 从底部弹出
     *
     * @param _context
     * @param _anchor
     * @param _dataLeft - 左侧列表数据
     * @param _dataList - 列表数据
     * @param _dataRight - 右侧列表数据
     * @param _titleBgColor - 标题背景颜色
     * @param _titleColor - 标题字体颜色
     * @param _builderH - 弹窗高度自定义
     * @param _onLocationClickListenner
     * @return
     */
    public static BasePop.Builder showFromBottom(Context _context,
                                                 View _anchor,
                                                 List<String> _dataLeft,
                                                 List<String> _dataList,
                                                 List<String> _dataRight,
                                                 int _builderH,
                                                 String _titleBgColor, String _titleColor,
                                                 OnEventListenner.OnLocationClickListenner _onLocationClickListenner) {
        return new VListSelectPopView.Builder(_context)
                .create(_anchor, 1, _titleBgColor, _titleColor)
                .show(_dataLeft, _dataList, _dataRight, _onLocationClickListenner);
    }

    /*
     *@Description: 地区选择弹窗建造器
     *@Author: hl
     *@Time: 2019/2/15 16:04
     */
    public static class Builder {
        private WeakReference<Context> contextWeakReference;
        private BasePop.Builder builder = null;
        private BasePop.Builder bg_builder = null;
        private int revH;
        private int builderH;
        private int showType;

        private RecyclerView provinceRv;
        private RecyclerView cityRv;
        private RecyclerView districtRv;

        private View topLineV;
        private View centerLineV;
        private View bottomLineV;
        private ConstraintLayout bottomCL, locationRoot;

        private TextView selectOk;
        private TextView selectCancel;

        private VListAdapter provinceAdapter;
        private VListAdapter cityAdapter;
        private VListAdapter districtAdapter;

        private String currentLeftData = "";
        private String currentData  = "";
        private String currentRightData  = "";
        private List<String> dataLeftList  = null;
        private List<String> dataList  = null;
        private List<String> dataRightList = null;

        public Builder(Context _context) {
            this.contextWeakReference = new WeakReference<>(_context);
        }

        /**
         * 垂直列表弹窗显示
         *
         * @param _anchor
         * @return
         */
        public VListSelectPopView.Builder create(View _anchor, int _builderH, int _showType,  String _titleBgColor, String _titleColor) {
            ///< recycleview均分高度三分，所以弹窗高度决定了recycleview的高度
            this.revH = (int) (_builderH * 3.0f / 13.0f);
            this.builderH = _builderH;

            BasePopView.ANIMATION animation;
            if (1 == _showType){
                animation = BasePopView.ANIMATION.TRANSLATE;
            }else{
                animation = BasePopView.ANIMATION.FOLD;
            }

            ///< 创建弹窗视图
            builder = new BasePop.Builder(contextWeakReference.get())
                    .create(_anchor)
                    .setView(R.layout.pop_location)
                    .setAnimation(animation)
                    .setBackgroundDrawable(0xffffffff)
                    .setOutsideTouchable(false)
                    .setWidthAndHeight(LinearLayout.LayoutParams.MATCH_PARENT, builderH);

            ///< 设置标题背景/字体颜色
            ConstraintLayout bottomCL = builder.getView().findViewById(R.id.pl_bottomCL);
            TextView selectOk = builder.getView().findViewById(R.id.pl_selectOk);
            TextView selectCancel = builder.getView().findViewById(R.id.pl_selectCancel);
            if (null != _titleBgColor && !_titleBgColor.equals("")){
                bottomCL.setBackgroundColor(Color.parseColor(_titleBgColor));
            }
            if (null != _titleColor && !_titleColor.equals("")){
                selectOk.setTextColor(Color.parseColor(_titleColor));
                selectCancel.setTextColor(Color.parseColor(_titleColor));
            }

            ///< 背景弹窗丫丫呀 - 高度为控件之下到屏幕底部的高度
            int bgH;
            if (1 == _showType){
                bgH = LinearLayout.LayoutParams.MATCH_PARENT;
            }else{
                bgH = -10000;
            }
            bg_builder = new BasePop.Builder(contextWeakReference.get())
                    .create(_anchor)
                    .setView(new View(contextWeakReference.get()))
                    .setBackgroundDrawable(BasePop.bgColor)
                    .setWidthAndHeight(LinearLayout.LayoutParams.MATCH_PARENT, bgH);
            return this;
        }

        /**
         * 垂直弹窗列表显示
         * @param _anchor
         * @param _showType
         * @return
         */
        public VListSelectPopView.Builder create(View _anchor, int _showType,  String _titleBgColor, String _titleColor) {
            this.showType = _showType;
            return create(_anchor, ScreenUtil.getScreenH(contextWeakReference.get()) / 5, _showType, _titleBgColor, _titleColor);
        }

        /**
         * 地区弹窗列表显示
         *
         * @param _dataList
         * @param _onLocationClickListenner
         * @return
         */
        public BasePop.Builder show(  List<String> _dataLeft,
                                      List<String> _dataList,
                                      List<String> _dataRight,
                                    final OnEventListenner.OnLocationClickListenner _onLocationClickListenner) {
            if (null != _dataLeft){
                this.dataLeftList = new ArrayList<>();
                this.dataLeftList.add("");
                this.dataLeftList.add("");
                for (int i = 0; i < _dataLeft.size(); ++i){
                    this.dataLeftList.add(_dataLeft.get(i));
                }
                this.dataLeftList.add("");
                this.dataLeftList.add("");

                currentLeftData = this.dataLeftList.get(2);
            }

            if (null != _dataList) {
                this.dataList = new ArrayList<>();
                this.dataList.add("");
                this.dataList.add("");
                for (int i = 0; i < _dataList.size(); ++i) {
                    this.dataList.add(_dataList.get(i));
                }
                this.dataList.add("");
                this.dataList.add("");

                currentData = this.dataList.get(2);
            }

            if (null != _dataRight) {
                this.dataRightList = new ArrayList<>();
                this.dataRightList.add("");
                this.dataRightList.add("");
                for (int i = 0; i < _dataRight.size(); ++i) {
                    this.dataRightList.add(_dataRight.get(i));
                }
                this.dataRightList.add("");
                this.dataRightList.add("");

                currentRightData = this.dataRightList.get(2);
            }

            ///< 获取弹窗视图
            View popView = builder.getView();

            ///< 省、市、区
            provinceRv = popView.findViewById(R.id.pl_provenceRv);
            cityRv = popView.findViewById(R.id.pl_cityRv);
            districtRv = popView.findViewById(R.id.pl_districtRv);

            topLineV = popView.findViewById(R.id.pl_topLineV);
            centerLineV = popView.findViewById(R.id.pl_centerLineV);
            bottomLineV = popView.findViewById(R.id.pl_bottomLineV);
            bottomCL = popView.findViewById(R.id.pl_bottomCL);
            if (1 == showType){
                ///< 根据是否从底部显示，调整布局
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone((ConstraintLayout) popView);
                constraintSet.connect(R.id.pl_bottomCL, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
                constraintSet.connect(R.id.pl_locationRoot, ConstraintSet.TOP, R.id.pl_bottomCL, ConstraintSet.BOTTOM);
                constraintSet.applyTo((ConstraintLayout) popView);
            }

            selectOk = popView.findViewById(R.id.pl_selectOk);
            selectCancel = popView.findViewById(R.id.pl_selectCancel);

            ScreenUtil.setConstraintLayoutWH(topLineV, -1, revH);
            ScreenUtil.setConstraintLayoutWH(centerLineV, -1, revH);
            ScreenUtil.setConstraintLayoutWH(bottomLineV, -1, revH);
            ScreenUtil.setConstraintLayoutWH(bottomCL, -1, (int) (revH * (4.0f / 3.0f)) + 3);

            ScreenUtil.setConstraintLayoutWH(provinceRv, -1, revH * 3);
            ScreenUtil.setConstraintLayoutWH(cityRv, -1, revH * 3);
            ScreenUtil.setConstraintLayoutWH(districtRv, -1, revH * 3);

            ///< 分别布局数据
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(contextWeakReference.get());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            provinceRv.setLayoutManager(linearLayoutManager);

            LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(contextWeakReference.get());
            linearLayoutManager2.setOrientation(LinearLayoutManager.VERTICAL);
            cityRv.setLayoutManager(linearLayoutManager2);

            LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(contextWeakReference.get());
            linearLayoutManager3.setOrientation(LinearLayoutManager.VERTICAL);
            districtRv.setLayoutManager(linearLayoutManager3);

            ///< 由弹窗的高度来决定rev的item的高度
            provinceAdapter = new VListAdapter(contextWeakReference.get(), this.dataLeftList, null, revH, false);
            cityAdapter = new VListAdapter(contextWeakReference.get(), this.dataList, null, revH, false);
            districtAdapter = new VListAdapter(contextWeakReference.get(), this.dataRightList, null, revH, false);
            provinceRv.setAdapter(provinceAdapter);
            cityRv.setAdapter(cityAdapter);
            districtRv.setAdapter(districtAdapter);

            ///< 滚动监听并定位居中item
            if (null != dataLeftList && dataLeftList.size() > 4){
                provinceRv.addOnScrollListener(new MyOnSrollListenner(0));
            }
            if (null != dataList && dataList.size() > 4){
                cityRv.addOnScrollListener(new MyOnSrollListenner(1));
            }
            if (null != dataRightList && dataRightList.size() > 4) {
                districtRv.addOnScrollListener(new MyOnSrollListenner(2));
            }

            ///< 先滚过去【先显示省，根据省的选择再定位到其他】
            ((LinearLayoutManager) provinceRv.getLayoutManager()).scrollToPositionWithOffset(1, 0);
            ((LinearLayoutManager) cityRv.getLayoutManager()).scrollToPositionWithOffset(1, 0);
            ((LinearLayoutManager) districtRv.getLayoutManager()).scrollToPositionWithOffset(1, 0);

            ///< 背景弹窗走一走
            bg_builder.show(BasePopView.GRAVITY.LEFTTOP_TO_LEFTBOTTOM);
            builder.setOnDissmiss(new OnEventListenner.OnBaseListenner() {
                @Override
                public void onDissmiss() {
                    bg_builder.dissmiss();
                }
            });
            selectCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    builder.dissmiss();
                }
            });
            selectOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    builder.dissmiss();
                    if (null != _onLocationClickListenner) {
                        _onLocationClickListenner.onClick(v, new String[]{currentLeftData, currentData, currentRightData});
                    }
                }
            });
            ///< 显示弹窗
            if (1 == showType){
                builder.show(BasePopView.SIMPLE_GRAVITY.FROM_BOTTOM);
            }else{
                builder.show(BasePopView.GRAVITY.LEFTTOP_TO_LEFTBOTTOM);
            }
            return builder;
        }

        /**
         * 滚动监听并定位滑动item居中
         */
        private class MyOnSrollListenner extends RecyclerView.OnScrollListener {
            private int type = 0;

            MyOnSrollListenner(int type) {
                this.type = type;
            }

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (0 == newState) {
                    int scrollPos = changePos(recyclerView);
                    if (0 == type) {         ///< 滑动省
                        currentLeftData = dataLeftList.get(scrollPos + 1);
                    } else if (1 == type) {   ///< 滑动市
                        currentData = dataList.get(scrollPos + 1);
                    } else if (2 == type) {   ///< 滑动区
                        currentRightData = dataRightList.get(scrollPos + 1);
                    }
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        }

        /**
         * 定位item到指定的貌似中间的位置
         *
         * @param mRecyclerView
         * @return
         */
        private int changePos(RecyclerView mRecyclerView) {
            LinearLayoutManager layoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
            View firstVisibItem = mRecyclerView.getChildAt(0);
            //int firstItemPosition = layoutManager.findFirstVisibleItemPosition();
            int itemCount = layoutManager.getItemCount();
            //int recycleViewHeight = recyclerView.getHeight();
            int itemHeight = firstVisibItem.getHeight();
            //int lastItemPosition = layoutManager.findLastVisibleItemPosition();
            //int lastCItemPosition = layoutManager.findLastCompletelyVisibleItemPosition();

            int scrollViewHeight = getDistance(mRecyclerView);

            ///< 滚动后需要停留的位置
            ///< 列表前后各加了两个空位置，第1个置顶刚好是我们默认需要停留的位置（此时第0个看不见了）
            ///< 条目高度假设是100， 那么第1个置顶，滚动的距离也scrollViewHeight = 100；
            ///< 再向上或者向下滑动距离不足150的话，还是第1个置顶，具体看图说话才好
            int scrollPos = 1;
            if (scrollViewHeight < (itemHeight + itemHeight / 2)) {
                scrollPos = 1;
            } else if (scrollViewHeight > itemHeight * (itemCount - 4)) {
                scrollPos = itemCount - 4;
            } else {
                scrollPos = (scrollViewHeight + itemHeight / 2) / itemHeight;
            }
            ((LinearLayoutManager) mRecyclerView.getLayoutManager()).scrollToPositionWithOffset(scrollPos, 0);
            return scrollPos;
        }

        /**
         * RecyclerView已滑动的距离
         *
         * @param mRecyclerView
         * @return
         */
        private int getDistance(RecyclerView mRecyclerView) {
            LinearLayoutManager layoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
            View firstVisibItem = mRecyclerView.getChildAt(0);
            int firstItemPosition = layoutManager.findFirstVisibleItemPosition();
            //int itemCount = layoutManager.getItemCount();
            //int recycleViewHeight = mRecyclerView.getHeight();
            int itemHeight = firstVisibItem.getHeight();
            int firstItemBottom = layoutManager.getDecoratedBottom(firstVisibItem);
            return (firstItemPosition + 1) * itemHeight - firstItemBottom;
        }
    }
}
