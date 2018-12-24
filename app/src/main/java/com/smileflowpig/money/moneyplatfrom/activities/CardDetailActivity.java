package com.smileflowpig.money.moneyplatfrom.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.PresenterActivity;
import com.smileflowpig.money.common.factory.presenter.BaseContract;

import butterknife.BindView;

public class CardDetailActivity extends PresenterActivity implements View.OnClickListener{

    @BindView(R.id.table_back)
    LinearLayout table_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initview();

    }

    public void initview(){
        table_back.setOnClickListener(this);
    }

    @Override
    protected boolean isNeedNotch() {
        return true;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_card_detail;
    }

    @Override
    protected BaseContract.Presenter initPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.table_back:
                finish();
                break;
        }
    }
}
