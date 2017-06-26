package com.github.Util.UI;

import android.support.v7.widget.RecyclerView;

/**
 * Created by axnshy on 2017/6/24.
 */

public class RecyclerViewUtils {


    public static final int SCROLL_UP = -1;
    public static final int SCROLL_DOWN = 1;
    /**
     * direction 1 为判断是否可以向上滚动
     *
     * @param recyclerview
     * @param direction 1  为判断是否可以向上滚动
     *                 -1  为判断是否可以向下滚动
     * @return 返回 false 为滚动到顶／底了
     */
    public static boolean canScrollVertically(RecyclerView recyclerview,int direction){
        final int offset = recyclerview.computeVerticalScrollOffset();
        final int range = recyclerview.computeVerticalScrollRange() - recyclerview.computeVerticalScrollExtent();
        if(range==0) return false;

        if(direction<0){
            return offset>0;
        }else  {
            return offset<range-1;
        }
    }

}
