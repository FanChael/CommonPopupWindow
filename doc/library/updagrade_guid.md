# API说明-提供调用类UpdatePopView
>调用显示方法
```Java
    /**
     * 经典更新弹窗
     * @param _context
     * @param _achor
     * @param _titleBgId - 置顶背景主题图片
     * @param _h_dived_w - 资源图片设置的情况下，图片的高度/宽度
     * @param _allColor - 按钮、进度等主体颜色
     * @param _bforce - 是否强制更新
     * @param _updateMessage - 更新信息
     * @param _onUpdateClickListenner
     * @return
     */
    public static BasePop.Builder showNormalUpdate(Context _context, View _achor,
                                                   int _titleBgId, int _h_dived_w,
                                                   String _allColor, boolean _bforce,
                                                   String _updateMessage,
                                                   OnEventListenner.OnUpdateClickListenner _onUpdateClickListenner) {
        return new Builder(_context)
                .create(_titleBgId, _allColor, _h_dived_w, _bforce, _updateMessage, _achor)
                .showNormalUpdate(_onUpdateClickListenner);
    }
```
# 点击事件回调说明  
```Java
    /**
     * 更新弹窗点击事件回调
     * 1. 如果是强制更新的情况，则会返回进度条控件progressBar给调用者，方便外部设置进度
     * 2. 如果是非强制更新，则不显示进度条控件; 此时显示暂不更新文本按钮
     */
    public interface OnUpdateClickListenner{
        void onClick(View view, View progressBar);
    }
```

# USE 
a. 经典弹窗样式
```Java
UpdatePopView.showNormalUpdate(this, view, ///< 经典更新弹窗
    R.drawable.update_bg_app_top, 204/450,
            "#FF5C5C",  (new Random().nextInt(2)) == 1 ? true : false,
            "1、新增皮皮虾板块\n" + "2、新增皮皮狗板块\n"+ "3、新增皮皮你板块",
            new OnEventListenner.OnUpdateClickListenner() {
        @Override
        public void onClick(View view, View progress) {
            ProgressBar progressBar = (ProgressBar) progress;
            progressBar.setProgress(80);
        }
    });
```

# 特别说明
> R.drawable.update_bg_app_top的样式(顶部部分透明)建议如下比较好看 
![方位展示](https://github.com/FanChael/CommonPopupWindow/blob/master/doc/update_bg_app_top.png)  
 
> 图片的显示高度是根据传入的图片的比例(204/450 = 图片的高度/宽度)进行设置的，所以才不会变形  

> 主体的颜色值建议与置顶的主题图片配色一致比较协调