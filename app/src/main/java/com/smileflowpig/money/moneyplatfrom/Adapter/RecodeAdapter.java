package com.smileflowpig.money.moneyplatfrom.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smileflowpig.money.R;
import com.smileflowpig.money.factory.bean.MoneygetBean;

import java.util.List;

/**
 * Created by 小狼 on 2018/11/16.
 */

public class RecodeAdapter extends RecyclerView.Adapter<RecodeAdapter.MyViewHolder> {

    private Context context;
    private List<MoneygetBean.RstBean.DataBean> list;
    private int count;

    public RecodeAdapter(Context context, List<MoneygetBean.RstBean.DataBean> list, int count) {
        this.context = context;
        this.list = list;
        this.count = count;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.recode_layout, null);
        MyViewHolder myViewHolder=new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if(count==1){
            holder.type.setVisibility(View.GONE);
            holder.stute.setVisibility(View.GONE);
        }else if(count==2){
            holder.type.setVisibility(View.GONE);
            holder.stute.setVisibility(View.VISIBLE);
        }else if(count==3){
            holder.type.setVisibility(View.VISIBLE);
            holder.stute.setVisibility(View.GONE);
        }
        if(list.get(position).type.equals("1")){
            holder.name.setText("支付宝");
        }
        holder.time.setText(list.get(position).create_time);
        if(list.get(position).status.equals("1")){
            holder.stute.setText("申请中");
        }else if(list.get(position).status.equals("2")){
            holder.stute.setText("打款失败");
        }else if(list.get(position).status.equals("3")){
            holder.stute.setText("打款成功");
        }
        holder.sum.setText("-"+list.get(position).money);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView time;
        private final TextView sum;
        private final TextView type;
        private final TextView stute;

        public MyViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.recode_name);
            time = (TextView) itemView.findViewById(R.id.recode_time);
            sum = (TextView) itemView.findViewById(R.id.recode_sum);
            type = (TextView) itemView.findViewById(R.id.pay_type);
            stute = (TextView) itemView.findViewById(R.id.recode_stute);
        }
    }
}
