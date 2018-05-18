package money.kuxuan.platform.factory.presenter.creditcard;

import android.support.annotation.StringRes;
import android.text.TextUtils;

import money.kuxuan.platform.common.factory.data.DataSource;
import money.kuxuan.platform.common.factory.presenter.BasePresenter;
import money.kuxuan.platform.factory.R;
import money.kuxuan.platform.factory.data.helper.AccountHelper;
import money.kuxuan.platform.factory.data.helper.ProductHelper;
import money.kuxuan.platform.factory.model.api.RspModel;
import money.kuxuan.platform.factory.model.api.account.CodeModel;
import money.kuxuan.platform.factory.model.api.account.CodeRspModel;
import money.kuxuan.platform.factory.model.api.account.LoginByCodeModel;
import money.kuxuan.platform.factory.model.api.account.LoginModel;
import money.kuxuan.platform.factory.model.api.creditcard.CreditCardPageModel;
import money.kuxuan.platform.factory.model.api.product.CreditCardModel;
import money.kuxuan.platform.factory.model.db.User;
import money.kuxuan.platform.factory.net.Network;


/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class CreditcardPresent extends BasePresenter<CreditcardContract.View>
        implements CreditcardContract.Presenter {

    private int pageId = 1;
    private boolean hasNext = false;



    public CreditcardPresent(CreditcardContract.View view) {
        super(view);
    }


    @Override
    public void start() {
        super.start();

        pageId = 1;
        hasNext = false;

        CreditCardPageModel pageModel =  new CreditCardPageModel(pageId);

        ProductHelper.getCrediCard(pageModel,new DataSource.Callback<CreditCardModel>() {

            @Override
            public void onDataNotAvailable(@StringRes int strRes) {
                if(getView()!=null)
                    getView().showError(strRes);
            }

            @Override
            public void onDataLoaded(CreditCardModel productRspModel) {
                if(getView()!=null)
                    getView().requestData(productRspModel.getList());

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
    }



    @Override
    public void refreshData() {

        CreditCardPageModel pageModel =  new CreditCardPageModel(pageId);
        ProductHelper.getCrediCard(pageModel,new DataSource.Callback<CreditCardModel>() {

            @Override
            public void onDataNotAvailable(@StringRes int strRes) {
                if(getView()!=null)
                    getView().showError(strRes);
            }

            @Override
            public void onDataLoaded(CreditCardModel productRspModel) {
                if(productRspModel.getList()==null){
                    if(getView()!=null)
                        getView().NoData();
                }else{
                    if(getView()!=null)
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


    }


    //TODO 把这个抽出来
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
    public void login(String phone, String password) {
        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)) {
            getView().showError(R.string.data_account_login_invalid_parameter);
        } else {
            LoginByCodeModel model = new LoginByCodeModel(phone,password, Network.channelId);
            AccountHelper.loginToCode(model, new DataSource.Callback<User>() {
                @Override
                public void onDataNotAvailable(@StringRes int strRes) {
                    if(getView()!=null)
                        getView().loginFailure(strRes);
                }

                @Override
                public void onDataLoaded(User user) {
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
    public void loginState(final int product_id) {
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


                    AccountHelper.getAdd(product_id, new DataSource.Callback<RspModel>() {
                        @Override
                        public void onDataNotAvailable(@StringRes int strRes) {

                        }

                        @Override
                        public void onDataLoaded(RspModel rspModel) {

                            if(rspModel.getErrno()==0)
                            getView().state();

                        }
                    });




                }

            }
        });
    }


}
