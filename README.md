# CommonPopupWindow
Popular popupwindow in the market and customized.
[ ![Download](https://api.bintray.com/packages/resetmyself/holdon/commonpop/images/download.svg?version=1.1.0) ](https://bintray.com/resetmyself/holdon/commonpop/1.1.0/link)
（结合市面流行的弹窗样式+支持自定义布局+需求/问题请Issue）. 

# Description
>poplibrary引入即可调用弹窗

>(其他配置待续)

# Feature  

>自定义布局(所有点击事件统一回调)  

# Effect  
>总的效果  
~V1.1.0 
- 2019.02.14 -  - 自定义布局  
![方位展示](https://github.com/FanChael/CommonPopupWindow/blob/master/doc/2019.02.14_define_base_direction.gif) 

##### [Demo apk下载](https://github.com/FanChael/CommonPopupWindow/blob/master/doc/app-debug.apk)

# Import Library  
>For gradle:  
```Java
implementation 'com.hl:poplibrary:1.1.0'
```
>Or in maven:
```Java
<dependency>
    <groupId>com.hl</groupId>
    <artifactId>poplibrary</artifactId>
    <version>1.1.0</version>
    <type>pom</type>
</dependency>
```

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
     
     /**
      * 显示动画
      * SCALE - 缩放
      * TRANSLATE - 平移
      * FOLD - 折叠 - 从下往上/从上往下(暂不支持简单方位显示方式)
      */
     public enum ANIMATION {
         NONE, SCALE, TRANSLATE, FOLD
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
# ATTENTION 
>Animation：
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
