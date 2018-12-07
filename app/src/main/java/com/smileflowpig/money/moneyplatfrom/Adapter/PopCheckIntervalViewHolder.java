package com.smileflowpig.money.moneyplatfrom.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.smileflowpig.money.R;


/**
 * Created by xieshengqi on 2018/7/24.
 */

public class PopCheckIntervalViewHolder extends BaseQuViewHolder {
    public TextView name_text;
    public ImageView imageView;

    public PopCheckIntervalViewHolder(View itemView) {
        super(itemView);
        name_text = (TextView) itemView.findViewById(R.id.item_popcheck_name);
        imageView = (ImageView) itemView.findViewById(R.id.item_popcheck_image);
    }
}
