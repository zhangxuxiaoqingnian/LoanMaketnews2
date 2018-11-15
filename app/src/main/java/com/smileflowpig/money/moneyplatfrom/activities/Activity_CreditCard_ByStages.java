package com.smileflowpig.money.moneyplatfrom.activities;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.Activity;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by Allence on 2017/12/21 0021.
 */

public class Activity_CreditCard_ByStages extends Activity implements View.OnClickListener {
    @BindView(R.id.back)
    ImageView back;

    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.et_moneynum)
    EditText et_moneynum;

    @BindView(R.id.btn_calc)
    Button btn_calc;

    @BindView(R.id.et_date)
    EditText et_date;

    @BindView(R.id.et_rate)
    EditText et_rate;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_credicard_bystages_calc;
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

        tv_title.setText("分期计算器");
        back.setOnClickListener(this);
        btn_calc.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.back:

                finish();
                hideSoftKeyboard();
                break;

            case R.id.btn_calc:


                calc();


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

    private void calc() {

        String moneynum = et_moneynum.getText().toString().trim();
        String date = et_date.getText().toString().trim();
        String rate = et_rate.getText().toString().trim();

        if(moneynum==null||moneynum.equals("")){

            Toast.makeText(Activity_CreditCard_ByStages.this,"请输入分期金额",Toast.LENGTH_SHORT).show();
            return;
        }

        if(date==null||date.equals("")){

            Toast.makeText(Activity_CreditCard_ByStages.this,"请输入分期期数",Toast.LENGTH_SHORT).show();
            return;
        }

        if(rate==null||rate.equals("")){

            Toast.makeText(Activity_CreditCard_ByStages.this,"请输入月利率",Toast.LENGTH_SHORT).show();
            return;
        }
        
        
        realCalc(moneynum,date,rate);
        

    }



    @BindView(R.id.tv_average)
    TextView tv_average;

    @BindView(R.id.paytoalmoney)
    TextView paytoalmoney;

    @BindView(R.id.tv_interest)
    TextView tv_interest;

    private void realCalc(String moneynum,String date,String rate) {

        DecimalFormat fnum   =   new DecimalFormat("##0.00");
        float money = Float.parseFloat(moneynum);
        int dateint = Integer.parseInt(date);
        float ratef = Float.parseFloat(rate);


        float average = (money/dateint) + (money*ratef/100);

        String average_string = fnum.format(average);

        tv_average.setText(average_string);

        float sum = average * dateint;
        String sum_string = fnum.format(sum);
        //paytoalcom.setText(sum_string);

        float interest = sum - money;
        String interest_string = fnum.format(interest);

        tv_interest.setText(interest_string);



    }
}
