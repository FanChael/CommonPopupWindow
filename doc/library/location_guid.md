# API说明-提供调用类LocationPopView.java
>调用显示方法
```Java
/**
     * 地区弹窗列表显示 - 默认高度是屏幕高度的1/7 - Achor控件之下弹出
     *
     * @param _context
     * @param _anchor
     * @param _provinceData - 省列表
     * @param _cityList - 省对应的城市列表
     * @param _districtList - 城市对应的地区列表
     * @param _onLocationClickListenner
     * @return
     */
    public static BasePop.Builder show(Context _context,
                                       View _anchor,
                                       List<String> _provinceData,
                                       HashMap<String, List<String>> _cityList,
                                       HashMap<String, List<String>> _districtList,
                                       OnEventListenner.OnLocationClickListenner _onLocationClickListenner) {

    }

    /**
     * 地区弹窗列表显示 - Achor控件之下弹出
     *
     * @param _provinceData  - 省列表
     * @param _cityList - 省对应的城市列表
     * @param _districtList - 城市对应的地区列表
     * @param _builderH                 - 弹窗的高度【弹窗高度的1/3作为item的高度】
     * @param _onLocationClickListenner
     * @return
     */
    public static BasePop.Builder show(Context _context,
                                       View _anchor,
                                       List<String> _provinceData,
                                       HashMap<String, List<String>> _cityList,
                                       HashMap<String, List<String>> _districtList,
                                       int _builderH,
                                       OnEventListenner.OnLocationClickListenner _onLocationClickListenner) {

    }

    /**
     * 地区弹窗列表显示 - 默认高度是屏幕高度的1/7 - Achor控件之下弹出
     *
     * @param _context
     * @param _anchor
     * @param _loactionBeanList  - 城市地区列表对象
     * @param _onLocationClickListenner
     * @return
     */
    public static BasePop.Builder show(Context _context,
                                       View _anchor,
                                       List<LoactionBean> _loactionBeanList,
                                       OnEventListenner.OnLocationClickListenner _onLocationClickListenner) {
    
    }

    /**
     * 弹窗的高度【弹窗高度的1/3作为item的高度】 - Achor控件之下弹出
     *
     * @param _context
     * @param _anchor
     * @param _loactionBeanList  - 城市地区列表对象
     * @param _builderH - 弹窗高度自定义
     * @param _onLocationClickListenner
     * @return
     */
    public static BasePop.Builder show(Context _context,
                                       View _anchor,
                                       List<LoactionBean> _loactionBeanList,
                                       int _builderH,
                                       OnEventListenner.OnLocationClickListenner _onLocationClickListenner) {

    }

    /**
     * 地区弹窗列表显示 - 默认高度是屏幕高度的1/7 - 从底部弹出
     *
     * @param _context
     * @param _anchor
     * @param _loactionBeanList - 城市地区列表对象
     * @param _onLocationClickListenner
     * @return
     */
    public static BasePop.Builder showFromBottom(Context _context,
                                                 View _anchor,
                                                 List<LoactionBean> _loactionBeanList,
                                                 OnEventListenner.OnLocationClickListenner _onLocationClickListenner) {

    }

    /**
     * 弹窗的高度【弹窗高度的1/3作为item的高度】 - 从底部弹出
     *
     * @param _context
     * @param _anchor
     * @param _loactionBeanList - 城市地区列表对象
     * @param _builderH - 弹窗高度自定义
     * @param _onLocationClickListenner
     * @return
     */
    public static BasePop.Builder showFromBottom(Context _context,
                                                 View _anchor,
                                                 List<LoactionBean> _loactionBeanList,
                                                 int _builderH,
                                                 OnEventListenner.OnLocationClickListenner _onLocationClickListenner) {

    }
```
# USE 
> 为了兼容不同的地区实体类对象参数，需要继承实现抽象类LoactionBean，并实现对应的方法
```Java
import java.util.ArrayList;
import java.util.List;

import pop.hl.com.poplibrary.bean.LoactionBean;

/*
*@Description: 实现LoactionBean对象
*@Author: hl
*@Time: 2019/3/18 19:09
*/
public class LoactionBeanChild extends LoactionBean {
    private String name;
    private List<CityBeanChild> city;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setCity(List<CityBeanChild> city) {
        this.city = city;
    }

    @Override
    public List<CityBean> getCity() {
        List<CityBean> cityBeanList = new ArrayList<>();
        for (int i = 0; i < city.size(); ++i){
            cityBeanList.add(city.get(i));
        }
        return cityBeanList;
    }

    public static class CityBeanChild extends CityBean{
        private String name;
        private List<String> area;

        public void setName(String name) {
            this.name = name;
        }

        public void setArea(List<String> area) {
            this.area = area;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<String> getArea() {
            return area;
        }
    }
}

```
> go...
```Java
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
                BasePop.Builder builder1 = LocationPopView.show(this, view,
                        _provinceList, _cityList, _districtList,
                        new OnEventListenner.OnLocationClickListenner() {

                            @Override
                            public void onClick(View view, String[] locations) {
                                Toast.makeText(MainActivity.this,
                                        locations[0] + "-" + locations[1] + "-" + locations[2],
                                        Toast.LENGTH_SHORT).show();
                            }
                        });

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
                BasePop.Builder builder2 = LocationPopView.show(this, view,
                        /*loactionBeanList*/loactionBeanList, new OnEventListenner.OnLocationClickListenner() {
                            @Override
                            public void onClick(View view, String[] locations) {
                                Toast.makeText(MainActivity.this,
                                        locations[0] + "-" + locations[1] + "-" + locations[2],
                                        Toast.LENGTH_SHORT).show();
                            }
                        });

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
                        new TypeToken<List<LoactionBeanChild>>() {}.getType());

                ///< 从底部弹出 - 采用json转换的方式
                BasePop.Builder builder3 = LocationPopView.showFromBottom(this, view,
                        /*loactionBeanList*/loactionBeanListJson, new OnEventListenner.OnLocationClickListenner() {
                            @Override
                            public void onClick(View view, String[] locations) {
                                Toast.makeText(MainActivity.this,
                                        locations[0] + "-" + locations[1] + "-" + locations[2],
                                        Toast.LENGTH_SHORT).show();
                            }
                        });                
```
# 混淆注意
```java
-keep class pop.hl.com.commonpopupwindow.LoactionBeanChild{ *;}
// 内部类需要做混淆
-keepattributes Exceptions,InnerClasses,...
-keep class pop.hl.com.commonpopupwindow.LoactionBeanChild$* {
    *;
}
```

# 注意事项
> 推荐使用Json城市数据转对象列表的方式
> 如果要自定义对象的话，请继承抽象类LoactionBean并实现相关的get方法

> 列表的高度是根据弹窗高度进行的均分，同时字体也是进行了相应的缩放；所以弹窗高度设置需要注意  

> 目前提供两种显示方式，基于控件之下显示+从页面底部弹出  
  
> 没有特别的样式  

> 后续可以做样式和显示以及其他效果的优化处理  
