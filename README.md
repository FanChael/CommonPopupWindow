# CommonPopupWindow
Popular popupwindow in the market and customized.

（结合市面流行的弹窗样式+支持自定义布局）. 
# Description
>poplibrary引入即可调用弹窗

>(其他配置待续)

# Feature  

>支持自定义布局(所有点击事件统一回调)  

>底部分享弹窗-结合市面流行样式(目前两种主流样式，如需增加请issue)

>仿xxxx更新弹窗 TODO

# Effect  
>总的效果  
~V1.0.1 
- 2019.02.14 -  - 自定义布局  
![方位展示](https://github.com/FanChael/CommonPopupWindow/blob/master/doc/2019.02.14_define_base_direction.gif)   
~V1.0.2   
- 2019.02.16 -  - 添加分享弹窗  
![方位展示](https://github.com/FanChael/CommonPopupWindow/blob/master/doc/2019.02.16_add_shareboard.gif) 

##### [Demo apk下载](https://github.com/FanChael/CommonPopupWindow/blob/master/doc/app-debug.apk)

# Import Library  
>For gradle:  
```Java
implementation 'com.hl:poplibrary:1.0.2'
```
>Or in maven:
```Java
<dependency>
    <groupId>com.hl</groupId>
    <artifactId>poplibrary</artifactId>
    <version>1.0.2</version>
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
#### --> 分享弹窗 
a. 自家样式(列表随机为水平或者网格 标题和图标不传默认给五个基本分享平台)
```Java
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
    BasePop.Builder builder = SharePopView.showShare(this, view,
                randomValue == 1 ? _share2Name : null,
                randomValue == 1 ? _share2Icon : null,
                (new Random().nextInt(2)) == 1 ?
                        BasePopView.SIMPLE_GRAVITY.FROM_TOP :
                        BasePopView.SIMPLE_GRAVITY.FROM_BOTTOM,
                (new Random().nextInt(2)) == 1 ?
                        SharePopView.SHOW_TYPE.HORIZON :
                        SharePopView.SHOW_TYPE.GRID,
                onShareClickListenner);
                
    
```
b. 仿腾讯样式(列表随机为水平或者网格 标题和图标不传默认给五个基本分享平台)
```Java
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
    BasePop.Builder builder = SharePopView.showShareFTencent(this, view,
                randomValue == 1 ? _share2Name : null,
                randomValue == 1 ? _share2Icon : null,
                BasePopView.SIMPLE_GRAVITY.FROM_BOTTOM,
                (new Random().nextInt(2)) == 1 ?
                        SharePopView.SHOW_TYPE.HORIZON :
                        SharePopView.SHOW_TYPE.GRID,
                onShareClickListenner);
```

#### --> 有空写篇使用文章(不管好不好， 小萌新以后就会用起这个弹窗了，然后不断学习加强，嘻嘻) 

# ATTENTION 
>Animation：
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
