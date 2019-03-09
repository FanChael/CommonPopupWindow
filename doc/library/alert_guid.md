# API说明-提供调用类AlertPopView.java
>点击事件回调类型
```Java
    /**
     * 点击事件回调类型
     */
    public enum CALLBACK_TYPE {
        OK, CNACEL
    }
```
>调用显示方法
```Java
    /**
         * 显示警告弹窗(A类型警告弹窗)
         * @param _context
         * @param _achor
         * @param _title - 标题
         * @param _message - 提示消息
         * @param _nagative - 左侧文本框按钮内容
         * @param _positive - 右侧文本框按钮内容
         * @param _allColor - 主题颜色 - 目前针对左右按钮
         * @param _bCancelDismiss - 点击取消是否弹窗直接消失
         * @param _onAlertClickListenner
         * @return
         */
        public static BasePop.Builder showALertTypeA(Context _context, View _achor,
                                                     String _title, String _message,
                                                     String _nagative, String _positive,
                                                     String _allColor, boolean _bCancelDismiss,
                                                     OnEventListenner.OnAlertClickListenner _onAlertClickListenner) {
        }
    
        /**
         * 显示警告弹窗(A类型警告弹窗)
         * @param _context
         * @param _achor
         * @param _title - 标题
         * @param _message - 提示消息
         * @param _allColor - 主题颜色 - 目前针对左右按钮
         * @param _onAlertClickListenner
         * @return
         */
        public static BasePop.Builder showALertTypeA(Context _context, View _achor,
                                                     String _title, String _message,
                                                     String _allColor,
                                                     OnEventListenner.OnAlertClickListenner _onAlertClickListenner) {
        }
        /**
          * 显示警告弹窗(A类型警告弹窗) - 只有确定按钮
          * @param _context
          * @param _achor
          * @param _title - 标题
          * @param _message - 提示消息
          * @param _allColor - 主题颜色 - 目前针对左右按钮
          * @param _bOkDismiss - 点击确定是否消失弹窗
          * @param _onAlertClickListenner
          * @return
          */
          public static BasePop.Builder showALertTypeA(Context _context, View _achor,
                                                        String _title, String _message,
                                                        String _allColor, boolean _bOkDismiss,
                                                        OnEventListenner.OnAlertClickListenner _onAlertClickListenner) {
          }
          
        /**
         * 显示原生简洁弹窗
         *
         * @param _context
         * @param _title                  - 标题
         * @param _message                - 提示消息
         * @param _nagative               - 左侧文本框按钮内容 - 可以传null，默认不显示左侧按钮
         * @param _positive               - 右侧文本框按钮内容- 可以传null，默认不显示右侧按钮
         * @param _allColor               - 统一主题颜色 - 可以传null，默认系统颜色
         * @param bCanceledOnTouchOutside - 点击外部是否消失
         * @param _onAlertClickListenner
         * @return
         */
        public static AlertDialog showOriginAlert(Context _context,
                                                  String _title, String _message,
                                                  String _nagative, String _positive,
                                                  String _allColor, boolean bCanceledOnTouchOutside,
                                                  OnEventListenner.OnAlertClickListenner _onAlertClickListenner) {
        }
```
# USE 
```Java   
BasePop.Builder builder = AlertPopView.showALertTypeA(this, view,
                "提示", "是否确认删除!", "#f0008DCF", new OnEventListenner.OnAlertClickListenner() {
                    @Override
                    public void onClick(View view, AlertPopView.CALLBACK_TYPE callback_type) {

                    }
                });
BasePop.Builder builder2 = AlertPopView.showALertTypeA(this, view,
                "提示", "是否确认删除!", "#f0008DCF",
                 true, new OnEventListenner.OnAlertClickListenner() {
                     @Override
                     public void onClick(View view, AlertPopView.CALLBACK_TYPE callback_type) {
                    
                     }
                 });
                ///< 颜色可以不设置，默认系统颜色
AlertDialog   alertDialog = AlertPopView.showOriginAlert(this,
                        "升级啦！", "1.我是皮皮虾\n2.我是大皮皮虾\n2.xx银行就是这样的弹窗，蛮清爽的.",
                        "取消", "确定",
                        "#f0008DCF",false,
                        new OnEventListenner.OnAlertClickListenner() {
                            @Override
                            public void onClick(View view, AlertPopView.CALLBACK_TYPE callback_type) {

                            }
                        });
                ///< 可以nagative、postive，那样则不显示确定取消按钮
AlertDialog    alertDialog2 = AlertPopView.showOriginAlert(this,
                        null, null,
                        null, null,
                        null, true,
                        new OnEventListenner.OnAlertClickListenner() {
                            @Override
                            public void onClick(View view, AlertPopView.CALLBACK_TYPE callback_type) {

                            }
                        });
```