package pop.hl.com.poplibrary.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/*
*@Description: Rv间距
*@Author: hl
*@Time: 2019/2/15 16:37
*/
public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;
    private Context context;
    private int itemNum;

    public SpacesItemDecoration(Context _context, int _itemNum, int space) {
        this.space = space;
        this.itemNum = _itemNum;
        this.context = _context;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        //int position = parent.getChildAdapterPosition(view);
        outRect.left = space;
        outRect.right = space;
        outRect.bottom = space;
        outRect.top = space;

        // Add top margin only for the first item to avoid double space between items
        //if (parent.getChildAdapterPosition(view) == 0)
        //    outRect.top = space;
    }
}
