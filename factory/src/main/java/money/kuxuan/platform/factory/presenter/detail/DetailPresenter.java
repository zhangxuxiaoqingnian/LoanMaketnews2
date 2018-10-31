package money.kuxuan.platform.factory.presenter.detail;

import android.support.annotation.StringRes;
import android.text.TextUtils;

import money.kuxuan.platform.common.factory.data.DataSource;
import money.kuxuan.platform.common.factory.presenter.BasePresenter;
import money.kuxuan.platform.factory.Constant;
import money.kuxuan.platform.factory.Factory;
import money.kuxuan.platform.factory.R;
import money.kuxuan.platform.factory.data.helper.AccountHelper;
import money.kuxuan.platform.factory.data.helper.ProductHelper;
import money.kuxuan.platform.factory.model.api.account.CodeModel;
import money.kuxuan.platform.factory.model.api.account.CodeRspModel;
import money.kuxuan.platform.factory.model.api.account.LoginByCodeModel;
import money.kuxuan.platform.factory.model.api.account.LoginModel;
import money.kuxuan.platform.factory.model.api.detail.AmountRspModel;
import money.kuxuan.platform.factory.model.api.detail.DetailModel;
import money.kuxuan.platform.factory.model.api.product.Apply;
import money.kuxuan.platform.factory.model.api.product.ProductDetail;
import money.kuxuan.platform.factory.model.db.ApplyModel;
import money.kuxuan.platform.factory.model.db.User;
import money.kuxuan.platform.factory.net.Network;
import money.kuxuan.platform.factory.util.SPUtil;


/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class DetailPresenter extends BasePresenter<DetailContract.View>
        implements DetailContract.Presenter {
    private String id;
    private String enter_source;

    public DetailPresenter(DetailContract.View view, String id, String enter_source) {
        super(view);
        this.id = id;
        this.enter_source = enter_source;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DetailPresenter(DetailContract.View view, String id) {
        super(view);
        this.id = id;
    }

    @Override
    public void start() {
        DetailModel detailModel = null;
        if (!TextUtils.isEmpty(enter_source)) {
            detailModel = new DetailModel(id, enter_source);
        } else
            detailModel = new DetailModel(id);
        ProductHelper.getDetailData(detailModel, new DataSource.Callback<ProductDetail>() {

            @Override
            public void onDataNotAvailable(@StringRes int strRes) {
                if (getView() != null)
                    getView().showError(strRes);
            }

            @Override
            public void onDataLoaded(final ProductDetail productDetail) {
                if (getView() != null)
                    getView().onDone(productDetail);
                // 成功
//                Run.onUiAsync(new Action() {
//                    @Override
//                    public void call() {
//                        DetailContract.View view = getView();
//                        if (view != null) {
//                            view.onDone(productDetail);
//                        }
//                    }
//                });
            }
        });


        ProductHelper.getHorData(detailModel, new DataSource.Callback<AmountRspModel>() {

            @Override
            public void onDataNotAvailable(@StringRes int strRes) {
                if (getView() != null)
                    getView().showError(strRes);
            }

            @Override
            public void onDataLoaded(AmountRspModel amountRspModel) {
                if (amountRspModel != null) {
                    if (getView() != null)
                        getView().onHorData(amountRspModel.getData());
                }

            }
        });

    }


    @Override
    public void codePush(String phone, String smstype) {
        CodeModel codeModel = new CodeModel(phone, "1", smstype);
        // 调用Retrofit对我们的网络请求接口做代理
        AccountHelper.loginByCode(codeModel, new DataSource.Callback<CodeRspModel>() {
            @Override
            public void onDataNotAvailable(@StringRes int strRes) {

            }

            @Override
            public void onDataLoaded(CodeRspModel codeRspModel) {
                if (getView() != null)
                    getView().codeSuccess();
            }
        });
    }

    @Override
    public void login(String phone, String code) {
        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(code)) {
            getView().showError(R.string.data_account_login_invalid_parameter);
        } else {
            LoginByCodeModel model = new LoginByCodeModel(phone, code, Network.channelId);
            AccountHelper.loginToCode(model, new DataSource.Callback<User>() {
                @Override
                public void onDataNotAvailable(@StringRes int strRes) {
                    if (getView() != null)
                        getView().showError(strRes);
                }

                @Override
                public void onDataLoaded(User user) {
                    if(user!=null)
                        SPUtil.putAndApply(Factory.app(), Constant.UserInfo.SESSIONID, user.getPHPSESSID());
                    if (getView() != null)
                        getView().loginSuccess();
                }
            });


        }
    }


    public void loginP(String phone, String password) {
        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)) {
            getView().showError(R.string.data_account_login_invalid_parameter);
        } else {
            LoginModel model = new LoginModel(phone, password);
            AccountHelper.login(model, new DataSource.Callback<User>() {
                @Override
                public void onDataNotAvailable(@StringRes int strRes) {
                    if (getView() != null)
                        getView().showError(strRes);
                }

                @Override
                public void onDataLoaded(User user) {
                    if (getView() != null){
                        if(user!=null)
                            SPUtil.putAndApply(Factory.app(), Constant.UserInfo.SESSIONID, user.getPHPSESSID());
                        getView().loginSuccess();
                    }

                }
            });


        }
    }

    @Override
    public void loginState() {
        AccountHelper.loginState(new DataSource.Callback<User>() {
            @Override
            public void onDataNotAvailable(@StringRes int strRes) {
                if (getView() != null) {
                    getView().stateError();
                }
            }

            @Override
            public void onDataLoaded(User user) {
                if (getView() != null) {
                    getView().state();
                }

            }
        });
    }

    @Override
    public void SoundCode(String phone) {
        CodeModel codeModel = new CodeModel(phone, "1", "2");
        // 调用Retrofit对我们的网络请求接口做代理
        AccountHelper.loginByCode(codeModel, new DataSource.Callback<CodeRspModel>() {
            @Override
            public void onDataNotAvailable(@StringRes int strRes) {
                if (getView() != null)
                    getView().showError(strRes);
            }

            @Override
            public void onDataLoaded(CodeRspModel codeRspModel) {
                if (getView() != null)
                    getView().codeSuccess();
            }
        });
    }

    @Override
    public void apply(String product_id, String periods, String money) {
        Apply apply = new Apply(product_id, periods, money);
        ProductHelper.pushData(apply, new DataSource.Callback<ApplyModel>() {
            @Override
            public void onDataLoaded(ApplyModel applyModel) {
                if (getView() != null)
                    getView().getApplyId(applyModel.getProduct_apply_id());

            }

            @Override
            public void onDataNotAvailable(@StringRes int strRes) {


            }


        });
    }
}
