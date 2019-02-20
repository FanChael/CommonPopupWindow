package pop.hl.com.poplibrary.utils;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

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

	/**
	 * 设置控件间距
	 * @param v
	 * @param _l
	 * @param _t
	 * @param _r
	 * @param _b
	 */
	public static void setMargin(View v, int _l, int _t, int _r, int _b)
	{
		if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)
		{
			ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
			int l = _l, t = _t,
				r = _r, b = _b;
			if (-10000 == _l)
			{
				l = p.leftMargin;
			}
			if (-10000 == _r)
			{
				r = p.rightMargin;
			}
			if (-10000 == _t)
			{
				t = p.topMargin;
			}
			if (-10000 == _b)
			{
				b = p.bottomMargin;
			}
			p.setMargins(l, t, r, b);
			v.requestLayout();
		}
	}

	/**
	 * 获取控件的间距
	 * @param v
	 * @param flag 0 - 3 -> 左上右下
	 * @return 其他flag为-1
	 */
	public static int getMargin(View v, int flag)
	{
		if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)
		{
			ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
			if (0 == flag){
				return p.leftMargin;
			}
			if (1 == flag){
				return p.topMargin;
			}
			if (2 == flag){
				return p.rightMargin;
			}
			if (3 == flag){
				return p.bottomMargin;
			}
		}
		return -1;
	}

	/**
	 * 设置父组件是ConstraintLayout的控件的宽高
	 * @param v
	 * @param w -1表示无效设置
	 * @param h -1表示无效设置
	 */
	public static void setConstraintLayoutWH(View v, int w, int h) {
		ConstraintLayout.LayoutParams layoutT = (ConstraintLayout.LayoutParams) v.getLayoutParams();
		if (-1 != w){
			layoutT.width = w;
		}
		if (-1 != h){
			layoutT.height = h;
		}
		v.setLayoutParams(layoutT);
	}
}
