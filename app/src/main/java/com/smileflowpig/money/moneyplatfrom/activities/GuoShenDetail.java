package com.smileflowpig.money.moneyplatfrom.activities;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.smileflowpig.money.moneyplatfrom.Adapter.GuoAdapter;
import com.smileflowpig.money.moneyplatfrom.Adapter.GuopopAdapter;
import com.smileflowpig.money.moneyplatfrom.guoshen.activity.AddActivity;
import com.smileflowpig.money.moneyplatfrom.guoshen.adapter.Pop_Adapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.PresenterActivity;
import com.smileflowpig.money.common.factory.presenter.BaseContract;
import com.smileflowpig.money.factory.bean.MyBean;
import com.smileflowpig.money.factory.bean.NewDeatilsBean;
import com.smileflowpig.money.factory.bean.NewListBean;
import com.smileflowpig.money.factory.model.api.guoshen.PutStats;
import com.smileflowpig.money.factory.netword.NetRequestUtils;

public class GuoShenDetail  extends PresenterActivity{

    private RecyclerView rv;
    private TextView stute;
    private TextView qishu;
    private TextView data;
    private TextView price;
    private TextView buover;
    private PopupWindow popupWindow;
    private Pop_Adapter pop_adapter;
    private LinearLayout layout;
    private int sum=0;
    private Gson gson;
    private int guoid;
    private ImageView iv_mine;
    private TextView tv_title;
    private ImageView iv_add;
    @Override
    protected boolean isNeedNotch() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initview();

        iv_mine.setImageResource(R.drawable.guoshen_back);
        iv_add.setVisibility(View.VISIBLE);

        iv_mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        gson = new Gson();
        final List<NewListBean.RstBean.DetailBean> guodata = (List<NewListBean.RstBean.DetailBean>) getIntent().getSerializableExtra("guodata");
        String guoprice = getIntent().getStringExtra("guoprice");
        String guostute = getIntent().getStringExtra("guostute");
        String guotime = getIntent().getStringExtra("guotime");
        guoid = getIntent().getIntExtra("guoid", -1);
        int guoqishu = getIntent().getIntExtra("guoqishu", -1);
        String guoname = getIntent().getStringExtra("guoname");
        tv_title.setText(guoname);
        price.setText(guoprice+"元");
        data.setText(guotime.substring(0,10));
        qishu.setText(guoqishu+"期");
        if(guostute.equals("0")){
            stute.setText("待还款");
        }else {
            stute.setText("已还款");
        }

        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddActivity.show(GuoShenDetail.this,guoid);
            }
        });

        rv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));


        getdata(guoid);



    }

    public void getdata(int id){

        Observable<NewDeatilsBean> objectObservable = new NetRequestUtils().bucuo().getbaseretrofit().getnewdetail(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        objectObservable.subscribe(new Observer<NewDeatilsBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(NewDeatilsBean o) {


                final List<NewDeatilsBean.RstBean> rst = o.rst;
                rv.setLayoutManager(new LinearLayoutManager(GuoShenDetail.this));
                GuoAdapter guoAdapter=new GuoAdapter(GuoShenDetail.this,rst);
                rv.setAdapter(guoAdapter);

                buover.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setPopDtailView(rst);
                    }
                });
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
    public void initview(){

        rv = (RecyclerView) findViewById(R.id.guorv);
        price = (TextView) findViewById(R.id.guoprice);
        data = (TextView) findViewById(R.id.guodata);
        qishu = (TextView) findViewById(R.id.guoqishu);
        stute = (TextView) findViewById(R.id.guostute);
        buover = (TextView) findViewById(R.id.tv_updatestatus);
        layout = (LinearLayout) findViewById(R.id.layout);
        iv_mine = (ImageView) findViewById(R.id.iv_mine);
        iv_add = (ImageView) findViewById(R.id.iv_add);
        tv_title = (TextView) findViewById(R.id.tv_title);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_guo_shen_detail;
    }

    @Override
    protected BaseContract.Presenter initPresenter() {

        return null;
    }

    public void setPopDtailView(final List<NewDeatilsBean.RstBean> popModels) {


        setBackgroundAlpha(0.5f);
        View view  = getLayoutInflater().inflate(R.layout.guoshen_popupwindow,null);
        RecyclerView guorv = (RecyclerView) view.findViewById(R.id.guoshen_poprecyclerView);
        TextView jieqing = (TextView) view.findViewById(R.id.tv_update1);
        TextView deng = (TextView) view.findViewById(R.id.tv_update0);
        guorv.setLayoutManager(new LinearLayoutManager(GuoShenDetail.this));
        guorv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        final List<MyBean> list=new ArrayList<>();
        for (int i = 0; i < popModels.size(); i++) {
            list.add(new MyBean(false));
        }
        GuopopAdapter guopopAdapter=new GuopopAdapter(GuoShenDetail.this,popModels,list);
        guorv.setAdapter(guopopAdapter);
        final PopupWindow popupWindow=new PopupWindow(view,RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(layout, Gravity.BOTTOM,0,0);

        //修改为已结清
        jieqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<PutStats> putStats = new ArrayList<>();
                sum=0;
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).istrue){
                        putStats.add(new PutStats(popModels.get(i).id,1));
                        sum++;
                    }
                }
                if(sum!=0){
                    popupWindow.dismiss();
                    String json =  gson.toJson(putStats);
                    getpush(json);
                }

            }
        });

        deng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<PutStats> putStats = new ArrayList<>();

                sum=0;
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).istrue){
                        putStats.add(new PutStats(popModels.get(i).id,0));
                        sum++;
                    }
                }
                if(sum!=0){
                    popupWindow.dismiss();
                    String json =  gson.toJson(putStats);
                    getpush(json);
                }
            }
        });

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackgroundAlpha(1.0f);
            }
        });

    }
    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
    }

    //修改为已结清
    public void getpush(String str){

        Observable<Object> objectObservable = new NetRequestUtils().bucuo().getbaseretrofit().putStatus(str).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        objectObservable.subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {

                getdata(guoid);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
    //修改为待还款

}
