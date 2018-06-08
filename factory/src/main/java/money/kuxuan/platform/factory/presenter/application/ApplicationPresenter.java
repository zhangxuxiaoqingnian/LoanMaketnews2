package money.kuxuan.platform.factory.presenter.application;

import android.support.annotation.StringRes;

import money.kuxuan.platform.common.factory.data.DataSource;
import money.kuxuan.platform.common.factory.presenter.BasePresenter;
import money.kuxuan.platform.factory.data.helper.ProductHelper;
import money.kuxuan.platform.factory.model.api.application.PageModel;
import money.kuxuan.platform.factory.model.api.creditcard.CreditCardPageModel;
import money.kuxuan.platform.factory.model.api.product.ApplyProductModel;
import money.kuxuan.platform.factory.model.api.product.CreditCardAppliModel;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class ApplicationPresenter extends BasePresenter<ApplicationContract.View>
        implements ApplicationContract.Presenter {

    private int pageId = 1;
    private int page = 1;
    private boolean hasNext = false;

    private boolean click=true;
    private int num;
    private String ppger;


    private static final String TAG = "ApplicationPresenter";


    public ApplicationPresenter(ApplicationContract.View view) {
        super(view);
    }


    public boolean getIsClick(){
        return click;
    }



    public void setIsClick(boolean click,String ppger){
        this.click = click;
        this.ppger=ppger;
    }


    @Override
    public void start() {
        super.start();


        if(click){

            PageModel pageModel = new PageModel(pageId,ppger);
            ProductHelper.getApplication(click,pageModel,new DataSource.Callback<ApplyProductModel>() {

                @Override
                public void onDataNotAvailable(@StringRes int strRes) {
                    if(getView()!=null)
                        getView().showError(strRes);
                }

                @Override
                public void onDataLoaded(ApplyProductModel productRspModel) {
                    if(getView()!=null) {

                        getView().requestData(productRspModel.getList());

                    }
                    if(productRspModel.getPageinfo().isHasNext()){
                        hasNext =true;
                        pageId++;
                    }else{
                        hasNext=false;
                        if(getView()!=null)
                            getView().NoData();
                    }
                }
            });


        }else {
            PageModel pageModel = new PageModel(pageId,ppger);
            //网络请求
            ProductHelper.getApplication(click,pageModel,new DataSource.Callback<CreditCardAppliModel>(){


                @Override
                public void onDataNotAvailable(@StringRes int strRes) {


                    if(getView()!=null)
                        getView().showError(strRes);

                }

                @Override
                public void onDataLoaded(CreditCardAppliModel creditCardAppliModel) {

                    if(getView()!=null) {
                        getView().requestData1(creditCardAppliModel.getList());
                    }
                    if(creditCardAppliModel.getPageinfo().isHasNext()){
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

    @Override
    public void refreshData() {


        if(click) {
            PageModel pageModel = new PageModel(pageId,ppger);
            ProductHelper.getApplication(click, pageModel, new DataSource.Callback<ApplyProductModel>() {

                @Override
                public void onDataNotAvailable(@StringRes int strRes) {
                    if (getView() != null)
                        getView().showError(strRes);
                }

                @Override
                public void onDataLoaded(ApplyProductModel productRspModel) {
                    if (productRspModel.getList() == null) {
                        if (getView() != null)
                            getView().NoData();
                    } else {
                        if (getView() != null)
                            getView().refresh(productRspModel.getList());
                        if(productRspModel.getPageinfo().isHasNext()){
                            hasNext =true;
                            pageId++;
                        }else{
                            hasNext=false;
                            if(getView()!=null)
                                getView().NoData();
                        }
                    }
                }
            });

        }else {
            PageModel pageModel = new PageModel(pageId,ppger);
            ProductHelper.getApplication(click,pageModel,new DataSource.Callback<CreditCardAppliModel>(){


                @Override
                public void onDataNotAvailable(@StringRes int strRes) {

                    if(getView()!=null)
                        getView().showError(strRes);

                }

                @Override
                public void onDataLoaded(CreditCardAppliModel creditCardAppliModel) {

                    if(getView()!=null)
                        getView().refresh1(creditCardAppliModel.getList());
                    if(creditCardAppliModel.getPageinfo().isHasNext()){
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
}
