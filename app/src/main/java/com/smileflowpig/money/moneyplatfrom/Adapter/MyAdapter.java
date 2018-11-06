package com.smileflowpig.money.moneyplatfrom.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.smileflowpig.money.moneyplatfrom.activities.DetailActivity;

import java.util.List;

import com.smileflowpig.money.R;
import com.smileflowpig.money.factory.bean.HomedataBean;

/**
 * Created by 小狼 on 2018/11/2.
 */

public class MyAdapter extends PagerAdapter {

    private Context context;
    private List<HomedataBean.RstBean.DataBean> list;

    public MyAdapter(Context context, List<HomedataBean.RstBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.myvp_layout, null);
        ImageView icon = (ImageView) inflate.findViewById(R.id.close_icon);
        TextView name = (TextView) inflate.findViewById(R.id.close_name);
        TextView money = (TextView) inflate.findViewById(R.id.close_money);
        TextView goset = (TextView) inflate.findViewById(R.id.close_to);
        Glide.with(context).load(list.get(position%list.size()).icon).into(icon);
        name.setText(list.get(position%list.size()).name);
        money.setText(list.get(position%list.size()).upper_amount+"-"+list.get(position%list.size()).lower_amount);
        goset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailActivity.show(context, list.get(position%list.size()).id+"","notice",0);

            }
        });
        container.addView(inflate);
        return inflate;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
