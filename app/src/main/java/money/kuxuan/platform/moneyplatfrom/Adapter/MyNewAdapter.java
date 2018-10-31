package money.kuxuan.platform.moneyplatfrom.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import money.kuxuan.platform.factory.bean.NewListBean;
import money.kuxuan.platform.moneyplatfrom.R;
import money.kuxuan.platform.moneyplatfrom.guoshen.activity.AddActivity;


/**
 * Created by 小狼 on 2018/9/19.
 */

public class MyNewAdapter extends RecyclerView.Adapter<MyNewAdapter.MyViewHolder> {

    private Context context;
    private List<NewListBean.RstBean>  list;
    private Getnum getnum;

    public MyNewAdapter(Context context, List<NewListBean.RstBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.mynewlist_layout, parent, false);
        MyViewHolder myViewHolder=new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.name.setText(list.get(position).platform);
        holder.text1.setText(list.get(position).amount);
        String first_time = list.get(position).first_time;
        String substring = first_time.substring(0, 10);
        holder.text2.setText(substring);
        holder.id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddActivity.show(context,list.get(position).id);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getnum.itemclick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView text1;
        private final TextView text2;
        private final ImageView id;

        public MyViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.newname);
            text1 = (TextView) itemView.findViewById(R.id.newtext);
            text2 = (TextView) itemView.findViewById(R.id.newtext2);
            id = (ImageView) itemView.findViewById(R.id.newid);
        }
    }

    public interface Getnum{

        void itemclick(int pos);
    }
    public void Setnum(Getnum getnum){
        this.getnum=getnum;
    }
}
