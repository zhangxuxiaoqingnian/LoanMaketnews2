package money.kuxuan.platform.moneyplatfrom.frags.main.state;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import money.kuxuan.platform.common.app.PresenterFragment;
import money.kuxuan.platform.common.factory.presenter.BaseContract;
import money.kuxuan.platform.moneyplatfrom.R;
import money.kuxuan.platform.moneyplatfrom.activities.MyBillActivity;

/**
 * Created by 小狼 on 2018/10/10.
 */

public class MyBillFragment extends PresenterFragment {
    @Override
    protected BaseContract.Presenter initPresenter() {
        return null;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.billfrag_layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Intent intent=new Intent(getActivity(), MyBillActivity.class);
        startActivity(intent);

    }
}
