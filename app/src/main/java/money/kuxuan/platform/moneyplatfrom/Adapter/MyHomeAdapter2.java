package money.kuxuan.platform.moneyplatfrom.Adapter;

import android.content.Context;
import android.content.Intent;
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
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import money.kuxuan.platform.factory.bean.MyHomeFragmentBean;
import money.kuxuan.platform.factory.bean.MyHomeListBean;
import money.kuxuan.platform.factory.model.api.examine.Banner;
import money.kuxuan.platform.factory.netword.NetRequestUtils;
import money.kuxuan.platform.moneyplatfrom.R;
import money.kuxuan.platform.moneyplatfrom.activities.MyTypeActivity;

/**
 * Created by 小狼 on 2018/10/9.
 */

public class MyHomeAdapter2 extends RecyclerView.Adapter<MyHomeAdapter2.MyViewHolder>{

    private Context context;
    private List<MyHomeListBean.RstBean.DataBean> list;
    private Getnum getnum;
    private String olad;
    private View inflate;
    List<MyHomeFragmentBean.RstBean.BannerBean> list2;


    public MyHomeAdapter2(Context context, List<MyHomeListBean.RstBean.DataBean> list, String olad,List<MyHomeFragmentBean.RstBean.BannerBean> list2) {
        this.context = context;
        this.list = list;
        this.olad=olad;
        this.list2=list2;
    }

    @Override
    public MyHomeAdapter2.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==0){
             inflate = LayoutInflater.from(context).inflate(R.layout.mylisttitle_layout, parent, false);
        }else {
            inflate = LayoutInflater.from(context).inflate(R.layout.myhomeadapter_layout, parent,false);
        }
        MyViewHolder myViewHolder=new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyHomeAdapter2.MyViewHolder holder, final int position) {
        if(position==0){

            if(list2!=null&&list2.size()>0){
                holder.banner.setData(list2,null);
                holder.banner.loadImage(new XBanner.XBannerAdapter() {
        @Override
        public void loadBanner(XBanner banner, Object model, View view, int position2) {

            Glide.with(context).load(list2.get(position2).thumbnail).into((ImageView) view);
        }
    });

            }

            holder.shungou.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent=new Intent(context, MyTypeActivity.class);
                    intent.putExtra("overtype","顺购");
                    intent.putExtra("typeid",1);
                    context.startActivity(intent);
                }
            });
            holder.lineup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, MyTypeActivity.class);
                    intent.putExtra("overtype","排队");
                    intent.putExtra("typeid",2);
                    context.startActivity(intent);
                }
            });
            holder.otherop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, MyTypeActivity.class);
                    intent.putExtra("overtype","其他");
                    intent.putExtra("typeid",3);
                    context.startActivity(intent);
                }
            });
        }else {
            if (olad.equals("head")) {
                if (position == 1) {
                    holder.homeimg.setVisibility(View.VISIBLE);
                } else {
                    holder.homeimg.setVisibility(View.GONE);
                }
            } else if (olad.equals("foot")) {
                holder.homeimg.setVisibility(View.GONE);
            }

            holder.name.setText(list.get(position-1).publisher_nick);
            holder.textgo.setText(list.get(position-1).status);

            String publisher_avatar = list.get(position-1).publisher_avatar;
            if (publisher_avatar.equals("")) {
                holder.icon.setImageResource(R.mipmap.nameicon);
            } else {
                Glide.with(context).load(publisher_avatar).into(holder.icon);
            }
            String type = list.get(position-1).type;
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
            holder.title.setText(list.get(position-1).title);
            holder.time.setText(list.get(position-1).time);
            holder.price.setText("¥" + list.get(position-1).remuneration + "元");
            holder.address.setText(list.get(position-1).place);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    getnum.onclice(position-1);
                }
            });
        }

        }


    @Override
    public int getItemCount() {
        return list.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return 0;
        }else {
            return 1;
        }

    }

//    banner.setData(homebanner, null);
//
//                    banner.loadImage(new XBanner.XBannerAdapter() {
//        @Override
//        public void loadBanner(XBanner banner, Object model, View view, int position) {
//
//            Glide.with(getActivity()).load(homebanner.get(position).thumbnail).into((ImageView) view);
//        }
//    });
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
        private final LinearLayout otherop;
        private final LinearLayout lineup;
        private final LinearLayout shungou;
        private final XBanner banner;

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
            banner = (XBanner) itemView.findViewById(R.id.xbanner);
            shungou = (LinearLayout) itemView.findViewById(R.id.shungou);
            lineup = (LinearLayout) itemView.findViewById(R.id.lineup);
            otherop = (LinearLayout) itemView.findViewById(R.id.otherup);

        }
    }

    public interface Getnum{

        void onclice(int pos);
    }
    public void Setnum(Getnum getnum){
        this.getnum=getnum;
    }
}
