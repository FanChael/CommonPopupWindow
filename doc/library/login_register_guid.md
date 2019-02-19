# API说明-提供调用类LgRgPopView.java
>登录提供做种组合样式
```Java
    /**
     * 登录弹窗显示类型
     * USER_NAME - 用户名+密码登录
     * USER_PHONE - 手机号+密码登录
     * PHONE_VERICODE - 手机号验证码登录
     * THIRD_ACCOUNT - 第三方登录
     * 可叠加方式:
     * 1. USER_NAME | USER_PHONE（单行11位数字限制)
     * 2. USER_NAME | USER_PHONE（单行11位数字限制) | THIRD_ACCOUNT(目前提供微信、QQ)
     * 3. PHONE_VERICODE | THIRD_ACCOUNT(目前提供微信、QQ)
     * 4. LOGIN_TYPE.USER_PHONE单行11位数字限制) | LOGIN_TYPE.THIRD_ACCOUNT(目前提供微信、QQ)
     */
    public static class LOGIN_TYPE {
        public static final int USER_NAME = 0x0001;
        public static final int USER_PHONE = 0x0001 << 1;
        public static final int PHONE_VERICODE = 0x0001 << 2;
        public static final int THIRD_ACCOUNT = 0x0001 << 3;
    }
```
>点击事件回调类型属性
```Java
    /**
     * 点击事件回调类型
     */
    public enum CALLBACK_TYPE {
        CLICK_LOGIN, CLICK_REGISTER,
        CLICK_GET_VERYCODE,
        FORGET_PASS, GO_REGISTER,
        QQ_LOGIN, WEIXIN_LOGIN
    }
```

>回调参数说明
```Java
    /**
     * 登录注册弹窗点击事件回调
     * parmas - 表示当前弹窗对应的编辑框的内容（从上之下)的数组值
     *        - 如果没有编辑框，则返回的是null(无编辑框内容返回)
     * callback_type - 是各个按钮点击类型LgRgPopView.CALLBACK_TYPE
     */
    public interface OnLRClickListenner{
        void onClick(View view, String[] parmas, LgRgPopView.CALLBACK_TYPE callback_type);
    }
```

>调用显示方法
```Java
    /**
     * 登录弹窗显示
     * @param _context
     * @param _achor
     * @param _login_type
     * @param _allColor - 代表了按钮、光标的颜色
     * @param _onLRClickListenner
     * @return
     */
    public static BasePop.Builder showLogin(Context _context,
                                            View _achor, int _login_type,
                                            String _allColor,
                                            final OnEventListenner.OnLRClickListenner _onLRClickListenner) {
        return new Builder(_context)
                .create(_allColor, _achor)
                .showLogin(_login_type, _onLRClickListenner);
    }

    /**
     * 注册弹窗显示
     * @param _context
     * @param _achor
     * @param _onLRClickListenner
     * @return
     */
    public static BasePop.Builder showRegister(Context _context,
                                               View _achor,
                                               String _allColor,
                                               final OnEventListenner.OnLRClickListenner _onLRClickListenner) {
        return new Builder(_context)
                .create(_allColor, _achor)
                .showRegister(_onLRClickListenner);
    }
```
# USE  
#####_allColor - 代表了按钮、光标的颜色; 
#####如果要修改下划线的颜色，建议app下：
```Java
 <!-- Base application theme. -->
    <style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
        <!-- Customize your theme here. -->
        <item name="colorPrimary">@color/colorPrimary</item>
        <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
        <item name="colorAccent">@color/colorAccent</item>
        <!-- AppCompatEditText默认状态状态设置底线颜色 -->
        <item name="colorControlNormal">@color/blue_a</item>
        <!-- AppCompatEditText选择的底线颜色 -->
        <item name="colorControlActivated">@color/blue_a</item>
    </style>
```
a. 注册弹窗调用(如需增加样式，可以issue)
```Java
LgRgPopView.showRegister(this,
    view, "#f0008DCF",
            new OnEventListenner.OnLRClickListenner() {
        @Override
        public void onClick(View view, String[] parmas, LgRgPopView.CALLBACK_TYPE callback_type) {
                switch (callback_type){
                    case CLICK_REGISTER:
                        Toast.makeText(MainActivity.this, "callback_type=" + callback_type + " parmas" + parmas[0], Toast.LENGTH_SHORT).show();
                        break;
                    case CLICK_GET_VERYCODE:
                        break;
                }
        }
    });
```
b. 登录弹窗
```Java
LgRgPopView.showLogin(this,view,
                LgRgPopView.LOGIN_TYPE.THIRD_ACCOUNT | LgRgPopView.LOGIN_TYPE.PHONE_VERICODE,
                "#f0008DCF",
                new OnEventListenner.OnLRClickListenner() {
                    @Override
                    public void onClick(View view, String[] parmas, LgRgPopView.CALLBACK_TYPE callback_type) {
                            switch (callback_type){
                                case QQ_LOGIN:
                                    Toast.makeText(MainActivity.this, "点击了QQ登录", Toast.LENGTH_SHORT).show();
                                    break;
                                case WEIXIN_LOGIN:
                                    break;
                                case CLICK_LOGIN:
                                    break;
                                default:    
                                    break;
                            }
                    }
                });
```