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

import java.util.List;

import com.smileflowpig.money.R;

/**
 * Created by 小狼 on 2018/10/19.
 */

public class DaiAdapter2 extends RecyclerView.Adapter<DaiAdapter2.MyViewHolder> {

    private Context context;
    private List<String> list;
    private List<Boolean> list2;
    private boolean isShow=false;
    private boolean iscode=false;
    private int pos;
    private getItemposition getItemposition;

    public DaiAdapter2(Context context, List<String> list, List<Boolean> list2) {
        this.context = context;
        this.list = list;
        this.list2=list2;

    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.daiadapter_layout, null);
        MyViewHolder myViewHolder=new MyViewHolder(inflate);
        return myViewHolder;
    }

    public void setShow(boolean isShow){
        this.isShow=isShow;
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        if(isShow){
            holder.layout.setVisibility(View.VISIBLE);
            if(list2.get(position)){
                holder.check.setChecked(true);
            }else {
                holder.check.setChecked(false);
            }

        }else {
            holder.layout.setVisibility(View.GONE);
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
    }
    public void setItemposition(getItemposition getItemposition){
        this.getItemposition=getItemposition;
    }
}
