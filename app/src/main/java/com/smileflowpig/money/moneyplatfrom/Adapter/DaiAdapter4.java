package com.smileflowpig.money.moneyplatfrom.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import com.smileflowpig.money.R;
import com.smileflowpig.money.factory.bean.HomedataBean;

/**
 * Created by 小狼 on 2018/10/19.
 */

public class DaiAdapter4 extends RecyclerView.Adapter<DaiAdapter4.MyViewHolder> {

    private Context context;
    private getItemposition getItemposition;
   private List<HomedataBean.RstBean.DataBean> list;
   private boolean isture=false;

    public DaiAdapter4(Context context, List<HomedataBean.RstBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.daiadapter_layout, null);
        MyViewHolder myViewHolder=new MyViewHolder(inflate);
        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getItemposition!=null)
                getItemposition.success(position);
            }
        });

        Glide.with(context).load(list.get(position).icon).into(holder.img);
        holder.name.setText(list.get(position).name);
        holder.jieshao.setText(list.get(position).description);
       holder.money.setText(list.get(position).upper_amount+"-"+list.get(position).lower_amount+"元");
        holder.lv.setText(list.get(position).monthly_rate+"%");
        holder.man.setText(list.get(position).applicants);

        holder.shen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getItemposition.request(position);
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView img;
        private final TextView lv;
        private final TextView man;
        private final TextView money;
        private final TextView name;
        private final TextView jieshao;
        private final TextView shen;
        private final CheckBox check;
        private final LinearLayout layout;

        public MyViewHolder(View itemView) {
            super(itemView);

            img = (ImageView) itemView.findViewById(R.id.dai_img);
            lv = (TextView) itemView.findViewById(R.id.dai_lv);
            man = (TextView) itemView.findViewById(R.id.dai_man);
            money = (TextView) itemView.findViewById(R.id.dai_money);
            name = (TextView) itemView.findViewById(R.id.dai_name);
            jieshao = (TextView) itemView.findViewById(R.id.dai_shao);
            shen = (TextView) itemView.findViewById(R.id.dai_shen);
            layout = (LinearLayout) itemView.findViewById(R.id.adcb);
            check = (CheckBox) itemView.findViewById(R.id.adaptercb);

        }
    }

    public interface getItemposition{

        void success(int pos);
        void request(int pos);
    }
    public void setItemposition(getItemposition getItemposition){
        this.getItemposition=getItemposition;
    }
}
