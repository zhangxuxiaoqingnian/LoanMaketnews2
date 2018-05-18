package money.kuxuan.platform.moneyplatfrom.frags.main.state;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import money.kuxuan.platform.common.app.PresenterFragment;
import money.kuxuan.platform.common.widget.EmptyView;
import money.kuxuan.platform.factory.model.api.examine.BannerExprt;
import money.kuxuan.platform.factory.model.db.DataE;
import money.kuxuan.platform.factory.presenter.statehome.ExpertContract;
import money.kuxuan.platform.factory.presenter.statehome.ExpertPresenter;
import money.kuxuan.platform.moneyplatfrom.Adapter.ExaimeAdapter;
import money.kuxuan.platform.moneyplatfrom.InfoLayout;
import money.kuxuan.platform.moneyplatfrom.R;


public class ExpertFragment extends PresenterFragment<ExpertContract.Presenter>
        implements ExpertContract.View {
    //空布局
    @BindView(R.id.empty)
    EmptyView mEmptyView;
    //recyclerView

    ListView mListView;

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    List<DataE> dataEList;


    private ExaimeAdapter mAdapter;

    public ExpertFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_expert;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        mListView  = (ListView) root.findViewById(R.id.listview);
        mEmptyView.bind(mListView);
        setPlaceHolderView(mEmptyView);


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

    @Override
    protected void onFirstInit() {
        super.onFirstInit();
        mPresenter.start();
    }

    @Override
    public void getHomeData(BannerExprt bannerExprt) {
        this.dataEList = bannerExprt.getListdata().getData();
        refreshLayout.setEnableLoadmore(true);
        refreshLayout.setEnableRefresh(true);
        mAdapter = new ExaimeAdapter(getContext(), bannerExprt.getListdata().getData());
        InfoLayout infoLayout = new InfoLayout(getContext(),bannerExprt.getBanner());
        mListView.addHeaderView(infoLayout);
        mListView.setAdapter(mAdapter);
        mPlaceHolderView.triggerOkOrEmpty(true);

    }

    @Override
    public void NoData() {
        refreshLayout.setLoadmoreFinished(true);
    }

    @Override
    public void getRefreshData(BannerExprt rspExamHomeModel) {
        dataEList.addAll(rspExamHomeModel.getListdata().getData());
        mAdapter.notifyDataSetChanged();
        refreshLayout.finishLoadmore(true);
    }


    @Override
    protected ExpertContract.Presenter initPresenter() {
        return new ExpertPresenter(this);
    }

    public void showError(int str) {
        super.showError(str);
        mPlaceHolderView.triggerNetError();
        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setEnableRefresh(false);
        refreshLayout.finishLoadmore();
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
