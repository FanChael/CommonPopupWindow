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
     * 更新弹窗点击事件回调
     * 1. 如果是强制更新的情况，则会返回进度条控件progressBar给调用者，方便外部设置进度
     * 2. 如果是非强制更新，则不显示进度条控件; 此时显示暂不更新文本按钮
     */
    public interface OnUpdateClickListenner{
        void onClick(View view, View progressBar);
    }
}
