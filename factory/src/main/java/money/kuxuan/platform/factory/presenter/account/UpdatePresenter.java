package money.kuxuan.platform.factory.presenter.account;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

import money.kuxuan.platform.common.factory.data.DataSource;
import money.kuxuan.platform.common.factory.presenter.BasePresenter;
import money.kuxuan.platform.common.widget.SelfDialog;
import money.kuxuan.platform.factory.Factory;
import money.kuxuan.platform.factory.R;
import money.kuxuan.platform.factory.data.helper.AccountHelper;
import money.kuxuan.platform.factory.model.api.RspModel;
import money.kuxuan.platform.factory.model.api.account.UpdateModel;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class UpdatePresenter extends BasePresenter<UpdateContract.View>
        implements UpdateContract.Presenter {
    public UpdatePresenter(UpdateContract.View view) {
        super(view);
    }

    @Override
    public void update(String oldpassword, String newpassword, final Context context) {

        UpdateModel updateModel = new UpdateModel(newpassword,oldpassword);
        AccountHelper.update(updateModel, new DataSource.Callback<RspModel>() {
            @Override
            public void onDataNotAvailable(@StringRes int strRes) {
               final SelfDialog selfDialog = new SelfDialog(context);
                selfDialog.setTitle("温馨提示");
                selfDialog.setMessage(R.string.data_account_login_invalid_parameter4);
                selfDialog.setNoOnclickListener("确定", new SelfDialog.onNoOnclickListener() {
                    @Override
                    public void onNoClick() {
                        selfDialog.dismiss();
                    }
                });

                selfDialog.show();
            }

            @Override
            public void onDataLoaded(RspModel rspModel) {
                Toast.makeText(Factory.app(), "修改成功", Toast.LENGTH_SHORT).show();
                if(getView()!=null)
                   getView().updateSuccess();
            }
        });
    }
}
