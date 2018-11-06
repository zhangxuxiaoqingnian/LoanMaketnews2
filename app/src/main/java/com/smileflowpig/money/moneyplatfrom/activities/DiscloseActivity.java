package com.smileflowpig.money.moneyplatfrom.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.smileflowpig.money.moneyplatfrom.Adapter.DiscloseAdapter;
import com.smileflowpig.money.moneyplatfrom.util.DisplayUtils3;

import java.util.ArrayList;
import java.util.List;

import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.PresenterActivity;
import com.smileflowpig.money.common.factory.presenter.BaseContract;

public class DiscloseActivity extends PresenterActivity implements View.OnClickListener{

    private RecyclerView rv;
    private ImageView compile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initview();

        compile.setOnClickListener(this);
        List<String> list=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("条目");
        }
        DiscloseAdapter discloseAdapter=new DiscloseAdapter(this,list);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(discloseAdapter);
        rv.addItemDecoration(new DisplayUtils3.SpacesItemDecoration());


    }
    public void initview(){

        rv = (RecyclerView) findViewById(R.id.dis_rv);
        compile = (ImageView) findViewById(R.id.compile);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_disclose;
    }

    @Override
    protected BaseContract.Presenter initPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.compile:
                //进入发帖页面

                break;
        }
    }
}
