package money.kuxuan.platform.factory.presenter.active;

import android.support.annotation.StringRes;
import android.text.TextUtils;

import money.kuxuan.platform.common.factory.data.DataSource;
import money.kuxuan.platform.common.factory.presenter.BasePresenter;
import money.kuxuan.platform.factory.Constant;
import money.kuxuan.platform.factory.Factory;
import money.kuxuan.platform.factory.R;
import money.kuxuan.platform.factory.data.helper.AccountHelper;
import money.kuxuan.platform.factory.data.helper.ActiveHelper;
import money.kuxuan.platform.factory.model.api.account.CodeModel;
import money.kuxuan.platform.factory.model.api.account.CodeRspModel;
import money.kuxuan.platform.factory.model.api.account.LoginByCodeModel;
import money.kuxuan.platform.factory.model.api.account.LoginModel;
import money.kuxuan.platform.factory.model.api.active.ActiveModel;
import money.kuxuan.platform.factory.model.api.active.ActiveRspModel;
import money.kuxuan.platform.factory.model.db.User;
import money.kuxuan.platform.factory.net.Network;
import money.kuxuan.platform.factory.util.SPUtil;


/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class ActivePresenter extends BasePresenter<ActiveContract.View>
        implements ActiveContract.Presenter {
    private int page = 1;
    private boolean hasNext = false;

    private static final String TAG = "ActivePresenter";

    public ActivePresenter(ActiveContract.View view) {
        super(view);
    }

    @Override
    public void start() {
        super.start();
        page = 1;
        hasNext = false;
       ActiveModel model = new ActiveModel(page);
        ActiveHelper.getActiveData(model,new DataSource.Callback<ActiveRspModel>() {

            @Override
            public void onDataNotAvailable(@StringRes int strRes) {
                if(getView()!=null)
                 getView().showError(strRes);
            }

            @Override
            public void onDataLoaded(ActiveRspModel activeRspModel) {
                if(getView()!=null)
                getView().requestData(activeRspModel.getData());
                if(activeRspModel.getPageinfo().isHasNext()){
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

    @Override
    public void loginState() {
        AccountHelper.loginState(new DataSource.Callback<User>() {
            @Override
            public void onDataNotAvailable(@StringRes int strRes) {
                if(getView()!=null){
                    getView().stateError();
                }
            }

            @Override
            public void onDataLoaded(User user) {
                if(getView()!=null){
                    getView().state();
                }

            }
        });
    }

    @Override
    public void codePush(String phone, String smstype) {
        CodeModel codeModel = new CodeModel(phone,"1",smstype);
        // 调用Retrofit对我们的网络请求接口做代理
        AccountHelper.loginByCode(codeModel, new DataSource.Callback<CodeRspModel>() {
            @Override
            public void onDataNotAvailable(@StringRes int strRes) {

            }
            @Override
            public void onDataLoaded(CodeRspModel codeRspModel) {
                if(getView()!=null)
                getView().codeSuccess();
            }
        });
    }

    @Override
    public void login(String phone, String code) {
        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(code)) {
            getView().showError(R.string.data_account_login_invalid_parameter);
        } else {
            LoginByCodeModel model = new LoginByCodeModel(phone,code, Network.channelId);
            AccountHelper.loginToCode(model, new DataSource.Callback<User>() {
                @Override
                public void onDataNotAvailable(@StringRes int strRes) {
                    if(getView()!=null)
                      getView().loginFailure(strRes);
                }

                @Override
                public void onDataLoaded(User user) {
                    if(user!=null)
                        SPUtil.putAndApply(Factory.app(), Constant.UserInfo.SESSIONID, user.getPHPSESSID());
                    if(getView()!=null)
                    getView().loginSuccess();
                }
            });


        }
    }

    @Override
    public void loginP(String phone, String password) {
        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)) {
            getView().showError(R.string.data_account_login_invalid_parameter);
        } else {
            LoginModel model = new LoginModel(phone,password);
            AccountHelper.login(model, new DataSource.Callback<User>() {
                @Override
                public void onDataNotAvailable(@StringRes int strRes) {
                    getView().loginFailure(strRes);
                }

                @Override
                public void onDataLoaded(User user) {
                    getView().loginSuccess();
                    if(user!=null)
                    SPUtil.putAndApply(Factory.app(), Constant.UserInfo.SESSIONID, user.getPHPSESSID());

                }
            });


        }
    }

    @Override
    public void SoundCode(String phone) {
        CodeModel codeModel = new CodeModel(phone,"1","2");
        // 调用Retrofit对我们的网络请求接口做代理
        AccountHelper.loginByCode(codeModel, new DataSource.Callback<CodeRspModel>() {
            @Override
            public void onDataNotAvailable(@StringRes int strRes) {

            }
            @Override
            public void onDataLoaded(CodeRspModel codeRspModel) {
                getView().codeSuccess();
            }
        });
    }

    @Override
    public void requestData() {
        if(hasNext==false)
            return;
       ActiveModel model = new ActiveModel(page);
        ActiveHelper.getActiveData(model,new DataSource.Callback<ActiveRspModel>() {

            @Override
            public void onDataNotAvailable(@StringRes int strRes) {
                getView().showError(strRes);
            }

            @Override
            public void onDataLoaded(ActiveRspModel activeRspModel) {
                getView().requestData(activeRspModel.getData());
                if (activeRspModel.getPageinfo().isHasNext()) {
                    hasNext = true;
                    page++;
                } else {
                    hasNext = false;
                    getView().NoData();
                }
            }
        });
    }
}
