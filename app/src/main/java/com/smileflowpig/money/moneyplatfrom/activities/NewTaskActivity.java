package com.smileflowpig.money.moneyplatfrom.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.PresenterActivity;
import com.smileflowpig.money.common.factory.presenter.BaseContract;
import com.smileflowpig.money.factory.bean.MoneyBean;
import com.smileflowpig.money.factory.netword.NetRequestUtils;

import java.text.DecimalFormat;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NewTaskActivity extends PresenterActivity implements View.OnClickListener{

    @BindView(R.id.money_back)
    LinearLayout back;
    @BindView(R.id.mymoney)
    TextView mymoney;
    @BindView(R.id.withdraw)
    TextView withdraw;
    //红包记录
    @BindView(R.id.packet_record)
    RelativeLayout packet_record;
    //提现记录
    @BindView(R.id.deposit_page)
    RelativeLayout deposit_page;
    //消费记录
//   @BindView(R.id.consume_page)
//   RelativeLayout consume_page;
   @BindView(R.id.tv_cont)
   TextView cont;
   @BindView(R.id.tasklayout)
   LinearLayout layout;
    private int money;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initview();

    }
    public void initview(){
        back.setOnClickListener(this);
        packet_record.setOnClickListener(this);
        deposit_page.setOnClickListener(this);
        //consume_page.setOnClickListener(this);
        withdraw.setOnClickListener(this);
        cont.setOnClickListener(this);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_new_task;
    }

    @Override
    protected BaseContract.Presenter initPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.money_back:
                finish();
                break;
                //红包记录
            case R.id.packet_record:
                Intent intent=new Intent(NewTaskActivity.this,RecodePagerActivity.class);
                intent.putExtra("cardname","红包记录");
                startActivity(intent);
                break;
                //提现记录
            case R.id.deposit_page:
                Intent intent2=new Intent(NewTaskActivity.this,RecodePagerActivity.class);
                intent2.putExtra("cardname","提现记录");
                startActivity(intent2);
                break;
                //消费记录
//            case R.id.consume_page:
//                Intent intent3=new Intent(NewTaskActivity.this,RecodePagerActivity.class);
//                intent3.putExtra("cardname","消费记录");
//                startActivity(intent3);
//                break;
            case R.id.withdraw:
               if(money>=50){
                    Intent intent4=new Intent(NewTaskActivity.this,RedPagerActivity.class);
                    startActivity(intent4);
                }
                break;
            case R.id.tv_cont:
                light(0.6f);
                getpopwindow();
                break;
        }
    }
    public void getpopwindow(){
        int width = getWindowManager().getDefaultDisplay().getWidth();
        View inflate = LayoutInflater.from(NewTaskActivity.this).inflate(R.layout.money_text_layout, null, false);
        ImageView dis = (ImageView) inflate.findViewById(R.id.money_diss);
        final PopupWindow popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.WRAP_CONTENT,  ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(layout, Gravity.CENTER,0,0);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

                light(1.0f);
            }
        });
        dis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }
    public void light(float t){
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = t;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }
    public void getdata(){

        Observable<MoneyBean> moneyBeanObservable = new NetRequestUtils().bucuo().getbaseretrofit().getmonth().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        moneyBeanObservable.subscribe(new Observer<MoneyBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MoneyBean moneyBean) {

                money = moneyBean.rst.money;
                if(moneyBean.rst.money==0){
                    mymoney.setText("¥0.00");
                }else {
                    DecimalFormat df = new DecimalFormat("#.00");
                    mymoney.setText("¥"+df.format(moneyBean.rst.money));
                }
                if(money<50){
                        withdraw.setClickable(false);
                }else {
                    withdraw.setBackground(getResources().getDrawable(R.drawable.outket));
                    withdraw.setTextColor(Color.parseColor("#FFA545"));
                    withdraw.setClickable(true);
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        getdata();
    }
}
