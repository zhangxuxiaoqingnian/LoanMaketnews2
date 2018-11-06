package com.smileflowpig.money.moneyplatfrom.util;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by 小狼 on 2018/4/12.
 */

public class DisplayUtils6 {

    public static class SpacesItemDecoration extends RecyclerView.ItemDecoration {

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

            //不是第一个的格子都设一个左边和底部的间距
            int pos = parent.getChildLayoutPosition(view);
            int i = state.getItemCount() - 1;

            if(pos==i){
                outRect.left = 40;
                outRect.right = 0;
            }else {
                if (pos % 2 == 1) {  //下面一行

                    outRect.left = 40;
                    outRect.right = 40;
                } else { //上面一行
                    if (pos == 0) {
                        outRect.left = 40;
                        outRect.right = 40;
                    } else {
                        outRect.left = 40;
                        outRect.right = 40;
                    }


                }
            }
        }
    }
}
