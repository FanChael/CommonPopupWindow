# CommonPopupWindow
Popular popupwindow in the market and customized.

（结合市面流行的弹窗样式+支持自定义布局）. 
# Description
>poplibrary引入即可调用弹窗

>全局配置是否允许重复弹出

>(其他配置待续)

# Feature  

>支持自定义布局(所有点击事件统一回调)  

>仿今日头条更新弹窗

# Effect  
>总的效果  
~V1.0.0 
- 2019.02.14 -  - 自定义布局  
![方位展示](https://github.com/FanChael/CommonPopupWindow/blob/master/doc/2019.02.14_define_base_direction.gif) 

>![Demo.apk下载](https://github.com/FanChael/CommonPopupWindow/blob/master/doc/app-debug.apk)<br/> 

# API  
>调用类介绍  
+PopView是主要调用类，对创建显示进行了一定封装
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

>调用接口介绍
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
     * @param _gravity 【必填】 PopView.SIMPLE_GRAVITY
     * @return BasePop.Builder
     */
    public static BasePop.Builder show(Context _context, View _anchor,
                                       int _layoutResId, int _popW, int _popH,
                                       boolean bOutsideTouchable, int _backColor,
                                       PopView.ANIMATION _animation,
                                       PopView.OnClickListenner _onClickListenner,
                                       PopView.SIMPLE_GRAVITY _gravity){
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
     * @param _gravity 【必填】 PopView.GRAVITY
     * @return BasePop.Builder
     */
    public static BasePop.Builder show(Context _context, View _anchor,
                                       int _layoutResId, int _popW, int _popH,
                                       boolean bOutsideTouchable, int _backColor,
                                       PopView.ANIMATION _animation,
                                       PopView.OnClickListenner _onClickListenner,
                                       PopView.GRAVITY _gravity){
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
     * @param _gravity 【与_sgravity选一个】 PopView.GRAVITY
     * @param _sgravity 【与_gravity选一个】 PopView.SIMPLE_GRAVITY
     * @return BasePop.Builder
     */
    private static BasePop.Builder show(Context _context, View _anchor,
                                       int _layoutResId, int _popW, int _popH,
                                       boolean bOutsideTouchable, int _backColor,
                                       PopView.ANIMATION _animation,
                                       PopView.OnClickListenner _onClickListenner,
                                       PopView.GRAVITY _gravity, PopView.SIMPLE_GRAVITY _sgravity){
                                       }
```

# USE  

a. 基于控件(achor_view)右下角显示
```Java
 // PopView.ANIMATION animation可以传null或者PopView.NONE
 PopView.show(this,
              view, R.layout.activity_pop,
              ScreenUtil.getScreenW(this) / 2, 500,
              PopView.ANIMATION.SCALE, null,
              PopView.GRAVITY.LEFTTOP_TO_RIGHTBOTTOM);
```
b. 基于控件(achor_view)左下角显示
```Java
 // PopView.ANIMATION animation可以传null或者PopView.NONE
 PopView.show(this,
              view, R.layout.activity_pop,
              ScreenUtil.getScreenW(this) / 2, 500,
              PopView.ANIMATION.SCALE, null,
              PopView.GRAVITY.RIGHTTOP_TO_LEFTBOTTOM);
```
c. 基于控件(achor_view)右上角显示
```Java
 // PopView.ANIMATION animation可以传null或者PopView.NONE
 PopView.show(this,
              view, R.layout.activity_pop,
              ScreenUtil.getScreenW(this) / 2, 500,
              PopView.ANIMATION.SCALE, null,
              PopView.GRAVITY.LEFTBOTTOM_TO_RIGHTTOP);
```
d. 基于控件(achor_view)左上角显示
```Java
 // PopView.ANIMATION animation可以传null或者PopView.NONE
 PopView.show(this,
              view, R.layout.activity_pop,
              ScreenUtil.getScreenW(this) / 2, 500,
              PopView.ANIMATION.SCALE, null,
              PopView.GRAVITY.RIGHTBOTTOM_TO_LEFTTOP);
```
e. 居中显示
```Java
 // PopView.ANIMATION animation可以传null或者PopView.NONE
 PopView.show(this,
              view, R.layout.activity_pop,
              ScreenUtil.getScreenW(this) / 2, 500,
              PopView.ANIMATION.SCALE, null,
              PopView.SIMPLE_GRAVITY.CENTER_IN_PARENT);
```
f. 从上往下平移显示
```Java
 // PopView.ANIMATION animation可以传null或者PopView.NONE
 PopView.show(this,
              view, R.layout.activity_pop,
              ScreenUtil.getScreenW(this), 500,
              PopView.ANIMATION.TRANSLATE, null,
              PopView.SIMPLE_GRAVITY.FROM_TOP);
```
g. 从下往上平移显示
```Java
 // PopView.ANIMATION animation可以传null或者PopView.NONE
 PopView.show(this,
              view, R.layout.activity_pop,
              ScreenUtil.getScreenW(this), 500,
              PopView.ANIMATION.TRANSLATE, null,
              PopView.GRAVITY.FROM_BOTTOM);
```
h. 从左往右平移显示
```Java
 // PopView.ANIMATION animation可以传null或者PopView.NONE
 PopView.show(this,
              view, R.layout.activity_pop,
              ScreenUtil.getScreenW(this) / 4, ScreenUtil.getScreenH(this),
              PopView.ANIMATION.TRANSLATE, null,
              PopView.GRAVITY.FROM_LEFT);
```
i. 从右往左平移显示
```Java
 // PopView.ANIMATION animation可以传null或者PopView.NONE
 PopView.show(this,
              view, R.layout.activity_pop,
              ScreenUtil.getScreenW(this) / 4, ScreenUtil.getScreenH(this),
              PopView.ANIMATION.TRANSLATE, null,
              PopView.GRAVITY.FROM_RIGHT);
```

# 其他逻辑    
>调用PopView.show后可以保存BasePop.Builder，方便处理消失等逻辑;
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
        PopView.OnClickListenner onClickListenner = new PopView.OnClickListenner() {
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
                builder = PopView.show(this,
                        view, R.layout.activity_pop,
                        ScreenUtil.getScreenW(this) / 2, 500,
                        PopView.ANIMATION.SCALE, onClickListenner,
                        PopView.GRAVITY.LEFTTOP_TO_RIGHTBOTTOM);
                break;
        }
    }
```
