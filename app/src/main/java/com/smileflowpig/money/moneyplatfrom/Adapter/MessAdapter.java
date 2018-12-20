package com.smileflowpig.money.moneyplatfrom.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.smileflowpig.money.R;
import com.smileflowpig.money.factory.bean.PushBean;
import com.smileflowpig.money.moneyplatfrom.activities.CaseurlActivity;
import com.smileflowpig.money.moneyplatfrom.activities.DetailActivity;

import java.util.List;

/**
 * Created by 小狼 on 2018/11/29.
 */

public class MessAdapter extends RecyclerView.Adapter<MessAdapter.MyViewHolder> {

    private Context context;
    private List<PushBean.ResBean> list;

    public MessAdapter(Context context, List<PushBean.ResBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_trace, null);
        MyViewHolder myViewHolder=new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        SpannableString clickString2 = new SpannableString("速戳查看");
        clickString2.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                //首页
                if(list.get(position).xhz_type==1){

                }//资讯url
                else if(list.get(position).xhz_type==2){
                    Intent intent=new Intent(context, CaseurlActivity.class);
                    intent.putExtra("urlid","");
                    intent.putExtra("urlname","");
                    intent.putExtra("urladdress","");
                    intent.putExtra("pushurlmess",list.get(position).link);
                    context.startActivity(intent);
                }//产品
                else if(list.get(position).xhz_type==3){
                    DetailActivity.show(context, list.get(position).product_id + "", "notice", 0, 8);
                }

            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.parseColor("#FD871E")); //设置颜色
            }
        }, 0, clickString2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        holder.tvAcceptStation.setText(list.get(position).content+"   ");
        holder.tvAcceptStation.append(clickString2);
        holder.tvAcceptStation.setMovementMethod(LinkMovementMethod.getInstance());
        //holder.cont.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        holder.tvAcceptTime.setText(list.get(position).push_time);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvAcceptTime;
        private final TextView tvAcceptStation;
        //private final TextView cont;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvAcceptTime = (TextView) itemView.findViewById(R.id.tvAcceptTime);
            tvAcceptStation = (TextView) itemView.findViewById(R.id.tvAcceptStation);
            //cont = (TextView) itemView.findViewById(R.id.tv_cont);
        }
    }


}
