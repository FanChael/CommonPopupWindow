package pop.hl.com.commonpopupwindow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import pop.hl.com.poplibrary.BasePop;
import pop.hl.com.poplibrary.PopView;
import pop.hl.com.poplibrary.ScreenUtil;

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
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    private BasePop.Builder builder;

    public void testPop(View view) {
        ///< 已经显示则不再重复创建显示
        if (null != builder && builder.bIsShowing()) {
            return;
        }
        ///< 显示测试
        //        builder =  new BasePop.Builder(this)
        //                .create(view)
        //                .setView(R.layout.activity_pop)
        //                .setWidthAndHeight(ScreenUtil.getScreenW(this), 500)
        //                .setOutsideTouchable(false)
        //                .setBackgroundDrawable(0xff00aa00)
        //                .setOnClickEvent(new PopView.OnClickListenner() {
        //                    @Override
        //                    public void onClick(View view) {
        //                        if (view.getId() == R.id.ap_leftBtn){
        //                            builder.dissmiss();
        //                        }
        //                    }
        //                })
        //                .setAnimation(PopView.ANIMATION.TRANSLATE)
        //                //.show(view, Gravity.LEFT);
        //                //.showAsDropDown(view, 10, 0, Gravity.LEFT);
        //                //.showLocation(view, Gravity.LEFT | Gravity.TOP, 100, 600);
        //                //.show(view.getRootView(), Gravity.RIGHT); ///< view.getRootView() - 页面根布局
        //
        //                //.show(PopView.GRAVITY.CENTER_IN_PARENT);
        //                //.show(PopView.GRAVITY.CENTER_IN_PARENT);
        //                .show(PopView.SIMPLE_GRAVITY.FROM_BOTTOM);
        ///< 封装创建显示
        builder = PopView.show(this,
                view, R.layout.activity_pop,
                ScreenUtil.getScreenW(this), 500,
                PopView.ANIMATION.SCALE, new PopView.OnClickListenner() {
                    @Override
                    public void onClick(View view) {
                        if (view.getId() == R.id.ap_leftBtn) {
                            builder.dissmiss();
                        }
                    }
                }, PopView.SIMPLE_GRAVITY.FROM_BOTTOM);
    }
}
