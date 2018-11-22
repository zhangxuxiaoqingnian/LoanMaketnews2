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
import com.smileflowpig.money.moneyplatfrom.Bean.NoteEntity;

import java.util.ArrayList;

import com.smileflowpig.money.R;
import com.smileflowpig.money.moneyplatfrom.util.GlideUtil;

/**
 * Created by 小狼 on 2018/10/19.
 */

public class DaiAdapter3 extends RecyclerView.Adapter<DaiAdapter3.MyViewHolder> {

    private Context context;
    private boolean isShow=false;
    private boolean iscode=false;
    private int pos;
    private getItemposition getItemposition;
    private ArrayList<NoteEntity> list;



    public DaiAdapter3(Context context, ArrayList<NoteEntity> list) {
        this.context = context;
        this.list = list;
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


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getItemposition!=null)
                getItemposition.success(position);
            }
        });

        GlideUtil.setImageViewCrop(context,list.get(position).imgurl,holder.img);
//        Glide.with(context).load(list.get(position).imgurl).into(holder.img);
       holder.name.setText(list.get(position).title);
        holder.jieshao.setText(list.get(position).content);
        holder.money.setText(list.get(position).price);
        holder.lv.setText(list.get(position).interest);
        holder.man.setText(list.get(position).peoplenum);
        holder.name.setText(list.get(position).title);

        holder.shen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getItemposition.requst(position);
            }
        });

//        holder.shen.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(isture){
//                    WebActivity.show(context, list.get(position).title,
//                            list.get(position).url, list.get(position).productid, "", "0",false);
//                }else {
//                    Intent intent=new Intent(context, AccountActivity.class);
//                    context.startActivity(intent);
//                }
//            }
//        });


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

        void requst(int pos);
    }
    public void setItemposition(getItemposition getItemposition){
        this.getItemposition=getItemposition;
    }
}
