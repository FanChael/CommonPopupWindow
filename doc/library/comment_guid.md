# API说明-提供调用类CommentViewjava
>调用显示方法
```Java
/**
     * 显示评论弹窗
     * @param _context
     * @param _texthint - 输入框提示文本
     * @param _hisMsg - 输入框历史输入文本(需要自己保存之前的输入)
     * @param _allColor - 主题颜色(主要是发送按钮的颜色)
     * @param _bShowProgress - 点击发送是否显示系统的进度条(可以自己定义进度条以及显示逻辑)
     *                         - 关闭弹窗内部会处理进度弹窗消失
     * @param _sendBackListener - 点击发送回调事件(返回文本信息)
     * @return
     */
    public static BasePop.Builder showComment(Context _context,
                                              String _texthint, String _hisMsg,
                                              String _allColor, boolean _bShowProgress,
                                              OnEventListenner.SendBackListener _sendBackListener) {
    }
```
# USE 
```Java
BasePop.Builder builder = CommentView.showComment(this,
                        "输入评论呀", "啦啦啦，我是卖报的小行家！"/*上一次的记录*/,
                        "#FFf5c5c0", true,
                        new OnEventListenner.SendBackListener() {
                    @Override
                    public void sendBack(String inputText) {

                    }
                });
                // builder.dissmiss();             
```

# 注意事项
> 目前暂时没有做表情的选择，后面尝试搞搞; 
