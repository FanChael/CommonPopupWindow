package pop.hl.com.commonpopupwindow;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import java.util.Random;

import pop.hl.com.poplibrary.AppUpdate;
import pop.hl.com.poplibrary.OnEventListenner;
import pop.hl.com.poplibrary.UpdatePopView;
import pop.hl.com.poplibrary.base.BasePop;
import pop.hl.com.poplibrary.update.DownLoadIntentService;

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
        PermissionTool.checkPermission(this);
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
        ///< 设置更新弹窗样式+升级信息
        AppUpdate appUpdate = new AppUpdate(this, view, R.drawable.update_bg_app_top, 204.0f/450.0f,
                "#FF5C5C", (new Random().nextInt(2)) == 1 ? true : false,
                "1、新增皮皮虾板块\n" + "2、新增皮皮狗板块\n"+ "3、新增皮皮你板块");
        ///< 开启更新，设置apk下载地址+通知栏图标+fileProvider直接启动安装+(md5、versionCode、apk大小)进行已经下载安装包的校验，防止重复下载
        appUpdate.startAppUpdate("https://raw.githubusercontent.com/FanChael/CommonPopupWindow/master/doc/app-debug.apk",
                        R.drawable.share_circle,
                        "pop.hl.com.commonpopupwindow.fileProvider",
                        "6FA8D1B09B54580CA69FA7BF62D0C4A7", 1,2978651);
    }
}
