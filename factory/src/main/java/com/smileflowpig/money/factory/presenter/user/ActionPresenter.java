package com.smileflowpig.money.factory.presenter.user;

import android.support.annotation.StringRes;
import android.widget.Toast;

import com.smileflowpig.money.common.factory.data.DataSource;
import com.smileflowpig.money.common.factory.presenter.BasePresenter;
import com.smileflowpig.money.factory.Factory;
import com.smileflowpig.money.factory.data.helper.AccountHelper;
import com.smileflowpig.money.factory.model.api.RspModel;
import com.smileflowpig.money.factory.model.api.account.ContentModel;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class ActionPresenter extends BasePresenter<ActionContract.View>
        implements ActionContract.Presenter{


    public ActionPresenter(ActionContract.View view) {
        super(view);
    }

    @Override
    public void send(String content) {
        ContentModel contentModel = new ContentModel(content);
        AccountHelper.SendMessage(contentModel, new DataSource.Callback<RspModel>() {
            @Override
            public void onDataNotAvailable(@StringRes int strRes) {
                if(getView()!=null)
                getView().showError(strRes);
            }

            @Override
            public void onDataLoaded(RspModel rspModel) {
                Toast.makeText(Factory.app(), "提交成功，谢谢支持", Toast.LENGTH_SHORT).show();
                if(getView()!=null)
                getView().sendSuccess();
            }
        });
    }
}
