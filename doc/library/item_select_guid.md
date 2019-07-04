# API说明-提供调用类ItemSelectPopView.java
>调用显示方法
```Java
/**
     * 显示单项选择的弹窗
     * @param _context
     * @param _title  - 抬头标题
     * @param _bottomTip - 底部提示
     * @param _itemList - 单项列表选项
     * @param _onItemSelectClickListenner
     * @return
     */
    public static BasePop.Builder showSelectPopView(Context _context, String _title, String _bottomTip, List<String> _itemList, OnEventListenner.OnItemSelectClickListenner _onItemSelectClickListenner) {
    }

    /**
     * 显示单项选择的弹窗
     * @param _context
     * @param _achor
     * @param _title - 抬头标题
     * @param _bottomTip - 底部提示
     * @param _itemList - 单项列表选项
     * @param _titleBgId - 抬头背景图片 - 左上角，右上角，需要圆角处理
     * @param _fillColor - 选项背景颜色
     * @param _fontColor - 选项字体颜色
     * @param _closeResourceId - 关闭按钮资源背景图片
     * @param _bScale - 是否显示缩放动画
     * @param _onItemSelectClickListenner
     * @return
     */
    public static BasePop.Builder showSelectPopView(Context _context, View _achor,
                                                    String _title, String _bottomTip, List<String> _itemList,
                                                    int _titleBgId, String _fillColor, String _fontColor,
                                                    int _closeResourceId, boolean _bScale, OnEventListenner.OnItemSelectClickListenner _onItemSelectClickListenner) {
    }
```
# USE 
```Java
                List<String> cityList = new ArrayList<>();
                                cityList.add("北京");
                                cityList.add("成都");
                                cityList.add("北京");
                                cityList.add("成都");
                                cityList.add("北京");
                                ///< 列表可以给null，此时该列表返回数据就是""空字符串
                                ///< 不带抬头图片
                                //  BasePop.Builder builder = ItemSelectPopView.showSelectPopView(this, view,
                                //                        "请您选择所在的城市", "更多城市持续开通中", cityList,
                                //                        -1, "#FFf5c5c0", "#000000",
                                //                         R.drawable.profile_icon_close_n, true,  new OnEventListenner.OnItemSelectClickListenner() {
                                //                            @Override
                                //                            public void onClick(View view, String item, int pos) {
                                //                                builder.dissmiss();
                                //                                Toast.makeText(MainActivity.this,
                                //                                        item, Toast.LENGTH_SHORT).show();
                                //                            }
                                //                        });
                                BasePop.Builder builder = ItemSelectPopView.showSelectPopView(this, view,
                                        "请您选择所在的城市", "更多城市持续开通中", cityList,
                                        R.drawable.update_img, "#FFf5c5c0", "#000000",
                                        R.drawable.profile_icon_close_n, true,  new OnEventListenner.OnItemSelectClickListenner() {
                                            @Override
                                            public void onClick(View view, String item, int pos) {
                                                builder.dissmiss();
                                                Toast.makeText(MainActivity.this,
                                                        item, Toast.LENGTH_SHORT).show();
                                            }
                                        });
```
# 注意事项
> 抬头可以设置图片背景 - 注意图片左上角，右上角需要圆角处理
> 提供两个接口，一个是默认的一些颜色、关闭按钮，一个是提供全部自定义，垂直滑动条默认样式显示; 
> 未提供大小样式定义，那样感觉麻烦，需要可以进行扩展;