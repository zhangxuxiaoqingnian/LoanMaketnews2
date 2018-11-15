package com.smileflowpig.money.moneyplatfrom.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.smileflowpig.money.moneyplatfrom.frags.AlertFragment;
import com.smileflowpig.money.moneyplatfrom.util.MarkView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.PresenterActivity;
import com.smileflowpig.money.common.factory.presenter.BaseContract;
import com.smileflowpig.money.factory.bean.DetailBean;
import com.smileflowpig.money.factory.model.db.Dialogs;
import com.smileflowpig.money.factory.netword.NetRequestUtils;
import com.umeng.analytics.MobclickAgent;

public class NewDetailActivity extends PresenterActivity
        implements View.OnClickListener {

    private TextView time;
    private TextView give;
    private LinearLayout data;
    private EditText money;
    private ImageView icon;
    private TextView name;
    private TextView text;
    private MarkView mark;
    private TextView money1;
    private TextView timer;
    private TextView obser;
    private TextView lilv;
    private TextView type;
    private TextView success;
    private TextView cond;
    private TextView datum;
    private TextView state;
    private TextView title;
    private LinearLayout back;
    private TextView timetype;
    private DetailBean.RstBean rst;
    private LinearLayout moneylayout;
    private ImageView moneyedit;
    private LinearLayout collectlayout;
    private ImageView nocollect;
    private TextView apply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initview();

        back.setOnClickListener(this);
        moneyedit.setOnClickListener(this);
        collectlayout.setOnClickListener(this);
        apply.setOnClickListener(this);
        Intent intent = getIntent();
        String typeid = intent.getStringExtra("typeid");
        int type = intent.getIntExtra("type", -1);

        //屏蔽关闭输入法的时候，输入键盘同时关闭
        money.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_BACK){
                    money.clearFocus();
                }
                return false;
            }
        });


        //请求详情数据
        getdetails(typeid);


    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
    public void getdetails(String pos){

        Observable<DetailBean> detailBeanObservable = new NetRequestUtils().bucuo().getbaseretrofit().getdetail("", pos).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        detailBeanObservable.subscribe(new Observer<DetailBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(DetailBean detailBean) {

                rst = detailBean.rst;
                Glide.with(NewDetailActivity.this).load(rst.icon).into(icon);
                name.setText(rst.name);
                text.setText(rst.description);
                mark.setValue(rst.star);
                money1.setText("金额 （"+ rst.upper_amount+"-"+ rst.lower_amount+")");
                timer.setText("期限 （"+ rst.term+")");

                obser.setText(rst.lend_time);

                type.setText(rst.show_day);
                if(rst.show_day.equals("参考月利率")){
                    lilv.setText(rst.monthly_rate+"%");
                    timetype.setText("每月还款金额");
                }else {
                    lilv.setText(rst.day_rate+"%");
                    timetype.setText("每日还款金额");
                }

                success.setText("成功放款人数"+ rst.applicants+"人");
                cond.setText(rst.requirements);
                datum.setText(rst.document);
                state.setText(rst.explain);

//                if(rst.customer_service_number.equals("")){
//                    phone.setVisibility(View.GONE);
//                }else {
//                    phone.setText("客服电话："+ rst.customer_service_number);
//                }
                title.setText(rst.name);
                money.setText(rst.upper_amount+"");
                time.setText(rst.loan_period.get(0).v);

                if(rst.is_collection==0){
                    //未收藏
                    nocollect.setImageResource(R.mipmap.noshoucang);
                }else{
                    //已收藏
                    nocollect.setImageResource(R.mipmap.collect);
                }
                List<DetailBean.RstBean.LoanPeriodBean> loan_period = rst.loan_period;
                final List<Dialogs> list=new ArrayList<>();
                for (int i = 0; i < loan_period.size(); i++) {
                    list.add(new Dialogs(loan_period.get(i).k,loan_period.get(i).v));
                }
                data.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (rst.loan_period != null) {
                            createDialog(list);
                        }
                    }
                });

                count();

                //借钱的变化监听
                money.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {


                        String trim = s.toString().trim();
                        if(!trim.equals("")){
                            String s1 = time.getText().toString();
                            Pattern p = Pattern.compile("\\d+");
                            Matcher m = p.matcher(s1);
                            m.find();
                            double v2 = Double.parseDouble(m.group());
                            double v1 = Double.parseDouble(trim);
                            if(!TextUtils.isEmpty(trim)){
                                if (rst.show_day.equals("参考日利率")) {
                                    double c = Double.parseDouble(rst.day_rate);
                                    double ben = (v1+v1*v2*c/100)/v2;
                                    give.setText(String.format("%.2f", ben) + "元");
                                } else {
                                    double c = Double.parseDouble(rst.monthly_rate);
                                    double ben = (v1+v1*v2*c/100)/v2;
                                    give.setText(String.format("%.2f", ben) + "元");
                                }
                            }
                        }else {
                            give.setText(String.format("%.2f", 0.0) + "元");
                        }

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

    public void createDialog(final List<Dialogs> data) {
        AlertFragment alertFragment = new AlertFragment();
        alertFragment.setListener(new AlertFragment.OnSelectedListener() {
            @Override
            public void onSelectedItem(String k, String v) {

                time.setText(v);
                //选择之后重新计算
                count();

            }
        });
        alertFragment.setData(data);
        alertFragment.show(getSupportFragmentManager(), AlertFragment.class.getName());
    }

    public void count(){
        if(money.getText().toString().equals("")){
        }else {
            double a = Double.parseDouble(money.getText().toString());
            String s = time.getText().toString();
            Pattern p = Pattern.compile("\\d+");
            Matcher m = p.matcher(s);
            m.find();
            double b = Double.parseDouble(m.group());
            if (rst.show_day.equals("参考日利率")) {
                double c = Double.parseDouble(rst.day_rate);
                double ben = (a + b * c * a / 100)/b;
                give.setText(String.format("%.2f", ben) + "元");
            } else {
                double c = Double.parseDouble(rst.monthly_rate);
                double ben = (a + b * c * a / 100)/b;
                give.setText(String.format("%.2f", ben) + "元");
            }
        }
    }
    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_new_detail;
    }

    @Override
    protected BaseContract.Presenter initPresenter() {
        return null;
    }

    public void initview(){
        money = (EditText) findViewById(R.id.summoney);
        data = (LinearLayout) findViewById(R.id.ppdata);
        give = (TextView) findViewById(R.id.successgive);
        time = (TextView) findViewById(R.id.pptime);
        icon = (ImageView) findViewById(R.id.ppicon);
        name = (TextView) findViewById(R.id.ppname);
        text = (TextView) findViewById(R.id.pptext);
        mark = (MarkView) findViewById(R.id.ppmark);
        money1 = (TextView) findViewById(R.id.ppmoney);
        timer = (TextView) findViewById(R.id.pptimer);
        obser = (TextView) findViewById(R.id.pp_obser);
        lilv = (TextView) findViewById(R.id.pp_lilv);
        type = (TextView) findViewById(R.id.pp_lilvtype);
        success = (TextView) findViewById(R.id.pp_success);
        cond = (TextView) findViewById(R.id.pp_cond);
        datum = (TextView) findViewById(R.id.pp_datum);
        state = (TextView) findViewById(R.id.pp_state);
        title = (TextView) findViewById(R.id.tv_title);
        back = (LinearLayout) findViewById(R.id.detal_back);
        timetype = (TextView) findViewById(R.id.pp_timetype);
        moneyedit = (ImageView) findViewById(R.id.money_edit);
        collectlayout = (LinearLayout) findViewById(R.id.collect_layout);
        nocollect = (ImageView) findViewById(R.id.no_collect);
        apply = (TextView) findViewById(R.id.apply_success);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.detal_back:
                finish();
                break;
            case R.id.money_edit:
                money.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(money,0);
                String s = money.getText().toString();
                money.setSelection(s.length());
                break;
            case R.id.collect_layout:
                //收藏与取消收藏   先判断是否登录


                break;

        }
    }



}
