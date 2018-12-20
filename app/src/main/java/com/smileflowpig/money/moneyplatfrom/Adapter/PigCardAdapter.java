package com.smileflowpig.money.moneyplatfrom.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.smileflowpig.money.R;
import com.smileflowpig.money.factory.bean.CardBean;

import java.util.List;

/**
 * Created by 小狼 on 2018/12/13.
 */

public class PigCardAdapter extends RecyclerView.Adapter<PigCardAdapter.MyViewHolder> {

    private List<CardBean.RstBean.ListBean> list;
    private Context context;
    private getItemshap getItemshap;

    public PigCardAdapter(List<CardBean.RstBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.pigcard_layout, null);
        MyViewHolder myViewHolder=new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        Glide.with(context).load(list.get(position).card_icon).into(holder.pigimg);
        holder.title.setText(list.get(position).name);
        holder.destroy.setText(list.get(position).desc);
        int apply_num = list.get(position).apply_num;
        double v = Double.parseDouble(String.valueOf(apply_num));
        double v1 = v / 10000;
        String format = String.format("%.1f", v1);
        holder.cont.setText(format+"万人");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getItemshap.success(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView cont;
        private final TextView destroy;
        private final TextView title;
        private final ImageView pigimg;

        public MyViewHolder(View itemView) {
            super(itemView);

            pigimg = (ImageView) itemView.findViewById(R.id.pig_img);
            title = (TextView) itemView.findViewById(R.id.pig_title);
            destroy = (TextView) itemView.findViewById(R.id.pig_destroy);
            cont = (TextView) itemView.findViewById(R.id.pig_num);
        }
    }
    public interface getItemshap{

        void success(int pos);
    }
    public void setItemshap(getItemshap getItemshap){
        this.getItemshap=getItemshap;
    }
}
