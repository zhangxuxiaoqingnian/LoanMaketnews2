package com.smileflowpig.money.factory.presenter.web;

import com.smileflowpig.money.common.factory.presenter.BasePresenter;
import com.smileflowpig.money.factory.model.api.web.WebModel;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class WebPresenter extends BasePresenter<WebContract.View>
        implements WebContract.Presenter{


    public WebPresenter(WebContract.View view) {
        super(view);
    }

    @Override
    public void pushData(String product_apply_id, String status, String skip_type, String product_id) {
        WebModel webModel = new WebModel(product_apply_id,status,skip_type,product_id);
        //WebHelper.pushData(webModel);

    }
}
