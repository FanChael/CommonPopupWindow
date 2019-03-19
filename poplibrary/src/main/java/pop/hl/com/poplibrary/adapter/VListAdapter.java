package pop.hl.com.poplibrary.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.List;

import pop.hl.com.poplibrary.OnEventListenner;
import pop.hl.com.poplibrary.R;
import pop.hl.com.poplibrary.utils.ScreenUtil;

/*
*@Description: 垂直列表弹窗适配器
*@Author: hl
*@Time: 2019/2/15 14:21
*/
public class VListAdapter extends RecyclerView.Adapter<VListAdapter.ViewHolder>{
    private WeakReference<Context> contextWeakReference;
    private List<String> vDataList = null;
    private OnEventListenner.OnVListClickListenner onVListClickListenner = null;

    private boolean bHasBottomLine = true;
    private int revH;

    public VListAdapter(Context _context, List<String> _vDataList,
                        OnEventListenner.OnVListClickListenner _onVListClickListenner){
        this.contextWeakReference = new WeakReference<>(_context);
        this.vDataList = _vDataList;
        this.onVListClickListenner = _onVListClickListenner;
    }

    public VListAdapter(Context _context, List<String> _vDataList,
                        OnEventListenner.OnVListClickListenner _onVListClickListenner,
                        int _revH,
                        boolean _bNoBottomLine){
        this.contextWeakReference = new WeakReference<>(_context);
        this.vDataList = _vDataList;
        this.onVListClickListenner = _onVListClickListenner;
        this.bHasBottomLine = _bNoBottomLine;
        this.revH = _revH;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        if (bHasBottomLine) {
            View view = LayoutInflater.from(contextWeakReference.get()).
                    inflate(R.layout.pop_vlist_item, parent, false);
            return new ViewHolder(view);
        }else{
            View view = LayoutInflater.from(contextWeakReference.get()).
                    inflate(R.layout.pop_vlist_nobottom_line_item, parent, false);
            return new ViewHolder(view, revH);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int pos) {
        ///< 获取数据
        String title = (null == vDataList) ? null : vDataList.get(pos);
        ///< 绑定数据
        viewHolder.bindData(title, pos, onVListClickListenner);
    }

    @Override
    public int getItemCount() {
        return (null == vDataList) ? 0 : vDataList.size();
    }

    /*
    *@Description: 垂直弹窗内容持有器
    *@Author: hl
    *@Time: 2019/2/15 15:24
    */
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public View itemView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            this.textView = itemView.findViewById(R.id.pvi_titleTv);
        }
        public ViewHolder(@NonNull View itemView, int revH) {
            super(itemView);
            this.itemView = itemView;
            this.textView = itemView.findViewById(R.id.pvi_titleTv);
            ///< 设置item的高度
            ScreenUtil.setRecyclerViewWH(itemView, -1, revH);
            ///< 设置item文本的大小 之前高度100对应了 15sp
            textView.setTextSize(revH > 100 ? (revH / 100 + 16) : 16);
        }
        public void bindData(String _name, final int pos,
                             final OnEventListenner.OnVListClickListenner _onVListClickListenner) {
            textView.setText(_name);
            ///< 点击事件回调
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != _onVListClickListenner){
                        _onVListClickListenner.onClick(v, pos);
                    }
                }
            });
        }
    }
}
