package com.smileflowpig.money.moneyplatfrom.frags.main.state;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.smileflowpig.money.moneyplatfrom.Adapter.ExaimeAdapter;
import com.smileflowpig.money.moneyplatfrom.InfoLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.PresenterFragment;
import com.smileflowpig.money.common.widget.EmptyView;
import com.smileflowpig.money.factory.model.api.examine.Banner;
import com.smileflowpig.money.factory.model.api.examine.RspExamHomeModel;
import com.smileflowpig.money.factory.model.db.DataE;
import com.smileflowpig.money.factory.presenter.statehome.ExamineContract;
import com.smileflowpig.money.factory.presenter.statehome.ExaminePresenter;

/**
 *
 */
public class ExaimeFragment extends PresenterFragment<ExamineContract.Presenter>
        implements ExamineContract.View {
    //空布局
    @BindView(R.id.empty)
    EmptyView mEmptyView;


    ListView mListView;

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    private ExaimeAdapter mAdapter;

    private static final String TAG = "ExaimeFragment";

    InfoLayout infoLayout;

    List<DataE> dataEList;



    @Override
    protected int getContentLayoutId() {


        return R.layout.fra_home_state;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        EventBus.getDefault().register(this);
        mListView = (ListView) root.findViewById(R.id.listview);
        mEmptyView.bind(mListView);
        setPlaceHolderView(mEmptyView);
        ClassicsFooter footer = (ClassicsFooter)root.findViewById(R.id.footer);

        footer.setFinishDuration(0);//设置刷新完成显示的停留时间

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000);

            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPresenter.refreshData();
            }
        });

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void helloEventBus(String message){

        switch (message){

            case "0":
                mPresenter.getInfoList(0);
                break;
            case "1":
                mPresenter.getInfoList(1);
                break;
            case "2":
                mPresenter.getInfoList(2);
                break;
            case "3":
                mPresenter.getInfoList(3);
                break;
            case "4":
                mPresenter.getInfoList(4);
                break;
        }

    }




    @Override
    protected void onFirstInit() {
        super.onFirstInit();
        mPresenter.start();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void getHomeData(RspExamHomeModel rspExamHomeModel) {
        this.dataEList = rspExamHomeModel.getListdata().getData();
        refreshLayout.setEnableLoadmore(true);
        refreshLayout.setEnableRefresh(true);

        //TODO
        mAdapter = new ExaimeAdapter(getContext(), rspExamHomeModel.getListdata().getData());

//        infoLayout = new InfoLayout(getContext(),rspExamHomeModel.getBanner());
//        mListView.addHeaderView(infoLayout);

        mListView.setAdapter(mAdapter);
        mPlaceHolderView.triggerOkOrEmpty(true);
    }

    @Override
    public void tabList(Banner banner, List<String> tabList) {

        infoLayout = new InfoLayout(getContext(),banner,tabList);
        mListView.addHeaderView(infoLayout);

    }


    @Override
    public void NoData() {
        refreshLayout.setLoadmoreFinished(true);
    }

    @Override
    public void getRefreshData(RspExamHomeModel rspExamHomeModel) {
        dataEList.addAll(rspExamHomeModel.getListdata().getData());
        mAdapter.notifyDataSetChanged();
        refreshLayout.finishLoadmore(true);
    }





    @Override
    protected ExamineContract.Presenter initPresenter() {
        return new ExaminePresenter(this,"package_5");
    }

    public void showError(int str) {
        super.showError(str);
        mPlaceHolderView.triggerNetError();
        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setEnableRefresh(false);
        refreshLayout.finishLoadmore();
        mListView.removeHeaderView(infoLayout);





        Button button = (Button) mEmptyView.findViewById(R.id.bu_re);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.hasNet(true);
                mPresenter.setPage(1);
                mPresenter.start();
            }
        });
    }
}
