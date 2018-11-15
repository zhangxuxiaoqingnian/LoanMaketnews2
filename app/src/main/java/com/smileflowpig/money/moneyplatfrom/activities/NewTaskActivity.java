package com.smileflowpig.money.moneyplatfrom.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.PresenterActivity;
import com.smileflowpig.money.common.factory.presenter.BaseContract;

public class NewTaskActivity extends PresenterActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_new_task;
    }

    @Override
    protected BaseContract.Presenter initPresenter() {
        return null;
    }
}
