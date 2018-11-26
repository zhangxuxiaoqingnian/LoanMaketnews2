package com.smileflowpig.money.moneyplatfrom.activities;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.Activity;
import com.smileflowpig.money.common.widget.DividerItemDecoration;
import com.smileflowpig.money.factory.model.api.guoshen.Average_CaptialBean;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by Allence on 2017/12/21 0021.
 */

public class Activity_Average_Capital extends Activity implements View.OnClickListener{


    @Override
    protected boolean isNeedNotch() {
        return true;
    }
    @BindView(R.id.back)
    ImageView back;

    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.tv_payinterest)
    TextView tv_payinterest;

    @BindView(R.id.tv_paytotalMoney)
    TextView tv_paytotalMoney;

    @BindView(R.id.tv_totalloan)
    TextView tv_totalloan;

    @BindView(R.id.tv_loanrate)
    TextView tv_loanrate;

    @BindView(R.id.tv_date)
    TextView tv_date;

    @BindView(R.id.tv_first)
    TextView tv_first;

    private List<String> monthlypaymomeyList;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_average_capital;
    }

    @Override
    protected void initBefore() {
        super.initBefore();
        ButterKnife.bind(this);
    }
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        initView();
    }

    private void initView() {

        tv_title.setText("等额本金");
        back.setOnClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerView.setAdapter(new MyAdapter());


    }


    @Override
    protected void initData() {
        super.initData();

       Average_CaptialBean bean = (Average_CaptialBean) getIntent().getSerializableExtra("duixiang");

       setView(bean);


    }

    private void setView(Average_CaptialBean bean) {

        tv_payinterest.setText(bean.getPayInterest()+"万");
        //tv_paytotalcom.setText(bean.getPaytotalMoney()+"万");
        tv_totalloan.setText(bean.getTotalloan()+"万");
        tv_loanrate.setText(bean.getLoanrate()+"%");
        tv_date.setText(bean.getPaymonth()+"个月");
        monthlypaymomeyList = bean.getMonthlypaymomeyList();
        tv_first.setText(monthlypaymomeyList.get(0).toString()+"万");
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(Activity_Average_Capital.this).inflate(R.layout.item_average_capital,parent,false);

            MyViewHolder holder = new MyViewHolder(view);

            return holder;
        }


        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
//            monthlypaymomeyList

            int size = monthlypaymomeyList.size();

//            int length = size - 1;
//            int recysize=0;
//            if(length%2==0){
//                recysize  = length/2;
//            }else {
//                recysize = length/2+1;
//            }


            if(position*2>(size-1)){
                if(position*2==size){
                    return;
                }
            }


            holder.tv_yuegong.setText("第"+(position*2)+"期月供");
            holder.tv_yuegong1.setText("第"+(position*2+1)+"期月供");
            //holder.tv_com.setText(monthlypaymomeyList.get(position*2)+"万");
            holder.tv_money1.setText(monthlypaymomeyList.get(position*2+1)+"万");



        }


        @Override
        public int getItemCount() {

            int size = monthlypaymomeyList.size();

            int length = size - 1;
            int recysize=0;
            if(length%2==0){
                recysize  = length/2;
            }else {
                recysize = length/2+1;
            }

            return recysize;
        }


        class MyViewHolder extends RecyclerView.ViewHolder{

            TextView tv_money;
            TextView tv_money1;
            TextView tv_yuegong;
            TextView tv_yuegong1;

            public MyViewHolder(View itemView) {
                super(itemView);

                tv_money = (TextView) itemView.findViewById(R.id.tv_money);
                tv_money1 = (TextView) itemView.findViewById(R.id.tv_money1);
                tv_yuegong = (TextView) itemView.findViewById(R.id.tv_yuegong);
                tv_yuegong1 = (TextView) itemView.findViewById(R.id.tv_yuegong1);


            }
        }

    }



    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.back:

                finish();

                break;



        }


    }
}
