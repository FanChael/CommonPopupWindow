# API说明-提供调用类AppUpdate
>调用显示方法
```Java
    /**
     *  创建更新弹窗参数
     * @param _context
     * @param _achor
     * @param _titleBgId - 弹窗抬头图片资源
     * @param _h_dived_w - 弹窗抬头图片资源h/w
     * @param _allColor - 弹窗主体颜色
     * @param _bforce - 是否强制更新
     * @param _updateMessage - 更新信息，多行换行
     */
   public AppUpdate(Context _context, View _achor,
                        int _titleBgId, float _h_dived_w,
                        String _allColor, boolean _bforce,
                        String _updateMessage) {
       }
   
          /**
           * 启动更新App
           * @param _appUrl - apk下载地址
           * @param _notifyIcon - 标题栏通知图标
           * @param _fileProvider - 应用的fileProvider(下载和启动安装使用)
           * @param _md5  - apk包的md5 - 防止重复下载
           * @param _versionCode apk包的_versionCode - 防止重复下载
           * @param _apkSize apk包的大小 - 防止重复下载
           */
       public void startAppUpdate(final String _appUrl, final int _notifyIcon,
                                  final String _fileProvider,
                                  final String _md5, final int _versionCode, final long _apkSize) {
       }
```

# USE 
```Java
        ///< 设置更新弹窗样式+升级信息
        AppUpdate appUpdate = new AppUpdate(this, view, R.drawable.update_bg_app_top, 204.0f/450.0f,
                "#FF5C5C", (new Random().nextInt(2)) == 1 ? true : false,
                "1、新增皮皮虾板块\n" + "2、新增皮皮狗板块\n"+ "3、新增皮皮你板块");
        ///< 开启更新，设置apk下载地址+通知栏图标+fileProvider直接启动安装+(apk的md5、apk的versionCode、apk大小)进行已经下载安装包的校验，防止重复下载
        appUpdate.startAppUpdate("https://raw.githubusercontent.com/FanChael/CommonPopupWindow/appupdate/doc/app_update.apk",
                        R.drawable.share_circle,
                        "pop.hl.com.commonpopupwindow.fileProvider",
                        "6FA8D1B09B54580CA69FA7BF62D0C4A7", 1,2978651);
```

# xml关键配置参考
```Java
        <uses-permission android:name="android.permission.INTERNET" />


        <!-- 下载服务 -->
        <service
            android:name="pop.hl.com.poplibrary.update.DownLoadIntentService"
            android:enabled="true"
            android:exported="true" />
        <!-- FileProvider -->
        <provider
            android:name="android.support.v4.content.FileProvider"
            android:authorities="pop.hl.com.commonpopupwindow.fileProvider"
            android:exported="false"
            android:grantUriPermissions="true">
            <meta-data
                android:name="android.support.FILE_PROVIDER_PATHS"
                android:resource="@xml/file_paths" />
        </provider>
```
##### file_paths.xml
```Java
<?xml version="1.0" encoding="utf-8"?>
<paths>
    <external-path path="Android/data/pop.hl.com.commonpopupwindow/" name="files_root" />
    <external-path path="." name="external_storage_root" />
</paths>
```

# 特别说明
> R.drawable.update_bg_app_top的样式(顶部部分透明)建议如下比较好看 
![方位展示](https://github.com/FanChael/CommonPopupWindow/blob/appupdate/doc/update_bg_app_top.png)  
 
> 图片的显示高度是根据传入的图片的比例(204/450 = 图片的高度/宽度)进行设置的，所以才不会变形  

> 主体的颜色值建议与置顶的主题图片配色一致比较协调  

> AppUpdate的startAppUpdate方法的后三个参数(从后台获取，用作校验apk包)可以传null,-1,-1等，每次点击更新都重新下载;