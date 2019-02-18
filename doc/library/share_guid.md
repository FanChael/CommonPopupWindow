# API说明-提供调用类SharePopView.java
>目前提供两种显示样式
```Java
    /**
     * 分享弹窗样式
     * ME - 自家的
     * TENCENT - 仿腾讯
     */
    public enum SHARE_TYPE{
        ME, F_TENCENT
    }
```
>分享列表提供横向滑动+网格类型
```Java
    /**
     * 分享列表显示模式
     * HORIZON - 水平滑动 Item's Count > 5个的情况下
     * GRID - 网格 5列
     */
    public enum SHOW_TYPE{
        HORIZON, GRID
    }
```
>调用显示方法
```Java
    /**
     * 显示分享弹窗 - 默认大于5个方可左右滑动
     * @param _context
     * @param _achor
     * @param _share2Name - 如果传null，默认提供朋友圈、微信、qq、新浪、复制链接
     * @param _share2Icon
     * @param _simple_gravity - 支持从下到上以及从上到下显示方式
     * @param _show_type
     * @param _onShareClickListenner
     * @return
     */
    public static BasePop.Builder showShare(Context _context, View _achor,
                                 List<String> _share2Name, List<Integer> _share2Icon,
                                 BasePopView.SIMPLE_GRAVITY _simple_gravity,
                                 SharePopView.SHOW_TYPE _show_type,
                                 OnEventListenner.OnShareClickListenner _onShareClickListenner){
        return new Builder(_context)
                .create(_achor, SHARE_TYPE.ME)
                .setTitleAndIcon(_share2Name, _share2Icon)
                .showShareBorder(_simple_gravity, _show_type, _onShareClickListenner);
    }

    /**
     * 显示分享弹窗 - 默认大于5个方可左右滑动
     * @param _context
     * @param _achor
     * @param _share2Name- 如果传null，默认提供朋友圈、微信、qq、新浪、复制链接
     * @param _share2Icon
     * @param _simple_gravity- 支持从下到上以及从上到下显示方式
     * @param _show_type
     * @param _onShareClickListenner
     * @return
     */
    public static BasePop.Builder showShareFTencent(Context _context, View _achor,
                                            List<String> _share2Name, List<Integer> _share2Icon,
                                            BasePopView.SIMPLE_GRAVITY _simple_gravity,
                                            SharePopView.SHOW_TYPE _show_type,
                                            OnEventListenner.OnShareClickListenner _onShareClickListenner){
        return new Builder(_context)
                .create(_achor, SHARE_TYPE.F_TENCENT)
                .setTitleAndIcon(_share2Name, _share2Icon)
                .showShareBorder(_simple_gravity, _show_type, _onShareClickListenner);
    }
```
# USE 
a. 自家样式(列表随机为水平或者网格 标题和图标不传默认给五个基本分享平台)
```Java
    List<String> _share2Name = new ArrayList<>();
                _share2Name.add("华为");
                _share2Name.add("阿里");
                _share2Name.add("小米");
                _share2Name.add("毛豆");
                _share2Name.add("无聊");
                _share2Name.add("华为");
                _share2Name.add("阿里");
                _share2Name.add("小米");
                _share2Name.add("毛豆");
                _share2Name.add("无聊");
    List<Integer> _share2Icon = new ArrayList<>();
                _share2Icon.add(R.drawable.huawei);
                _share2Icon.add(R.drawable.ali);
                _share2Icon.add(R.drawable.xiaomi);
                _share2Icon.add(R.drawable.moredo);
                _share2Icon.add(R.drawable.share_link);
                _share2Icon.add(R.drawable.huawei);
                _share2Icon.add(R.drawable.ali);
                _share2Icon.add(R.drawable.xiaomi);
                _share2Icon.add(R.drawable.moredo);
                _share2Icon.add(R.drawable.share_link);

    ///< 点击事件回调
    OnEventListenner.OnShareClickListenner onShareClickListenner = new OnEventListenner.OnShareClickListenner() {
        @Override
        public void onClick(View view, int pos) {
            Toast.makeText(MainActivity.this, "pos=" + pos, Toast.LENGTH_SHORT).show();
        }
    };
    ///< 显示链表添加的分享图标
    int randomValue = new Random().nextInt(2);
    BasePop.Builder builder = SharePopView.showShare(this, view,
                randomValue == 1 ? _share2Name : null,
                randomValue == 1 ? _share2Icon : null,
                (new Random().nextInt(2)) == 1 ?
                        BasePopView.SIMPLE_GRAVITY.FROM_TOP :
                        BasePopView.SIMPLE_GRAVITY.FROM_BOTTOM,
                (new Random().nextInt(2)) == 1 ?
                        SharePopView.SHOW_TYPE.HORIZON :
                        SharePopView.SHOW_TYPE.GRID,
                onShareClickListenner);
                
    
```
b. 仿腾讯样式(列表随机为水平或者网格 标题和图标不传默认给五个基本分享平台)
```Java
    List<String> _share2Name = new ArrayList<>();
                _share2Name.add("华为");
                _share2Name.add("阿里");
                _share2Name.add("小米");
                _share2Name.add("毛豆");
                _share2Name.add("无聊");
                _share2Name.add("华为");
                _share2Name.add("阿里");
                _share2Name.add("小米");
                _share2Name.add("毛豆");
                _share2Name.add("无聊");
    List<Integer> _share2Icon = new ArrayList<>();
                _share2Icon.add(R.drawable.huawei);
                _share2Icon.add(R.drawable.ali);
                _share2Icon.add(R.drawable.xiaomi);
                _share2Icon.add(R.drawable.moredo);
                _share2Icon.add(R.drawable.share_link);
                _share2Icon.add(R.drawable.huawei);
                _share2Icon.add(R.drawable.ali);
                _share2Icon.add(R.drawable.xiaomi);
                _share2Icon.add(R.drawable.moredo);
                _share2Icon.add(R.drawable.share_link);

    ///< 点击事件回调
    OnEventListenner.OnShareClickListenner onShareClickListenner = new OnEventListenner.OnShareClickListenner() {
        @Override
        public void onClick(View view, int pos) {
            Toast.makeText(MainActivity.this, "pos=" + pos, Toast.LENGTH_SHORT).show();
        }
    };
    ///< 显示链表添加的分享图标
    int randomValue = new Random().nextInt(2);
    BasePop.Builder builder = SharePopView.showShareFTencent(this, view,
                randomValue == 1 ? _share2Name : null,
                randomValue == 1 ? _share2Icon : null,
                BasePopView.SIMPLE_GRAVITY.FROM_BOTTOM,
                (new Random().nextInt(2)) == 1 ?
                        SharePopView.SHOW_TYPE.HORIZON :
                        SharePopView.SHOW_TYPE.GRID,
                onShareClickListenner);
```