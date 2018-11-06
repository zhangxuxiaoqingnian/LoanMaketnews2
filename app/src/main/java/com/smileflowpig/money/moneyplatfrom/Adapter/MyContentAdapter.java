package com.smileflowpig.money.moneyplatfrom.Adapter;

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
import com.smileflowpig.money.factory.model.db.Active;

/**
 * Created by 小狼 on 2018/10/30.
 */

public class MyContentAdapter extends RecyclerView.Adapter<MyContentAdapter.MyViewHolder>{

    private Context context;
    private List<Active> list;

    public MyContentAdapter(Context context, List<Active> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.cell_active_list, null);
        MyViewHolder myViewHolder=new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.title.setText(list.get(position).getTitle());
        Glide.with(context).load(list.get(position).getImage_url()).into(holder.icon);
        holder.time.setText(list.get(position).getCreate_time());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final ImageView icon;
        private final TextView time;

        public MyViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.active_title);
            icon = (ImageView) itemView.findViewById(R.id.active_image);
            time = (TextView) itemView.findViewById(R.id.time);

        }
    }
}
