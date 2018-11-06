package com.smileflowpig.money.moneyplatfrom.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bumptech.glide.Glide;

import java.util.Date;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.PresenterActivity;
import com.smileflowpig.money.common.factory.presenter.BaseContract;
import com.smileflowpig.money.factory.bean.MemoShowBean;
import com.smileflowpig.money.factory.netword.NetRequestUtils;

import static com.smileflowpig.money.moneyplatfrom.activities.MyBillActivity.dateToString;

public class MemoTwoActivity extends PresenterActivity implements View.OnClickListener{

    @BindView(R.id.platform_text)
    TextView plattext;
    @BindView(R.id.platform)
    RelativeLayout platfrom;
    @BindView(R.id.use)
    EditText use;
    @BindView(R.id.use_money)
    EditText money;
    @BindView(R.id.use_data)
    EditText data;
    @BindView(R.id.memotime)
    RelativeLayout memotime;
    @BindView(R.id.memotext)
    TextView memotext;
    @BindView(R.id.use_interest)
    EditText interest;
    @BindView(R.id.memosuccess)
    TextView success;
    @BindView(R.id.memo2_back)
    ImageView back;
    @BindView(R.id.platform_icon)
    ImageView platform_icon;

    private boolean istrue=false;
    private String daiid;
    private boolean isfank=false;
    private String beiid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initview();

        Intent intent = getIntent();
        beiid = intent.getStringExtra("beiid");
        if(beiid.equals("")){
            isfank=false;
        }else {
            isfank=true;
            success.setText("保存");
            //请求原先数据
            getcontent(beiid);

        }

    }
    public void initview(){
        platfrom.setOnClickListener(this);
        memotime.setOnClickListener(this);
        success.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_memo_two;
    }

    @Override
    protected BaseContract.Presenter initPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //选择贷款平台
            case R.id.platform:
                istrue=true;
                Intent intent = new Intent(this,Activity_AddressBook.class);
                intent.putExtra("All_App",true);
                startActivity(intent);
                break;
            case R.id.memotime:
                //关闭软键盘
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                //时间选择器
                TimePickerView pvTime = new TimePickerBuilder(MemoTwoActivity.this, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        String formatType="yyyy-MM-dd";
                        String s = dateToString(date, formatType);
                        //获取当前时间
//                        Date over = new Date(System.currentTimeMillis());
//                        if(date.getTime()>=over.getTime()){
//                            memotext.setText(s);
//                        }else {
//                            Toast.makeText(MemoTwoActivity.this,"时间已过期",Toast.LENGTH_SHORT).show();
//                        }
                        memotext.setText(s);
                    }
                })
                        .setType(new boolean[]{true, true, true, false, false, false})
                        .setCancelText("取消")
                        .setSubmitText("确定")
                        .setTitleText("请选择时间")
                        .setOutSideCancelable(false)
                        .isCyclic(false)
                        .setTitleColor(Color.BLACK)
                        .setSubmitColor(Color.BLUE)
                        .setCancelColor(Color.BLUE)
                        .isCenterLabel(false)
                        .setLabel("年","月","日","时","分","秒")
                        .isDialog(false)
                        .build();
                pvTime.show();
                break;
                //提交信息
            case R.id.memosuccess:
                String s = plattext.getText().toString();
                String s1 = use.getText().toString();
                String s2 = money.getText().toString();
                String s3 = data.getText().toString();
                String s4 = memotext.getText().toString();
                String s5 = interest.getText().toString();
                if(s.equals("添加")|| TextUtils.isEmpty(s1)||TextUtils.isEmpty(s2)||TextUtils.isEmpty(s3)||TextUtils.isEmpty(s4)||TextUtils.isEmpty(s5)){
                    Toast.makeText(MemoTwoActivity.this,"请输入完整信息",Toast.LENGTH_SHORT).show();
                }else {
                    if(isfank){
                        //保存信息接口
                        getupdata(beiid,daiid,s1,s2,s3,s4,s5);
                    }else {
                        //走添加接口
                        getadddate(daiid,s1,s2,s3,s4,s5);
                    }

                }
                break;
            case R.id.memo2_back:
                finish();
                break;

        }
    }
    //获取数据
    public void getcontent(String id){

        Observable<MemoShowBean> objectObservable = new NetRequestUtils().bucuo().getbaseretrofit().getmemoshow(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        objectObservable.subscribe(new Observer<MemoShowBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MemoShowBean o) {
                MemoShowBean.RstBean rst = o.rst;

                Glide.with(MemoTwoActivity.this).load(rst.platform_icon).into(platform_icon);
                plattext.setText(rst.platform_name);
                use.setText(rst.how_to_use);
                money.setText(rst.how_much);
                data.setText(rst.how_long);
                memotext.setText(rst.due_date);
                interest.setText(rst.gross_interest);
                daiid=rst.platform_id;

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }
    //保存修改
    public void getupdata(String t,String t2,String t3,String t4,String t5,String t6,String t7){

        Observable<Object> memoShowBeanObservable = new NetRequestUtils().bucuo().getbaseretrofit().getmemoupdata(t, t2, t3, t4, t5, t6, t7).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        memoShowBeanObservable.subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {
                Toast.makeText(MemoTwoActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


    public void getadddate(String t,String t2,String t3,String t4,String t5,String t6){

        Observable<Object> memoBeanObservable = new NetRequestUtils().bucuo().getbaseretrofit().getmemoadd(t, t2, t3, t4, t5, t6).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        memoBeanObservable.subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {

                Toast.makeText(MemoTwoActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                finish();
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

        if(istrue){
            SharedPreferences sharedPreferences=getSharedPreferences("DaiData",MODE_PRIVATE);
            String daiimg = sharedPreferences.getString("daiimg", null);
            String dainame = sharedPreferences.getString("dainame", null);
            daiid = sharedPreferences.getString("daiid", null);

            plattext.setText(dainame);
            Glide.with(MemoTwoActivity.this).load(daiimg).into(platform_icon);

        }



    }
}
