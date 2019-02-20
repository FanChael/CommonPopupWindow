package pop.hl.com.commonpopupwindow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pop.hl.com.poplibrary.LgRgPopView;
import pop.hl.com.poplibrary.SharePopView;
import pop.hl.com.poplibrary.OnEventListenner;
import pop.hl.com.poplibrary.UpdatePopView;
import pop.hl.com.poplibrary.base.BasePop;
import pop.hl.com.poplibrary.BasePopView;
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
            case R.id.am_share_btn: ///< 分享弹窗显示
            case R.id.am_share_ftecent_btn:
                List<String> _share2Name = new ArrayList<>();
                _share2Name.add("华为");
                _share2Name.add("阿里");
                _share2Name.add("小米");
                _share2Name.add("毛豆");
                _share2Name.add("无聊");
                _share2Name.add("华为");
                _share2Name.add("阿里");
                _share2Name.add("小米");
                _share2Name.add("毛豆");
                _share2Name.add("无聊");
                List<Integer> _share2Icon = new ArrayList<>();
                _share2Icon.add(R.drawable.huawei);
                _share2Icon.add(R.drawable.ali);
                _share2Icon.add(R.drawable.xiaomi);
                _share2Icon.add(R.drawable.moredo);
                _share2Icon.add(R.drawable.share_link);
                _share2Icon.add(R.drawable.huawei);
                _share2Icon.add(R.drawable.ali);
                _share2Icon.add(R.drawable.xiaomi);
                _share2Icon.add(R.drawable.moredo);
                _share2Icon.add(R.drawable.share_link);

                ///< 点击事件回调
                OnEventListenner.OnShareClickListenner onShareClickListenner = new OnEventListenner.OnShareClickListenner() {
                    @Override
                    public void onClick(View view, int pos) {
                        Toast.makeText(MainActivity.this, "pos=" + pos, Toast.LENGTH_SHORT).show();
                    }
                };
                ///< 显示链表添加的分享图标
                int randomValue = new Random().nextInt(2);
                switch (view.getId()) {
                    case R.id.am_share_btn:
                        ///< 显示链表添加的分享图标
                        builder = SharePopView.showShare(this, view,
                                randomValue == 1 ? _share2Name : null,
                                randomValue == 1 ? _share2Icon : null,
                                (new Random().nextInt(2)) == 1 ?
                                        BasePopView.SIMPLE_GRAVITY.FROM_TOP :
                                        BasePopView.SIMPLE_GRAVITY.FROM_BOTTOM,
                                (new Random().nextInt(2)) == 1 ?
                                        SharePopView.SHOW_TYPE.HORIZON :
                                        SharePopView.SHOW_TYPE.GRID,
                                onShareClickListenner);
                        break;
                    case R.id.am_share_ftecent_btn:
                        ///< 显示库默认的显示列表
                        builder = SharePopView.showShareFTencent(this, view,
                                randomValue == 1 ? _share2Name : null,
                                randomValue == 1 ? _share2Icon : null,
                                BasePopView.SIMPLE_GRAVITY.FROM_BOTTOM,
                                (new Random().nextInt(2)) == 1 ?
                                        SharePopView.SHOW_TYPE.HORIZON :
                                        SharePopView.SHOW_TYPE.GRID,
                                onShareClickListenner);
                        break;
                }
                break;
            case R.id.am_register_btn:   ///< Achor左上角缩放显示
                builder = LgRgPopView.showRegister(this,
                        view, "#f0008DCF",
                        new OnEventListenner.OnLRClickListenner() {
                            @Override
                            public void onClick(View view, String[] parmas, LgRgPopView.CALLBACK_TYPE callback_type) {
                                switch (callback_type){
                                    case CLICK_REGISTER:
                                        Toast.makeText(MainActivity.this, "callback_type=" + callback_type + " parmas" + parmas[0], Toast.LENGTH_SHORT).show();
                                        break;
                                    case CLICK_GET_VERYCODE:
                                        break;
                                }
                            }
                        });
                break;
            case R.id.am_login_btn:        ///< 居中缩放显示
                builder = LgRgPopView.showLogin(this,
                        view,LgRgPopView.LOGIN_TYPE.THIRD_ACCOUNT
                                | LgRgPopView.LOGIN_TYPE.PHONE_VERICODE,
                        "#f0008DCF",
                        new OnEventListenner.OnLRClickListenner() {
                            @Override
                            public void onClick(View view, String[] parmas, LgRgPopView.CALLBACK_TYPE callback_type) {
                                switch (callback_type){
                                    case QQ_LOGIN:
                                        Toast.makeText(MainActivity.this, "点击了QQ登录", Toast.LENGTH_SHORT).show();
                                        break;
                                    case WEIXIN_LOGIN:
                                        break;
                                    case CLICK_LOGIN:
                                        break;
                                    default:
                                        break;
                                }
                            }
                        });
                break;
            case R.id.am_normal_update_btn:        ///< 经典更新弹窗
                builder = UpdatePopView.showNormalUpdate(this, view,
                        R.drawable.update_bg_app_top, 204/450,
                        "#FF5C5C",  (new Random().nextInt(2)) == 1 ? true : false,
                        "1、新增皮皮虾板块\n" + "2、新增皮皮狗板块\n"+ "3、新增皮皮你板块",
                        new OnEventListenner.OnUpdateClickListenner() {
                            @Override
                            public void onClick(View view, View progress) {
                                ProgressBar progressBar = (ProgressBar) progress;
                                progressBar.setProgress(80);
                            }
                        });
                break;
        }
        ///< 显示测试
        //        builder =  new BasePop.Builder(this)
        //                .create(view)
        //                .setView(R.layout.activity_pop)
        //                .setWidthAndHeight(ScreenUtil.getScreenW(this), 500)
        //                .setOutsideTouchable(false)
        //                .setBackgroundDrawable(0xff00aa00)
        //                .setOnClickEvent(new BasePopView.OnEventListenner() {
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
        //                BasePopView.ANIMATION.SCALE, new BasePopView.OnEventListenner() {
        //                    @Override
        //                    public void onClick(View view) {
        //                        if (view.getId() == R.id.ap_leftBtn) {
        //                            builder.dissmiss();
        //                        }
        //                    }
        //                }, BasePopView.SIMPLE_GRAVITY.FROM_BOTTOM);
    }
}
