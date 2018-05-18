package money.kuxuan.platform.moneyplatfrom.guoshen.activity;

import money.kuxuan.platform.common.factory.presenter.BaseContract;
import money.kuxuan.platform.factory.model.api.guoshen.AddModel;
import money.kuxuan.platform.factory.model.api.guoshen.AddModel1;
import money.kuxuan.platform.factory.model.api.guoshen.GetRepaymentModel;

/**
 * Created by Allence on 2018/5/7 0007.
 */

public interface AddConreact {

    interface View extends BaseContract.View<AddConreact.Presenter> {


        void setAddEndView();

        void setRepaymentView(GetRepaymentModel getRepaymentModel);

    }

    interface Presenter extends BaseContract.Presenter {


        void addRepayment(AddModel addModel);
        void addRepayment1(AddModel1 addModel1);
        void getRepayment(int id);

    }







}
