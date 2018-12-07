package com.smileflowpig.money.moneyplatfrom.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.PresenterActivity;
import com.smileflowpig.money.common.factory.presenter.BaseContract;
import com.smileflowpig.money.moneyplatfrom.Adapter.MessAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MessContextActivity extends PresenterActivity implements View.OnClickListener{

    @BindView(R.id.mess_rv)
    RecyclerView rv;
    @BindView(R.id.back)
    LinearLayout back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initview();
        List<String> list=new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("款了圣诞节反馈老师开始的减肥邻科技水电费讲的六块腹肌收到了据"+i);
        }

        MessAdapter messAdapter=new MessAdapter(this,list);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(messAdapter);



    }
    public void initview(){
        back.setOnClickListener(this);
    }

    @Override
    protected boolean isNeedNotch() {
        return true;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_mess_context;
    }

    @Override
    protected BaseContract.Presenter initPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
        }
    }
}
