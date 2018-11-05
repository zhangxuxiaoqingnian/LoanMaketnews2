package money.kuxuan.platform.moneyplatfrom.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import money.kuxuan.platform.factory.bean.MessTypeBean;
import money.kuxuan.platform.moneyplatfrom.R;
import money.kuxuan.platform.moneyplatfrom.guoshen.activity.MineConreact;

/**
 * Created by 小狼 on 2018/11/4.
 */

public class MessTypeAdapter extends RecyclerView.Adapter<MessTypeAdapter.MyViewHolder> {

    private Context context;
    private List<MessTypeBean.RstBean>  list;
    private getItemposition getItemposition;
    private int selectedPositon=0;

    public MessTypeAdapter(Context context, List<MessTypeBean.RstBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.messtype_layout, null);
        MyViewHolder myViewHolder=new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.typetext.setText(list.get(position).name);
        if(selectedPositon==position){
            holder.typetext.setTextSize(TypedValue.COMPLEX_UNIT_PX, 18);
            holder.typetext.setTextColor(Color.parseColor("#FFB33A"));
            holder.typeview.setVisibility(View.VISIBLE);
        }else {
            holder.typetext.setTextSize(TypedValue.COMPLEX_UNIT_PX, 14);
            holder.typetext.setTextColor(Color.parseColor("#999999"));
            holder.typeview.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getItemposition.success(position);
            }
        });
    }

    public void selectedItemPosition(int position) {
        this.selectedPositon = position;
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final View typeview;
        private final TextView typetext;

        public MyViewHolder(View itemView) {
            super(itemView);

            typetext = (TextView) itemView.findViewById(R.id.typetext);
            typeview = itemView.findViewById(R.id.typeview);
        }
    }

    public interface getItemposition{

        void success(int pos);
    }
    public void setItemposition(getItemposition getItemposition){
        this.getItemposition=getItemposition;
    }
}
