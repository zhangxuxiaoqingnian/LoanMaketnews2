package money.kuxuan.platform.factory.presenter.statehome;

import android.support.annotation.StringRes;

import money.kuxuan.platform.common.factory.data.DataSource;
import money.kuxuan.platform.common.factory.presenter.BasePresenter;
import money.kuxuan.platform.factory.data.helper.ExaimeHelper;
import money.kuxuan.platform.factory.model.api.examine.BannerExprt;
import money.kuxuan.platform.factory.model.api.examine.Examine;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class ExpertPresenter extends BasePresenter<ExpertContract.View>
        implements ExpertContract.Presenter {
    private int page = 1;
    private boolean hasNext = false;
    private String pageid = "package_2";
    public ExpertPresenter(ExpertContract.View view) {
        super(view);

    }

    public void setPage(int page){
        this.page = page;
    }
    public void hasNet(boolean flag){
        hasNext = flag;
    }
    @Override
    public void refreshData() {
        if(hasNext==false)
            return;
        Examine model = new Examine(pageid,page);
        ExaimeHelper.refreshExpert(model, new DataSource.Callback<BannerExprt>() {

            @Override
            public void onDataNotAvailable(@StringRes int strRes) {
                if(getView()!=null)
                getView().showError(strRes);
            }

            @Override
            public void onDataLoaded(BannerExprt bannerExprt) {
                if(getView()!=null)
                getView().getRefreshData(bannerExprt);
                if(bannerExprt.getListdata().getPageinfo().isHasNext()){
                    hasNext =true;
                    page++;
                }else{
                    hasNext=false;
                    if(getView()!=null)
                    getView().NoData();
                }
            }
        });
    }



    @Override
    public void start() {
        super.start();
        Examine examine = new Examine(pageid,page);
        ExaimeHelper.refreshExpert(examine, new DataSource.Callback<BannerExprt>() {

            @Override
            public void onDataNotAvailable(@StringRes int strRes) {
                if(getView()!=null)
                getView().showError(strRes);
            }

            @Override
            public void onDataLoaded(BannerExprt bannerExprt) {
                if(getView()!=null)
                getView().getHomeData(bannerExprt);
                if(bannerExprt.getListdata().getPageinfo().isHasNext()){
                    hasNext = true;
                    page++;
                }else{
                    hasNext = false;
                    if(getView()!=null)
                    getView().NoData();

                }
            }
        });
    }

}
