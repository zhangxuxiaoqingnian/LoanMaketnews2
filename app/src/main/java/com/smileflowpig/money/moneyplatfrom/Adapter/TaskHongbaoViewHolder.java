package com.smileflowpig.money.moneyplatfrom.Adapter;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.smileflowpig.money.R;
import com.zhy.autolayout.utils.AutoUtils;

class TaskHongbaoViewHolder extends BaseQuViewHolder {

    public TextView task_name_text, title_text, money_text, setting_text;
    public ImageView title_image, loadding_img;
    public LinearLayout title_layout;
    public View line_view;

    public FrameLayout frame_layout;


    public TaskHongbaoViewHolder(View view) {
        super(view);
        frame_layout = (FrameLayout) view.findViewById(R.id.item_task_hongbao_layout);
        task_name_text = (TextView) view.findViewById(R.id.item_task_hongbao_name_text);
        title_text = (TextView) view.findViewById(R.id.item_task_hongbao_titlename_text);
        money_text = (TextView) view.findViewById(R.id.item_task_hongbao_money_text);
        setting_text = (TextView) view.findViewById(R.id.item_task_hongbao_setting_text);
        title_image = (ImageView) view.findViewById(R.id.item_task_hongbao_imageView);
        title_layout = (LinearLayout) view.findViewById(R.id.item_task_hongbao_title_layout);
        line_view = view.findViewById(R.id.item_task_hongbao_title_view);
        loadding_img = (ImageView) view.findViewById(R.id.item_task_hongbao_loadding_img);
    }
}
