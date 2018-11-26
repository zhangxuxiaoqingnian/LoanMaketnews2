package com.smileflowpig.money.moneyplatfrom.activities;

import android.content.Context;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.smileflowpig.money.moneyplatfrom.util.ToastUtil;

import java.text.DecimalFormat;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.Activity;
import com.smileflowpig.money.common.widget.WheelView;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by Allence on 2017/12/21 0021.
 */

public class Activity_loanCarCalc extends Activity implements View.OnClickListener {

    @BindView(R.id.back)
    ImageView back;

    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.rel_firstpay)
    RelativeLayout rel_firstpay;

    @BindView(R.id.rel_loandate)
    RelativeLayout rel_loandate;

    @BindView(R.id.rel_loanrate)
    RelativeLayout rel_loanrate;

    @BindView(R.id.btn_calc)
    Button btn_calc;

    @BindView(R.id.tv_firstpay)
    TextView tv_firstpay;

    @BindView(R.id.tv_loandate)
    TextView tv_loandate;

    @BindView(R.id.tv_loanrate)
    TextView tv_loanrate;

    @BindView(R.id.et_moneynum)
    EditText et_moneynum;
    @Override
    protected boolean isNeedNotch() {
        return true;
    }

    int flag;


    String[] firstpayarr = {"2","3","4","5","6","7","8","9"};
    String[] loandatearr = {"1","2","3","4","5"};
    String[] loanrate = {"基础利率","9.5折","9折","8.5折","8折","7.5折","6.5折","6折","5.5折","5折"};
    float[] loanratefloat ={0.0435f,0.041325f,0.03915f,0.036975f,0.0348f,0.032625f,0.028275f,0.0261f,0.023925f,0.02175f};


    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_carloan_calc;
    }


    @Override
    protected void initBefore() {
        super.initBefore();
        ButterKnife.bind(this);
    }


    @Override
    protected void initWidget() {
        super.initWidget();

        initView();
    }

    private void initView() {

        tv_title.setText("车贷计算器");
        back.setOnClickListener(this);
        rel_firstpay.setOnClickListener(this);
        rel_loandate.setOnClickListener(this);
        rel_loanrate.setOnClickListener(this);
        btn_calc.setOnClickListener(this);


    }
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    View contentView;
    PopupWindow popupWindow;
    String selected;
    int selectindex=0;
    int selectindex1=0;
    int selectindex2=0;

    private void showPopWindow(String[] arr){

        contentView = LayoutInflater.from(this).inflate(R.layout.mypopwindow,null,false);
        TextView tv_dismiss = (TextView) contentView.findViewById(R.id.tv_dismiss);
        tv_dismiss.setOnClickListener(this);

        TextView tv_tagname = (TextView) contentView.findViewById(R.id.tv_tagname);

        if(flag==0){

            tv_tagname.setText("首付比例");

        }else if(flag==1){

            tv_tagname.setText("贷款年限");

        }else if(flag==2){

            tv_tagname.setText("贷款利率(%)");

        }


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

                if(flag==0){
                  selectindex = selectedIndex-1;
                }else if (flag==1){
                    selectindex1 = selectedIndex-1;
                }else if(flag==2){
                    selectindex2 = selectedIndex-1;
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

            case R.id.back:

                if(popupWindow!=null&&popupWindow.isShowing()){
                    popupWindow.dismiss();
                }

                finish();
                hideSoftKeyboard();
                break;

            case R.id.rel_firstpay:

                flag = 0;
                selected =firstpayarr[0];
                showPopWindow(firstpayarr);

                break;


            case  R.id.rel_loandate:
                flag = 1;
                selected = loandatearr[0];
                showPopWindow(loandatearr);

                break;

            case R.id.rel_loanrate:

                flag = 2;
                selected = loanrate[0];
                showPopWindow(loanrate);

                break;

            case R.id.btn_calc:

                String et_money = et_moneynum.getText().toString().trim();
                if(et_money==null||et_money.equals("")){
                    ToastUtil.show(Activity_loanCarCalc.this,"请输入金额");
                    return;
                }


                String firstpay = tv_firstpay.getText().toString().trim();
                String loandate = tv_loandate.getText().toString().trim();
                String loanrate = tv_loanrate.getText().toString().trim();
                if(firstpay==null||firstpay.equals("")){
                    ToastUtil.show(Activity_loanCarCalc.this,"请选择首付比例");
                    return;
                }

                if(loandate==null||loandate.equals("")){
                    ToastUtil.show(Activity_loanCarCalc.this,"请选择贷款年限");
                    return;
                }

                if(loanrate==null||loanrate.equals("")){
                    ToastUtil.show(Activity_loanCarCalc.this,"请选择贷款利率");
                    return;
                }

                realCalc(et_money,selectindex+2,selectindex1+1,loanratefloat[selectindex2]);

                break;

            case R.id.tv_ok:

                if(flag==0){

                      tv_firstpay.setText(selected);

                }else if (flag == 1){

                    tv_loandate.setText(selected);

                }else if (flag == 2){

                    tv_loanrate.setText(selected);

                }


                if(popupWindow!=null&&popupWindow.isShowing())
                    popupWindow.dismiss();
                break;

            case R.id.tv_dismiss:
                if(popupWindow!=null&&popupWindow.isShowing())
                    popupWindow.dismiss();
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


    @BindView(R.id.tv_firstpart)
    TextView tv_firstpart;

    private void realCalc(String moneynum,int part,int loandate,float loanrate) {


        int money = Integer.parseInt(moneynum);

        //首付
        float firstpayfloat = money*part/10;

        tv_firstpart.setText(firstpayfloat+"");

        float loanmoney = money - firstpayfloat;

        realcalcinterest(firstpayfloat,loanmoney,loandate,loanrate);



    }



    @BindView(R.id.tv_monthlypay)
    TextView tv_monthlypay;
    @BindView(R.id.tv_interest)
    TextView tv_interest;

    @BindView(R.id.tv_paytotal)
    TextView tv_paytotal;

    private void realcalcinterest(float firstpayfloat,float money,int date,float ratef) {


        float monthrate = ratef/12;

        double monthlypayments = (money*(monthrate)*Math.pow((1+monthrate),date*12))/(Math.pow((1+monthrate),date*12)-1);

        DecimalFormat fnum   =   new DecimalFormat("##0.00");
        //月供
        String monthlypayments_string =fnum.format(monthlypayments);

        tv_monthlypay.setText(monthlypayments_string);

        double repayment = monthlypayments*12*date;

        double interest =  repayment - money;

        //利息
        String interest_string =fnum.format(interest);
        tv_interest.setText(interest_string);



        double sum = repayment + firstpayfloat;

        //还款总额
        String sum_string = fnum.format(sum);

        tv_paytotal.setText(sum_string);


    }













}
