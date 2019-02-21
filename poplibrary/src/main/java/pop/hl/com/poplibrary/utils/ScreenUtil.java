package pop.hl.com.poplibrary.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Author:hl
 * Time:  2018/5/24 11:34
 * Des:   This is ScreenUtil
 */
public class ScreenUtil
{
	/**
	 * 获取屏幕宽度
	 * @param mContext
	 * @return
	 */
	public static int getScreenW(Context mContext)
	{
		return getScreenWH(mContext, false);
	}

	/**
	 * 获取屏幕高度
	 * @param mContext
	 * @return
	 */
	public static int getScreenH(Context mContext)
	{
		return getScreenWH(mContext, true);
	}

	private static int getScreenWH(Context mContext, boolean bGetH){
		DisplayMetrics dm = new DisplayMetrics();
		dm = mContext.getResources().getDisplayMetrics();
		int screen_w = dm.widthPixels;
		int screen_h  = dm.heightPixels;
		///< 如果宽大于高，宽高互换
		if(screen_w > screen_h)
		{
			int temp = screen_h;
			screen_h = screen_w;
			screen_w = temp;
		}
		if (bGetH){
			return screen_h;
		}
		return screen_w;
	}
}
