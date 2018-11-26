package com.smileflowpig.money.moneyplatfrom.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextClock;
import android.widget.TextView;

import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.PresenterActivity;
import com.smileflowpig.money.common.factory.presenter.BaseContract;
import com.smileflowpig.money.factory.bean.MyidentcardBean;
import com.smileflowpig.money.factory.netword.NetRequestUtils;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MyDatumActivity extends PresenterActivity implements View.OnClickListener{

    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.authentication)
    RelativeLayout authentication;
    @BindView(R.id.Alipay)
    RelativeLayout alipay;
    @BindView(R.id.cardphone)
    TextView cardphone;
    @BindView(R.id.identycard)
    TextView identycard;
    @BindView(R.id.gotopay)
    TextView gotopay;
    private String myphone;
    private String alipay_id;
    private String real_name;
    @Override
    protected boolean isNeedNotch() {
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initview();
        myphone = getIntent().getStringExtra("myphone");
        String s = myphone.substring(0, 3) + "****" + myphone.substring(7, myphone.length());
        cardphone.setText(s);



    }
    public void initview(){
        back.setOnClickListener(this);
        authentication.setOnClickListener(this);
        alipay.setOnClickListener(this);

    }

    public void getdata(){

        Observable<MyidentcardBean> objectObservable = new NetRequestUtils().bucuo().getbaseretrofit().getnametext().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        objectObservable.subscribe(new Observer<MyidentcardBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MyidentcardBean o) {
                if(o.rst==null){
                    System.out.println("没数据");
                }else {
                    MyidentcardBean.RstBean rst = o.rst;
                    alipay_id = o.rst.alipay_id;
                    real_name = o.rst.real_name;
                    if(!rst.idcard_no.equals("")){
                        String s = rst.idcard_no.substring(0, 3) + "***********" + rst.idcard_no.substring(14, rst.idcard_no.length());
                        identycard.setText(s);
                        authentication.setClickable(false);
                    }
                    if(rst.alipay_id!=null){
                        if(!rst.alipay_id.equals("")){
                            String alipay_id = rst.alipay_id;
                            String s = alipay_id.substring(0, 3) + "****" + alipay_id.substring(7, alipay_id.length());
                            gotopay.setText(s);
                        }
                    }

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
    protected int getContentLayoutId() {
        return R.layout.activity_my_datum;
    }

    @Override
    protected BaseContract.Presenter initPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
                //身份认证
            case R.id.authentication:
                Intent intent=new Intent(MyDatumActivity.this,IdentityCardActivity.class);
                startActivity(intent);
                break;
                //支付宝绑定
            case R.id.Alipay:
                Intent intent2=new Intent(MyDatumActivity.this,PayEditActivity.class);
                intent2.putExtra("myname",real_name);
                intent2.putExtra("myphone",alipay_id);
                startActivity(intent2);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //看是否有绑定信息
        getdata();
    }
}
