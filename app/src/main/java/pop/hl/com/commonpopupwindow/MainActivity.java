package pop.hl.com.commonpopupwindow;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import pop.hl.com.poplibrary.AlertPopView;
import pop.hl.com.poplibrary.LgRgPopView;
import pop.hl.com.poplibrary.LocationPopView;
import pop.hl.com.poplibrary.SharePopView;
import pop.hl.com.poplibrary.OnEventListenner;
import pop.hl.com.poplibrary.UpdatePopView;
import pop.hl.com.poplibrary.VListPopView;
import pop.hl.com.poplibrary.WebPopView;
import pop.hl.com.poplibrary.base.BasePop;
import pop.hl.com.poplibrary.BasePopView;
import pop.hl.com.poplibrary.bean.LoactionBean;
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
    //public native String stringFromJNI();

    private BasePop.Builder builder;
    private AlertDialog alertDialog;


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
                                switch (callback_type) {
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
                        view, LgRgPopView.LOGIN_TYPE.THIRD_ACCOUNT
                                | LgRgPopView.LOGIN_TYPE.PHONE_VERICODE,
                        "#f0008DCF",
                        new OnEventListenner.OnLRClickListenner() {
                            @Override
                            public void onClick(View view, String[] parmas, LgRgPopView.CALLBACK_TYPE callback_type) {
                                switch (callback_type) {
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
                        R.drawable.update_bg_app_top, 204.0f / 450.0f,
                        "#FF5C5C", (new Random().nextInt(2)) == 1 ? true : false,
                        "1、新增皮皮虾板块\n" + "2、新增皮皮狗板块\n" + "3、新增皮皮你板块",
                        new OnEventListenner.OnUpdateClickListenner() {
                            @Override
                            public void onClick(View view, View progress) {
                                ProgressBar progressBar = (ProgressBar) progress;
                                progressBar.setProgress(80);
                                builder.dissmiss();
                            }
                        });
                break;
            case R.id.am_vlist_btn:
                List<String> vDataList = new ArrayList<>();
                vDataList.add("别急呀！");
                vDataList.add("别急呀！");
                vDataList.add("别急呀！");
                vDataList.add("别急呀！");
                vDataList.add("别急呀！");
                vDataList.add("别急呀！");
                vDataList.add("别急呀！");
                vDataList.add("别急呀！");
                vDataList.add("别急呀！");
                vDataList.add("END！");
                builder = new VListPopView.Builder(this)
                        .create(view, ScreenUtil.getScreenH(this) / 8)
                        .show(vDataList, new OnEventListenner.OnVListClickListenner() {
                            @Override
                            public void onClick(View view, int pos) {

                            }
                        });
                break;
            case R.id.am_alertA_btn:
                builder = AlertPopView.showALertTypeA(this, view,
                        "提示", "是否确认删除!", "#f0008DCF",
                        true, new OnEventListenner.OnAlertClickListenner() {
                            @Override
                            public void onClick(View view, AlertPopView.CALLBACK_TYPE callback_type) {

                            }
                        });
                break;
            case R.id.am_alert_btn:
                ///< 颜色可以不设置，默认系统颜色
                alertDialog = AlertPopView.showOriginAlert(this,
                        "升级啦！", "1.我是皮皮虾\n2.我是大皮皮虾\n2.xx银行就是这样的弹窗，蛮清爽的.",
                        "取消", "确定",
                        "#f0008DCF", false,
                        new OnEventListenner.OnAlertClickListenner() {
                            @Override
                            public void onClick(View view, AlertPopView.CALLBACK_TYPE callback_type) {

                            }
                        });
                ///< 可以nagative、postive，那样则不显示确定取消按钮
                alertDialog = AlertPopView.showOriginAlert(this,
                        null, null,
                        null, null,
                        null, true,
                        new OnEventListenner.OnAlertClickListenner() {
                            @Override
                            public void onClick(View view, AlertPopView.CALLBACK_TYPE callback_type) {

                            }
                        });
                break;
            case R.id.am_location_btn:
                List<String> _provinceList = new ArrayList<>();
                _provinceList.add("");
                _provinceList.add("");
                _provinceList.add("月华乡");
                _provinceList.add("成都");
                _provinceList.add("北京");
                _provinceList.add("深证");
                _provinceList.add("上海");
                _provinceList.add("香港");
                _provinceList.add("");
                _provinceList.add("");

                HashMap<String, List<String>> _cityList = new HashMap<>();
                List<String> yuehuaList = new ArrayList<>();
                yuehuaList.add("");
                yuehuaList.add("");
                yuehuaList.add("富裕村");
                yuehuaList.add("三家村");
                yuehuaList.add("红旗村");
                yuehuaList.add("");
                yuehuaList.add("");
                List<String> yuehuaList2 = new ArrayList<>();
                yuehuaList2.add("");
                yuehuaList2.add("");
                yuehuaList2.add("富裕村2");
                yuehuaList2.add("三家村2");
                yuehuaList2.add("红旗村2");
                yuehuaList2.add("");
                yuehuaList2.add("");
                List<String> yuehuaList3 = new ArrayList<>();
                yuehuaList3.add("");
                yuehuaList3.add("");
                yuehuaList3.add("富裕村3");
                yuehuaList3.add("三家村3");
                yuehuaList3.add("红旗村3");
                yuehuaList3.add("");
                yuehuaList3.add("");
                List<String> yuehuaList4 = new ArrayList<>();
                yuehuaList4.add("");
                yuehuaList4.add("");
                yuehuaList4.add("富裕村4");
                yuehuaList4.add("三家村4");
                yuehuaList4.add("红旗村4");
                yuehuaList4.add("");
                yuehuaList4.add("");
                List<String> yuehuaList5 = new ArrayList<>();
                yuehuaList5.add("");
                yuehuaList5.add("");
                yuehuaList5.add("富裕村5");
                yuehuaList5.add("三家村5");
                yuehuaList5.add("红旗村5");
                yuehuaList5.add("");
                yuehuaList5.add("");
                List<String> yuehuaList6 = new ArrayList<>();
                yuehuaList6.add("");
                yuehuaList6.add("");
                yuehuaList6.add("富裕村6");
                yuehuaList6.add("三家村6");
                yuehuaList6.add("红旗村6");
                yuehuaList6.add("");
                yuehuaList6.add("");

                _cityList.put("月华乡", yuehuaList);
                _cityList.put("成都", yuehuaList2);
                _cityList.put("北京", yuehuaList3);
                _cityList.put("深证", yuehuaList4);
                _cityList.put("上海", yuehuaList5);
                _cityList.put("香港", yuehuaList6);

                HashMap<String, List<String>> _districtList = new HashMap<>();
                List<String> zuList = new ArrayList<>();
                zuList.add("");
                zuList.add("");
                zuList.add("7队");
                zuList.add("2队");
                zuList.add("1队");
                zuList.add("");
                zuList.add("");
                List<String> zuList2 = new ArrayList<>();
                zuList2.add("");
                zuList2.add("");
                zuList2.add("7队2");
                zuList2.add("2队2");
                zuList2.add("1队2");
                zuList2.add("");
                zuList2.add("");

                _districtList.put("富裕村", zuList);
                _districtList.put("三家村", zuList2);
                _districtList.put("红旗村", zuList);

                _districtList.put("富裕村2", zuList);
                _districtList.put("三家村2", zuList);
                _districtList.put("红旗村2", zuList);

                _districtList.put("富裕村3", zuList);
                _districtList.put("三家村3", zuList);
                _districtList.put("红旗村3", zuList);

                _districtList.put("富裕村4", zuList);
                _districtList.put("三家村4", zuList);
                _districtList.put("红旗村4", zuList);

                _districtList.put("富裕村5", zuList);
                _districtList.put("三家村5", zuList);
                _districtList.put("红旗村5", zuList);

                _districtList.put("富裕村6", zuList);
                _districtList.put("三家村6", zuList);
                _districtList.put("红旗村6", zuList);

                ///< 采用自己组装数组列表的形式(前后记得增加空项目)
                //                builder = LocationPopView.show(this, view,
                //                        _provinceList, _cityList, _districtList,
                //                        new OnEventListenner.OnLocationClickListenner() {
                //
                //                            @Override
                //                            public void onClick(View view, String[] locations) {
                //                                Toast.makeText(MainActivity.this,
                //                                        locations[0] + "-" + locations[1] + "-" + locations[2],
                //                                        Toast.LENGTH_SHORT).show();
                //                            }
                //                        });

                ///< json转对象列表的方式加载-以下是手动添加模拟数据，请用json转换；手动很麻烦.
                List<LoactionBean> loactionBeanList = new ArrayList<>();

                ///< 省
                LoactionBeanChild loactionBean = new LoactionBeanChild();
                loactionBean.setName("四川省");

                ///< 市区
                List<LoactionBeanChild.CityBeanChild> cityBeanList = new ArrayList<>();
                LoactionBeanChild.CityBeanChild cityBean = new LoactionBeanChild.CityBeanChild();
                cityBean.setName("凉山州");
                LoactionBeanChild.CityBeanChild cityBean2 = new LoactionBeanChild.CityBeanChild();
                cityBean2.setName("成都市");
                cityBeanList.add(cityBean);
                cityBeanList.add(cityBean2);

                loactionBean.setCity(cityBeanList);

                ///< 区
                List<String> areaList = new ArrayList<>();
                areaList.add("西昌市");
                areaList.add("冕宁县");
                areaList.add("喜德县");
                cityBean.setArea(areaList);
                List<String> areaList2 = new ArrayList<>();
                areaList2.add("双流区");
                areaList2.add("成华区");
                areaList2.add("锦江区");
                cityBean2.setArea(areaList2);

                ///< 省
                LoactionBeanChild loactionBean2 = new LoactionBeanChild();
                loactionBean2.setName("贵州省");

                ///< 市区
                List<LoactionBeanChild.CityBeanChild> cityBeanList2 = new ArrayList<>();
                LoactionBeanChild.CityBeanChild cityBean21 = new LoactionBeanChild.CityBeanChild();
                cityBean21.setName("黔南");
                LoactionBeanChild.CityBeanChild cityBean22 = new LoactionBeanChild.CityBeanChild();
                cityBean22.setName("黔北");
                cityBeanList2.add(cityBean21);
                cityBeanList2.add(cityBean22);

                loactionBean2.setCity(cityBeanList2);

                ///< 区
                List<String> areaList21 = new ArrayList<>();
                areaList21.add("花溪区");
                areaList21.add("花溪区2");
                areaList21.add("花溪区3");
                cityBean21.setArea(areaList21);
                List<String> areaList22 = new ArrayList<>();
                areaList22.add("花溪区4");
                areaList22.add("花溪区5");
                areaList22.add("花溪区6");
                cityBean22.setArea(areaList22);

                ///< 装载
                loactionBeanList.add(loactionBean);
                loactionBeanList.add(loactionBean2);

                ///< 采用手动构造地区列表的形式
                //                builder = LocationPopView.show(this, view,
                //                        /*loactionBeanList*/loactionBeanList, new OnEventListenner.OnLocationClickListenner() {
                //                            @Override
                //                            public void onClick(View view, String[] locations) {
                //                                Toast.makeText(MainActivity.this,
                //                                        locations[0] + "-" + locations[1] + "-" + locations[2],
                //                                        Toast.LENGTH_SHORT).show();
                //                            }
                //                        });

                ///< 转换json方式
                String locationJson = "[\n" +
                        "  {\n" +
                        "    \"name\": \"北京市\",\n" +
                        "    \"city\": [\n" +
                        "      {\n" +
                        "        \"name\": \"北京市\",\n" +
                        "        \"area\": [\n" +
                        "          \"东城区\",\n" +
                        "          \"西城区\",\n" +
                        "          \"崇文区\",\n" +
                        "          \"宣武区\",\n" +
                        "          \"朝阳区\",\n" +
                        "          \"丰台区\",\n" +
                        "          \"石景山区\",\n" +
                        "          \"海淀区\",\n" +
                        "          \"门头沟区\",\n" +
                        "          \"房山区\",\n" +
                        "          \"通州区\",\n" +
                        "          \"顺义区\",\n" +
                        "          \"昌平区\",\n" +
                        "          \"大兴区\",\n" +
                        "          \"平谷区\",\n" +
                        "          \"怀柔区\",\n" +
                        "          \"密云县\",\n" +
                        "          \"延庆县\"\n" +
                        "        ]\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"name\": \"天津市\",\n" +
                        "    \"city\": [\n" +
                        "      {\n" +
                        "        \"name\": \"天津市\",\n" +
                        "        \"area\": [\n" +
                        "          \"和平区\",\n" +
                        "          \"河东区\",\n" +
                        "          \"河西区\",\n" +
                        "          \"南开区\",\n" +
                        "          \"河北区\",\n" +
                        "          \"红桥区\",\n" +
                        "          \"塘沽区\",\n" +
                        "          \"汉沽区\",\n" +
                        "          \"大港区\",\n" +
                        "          \"东丽区\",\n" +
                        "          \"西青区\",\n" +
                        "          \"津南区\",\n" +
                        "          \"北辰区\",\n" +
                        "          \"武清区\",\n" +
                        "          \"宝坻区\",\n" +
                        "          \"宁河县\",\n" +
                        "          \"静海县\",\n" +
                        "          \"蓟  县\"\n" +
                        "        ]\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"name\": \"河北省\",\n" +
                        "    \"city\": [\n" +
                        "      {\n" +
                        "        \"name\": \"石家庄市\",\n" +
                        "        \"area\": [\n" +
                        "          \"长安区\",\n" +
                        "          \"桥东区\",\n" +
                        "          \"桥西区\",\n" +
                        "          \"新华区\",\n" +
                        "          \"郊  区\",\n" +
                        "          \"井陉矿区\",\n" +
                        "          \"井陉县\",\n" +
                        "          \"正定县\",\n" +
                        "          \"栾城县\",\n" +
                        "          \"行唐县\",\n" +
                        "          \"灵寿县\",\n" +
                        "          \"高邑县\",\n" +
                        "          \"深泽县\",\n" +
                        "          \"赞皇县\",\n" +
                        "          \"无极县\",\n" +
                        "          \"平山县\",\n" +
                        "          \"元氏县\",\n" +
                        "          \"赵  县\",\n" +
                        "          \"辛集市\",\n" +
                        "          \"藁\",\n" +
                        "          \"晋州市\",\n" +
                        "          \"新乐市\",\n" +
                        "          \"鹿泉市\"\n" +
                        "        ]\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"name\": \"唐山市\",\n" +
                        "        \"area\": [\n" +
                        "          \"路南区\",\n" +
                        "          \"路北区\",\n" +
                        "          \"古冶区\",\n" +
                        "          \"开平区\",\n" +
                        "          \"新  区\",\n" +
                        "          \"丰润县\",\n" +
                        "          \"滦  县\",\n" +
                        "          \"滦南县\",\n" +
                        "          \"乐亭县\",\n" +
                        "          \"迁西县\",\n" +
                        "          \"玉田县\",\n" +
                        "          \"唐海县\",\n" +
                        "          \"遵化市\",\n" +
                        "          \"丰南市\",\n" +
                        "          \"迁安市\"\n" +
                        "        ]\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"name\": \"秦皇岛市\",\n" +
                        "        \"area\": [\n" +
                        "          \"海港区\",\n" +
                        "          \"山海关区\",\n" +
                        "          \"北戴河区\",\n" +
                        "          \"青龙满族自治县\",\n" +
                        "          \"昌黎县\",\n" +
                        "          \"抚宁县\",\n" +
                        "          \"卢龙县\"\n" +
                        "        ]\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"name\": \"邯郸市\",\n" +
                        "        \"area\": [\n" +
                        "          \"邯山区\",\n" +
                        "          \"丛台区\",\n" +
                        "          \"复兴区\",\n" +
                        "          \"峰峰矿区\",\n" +
                        "          \"邯郸县\",\n" +
                        "          \"临漳县\",\n" +
                        "          \"成安县\",\n" +
                        "          \"大名县\",\n" +
                        "          \"涉  县\",\n" +
                        "          \"磁  县\",\n" +
                        "          \"肥乡县\",\n" +
                        "          \"永年县\",\n" +
                        "          \"邱  县\",\n" +
                        "          \"鸡泽县\",\n" +
                        "          \"广平县\",\n" +
                        "          \"馆陶县\",\n" +
                        "          \"魏  县\",\n" +
                        "          \"曲周县\",\n" +
                        "          \"武安市\"\n" +
                        "        ]\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"name\": \"邢台市\",\n" +
                        "        \"area\": [\n" +
                        "          \"桥东区\",\n" +
                        "          \"桥西区\",\n" +
                        "          \"邢台县\",\n" +
                        "          \"临城县\",\n" +
                        "          \"内丘县\",\n" +
                        "          \"柏乡县\",\n" +
                        "          \"隆尧县\",\n" +
                        "          \"任  县\",\n" +
                        "          \"南和县\",\n" +
                        "          \"宁晋县\",\n" +
                        "          \"巨鹿县\",\n" +
                        "          \"新河县\",\n" +
                        "          \"广宗县\",\n" +
                        "          \"平乡县\",\n" +
                        "          \"威  县\",\n" +
                        "          \"清河县\",\n" +
                        "          \"临西县\",\n" +
                        "          \"南宫市\",\n" +
                        "          \"沙河市\"\n" +
                        "        ]\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"name\": \"保定市\",\n" +
                        "        \"area\": [\n" +
                        "          \"新市区\",\n" +
                        "          \"北市区\",\n" +
                        "          \"南市区\",\n" +
                        "          \"满城县\",\n" +
                        "          \"清苑县\",\n" +
                        "          \"涞水县\",\n" +
                        "          \"阜平县\",\n" +
                        "          \"徐水县\",\n" +
                        "          \"定兴县\",\n" +
                        "          \"唐  县\",\n" +
                        "          \"高阳县\",\n" +
                        "          \"容城县\",\n" +
                        "          \"涞源县\",\n" +
                        "          \"望都县\",\n" +
                        "          \"安新县\",\n" +
                        "          \"易  县\",\n" +
                        "          \"曲阳县\",\n" +
                        "          \"蠡  县\",\n" +
                        "          \"顺平县\",\n" +
                        "          \"博野\",\n" +
                        "          \"雄县\",\n" +
                        "          \"涿州市\",\n" +
                        "          \"定州市\",\n" +
                        "          \"安国市\",\n" +
                        "          \"高碑店市\"\n" +
                        "        ]\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"name\": \"张家口\",\n" +
                        "        \"area\": [\n" +
                        "          \"桥东区\",\n" +
                        "          \"桥西区\",\n" +
                        "          \"宣化区\",\n" +
                        "          \"下花园区\",\n" +
                        "          \"宣化县\",\n" +
                        "          \"张北县\",\n" +
                        "          \"康保县\",\n" +
                        "          \"沽源县\",\n" +
                        "          \"尚义县\",\n" +
                        "          \"蔚  县\",\n" +
                        "          \"阳原县\",\n" +
                        "          \"怀安县\",\n" +
                        "          \"万全县\",\n" +
                        "          \"怀来县\",\n" +
                        "          \"涿鹿县\",\n" +
                        "          \"赤城县\",\n" +
                        "          \"崇礼县\"\n" +
                        "        ]\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"name\": \"承德市\",\n" +
                        "        \"area\": [\n" +
                        "          \"双桥区\",\n" +
                        "          \"双滦区\",\n" +
                        "          \"鹰手营子矿区\",\n" +
                        "          \"承德县\",\n" +
                        "          \"兴隆县\",\n" +
                        "          \"平泉县\",\n" +
                        "          \"滦平县\",\n" +
                        "          \"隆化县\",\n" +
                        "          \"丰宁满族自治县\",\n" +
                        "          \"宽城满族自治县\",\n" +
                        "          \"围场满族蒙古族自治县\"\n" +
                        "        ]\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"name\": \"沧州市\",\n" +
                        "        \"area\": [\n" +
                        "          \"新华区\",\n" +
                        "          \"运河区\",\n" +
                        "          \"沧  县\",\n" +
                        "          \"青  县\",\n" +
                        "          \"东光县\",\n" +
                        "          \"海兴县\",\n" +
                        "          \"盐山县\",\n" +
                        "          \"肃宁县\",\n" +
                        "          \"南皮县\",\n" +
                        "          \"吴桥县\",\n" +
                        "          \"献  县\",\n" +
                        "          \"孟村回族自治县\",\n" +
                        "          \"泊头市\",\n" +
                        "          \"任丘市\",\n" +
                        "          \"黄骅市\",\n" +
                        "          \"河间市\"\n" +
                        "        ]\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"name\": \"廊坊市\",\n" +
                        "        \"area\": [\n" +
                        "          \"安次区\",\n" +
                        "          \"固安县\",\n" +
                        "          \"永清县\",\n" +
                        "          \"香河县\",\n" +
                        "          \"大城县\",\n" +
                        "          \"文安县\",\n" +
                        "          \"大厂回族自治县\",\n" +
                        "          \"霸州市\",\n" +
                        "          \"三河市\"\n" +
                        "        ]\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"name\": \"衡水市\",\n" +
                        "        \"area\": [\n" +
                        "          \"桃城区\",\n" +
                        "          \"枣强县\",\n" +
                        "          \"武邑县\",\n" +
                        "          \"武强县\",\n" +
                        "          \"饶阳县\",\n" +
                        "          \"安平县\",\n" +
                        "          \"故城县\",\n" +
                        "          \"景  县\",\n" +
                        "          \"阜城县\",\n" +
                        "          \"冀州市\",\n" +
                        "          \"深州市\"\n" +
                        "        ]\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  }\n" +
                        "]";
                ///< 记得引入Gson...
                Gson gson1 = new Gson();
                List<LoactionBean> loactionBeanListJson = gson1.fromJson(locationJson,
                        new TypeToken<List<LoactionBeanChild>>() {
                        }.getType());

                ///< 从底部弹出 - 采用json转换的方式
                builder = LocationPopView.showFromBottom(this, view,
                        /*loactionBeanList*/loactionBeanListJson, new OnEventListenner.OnLocationClickListenner() {
                            @Override
                            public void onClick(View view, String[] locations) {
                                Toast.makeText(MainActivity.this,
                                        locations[0] + "-" + locations[1] + "-" + locations[2],
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
            case R.id.am_webview_btn:
                builder = WebPopView.showWebview(this, "<p style=\"white-space: normal;\"><span style=\"font-family: 宋体; font-size: 14px;\">1、独立根据客户需求完成媒体合作及投放方案；</span></p><p style=\"white-space: normal;\"><span style=\"font-family: 宋体; font-size: 14px;\">2、负责对区域客户的销售工作，拓展新的客户和项目，并有效地形成销售机会；</span></p><p style=\"white-space: normal;\"><span style=\"font-family: 宋体; font-size: 14px;\">3、通过深入了解客户的核心业务，挖掘和寻找潜在的项目机会；</span>");
                builder = WebPopView.showWebview(this, "<div class=\"layui-m-layer-layui-layer-jd\">\r\n    <div class=\"layui-layer-jd-container content\">\r\n        <div class=\"layui-layer-jd-section\">\r\n            <div class=\"layui-layer-jd-title\">岗位职责：</div>\r\n            <div class=\"layui-layer-jd-content\">\r\n                 <p><p>岗位职责<br/></p></p>\r\n            </div>\r\n        </div>\r\n        <div class=\"layui-layer-jd-section\">\r\n            <div class=\"layui-layer-jd-title\">任职要求：</div>\r\n            <div class=\"layui-layer-jd-content\">\r\n                 <p><p>任职要求</p></p>\r\n            </div>\r\n        </div>\r\n    </div>\r\n</div>\r\n", R.drawable.huawei);
                builder = WebPopView.showWebview(this, view, "<p style=\"white-space: normal;\"><span style=\"font-family: 宋体; font-size: 14px;\">1、独立根据客户需求完成媒体合作及投放方案；</span></p><p style=\"white-space: normal;\"><span style=\"font-family: 宋体; font-size: 14px;\">2、负责对区域客户的销售工作，拓展新的客户和项目，并有效地形成销售机会；</span></p><p style=\"white-space: normal;\"><span style=\"font-family: 宋体; font-size: 14px;\">3、通过深入了解客户的核心业务，挖掘和寻找潜在的项目机会；</span>",
                        -1, true);
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return false;
    }
}
