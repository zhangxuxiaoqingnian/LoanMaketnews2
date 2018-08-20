package money.kuxuan.platform.moneyplatfrom.activities;

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
import money.kuxuan.platform.common.app.Activity;
import money.kuxuan.platform.moneyplatfrom.R;

/**
 * Created by Allence on 2017/12/21 0021.
 */

public class Activity_Income_Tax_Calc extends Activity implements View.OnClickListener {
    @BindView(R.id.back)
    ImageView back;

    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.btn_calc)
    Button btn_calc;

    @BindView(R.id.et_moneynum)
    EditText et_moneynum;

    @BindView(R.id.tv_threshold)
    EditText tv_threshold;

    @BindView(R.id.tv_income)
    TextView tv_income;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_incom_tax_calc;
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

        tv_title.setText("个税计算器");
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
        String money1 = et_moneynum.getText().toString().trim();

        String money2 = tv_threshold.getText().toString().trim();


        if(money1==null||money1.equals("")){

            Toast.makeText(Activity_Income_Tax_Calc.this,"请输入工资金额",Toast.LENGTH_SHORT).show();

            return;
        }


        if(money2==null||money2.equals("")){

            Toast.makeText(Activity_Income_Tax_Calc.this,"请输入起征点金额",Toast.LENGTH_SHORT).show();
           return;
        }

        int salary = Integer.parseInt(money1);
        int threshold = Integer.parseInt(money2);

        if(threshold>salary){

            tv_income.setText("0.00");
            return;
        }


        realCalc(salary,threshold);


    }

    float result = 0;
    private void realCalc(int firstNum,int secondNum) {




       int incomeTax = firstNum - secondNum;


       if(incomeTax<=1500){

           result = (float) (incomeTax*0.03);

       }else if(incomeTax<=4500){

           result = (float) (incomeTax*0.1-105);

       }else if(incomeTax<=9000){

           result = (float) (incomeTax*0.2-555);

       }else if(incomeTax<=35000){

           result = (float) (incomeTax*0.25-1005);

       }else if(incomeTax<=55000){

           result = (float) (incomeTax*0.3-2755);

       }else if(incomeTax<=80000){

           result = (float) (incomeTax*0.35-5505);

       }else {

           result = (float) (incomeTax*0.45-13505);

       }




        DecimalFormat   fnum   =   new DecimalFormat("##0.00");
        String   dd =fnum.format(result);


        tv_income.setText(dd);


    }



}
