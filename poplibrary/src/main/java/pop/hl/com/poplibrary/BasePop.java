package pop.hl.com.poplibrary;

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

/*
 *@Description: 通用PopupWindow - 自定义布局
 *@Author: hl
 *@Time: 2019/2/12 9:58
 */
public class BasePop extends PopupWindow {
    public BasePop(Context context) {
        super(context);
        /******设置默认属性******/
        ///< 设置是否允许PopupWindow的范围超过屏幕范围
        setClippingEnabled(true);
        ///< 设置背景颜色 半透明纯黑 【不设置，默认全屏有白边】
        setBackgroundDrawable(new ColorDrawable(0x80000000));
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
        setBackgroundDrawable(new ColorDrawable(0x80000000));
    }

    /**
     * 通用PopupWindow-建造者
     */
    public static class Builder {
        private Context context = null;
        private View popView = null;
        private BasePop basePop = null;

        public Builder(Context _context) {
            this.context = _context;
        }

        public Builder create() {
            basePop = new BasePop(context);
            return this;
        }

        /**
         * 设置弹窗视图
         *
         * @param _layoutResId - 布局ID
         * @return
         */
        public Builder setView(int _layoutResId) {
            if (_layoutResId != 0) {
                popView = LayoutInflater.from(context).inflate(_layoutResId, null);
            }
            setContentView();
            return this;
        }

        /**
         * 设置弹窗视图
         *
         * @param _view - 视图View
         * @return
         */
        public Builder setView(View _view) {
            popView = _view;
            setContentView();
            return this;
        }

        /**
         * 具体设置视图
         *
         * @throws Exception
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
         *
         * @param width
         * @param height
         */
        public Builder setWidthAndHeight(int width, int height) {
            if (width == 0 || height == 0) {
                ///< 如果没设置宽高，默认是WRAP_CONTENT
                basePop.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
                basePop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            } else if (width == -1 || height == -1) {
                basePop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                basePop.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            } else {
                basePop.setWidth(width);
                basePop.setHeight(height);
            }
            return this;
        }

        /**
         * 设置背景颜色
         *
         * @param hexColor
         * @return
         */
        public Builder setBackgroundDrawable(int hexColor) {
            basePop.setBackgroundDrawable(new ColorDrawable(hexColor));
            return this;
        }

        /**
         * 设置Outside是否可点击
         *
         * @param touchable 是否可点击
         */
        public Builder setOutsideTouchable(boolean touchable) {
            ///< 设置outside是否可点击
            basePop.setOutsideTouchable(touchable);
            basePop.setFocusable(touchable);
            return this;
        }

        /**
         * 设置点击事件
         * @param _onClickListenner - 事件回调
         */
        public Builder setOnClickEvent(final PopView.OnClickListenner _onClickListenner){
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
         * 简单位置显示-居中显示
         *
         * @param anchor
         */
        public Builder show(View anchor) {
            show(anchor, Gravity.CENTER);
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
         * 消失弹窗
         */
        public void dissmiss(){
            if (null != basePop && basePop.isShowing()){
                basePop.dismiss();
            }
        }
    }
}
