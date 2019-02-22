# CommonPopupWindow
Popular popupwindow in the market and customized.  
[ ![Download](https://api.bintray.com/packages/resetmyself/holdon/commonpop/images/download.svg?version=1.0.4) ](https://bintray.com/resetmyself/holdon/commonpop/1.0.4/link)  

（结合市面流行的弹窗样式+支持自定义布局）. 
# Description
>poplibrary引入即可调用弹窗

>(其他配置待续)

# Feature  

>支持自定义布局(所有点击事件统一回调)  

>更新弹窗(目前经典样式)

>基于更新弹窗+Rx家族的App更新模块1.0.1.4(只包含自定义1.0.1+更新弹窗1.0.4)

# Effect  
>总的效果 
 
~V1.0.1 
- 2019.02.14 -  - 自定义布局  
![方位展示](https://github.com/FanChael/CommonPopupWindow/blob/master/doc/2019.02.14_define_base_direction.gif)  

~V1.0.4   
- 2019.02.20 -  - 添加经典更新弹窗  
![方位展示](https://github.com/FanChael/CommonPopupWindow/blob/master/doc/2019.02.20_add_normalupdate.gif) 

~V1.0.1.4  
- 2019.02.22 -  - 添加App更新模块
![方位展示](https://github.com/FanChael/CommonPopupWindow/blob/master/doc/2019.02.22_updateapp.gif) 

##### [Demo apk下载](https://github.com/FanChael/CommonPopupWindow/blob/master/doc/app_update.apk)

# Import Library  
>For gradle:  
```Java
//1.0.1 自定义
implementation 'com.hl:poplibrary:1.0.1'
//1.0.4 更新弹窗模块
implementation 'com.hl:poplibrary:1.0.1.4'
```
>Or in maven:
```Java
<dependency>
    <groupId>com.hl</groupId>
    <artifactId>poplibrary</artifactId>
    <version>1.0.x</version>
    <type>pom</type>
</dependency>
```
# 传送门  
> 使用
* [更新弹窗使用](https://github.com/FanChael/CommonPopupWindow/blob/master/doc/library/updagrade_guid.md)
* [APP更新使用](https://github.com/FanChael/CommonPopupWindow/blob/master/doc/library/appupdate_guid.md)

> Hold on！
* [更新日志](https://github.com/FanChael/CommonPopupWindow/blob/master/doc/library/update_guid.md)
* [学习博客](https://github.com/FanChael/CommonPopupWindow/blob/master/doc/library/study_guid.md)

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
#### --> [分享弹窗](https://github.com/FanChael/CommonPopupWindow/blob/master/doc/library/share_guid.md)

#### --> [注册登录弹窗使用](https://github.com/FanChael/CommonPopupWindow/blob/master/doc/library/login_register_guid.md)

#### --> [更新弹窗使用](https://github.com/FanChael/CommonPopupWindow/blob/master/doc/library/login_register_guid.md)

# ATTENTION 
>Animation:  
1.基于控件方位(GRAVITY)显示的方式-平移动画无效  
2.基于简单上下左右平移(SIMPLE_GRAVITY)显示的方式-缩放无效  
3.居中显示方式-只有居中缩放动画  
4.不需要动画传null即可

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
