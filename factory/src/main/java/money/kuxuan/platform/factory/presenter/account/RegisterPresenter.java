package money.kuxuan.platform.factory.presenter.account;

import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.widget.Toast;

import net.qiujuer.genius.kit.handler.Run;
import net.qiujuer.genius.kit.handler.runable.Action;

import java.util.regex.Pattern;

import money.kuxuan.platform.common.Common;
import money.kuxuan.platform.common.factory.data.DataSource;
import money.kuxuan.platform.common.factory.presenter.BasePresenter;
import money.kuxuan.platform.factory.Factory;
import money.kuxuan.platform.factory.R;
import money.kuxuan.platform.factory.data.helper.AccountHelper;
import money.kuxuan.platform.factory.model.api.account.CodeModel;
import money.kuxuan.platform.factory.model.api.account.CodeRspModel;
import money.kuxuan.platform.factory.model.api.account.RegisterModel;
import money.kuxuan.platform.factory.model.db.User;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class RegisterPresenter extends BasePresenter<RegisterContract.View>
        implements RegisterContract.Presenter,DataSource.Callback<User> {
    public RegisterPresenter(RegisterContract.View view) {
        super(view);
    }


    @Override
    public void register(String phone, String password, String code, String source) {
        // 调用开始方法，在start中默认启动了Loading
        start();

        // 得到View接口
        RegisterContract.View view = getView();
        if(view==null){
            return;
        }
        // 校验
        if (!checkMobile(phone)) {
            // 提示
            view.showError(R.string.data_account_register_invalid_parameter_mobile);
        } else if (password.length() < 6) {
            // 密码需要大于6位
            view.showError(R.string.data_account_register_invalid_parameter_password);
        } else {
            // 进行网络请求
            // 构造Model，进行请求调用
            RegisterModel model = new RegisterModel(phone, password, code, source);
            // 进行网络请求，并设置回送接口为自己
            AccountHelper.register(model, this);
        }
    }



    /**
     * 检查手机号码是否合法
     *
     * @param phone 手机号码
     * @return 合法为True
     */
    @Override
    public boolean checkMobile(String phone) {
        // 手机号不为空，并且满足格式
        return !TextUtils.isEmpty(phone)
                && Pattern.matches(Common.Constance.REGEX_MOBILE, phone);
    }

    @Override
    public void requestCode(String phone,String smstype) {
        // 得到View接口
        RegisterContract.View view = getView();

        // 校验
        if (!checkMobile(phone)) {
            // 提示
            view.showError(R.string.data_account_register_invalid_parameter_mobile);
        }
         else {
            // 进行网络请求
            // 构造Model，进行请求调用
            CodeModel model = new CodeModel(phone,smstype);
            // 进行网络请求，并设置回送接口为自己
            AccountHelper.loginByCode(model, new DataSource.Callback<CodeRspModel>() {
                @Override
                public void onDataNotAvailable(@StringRes int strRes) {
                    getView().showError(strRes);
                }

                @Override
                public void onDataLoaded(CodeRspModel codeRspModel) {
                    Toast.makeText(Factory.app(), "发送成功", Toast.LENGTH_SHORT).show();
                    getView().codeSuccess();
                }
            });
        }
    }

    @Override
    public void onDataLoaded(final User user) {
        final RegisterContract.View view = getView();
        if (view == null)
            return;
        // 此时是从网络回送回来的，并不保证处于主现场状态
        // 强制执行在主线程中
        Run.onUiAsync(new Action() {
            @Override
            public void call() {
                // 调用主界面注册成功
                view.registerSuccess(user);
            }
        });
    }

    @Override
    public void onDataNotAvailable(@StringRes final int strRes) {
        // 网络请求告知注册失败
        final RegisterContract.View view = getView();
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
