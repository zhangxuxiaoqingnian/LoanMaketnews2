package money.kuxuan.platform.moneyplatfrom.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import money.kuxuan.platform.moneyplatfrom.R;

/**
 * Created by 小狼 on 2018/10/23.
 */

public class Typeadapter extends RecyclerView.Adapter<Typeadapter.MyViewHolder> {

    private Context context;
    private List<String> list;
    private View inflate;
    private GetNum getNum;
    private int selectedPositon=0;

    public Typeadapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==0){
            inflate = LayoutInflater.from(context).inflate(R.layout.typesellow_layout2, null);
        }else {
            inflate = LayoutInflater.from(context).inflate(R.layout.typesellow_layout, null);
        }

        MyViewHolder myViewHolder=new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        if(position==0){
            holder.text2.setText(list.get(position));
            if(position==selectedPositon){
                holder.text2.setBackgroundResource(R.drawable.money);
                holder.text2.setTextColor(Color.parseColor("#ffffff"));
            }else {
                holder.text2.setBackgroundResource(R.drawable.money_quan);
                holder.text2.setTextColor(Color.parseColor("#666666"));

            }
        }else {
            holder.text.setText(list.get(position));

            if(position==selectedPositon){
                holder.text.setBackgroundResource(R.drawable.money);
                holder.text.setTextColor(Color.parseColor("#ffffff"));
            }else {

                holder.text.setBackgroundResource(R.drawable.money_quan);
                holder.text.setTextColor(Color.parseColor("#666666"));
            }
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNum.success(position);
            }
        });





    }
    public void selectedItemPosition(int position) {
        this.selectedPositon = position;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return 0;
        }else {
            return 1;
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView text;
        private final TextView text2;

        public MyViewHolder(View itemView) {
            super(itemView);

            text = (TextView) itemView.findViewById(R.id.money_text);
            text2 = (TextView) itemView.findViewById(R.id.money_text2);
        }
    }
    public interface GetNum{

        void success(int pos);
    }
    public void SetNum(GetNum getNum){
        this.getNum=getNum;
    }
}
