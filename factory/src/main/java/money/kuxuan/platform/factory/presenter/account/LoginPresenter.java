package money.kuxuan.platform.factory.presenter.account;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.widget.Toast;

import net.qiujuer.genius.kit.handler.Run;
import net.qiujuer.genius.kit.handler.runable.Action;

import money.kuxuan.platform.common.factory.data.DataSource;
import money.kuxuan.platform.common.factory.presenter.BasePresenter;
import money.kuxuan.platform.factory.Constant;
import money.kuxuan.platform.factory.Factory;
import money.kuxuan.platform.factory.R;
import money.kuxuan.platform.factory.data.helper.AccountHelper;
import money.kuxuan.platform.factory.model.api.account.CodeModel;
import money.kuxuan.platform.factory.model.api.account.CodeRspModel;
import money.kuxuan.platform.factory.model.api.account.LoginByCodeModel;
import money.kuxuan.platform.factory.model.api.account.LoginModel;
import money.kuxuan.platform.factory.model.db.User;
import money.kuxuan.platform.factory.net.Network;
import money.kuxuan.platform.factory.util.SPUtil;


/**
 * 登录的逻辑实现
 *
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class LoginPresenter extends BasePresenter<LoginContract.View>
        implements LoginContract.Presenter {

    public LoginPresenter(LoginContract.View view) {
        super(view);
    }

    @Override
    public void login(final String phone, final String password) {
        start();
        final LoginContract.View view = getView();
        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)) {
            if (view != null)
                view.showError(R.string.data_account_login_invalid_parameter);
        } else {
            LoginModel model = new LoginModel(phone, password);
            AccountHelper.login(model, new DataSource.Callback<User>() {
                @Override
                public void onDataNotAvailable(@StringRes int strRes) {
                    onFail(strRes);
                }

                @Override
                public void onDataLoaded(User user) {
                    if(user!=null){

                        SPUtil.putAndApply(Factory.app(), Constant.UserInfo.SESSIONID, user.getPHPSESSID());
                        SPUtil.putAndApply(Factory.app(), Constant.UserInfo.USERNAME, user.getPhone());
                        SPUtil.putAndApply(Factory.app(), Constant.UserInfo.PASSWORD, password);
                        SPUtil.putAndApply(Factory.app(),Constant.UserInfo.ISEXITE,false);
                    }
                    onSuccess(user);
                }
            });
        }
    }

    @Override
    public void loginByCode(String phone, String code) {
        start();
        final LoginContract.View view = getView();

        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(code)) {
            if (view != null)
                view.showError(R.string.data_account_login_invalid_parameter);
        } else {
            LoginByCodeModel model = new LoginByCodeModel(phone, code, Network.channelId);
            AccountHelper.loginToCode(model, new DataSource.Callback<User>() {
                @Override
                public void onDataNotAvailable(@StringRes int strRes) {
                    onFail(strRes);
                }

                @Override
                public void onDataLoaded(User user) {
                    if(user!=null){
                        SPUtil.putAndApply(Factory.app(), Constant.UserInfo.SESSIONID, user.getPHPSESSID());
                        SPUtil.putAndApply(Factory.app(), Constant.UserInfo.USERNAME, user.getPhone());
                        SPUtil.putAndApply(Factory.app(), Constant.UserInfo.PASSWORD, "");
                        SPUtil.putAndApply(Factory.app(),Constant.UserInfo.ISEXITE,false);
                        if (user.getUser_token()!=null)
                            SPUtil.putAndApply(Factory.app(), Constant.UserInfo.CODETOKEN, user.getUser_token());
                    }
                    onSuccess(user);
                }
            });


        }
    }

    @Override
    public void getCode(String phone, String qrcode, String smstype) {
        final LoginContract.View view = getView();

        if (TextUtils.isEmpty(phone)) {
            if (view != null)
                view.showError(R.string.data_account_login_invalid_parameter);
        } else {
            // 尝试传递PushId
            CodeModel model = new CodeModel(phone, qrcode, smstype);
            AccountHelper.loginByCode(model, new DataSource.Callback<CodeRspModel>() {
                @Override
                public void onDataNotAvailable(@StringRes int strRes) {

                    getView().showError(strRes);

                }

                @Override
                public void onDataLoaded(CodeRspModel codeRspModel) {
                    if (getView() != null) {
                        getView().codeSuccess();
                    }
                    Toast.makeText(Factory.app(), "验证码发送成功", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void onSuccess(final User user) {
        final LoginContract.View view = getView();
        if (view == null)
            return;
        // 强制执行在主线程中
        Run.onUiAsync(new Action() {
            @Override
            public void call() {
                view.loginSuccess(user);
            }
        });
    }

    public void onFail(final int strRes) {
        // 网络请求告知注册失败
        final LoginContract.View view = getView();
        if (view == null)
            return;
        // 此时是从网络回送回来的，并不保证处于主现场状态
        // 强制执行在主线程中
        Run.onUiAsync(new Action() {
            @Override
            public void call() {
                // 调用主界面注册失败显示错误
                view.showError(strRes);
            }
        });
    }
}