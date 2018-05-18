package money.kuxuan.platform.moneyplatfrom.activities;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import money.kuxuan.platform.common.app.Activity;
import money.kuxuan.platform.factory.model.api.guoshen.Average_Captial_Plus_InterestBean;
import money.kuxuan.platform.moneyplatfrom.R;

/**
 * Created by Allence on 2017/12/21 0021.
 */

public class Activity_Average_Capital_Interest extends Activity implements View.OnClickListener{


    @BindView(R.id.back)
    ImageView back;

    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.tv_monthleyPayments)
    TextView tv_monthleyPayments;

    @BindView(R.id.tv_interest)
    TextView tv_interest;

    @BindView(R.id.tv_repaments)
    TextView tv_repaments;

    @BindView(R.id.tv_totalloan)
    TextView tv_totalloan;

    @BindView(R.id.tv_rate)
    TextView tv_rate;

    @BindView(R.id.tv_date)
    TextView tv_date;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_average_capital_interest;
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

        tv_title.setText("等额本息");
        back.setOnClickListener(this);

    }


    @Override
    protected void initData() {
        super.initData();

        Average_Captial_Plus_InterestBean bean = (Average_Captial_Plus_InterestBean) getIntent().getSerializableExtra("duixiang");

        if(bean==null){
            return;
        }
        setView(bean);

    }



    private void setView(Average_Captial_Plus_InterestBean bean){

        tv_monthleyPayments.setText(bean.getMonthlyPayments()+"万");
        tv_interest.setText(bean.getPayInterest()+"万");
        tv_repaments.setText(bean.getRepayment()+"万");
        tv_totalloan.setText(bean.getTotalloan()+"万");
        tv_rate.setText(bean.getRate()+"%");
        tv_date.setText(bean.getRefundMonth()+"个月");

    }



    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.back:

                finish();

                break;


        }


    }
}
