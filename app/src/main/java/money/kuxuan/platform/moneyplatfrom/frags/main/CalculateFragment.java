package money.kuxuan.platform.moneyplatfrom.frags.main;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import money.kuxuan.platform.common.app.Fragment;
import money.kuxuan.platform.common.factory.model.Calculate;
import money.kuxuan.platform.common.widget.TextWatcherAdapter;
import money.kuxuan.platform.factory.Factory;
import money.kuxuan.platform.moneyplatfrom.Adapter.CalculateAdapter;
import money.kuxuan.platform.moneyplatfrom.R;

/**

 */
public class CalculateFragment extends Fragment {

    @BindView(R.id.loan_amount)
    EditText loan_amount;
    @BindView(R.id.term_of_loan)
    EditText term_of_loan;
    @BindView(R.id.monthly_fee_rate)
    EditText monthly_fee_rate;
    @BindView(R.id.calculate_listview)
    ListView calculate_listview;
    @BindView(R.id.sum)
    TextView sum;
    @BindView(R.id.error)
    TextView error;
    @BindView(R.id.left_button)
    Button left_button;
    @BindView(R.id.right_button)
    Button right_button;

    @BindView(R.id.back)
    ImageView back;



    boolean havechange1 = true;
    boolean havechange2 = true;
    boolean havechange3 = true;
    int flag=1;

    List<Calculate> calculateList;
    CalculateAdapter mAdapter;
    String loan;
    String term;
    private static final String CHANNEL = "CHANNEL";

    private static final String CHANNELOKORNOTOK = "CHANNELOKORNOTOK";
    String monthly_fee;
    double imonthly_fee;
    int iterm;
    int iloan;
    double num ;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public CalculateFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_calculate;
    }

    public void setMargin(){

    }

    @OnClick(R.id.left_button)
    void onLeftButton(){
        left_button.setBackgroundResource(R.drawable.button_shape_half);
        left_button.setTextColor(getResources().getColor(R.color.white));
        right_button.setBackgroundResource(R.drawable.button_shape_half2);
        right_button.setTextColor(getResources().getColor(R.color.bu_yellow_bg));
        flag = 1;
        calculateByOne();
    }

    @OnClick(R.id.right_button)
    void onRightButton(){
        right_button.setBackgroundResource(R.drawable.button_shape_half_3);
        left_button.setBackgroundResource(R.drawable.button_shape_half_4);
        right_button.setTextColor(getResources().getColor(R.color.white));
        left_button.setTextColor(getResources().getColor(R.color.bu_yellow_bg));
        flag =2;
        calculateByTwo();
    }
    @OnClick(R.id.back)
    void OnClick(){
        getActivity().finish();
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        if(!checkChannel()){
            back.setVisibility(View.GONE);
        }
        loan_amount.addTextChangedListener(new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                loan = s.toString();
                if(s.toString().equals("")){
                    havechange1 =false;
                    calculate_listview.setVisibility(View.GONE);
                    error.setVisibility(View.VISIBLE);
                    sum.setText("0.00");
                    return;
                }
                else if(s.length()>0&&s.toString().substring(0,1).equals("0")){
                    havechange1 =false;
                    calculate_listview.setVisibility(View.GONE);
                    error.setVisibility(View.VISIBLE);
                    sum.setText("0.00");
                    return;
                }else{
                    havechange1 = true;
                }
                if(havechange1==true&&havechange2==true&&havechange3==true) {
                    if (flag == 1) {
                        calculateByOne();
                    } else {
                        calculateByTwo();
                    }
                    calculate_listview.setVisibility(View.VISIBLE);
                    error.setVisibility(View.GONE);
                }
            }
        });
        term_of_loan.addTextChangedListener(new TextWatcherAdapter() {

            @Override
            public void afterTextChanged(Editable s) {
                term = s.toString();

                if(s.toString().equals("")){
                    havechange2 =false;
                    calculate_listview.setVisibility(View.GONE);
                    error.setVisibility(View.VISIBLE);
                    sum.setText("0.00");
                    return;
                }
                else if(s.length()>0&&s.toString().substring(0,1).equals("0")){
                    havechange2 =false;
                    calculate_listview.setVisibility(View.GONE);
                    error.setVisibility(View.VISIBLE);
                    sum.setText("0.00");
                    return;
                }else{
                    havechange2 = true;
                }
                if(havechange1==true&&havechange2==true&&havechange3==true) {
                    if (flag == 1) {
                        calculateByOne();
                    } else {
                        calculateByTwo();
                    }
                    calculate_listview.setVisibility(View.VISIBLE);
                    error.setVisibility(View.GONE);

                }

            }
        });
        monthly_fee_rate.addTextChangedListener(new TextWatcherAdapter() {


            @Override
            public void afterTextChanged(Editable s) {
                monthly_fee = s.toString();
                if (s.toString().trim().substring(0).equals(".")) {
                    calculate_listview.setVisibility(View.GONE);
                    havechange3 =false;
                    error.setVisibility(View.VISIBLE);
                    sum.setText("0.00");
                    return;
                }
                if(s.toString().equals("")){
                    havechange3 =false;
                    calculate_listview.setVisibility(View.GONE);
                    error.setVisibility(View.VISIBLE);
                    sum.setText("0.00");
                    return;
                }
                else if(s.length()>0&&Double.parseDouble(s.toString())==0){
                    calculate_listview.setVisibility(View.GONE);
                    havechange3 =false;
                    error.setVisibility(View.VISIBLE);
                    sum.setText("0.00");
                    return;
                }else{
                    havechange3 = true;
                }
                if(havechange1==true&&havechange2==true&&havechange3==true) {
                    if (flag == 1) {
                        calculateByOne();
                    } else {
                        calculateByTwo();
                    }
                    calculate_listview.setVisibility(View.VISIBLE);
                    error.setVisibility(View.GONE);

                }
            }
        });

        loan = loan_amount.getText().toString();
        term = term_of_loan.getText().toString();
        monthly_fee = monthly_fee_rate.getText().toString();
        imonthly_fee = Double.parseDouble(monthly_fee)/100;
        iterm = Integer.parseInt(term);
        iloan = Integer.parseInt(loan);
        calculateByOne();
    }

    private void calculateByTwo(){
        if(!(havechange1&&havechange2&&havechange3)){
            return;
        }else{
            calculateList = new ArrayList<Calculate>();
            loan = loan_amount.getText().toString();
            term = term_of_loan.getText().toString();
            monthly_fee = monthly_fee_rate.getText().toString();
            imonthly_fee = Double.parseDouble(monthly_fee)/100;
            iterm = Integer.parseInt(term);
            iloan = Integer.parseInt(loan);
            num=0;
            for(int i=0;i<iterm;i++){
                Calculate c = new Calculate();
                c.numberofperiods = i+1+"";
                double sum  = (iloan*imonthly_fee*Math.pow((1+imonthly_fee),iterm))/(Math.pow((1+imonthly_fee),iterm)-1);
                BigDecimal b   =   new BigDecimal(sum);
                double   f1   =   b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                c.repaymentperinstallment ="¥"+ f1;
                double ben = iloan*imonthly_fee*Math.pow((1+imonthly_fee),i)/(Math.pow((1+imonthly_fee),iterm)-1);
                BigDecimal   d  =   new BigDecimal(ben);
                double   f2   =   d.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                c.principalpayable ="¥"+ f2;
                double lixi =  (f1-f2);
                BigDecimal   g  =   new BigDecimal(lixi);
                double   f3   =   g.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                c.interestpayable = "¥"+f3  ;
                calculateList.add(i,c);
                mAdapter = new CalculateAdapter(getContext(),calculateList);
                calculate_listview.setAdapter(mAdapter);
            }
            num = iloan*iterm*imonthly_fee* Math.pow((1+imonthly_fee),iterm)/(Math.pow((1+imonthly_fee),iterm)-1)-iloan;
            BigDecimal   g  =   new BigDecimal(num);
            double   f3   =   g.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            sum.setText(f3+"");
        }

    }


    private void calculateByOne(){
        if(!havechange1&&havechange2&&havechange3){
            return;
        }else{
            calculateList = new ArrayList<Calculate>();
            loan = loan_amount.getText().toString();
            term = term_of_loan.getText().toString();
            monthly_fee = monthly_fee_rate.getText().toString();
            if(!monthly_fee.equals("")){
                imonthly_fee = Double.parseDouble(monthly_fee)/100;
            }

            if(!term.equals("")){
                iterm = Integer.parseInt(term);
            }
            if(!loan.equals("")){
                iloan = Integer.parseInt(loan);
            }

            num=0;
            for(int i=0;i<iterm;i++){
                Calculate c = new Calculate();
                c.numberofperiods = i+1+"";
                double a= (iloan/iterm);
                BigDecimal   e   =   new BigDecimal(a);
                double   f2   =   e.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                c.principalpayable = "¥"+f2;
                double f= (iloan-(iloan/iterm*i))*imonthly_fee;
                BigDecimal   b   =   new BigDecimal(f);
                double   f1   =   b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                c.interestpayable = "¥"+ f1;
                c.repaymentperinstallment ="¥"+ ((iloan/iterm)+ f1);
                calculateList.add(i,c);
                mAdapter = new CalculateAdapter(getContext(),calculateList);
                calculate_listview.setAdapter(mAdapter);
                num = num +f1;
            }

            BigDecimal   g  =   new BigDecimal(num);
            double   f3   =   g.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            sum.setText(f3+"");
        }

    }

    boolean isDouble(String str)
    {
        try
        {
            Double.parseDouble(str);
            return true;
        }
        catch(NumberFormatException ex){}
        return false;
    }

    /**
     * 判断是否是线上
     *
     * @return 线上
     */
    private boolean checkChannel() {
        SharedPreferences sp = Factory.app().getSharedPreferences(CHANNEL,
                Context.MODE_PRIVATE);
        String channelOk = sp.getString(CHANNELOKORNOTOK, "1");
        return channelOk.equals("0");
    }
}
