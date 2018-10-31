package money.kuxuan.platform.moneyplatfrom.frags.main.state;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import money.kuxuan.platform.common.app.PresenterFragment;
import money.kuxuan.platform.common.factory.presenter.BaseContract;
import money.kuxuan.platform.factory.Factory;
import money.kuxuan.platform.factory.bean.NewListBean;
import money.kuxuan.platform.factory.model.api.RspModel;
import money.kuxuan.platform.factory.model.api.guoshen.RepaymentListBean;
import money.kuxuan.platform.factory.net.Network;
import money.kuxuan.platform.factory.net.RemoteService;
import money.kuxuan.platform.factory.netword.NetRequestUtils;
import money.kuxuan.platform.moneyplatfrom.Adapter.MyNewAdapter;
import money.kuxuan.platform.moneyplatfrom.R;
import money.kuxuan.platform.moneyplatfrom.activities.AccountActivity;
import money.kuxuan.platform.moneyplatfrom.activities.GuoShenDetail;
import money.kuxuan.platform.moneyplatfrom.guoshen.activity.AddActivity;
import money.kuxuan.platform.moneyplatfrom.util.DisplayUtils2;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 小狼 on 2018/9/19.
 */

public class ExaimeNewFragment extends PresenterFragment {

    private View inflate;
    private RecyclerView rv;
    private RelativeLayout emptt;
    private float sum=0;
    private int num=0;
    private TextView fen;
    private TextView zong;
    private DecimalFormat decimalFormat;
    private ImageView ji;
    private boolean liulang;
    private SharedPreferences sp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = LayoutInflater.from(getActivity()).inflate(R.layout.mynew_layout, null);
        return inflate;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.mynew_layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        rv = (RecyclerView) inflate.findViewById(R.id.mynewrv);
        zong = (TextView) inflate.findViewById(R.id.daizong);
        fen = (TextView) inflate.findViewById(R.id.daifen);
        ji = (ImageView) inflate.findViewById(R.id.ji);
        rv.addItemDecoration(new DisplayUtils2.SpacesItemDecoration());
        TextView time = (TextView) inflate.findViewById(R.id.newtime);

        //获取当前日期
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月");// HH:mm:ss
        Date date = new Date(System.currentTimeMillis());
        time.setText(simpleDateFormat.format(date));

        emptt = (RelativeLayout) inflate.findViewById(R.id.rel_empty);
        sp = getActivity().getSharedPreferences("Deng", Context.MODE_PRIVATE);


        ji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(liulang){
                    AddActivity.show(getActivity());
                }else {
                    Intent intent = new Intent(getActivity(), AccountActivity.class);
                    startActivityForResult(intent, money.kuxuan.platform.moneyplatfrom.Constant.Code.REQUEST_CODE);
                }
            }
        });




    }

    public void getData(){


        Observable<NewListBean> stringObservable = new NetRequestUtils().bucuo().getbaseretrofit().getnewlist().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        stringObservable.subscribe(new Observer<NewListBean>() {

            private MyNewAdapter myNewAdapter;

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(NewListBean s) {

                final List<NewListBean.RstBean> rst = s.rst;
                if(rst.size()>0){

                    rv.setVisibility(View.VISIBLE);
                    emptt.setVisibility(View.GONE);
                    List<NewListBean.RstBean.DetailBean> detail = rst.get(0).detail;

                    sum=0;
                    num=0;
                    DecimalFormat decimalFormat=new DecimalFormat(".00");
                    for (int i = 0; i < rst.size(); i++) {
                        String amount = rst.get(i).amount;
                        float v = Float.parseFloat(amount);
                        sum+= v;

                        String total_amount = rst.get(i).total_amount;
                        float v1 = Float.parseFloat(total_amount);
                        num+=v1;

                    }
                    zong.setText(num+"");
                    fen.setText(decimalFormat.format(sum));

                    myNewAdapter = new MyNewAdapter(getActivity(),rst);
                    rv.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rv.setAdapter(myNewAdapter);

                    myNewAdapter.Setnum(new MyNewAdapter.Getnum() {
                        @Override
                        public void itemclick(int pos) {

                            List<NewListBean.RstBean.DetailBean> detail1 = rst.get(pos).detail;

                            Intent intent=new Intent(getActivity(), GuoShenDetail.class);
                            intent.putExtra("guodata", (Serializable) detail1);
                            intent.putExtra("guoprice",rst.get(pos).amount);
                            intent.putExtra("guostute",rst.get(pos).status);
                            intent.putExtra("guotime",rst.get(pos).first_time);
                            intent.putExtra("guoqishu",rst.get(pos).periods);
                            intent.putExtra("guoid",rst.get(pos).id);
                            intent.putExtra("guoname",rst.get(pos).platform);
                            startActivity(intent);
                        }
                    });
                }else {
                    rv.setVisibility(View.GONE);
                    emptt.setVisibility(View.VISIBLE);
                    zong.setText("0");
                    fen.setText("0");
                }




            }

            @Override
            public void onError(Throwable e) {


            }

            @Override
            public void onComplete() {

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();

        liulang = sp.getBoolean("liulang", false);
        if(liulang){
            //请求数据
            getData();

        }else {
            rv.setVisibility(View.GONE);
            emptt.setVisibility(View.VISIBLE);
            zong.setText("0");
            fen.setText("0");

        }
    }



    @Override
    protected BaseContract.Presenter initPresenter() {
        return null;
    }
}
