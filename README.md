# CommonPopupWindow - APP更新模块
Popular popupwindow in the market and customized.  
[ ![Download](https://api.bintray.com/packages/resetmyself/holdon/commonpop/images/download.svg?version=1.0.1.4) ](https://bintray.com/resetmyself/holdon/commonpop/1.0.1.4/link)

（结合市面流行的弹窗样式+支持自定义布局）. 
# Description
>poplibrary引入即可调用弹窗

>(其他配置待续)

# Feature  

>支持自定义布局(所有点击事件统一回调)  

>更新弹窗(目前经典样式)

>基于更新弹窗+Rx家族的App更新模块1.0.1.4(只包含自定义1.0.1+更新弹窗1.0.4)

# Effect  
~V1.0.1.4  
- 2019.02.22 -  - 添加App更新模块  
![方位展示](https://github.com/FanChael/CommonPopupWindow/blob/appupdate/doc/2019.02.22_updateapp.gif) 

##### [Demo apk下载](https://github.com/FanChael/CommonPopupWindow/blob/master/doc/app_update.apk)

# Import Library  
>For gradle:  
```Java
//1.0.1 自定义
implementation 'com.hl:poplibrary:1.0.1'
//1.0.4 更新弹窗模块
implementation 'com.hl:poplibrary:1.0.1.4'
   
///< retrofit+rxjava - 下载处理
implementation 'com.squareup.retrofit2:retrofit:2.3.0'
implementation 'io.reactivex.rxjava2:rxjava:2.1.10'
implementation 'com.squareup.retrofit2:adapter-rxjava:2.3.0'
///< eventbus3.0 - 进度通知
implementation 'org.greenrobot:eventbus:3.0.0'
```
>Or in maven:
```Java
<dependency>
    <groupId>com.hl</groupId>
    <artifactId>poplibrary</artifactId>
    <version>1.0.x</version>
    <type>pom</type>
</dependency>
```
# 传送门  
> 使用
* [更新弹窗使用](https://github.com/FanChael/CommonPopupWindow/blob/master/doc/library/updagrade_guid.md)
* [APP更新使用](https://github.com/FanChael/CommonPopupWindow/blob/appupdate/doc/library/appupdate_guid.md)

> Hold on！
* [更新日志](https://github.com/FanChael/CommonPopupWindow/blob/appupdate/doc/library/update_guid.md)
* [学习博客](https://github.com/FanChael/CommonPopupWindow/blob/master/doc/library/study_guid.md)
