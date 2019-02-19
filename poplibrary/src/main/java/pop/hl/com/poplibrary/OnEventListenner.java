package pop.hl.com.poplibrary;

import android.view.View;

/*
*@Description: 事件管理类
*@Author: hl
*@Time: 2019/2/15 15:38
*/
public class OnEventListenner {
    /**
     * 自定义点击事件回调
     */
    public interface OnBaseClickListenner{
        void onClick(View view);
    }
    /**
     * 弹窗消失回调
     */
    public interface OnBaseListenner{
        void onDissmiss();
    }
    /**
     * 底部分享弹窗点击事件回调
     */
    public interface OnShareClickListenner{
        void onClick(View view, int pos);
    }
    /**
     * 登录注册弹窗点击事件回调
     * parmas - 表示当前弹窗对应的编辑框的内容（从上之下)的数组值
     *        - 如果没有编辑框，则返回的是null(无编辑框内容返回)
     * callback_type - 是各个按钮点击类型LgRgPopView.CALLBACK_TYPE
     */
    public interface OnLRClickListenner{
        void onClick(View view, String[] parmas, LgRgPopView.CALLBACK_TYPE callback_type);
    }
}
