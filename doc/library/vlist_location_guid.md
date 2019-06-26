# API说明-提供调用类VListSelectPopView.java
>调用显示方法
```Java
/**
     * 地区弹窗列表显示 - 默认高度是屏幕高度的1/7 - 从底部弹出
     *
     * @param _context
     * @param _anchor
     * @param _dataLeft - 左侧列表数据
     * @param _dataList - 列表数据
     * @param _dataRight - 右侧列表数据
     * @param _titleBgColor - 标题背景颜色
     * @param _titleColor - 标题字体颜色
     * @param _onLocationClickListenner
     * @return
     */
    public static BasePop.Builder showFromBottom(Context _context,
                                                 View _anchor,
                                                 List<String> _dataLeft,
                                                 List<String> _dataList,
                                                 List<String> _dataRight,
                                                 String _titleBgColor, String _titleColor,
                                                 OnEventListenner.OnLocationClickListenner _onLocationClickListenner) {

    }

    /**
     * 弹窗的高度【弹窗高度的1/3作为item的高度】 - 从底部弹出
     *
     * @param _context
     * @param _anchor
     * @param _dataLeft - 左侧列表数据
     * @param _dataList - 列表数据
     * @param _dataRight - 右侧列表数据
     * @param _titleBgColor - 标题背景颜色
     * @param _titleColor - 标题字体颜色
     * @param _builderH - 弹窗高度自定义
     * @param _onLocationClickListenner
     * @return
     */
    public static BasePop.Builder showFromBottom(Context _context,
                                                 View _anchor,
                                                 List<String> _dataLeft,
                                                 List<String> _dataList,
                                                 List<String> _dataRight,
                                                 int _builderH,
                                                 String _titleBgColor, String _titleColor,
                                                 OnEventListenner.OnLocationClickListenner _onLocationClickListenner) {
      
    }
```
# USE 
```Java
                List<String> areanList = new ArrayList<>();
                areanList.add("密云区");
                areanList.add("双流区");
                areanList.add("锦江区");
                areanList.add("月华乡");
                ///< 列表可以给null，此时该列表返回数据就是""空字符串
                BasePop.Builder builder = VListSelectPopView.showFromBottom(this, view,
                        areanList, areanList, areanList, "#FFf5c5c0", "#000000", new OnEventListenner.OnLocationClickListenner() {
                            @Override
                            public void onClick(View view, String[] locations) {
                                Toast.makeText(MainActivity.this,
                                        locations[0] + locations[1] + locations[2],
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
```