package pop.hl.com.poplibrary.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.List;

import pop.hl.com.poplibrary.SharePopView;
import pop.hl.com.poplibrary.OnEventListenner;
import pop.hl.com.poplibrary.R;
import pop.hl.com.poplibrary.utils.DensityUtil;
import pop.hl.com.poplibrary.utils.ScreenUtil;

/*
*@Description: 底部分享弹窗适配器
*@Author: hl
*@Time: 2019/2/15 14:21
*/
public class ShareBorderAdapter extends RecyclerView.Adapter<ShareBorderAdapter.ViewHolder>{
    private WeakReference<Context> contextWeakReference;
    private List<String> share2Name = null;
    private List<Integer> share2Icon = null;
    private OnEventListenner.OnShareClickListenner onShareClickListenner = null;
    private SharePopView.SHARE_TYPE share_type;
    private SharePopView.SHOW_TYPE show_type;

    public ShareBorderAdapter(Context _context, List<String> _share2Name, List<Integer> _share2Icon,
                              SharePopView.SHARE_TYPE _share_type,
                              SharePopView.SHOW_TYPE _show_type,
                              OnEventListenner.OnShareClickListenner _onShareClickListenner){
        this.contextWeakReference = new WeakReference<>(_context);
        this.share2Name = _share2Name;
        this.share2Icon = _share2Icon;
        this.share_type = _share_type;
        this.show_type = _show_type;
        this.onShareClickListenner = _onShareClickListenner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        int layoutId = R.layout.pop_bottom_shareborder_item;
        if (SharePopView.SHOW_TYPE.GRID == show_type){
            layoutId = R.layout.pop_bottom_shareborder_grid_item;
        }
        View view = LayoutInflater.from(contextWeakReference.get()).
                inflate(layoutId, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int pos) {
        ///< 获取数据
        String title = (null == share2Name) ? null : share2Name.get(pos);
        int icon = (null == share2Icon) ? -1 : share2Icon.get(pos);
        ///< 绑定数据
        viewHolder.bindData(contextWeakReference.get(), share2Icon.size(),
                title, icon, pos, share_type, show_type,
                onShareClickListenner);
    }

    @Override
    public int getItemCount() {
        return (null == share2Icon) ? 0 : share2Icon.size();
    }

    /*
    *@Description: 底部弹窗内容持有器
    *@Author: hl
    *@Time: 2019/2/15 15:24
    */
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView textView;
        public View itemView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            this.imageView = itemView.findViewById(R.id.pbsi_share2Icon);
            this.textView = itemView.findViewById(R.id.pbsi_share2Title);
        }
        public void bindData(final Context _context, final int _count, String _name,
                             int _drawableId, final int pos,
                             final SharePopView.SHARE_TYPE _share_type, SharePopView.SHOW_TYPE _show_type,
                             final OnEventListenner.OnShareClickListenner _onShareClickListenner) {
            if (null != _name){
                imageView.setImageDrawable(_context.getResources().getDrawable(_drawableId));
            }
            if (-1 != _drawableId){
                textView.setText(_name);
            }
            ///< 点击事件回调
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != _onShareClickListenner){
                        _onShareClickListenner.onClick(v, pos);
                    }
                }
            });
            ///< 最大为5个条目的情况下，均分屏幕宽度；其他情况，则SpacesItemDecoration设置的间距即可！
            if (_count < 6 && (_show_type == SharePopView.SHOW_TYPE.HORIZON)){
                ///< 设置Margin
                itemView.post(new Runnable() {
                    @Override
                    public void run() {
                        ///< 屏幕宽度 - n个控件的宽度和 - SpacesItemDecoration设置的间距 / (n + 1)个控件个数 = 需要设置的控件的真实的margin
                        int leftMargin = (ScreenUtil.getScreenW(_context) - itemView.getMeasuredWidth() * _count - 20 * _count * 2) / (_count + 1);
                        switch (_share_type){
                            case ME:
                                break;
                            case F_TENCENT:
                                leftMargin = (ScreenUtil.getScreenW(_context) - itemView.getMeasuredWidth() * _count - 20 * _count * 2 - DensityUtil.dip2px(_context, 32)) / (_count + 1);
                                break;
                        }
                        ScreenUtil.setMargin(itemView, leftMargin, -10000, -10000, -10000);
                    }
                });
            }
        }
    }
}
