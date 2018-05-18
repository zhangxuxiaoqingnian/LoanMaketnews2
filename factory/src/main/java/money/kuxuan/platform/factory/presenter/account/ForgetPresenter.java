package money.kuxuan.platform.factory.presenter.account;

import android.support.annotation.StringRes;

import money.kuxuan.platform.common.factory.data.DataSource;
import money.kuxuan.platform.common.factory.presenter.BasePresenter;
import money.kuxuan.platform.factory.data.helper.AccountHelper;
import money.kuxuan.platform.factory.model.api.RspModel;
import money.kuxuan.platform.factory.model.api.account.CodeModel;
import money.kuxuan.platform.factory.model.api.account.CodeRspModel;
import money.kuxuan.platform.factory.model.api.account.ForgetModel;

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
