# API说明-提供调用类CameraPicPopView.java
>相机相册点击回调
```Java
  /**
     * 点击事件回调类型
     */
    public enum CALLBACK_TYPE {
        PHOTO, CAMERA
    }
```
>调用显示方法
```Java
/**
     * 相册/相机选择
     *
     * @param _context
     * @param _achor
     * @param _funtionbgColor         - 功能区背景颜色
     * @param _funtionTextColor       - 功能选择字体颜色
     * @param _cancelBgColor          - 取消按钮背景颜色
     * @param _cancelTextColor        - 取消按钮字体颜色
     * @param _onCameraClickListenner
     * @return
     */
    public static BasePop.Builder showCamera(Context _context, View _achor,
                                             String _funtionbgColor, String _funtionTextColor,
                                             String _cancelBgColor, String _cancelTextColor,
                                             OnEventListenner.OnCameraClickListenner _onCameraClickListenner) {
        
    }
```
# USE 
```Java
                ///< 相机，相册弹窗 - 分别包含了相机相册按钮区域背景，字体颜色；取消按钮背景和字体颜色；相机相册按钮哪个被按下的回调
                BasePop.Builder builder = CameraPicPopView.showCamera(this, view,
                        "#ffffff", "#000000",
                        "#f2f5f7", "#000000",
                        new OnEventListenner.OnCameraClickListenner() {
                            @Override
                            public void onClick(View view, CameraPicPopView.CALLBACK_TYPE callback_type) {

                            }
                        });
```
# 注意事项
> _funtionbgColor目前效果不好，本来弹窗必读背景色是白色。中间有分割线，如果设置一个其他颜色，可能会不好看！
所以上面的颜色需要比较淡的情况才行。你可以选择不设置。有特殊需求可以issue，再完善； 