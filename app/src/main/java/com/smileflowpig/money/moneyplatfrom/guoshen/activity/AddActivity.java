package com.smileflowpig.money.moneyplatfrom.guoshen.activity;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.smileflowpig.money.moneyplatfrom.util.PickerUtil;
import com.smileflowpig.money.moneyplatfrom.util.StringUtils;
import com.smileflowpig.money.moneyplatfrom.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;
import cn.addapp.pickers.picker.DatePicker;
import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.PresenterActivity;
import com.smileflowpig.money.factory.model.api.guoshen.AddModel;
import com.smileflowpig.money.factory.model.api.guoshen.AddModel1;
import com.smileflowpig.money.factory.model.api.guoshen.GetRepaymentModel;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by Allence on 2018/5/8 0008.
 */

public class AddActivity extends PresenterActivity<AddConreact.Presenter> implements AddConreact.View{


    @Override
    protected AddConreact.Presenter initPresenter() {
        return new AddPresenter(this);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.guoshen_add;
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
    @BindView(R.id.iv_mine)
    ImageView iv_mine;

    @BindView(R.id.iv_add)
    ImageView iv_add;

    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.et_name)
    EditText et_name;

    @BindView(R.id.et_money)
    EditText et_money;

    @BindView(R.id.et_jkqs)
    EditText et_jkqs;

    @BindView(R.id.et_rateMonthly)
    EditText et_rateMonthly;

    @BindView(R.id.tv_choiseTime)
    TextView tv_choiseTime;

    int id1;
    @Override
    protected void initWidget() {
        super.initWidget();

        initTitle();

        id1 = getIntent().getIntExtra("id",-1);

        if(id1!=-1){
            mPresenter.getRepayment(id1);
        }

    }


    @OnClick(R.id.iv_mine)
    public void back(){
        finish();
    }

    private void initTitle() {

       iv_mine.setImageResource(R.drawable.guoshen_back);
       iv_add.setVisibility(View.GONE);


        boolean nilang = getIntent().getBooleanExtra("nilang", false);
        if(nilang){
            tv_title.setText("添加");
        }else {
            tv_title.setText("修改");
        }

    }

    String daytime;
    @OnClick(R.id.tv_choiseTime)
    public void choideTime(){

        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR); // 获取当前年份
        int mMonth = c.get(Calendar.MONTH) + 1;// 获取当前月份
        int mDay = c.get(Calendar.DAY_OF_MONTH);// 获取当日期

        PickerUtil.onYearMonthDayPicker("选择日期", mYear, mMonth, mDay, this, new DatePicker.OnYearMonthDayPickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onDatePicked(String s, String s1, String s2) {

                daytime = s + "-" + s1 + "-" + s2 ;
                tv_choiseTime.setTextColor(R.color.textSecond);
                tv_choiseTime.setText(daytime);

            }
        });


    }



    @OnClick(R.id.tv_commit)
    public void commit(){

        String name = et_name.getText().toString().trim();

        if(StringUtils.isEmpty(name)){
            ToastUtil.show(getApplicationContext(),"请输入平台名称");
            return;
        }
        String money = et_money.getText().toString().trim();
        if(StringUtils.isEmpty(money)){
            ToastUtil.show(getApplicationContext(),"请输入借款金额");
            return;
        }
        String count = et_jkqs.getText().toString().trim();
        if(StringUtils.isEmpty(count)){
            ToastUtil.show(getApplicationContext(),"请输入借款期数");
            return;
        }
        String rate = et_rateMonthly.getText().toString().trim();
        if(StringUtils.isEmpty(rate)){
            ToastUtil.show(getApplicationContext(),"请输入月利率");
            return;
        }
        String choiseTime = tv_choiseTime.getText().toString().trim();
        if(StringUtils.isEmpty(choiseTime)||choiseTime.equals("选择时间")){
            ToastUtil.show(getApplicationContext(),"请选择时间");
            return;
        }

        //float money_int = Float.parseFloat(money);
        float rate_float = Float.parseFloat(rate);
        rate = StringUtils.formatFloatNumber(rate_float);
        rate_float = Float.parseFloat(rate);
//        AddModel addModel = new AddModel(name,count,money_int,rate_float,choiseTime);
//
//        if(id1!=-1){
//            AddModel1 addModel1 = new AddModel1(id1,name,count,money_int,rate_float,choiseTime);
//            mPresenter.addRepayment1(addModel1);
//            return;
//        }
        //mPresenter.addRepayment(addModel);
    }


    public static void show(Context context){
        Intent intent = new Intent(context, AddActivity.class);
        intent.putExtra("nilang",true);
        context.startActivity(intent);

    }

    public static void show(Context context,int id){

        Intent intent = new Intent(context, AddActivity.class);
        intent.putExtra("id",id);
        context.startActivity(intent);

    }


    @Override
    public void setAddEndView() {

        ToastUtil.show(getApplicationContext(),"添加成功");
        EventBus.getDefault().post(new GetRepaymentModel());
        finish();

    }

    @Override
    public void setRepaymentView(GetRepaymentModel repaymentBean) {

        et_name.setText(repaymentBean.getPlatform()+"");
        et_jkqs.setText(repaymentBean.getPeriods()+"");
        et_money.setText(repaymentBean.getAmount()+"");
        et_rateMonthly.setText(repaymentBean.getMonthly_rate()+"");
        String date = repaymentBean.getFirst_time();
        String[] dateArr = date.split(" ");
        tv_choiseTime.setText(dateArr[0]);

    }
}
