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
}