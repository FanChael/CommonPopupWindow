package pop.hl.com.poplibrary.base;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import java.lang.ref.WeakReference;

import pop.hl.com.poplibrary.BasePopView;
import pop.hl.com.poplibrary.OnEventListenner;
import pop.hl.com.poplibrary.R;
import pop.hl.com.poplibrary.utils.ScreenUtil;

import static pop.hl.com.poplibrary.BasePopView.ANIMATION.NONE;

/*
 *@Description: 通用PopupWindow - 自定义布局
 *@Author: hl
 *@Time: 2019/2/12 9:58
 */
public class BasePop extends PopupWindow {
    public static final int bgColor = 0x80000000;

    public BasePop(Context context) {
        super(context);
        /******设置默认属性******/
        ///< 设置是否允许PopupWindow的范围超过屏幕范围
        setClippingEnabled(true);
        ///< 设置背景颜色 半透明纯黑 【不设置，默认全屏有白边】
        setBackgroundDrawable(new ColorDrawable(bgColor));
    }

    public BasePop(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BasePop(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public BasePop(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        /******设置默认属性******/
        ///< 设置是否允许PopupWindow的范围超过屏幕范围
        setClippingEnabled(true);
        ///< 设置背景颜色 半透明纯黑 【不设置，默认全屏有白边】
        setBackgroundDrawable(new ColorDrawable(bgColor));
    }

    /*
    *@Description: 通用PopupWindow-建造者
    *@Author: hl
    *@Time: 2019/2/15 11:49
    */
    public static class Builder {
        private View popView = null;
        private BasePop basePop = null;
        private WeakReference<Context> contextWeakReference;
        private WeakReference<View> viewWeakReference;
        private BasePopView.ANIMATION baseAnimation = NONE;

        public Builder(Context _context) {
            this.contextWeakReference = new WeakReference<>(_context);
        }

        public Builder create(View _anchor) {
            this.viewWeakReference = new WeakReference<>(_anchor);
            basePop = new BasePop(contextWeakReference.get());
            return this;
        }

        /**
         * 设置弹窗视图
         * @param _layoutResId
         * @return
         */
        public Builder setView(int _layoutResId) {
            if (_layoutResId != 0) {
                popView = LayoutInflater.from(contextWeakReference.get()).inflate(_layoutResId, null);
            }
            setContentView();
            return this;
        }

        /**
         * 创建弹窗视图
         * @param _layoutResId
         * @return
         */
        public Builder createView(int _layoutResId) {
            if (_layoutResId != 0) {
                popView = LayoutInflater.from(contextWeakReference.get()).inflate(_layoutResId, null);
            }
            return this;
        }

        /**
         * 获取视图
         * @return
         */
        public View getView() {
            return popView;
        }

        /**
         * 设置弹窗视图
         * @param _view
         * @return
         */
        public Builder setView(View _view) {
            popView = _view;
            setContentView();
            return this;
        }

        /**
         * 具体设置视图
         */
        private void setContentView() {
            if (null == popView) {
                try {
                    throw new Exception("No pop view");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (null != basePop) {
                basePop.setContentView(popView);
            }
        }

        /**
         * 设置宽高 0 - 表示内容包裹 -1 - 表示全屏  其他表示具体宽高
         * @param width
         * @param height
         * @return
         */
        public Builder setWidthAndHeight(int width, int height) {
            if (width == 0 && height == 0) {
                ///< 如果没设置宽高，默认是WRAP_CONTENT
                basePop.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
                basePop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            } else if (width == -1 && height == -1) {
                basePop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                basePop.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            }else if (width > 0 && height == 0) {
                basePop.setWidth(width);
                basePop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            }else if (width == -1 && height == 0) {
                basePop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                basePop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            }else if (width > 0 && height == -1) {
                basePop.setWidth(width);
                basePop.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            }else {
                basePop.setWidth(width);
                basePop.setHeight(height);
            }
            return this;
        }

        /**
         * 设置背景颜色
         * @param hexColor
         * @return
         */
        public Builder setBackgroundDrawable(int hexColor) {
            basePop.setBackgroundDrawable(new ColorDrawable(hexColor));
            return this;
        }

        /**
         * 设置Outside是否可点击
         * @param touchable
         * @return
         */
        public Builder setOutsideTouchable(boolean touchable) {
            ///< 设置outside是否可点击
            basePop.setOutsideTouchable(touchable);
            basePop.setFocusable(touchable);
            return this;
        }

        /**
         * 设置显示动画
         * @param animation
         * @return
         */
        public Builder setAnimation(BasePopView.ANIMATION animation){
            baseAnimation = animation;
            return this;
        }

        /**
         * 设置点击事件
         * @param _onClickListenner - 事件回调
         */
        public Builder setOnClickEvent(final OnEventListenner.OnBaseClickListenner _onClickListenner){
            if (null != popView && popView instanceof ViewGroup){
                ViewGroup viewGroup = (ViewGroup) popView;
                for (int i = 0; i < viewGroup.getChildCount(); ++i) {
                    viewGroup.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            _onClickListenner.onClick(v);
                        }
                    });
                }
            }
            return this;
        }

        /**
         * 弹窗消失回调
         * @param _onBaseListenner
         * @return
         */
        public Builder setOnDissmiss(final OnEventListenner.OnBaseListenner _onBaseListenner){
            if (null != basePop){
                basePop.setOnDismissListener(new OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        if (null != _onBaseListenner){
                            _onBaseListenner.onDissmiss();
                        }
                    }
                });
            }
            return this;
        }

        /**
         * 是否显示
         * @return
         */
        public boolean bIsShowing(){
            if (null == basePop){
                return false;
            }
            return basePop.isShowing();
        }

        /**
         * 简单位置显示-居中显示
         *
         * @param _anchor
         */
        public Builder show(View _anchor) {
            show(_anchor, Gravity.CENTER);
            return this;
        }

        /**
         * 简单位置指定方向显示
         *
         * @param anchor
         * @param gravity
         */
        public Builder show(View anchor, int gravity) {
            showLocation(anchor, gravity, 0, 0);
            return this;
        }

        /**
         * 显示在anchor之下
         *
         * @param anchor
         * @param xoff
         * @param yoff
         * @param gravity
         */
        public Builder showAsDropDown(View anchor, int xoff, int yoff, int gravity) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                basePop.showAsDropDown(anchor, xoff, yoff, gravity);
            } else {
                basePop.showAsDropDown(anchor, xoff, yoff);
            }
            return this;
        }

        /**
         * 显示在具体位置
         *
         * @param parent
         * @param gravity
         * @param x
         * @param y
         */
        public Builder showLocation(View parent, int gravity, int x, int y) {
            basePop.showAtLocation(parent, gravity, x, y);
            return this;
        }

        /**
         * 显示方位
         * @param _gravity
         * @return
         */
        public Builder show(BasePopView.GRAVITY _gravity){
            if (null == viewWeakReference){
                try {
                    throw new Exception("No achor view for create(achor)");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            ///< 显示动画
            switch (baseAnimation){
                case SCALE:
                    switch (_gravity){
                        case LEFTBOTTOM_TO_RIGHTTOP:
                        case LEFTBOTTOM_TO_LEFTTOP:
                            basePop.setAnimationStyle(R.style.style_pop_animation_from_leftbottom);
                            break;
                        case RIGHTTOP_TO_RIGHTTOP:
                        case RIGHTTOP_TO_RIGHTBOTTOM:
                        case RIGHTTOP_TO_LEFTBOTTOM:
                            basePop.setAnimationStyle(R.style.style_pop_animation_from_righttop);
                            break;
                        case RIGHTBOTTOM_TO_LEFTTOP:
                        case RIGHTBOTTOM_TO_RIGHTTOP:
                            basePop.setAnimationStyle(R.style.style_pop_animation_from_rightbottom);
                            break;
                        default:
                            basePop.setAnimationStyle(R.style.style_pop_animation_from_lefttop);
                    }
                    break;
            }

            ///< 获取相关控件信息
            ///< 得到显示弹窗宽度popW
            ///< 得到点击控件左边位置achorX
            int popW = basePop.getWidth();
            int popH = basePop.getHeight();
            int achorW = viewWeakReference.get().getMeasuredWidth();
            int achorH = viewWeakReference.get().getMeasuredHeight();
            int screenW = ScreenUtil.getScreenW(contextWeakReference.get());
            int screenH = ScreenUtil.getScreenH(contextWeakReference.get());
            int[] achorLocation = new  int[2] ;
            viewWeakReference.get().getLocationInWindow(achorLocation); //获取在当前窗口内的绝对坐标，含toolBar
            Log.e("test", "popW=" + popW);
            Log.e("test", "popH=" + popH);
            Log.e("test", "screenW=" + screenW);
            Log.e("test", "screenH=" + screenH);
            Log.e("test", "location[0]=" + achorLocation[0]);
            Log.e("test", "location[1]=" + achorLocation[1]);

            /**
             * 分两部分处理
             * 1. 再底部显示的稍微控制下显示的位置，如果右侧空间不够就显示左边，依次类推
             * 2. 如果是achor控件上部分显示，则不用做特别多处理...
             * 3.
             *  3.1 showAsDropDown本身无论偏移多大,不会跑出屏幕，因此chor控件上部分可以不用特别处理
             *  3.2 showAsDropDown本身无论偏移多大,不会跑出屏幕，但是我们为了处理显示效果，所以针对achor下部分显示做了简单处理
             *  3.3 /** 这种方式可以控制超过屏幕范围显示
                public  void showAsDropDown(final PopupWindow pw, final View anchor, final int xoff, final int yoff) {
                    if (Build.VERSION.SDK_INT >= 24) {
                        Rect visibleFrame = new Rect();
                        anchor.getGlobalVisibleRect(visibleFrame);
                        int height = anchor.getResources().getDisplayMetrics().heightPixels - visibleFrame.bottom;
                        pw.setHeight(height);
                        pw.showAsDropDown(anchor, xoff, yoff);
                    } else {
                        pw.showAsDropDown(anchor, xoff, yoff);
                    }
                }
             */
            switch (_gravity){
                case LEFTTOP_TO_LEFTBOTTOM:
                    ///< 如果screen_w - achorX < popW， 表示无法显示完全，则显示弹窗显示模式为RIGHTTOP_TO_LEFTBOTTOM
                    if (screenW - achorLocation[0] < popW){
                        ///< 右上角靠achor左下角显示
                        basePop.showAsDropDown(viewWeakReference.get(), -popW, 0);
                    }else{
                        basePop.showAsDropDown(viewWeakReference.get(), 0, 0);
                    }
                    break;
                case LEFTTOP_TO_RIGHTBOTTOM:
                    ///< 如果(screenW - achorLocation[0] - achorW)< popW， 表示无法显示完全，则显示弹窗显示模式为RIGHTTOP_TO_RIGHTBOTTOM
                    if ((screenW - achorLocation[0] - achorW) < popW){
                        ///< 右上角靠achor右下角显示
                        basePop.showAsDropDown(viewWeakReference.get(), achorW - popW, 0);
                    }else{
                        basePop.showAsDropDown(viewWeakReference.get(), achorW, 0);
                    }
                    break;
                case LEFTTOP_TO_LEFTTOP:
                    ///< 如果screen_w - achorX < popW， 表示无法显示完全，则显示弹窗显示模式为RIGHTTOP_TO_LEFTBOTTOM
                    if (screenW - achorLocation[0] < popW){
                        ///< 右上角靠achor左下角显示
                        basePop.showAsDropDown(viewWeakReference.get(), -popW, -achorH);
                    }else{
                        basePop.showAsDropDown(viewWeakReference.get(), 0, -achorH);
                    }
                    break;
                case LEFTTOP_TO_RIGHTTOP:
                    ///< 如果(screenW - achorLocation[0] - achorW)< popW， 表示无法显示完全，则显示弹窗显示模式为RIGHTTOP_TO_RIGHTTOP
                    if ((screenW - achorLocation[0] - achorW) < popW){
                        ///< 右上角靠achor右上角显示
                        basePop.showAsDropDown(viewWeakReference.get(), achorW - popW, -achorH);
                    }else{
                        basePop.showAsDropDown(viewWeakReference.get(), achorW, -achorH);
                    }
                    break;
                case RIGHTTOP_TO_LEFTBOTTOM:
                    ///< 如果achorLocation[0] < popW， 表示无法显示完全，则显示弹窗显示模式为LEFTTOP_TO_RIGHTBOTTOM
                    if (achorLocation[0] < popW){
                        ///< 左上角靠achor右下角显示
                        basePop.showAsDropDown(viewWeakReference.get(), achorW, 0);
                    }else{
                        basePop.showAsDropDown(viewWeakReference.get(), -popW, 0);
                    }
                    break;
                case RIGHTTOP_TO_RIGHTBOTTOM:
                    ///< 如果achorLocation[0] + achorW < popW， 表示无法显示完全，则显示弹窗显示模式为LEFTTOP_TO_LEFTBOTTOM
                    if (achorLocation[0] + achorW < popW){
                        ///< 左上角靠achor左下角显示
                        basePop.showAsDropDown(viewWeakReference.get(), 0, 0);
                    }else{
                        basePop.showAsDropDown(viewWeakReference.get(), achorW - popW, 0);
                    }
                    break;
                case RIGHTTOP_TO_RIGHTTOP:
                    ///< 如果achorLocation[0] + achorW < popW， 表示无法显示完全，则显示弹窗显示模式为LEFTTOP_TO_LEFTTOP
                    if (achorLocation[0] + achorW < popW){
                        ///< 左上角靠achor左上角显示
                        basePop.showAsDropDown(viewWeakReference.get(), 0, -achorH);
                    }else{
                        basePop.showAsDropDown(viewWeakReference.get(), achorW - popW, -achorH);
                    }
                    break;
                case RIGHTBOTTOM_TO_LEFTTOP:
                    basePop.showAsDropDown(viewWeakReference.get(), -popW, -(popH + achorH));
                    break;
                case RIGHTBOTTOM_TO_RIGHTTOP:
                    basePop.showAsDropDown(viewWeakReference.get(), achorW - popW, -(popH + achorH));
                    break;
                case LEFTBOTTOM_TO_RIGHTTOP:
                    basePop.showAsDropDown(viewWeakReference.get(), achorW, -(popH + achorH));
                    break;
                case LEFTBOTTOM_TO_LEFTTOP:
                    basePop.showAsDropDown(viewWeakReference.get(), 0, -(popH + achorH));
                    break;
            }
            return this;
        }

        /**
         * 显示+简单方位
         * @param _gravity
         * @return
         */
        public Builder show(BasePopView.SIMPLE_GRAVITY _gravity){
            if (null == viewWeakReference){
                try {
                    throw new Exception("No achor view for create(achor)");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (null != baseAnimation) {
                ///< 显示动画
                switch (baseAnimation) {
                    case SCALE:
                        switch (_gravity) {
                            case CENTER_IN_PARENT:
                                basePop.setAnimationStyle(R.style.style_pop_animation_from_center);
                                break;
                        }
                        break;
                    case TRANSLATE:
                        switch (_gravity) {
                            case FROM_BOTTOM:
                                basePop.setAnimationStyle(R.style.style_translate_pop_animation_from_bottom);
                                break;
                            case FROM_TOP:
                                basePop.setAnimationStyle(R.style.style_translate_pop_animation_from_top);
                                break;
                            case FROM_LEFT:
                                basePop.setAnimationStyle(R.style.style_translate_pop_animation_from_left);
                                break;
                            case FROM_RIGHT:
                                basePop.setAnimationStyle(R.style.style_translate_pop_animation_from_right);
                                break;
                        }
                        break;
                }
            }
            switch (_gravity){
                case CENTER_IN_PARENT:
                    basePop.showAtLocation(viewWeakReference.get(), Gravity.CENTER, 0, 0);
                    break;
                case FROM_BOTTOM:
                    basePop.showAtLocation(viewWeakReference.get(), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                    break;
                case FROM_TOP:
                    basePop.showAtLocation(viewWeakReference.get(), Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                    break;
                case FROM_LEFT:
                    basePop.showAtLocation(viewWeakReference.get(), Gravity.LEFT | Gravity.CENTER_VERTICAL, 0, 0);
                    break;
                case FROM_RIGHT:
                    basePop.showAtLocation(viewWeakReference.get(), Gravity.RIGHT | Gravity.CENTER_VERTICAL, 0, 0);
                    break;
            }
            return this;
        }

        /**
         * 消失弹窗
         */
        public void dissmiss(){
            if (null != basePop && basePop.isShowing()){
                basePop.dismiss();
            }
        }
    }
}
