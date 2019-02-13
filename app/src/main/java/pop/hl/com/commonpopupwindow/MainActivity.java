package pop.hl.com.commonpopupwindow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import pop.hl.com.poplibrary.BasePop;
import pop.hl.com.poplibrary.PopView;

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
        builder =  new BasePop.Builder(this)
                .create()
                .setView(R.layout.activity_pop)
                .setWidthAndHeight(500, 500)
                .setOutsideTouchable(false)
                .setBackgroundDrawable(0xff00aa00)
                .setOnClickEvent(new PopView.OnClickListenner() {
                    @Override
                    public void onClick(View view) {
                        if (view.getId() == R.id.ap_leftBtn){
                            builder.dissmiss();
                        }
                    }
                })
                //.show(view, Gravity.LEFT);
                //.showAsDropDown(view, 10, 0, Gravity.LEFT);
                //.showLocation(view, Gravity.LEFT | Gravity.TOP, 100, 600);
                //.show(view.getRootView(), Gravity.RIGHT); ///< view.getRootView() - 页面根布局
                .show(view.getRootView(), Gravity.CENTER);
    }
}
