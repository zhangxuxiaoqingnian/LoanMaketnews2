package com.smileflowpig.money.factory.presenter.account;

import android.support.annotation.StringRes;

import com.smileflowpig.money.common.factory.data.DataSource;
import com.smileflowpig.money.common.factory.presenter.BasePresenter;
import com.smileflowpig.money.factory.data.helper.AccountHelper;
import com.smileflowpig.money.factory.model.api.RspModel;
import com.smileflowpig.money.factory.model.api.account.CodeModel;
import com.smileflowpig.money.factory.model.api.account.CodeRspModel;
import com.smileflowpig.money.factory.model.api.account.ForgetModel;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class ForgetPresenter extends BasePresenter<ForgetContract.View>
        implements ForgetContract.Presenter{

    public ForgetPresenter(ForgetContract.View view) {
        super(view);
    }

    @Override
    public void update(String phone, String password,String code) {
        ForgetModel forgetModel = new ForgetModel(phone,password,code);
        AccountHelper.update(forgetModel, new DataSource.Callback<RspModel>() {
            @Override
            public void onDataNotAvailable(@StringRes int strRes) {
                if(getView()!=null){
                    getView().showError(strRes);
                }

            }

            @Override
            public void onDataLoaded(RspModel rspModel) {
                if(getView()!=null){
                    getView().updateSuccess();
                }

            }
        });
    }

    @Override
    public void getCode(String phone,String smstype) {
        CodeModel codeModel = new CodeModel(phone,smstype);
        AccountHelper.forgetByCode(codeModel, new DataSource.Callback<CodeRspModel>() {
            @Override
            public void onDataNotAvailable(@StringRes int strRes) {
                if(getView()!=null){

                    getView().showError(strRes);
                }

            }

            @Override
            public void onDataLoaded(CodeRspModel codeRspModel) {
                if(getView()!=null){
                    getView().codeSuccess();
                }

            }
        });
    }
}
