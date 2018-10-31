package money.kuxuan.platform.moneyplatfrom.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import money.kuxuan.platform.factory.bean.MemoBean;
import money.kuxuan.platform.moneyplatfrom.R;
import money.kuxuan.platform.moneyplatfrom.activities.MemoActivity;

/**
 * Created by 小狼 on 2018/10/24.
 */

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.MyViewHolder>{

    private Context context;
    private List<MemoBean.RstBean.DataBean> list;
    private Getnum getnum;

    public MemoAdapter(Context context, List<MemoBean.RstBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.memoadapter_layout, null);
        MyViewHolder myViewHolder=new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.money.setText("¥"+list.get(position).how_much);
        Glide.with(context).load(list.get(position).platform_icon).into(holder.icon);
        holder.typetext.setText(list.get(position).platform_name);
        holder.detail.setText(list.get(position).how_to_use);
        holder.data.setText(list.get(position).due_date);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getnum.success(position);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //删除备忘录
                getnum.zipos(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView data;
        private final ImageView delete;
        private final TextView detail;
        private final TextView typetext;
        private final ImageView icon;
        private final TextView money;

        public MyViewHolder(View itemView) {
            super(itemView);

            money = (TextView) itemView.findViewById(R.id.memo_money);
            icon = (ImageView) itemView.findViewById(R.id.memo_icon);
            typetext = (TextView) itemView.findViewById(R.id.memo_type);
            detail = (TextView) itemView.findViewById(R.id.memo_detail);
            delete = (ImageView) itemView.findViewById(R.id.memo_detele);
            data = (TextView) itemView.findViewById(R.id.memo_data);
        }
    }

    public interface Getnum{
        void success(int pos);
        void zipos(int pos);
    }
    public void Setnum(Getnum getnum){
        this.getnum=getnum;
    }
}
