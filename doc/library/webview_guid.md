# API说明-提供调用类WebPopView.java
>调用显示方法
```Java
/**
     * 显示Webview承载内容的弹窗
     *
     * @param _context
     * @param _urlOrStr- html文本字符串
     * @return
     */
    public static BasePop.Builder showWebview(Context _context, String _urlOrStr) {
    }

    /**
     * 显示Webview承载内容的弹窗
     *
     * @param _context
     * @param _urlOrStr- html文本字符串
     * @param _closeResourceId- 支持关闭按钮背景资源自定义
     * @return
     */
    public static BasePop.Builder showWebview(Context _context, String _urlOrStr, int _closeResourceId) {
    }

    /**
     * 显示Webview承载内容的弹窗
     *
     * @param _context
     * @param _urlOrStr- html文本字符串
     * @param _closeResourceId- 支持关闭按钮背景资源自定义
     * @param _bScale- 是否带缩放动画
     * @return
     */
    public static BasePop.Builder showWebview(Context _context, String _urlOrStr,
                                              int _closeResourceId, boolean _bScale) {
    }

    /**
     * 显示Webview承载内容的弹窗
     *
     * @param _context
     * @param _urlOrStr- html文本字符串
     * @param _bScale- 是否带缩放动画
     * @return
     */
    public static BasePop.Builder showWebview(Context _context, String _urlOrStr, boolean _bScale) {
    }

    /**
     * 显示Webview承载内容的弹窗
     *
     * @param _context
     * @param _achor
     * @param _urlOrStr- html文本字符串
     * @param _closeResourceId- 支持关闭按钮背景资源自定义
     * @return
     */
    public static BasePop.Builder showWebview(Context _context, View _achor,
                                              String _urlOrStr, int _closeResourceId,
                                              boolean _bScale) {
    }
```
# USE 
```Java
BasePop.Builder builder1 = WebPopView.showWebview(this, "<p style=\"white-space: normal;\"><span style=\"font-family: 宋体; font-size: 14px;\">1、独立根据客户需求完成媒体合作及投放方案；</span></p><p style=\"white-space: normal;\"><span style=\"font-family: 宋体; font-size: 14px;\">2、负责对区域客户的销售工作，拓展新的客户和项目，并有效地形成销售机会；</span></p><p style=\"white-space: normal;\"><span style=\"font-family: 宋体; font-size: 14px;\">3、通过深入了解客户的核心业务，挖掘和寻找潜在的项目机会；</span>");
BasePop.Builder builder2 = WebPopView.showWebview(this, "<div class=\"layui-m-layer-layui-layer-jd\">\r\n    <div class=\"layui-layer-jd-container content\">\r\n        <div class=\"layui-layer-jd-section\">\r\n            <div class=\"layui-layer-jd-title\">岗位职责：</div>\r\n            <div class=\"layui-layer-jd-content\">\r\n                 <p><p>岗位职责<br/></p></p>\r\n            </div>\r\n        </div>\r\n        <div class=\"layui-layer-jd-section\">\r\n            <div class=\"layui-layer-jd-title\">任职要求：</div>\r\n            <div class=\"layui-layer-jd-content\">\r\n                 <p><p>任职要求</p></p>\r\n            </div>\r\n        </div>\r\n    </div>\r\n</div>\r\n", R.drawable.huawei);
BasePop.Builder builder3 = WebPopView.showWebview(this, view, "<p style=\"white-space: normal;\"><span style=\"font-family: 宋体; font-size: 14px;\">1、独立根据客户需求完成媒体合作及投放方案；</span></p><p style=\"white-space: normal;\"><span style=\"font-family: 宋体; font-size: 14px;\">2、负责对区域客户的销售工作，拓展新的客户和项目，并有效地形成销售机会；</span></p><p style=\"white-space: normal;\"><span style=\"font-family: 宋体; font-size: 14px;\">3、通过深入了解客户的核心业务，挖掘和寻找潜在的项目机会；</span>",
                        -1, true);                
```

# 注意事项
> 内容要为html字符串文本格式，内部Webview支持了js等相关配置; 
