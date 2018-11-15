package com.smileflowpig.money.moneyplatfrom.activities;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ListView;

import com.smileflowpig.money.moneyplatfrom.Adapter.MyExpandableAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;
import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.Activity;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by Administrator on 2017/6/28.
 */

public class RadiersActivity extends Activity {
    private View mContentView;
    ListView list_view;


    public static void show(Context context){
        Intent intent  = new Intent(context, RadiersActivity.class);
        context.startActivity(intent);

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
    protected int getContentLayoutId() {
        return R.layout.activity_radiers;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        list_view = (ListView)findViewById(R.id.list_view);
        initView();
    }


    private void initView() {

        List<String> data = new ArrayList<String>();
        data.add("1.如何申请贷款？");
        data.add("2.申请贷款后，审核需要多长时间？");
        data.add("3.如何提高贷款成功率？");
        data.add("4.申请贷款的利率和额度是多少");
        data.add("5.为什么审批下来的金额和期限与申请信息不一致");
        data.add("6.如何还款");


        List<String> childData = new ArrayList<String>();
        childData.add("您可以根据需求在\"极速贷款\"板块选择贷款产品，我们建议您选择符合个人信息和" +
                "需求的贷款产品，并根绝次贷款产品的要求" +
                "填写完资料并提交申请");
        childData.add("一般需要1-3个工作日，最快1小时可以到账。用户的资料真实度、" +
                "配合度也影响审核时长。请您按照消息提醒进行操作，保持电话畅通" +
                "");
        childData.add("① 您可以在“极速贷款”版块，选择符合个人信息和需求的贷款产品;\n" +
                "② 经小财神测算：申请多个产品，可大幅度提高贷款成功率，您可以尝试同时多申请几个贷款产品。");
        childData.add("  平台推荐的众多贷款产品，利率和额度各有不同：\n" +
                "①一般参考月利率范围在1%~1.5%，建议在申请时查看详细介绍;\n" +
                "②额度一般都在500元至50万元，您可以根据自身的资金需求自主申请;\n" +
                "特别提醒您，信贷机构会根据您的个人资质给出最终的贷款额度和利率，请以放款前的确认信息为准。");
        childData.add("信贷机构会根据您的个人资料、征信记录，并参考您的申请信息，进行综合评估得出审批" +
                "结果，因此可能与您的申请信息产生出入；" +
                "最终放款额将以信贷机构给出的确认批复为准，" +
                "如果未能完全满足您的资金需求，建议多申请几笔其他产品作为备用金。");

        childData.add("在【我的】页面点击【我的足迹】，找到已贷款的产品，点击进入详情页，底部有\"我要还款\"按钮，点击按钮进入贷款产品，需打开贷款产品app或打开应用商店直接下载app进行还款。");

        final MyExpandableAdapter dl = new MyExpandableAdapter(this,data,childData);
        list_view.setAdapter(dl);


    }
    @OnClick(R.id.back)
    void onClick(){
        finish();
    }




}



