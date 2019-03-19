package pop.hl.com.commonpopupwindow;

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
