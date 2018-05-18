package money.kuxuan.platform.factory.presenter.search;

import android.support.annotation.StringRes;
import android.util.Log;

import money.kuxuan.platform.common.factory.data.DataSource;
import money.kuxuan.platform.common.factory.presenter.BasePresenter;
import money.kuxuan.platform.factory.data.helper.SearchHelper;
import money.kuxuan.platform.factory.model.api.product.ProductRspModel;
import money.kuxuan.platform.factory.model.api.search.FilterRspModel;
import money.kuxuan.platform.factory.model.api.search.SearchModel;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class SearchPresent extends BasePresenter<SearchContract.View>
        implements SearchContract.Presenter {

    public static final String isSearch = "1";

    private  int page = 1;

    private boolean hasNext = false;

    private static final String TAG = "CreditcardPresent";

    public SearchPresent(SearchContract.View view) {
        super(view);
    }



    @Override
    public void start() {
        super.start();
        page =1;
        hasNext = false;
        SearchModel searchModel = new SearchModel(isSearch,1);
        Log.e(TAG,"已进入onStart方法");
        SearchHelper.getSearchData(searchModel, new DataSource.Callback<ProductRspModel>() {
            @Override
            public void onDataNotAvailable(@StringRes int strRes) {
                if(getView()!=null)
                getView().showError(strRes);
            }

            @Override
            public void onDataLoaded(ProductRspModel productRspModel) {
                if(getView()!=null)
                getView().onSearchDone(productRspModel.getData());
                if(productRspModel.getPageinfo().isHasNext()){
                    hasNext = true;
                    page++;
                }else{
                    hasNext = false;
                    if(getView()!=null)
                    getView().NoData();

                }
            }
        });
        SearchHelper.getDialogList(new DataSource.Callback<FilterRspModel>() {

            @Override
            public void onDataNotAvailable(@StringRes int strRes) {
                if(getView()!=null)

                getView().showError(strRes);
            }

            @Override
            public void onDataLoaded(FilterRspModel filterRspModel) {
                if(getView()!=null)
                getView().getList(filterRspModel);
            }
        });

    }

    @Override
    public void refreshData(String term, String amount, String user_title, String ranking_list, final int type) {
     if(type == 0 ){
         super.start();
     }
        if(hasNext==false)
            return;
        SearchModel model = new SearchModel(isSearch, term,amount,user_title,ranking_list,page);
       SearchHelper.getSearchData(model, new DataSource.Callback<ProductRspModel>() {

            @Override
            public void onDataNotAvailable(@StringRes int strRes) {
                if(getView()!=null)
                getView().showError(strRes);
            }

            @Override
            public void onDataLoaded(ProductRspModel productRspModel) {
                if(type==0){
                    if(getView()!=null)
                    getView().onSearchDone(productRspModel.getData());
                }else{
                    if(getView()!=null)
                    getView().refresh(productRspModel.getData());
                }
                if(productRspModel.getPageinfo().isHasNext()){
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
    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    @Override
    public void choseCategory(String term, String amount, String user_title, String ranking_list) {
        super.start();
        if(hasNext==false)
        {
            return;
        }
        SearchModel model = new SearchModel(isSearch, term,amount,user_title,ranking_list,page);
        SearchHelper.getSearchData(model, new DataSource.Callback<ProductRspModel>() {

            @Override
            public void onDataNotAvailable(@StringRes int strRes) {
                if(getView()!=null)
                getView().showError(strRes);
            }

            @Override
            public void onDataLoaded(ProductRspModel productRspModel) {
                if(getView()!=null)
                getView().getChoose(productRspModel.getData());
                if(productRspModel.getPageinfo().isHasNext()){
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
}
