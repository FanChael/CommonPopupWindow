package pop.hl.com.poplibrary;

import android.view.View;

/*
*@Description: 弹窗View总类 + 包含点击事件回调
*@Author: hl
*@Time: 2019/2/12 14:38
*/
public class PopView {
    /**
     * 点击事件
     */
    public interface OnClickListenner{
        public void onClick(View view);
    }
}
