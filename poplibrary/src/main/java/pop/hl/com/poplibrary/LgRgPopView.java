package pop.hl.com.poplibrary;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import pop.hl.com.poplibrary.base.BasePop;
import pop.hl.com.poplibrary.utils.EditTextUtil;
import pop.hl.com.poplibrary.utils.ShapeUtil;

/*
 *@Description: 登录注册弹窗View总类 + 包含点击事件回调
 *@Author: hl
 *@Time: 2019/2/15 11:37
 */
public class LgRgPopView {
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

    /**
     * 点击事件回调类型
     */
    public enum CALLBACK_TYPE {
        CLICK_LOGIN, CLICK_REGISTER,
        CLICK_GET_VERYCODE,
        FORGET_PASS, GO_REGISTER,
        QQ_LOGIN, WEIXIN_LOGIN
    }

    /**
     * 登录弹窗显示
     * @param _context
     * @param _achor
     * @param _login_type
     * @param _allColor
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
    
    /*
    *@Description: 登录注册建造器
    *@Author: hl
    *@Time: 2019/2/19 17:02
    */
    public static class Builder {
        private WeakReference<Context> contextWeakReference;
        private BasePop.Builder builder = null;
        private String allColor;
        private GradientDrawable gradientDrawable;
        private GradientDrawable gradientDrawableb;

        public Builder(Context _context){
            this.contextWeakReference = new WeakReference<>(_context);
        }

        public Builder create(String _allColor, View _achor) {
            this.allColor = _allColor;
            this.gradientDrawable = ShapeUtil.createShape(-1,
                    6,
                    -1, null, allColor);
            this.gradientDrawableb = ShapeUtil.createShape(-1,
                    6,
                    -1, null, allColor);
            this.builder = new BasePop.Builder(contextWeakReference.get())
                    .create(_achor)
                    .setOutsideTouchable(true)
                    .setWidthAndHeight(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            return this;
        }

        /**
         * 显示登录弹窗
         * @param _login_type
         * @param _onLRClickListenner
         * @return
         */
        public BasePop.Builder showLogin(int _login_type, final OnEventListenner.OnLRClickListenner _onLRClickListenner){
            int layoutId;
            String userLoginHint = "请输入手机号";
            int loginContentRootId = -1;
            int getVerifyTvId = -1;
            int loginTvId = -1;
            int usernameId = -1;
            int verifypassId = -1;
            int usernameRootId = -1;
            int verifypassRootId = -1;
            int loginQQId = -1;
            int loginWxId = -1;

            switch (_login_type) {
                case LOGIN_TYPE.USER_NAME:
                    layoutId = R.layout.pop_normal_login;
                    userLoginHint = "请输入用户名";
                    usernameId = R.id.pnl_useruserNameTIE;
                    verifypassId = R.id.pnl_passTIE;
                    usernameRootId = R.id.pnl_userNameTIL;
                    verifypassRootId = R.id.pnl_userPassTIL;
                    loginContentRootId = R.id.pnl_loginContentRoot;
                    loginTvId = R.id.pnl_loginTv;
                    break;
                case LOGIN_TYPE.USER_PHONE:
                    layoutId = R.layout.pop_normal_login;
                    loginContentRootId = R.id.pnl_loginContentRoot;
                    loginTvId = R.id.pnl_loginTv;
                    usernameId = R.id.pnl_useruserNameTIE;
                    verifypassId = R.id.pnl_passTIE;
                    usernameRootId = R.id.pnl_userNameTIL;
                    verifypassRootId = R.id.pnl_userPassTIL;
                    break;
                case LOGIN_TYPE.PHONE_VERICODE:
                    layoutId = R.layout.pop_verify_login;
                    loginContentRootId = R.id.pvl_loginContentRoot;
                    getVerifyTvId = R.id.pvl_getVerifyTv;
                    loginTvId = R.id.pvl_loginTv;
                    usernameId = R.id.pvl_useruserNameTIE;
                    verifypassId = R.id.pvl_passTIE;
                    usernameRootId = R.id.pvl_userNameTIL;
                    verifypassRootId = R.id.pvl_userVerifyTIL;
                    break;
                case LOGIN_TYPE.THIRD_ACCOUNT:
                    layoutId = R.layout.pop_third_login;
                    loginContentRootId = R.id.ptl_loginContentRoot;
                    loginQQId = R.id.ptl_loginQQIv;
                    loginWxId = R.id.ptl_loginWXIv;
                    break;
                case LOGIN_TYPE.USER_NAME | LOGIN_TYPE.USER_PHONE:
                    layoutId = R.layout.pop_normal_login;
                    userLoginHint = "请输入手机号/用户名";
                    usernameId = R.id.pnl_useruserNameTIE;
                    verifypassId = R.id.pnl_passTIE;
                    usernameRootId = R.id.pnl_userNameTIL;
                    verifypassRootId = R.id.pnl_userPassTIL;
                    loginContentRootId = R.id.pnl_loginContentRoot;
                    loginTvId = R.id.pnl_loginTv;
                    break;
                case LOGIN_TYPE.USER_NAME | LOGIN_TYPE.USER_PHONE | LOGIN_TYPE.THIRD_ACCOUNT:
                    layoutId = R.layout.pop_normal_third_login;
                    userLoginHint = "请输入手机号/用户名";
                    usernameId = R.id.pntl_useruserNameTIE;
                    verifypassId = R.id.pntl_passTIE;
                    usernameRootId = R.id.pntl_userNameTIL;
                    verifypassRootId = R.id.pntl_userPassTIL;
                    loginContentRootId = R.id.pntl_loginContentRoot;
                    loginTvId = R.id.pntl_loginTv;
                    loginQQId = R.id.pntl_loginQQIv;
                    loginWxId = R.id.pntl_loginWXIv;
                    break;
                case LOGIN_TYPE.PHONE_VERICODE | LOGIN_TYPE.THIRD_ACCOUNT:
                    layoutId = R.layout.pop_verify_third_login;
                    loginContentRootId = R.id.pvtl_loginContentRoot;
                    getVerifyTvId = R.id.pvtl_getVerifyTv;
                    usernameId = R.id.pvtl_useruserNameTIE;
                    verifypassId = R.id.pvtl_passTIE;
                    usernameRootId = R.id.pvtl_userNameTIL;
                    verifypassRootId = R.id.pvtl_userVerifyTIL;
                    loginTvId = R.id.pvtl_loginTv;
                    loginQQId = R.id.pvtl_loginQQIv;
                    loginWxId = R.id.pvtl_loginWXIv;
                    break;
                case LOGIN_TYPE.USER_PHONE | LOGIN_TYPE.THIRD_ACCOUNT:
                    layoutId = R.layout.pop_normal_third_login;
                    loginContentRootId = R.id.pntl_loginContentRoot;
                    usernameId = R.id.pntl_useruserNameTIE;
                    verifypassId = R.id.pntl_passTIE;
                    usernameRootId = R.id.pntl_userNameTIL;
                    verifypassRootId = R.id.pntl_userPassTIL;
                    loginTvId = R.id.pntl_loginTv;
                    loginQQId = R.id.pntl_loginQQIv;
                    loginWxId = R.id.pntl_loginWXIv;
                    break;
                default:
                    layoutId = R.layout.pop_verify_login;
                    loginContentRootId = R.id.pvl_loginContentRoot;
                    loginTvId = R.id.pvl_loginTv;
                    usernameId = R.id.pvl_useruserNameTIE;
                    usernameRootId = R.id.pvl_userNameTIL;
                    verifypassRootId = R.id.pvl_userVerifyTIL;
                    verifypassId = R.id.pvl_passTIE;
                    break;
            }
            ///< 加载视图
            builder.setView(layoutId);

            ///< 获取弹窗视图
            View popView = builder.getView();

            final ConstraintLayout loginContentRoot = popView.findViewById(loginContentRootId);
            ///< 修改用户登录框提示
            final TextInputLayout usernameRootTi = popView.findViewById(usernameRootId);
            final TextInputLayout verifypassRootTi = popView.findViewById(verifypassRootId);
            final TextInputEditText usernameEt = popView.findViewById(usernameId);
            final TextInputEditText verifypassEt = popView.findViewById(verifypassId);

            ///< 编辑框下划线颜色、按钮颜色统一修改
            EditTextUtil.setCursorDrawableColor(usernameEt, Color.parseColor(allColor));
            EditTextUtil.setCursorDrawableColor(verifypassEt, Color.parseColor(allColor));

            ///< 用户登录提示、样式设置
            if (!userLoginHint.equals("请输入手机号")) {
                usernameRootTi.setCounterMaxLength(18);
                ///< 这里设置Hint方可(usernameEt中再设置就重复了，效果重点是由TextInputLayout控制
                ///< 猜想下，如果edit也设置hint就会重叠显示，那么估计要么hint绘制了两遍，要么就是独立进行上层绘制了，感觉不友好的样子呀！)
                usernameRootTi.setHint(userLoginHint);
                //usernameEt.setHint(userLoginHint);
                usernameEt.setFilters(new InputFilter[]{new InputFilter.LengthFilter(18)});
                usernameEt.setInputType(InputType.TYPE_CLASS_TEXT);
            }
            ///< 点击事件回调
            popView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        int[] location = new int[2];
                        loginContentRoot.getLocationInWindow(location);
                        int rcW = loginContentRoot.getMeasuredWidth();
                        int rcH = loginContentRoot.getMeasuredHeight();
                        if (!(event.getX() >= location[0] &&
                                event.getX() < (location[0] + rcW) &&
                                event.getY() >= location[1] &&
                                event.getY() < (location[1] + rcH))) {
                            builder.dissmiss();
                        }
                    }
                    return true;
                }
            });
            usernameEt.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (!usernameEt.getText().toString().trim().equals("")){
                        usernameRootTi.setErrorEnabled(false);
                    }
                }
            });
            verifypassEt.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (!verifypassEt.getText().toString().trim().equals("")){
                        verifypassRootTi.setErrorEnabled(false);
                    }
                }
            });
            ///< 获取验证码点击事件
            if (-1 != getVerifyTvId) {
                TextView getVerifyTv = popView.findViewById(getVerifyTvId);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    getVerifyTv.setBackground(gradientDrawable);
                }else{
                    getVerifyTv.setBackgroundDrawable(gradientDrawable);
                }
                getVerifyTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (usernameEt.getText().toString().trim().equals("")) {
                            usernameRootTi.setError(usernameRootTi.getHint().toString());
                            return;
                        }
                        _onLRClickListenner.onClick(v,
                                new String[]{usernameEt.getText().toString().trim()},
                                CALLBACK_TYPE.CLICK_GET_VERYCODE);
                    }
                });
            }
            ///< 登录点击事件
            if (-1 != loginTvId) {
                Button loginTv = popView.findViewById(loginTvId);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    loginTv.setBackground(gradientDrawableb);
                }else{
                    loginTv.setBackgroundDrawable(gradientDrawableb);
                }
                loginTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (usernameEt.getText().toString().trim().equals("")) {
                            usernameRootTi.setError(usernameRootTi.getHint().toString());
                            return;
                        }
                        if (verifypassEt.getText().toString().trim().equals("")) {
                            verifypassRootTi.setError(verifypassRootTi.getHint().toString());
                            return;
                        }
                        _onLRClickListenner.onClick(v,
                                new String[]{usernameEt.getText().toString().trim(),
                                        verifypassEt.getText().toString().trim()},
                                CALLBACK_TYPE.CLICK_LOGIN);
                    }
                });
            }
            ///< 第三方登录点击事件
            if (-1 != loginQQId && -1 != loginWxId) {
                ImageView loginQQIv = popView.findViewById(loginQQId);
                loginQQIv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        _onLRClickListenner.onClick(v, null, CALLBACK_TYPE.QQ_LOGIN);
                    }
                });
                ImageView loginWxIv = popView.findViewById(loginWxId);
                loginWxIv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        _onLRClickListenner.onClick(v, null, CALLBACK_TYPE.WEIXIN_LOGIN);
                    }
                });
            }
            builder.show(BasePopView.SIMPLE_GRAVITY.CENTER_IN_PARENT);

            return builder;
        }

        /**
         * 显示注册弹窗
         * @param _onLRClickListenner
         * @return
         */
        public BasePop.Builder showRegister(final OnEventListenner.OnLRClickListenner _onLRClickListenner) {
            ///< 加载视图
            builder.setView(R.layout.pop_register);

            ///< 获取弹窗视图
            View popView = builder.getView();

            ConstraintLayout registerRoot = popView.findViewById(R.id.pr_registerRoot);
            final ConstraintLayout registerContentRoot = popView.findViewById(R.id.pr_registerContentRoot);
            TextView getVerifyTv = popView.findViewById(R.id.pr_getVerifyTv);
            final TextInputLayout usernameRootTIL = popView.findViewById(R.id.pr_userNameTIL);
            final TextInputEditText usernameEt = popView.findViewById(R.id.pr_userNameEt);
            final TextInputLayout verifypassRootTIL = popView.findViewById(R.id.pr_userVerifyTIL);
            final TextInputEditText verifypassEt = popView.findViewById(R.id.pr_userVerifyEt);
            final TextInputLayout userPassTIL = popView.findViewById(R.id.pr_userPassTIL);
            final TextInputEditText userPassEt = popView.findViewById(R.id.pr_userPassEt);
            Button registerTv = popView.findViewById(R.id.pr_registerTv);

            ///< 修改编辑框光标颜色
            EditTextUtil.setCursorDrawableColor(usernameEt, Color.parseColor(allColor));
            EditTextUtil.setCursorDrawableColor(verifypassEt, Color.parseColor(allColor));
            EditTextUtil.setCursorDrawableColor(userPassEt, Color.parseColor(allColor));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                getVerifyTv.setBackground(gradientDrawable);
                registerTv.setBackground(gradientDrawableb);
            }else{
                getVerifyTv.setBackgroundDrawable(gradientDrawable);
                registerTv.setBackgroundDrawable(gradientDrawableb);
            }

            ///< 点击事件回调
            registerRoot.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        int[] location = new int[2];
                        registerContentRoot.getLocationInWindow(location);
                        int rcW = registerContentRoot.getMeasuredWidth();
                        int rcH = registerContentRoot.getMeasuredHeight();
                        if (!(event.getX() >= location[0] &&
                                event.getX() < (location[0] + rcW) &&
                                event.getY() >= location[1] &&
                                event.getY() < (location[1] + rcH))) {
                            builder.dissmiss();
                        }
                    }
                    return true;
                }
            });
            usernameEt.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (!usernameEt.getText().toString().trim().equals("")){
                        usernameRootTIL.setErrorEnabled(false);
                    }
                }
            });
            verifypassEt.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (!verifypassEt.getText().toString().trim().equals("")){
                        verifypassRootTIL.setErrorEnabled(false);
                    }
                }
            });
            userPassEt.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (!userPassEt.getText().toString().trim().equals("")){
                        userPassTIL.setErrorEnabled(false);
                    }
                }
            });
            getVerifyTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (usernameEt.getText().toString().trim().equals("")) {
                        usernameRootTIL.setError(usernameRootTIL.getHint().toString());
                        return;
                    }
                    _onLRClickListenner.onClick(v, new String[]{usernameEt.getText().toString().trim()}, CALLBACK_TYPE.CLICK_GET_VERYCODE);
                }
            });
            registerTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (usernameEt.getText().toString().trim().equals("")) {
                        usernameRootTIL.setError(usernameRootTIL.getHint().toString());
                        return;
                    }
                    if (verifypassEt.getText().toString().trim().equals("")) {
                        verifypassRootTIL.setError(verifypassRootTIL.getHint().toString());
                        return;
                    }
                    if (userPassEt.getText().toString().trim().equals("")) {
                        userPassTIL.setError(userPassTIL.getHint().toString());
                        return;
                    }
                    _onLRClickListenner.onClick(v,
                            new String[]{usernameEt.getText().toString().trim(),
                                    verifypassEt.getText().toString().trim(),
                                    userPassEt.getText().toString().trim()},
                            CALLBACK_TYPE.CLICK_REGISTER);
                }
            });
            builder.show(BasePopView.SIMPLE_GRAVITY.CENTER_IN_PARENT);

            return builder;
        }
    }
}
