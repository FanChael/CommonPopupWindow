# CommonPopupWindow
Popular popupwindow in the market and customized.  
[ ![Download](https://api.bintray.com/packages/resetmyself/holdon/commonpop/images/download.svg?version=1.1.1) ](https://bintray.com/resetmyself/holdon/commonpop/1.1.1/link)
[ ![Download](https://api.bintray.com/packages/resetmyself/holdon/commonpop/images/download.svg?version=1.14.0) ](https://bintray.com/resetmyself/holdon/commonpop/1.14.0/link)
[ ![Download](https://api.bintray.com/packages/resetmyself/holdon/commonpop/images/download.svg?version=2.0.1) ](https://bintray.com/resetmyself/holdon/commonpop/2.0.1/link)
[ ![Download](https://api.bintray.com/packages/resetmyself/holdon/commonpop/images/download.svg?version=2.0.2) ](https://bintray.com/resetmyself/holdon/commonpop/2.0.2/link)   

（结合市面流行的弹窗样式+支持自定义布局）.  
# Description
>poplibrary引入即可调用弹窗

>(其他配置待续)

>目前集成库后apk大小约增加了200KB(默认分享图片资源总大小约100KB)

# Feature  

>支持自定义布局(所有点击事件统一回调)  

>底部分享弹窗-结合市面流行样式(目前两种主流样式，如需增加请issue)

>注册登录弹窗基本样式(登录可以设置显示多种组合样式)

>更新弹窗(目前经典样式)

>基于更新弹窗+Rx家族的App更新模块1.14.0(只包含自定义1.0.1+更新弹窗1.0.4) (第一版完成)

>增加垂直列表弹窗(上下伸缩显示动画)

>增加警告类弹窗(目前增加经典样式A)

>增加原生警告弹窗

>增加地区选择弹窗

# Effect  
>总的效果 
  
<table border="1">
  <tr>
    <th>自定义布局</th>
    <th>分享弹窗</th>
    <th>注册登录弹窗</th>
    <th>普通更新弹窗</th>
  </tr>
  <tr>
    <td><img src="https://github.com/FanChael/CommonPopupWindow/blob/master/doc/2019.02.14_define_base_direction.gif" width="228" height="374" alt="自定义布局"/></td>
    <td><img src="https://github.com/FanChael/CommonPopupWindow/blob/master/doc/2019.02.16_add_shareboard.gif" width="228" height="374" alt="分享弹窗"/></td>
    <td><img src="https://github.com/FanChael/CommonPopupWindow/blob/master/doc/2019.02.19_add_loginregister.gif" width="228" height="374" alt="注册登录弹窗"/></td>
    <td><img src="https://github.com/FanChael/CommonPopupWindow/blob/master/doc/2019.02.20_add_normalupdate.gif" width="228" height="374" alt="普通更新弹窗"/></td>
  </tr>
</table> 

<table border="1">
  <tr>
    <th>App更新模块</th>
    <th>垂直列表弹窗</th>
    <th>警告弹窗</th>
    <th>原生警告弹窗</th>
  </tr>
  <tr>
    <td><img src="https://github.com/FanChael/CommonPopupWindow/blob/appupdate/doc/2019.02.22_updateapp.gif" width="228" height="374" alt="App更新模块"/></td>
    <td><img src="https://github.com/FanChael/CommonPopupWindow/blob/master/doc/2019.03.05_vlistpop.gif" width="228" height="374" alt="垂直列表弹窗"/></td>
    <td><img src="https://github.com/FanChael/CommonPopupWindow/blob/master/doc/2019.03.07_alertpop.gif" width="228" height="374" alt="警告弹窗"/></td>
    <td><img src="https://github.com/FanChael/CommonPopupWindow/blob/master/doc/2019.03.09_alertpop.gif" width="228" height="374" alt="原生警告弹窗"/></td>
  </tr>
</table>  

<table border="1">
  <tr>
    <th>地区选择弹窗</th>
  </tr>
  <tr>
    <td><img src="https://github.com/FanChael/CommonPopupWindow/blob/appupdate/doc/2019.03.18_locationpop.gif" width="228" height="374" alt="地区选择弹窗"/></td>
  </tr>
</table>  

##### [Demo apk下载](https://github.com/FanChael/CommonPopupWindow/blob/master/doc/app-debug.apk)

# Import Library  
>For gradle:  
```Java
//1.1.1 自定义布局(下面有API、使用说明)
implementation 'com.hl:poplibrary:1.1.1'
//1.14.0 App更新模块
implementation 'com.hl:poplibrary:1.14.0'
//2.0.1 自定义布局+分享弹窗+注册登录弹窗+更新弹窗+垂直列表弹窗+警告类弹窗A+原生弹窗
implementation 'com.hl:poplibrary:2.0.1'
//2.0.1 + 地区选择弹窗
implementation 'com.hl:poplibrary:2.0.2'
                                            
//依赖过可以忽略（分享弹窗用到了相关组件）/2.x.x都需要依赖
implementation 'com.android.support:recyclerview-v7:28.0.0'//看你版本
//依赖过可以忽略（注册登录弹窗用到了相关组件）/2.x.x都需要依赖
implementation 'com.android.support:design:28.+'//看你版本
```

>Hisotry:
```Java 
//2.0.0 自定义布局+分享弹窗+注册登录弹窗+更新弹窗+垂直列表弹窗+警告类弹窗A
implementation 'com.hl:poplibrary:2.0.0'
```
```Java 
//不再分多版本迭代更新
//1.0.2 自定义+分享弹窗
//implementation 'com.hl:poplibrary:1.0.2'
//1.0.3 自定义+分享弹窗+注册登录弹窗
//implementation 'com.hl:poplibrary:1.0.3'
//1.0.4 自定义+分享弹窗+注册登录弹窗+更新弹窗
//implementation 'com.hl:poplibrary:1.0.4'
//1.0.6.1 自定义+分享弹窗+注册登录弹窗+更新弹窗+垂直列表弹窗 + 增加了伸缩显示动画 - 下一版决定整合完善为一个版本！
//implementation 'com.hl:poplibrary:1.0.6.1'
//1.0.6.2 自定义+分享弹窗+注册登录弹窗+更新弹窗+垂直列表弹窗+警告类弹窗A
//implementation 'com.hl:poplibrary:1.0.6.3'
```
>Or in maven:  
```Java
<dependency>
    <groupId>com.hl</groupId>
    <artifactId>poplibrary</artifactId>
    <version>x.x.x</version>
    <type>pom</type>
</dependency>
```
# 传送门  
> 使用
* [分享弹窗使用](https://github.com/FanChael/CommonPopupWindow/blob/master/doc/library/share_guid.md)
* [注册登录弹窗使用](https://github.com/FanChael/CommonPopupWindow/blob/master/doc/library/login_register_guid.md)
* [更新弹窗使用](https://github.com/FanChael/CommonPopupWindow/blob/master/doc/library/updagrade_guid.md)
* [APP更新使用](https://github.com/FanChael/CommonPopupWindow/tree/appupdate)
* [垂直列表弹窗使用](https://github.com/FanChael/CommonPopupWindow/blob/master/doc/library/vlist_guid.md)
* [警告类弹窗使用](https://github.com/FanChael/CommonPopupWindow/blob/master/doc/library/alert_guid.md)
* [地区选择弹窗使用](https://github.com/FanChael/CommonPopupWindow/blob/master/doc/library/location_guid.md)

> Hold on！
* [更新日志](https://github.com/FanChael/CommonPopupWindow/blob/master/doc/library/update_guid.md)
* [学习博客](https://github.com/FanChael/CommonPopupWindow/blob/master/doc/library/study_guid.md)
* [指教交流QQ群: 752871516](https://github.com/FanChael/CommonPopupWindow#传送门)


# API
>调用类介绍(重点关注自定义布局视图类BasePopView)    
+BasePopView、SharePopView是主要调用类，对创建显示进行了一定封装
+同时也是提供方位，动画、监听属性的关键类  

>显示方位介绍  
方位分两种:  
a.一种是基于点击控件的位置显示  
b. 另外一种是简单的上下左右居中的位置显示
```Java
 /**
      * 显示方位【基于控件】
      */
     public enum GRAVITY {
         LEFTTOP_TO_LEFTBOTTOM, LEFTTOP_TO_RIGHTBOTTOM,
         LEFTTOP_TO_LEFTTOP, LEFTTOP_TO_RIGHTTOP,
         RIGHTTOP_TO_LEFTBOTTOM, RIGHTTOP_TO_RIGHTBOTTOM,
         RIGHTTOP_TO_RIGHTTOP, RIGHTBOTTOM_TO_LEFTTOP,
         RIGHTBOTTOM_TO_RIGHTTOP, LEFTBOTTOM_TO_RIGHTTOP,
         LEFTBOTTOM_TO_LEFTTOP
     }
 
     /**
      * 简单上下左右中显示
      */
     public enum SIMPLE_GRAVITY {
         CENTER_IN_PARENT, FROM_BOTTOM, FROM_TOP,
         FROM_LEFT, FROM_RIGHT
     }
```
>动画介绍  
居于控件方位显示可以设置SCALE动画  
居于简单平移显示可以设置平移动画  
其他情况设置动画不会生效
```Java
 /**
     * 显示动画
     */
    public enum ANIMATION {
        NONE, SCALE, TRANSLATE
    }
```

>调用接口介绍  

1.自定义弹窗方式 - 提供调用类 BasePopView.java
```Java
/**
     * 传递对应参数进行窗体创建和显示
     * @param _context 【必填】
     * @param _anchor 【必填】
     * @param _layoutResId 【必填】
     * @param _popW 不需要给 < 0 - 那样需要自己做好布局自适应处理
     * @param _popH 不需要给 < 0 - 那样需要自己做好布局自适应处理
     * @param bOutsideTouchable 【必填】
     * @param _backColor - 不需要给-1
     * @param _animation - 不需要给null
     * @param _onClickListenner - 不需要给null
     * @param _gravity 【必填】 BasePopView.SIMPLE_GRAVITY
     * @return BasePop.Builder
     */
    public static BasePop.Builder show(Context _context, View _anchor,
                                       int _layoutResId, int _popW, int _popH,
                                       boolean bOutsideTouchable, int _backColor,
                                       BasePopView.ANIMATION _animation,
                                       BasePopView.OnClickListenner _onClickListenner,
                                       BasePopView.SIMPLE_GRAVITY _gravity){
        return show(_context, _anchor, _layoutResId, _popW, _popH,
                bOutsideTouchable, _backColor, _animation,
                _onClickListenner, null, _gravity);
    }
    /**
     * 传递对应参数进行窗体创建和显示
     * @param _context 【必填】
     * @param _anchor 【必填】
     * @param _layoutResId 【必填】
     * @param _popW 不需要给 < 0 - 那样需要自己做好布局自适应处理
     * @param _popH 不需要给 < 0 - 那样需要自己做好布局自适应处理
     * @param bOutsideTouchable 【必填】
     * @param _backColor - 不需要给-1
     * @param _animation - 不需要给null
     * @param _onClickListenner - 不需要给null
     * @param _gravity 【必填】 BasePopView.GRAVITY
     * @return BasePop.Builder
     */
    public static BasePop.Builder show(Context _context, View _anchor,
                                       int _layoutResId, int _popW, int _popH,
                                       boolean bOutsideTouchable, int _backColor,
                                       BasePopView.ANIMATION _animation,
                                       BasePopView.OnClickListenner _onClickListenner,
                                       BasePopView.GRAVITY _gravity){
        return show(_context, _anchor, _layoutResId, _popW, _popH,
                bOutsideTouchable, _backColor, _animation,
                _onClickListenner, _gravity, null);
    }
     /**
     * 传递对应参数进行窗体创建和显示
     * @param _context 【必填】
     * @param _anchor 【必填】
     * @param _layoutResId 【必填】
     * @param _popW 不需要给 < 0 - 那样需要自己做好布局自适应处理
     * @param _popH 不需要给 < 0 - 那样需要自己做好布局自适应处理
     * @param bOutsideTouchable 【必填】
     * @param _backColor - 不需要给-1
     * @param _animation - 不需要给null
     * @param _onClickListenner - 不需要给null
     * @param _gravity 【与_sgravity选一个】 BasePopView.GRAVITY
     * @param _sgravity 【与_gravity选一个】 BasePopView.SIMPLE_GRAVITY
     * @return BasePop.Builder
     */
    private static BasePop.Builder show(Context _context, View _anchor,
                                       int _layoutResId, int _popW, int _popH,
                                       boolean bOutsideTouchable, int _backColor,
                                       BasePopView.ANIMATION _animation,
                                       BasePopView.OnClickListenner _onClickListenner,
                                       BasePopView.GRAVITY _gravity, BasePopView.SIMPLE_GRAVITY _sgravity){
                                       }
```
2.分享弹窗 - 提供调用类 SharePopView.java
>目前提供两种显示样式
```Java
    /**
     * 分享弹窗样式
     * ME - 自家的
     * TENCENT - 仿腾讯
     */
    public enum SHARE_TYPE{
        ME, F_TENCENT
    }
```
>分享列表提供横向滑动+网格类型
```Java
    /**
     * 分享列表显示模式
     * HORIZON - 水平滑动 Item's Count > 5个的情况下
     * GRID - 网格 5列
     */
    public enum SHOW_TYPE{
        HORIZON, GRID
    }
```
>调用显示方法
```Java
    /**
     * 显示分享弹窗 - 默认大于5个方可左右滑动
     * @param _context
     * @param _achor
     * @param _share2Name - 如果传null，默认提供朋友圈、微信、qq、新浪、复制链接
     * @param _share2Icon
     * @param _simple_gravity - 支持从下到上以及从上到下显示方式
     * @param _show_type
     * @param _onShareClickListenner
     * @return
     */
    public static BasePop.Builder showShare(Context _context, View _achor,
                                 List<String> _share2Name, List<Integer> _share2Icon,
                                 BasePopView.SIMPLE_GRAVITY _simple_gravity,
                                 SharePopView.SHOW_TYPE _show_type,
                                 OnEventListenner.OnShareClickListenner _onShareClickListenner){
        return new Builder(_context)
                .create(_achor, SHARE_TYPE.ME)
                .setTitleAndIcon(_share2Name, _share2Icon)
                .showShareBorder(_simple_gravity, _show_type, _onShareClickListenner);
    }

    /**
     * 显示分享弹窗 - 默认大于5个方可左右滑动
     * @param _context
     * @param _achor
     * @param _share2Name- 如果传null，默认提供朋友圈、微信、qq、新浪、复制链接
     * @param _share2Icon
     * @param _simple_gravity- 支持从下到上以及从上到下显示方式
     * @param _show_type
     * @param _onShareClickListenner
     * @return
     */
    public static BasePop.Builder showShareFTencent(Context _context, View _achor,
                                            List<String> _share2Name, List<Integer> _share2Icon,
                                            BasePopView.SIMPLE_GRAVITY _simple_gravity,
                                            SharePopView.SHOW_TYPE _show_type,
                                            OnEventListenner.OnShareClickListenner _onShareClickListenner){
        return new Builder(_context)
                .create(_achor, SHARE_TYPE.F_TENCENT)
                .setTitleAndIcon(_share2Name, _share2Icon)
                .showShareBorder(_simple_gravity, _show_type, _onShareClickListenner);
    }
```
##### 3.后续新的功能的用法，请移步传送门

# USE  
#### --> 自定义方式 
a. 基于控件(achor_view)右下角显示
```Java
 // BasePopView.ANIMATION animation可以传null或者BasePopView.NONE
 BasePopView.show(this,
              view, R.layout.activity_pop,
              ScreenUtil.getScreenW(this) / 2, 500,
              BasePopView.ANIMATION.SCALE, null,
              BasePopView.GRAVITY.LEFTTOP_TO_RIGHTBOTTOM);
```
b. 基于控件(achor_view)左下角显示
```Java
 BasePopView.show(this,
              view, R.layout.activity_pop,
              ScreenUtil.getScreenW(this) / 2, 500,
              BasePopView.ANIMATION.SCALE, null,
              BasePopView.GRAVITY.RIGHTTOP_TO_LEFTBOTTOM);
```
c. 基于控件(achor_view)右上角显示
```Java
 BasePopView.show(this,
              view, R.layout.activity_pop,
              ScreenUtil.getScreenW(this) / 2, 500,
              BasePopView.ANIMATION.SCALE, null,
              BasePopView.GRAVITY.LEFTBOTTOM_TO_RIGHTTOP);
```
d. 基于控件(achor_view)左上角显示
```Java
 BasePopView.show(this,
              view, R.layout.activity_pop,
              ScreenUtil.getScreenW(this) / 2, 500,
              BasePopView.ANIMATION.SCALE, null,
              BasePopView.GRAVITY.RIGHTBOTTOM_TO_LEFTTOP);
```
e. 居中显示
```Java
 BasePopView.show(this,
              view, R.layout.activity_pop,
              ScreenUtil.getScreenW(this) / 2, 500,
              BasePopView.ANIMATION.SCALE, null,
              BasePopView.SIMPLE_GRAVITY.CENTER_IN_PARENT);
```
f. 从上往下平移显示
```Java
 BasePopView.show(this,
              view, R.layout.activity_pop,
              ScreenUtil.getScreenW(this), 500,
              BasePopView.ANIMATION.TRANSLATE, null,
              BasePopView.SIMPLE_GRAVITY.FROM_TOP);
```
g. 从下往上平移显示
```Java
 BasePopView.show(this,
              view, R.layout.activity_pop,
              ScreenUtil.getScreenW(this), 500,
              BasePopView.ANIMATION.TRANSLATE, null,
              BasePopView.GRAVITY.FROM_BOTTOM);
```
h. 从左往右平移显示
```Java
 BasePopView.show(this,
              view, R.layout.activity_pop,
              ScreenUtil.getScreenW(this) / 4, ScreenUtil.getScreenH(this),
              BasePopView.ANIMATION.TRANSLATE, null,
              BasePopView.GRAVITY.FROM_LEFT);
```
i. 从右往左平移显示
```Java
 BasePopView.show(this,
              view, R.layout.activity_pop,
              ScreenUtil.getScreenW(this) / 4, ScreenUtil.getScreenH(this),
              BasePopView.ANIMATION.TRANSLATE, null,
              BasePopView.GRAVITY.FROM_RIGHT);
```
#### --> 其他使用请移步传送门

# ATTENTION 
>Animation:  
1.基于控件方位(GRAVITY)显示的方式-平移动画无效  
2.基于简单上下左右平移(SIMPLE_GRAVITY)显示的方式-缩放无效  
3.居中显示方式-只有居中缩放动画  
4.不需要动画传null即可  
5.注意下宽高设置的参数(有问题再完善)    
```Java
        /**
         * 设置宽高 0 - 表示内容包裹 -1 - 表示全屏  其他表示具体宽高
         *         (width == -1)height -10000 - 表示高度为控件之下到屏幕底部的高度
         *         (width == -1)height -20000 - 表示高度为控件之上到屏幕顶部的高度
         * @param width
         * @param height
         * @return
         */
        public Builder setWidthAndHeight(int width, int height) {
            if (width == 0 && height == 0) {
                ///< 如果没设置宽高，默认是WRAP_CONTENT
                basePop.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
                basePop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            } else if (width == -1 && height == -1) {
                basePop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                basePop.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            }else if (width > 0 && height == 0) {
                basePop.setWidth(width);
                basePop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            }else if (width == -1 && height == 0) {
                basePop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                basePop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            }else if (width > 0 && height == -1) {
                basePop.setWidth(width);
                basePop.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            }else if (width == -1 && height == -10000) {    ///< 高度为控件之下到屏幕底部的高度
                int achorH = viewWeakReference.get().getMeasuredHeight();
                int screenH = ScreenUtil.getScreenH(contextWeakReference.get());
                int[] achorLocation = new  int[2] ;
                viewWeakReference.get().getLocationInWindow(achorLocation);
                basePop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                basePop.setHeight(screenH - achorLocation[1] - achorH - 1);
            }else if (width == -1 && height == -20000) {    ///< 高度为控件之上到屏幕顶部的高度
                int[] achorLocation = new  int[2] ;
                viewWeakReference.get().getLocationInWindow(achorLocation);
                basePop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                basePop.setHeight(achorLocation[1]);
            }
            else {
                basePop.setWidth(width);
                basePop.setHeight(height);
            }
            return this;
        }
```

# 其他逻辑    
>调用BasePopView.show后可以保存BasePop.Builder，方便处理消失等逻辑;
比如，demo工程里面的案例
```Java
    /**
     * 弹窗显示测试
     *
     * @param view
     */
    public void testPop(View view) {
        ///< 已经显示则重新来过
        if (null != builder && builder.bIsShowing()) {
            builder.dissmiss();///< 或者return，防止重复显示
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
           ///....其他方式
        }
    }
```
