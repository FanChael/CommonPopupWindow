# API说明-提供调用类VListPopView.java
>调用显示方法
```Java
     /**
         * 显示单列表弹窗
         *
         * @param _context
         * @param _anchor
         * @param _vListData
         * @param _itemTextColor - item字体颜色
         * @param _onVListClickListenner
         * @return
         */
        public static BasePop.Builder showVSingleListPop(Context _context, View _anchor, List<String> _vListData,
                                                         int _itemTextColor,
                                                         OnEventListenner.OnVListClickListenner _onVListClickListenner) {
        }
```
# USE 
```Java
   List<String> vlistData = new ArrayList<>();
                   vlistData.add("高德");
                   vlistData.add("百度");
   BasePop.Builder builder = VSingleListPopView.showVSingleListPop(this, view,
                           vlistData, 0xf0008DCF, new OnEventListenner.OnVListClickListenner() {
                               @Override
                               public void onClick(View view, int pos) {
                                   if (null != builder){
                                       builder.dissmiss();
                                   }
                               }
                           });
```