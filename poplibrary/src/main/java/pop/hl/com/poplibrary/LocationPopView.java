package pop.hl.com.poplibrary;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pop.hl.com.poplibrary.adapter.VListAdapter;
import pop.hl.com.poplibrary.base.BasePop;
import pop.hl.com.poplibrary.utils.ScreenUtil;

/*
 *@Description: 地区选择弹窗API + 包含点击事件回调
 *@Author: hl
 *@Time: 2019/2/12 14:38
 */
public class LocationPopView {
    /**
     * 地区弹窗列表显示
     *
     * @param _provinceData
     * @param _cityList
     * @param _districtList
     * @param _onVListClickListenner
     * @return
     */
    public static BasePop.Builder show(Context _context,
                                View _anchor,
                                List<String> _provinceData,
                                HashMap<String, List<String>> _cityList,
                                HashMap<String, List<String>> _districtList,
                                OnEventListenner.OnVListClickListenner _onVListClickListenner) {
        return new LocationPopView.Builder(_context)
                .create(_anchor)
                .show(_provinceData, _cityList, _districtList, _onVListClickListenner);
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

        private RecyclerView provinceRv;
        private RecyclerView cityRv;
        private RecyclerView districtRv;

        private VListAdapter provinceAdapter;
        private VListAdapter cityAdapter;
        private VListAdapter districtAdapter;

        private List<String> provinceData;
        private HashMap<String, List<String>> cityList;
        private List<String> cityData = new ArrayList<>();
        private HashMap<String, List<String>> districtList;
        private List<String> districtData = new ArrayList<>();

        private String currentProvince;
        private String currentCity;
        private String currentDistrict;

        public Builder(Context _context) {
            this.contextWeakReference = new WeakReference<>(_context);
        }


        /**
         * 垂直列表弹窗显示
         *
         * @param _anchor
         * @return
         */
        public LocationPopView.Builder create(View _anchor) {
            ///< 创建弹窗视图
            builder = new BasePop.Builder(contextWeakReference.get())
                    .create(_anchor)
                    .setView(R.layout.pop_location)
                    .setAnimation(BasePopView.ANIMATION.FOLD)
                    .setBackgroundDrawable(0xffffffff)
                    .setOutsideTouchable(true)
                    .setWidthAndHeight(LinearLayout.LayoutParams.MATCH_PARENT,
                            ScreenUtil.getScreenH(contextWeakReference.get()) / 8);
            ///< 背景弹窗丫丫呀 - 高度为控件之下到屏幕底部的高度
            bg_builder = new BasePop.Builder(contextWeakReference.get())
                    .create(_anchor)
                    .setView(new View(contextWeakReference.get()))
                    .setBackgroundDrawable(BasePop.bgColor)
                    .setWidthAndHeight(LinearLayout.LayoutParams.MATCH_PARENT, -10000);
            return this;
        }

        /**
         * 地区弹窗列表显示
         *
         * @param _provinceData
         * @param _cityList
         * @param _districtList
         * @param _onVListClickListenner
         * @return
         */
        public BasePop.Builder show(List<String> _provinceData,
                                    HashMap<String, List<String>> _cityList,
                                    HashMap<String, List<String>> _districtList,
                                    OnEventListenner.OnVListClickListenner _onVListClickListenner) {
            this.provinceData = _provinceData;
            this.cityList = _cityList;
            this.districtList = _districtList;

            ///< 获取弹窗视图
            View popView = builder.getView();

            ///< 省、市、区
            provinceRv = popView.findViewById(R.id.pl_provenceRv);
            cityRv = popView.findViewById(R.id.pl_cityRv);
            districtRv = popView.findViewById(R.id.pl_districtRv);

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

            ///< 填充数据
            cityData.addAll(_cityList.get(_provinceData.get(2)));
            districtData.addAll(_districtList.get(_cityList.get(_provinceData.get(2)).get(2)));

            provinceAdapter = new VListAdapter(contextWeakReference.get(), provinceData, null);
            cityAdapter = new VListAdapter(contextWeakReference.get(), cityData, null);
            districtAdapter = new VListAdapter(contextWeakReference.get(), districtData, null);
            provinceRv.setAdapter(provinceAdapter);
            cityRv.setAdapter(cityAdapter);
            districtRv.setAdapter(districtAdapter);

            ///< 滚动监听并定位居中item
            provinceRv.addOnScrollListener(new MyOnSrollListenner(0));
            cityRv.addOnScrollListener(new MyOnSrollListenner(1));
            districtRv.addOnScrollListener(new MyOnSrollListenner(2));

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
            ///< 显示弹窗
            builder.show(BasePopView.GRAVITY.LEFTTOP_TO_LEFTBOTTOM);
            ///< 测试滚动条目的相关问题
            //            new Thread(new Runnable() {
            //                @Override
            //                public void run() {
            //                    while (true) {
            //                        try {
            //                            //Looper.prepare();
            //                            ((Activity)contextWeakReference.get()).runOnUiThread(new Runnable() {
            //                                @Override
            //                                public void run() {
            //                                    int toP = new Random().nextInt(6) + 2;
            //                                    Log.e("test2", "toP=" + toP);
            //                                    ((LinearLayoutManager)provenceRv.getLayoutManager()).scrollToPositionWithOffset(toP, 0);
            //                                }
            //                            });
            //                            //Looper.loop();
            //
            //                            Thread.sleep(3000);
            //                        } catch (InterruptedException e) {
            //                            e.printStackTrace();
            //                        }
            //                    }
            //                }
            //            }).start();
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
                        currentProvince = provinceData.get(scrollPos + 1);

                        ///< 更新市
                        cityData.clear();
                        cityData.addAll(cityList.get(currentProvince));
                        cityAdapter.notifyDataSetChanged();
                        ((LinearLayoutManager) cityRv.getLayoutManager()).scrollToPositionWithOffset(1, 0);

                        currentCity = cityData.get(2);

                        ///< 更新区
                        districtData.clear();
                        districtData.addAll(districtList.get(currentCity));
                        districtAdapter.notifyDataSetChanged();
                        ((LinearLayoutManager) districtRv.getLayoutManager()).scrollToPositionWithOffset(1, 0);
                    } else if (1 == type) {   ///< 滑动市
                        currentCity = cityData.get(scrollPos + 1);

                        ///< 更新区
                        districtData.clear();
                        districtData.addAll(districtList.get(currentCity));
                        districtAdapter.notifyDataSetChanged();
                        ((LinearLayoutManager) districtRv.getLayoutManager()).scrollToPositionWithOffset(1, 0);
                    } else if (2 == type) {   ///< 滑动区
                        currentDistrict = districtData.get(scrollPos + 1);
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
