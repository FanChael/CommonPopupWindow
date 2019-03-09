package pop.hl.com.poplibrary;

import android.content.Context;
import android.view.View;

import pop.hl.com.poplibrary.base.BasePop;

/*
*@Description: 自定义弹窗API + 包含点击事件回调
*@Author: hl
*@Time: 2019/2/12 14:38
*/
public class BasePopView {
    /**
     * 显示方位【基于控件】
     */
    public enum GRAVITY {
        LEFTTOP_TO_LEFTBOTTOM, LEFTTOP_TO_RIGHTBOTTOM,
        LEFTTOP_TO_LEFTTOP, LEFTTOP_TO_RIGHTTOP,
        RIGHTTOP_TO_LEFTBOTTOM, RIGHTTOP_TO_RIGHTBOTTOM,
        RIGHTTOP_TO_RIGHTTOP, RIGHTBOTTOM_TO_LEFTTOP,
        RIGHTBOTTOM_TO_RIGHTTOP, LEFTBOTTOM_TO_RIGHTTOP,
        LEFTBOTTOM_TO_LEFTTOP
    }

    /**
     * 简单上下左右中显示
     */
    public enum SIMPLE_GRAVITY {
        CENTER_IN_PARENT, FROM_BOTTOM, FROM_TOP,
        FROM_LEFT, FROM_RIGHT
    }

    /**
     * 显示动画
     * SCALE - 缩放
     * TRANSLATE - 平移
     * FOLD - 折叠 - 从下往上/从上往下(暂不支持简单方位显示方式)
     */
    public enum ANIMATION {
        NONE, SCALE, TRANSLATE, FOLD
    }

    /**
     * 传递对应参数进行窗体创建和显示
     * @param _context 【必填】
     * @param _anchor 【必填】
     * @param _layoutResId 【必填】
     * @param _popW 不需要给 < 0 - 那样需要自己做好布局自适应处理
     * @param _popH 不需要给 < 0 - 那样需要自己做好布局自适应处理
     * @param bOutsideTouchable 【必填】
     * @param _backColor - 不需要给-1
     * @param _animation - 不需要给null
     * @param _onClickListenner - 不需要给null
     * @param _gravity 【必填】 BasePopView.SIMPLE_GRAVITY
     * @return BasePop.Builder
     */
    public static BasePop.Builder show(Context _context, View _anchor,
                                       int _layoutResId, int _popW, int _popH,
                                       boolean bOutsideTouchable, int _backColor,
                                       ANIMATION _animation,
                                       OnEventListenner.OnBaseClickListenner _onClickListenner,
                                       SIMPLE_GRAVITY _gravity){
        return show(_context, _anchor, _layoutResId, _popW, _popH,
                bOutsideTouchable, _backColor, _animation,
                _onClickListenner, null, _gravity);
    }
    /**
     * 传递对应参数进行窗体创建和显示
     * @param _context 【必填】
     * @param _anchor 【必填】
     * @param _layoutResId 【必填】
     * @param _popW 不需要给 < 0 - 那样需要自己做好布局自适应处理
     * @param _popH 不需要给 < 0 - 那样需要自己做好布局自适应处理
     * @param bOutsideTouchable 【必填】
     * @param _backColor - 不需要给-1
     * @param _animation - 不需要给null
     * @param _onClickListenner - 不需要给null
     * @param _gravity 【必填】 BasePopView.GRAVITY
     * @return BasePop.Builder
     */
    public static BasePop.Builder show(Context _context, View _anchor,
                                       int _layoutResId, int _popW, int _popH,
                                       boolean bOutsideTouchable, int _backColor,
                                       ANIMATION _animation,
                                       OnEventListenner.OnBaseClickListenner _onClickListenner,
                                       GRAVITY _gravity){
        return show(_context, _anchor, _layoutResId, _popW, _popH,
                bOutsideTouchable, _backColor, _animation,
                _onClickListenner, _gravity, null);
    }
    /**
     * 传递对应参数进行窗体创建和显示
     * @param _context 【必填】
     * @param _anchor 【必填】
     * @param _layoutResId 【必填】
     * @param _popW 不需要给 < 0 - 那样需要自己做好布局自适应处理
     * @param _popH 不需要给 < 0 - 那样需要自己做好布局自适应处理
     * @param bOutsideTouchable 【必填】
     * @param _backColor - 不需要给-1
     * @param _animation - 不需要给null
     * @param _onClickListenner - 不需要给null
     * @param _gravity 【与_sgravity选一个】 BasePopView.GRAVITY
     * @param _sgravity 【与_gravity选一个】 BasePopView.SIMPLE_GRAVITY
     * @return BasePop.Builder
     */
    private static BasePop.Builder show(Context _context, View _anchor,
                                        int _layoutResId, int _popW, int _popH,
                                        boolean bOutsideTouchable, int _backColor,
                                        ANIMATION _animation,
                                        OnEventListenner.OnBaseClickListenner _onClickListenner,
                                        GRAVITY _gravity, SIMPLE_GRAVITY _sgravity){
        BasePop.Builder builder = null;
        if (-1 != _layoutResId){
            builder = new BasePop.Builder(_context)
                    .create(_anchor)
                    .setView(_layoutResId)
                    .setOutsideTouchable(bOutsideTouchable);
        }
        builder.setWidthAndHeight(_popW, _popH);
        if (-1 != _backColor){
            builder.setBackgroundDrawable(_backColor);
        }
        if (null != _animation && ANIMATION.NONE != _animation){
            builder.setAnimation(_animation);
        }
        if (null != _onClickListenner){
            builder.setOnClickEvent(_onClickListenner);
        }
        if (null != _gravity){
            return builder.show(_gravity);
        }
        return builder.show(_sgravity);
    }

    /**
     * 以下是不考虑bOutsideTouchable和_backColor的方法，或者加上不考虑ANIMATION的情况
     * @param _context
     * @param _anchor
     * @param _layoutResId
     * @param _popW
     * @param _popH
     * @param _onClickListenner
     * @param _gravity
     * @return
     */
    public static BasePop.Builder show(Context _context, View _anchor,
                                       int _layoutResId, int _popW, int _popH,
                                       OnEventListenner.OnBaseClickListenner _onClickListenner,
                                       SIMPLE_GRAVITY _gravity){
        return show(_context, _anchor,
                _layoutResId, _popW, _popH,
                false, BasePop.bgColor,
                null, null, _gravity);
    }
    public static BasePop.Builder show(Context _context, View _anchor,
                                       int _layoutResId, int _popW, int _popH,
                                       ANIMATION animation,
                                       OnEventListenner.OnBaseClickListenner _onClickListenner,
                                       SIMPLE_GRAVITY _gravity){
        return show(_context, _anchor,
                _layoutResId, _popW, _popH,
                false, BasePop.bgColor,
                animation, _onClickListenner, _gravity);
    }
    public static BasePop.Builder show(Context _context, View _anchor,
                                       int _layoutResId, int _popW, int _popH,
                                       OnEventListenner.OnBaseClickListenner _onClickListenner,
                                       GRAVITY _gravity){
        return show(_context, _anchor,
                _layoutResId, _popW, _popH,
                false, BasePop.bgColor,
                null, null, _gravity);
    }
    public static BasePop.Builder show(Context _context, View _anchor,
                                       int _layoutResId, int _popW, int _popH,
                                       ANIMATION animation,
                                       OnEventListenner.OnBaseClickListenner _onClickListenner,
                                       GRAVITY _gravity){
        return show(_context, _anchor,
                _layoutResId, _popW, _popH,
                false, BasePop.bgColor,
                animation, _onClickListenner, _gravity);
    }
}
