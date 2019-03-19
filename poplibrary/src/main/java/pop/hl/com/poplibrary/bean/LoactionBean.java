package pop.hl.com.poplibrary.bean;

import java.util.List;

/*
*@Description: 省、市、区列表对象
*@Author: hl
*@Time: 2019/3/18 12:08
*/
public abstract class LoactionBean {
    /**
     * name : 北京市
     * city : [{"name":"北京市","area":["东城区","西城区","崇文区","宣武区","朝阳区","丰台区","石景山区","海淀区","门头沟区","房山区","通州区","顺义区","昌平区","大兴区","平谷区","怀柔区","密云县","延庆县"]}]
     */

//    private String provinceName;
//    private List<CityBean> cityList;

//    public LoactionBean(){}
    public abstract String getName();

//    public void setName(String provinceName) {
//        this.provinceName = provinceName;
//    }

    public abstract List<CityBean> getCity();

//    public void setCity(List<CityBean> cityList) {
//        this.cityList = cityList;
//    }

    public abstract static class CityBean {
        /**
         * name : 北京市
         * area : ["东城区","西城区","崇文区","宣武区","朝阳区","丰台区","石景山区","海淀区","门头沟区","房山区","通州区","顺义区","昌平区","大兴区","平谷区","怀柔区","密云县","延庆县"]
         */

//        private String cityName;
//        private List<String> areaList;

//        public CityBean(){}
        public abstract String getName();

//        public void setName(String cityName) {
//            this.cityName = cityName;
//        }

        public abstract List<String> getArea();

//        public void setArea(List<String> area) {
//            this.areaList = area;
//        }
    }
}
