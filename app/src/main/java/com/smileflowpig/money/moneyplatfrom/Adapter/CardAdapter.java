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
import com.smileflowpig.money.common.widget.PortraitView;
import com.smileflowpig.money.factory.bean.CardBean;
import com.smileflowpig.money.moneyplatfrom.util.GlideUtil;

import java.util.List;

/**
 * Created by 小狼 on 2018/11/7.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyViewHolder> {

    private Context context;
    private List<CardBean.RstBean.ListBean> list;
    private getItemposition getItemposition;

    public CardAdapter(Context context, List<CardBean.RstBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.cell_creditcard_list, parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.im_portrait.setup(Glide.with(context),list.get(position).card_icon);
        holder.txt_name.setText(list.get(position).name);
        holder.txt_desc.setText(list.get(position).desc);

        holder.txtApplication.setText("申请人数:");
        holder.txt_people_number.setText(list.get(position).apply_num+"");
        holder.txt_rate.setVisibility(View.GONE);
        holder.textqi.setVisibility(View.GONE);
        holder.txt_rate_number.setVisibility(View.GONE);

        int size =0;

        if(list.get(position).keywords!=null){
            size = list.get(position).keywords.size();
        }

        if(size==1){
            if(list.get(position).keywords.get(0).equals("礼")){
                Glide.with(context).load(R.drawable.li).into(holder.iv_first);
            }else if(list.get(position).keywords.get(0).equals("荐")){
                Glide.with(context).load(R.drawable.jian).into(holder.iv_first);
            }
        }else if(size==2){
            Glide.with(context).load(R.drawable.jian).into(holder.iv_first);
            Glide.with(context).load(R.drawable.li).into(holder.iv_second);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getItemposition.success(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView textqi;
        private final ImageView iv_second;
        private final ImageView iv_first;
        private final TextView txtApplication;
        private final TextView txt_name;
        private final PortraitView im_portrait;
        private final TextView txt_desc;
        private final TextView txt_people_number;
        private final TextView txt_rate;
        private final TextView txt_rate_number;

        public MyViewHolder(View itemView) {
            super(itemView);

            txt_rate_number = (TextView) itemView.findViewById(R.id.txt_rate_number);
            txt_rate = (TextView) itemView.findViewById(R.id.txt_rate);
            txt_people_number = (TextView) itemView.findViewById(R.id.txt_people_number);
            txt_desc = (TextView) itemView.findViewById(R.id.txt_desc);
            im_portrait = (PortraitView) itemView.findViewById(R.id.im_portrait);
            txt_name = (TextView) itemView.findViewById(R.id.txt_name);
            txtApplication = (TextView) itemView.findViewById(R.id.application);
            iv_first = (ImageView) itemView.findViewById(R.id.iv_first);
            iv_second = (ImageView) itemView.findViewById(R.id.iv_second);
            textqi = (TextView) itemView.findViewById(R.id.textqi);
        }
    }

    public interface getItemposition{
        void success(int pos);
    }
    public void setItemposition(getItemposition getItemposition){

        this.getItemposition=getItemposition;
    }
}
