package money.kuxuan.platform.moneyplatfrom.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import money.kuxuan.platform.factory.bean.LoseBean;
import money.kuxuan.platform.moneyplatfrom.R;
import money.kuxuan.platform.moneyplatfrom.util.NiceImageView;

/**
 * Created by 小狼 on 2018/10/25.
 */

public class LoseAdapter extends RecyclerView.Adapter<LoseAdapter.MyViewHolder>{

    private Context context;
    private List<LoseBean.RstBean.DataBean> list;
    private Getitempostion getitempostion;

    public LoseAdapter(Context context, List<LoseBean.RstBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.lose_adapter_layout, null);
        MyViewHolder myViewHolder=new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        Glide.with(context).load(list.get(position).icon).into(holder.icon);
        holder.name.setText(list.get(position).name);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getitempostion.success(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final NiceImageView icon;

        public MyViewHolder(View itemView) {
            super(itemView);

            icon = (NiceImageView) itemView.findViewById(R.id.lose_icon);
            name = (TextView) itemView.findViewById(R.id.lose_name);
        }
    }
    public interface Getitempostion{

        void success(int pos);
    }
    public void SetItemposition(Getitempostion getitempostion){
        this.getitempostion=getitempostion;
    }
}
