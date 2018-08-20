package money.kuxuan.platform.moneyplatfrom.frags.main;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.StringRes;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;
import money.kuxuan.platform.common.SecondEvent;
import money.kuxuan.platform.common.app.PresenterFragment;
import money.kuxuan.platform.common.widget.SelfDialog;
import money.kuxuan.platform.factory.Constant;
import money.kuxuan.platform.factory.model.db.User;
import money.kuxuan.platform.factory.presenter.state.StateContract;
import money.kuxuan.platform.factory.presenter.state.StatePresenter;
import money.kuxuan.platform.factory.util.LoginUtil;
import money.kuxuan.platform.factory.util.SPUtil;
import money.kuxuan.platform.moneyplatfrom.R;
import money.kuxuan.platform.moneyplatfrom.activities.AccountActivity;
import money.kuxuan.platform.moneyplatfrom.activities.ApActivity;
import money.kuxuan.platform.moneyplatfrom.activities.ApActivity1;
import money.kuxuan.platform.moneyplatfrom.activities.RadiersActivity;
import money.kuxuan.platform.moneyplatfrom.activities.SetActivity;


/**
 *
 */
public class MineFragment extends PresenterFragment<StateContract.Presenter>
        implements StateContract.View {

    private static final String CHANNEL = "CHANNEL";

    private static final String CHANNELOKORNOTOK = "CHANNELOKORNOTOK";
    int flag = 0;

    @BindView(R.id.phone)
    public TextView phoneTv;

    @BindView(R.id.ap_lin)
    LinearLayout ap_lin;
    @BindView(R.id.apply_lin)
    LinearLayout apply_lin;
    @BindView(R.id.lin_problem)
    LinearLayout lin_problem;



    private SelfDialog selfDialog;


    public MineFragment() {
        // Required empty public constructor
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        mPresenter.start();
        if (checkChannel()==false) {
            ap_lin.setVisibility(View.GONE);
            apply_lin.setVisibility(View.GONE);
            lin_problem.setVisibility(View.GONE);
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.start();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEventMainThread(SecondEvent event) {

        String msg = event.getMsg();
        if (msg.equals("登录/注册")) {
            flag = 0;
        } else {
            flag = 1;
        }
        Log.d("harvic", msg);
        phoneTv.setText(msg);

    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_mine;
    }

    @OnClick(R.id.top_rel)
    void topClick() {
        if (flag == 0) {
//            AccountActivity.show(getContext());
//            getActivity().finish();
            Intent intent = new Intent(getActivity(), AccountActivity.class);
            getActivity().startActivityForResult(intent, money.kuxuan.platform.moneyplatfrom.Constant.Code.REQUEST_CODE);
        } else {
            return;
        }
    }

    @OnClick(R.id.set_layout)
    void onSetting() {
        if (flag == 0) {
            createDialog(R.string.no_login);
            return;
        }
        SetActivity.show(getContext());
    }

    @OnClick(R.id.lin_problem)
    public void commonProblem(){

        RadiersActivity.show(getActivity());

    }


    @Override
    public void stateLogin(User user) {
        phoneTv.setText(user.phone);
        flag = 1;
    }

    @Override
    public void setNoLogin() {
        phoneTv.setText("登录/注册");
        flag = 0;
        Boolean isExit = (Boolean) SPUtil.get(getActivity(), Constant.UserInfo.ISEXITE, true);
        if (!isExit) {
            LoginUtil.getInstance().reLoadLogin(new LoginUtil.OnLoadListener() {
                @Override
                public void onLoadSuccess(User user) {
                    stateLogin(user);
                }

                @Override
                public void onLoadFail(int strRes) {
                    phoneTv.setText("登录/注册");
                    flag = 0;
                }
            });
        }
    }

    @Override
    protected StateContract.Presenter initPresenter() {
        return new StatePresenter(this);
    }


    /**
     * Dialog
     */
    public void createDialog(@StringRes int str) {
        selfDialog = new SelfDialog(getContext());
        selfDialog.setTitle("温馨提示");
        selfDialog.setMessage(str);
        selfDialog.setNoOnclickListener("确定", new SelfDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                selfDialog.dismiss();
            }
        });
        selfDialog.show();
    }

    @OnClick(R.id.ap_lin)
    void onApClick() {
        if (flag == 0) {
            createDialog(R.string.no_login);
            return;
        }
        ApActivity.show(getContext());
    }


    @OnClick(R.id.apply_lin)
    void onApplyClick(){
        if(flag==0){
            createDialog(R.string.no_login);
            return;
        }

        ApActivity1.show(getContext());
    }





    private boolean checkChannel() {
        SharedPreferences sp = getActivity().getSharedPreferences(CHANNEL,
                Context.MODE_PRIVATE);
        String channelOk = sp.getString(CHANNELOKORNOTOK, "1");
        return channelOk.equals("0");
    }


}





