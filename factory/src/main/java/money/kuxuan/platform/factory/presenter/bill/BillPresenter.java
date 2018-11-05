package money.kuxuan.platform.factory.presenter.bill;

import net.qiujuer.genius.kit.handler.Run;
import net.qiujuer.genius.kit.handler.runable.Action;

import money.kuxuan.platform.common.factory.data.DataSource;
import money.kuxuan.platform.common.factory.presenter.BasePresenter;
import money.kuxuan.platform.factory.bean.BillData;
import money.kuxuan.platform.factory.bean.BillStatusData;
import money.kuxuan.platform.factory.data.helper.BillHelper;
import money.kuxuan.platform.factory.presenter.account.LoginContract;

public class BillPresenter extends BasePresenter<BillContract.View>
        implements BillContract.Presenter {
    public BillPresenter(BillContract.View view) {
        super(view);
    }

    @Override
    public void start() {

    }

    @Override
    public void destroy() {

    }


    @Override
    public void getLists(String page, String status, final boolean isRefresh) {
        BillHelper.getLists(page, status, new DataSource.Callback<BillData>() {
            @Override
            public void onDataNotAvailable(int strRes) {
                getView().showNoData();
            }

            @Override
            public void onDataLoaded(BillData billData) {
                if (getView() != null)
                    getView().setData(billData, isRefresh);
            }
        });
    }

    @Override
    public void changeStatus(final String id, final String status) {
        BillHelper.changeStatus(id, status, new DataSource.Callback<BillStatusData>() {
            @Override
            public void onDataNotAvailable(int strRes) {


            }

            @Override
            public void onDataLoaded(BillStatusData billStatusData) {
                if (getView() != null)
                    getView().changeStatus(id, status);
            }
        });
    }

    public void onFail(final int strRes) {
        // 网络请求告知注册失败
        final BillContract.View view = getView();
        if (view == null)
            return;
        // 此时是从网络回送回来的，并不保证处于主现场状态
        // 强制执行在主线程中
        Run.onUiAsync(new Action() {
            @Override
            public void call() {
                // 调用主界面注册失败显示错误
                view.showError(strRes);
            }
        });
    }
}
