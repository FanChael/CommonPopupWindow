package pop.hl.com.poplibrary;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import pop.hl.com.poplibrary.adapter.ShareBorderAdapter;
import pop.hl.com.poplibrary.adapter.SpacesItemDecoration;
import pop.hl.com.poplibrary.base.BasePop;

/*
 *@Description: 弹窗View总类 + 包含点击事件回调
 *@Author: hl
 *@Time: 2019/2/15 11:37
 */
public class SharePopView {
    /**
     * 分享弹窗样式
     * ME - 自家的
     * TENCENT - 仿腾讯
     */
    public enum SHARE_TYPE{
        ME, F_TENCENT
    }

    /**
     * 分享列表显示模式
     * HORIZON - 水平滑动 Item's Count > 5个的情况下
     * GRID - 网格 5列
     */
    public enum SHOW_TYPE{
        HORIZON, GRID
    }

    /**
     * 显示分享弹窗 - 默认大于5个方可左右滑动
     * @param _context
     * @param _achor
     * @param _share2Name - 如果传null，默认提供朋友圈、微信、qq、新浪、复制链接
     * @param _share2Icon
     * @param _simple_gravity - 支持从下到上以及从上到下显示方式
     * @param _show_type
     * @param _onShareClickListenner
     * @return
     */
    public static BasePop.Builder showShare(Context _context, View _achor,
                                 List<String> _share2Name, List<Integer> _share2Icon,
                                 BasePopView.SIMPLE_GRAVITY _simple_gravity,
                                 SharePopView.SHOW_TYPE _show_type,
                                 OnEventListenner.OnShareClickListenner _onShareClickListenner){
        return new Builder(_context)
                .create(_achor, SHARE_TYPE.ME)
                .setTitleAndIcon(_share2Name, _share2Icon)
                .showShareBorder(_simple_gravity, _show_type, _onShareClickListenner);
    }

    /**
     * 显示分享弹窗 - 默认大于5个方可左右滑动
     * @param _context
     * @param _achor
     * @param _share2Name- 如果传null，默认提供朋友圈、微信、qq、新浪、复制链接
     * @param _share2Icon
     * @param _simple_gravity- 支持从下到上以及从上到下显示方式
     * @param _show_type
     * @param _onShareClickListenner
     * @return
     */
    public static BasePop.Builder showShareFTencent(Context _context, View _achor,
                                            List<String> _share2Name, List<Integer> _share2Icon,
                                            BasePopView.SIMPLE_GRAVITY _simple_gravity,
                                            SharePopView.SHOW_TYPE _show_type,
                                            OnEventListenner.OnShareClickListenner _onShareClickListenner){
        return new Builder(_context)
                .create(_achor, SHARE_TYPE.F_TENCENT)
                .setTitleAndIcon(_share2Name, _share2Icon)
                .showShareBorder(_simple_gravity, _show_type, _onShareClickListenner);
    }

    /*
    *@Description: 分享弹窗建造器
    *@Author: hl
    *@Time: 2019/2/15 16:04
    */
    public static class Builder {
        private WeakReference<Context> contextWeakReference;
        private BasePop.Builder builder = null;
        private BasePop.Builder bg_builder = null;
        private List<String> share2Name = null;
        private List<Integer> share2Icon = null;
        private SHARE_TYPE share_type = SHARE_TYPE.ME;

        public Builder(Context _context){
            this.contextWeakReference = new WeakReference<>(_context);
        }

        /**
         * 创建视图弹窗
         * @param _anchor
         * @return
         */
        public SharePopView.Builder create(View _anchor, SHARE_TYPE _share_type) {
            int layoutId  = R.layout.pop_bottom_shareborder;
            boolean bOutsideTouchable = false;
            this.share_type = _share_type;
            int bgColor = BasePop.bgColor;
            switch (_share_type){
                case ME:
                    layoutId = R.layout.pop_bottom_shareborder;
                    bOutsideTouchable = false;
                    break;
                case F_TENCENT:
                    layoutId = R.layout.pop_bottom_shareborder_tencent;
                    bOutsideTouchable = true;
                    bgColor = 0x00111111;
                    break;
            }
            ///< 创建弹窗视图
            builder = new BasePop.Builder(contextWeakReference.get())
                    .create(_anchor)
                    .setView(layoutId)
                    .setBackgroundDrawable(bgColor)
                    .setAnimation(BasePopView.ANIMATION.TRANSLATE)
                    .setOutsideTouchable(bOutsideTouchable)
                    .setWidthAndHeight(LinearLayout.LayoutParams.MATCH_PARENT,  LinearLayout.LayoutParams.WRAP_CONTENT);
            ///< 背景弹窗丫丫呀
            bg_builder = new BasePop.Builder(contextWeakReference.get())
                    .create(_anchor)
                    .setView(new View(contextWeakReference.get()))
                    .setBackgroundDrawable(BasePop.bgColor)
                    .setWidthAndHeight(LinearLayout.LayoutParams.MATCH_PARENT,  LinearLayout.LayoutParams.MATCH_PARENT);
            return this;
        }

        /**
         * 设置标题和图标
         * @param _share2Name
         * @param _share2Icon
         * @return
         */
        public SharePopView.Builder setTitleAndIcon(List<String> _share2Name, List<Integer> _share2Icon) {
            List<String> shareName = _share2Name;
            List<Integer> shareIcon = _share2Icon;
            if (null == shareName){
                shareName = new ArrayList<>();
                shareName.add("朋友圈");
                shareName.add("微信");
                shareName.add("QQ");
                shareName.add("新浪");
                shareName.add("复制链接");
            }
            if (null == shareIcon){
                shareIcon = new ArrayList<>();
                shareIcon.add(R.drawable.share_circle);
                shareIcon.add(R.drawable.share_wechat);
                shareIcon.add(R.drawable.share_qq);
                shareIcon.add(R.drawable.share_sina);
                shareIcon.add(R.drawable.share_link);
            }
            this.share2Name = shareName;
            this.share2Icon = shareIcon;
            return this;
        }
        /**
         * 分享弹窗
         * @param _simple_gravity - 提供上下两种平移显示方式
         * @return
         */
        public BasePop.Builder showShareBorder(BasePopView.SIMPLE_GRAVITY _simple_gravity,
                                               SharePopView.SHOW_TYPE _show_type,
                                               OnEventListenner.OnShareClickListenner _onShareClickListenner) {
            ///< 获取弹窗视图
            View popView = builder.getView();
            ///< 获取视图控件
            RecyclerView shareToRev = popView.findViewById(R.id.pbs_shareToRev);
            Button shareCancelBtn = popView.findViewById(R.id.pbs_shareCancelBtn);

            ///< 如果是从上往下弹出分享弹窗，则隐藏取消按钮
            if (BasePopView.SIMPLE_GRAVITY.FROM_TOP == _simple_gravity){
                shareCancelBtn.setVisibility(View.GONE);
                ///< 点击外部消失【重新】打开
                builder.setOutsideTouchable(true);
            }else{
                _simple_gravity = BasePopView.SIMPLE_GRAVITY.FROM_BOTTOM;
            }

            ///< 填充分享功能图标和文字
            if (_show_type == SHOW_TYPE.HORIZON){
                ///< 横向布局
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(contextWeakReference.get());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                shareToRev.setLayoutManager(linearLayoutManager);
            }else if (_show_type == SHOW_TYPE.GRID){
                ///< 网格布局
                GridLayoutManager gridLayoutManager = new GridLayoutManager(contextWeakReference.get(), 5);
                shareToRev.setLayoutManager(gridLayoutManager);
            }
            ///< 创建适配器
            ShareBorderAdapter shareBorderAdapter = new ShareBorderAdapter(
                    contextWeakReference.get(), share2Name, share2Icon,
                    share_type, _show_type, _onShareClickListenner);
            ///< 设置间距
            shareToRev.addItemDecoration(new SpacesItemDecoration(contextWeakReference.get(),
                    share2Icon.size(), 20));
            ///< 设置适配器
            shareToRev.setAdapter(shareBorderAdapter);
            ///< 弹窗消失事件
            shareCancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != builder){
                        builder.dissmiss();
                        builder = null;
                    }
                }
            });
            ///< 背景弹窗走一走
            bg_builder.show(BasePopView.SIMPLE_GRAVITY.CENTER_IN_PARENT);
            builder.setOnDissmiss(new OnEventListenner.OnBaseListenner() {
                @Override
                public void onDissmiss() {
                    bg_builder.dissmiss();
                }
            });
            ///< 显示弹窗
            return builder.show(_simple_gravity);
        }
    }
}
