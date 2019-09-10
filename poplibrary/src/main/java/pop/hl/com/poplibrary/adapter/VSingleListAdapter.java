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

/*
*@Description: 垂直列表弹窗适配器
*@Author: hl
*@Time: 2019/2/15 14:21
*/
public class VSingleListAdapter extends RecyclerView.Adapter<VSingleListAdapter.ViewHolder>{
    private WeakReference<Context> contextWeakReference;
    private List<String> vDataList = null;
    private OnEventListenner.OnVListClickListenner onVListClickListenner = null;
    private int itemTextColor;

    public VSingleListAdapter(Context _context, List<String> _vDataList,
                              int _itemTextColor,
                              OnEventListenner.OnVListClickListenner _onVListClickListenner){
        this.contextWeakReference = new WeakReference<>(_context);
        this.vDataList = _vDataList;
        this.itemTextColor = _itemTextColor;
        this.onVListClickListenner = _onVListClickListenner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(contextWeakReference.get()).
                inflate(R.layout.pop_vsingle_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int pos) {
        ///< 获取数据
        String title = (null == vDataList) ? null : vDataList.get(pos);
        ///< 绑定数据
        viewHolder.bindData(title, pos, itemTextColor, onVListClickListenner);
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
            this.textView = itemView.findViewById(R.id.pvsi_titleTv);
        }
        public void bindData(String _name, final int pos,
                             int _itemTextColor,
                             final OnEventListenner.OnVListClickListenner _onVListClickListenner) {
            textView.setTextColor(_itemTextColor);
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
