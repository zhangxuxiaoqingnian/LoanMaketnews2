package com.smileflowpig.money.factory.presenter.child;

import android.support.annotation.StringRes;

import com.smileflowpig.money.common.factory.data.DataSource;
import com.smileflowpig.money.common.factory.presenter.BasePresenter;
import com.smileflowpig.money.factory.data.helper.ProductHelper;
import com.smileflowpig.money.factory.model.api.product.ChildModel;
import com.smileflowpig.money.factory.model.api.product.PlatFormModel;
import com.smileflowpig.money.factory.model.api.product.ProductRspModel;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class ChildPresenter extends BasePresenter<ChildContract.View>
        implements ChildContract.Presenter {
    private static final String is_new = "1";
    private int page = 1;
    private boolean hasNext = false;
    private static final String TAG = "ChildPresenter";

    private static final String REFRESH_TYPE = "REFRESH_TYPE";
    private static final String LOAD_TYPE = "LOAD_TYPE";
    private String title;

    public ChildPresenter(ChildContract.View view,String title) {
        super(view);
        this.title = title;
    }

    @Override
    public void start() {
        super.start();
        if(title.equals("新品区")){
            requestNewData(LOAD_TYPE);
        }else{
            requestOtherData(LOAD_TYPE);
        }

    }

    @Override
    public void requestData() {
        if(title.equals("新品区")){
            requestNewData(REFRESH_TYPE);
        }else{
            requestOtherData(REFRESH_TYPE);
        }

    }

    /**
     * 申请新品区数据
     */
    private void requestNewData(final String type){

        PlatFormModel model = new PlatFormModel(null, page,is_new);
        ProductHelper.refreshHome(model, new DataSource.Callback<ProductRspModel>() {

            @Override
            public void onDataNotAvailable(@StringRes int strRes) {
                if(getView()!=null)
                getView().showError(strRes);
            }

            @Override
            public void onDataLoaded(ProductRspModel productRspModel) {
                if(type == LOAD_TYPE){
                    if(getView()!=null)
                    getView().onDone(productRspModel.getData());
                }else{
                    if(getView()!=null)
                    getView().refresh(productRspModel.getData());
                }

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
    }

    /**
     * 申请新品区数据
     */
    private void requestOtherData(final String type){
        ChildModel model = new ChildModel(title,page);
        ProductHelper.refreshChild(model, new DataSource.Callback<ProductRspModel>() {

            @Override
            public void onDataNotAvailable(@StringRes int strRes) {
                getView().showError(strRes);
            }

            @Override
            public void onDataLoaded(ProductRspModel productRspModel) {
                if(type == LOAD_TYPE){
                    if(getView()!=null)
                    getView().onDone(productRspModel.getData());
                }else{
                    if(getView()!=null)
                    getView().refresh(productRspModel.getData());
                }
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
    }

}
