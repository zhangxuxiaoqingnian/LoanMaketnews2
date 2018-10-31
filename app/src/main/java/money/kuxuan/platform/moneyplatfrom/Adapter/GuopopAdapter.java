package money.kuxuan.platform.moneyplatfrom.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import money.kuxuan.platform.factory.bean.MyBean;
import money.kuxuan.platform.factory.bean.NewDeatilsBean;
import money.kuxuan.platform.factory.bean.NewListBean;
import money.kuxuan.platform.moneyplatfrom.R;

/**
 * Created by 小狼 on 2018/9/20.
 */

public class GuopopAdapter extends RecyclerView.Adapter<GuopopAdapter.MyViewHolder>{

    private Context context;
    private List<NewDeatilsBean.RstBean> list;
    private List<MyBean> list2;


    public GuopopAdapter(Context context, List<NewDeatilsBean.RstBean> list, List<MyBean> list2) {
        this.context = context;
        this.list = list;
        this.list2 = list2;
    }

    @Override
    public GuopopAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.guoshen_popitem, parent, false);
        MyViewHolder myViewHolder=new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(GuopopAdapter.MyViewHolder holder, final int position) {

        holder.tv_qs.setText(list.get(position).periods);
        holder.tv_hkrnum.setText(list.get(position).date.substring(0,10));
        holder.tv_hkjenum.setText(list.get(position).amount);

        int status = list.get(position).status;
        if(status==0){
            holder.tv_status.setText("待还款");
        }else {
            holder.tv_status.setText("已结清");
        }



        holder.guoshen_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    list2.get(position).setIstrue(true);
                }else {
                    list2.get(position).setIstrue(false);
                }
            }
        });
        if(list2.get(position).istrue){
            holder.guoshen_checkbox.setChecked(true);
        }else {
            holder.guoshen_checkbox.setChecked(false);
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_qs;
        private final TextView tv_hkrnum;
        private final TextView tv_hkjenum;
        private final TextView tv_status;
        private final CheckBox guoshen_checkbox;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_qs = (TextView) itemView.findViewById(R.id.tv_qs);
            tv_hkrnum = (TextView) itemView.findViewById(R.id.tv_hkrnum);
            tv_hkjenum = (TextView) itemView.findViewById(R.id.tv_hkjenum);
            tv_status = (TextView) itemView.findViewById(R.id.tv_status);
            guoshen_checkbox = (CheckBox) itemView.findViewById(R.id.guoshen_checkbox);
        }
    }
}
