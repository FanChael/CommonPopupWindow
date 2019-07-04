package pop.hl.com.poplibrary.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
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
import pop.hl.com.poplibrary.utils.DensityUtil;
import pop.hl.com.poplibrary.utils.ShapeUtil;

/*
*@Description: 垂直列表弹窗适配器
*@Author: hl
*@Time: 2019/2/15 14:21
*/
public class ItemSelectAdapter extends RecyclerView.Adapter<ItemSelectAdapter.ViewHolder>{
    private WeakReference<Context> contextWeakReference;
    private List<String> vDataList = null;
    String fillColor, fontColor;
    private OnEventListenner.OnItemSelectClickListenner onItemSelectClickListenner = null;

    public ItemSelectAdapter(Context _context, List<String> _vDataList,
                             String _fillColor, String _fontColor,
                             OnEventListenner.OnItemSelectClickListenner  _onItemSelectClickListenner){
        this.contextWeakReference = new WeakReference<>(_context);
        this.vDataList = _vDataList;
        this.fillColor = _fillColor;
        this.fontColor = _fontColor;
        this.onItemSelectClickListenner = _onItemSelectClickListenner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(contextWeakReference.get()).
                inflate(R.layout.pop_item_select_item, parent, false);
        return new ViewHolder(contextWeakReference.get(), view, fillColor, fontColor);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int pos) {
        ///< 获取数据
        String title = (null == vDataList) ? null : vDataList.get(pos);
        ///< 绑定数据
        viewHolder.bindData(title, pos, onItemSelectClickListenner);
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
        private String fillColor;
        private String fontColor;
        private TextView textView;
        public View itemView;

        public ViewHolder(@NonNull Context _context, View itemView,  String _fillColor, String _fontColor) {
            super(itemView);
            this.fillColor = _fillColor;
            this.fontColor = _fontColor;
            this.itemView = itemView;
            if (null != fillColor && !fillColor.equals("")){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    itemView.setBackground(ShapeUtil.createShape(-1, DensityUtil.dip2px(_context, 6), -1, null, fillColor));
                } else {
                    itemView.setBackgroundColor(Color.parseColor(fillColor));
                }
            }
            this.textView = itemView.findViewById(R.id.pisi_titleTv);
            if (null != fontColor && !fontColor.equals("")){
                textView.setTextColor(Color.parseColor(fontColor));
            }
        }

        public void bindData(String _name, final int pos,
                             final OnEventListenner.OnItemSelectClickListenner  _onItemSelectClickListenner) {
            textView.setText(_name);
            ///< 点击事件回调
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != _onItemSelectClickListenner){
                        _onItemSelectClickListenner.onClick(v, textView.getText().toString(), pos);
                    }
                }
            });
        }
    }
}
