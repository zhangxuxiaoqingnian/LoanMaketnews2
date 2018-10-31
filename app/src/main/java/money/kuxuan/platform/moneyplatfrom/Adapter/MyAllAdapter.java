package money.kuxuan.platform.moneyplatfrom.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import money.kuxuan.platform.factory.bean.AllMyBean;
import money.kuxuan.platform.moneyplatfrom.R;

/**
 * Created by 小狼 on 2018/10/11.
 */

public class MyAllAdapter extends RecyclerView.Adapter<MyAllAdapter.MyViewHolder>{

    private Context context;
    private List<AllMyBean.RstBean.DataBean> list;
    private Getnum getnum;

    public MyAllAdapter(Context context, List<AllMyBean.RstBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.myalladapter_layout, null, false);
        MyViewHolder myViewHolder=new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        String type = list.get(position).type;
        if(type==null){
            holder.type.setText("其他");
        }else {
            if (type.equals("顺购")) {
                holder.type.setText("顺购");
                holder.type.setBackgroundResource(R.drawable.myhometype);
            } else {
                holder.type.setText(type);
                holder.type.setBackgroundResource(R.drawable.myhometype2);
            }
        }
        holder.title.setText(list.get(position).title);
        holder.time.setText(list.get(position).time);
        holder.stute.setText(list.get(position).status);
        holder.price.setText(list.get(position).remuneration+"元");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getnum.getpost(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView price;
        private final TextView time;
        private final TextView stute;
        private final TextView title;
        private final TextView type;

        public MyViewHolder(View itemView) {
            super(itemView);

            type = (TextView) itemView.findViewById(R.id.alltype);
            title = (TextView) itemView.findViewById(R.id.alltitle);
            stute = (TextView) itemView.findViewById(R.id.allprice);
            time = (TextView) itemView.findViewById(R.id.alltime);
            price = (TextView) itemView.findViewById(R.id.alladdress);
        }
    }
    public interface Getnum{

        void getpost(int pos);
    }
    public void Setnum(Getnum getnum){
        this.getnum=getnum;
    }

}
