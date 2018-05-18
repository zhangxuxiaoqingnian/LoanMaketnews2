package money.kuxuan.platform.moneyplatfrom.activities;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import money.kuxuan.platform.common.app.Activity;
import money.kuxuan.platform.factory.model.api.guoshen.Average_Captial_Plus_InterestBean;
import money.kuxuan.platform.moneyplatfrom.R;

/**
 * Created by Allence on 2017/12/21 0021.
 */

public class Activity_Average_Capital_Interest_Calc extends Activity implements View.OnClickListener {
    @BindView(R.id.back)
    ImageView back;

    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.et_moneynum)
    EditText et_moneynum;

    @BindView(R.id.et_year)
    EditText et_year;

    @BindView(R.id.et_rate)
    EditText et_rate;

    @BindView(R.id.btn_calc)
    Button btn_calc;


    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_average_capital_calc;
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

        tv_title.setText("等额本息计算器");
        back.setOnClickListener(this);
        btn_calc.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.back:

                finish();

                break;

            case R.id.btn_calc:

                calc();

                break;



        }


    }

    private void calc() {

        String moneynum = et_moneynum.getText().toString().trim();
        String year = et_year.getText().toString().trim();
        String rate = et_rate.getText().toString().trim();

        if(moneynum==null||moneynum.equals("")){

            Toast.makeText(Activity_Average_Capital_Interest_Calc.this,"请输入贷款金额",Toast.LENGTH_SHORT).show();
            return;
        }

        if(year==null||year.equals("")){

            Toast.makeText(Activity_Average_Capital_Interest_Calc.this,"请输入贷款年限",Toast.LENGTH_SHORT).show();
            return;
        }

        if(rate==null||rate.equals("")){

            Toast.makeText(Activity_Average_Capital_Interest_Calc.this,"请输入贷款利率",Toast.LENGTH_SHORT).show();
            return;
        }
     //TODO 计算


//        等额本息计算公式：〔贷款本金×月利率×（1＋月利率）＾还款月数〕÷〔（1＋月利率）＾还款月数－1〕

        realcalc(moneynum,year,rate);



    }

    private void realcalc(String moneynum,String year,String rate) {

        float money = Float.parseFloat(moneynum);
        int date = Integer.parseInt(year);
        float ratef = Float.parseFloat(rate);

        float monthrate = ratef/12/100;



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

        Intent intent = new Intent(Activity_Average_Capital_Interest_Calc.this,Activity_Average_Capital_Interest.class);

        intent.putExtra("duixiang",bean);

        startActivity(intent);



    }
}
