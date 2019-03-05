# API说明-提供调用类VListPopView.java
>自定义布局动画增加了伸缩
```Java
     /**
         * 显示动画
         * SCALE - 缩放
         * TRANSLATE - 平移
         * FOLD - 折叠
         */
        public enum ANIMATION {
            NONE, SCALE, TRANSLATE, FOLD
        }
```
>调用显示方法
```Java
    public Builder(Context _context) {
            }
    
    
            /**
             * 垂直列表弹窗显示
             * @param _anchor
             * @return
             */
            public VListPopView.Builder create(View _anchor, int _popH) {
            }
    
            /**
             * 显示垂直列表弹窗
             * @param _vListData
             * @param _onVListClickListenner
             * @return
             */
            public BasePop.Builder show(List<String> _vListData,
                                             OnEventListenner.OnVListClickListenner _onVListClickListenner) {
            }
```
# USE 
```Java
   List<String> buysell_list = new ArrayList<String>();
   buysell_list.add("买卖");
   buysell_list.add("成都");
   buysell_list.add("上海");
   buysell_list.add("深圳");
   buysell_list.add("成都");
   buysell_list.add("成都");     
   BasePop.Builder builder = new VListPopView.Builder(mContext)
                       .create(view, ScreenUtil.getScreenH(mContext) / 6)
                       .show(buysell_list, new OnEventListenner.OnVListClickListenner() {
                           @Override
                           public void onClick(View view, int pos) {
   
                           }
                       });
```