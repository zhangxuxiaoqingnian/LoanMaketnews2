package com.smileflowpig.money.factory.presenter.application;

import android.support.annotation.StringRes;

import com.smileflowpig.money.common.factory.data.DataSource;
import com.smileflowpig.money.common.factory.presenter.BasePresenter;
import com.smileflowpig.money.factory.data.helper.ProductHelper;
import com.smileflowpig.money.factory.model.api.application.PageModel;
import com.smileflowpig.money.factory.model.api.product.ApplyProductModel;
import com.smileflowpig.money.factory.model.api.product.CreditCardAppliModel;
import com.smileflowpig.money.factory.model.db.CreditCardAppliProduct;

import java.util.List;

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
            pageId=1;
            hasNext=false;

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
            pageId=1;
            hasNext=false;
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
                    List<CreditCardAppliProduct> list = creditCardAppliModel.getList();
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
            if(hasNext==false)
                return;

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
            if(hasNext==false)
                return;
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
                        pageId++;
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
