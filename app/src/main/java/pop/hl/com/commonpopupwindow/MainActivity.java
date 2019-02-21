package pop.hl.com.commonpopupwindow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import pop.hl.com.poplibrary.BasePopView;
import pop.hl.com.poplibrary.OnEventListenner;
import pop.hl.com.poplibrary.base.BasePop;
import pop.hl.com.poplibrary.utils.ScreenUtil;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        //TextView tv = (TextView) findViewById(R.id.sample_text);
        //tv.setText(stringFromJNI());
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    private BasePop.Builder builder;

    /**
     * 弹窗显示测试
     *
     * @param view
     */
    public void testPop(View view) {
        ///< 已经显示则重新来过
        if (null != builder && builder.bIsShowing()) {
            builder.dissmiss();
        }

        ///< 点击事件走起
        OnEventListenner.OnBaseClickListenner onClickListenner = new OnEventListenner.OnBaseClickListenner() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.ap_leftBtn ||
                        view.getId() == R.id.ap_rightBtn) {
                    builder.dissmiss();
                }
            }
        };

        switch (view.getId()) {
            case R.id.am_lefttop_btn:           ///< Achor右下角缩放显示
                builder = BasePopView.show(this,
                        view, R.layout.activity_pop,
                        ScreenUtil.getScreenW(this) / 2, 500,
                        BasePopView.ANIMATION.SCALE, onClickListenner,
                        BasePopView.GRAVITY.LEFTTOP_TO_RIGHTBOTTOM);
                break;
            case R.id.am_righttop_btn:      ///< Achor左下角缩放显示
                builder = BasePopView.show(this,
                        view, R.layout.activity_pop,
                        ScreenUtil.getScreenW(this) / 2, 500,
                        BasePopView.ANIMATION.SCALE, onClickListenner,
                        BasePopView.GRAVITY.RIGHTTOP_TO_LEFTBOTTOM);
                break;
            case R.id.am_leftbottom_btn:    ///< Achor右上角缩放显示
                builder = BasePopView.show(this,
                        view, R.layout.activity_pop,
                        ScreenUtil.getScreenW(this) / 2, 500,
                        BasePopView.ANIMATION.SCALE, onClickListenner,
                        BasePopView.GRAVITY.LEFTBOTTOM_TO_RIGHTTOP);
                break;
            case R.id.am_rightbottom_btn:   ///< Achor左上角缩放显示
                builder = BasePopView.show(this,
                        view, R.layout.activity_pop,
                        ScreenUtil.getScreenW(this) / 2, 500,
                        BasePopView.ANIMATION.SCALE, onClickListenner,
                        BasePopView.GRAVITY.RIGHTBOTTOM_TO_LEFTTOP);
                break;
            case R.id.am_center_btn:        ///< 居中缩放显示
                builder = BasePopView.show(this,
                        view, R.layout.activity_pop,
                        ScreenUtil.getScreenW(this) / 2, 500,
                        BasePopView.ANIMATION.SCALE, onClickListenner,
                        BasePopView.SIMPLE_GRAVITY.CENTER_IN_PARENT);
                break;
            case R.id.am_top_btn:       ///< 从上往下平移显示
                builder = BasePopView.show(this,
                        view, R.layout.activity_pop,
                        ScreenUtil.getScreenW(this), 500,
                        BasePopView.ANIMATION.TRANSLATE, onClickListenner,
                        BasePopView.SIMPLE_GRAVITY.FROM_TOP);
                break;
            case R.id.am_bottom_btn:    ///< 从下往上平移显示
                builder = BasePopView.show(this,
                        view, R.layout.activity_pop,
                        ScreenUtil.getScreenW(this), 500,
                        BasePopView.ANIMATION.TRANSLATE, onClickListenner,
                        BasePopView.SIMPLE_GRAVITY.FROM_BOTTOM);
                break;
            case R.id.am_left_btn:  ///< 从左往右平移显示
                builder = BasePopView.show(this,
                        view, R.layout.activity_pop,
                        ScreenUtil.getScreenW(this) / 4, ScreenUtil.getScreenH(this),
                        BasePopView.ANIMATION.TRANSLATE, onClickListenner,
                        BasePopView.SIMPLE_GRAVITY.FROM_LEFT);
                break;
            case R.id.am_right_btn: ///< 从右往左平移显示
                builder = BasePopView.show(this,
                        view, R.layout.activity_pop,
                        ScreenUtil.getScreenW(this) / 4, ScreenUtil.getScreenH(this),
                        BasePopView.ANIMATION.TRANSLATE, onClickListenner,
                        BasePopView.SIMPLE_GRAVITY.FROM_RIGHT);
                break;
        }
        ///< 显示测试
        //        builder =  new BasePop.Builder(this)
        //                .create(view)
        //                .setView(R.layout.activity_pop)
        //                .setWidthAndHeight(ScreenUtil.getScreenW(this), 500)
        //                .setOutsideTouchable(false)
        //                .setBackgroundDrawable(0xff00aa00)
        //                .setOnClickEvent(new BasePopView.OnClickListenner() {
        //                    @Override
        //                    public void onClick(View view) {
        //                        if (view.getId() == R.id.ap_leftBtn){
        //                            builder.dissmiss();
        //                        }
        //                    }
        //                })
        //                .setAnimation(BasePopView.ANIMATION.TRANSLATE)
        //                //.show(view, Gravity.LEFT);
        //                //.showAsDropDown(view, 10, 0, Gravity.LEFT);
        //                //.showLocation(view, Gravity.LEFT | Gravity.TOP, 100, 600);
        //                //.show(view.getRootView(), Gravity.RIGHT); ///< view.getRootView() - 页面根布局
        //
        //                //.show(BasePopView.GRAVITY.CENTER_IN_PARENT);
        //                //.show(BasePopView.GRAVITY.CENTER_IN_PARENT);
        //                .show(BasePopView.SIMPLE_GRAVITY.FROM_BOTTOM);
        ///< 封装创建显示
        //        builder = BasePopView.show(this,
        //                view, R.layout.activity_pop,
        //                ScreenUtil.getScreenW(this), 500,
        //                BasePopView.ANIMATION.SCALE, new BasePopView.OnClickListenner() {
        //                    @Override
        //                    public void onClick(View view) {
        //                        if (view.getId() == R.id.ap_leftBtn) {
        //                            builder.dissmiss();
        //                        }
        //                    }
        //                }, BasePopView.SIMPLE_GRAVITY.FROM_BOTTOM);
    }
}
