package com.smileflowpig.money.moneyplatfrom.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import com.smileflowpig.money.R;
import com.smileflowpig.money.factory.bean.HomedataBean;
import com.smileflowpig.money.factory.bean.TabBean;

/**
 * Created by 小狼 on 2018/11/2.
 */

public class TabAdapter extends RecyclerView.Adapter<TabAdapter.MyViewHolder> {

    private Context context;
    private List<HomedataBean.RstBean.DataBean> list;
    private getItempostion getItempostion;

    public TabAdapter(Context context, List<HomedataBean.RstBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.hot_adapter_layout, null);
        MyViewHolder myViewHolder=new MyViewHolder(inflate);
        return myViewHolder;
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        Glide.with(context).load(list.get(position).icon).into(holder.icon);
        holder.name.setText(list.get(position).name);
        holder.destroy.setText(list.get(position).description);
        holder.money.setText(list.get(position).upper_amount+"-"+list.get(position).lower_amount);
        holder.day.setText(list.get(position).term);
        String applicants = list.get(position).applicants;
        double v = Double.parseDouble(applicants);
        double v1 = v / 10000;
        String format = String.format("%.2f", v1);
        holder.people.setText(format+"万人申请成功");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getItempostion.success(position);
            }
        });
        if (list.get(position).prod_title == null || list.get(position).prod_title.size() == 0) {
            holder.title.setVisibility(View.GONE);
            holder.title2.setVisibility(View.GONE);
        } else {
            if(list.get(position).prod_title.size()<3){
                if(list.get(position).prod_title.size()==1){
                    holder.title.setVisibility(View.VISIBLE);
                    holder.title.setText(list.get(position).prod_title.get(0));
                    holder.title2.setVisibility(View.GONE);
                }else if(list.get(position).prod_title.size()==2){
                    holder.title.setVisibility(View.VISIBLE);
                    holder.title.setText(list.get(position).prod_title.get(0));
                    holder.title2.setVisibility(View.VISIBLE);
                    holder.title2.setText(list.get(position).prod_title.get(1));
                }

            }else {
                holder.title.setVisibility(View.VISIBLE);
                holder.title.setText(list.get(position).prod_title.get(0));
                holder.title2.setVisibility(View.VISIBLE);
                holder.title2.setText(list.get(position).prod_title.get(1));
            }

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView people;
        private final TextView day;
        private final TextView money;
        private final TextView hotgo;
        private final TextView destroy;
        private final TextView name;
        private final ImageView icon;
        private final TextView title;
        private final TextView title2;

        public MyViewHolder(View itemView) {
            super(itemView);

            icon = (ImageView) itemView.findViewById(R.id.hot_icon);
            name = (TextView) itemView.findViewById(R.id.hot_name);
            destroy = (TextView) itemView.findViewById(R.id.hot_destory);
            hotgo = (TextView) itemView.findViewById(R.id.hot_goto);
            money = (TextView) itemView.findViewById(R.id.hot_money);
            day = (TextView) itemView.findViewById(R.id.hot_day);
            people = (TextView) itemView.findViewById(R.id.hot_people);
            title = (TextView) itemView.findViewById(R.id.pri_title);
            title2 = (TextView) itemView.findViewById(R.id.pri_title2);

        }
    }

    public interface getItempostion{

        void success(int pos);
    }
    public void setItempostion(getItempostion getItempostion){
        this.getItempostion=getItempostion;
    }
}
