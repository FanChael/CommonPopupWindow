package pop.hl.com.poplibrary;

import android.content.Context;
import android.view.View;

/*
*@Description: 弹窗View总类 + 包含点击事件回调
*@Author: hl
*@Time: 2019/2/12 14:38
*/
public class PopView {
    /**
     * 显示方位
     */
    public enum GRAVITY {
        LEFTTOP_TO_LEFTBOTTOM, LEFTTOP_TO_RIGHTBOTTOM,
        LEFTTOP_TO_LEFTTOP, LEFTTOP_TO_RIGHTTOP,
        RIGHTTOP_TO_LEFTBOTTOM, RIGHTTOP_TO_RIGHTBOTTOM,
        RIGHTTOP_TO_RIGHTTOP, RIGHTBOTTOM_TO_LEFTTOP,
        RIGHTBOTTOM_TO_RIGHTTOP, LEFTBOTTOM_TO_RIGHTTOP,
        LEFTBOTTOM_TO_LEFTTOP, CENTER_IN_PARENT,
        FROM_BOTTOM
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
     */
    public enum ANIMATION {
        NONE, SCALE, TRANSLATE
    }


    /**
     * 点击事件
     */
    public interface OnClickListenner{
        public void onClick(View view);
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
     * @param animation - 不需要给null
     * @param _onClickListenner - 不需要给null
     * @param _gravity 【必填】
     * @return BasePop.Builder
     */
    public static BasePop.Builder show(Context _context, View _anchor,
                                       int _layoutResId, int _popW, int _popH,
                                       boolean bOutsideTouchable, int _backColor,
                                       PopView.ANIMATION animation,
                                       PopView.OnClickListenner _onClickListenner,
                                       PopView.SIMPLE_GRAVITY _gravity){
        BasePop.Builder builder = null;
        if (-1 != _layoutResId){
            builder = new BasePop.Builder(_context)
                    .create(_anchor)
                    .setView(_layoutResId)
                    .setOutsideTouchable(bOutsideTouchable);
        }
        if (_popW > 0 && _popH > 0){
            builder.setWidthAndHeight(_popW, _popH);
        }
        if (-1 != _backColor){
            builder.setBackgroundDrawable(_backColor);
        }
        if (null != animation){
            builder.setAnimation(animation);
        }
        if (null != _onClickListenner){
            builder.setOnClickEvent(_onClickListenner);
        }
        return builder.show(_gravity);
    }
    public static BasePop.Builder show(Context _context, View _anchor,
                                       int _layoutResId, int _popW, int _popH,
                                       PopView.OnClickListenner _onClickListenner,
                                       PopView.SIMPLE_GRAVITY _gravity){
        return show(_context, _anchor,
                _layoutResId, _popW, _popH,
                false, BasePop.bgColor,
                null, null, _gravity);
    }
    public static BasePop.Builder show(Context _context, View _anchor,
                                       int _layoutResId, int _popW, int _popH,
                                       PopView.ANIMATION animation,
                                       PopView.OnClickListenner _onClickListenner,
                                       PopView.SIMPLE_GRAVITY _gravity){
        return show(_context, _anchor,
                _layoutResId, _popW, _popH,
                false, BasePop.bgColor,
                animation, _onClickListenner, _gravity);
    }
}
