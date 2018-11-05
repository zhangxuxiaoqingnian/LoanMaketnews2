package money.kuxuan.platform.moneyplatfrom.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import money.kuxuan.platform.factory.bean.MessageBean;
import money.kuxuan.platform.moneyplatfrom.R;

/**
 * Created by 小狼 on 2018/11/2.
 */

public class MessageTwoAdapter extends RecyclerView.Adapter<MessageTwoAdapter.MyViewHolder> {

    private Context context;
    private List<MessageBean.RstBean.NewListBean> list;

    public MessageTwoAdapter(Context context, List<MessageBean.RstBean.NewListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.message_layout, null);
        MyViewHolder myViewHolder=new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.name.setText(list.get(position).title);
        Glide.with(context).load(list.get(position).picture).into(holder.icon);
        holder.num.setText(list.get(position).view_num+"阅读");
        holder.time.setText(list.get(position).publish_time);
        if(list.get(position).is_up.equals("1")){
            holder.uper.setVisibility(View.VISIBLE);
        }else {
            holder.uper.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final ImageView icon;
        private final TextView num;
        private final TextView time;
        private final TextView uper;

        public MyViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.mess_name);
            icon = (ImageView) itemView.findViewById(R.id.mess_icon);
            num = (TextView) itemView.findViewById(R.id.mess_num);
            time = (TextView) itemView.findViewById(R.id.mess_time);
            uper = (TextView) itemView.findViewById(R.id.mess_up);


        }
    }
}
