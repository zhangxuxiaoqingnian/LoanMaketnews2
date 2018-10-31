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

import money.kuxuan.platform.factory.bean.HomedataBean;
import money.kuxuan.platform.moneyplatfrom.R;

/**
 * Created by 小狼 on 2018/10/19.
 */

public class HuaAdapter extends RecyclerView.Adapter<HuaAdapter.MyViewHolder>{

    private Context context;
    private List<HomedataBean.RstBean.DataBean> list;
    private Getnum getnum;

    public HuaAdapter(Context context, List<HomedataBean.RstBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.huaadapter_layout, null);
        MyViewHolder myViewHolder=new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        Glide.with(context).load(list.get(position).icon).into(holder.huaicon);
        holder.name.setText(list.get(position).name);
        holder.money.setText(list.get(position).upper_amount+"-"+list.get(position).lower_amount);
        holder.lilv.setText(list.get(position).day_rate+"%起");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getnum.successover(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView lilv;
        private final TextView money;
        private final TextView name;
        private final ImageView huaicon;

        public MyViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.huaname);
            money = (TextView) itemView.findViewById(R.id.huatext);
            lilv = (TextView) itemView.findViewById(R.id.huatext2);
            huaicon = (ImageView) itemView.findViewById(R.id.huaicon);
        }
    }

    public interface Getnum{

        void successover(int pos);
    }
    public void Setnum(Getnum getnum){
        this.getnum=getnum;
    }
}
