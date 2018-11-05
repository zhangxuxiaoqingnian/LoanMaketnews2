package money.kuxuan.platform.moneyplatfrom.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import money.kuxuan.platform.factory.bean.NewDeatilsBean;
import money.kuxuan.platform.factory.bean.NewListBean;
import money.kuxuan.platform.moneyplatfrom.R;


/**
 * Created by 小狼 on 2018/9/20.
 */

public class GuoAdapter extends RecyclerView.Adapter<GuoAdapter.MyViewHolder> {

    private Context context;
    private List<NewDeatilsBean.RstBean> list;

    public GuoAdapter(Context context, List<NewDeatilsBean.RstBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.guoshen_amortizeitem, parent, false);
        MyViewHolder myViewHolder=new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.tvtime.setText(list.get(position).date.substring(0,10));
        holder.qishu.setText(list.get(position).periods);
        holder.price.setText(list.get(position).amount);

        int status = list.get(position).getStatus();
        if(status==0){

            //获取当前日期
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");// HH:mm:ss
            Date date = new Date(System.currentTimeMillis());
            String format = simpleDateFormat.format(date);

            String replace = format.replace("-", "");
            String replace1 = list.get(position).date.substring(0, 10).replace("-", "");
            if(Integer.parseInt(replace)>Integer.parseInt(replace1)){
                holder.stute.setText("已逾期");
                holder.stute.setTextColor(Color.parseColor("#ff3300"));
            }else {
                holder.stute.setText("待还款");
                holder.stute.setTextColor(Color.parseColor("#000000"));
            }


//            if(i==0){
//                holder.stute.setText("已逾期");
//                holder.stute.setTextColor(Color.parseColor("#ff3300"));
//            }else {
//                holder.stute.setText("待还款");
//                holder.stute.setTextColor(Color.parseColor("#000000"));
//            }


        }else {
            holder.stute.setText("已还款");
            holder.stute.setTextColor(Color.parseColor("#cccccc"));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView stute;
        private final TextView price;
        private final TextView qishu;
        private final TextView tvtime;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvtime = (TextView) itemView.findViewById(R.id.tv_date);
            qishu = (TextView) itemView.findViewById(R.id.qishu);
            price = (TextView) itemView.findViewById(R.id.repayment);
            stute = (TextView) itemView.findViewById(R.id.tv_status);
        }
    }



    //比较时间大小


}
