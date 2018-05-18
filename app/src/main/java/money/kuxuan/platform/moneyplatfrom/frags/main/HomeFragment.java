package money.kuxuan.platform.moneyplatfrom.frags.main;


import android.content.Intent;
import android.support.annotation.StringRes;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import money.kuxuan.platform.common.app.PresenterFragment;
import money.kuxuan.platform.common.widget.EmptyView;
import money.kuxuan.platform.common.widget.SelfDialog;
import money.kuxuan.platform.factory.model.api.message.MessageRspModel;
import money.kuxuan.platform.factory.model.db.BannerData;
import money.kuxuan.platform.factory.model.db.Category;
import money.kuxuan.platform.factory.model.db.Product;
import money.kuxuan.platform.factory.presenter.home.HomeContract;
import money.kuxuan.platform.factory.presenter.home.HomePresenter;
import money.kuxuan.platform.factory.presenter.notice.Notice;
import money.kuxuan.platform.factory.service.DownService;
import money.kuxuan.platform.moneyplatfrom.Adapter.HomeAdapter;
import money.kuxuan.platform.moneyplatfrom.HomeHeaderLayout;
import money.kuxuan.platform.moneyplatfrom.R;

/**
 *
 */
public class HomeFragment extends PresenterFragment<HomeContract.Presenter>
        implements HomeContract.View {
    //空布局
    @BindView(R.id.empty)
    EmptyView mEmptyView;
    //recyclerView

    ListView mListView;

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    private HomeAdapter mAdapter;

    private static final String TAG = "ExaimeFragment";

    HomeHeaderLayout homeHeaderLayout ;

    List<Product> products;

    SelfDialog selfDialog;



    private static int  version =1;

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_home;
    }


    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        mListView = (ListView) root.findViewById(R.id.listview);
        mEmptyView.bind(mListView);
        setPlaceHolderView(mEmptyView);
        homeHeaderLayout = new HomeHeaderLayout(getContext());
        ClassicsFooter footer = (ClassicsFooter)root.findViewById(R.id.footer);

        footer.setFinishDuration(0);//设置刷新完成显示的停留时间
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh();

            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPresenter.requestData();
            }
        });
        //显示头部出现分割线
        mListView.setHeaderDividersEnabled(false);
        //禁止底部出现分割线
        mListView.setFooterDividersEnabled(false);
    }

    @Override
    protected void onFirstInit() {
        super.onFirstInit();
        mPresenter.start();
    }

    @Override
    protected HomeContract.Presenter initPresenter() {
        //初始化Presenter
        return new HomePresenter(this);
    }

    @Override
    public void onDone(final List<Product> product) {
        products = product;
        refreshLayout.setEnableLoadmore(true);
        refreshLayout.setEnableRefresh(true);
        mAdapter = new HomeAdapter(getContext(), products);
        mListView.addHeaderView(homeHeaderLayout);
        mListView.setAdapter(mAdapter);
        mPlaceHolderView.triggerOkOrEmpty(true);
    }

    @Override
    public void onBanner(List<BannerData> bannerDataList) {
        homeHeaderLayout.setBannerList(bannerDataList);
    }

    @Override
    public void onCategory(List<Category> categoryList) {
       homeHeaderLayout.setCategoryList(categoryList);
    }

    @Override
    public void refresh(List<Product> product) {
        products.addAll(product);
        mAdapter.notifyDataSetChanged();
        refreshLayout.finishLoadmore();
    }

    @Override
    public void NoData() {
        refreshLayout.setLoadmoreFinished(true);
    }

    @Override
    public void getMessageData(MessageRspModel messageRspModel, boolean flag) {
        homeHeaderLayout.setMessage(flag,messageRspModel.getMax_id());
    }

    @Override
    public void getNotice(List<Notice> noticeList) {
        homeHeaderLayout.setNotice(noticeList);
        Log.e(TAG,noticeList.get(0).getContent()+"-------------");
    }

    @Override
    public void version(String url) {
        if(version==1){
            createDialog(R.string.version,url);
            version = 2;
        }else{
            return;
        }

    }
    /**
     * Dialog
     */
    public void createDialog(@StringRes int str, final String url) {
        selfDialog = new SelfDialog(getContext());
        selfDialog.setTitle("温馨提示");
        selfDialog.setMessage(str);
        selfDialog.setNoOnclickListener("取消", new SelfDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                selfDialog.dismiss();
            }
        });
        selfDialog.setYesOnclickListener("立即更新", new SelfDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {

                Intent intent = new Intent(getActivity(), DownService.class);
                intent.putExtra("url",url);
                getActivity().startService(intent);
                selfDialog.dismiss();
            }
        });
        selfDialog.show();

    }

    @Override
    public void showError(int str) {
        super.showError(str);
        mPlaceHolderView.triggerNetError();
        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setEnableRefresh(false);
        refreshLayout.finishLoadmore();
        mListView.removeHeaderView(homeHeaderLayout);
        Button button = (Button) mEmptyView.findViewById(R.id.bu_re);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.start();
            }
        });
    }
}
