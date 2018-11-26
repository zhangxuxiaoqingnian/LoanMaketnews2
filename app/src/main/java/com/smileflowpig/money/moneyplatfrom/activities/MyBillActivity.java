package com.smileflowpig.money.moneyplatfrom.activities;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.smileflowpig.money.factory.netword.NetRequestUtils;
import com.umeng.analytics.MobclickAgent;

public class MyBillActivity extends PresenterActivity implements OnClickListener{

    private TextView tv_title;
    private ImageView iv_add;
    private ImageView iv_mine;
    private RelativeLayout tt;
    private TextView typetext;
    private LinearLayout layout;
    private EditText title;
    private TextView time;
    private EditText price;
    private EditText address;
    private EditText bei;
    private EditText man;
    private EditText phone;
    private TextView jiao;
    private int sum=0;
    private RelativeLayout timeover;
    private TextView fa;
    @Override
    protected boolean isNeedNotch() {
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initview();
        iv_mine.setImageResource(R.drawable.guoshen_back);
        iv_add.setVisibility(View.GONE);

        iv_mine.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                finish();
            }
        });
        tv_title.setText("发布订单");
        tt.setOnClickListener(this);
        jiao.setOnClickListener(this);
        timeover.setOnClickListener(this);
    }


    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
    public void initview(){

        iv_mine = (ImageView) findViewById(R.id.iv_mine);
        iv_add = (ImageView) findViewById(R.id.iv_add);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tt = (RelativeLayout) findViewById(R.id.selecttype);
        typetext = (TextView) findViewById(R.id.typeok);
        layout = (LinearLayout) findViewById(R.id.select_layout);
        title = (EditText) findViewById(R.id.idittitle);
        time = (TextView) findViewById(R.id.idittime);
        price = (EditText) findViewById(R.id.iditprice);
        address = (EditText) findViewById(R.id.iditaddress);
        bei = (EditText) findViewById(R.id.iditbei);
        man = (EditText) findViewById(R.id.iditman);
        phone = (EditText) findViewById(R.id.iditphone);
        jiao = (TextView) findViewById(R.id.getjiao);
        timeover = (RelativeLayout) findViewById(R.id.timeover);
    }
    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_my_bill;
    }

    @Override
    protected BaseContract.Presenter initPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.selecttype:
                //弹出选择类型框
                getpopuwindow();
                break;
            case R.id.getjiao:
                String tit = title.getText().toString();
                String tim = time.getText().toString();
                String pr = price.getText().toString();
                String add = address.getText().toString();
                String be = bei.getText().toString();
                String ma = man.getText().toString();
                String ph = phone.getText().toString();
                if(TextUtils.isEmpty(typetext.getText())||TextUtils.isEmpty(tit)||TextUtils.isEmpty(tim)||TextUtils.isEmpty(pr)
                        ||TextUtils.isEmpty(add)||TextUtils.isEmpty(be)||TextUtils.isEmpty(ma)||TextUtils.isEmpty(ph)){
                    Toast.makeText(MyBillActivity.this,"请输入完整信息",Toast.LENGTH_SHORT).show();
                }else {

                    if(isPhoneNumber(ph)){

                        //提交订单
                        if(typetext.getText().toString().equals("顺购")){
                            sum=1;
                        }else if(typetext.getText().toString().equals("排队")){
                            sum=2;
                        }else {
                            sum=3;
                        }
                        float v1 = Float.parseFloat(pr);
                        getselect(sum,tit,tim,v1,add,be,ma,ph);
                    }else {
                        Toast.makeText(MyBillActivity.this,"请输入正确手机号",Toast.LENGTH_SHORT).show();

                    }

                }
                break;
            case R.id.timeover:
                 //关闭软键盘
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                //时间选择器
                TimePickerView pvTime = new TimePickerBuilder(MyBillActivity.this, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        String formatType="yyyy-MM-dd HH:mm";
                        String s = dateToString(date, formatType);
                        //获取当前时间
                        Date over = new Date(System.currentTimeMillis());

                        if(date.getTime()>=over.getTime()){
                            time.setText(s);
                        }else {
                            Toast.makeText(MyBillActivity.this,"时间已过期",Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                        .setType(new boolean[]{true, true, true, true, true, false})
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
        }
    }

    public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
    }
    public void getselect(int type,String title,String time,float price,String address,String bei,String man,String phone){

        Observable<Object> objectObservable = new NetRequestUtils().bucuo().getbaseretrofit().setlist(type, address, bei, price, time, man, phone, title).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        objectObservable.subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {

                Toast.makeText(MyBillActivity.this,"发布成功",Toast.LENGTH_SHORT).show();
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

    public void getpopuwindow(){

        View inflate = LayoutInflater.from(MyBillActivity.this).inflate(R.layout.type_layout, null, false);
        final PopupWindow popupWindow=new PopupWindow(inflate,WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setAnimationStyle(R.style.pop_anim);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(layout,Gravity.BOTTOM,0,0);
        light(0.8f);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

                light(1.0f);
            }
        });
        TextView shun = (TextView) inflate.findViewById(R.id.shun);
        TextView paidui = (TextView) inflate.findViewById(R.id.paidui);
        TextView other = (TextView) inflate.findViewById(R.id.other);

        shun.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                typetext.setText("顺购");
            }
        });
        paidui.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                typetext.setText("排队");
            }
        });
        other.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                typetext.setText("其他");
            }
        });
    }

    public void light(float t){
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = t;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }

    public static boolean isPhoneNumber(String phoneNo) {
        if (TextUtils.isEmpty(phoneNo)) {
            return false;
        }
        if (phoneNo.length() == 11) {
            for (int i = 0; i < 11; i++) {
                if (!PhoneNumberUtils.isISODigit(phoneNo.charAt(i))) {
                    return false;
                }
            }
            Pattern p = Pattern.compile("^((13[^4,\\D])" + "|(134[^9,\\D])" +
                    "|(14[5,7])" +
                    "|(15[^4,\\D])" +
                    "|(17[3,6-8])" +
                    "|(18[0-9]))\\d{8}$");
            Matcher m = p.matcher(phoneNo);
            return m.matches();
        }
        return false;
    }

}
