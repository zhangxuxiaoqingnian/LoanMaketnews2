package com.smileflowpig.money.moneyplatfrom.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.smileflowpig.money.moneyplatfrom.util.ToastUtil;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.Activity;
import com.smileflowpig.money.common.widget.WheelView;
import com.smileflowpig.money.factory.model.api.guoshen.Average_CaptialBean;
import com.smileflowpig.money.factory.model.api.guoshen.Average_Captial_Plus_InterestBean;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by Allence on 2017/12/19 0019.
 */

public class Activity_HouseCalc extends Activity implements View.OnClickListener{

    View contentView;
    @BindView(R.id.tv_baseinterest)
    TextView tv_baseinterest;
    PopupWindow popupWindow;

    String[] rate = {"基准利率(4.90%)","7折利率","8折利率","8.3折利率","8.5折利率","8.8折利率","9折利率","9.5折利率","1.05倍利率","1.1倍利率","1.2倍利率","1.3倍利率"};
    float[] ratefloat ={0.049f,0.0343f,0.0392f,0.04067f,0.04165f,0.04312f,0.0441f,0.04655f,0.05145f,0.0539f,0.0588f,0.0637f};
    String selected;
    String[] date={"1年(12个月)","2年(24个月)","3年(36个月)","4年(48个月)","5年(60个月)","6年(72个月)","7年(84个月)","8年(96个月)",
            "9年(108个月)","10年(120个月)","11年(132个月)","12年(144个月)","13年(156个月)","14年(168个月)","15年(180个月)","16年(192个月)",
            "17年(204个月)","18年(216个月)","19年(228个月)","20年(240个月)","21年(252个月)","22年(264个月)","23年(276个月)","24年(288个月)",
            "25年(300个月)","26年(312个月)","27年(324个月)","28年(336个月)","29年(348个月)","30年(360个月)"};


    boolean flag;
    @BindView(R.id.tv_date)
    TextView tv_date;


    @BindView(R.id.rel_date)
    RelativeLayout rel_date;


    @BindView(R.id.radiogroup)
    RadioGroup radioGroup;


    @BindView(R.id.btn_calc)
    Button btn_calc;

    @BindView(R.id.back)
    ImageView back;

    @BindView(R.id.tv_title)
    TextView tv_title;
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected int getContentLayoutId() {

        return R.layout.activity_equalloan;
    }


    @Override
    protected void initBefore() {
        super.initBefore();
        ButterKnife.bind(this);

    }


    @BindView(R.id.et_moneynum)
    EditText et_moneynum;



    @Override
    protected void initWidget() {
        super.initWidget();
        initView();


    }



    private void initView(){

        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.rel_lilv);
        relativeLayout.setOnClickListener(this);
        rel_date.setOnClickListener(this);
//        radioGroup.setOnCheckedChangeListener(new MyCheckedChangeListener());
        btn_calc.setOnClickListener(this);
        back.setOnClickListener(this);
        tv_title.setText("房贷计算器");
    }



    int selectIndex1=0;
    int selectIndex2=0;
    private void showPopWindow(String[] arr){

        contentView = LayoutInflater.from(this).inflate(R.layout.mypopwindow,null,false);
        TextView tv_dismiss = (TextView) contentView.findViewById(R.id.tv_dismiss);
        tv_dismiss.setOnClickListener(this);

        TextView tv_ok = (TextView) contentView.findViewById(R.id.tv_ok);
        tv_ok.setOnClickListener(this);
        popupWindow = new PopupWindow(contentView, WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setAnimationStyle(R.style.pop_style);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            //在dismiss中恢复透明度
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());



        WheelView wva = (WheelView) contentView.findViewById(R.id.main_wv);
        wva.setOffset(1);
        wva.setItems(Arrays.asList(arr));
        wva.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {

                selected = item;
                if(flag){
                  selectIndex1 = selectedIndex-1;
                }else {
                   selectIndex2 =selectedIndex-1;
                }

            }
        });

        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.4f;
        getWindow().setAttributes(lp);

        popupWindow.showAtLocation(findViewById(R.id.lin_main), Gravity.BOTTOM, 0, 0);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.rel_lilv:
                flag = true;
                selected =rate[0];
                showPopWindow(rate);
                break;

            case R.id.tv_dismiss:
                if(popupWindow!=null&&popupWindow.isShowing())
                   popupWindow.dismiss();
                break;

            case R.id.tv_ok:
                if(flag) {
                    tv_baseinterest.setText(selected);
                }else {
                    tv_date.setText(selected);
                }

                if(popupWindow!=null&&popupWindow.isShowing())
                    popupWindow.dismiss();
                break;

            case R.id.rel_date:
                flag = false;
                selected =date[0];
                showPopWindow(date);
                break;

            case R.id.btn_calc:

                String et_money = et_moneynum.getText().toString().trim();
                if(et_money==null||et_money.equals("")){
                    ToastUtil.show(Activity_HouseCalc.this,"请输入贷款金额");
                    return;
                }

                if(radioButton1.isChecked()){

                    realcalcInterest(et_money,(selectIndex2+1)+"",ratefloat[selectIndex1]+"");

                }else if(radioButton2.isChecked()){

                    realcalcCapital(et_money,(selectIndex2+1)+"",ratefloat[selectIndex1]+"");

                }

                break;

            case R.id.back:

                if(popupWindow!=null&&popupWindow.isShowing()){
                    popupWindow.dismiss();
                }
                finish();
                hideSoftKeyboard();
                break;

        }


    }
    // 隐藏软件盘
    private void hideSoftKeyboard() {
        // 当前焦点的View
        View view = getCurrentFocus();
        if (view == null)
            return;

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void realcalcCapital(String moneynum, String year, String rate) {


        float money = Float.parseFloat(moneynum);
        int date = Integer.parseInt(year);
        float ratef = Float.parseFloat(rate);


        //        等额本金计算公式：每月还款金额 = （贷款本金 ÷ 还款月数）+（本金 — 已归还本金累计额）×每月利率

        //每月还的本金
        float monthlypaycapital = money/date/12;

        float monthlyrate = ratef/12;

        //每月月供的集合
        List<String> monthlypaymomeyList = new ArrayList<String>();
        DecimalFormat fnum   =   new DecimalFormat("##0.00");
        //总还款
        float totalPayMoney=0;

        for (int i=0;i<(date*12);i++){
            float mothlypaymomey = monthlypaycapital + (money - i*monthlypaycapital)*monthlyrate;
            String  mothlypaymomey_string = fnum.format(mothlypaymomey);
            monthlypaymomeyList.add(mothlypaymomey_string);
            totalPayMoney = totalPayMoney + mothlypaymomey;
        }


        //支付利息
        float payinterest = totalPayMoney-money;


        String payinterest_string = fnum.format(payinterest);
        String moneynum_string = fnum.format(money);
        String rate_String =  fnum.format(ratef);
        String totalpaymoney_string = fnum.format(totalPayMoney);
        Average_CaptialBean bean = new Average_CaptialBean(payinterest_string,totalpaymoney_string,moneynum_string,rate_String,(date*12)+"",monthlypaymomeyList);


        Intent intent = new Intent(Activity_HouseCalc.this,Activity_Average_Capital.class);
        intent.putExtra("duixiang",bean);
        startActivity(intent);


    }

    private void realcalcInterest(String moneynum,String year,String rate) {


        float money = Float.parseFloat(moneynum);
        int date = Integer.parseInt(year);
        float ratef = Float.parseFloat(rate);

        float monthrate = ratef/12;


        double monthlypayments = (money*(monthrate)*Math.pow((1+monthrate),date*12))/(Math.pow((1+monthrate),date*12)-1);

        DecimalFormat fnum   =   new DecimalFormat("##0.00");
        //月均还款
        String monthlypayments_string =fnum.format(monthlypayments);

        double repayment = monthlypayments*12*date;

        double interest =  repayment - money;

        //利息
        String interest_string =fnum.format(interest);
        //还款总额
        String repayment_string = fnum.format(repayment);

        String moneynum_string = fnum.format(money);
        String rate_String =  fnum.format(ratef);
        Average_Captial_Plus_InterestBean bean = new Average_Captial_Plus_InterestBean(monthlypayments_string,interest_string,repayment_string,moneynum_string,rate_String,(date*12)+"");


        Intent intent = new Intent(Activity_HouseCalc.this,Activity_Average_Capital_Interest.class);
        intent.putExtra("duixiang",bean);
        startActivity(intent);

    }

    @BindView(R.id.radiobutton1)
    RadioButton radioButton1;

    @BindView(R.id.radiobutton2)
    RadioButton radioButton2;




//    class  MyCheckedChangeListener implements RadioGroup.OnCheckedChangeListener{
//
//
//        @Override
//        public void onCheckedChanged(RadioGroup radioGroup, int id) {
//
//
//
//            switch (id){
//
//
//                case R.id.radiobutton1:
//
//
//                    break;
//
//                case R.id.radiobutton2:
//
//
//                    break;
//
//
//            }
//
//
//
//        }
//    }


}
