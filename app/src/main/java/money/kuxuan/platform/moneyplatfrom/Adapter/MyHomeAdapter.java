package money.kuxuan.platform.moneyplatfrom.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import money.kuxuan.platform.factory.bean.MyHomeListBean;
import money.kuxuan.platform.moneyplatfrom.R;

import static money.kuxuan.platform.moneyplatfrom.R.drawable.li;
import static money.kuxuan.platform.moneyplatfrom.R.drawable.myhometype;

/**
 * Created by 小狼 on 2018/10/9.
 */

public class MyHomeAdapter extends RecyclerView.Adapter<MyHomeAdapter.MyViewHolder>{

    private Context context;
    private List<MyHomeListBean.RstBean.DataBean> list;
    private Getnum getnum;
    private String olad;
    private View inflate;

    public MyHomeAdapter(Context context, List<MyHomeListBean.RstBean.DataBean> list,String olad) {
        this.context = context;
        this.list = list;
        this.olad=olad;
    }

    @Override
    public MyHomeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        inflate = LayoutInflater.from(context).inflate(R.layout.myhomeadapter_layout, parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyHomeAdapter.MyViewHolder holder, final int position) {


            if (olad.equals("head")) {
                if (position == 1) {
                    holder.homeimg.setVisibility(View.VISIBLE);
                } else {
                    holder.homeimg.setVisibility(View.GONE);
                }
            } else if (olad.equals("foot")) {
                holder.homeimg.setVisibility(View.GONE);
            }

            holder.name.setText(list.get(position).publisher_nick);
            holder.textgo.setText(list.get(position).status);

            String publisher_avatar = list.get(position).publisher_avatar;
            if (publisher_avatar.equals("")) {
                holder.icon.setImageResource(R.mipmap.nameicon);
            } else {
                Glide.with(context).load(publisher_avatar).into(holder.icon);
            }
            String type = list.get(position).type;
            if (type == null) {
                holder.hometype.setText("其他");
            } else {
                if (type.equals("顺购")) {
                    holder.hometype.setText("顺购");
                    holder.hometype.setBackgroundResource(R.drawable.myhometype);
                } else {
                    holder.hometype.setText(type);
                    holder.hometype.setBackgroundResource(R.drawable.myhometype2);
                }
            }
            holder.title.setText(list.get(position).title);
            holder.time.setText(list.get(position).time);
            holder.price.setText("¥" + list.get(position).remuneration + "元");
            holder.address.setText(list.get(position).place);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    getnum.onclice(position);
                }
            });
        }


    @Override
    public int getItemCount() {
        return list.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView address;
        private final TextView price;
        private final TextView time;
        private final TextView title;
        private final TextView hometype;
        private final TextView textgo;
        private final ImageView homeimg;
        private final TextView name;
        private final CircleImageView icon;

        public MyViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.homename);
            homeimg = (ImageView) itemView.findViewById(R.id.homeimg);
            textgo = (TextView) itemView.findViewById(R.id.homego);
            hometype = (TextView) itemView.findViewById(R.id.hometype);
            title = (TextView) itemView.findViewById(R.id.hometitle);
            time = (TextView) itemView.findViewById(R.id.hometime);
            price = (TextView) itemView.findViewById(R.id.homeprice);
            address = (TextView) itemView.findViewById(R.id.homeaddress);
            icon = (CircleImageView) itemView.findViewById(R.id.icon);

        }
    }

    public interface Getnum{

        void onclice(int pos);
    }
    public void Setnum(Getnum getnum){
        this.getnum=getnum;
    }
}
