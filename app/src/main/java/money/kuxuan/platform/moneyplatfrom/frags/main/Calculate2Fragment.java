package money.kuxuan.platform.moneyplatfrom.frags.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import money.kuxuan.platform.common.app.PresenterFragment;
import money.kuxuan.platform.common.factory.presenter.BaseContract;
import money.kuxuan.platform.moneyplatfrom.R;
import money.kuxuan.platform.moneyplatfrom.activities.Activity_Average_Capital_Calc;
import money.kuxuan.platform.moneyplatfrom.activities.Activity_Average_Capital_Interest_Calc;
import money.kuxuan.platform.moneyplatfrom.activities.Activity_CreditCard_ByStages;
import money.kuxuan.platform.moneyplatfrom.activities.Activity_HouseCalc;
import money.kuxuan.platform.moneyplatfrom.activities.Activity_Income_Tax_Calc;
import money.kuxuan.platform.moneyplatfrom.activities.Activity_loanCarCalc;

/**
 * Created by 小狼 on 2018/7/31.
 */

public class Calculate2Fragment extends Fragment implements View.OnClickListener{


    private View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = LayoutInflater.from(getActivity()).inflate(R.layout.calaur_layout, null);
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        LinearLayout lin_housecalc = (LinearLayout) mRootView.findViewById(R.id.lin_housecalc);
        lin_housecalc.setOnClickListener(this);

        LinearLayout lin_averagecapital = (LinearLayout) mRootView.findViewById(R.id.lin_averagecapital);
        lin_averagecapital.setOnClickListener(this);

        LinearLayout lin_averageinterest = (LinearLayout) mRootView.findViewById(R.id.lin_averageinterest);
        lin_averageinterest.setOnClickListener(this);

        LinearLayout lin_lin_loancar = (LinearLayout) mRootView.findViewById(R.id.lin_loancar);
        lin_lin_loancar.setOnClickListener(this);

        LinearLayout lin_creditcardbystages = (LinearLayout) mRootView.findViewById(R.id.lin_creditcardbystages);
        lin_creditcardbystages.setOnClickListener(this);

        LinearLayout lin_incometaxcalc = (LinearLayout) mRootView.findViewById(R.id.lin_incometaxcalc);
        lin_incometaxcalc.setOnClickListener(this);

        ImageView img = (ImageView) mRootView.findViewById(R.id.chart);
        String url="https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2758812849,70676744&fm=200&gp=0.jpg";
        Glide.with(getActivity()).load(url).into(img);

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.lin_averagecapital:
                intent = new Intent(getActivity(), Activity_Average_Capital_Calc.class);
                startActivity(intent);
                break;

            case R.id.lin_housecalc:
                intent = new Intent(getActivity(), Activity_HouseCalc.class);
                startActivity(intent);
                break;
            case R.id.lin_averageinterest:

                intent = new Intent(getActivity(), Activity_Average_Capital_Interest_Calc.class);
                startActivity(intent);

                break;

            case R.id.lin_loancar:

                intent =new Intent(getActivity(), Activity_loanCarCalc.class);
                startActivity(intent);

                break;

            case R.id.lin_creditcardbystages:

                intent = new Intent(getActivity(), Activity_CreditCard_ByStages.class);
                startActivity(intent);
                break;

            case R.id.lin_incometaxcalc:

                intent = new Intent(getActivity(), Activity_Income_Tax_Calc.class);
                startActivity(intent);

                break;
        }
    }
}
