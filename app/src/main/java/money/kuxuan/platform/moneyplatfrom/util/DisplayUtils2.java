package money.kuxuan.platform.moneyplatfrom.util;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by 小狼 on 2018/4/12.
 */

public class DisplayUtils2 {

    public static class SpacesItemDecoration extends RecyclerView.ItemDecoration {

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

            //不是第一个的格子都设一个左边和底部的间距
            int pos = parent.getChildLayoutPosition(view);
                if (pos % 2 == 1) {  //下面一行

                        outRect.bottom = 30;
                        outRect.top = 30;

                }
                else { //上面一行
                    if(pos==0){
                        outRect.bottom = 30;
                        outRect.top = 0;
                    }else {
                        outRect.top = 30;
                        outRect.bottom = 30;
                    }


                }

        }
    }
}
