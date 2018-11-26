package com.smileflowpig.money.moneyplatfrom.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.smileflowpig.money.moneyplatfrom.Constant;

import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.PresenterActivity;
import com.smileflowpig.money.common.factory.presenter.BaseContract;
import com.smileflowpig.money.common.widget.SelfDialog;
import com.smileflowpig.money.factory.bean.CancelMyBean;
import com.smileflowpig.money.factory.bean.MyDetailBean;
import com.smileflowpig.money.factory.bean.QiangBean;
import com.smileflowpig.money.factory.netword.NetRequestUtils;
import com.umeng.analytics.MobclickAgent;

public class MyHomeActivity extends PresenterActivity {

    private TextView tv_title;
    private ImageView iv_add;
    private ImageView iv_mine;
    private TextView phone;
    private TextView man;
    private TextView text;
    private TextView address;
    private TextView price;
    private TextView time;
    private TextView name;
    private ImageView fabuimg;
    private TextView fabutext;
    private ImageView jinimg;
    private TextView jintext;
    private ImageView wanimg;
    private TextView wantext;
    private ImageView jinjian;
    private ImageView wanjian;
    private TextView qiang;
    private CircleImageView icon;
    private boolean liulang;
    private LinearLayout layout;
    private static PopupWindow popupWindow;

    private static Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            popupWindow.dismiss();

        }
    };
    private int itemid;
    private CircleImageView qiangimg;
    private TextView qiangname;
    private TextView qiangphone;
    private LinearLayout over;
    private boolean isqiang=false;
    private boolean ismy=false;
    private TextView title;
    private TextView success;
    @Override
    protected boolean isNeedNotch() {
        return true;
    }
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initview();

        iv_mine.setImageResource(R.drawable.guoshen_back);
        iv_add.setVisibility(View.GONE);

        iv_mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_title.setText("订单详情");

        itemid = getIntent().getIntExtra("itemid", -1);

        //数据请求
        getdetaildata(itemid);

       SharedPreferences sp = getSharedPreferences("Deng", Context.MODE_PRIVATE);
        liulang = sp.getBoolean("liulang", false);

        success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //显示popuwindow
                getsuccess();

            }
        });

    }

    public void getsuccess(){
        light(0.8f);
        View inflate = LayoutInflater.from(MyHomeActivity.this).inflate(R.layout.success_layout, null, false);
        int height = getWindowManager().getDefaultDisplay().getHeight();
        int width = getWindowManager().getDefaultDisplay().getWidth();
        final PopupWindow popupWindow=new PopupWindow(inflate,width/3*2,height/3);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(false);
        popupWindow.showAtLocation(layout,Gravity.CENTER,0,0);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                light(1.0f);
            }
        });

        TextView no = (TextView) inflate.findViewById(R.id.cancleno);
        TextView yes = (TextView) inflate.findViewById(R.id.cancleyes);

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //请求接口
                getsuccessfor();
                popupWindow.dismiss();
            }
        });
    }

    public void getsuccessfor(){

        Observable<Object> objectObservable = new NetRequestUtils().bucuo().getbaseretrofit().getsuccess(itemid).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        objectObservable.subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {

                getdetaildata(itemid);

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void getmycancel(int id){

        Observable<CancelMyBean> objectObservable = new NetRequestUtils().bucuo().getbaseretrofit().getmycancle(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        objectObservable.subscribe(new Observer<CancelMyBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CancelMyBean o) {
                Toast.makeText(MyHomeActivity.this,o.rst,Toast.LENGTH_SHORT).show();
                if(o.rst.equals("取消发单成功")){
                    getdetaildata(itemid);
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
    public void getDialog(){

        final SelfDialog selfDialog=new SelfDialog(MyHomeActivity.this);
        selfDialog.setTitle("提醒");
        selfDialog.setMessage(R.string.cancleselect);
        selfDialog.setYesOnclickListener("是的", new SelfDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {

                getcancel(itemid);
                selfDialog.dismiss();
            }
        });
        selfDialog.setNoOnclickListener("我再想想", new SelfDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {

                selfDialog.dismiss();
            }
        });
        selfDialog.show();

    }

    public void getdialogtwo(){

        final SelfDialog selfDialog=new SelfDialog(MyHomeActivity.this);
        selfDialog.setTitle("提醒");
        selfDialog.setMessage(R.string.cancleselecttwo);
        selfDialog.setYesOnclickListener("是的", new SelfDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {

                getmycancel(itemid);
                selfDialog.dismiss();
            }
        });
        selfDialog.setNoOnclickListener("我再想想", new SelfDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {

                selfDialog.dismiss();
            }
        });
        selfDialog.show();

    }

    public void getcancel(int id){

        Observable<QiangBean> qiangBeanObservable = new NetRequestUtils().bucuo().getbaseretrofit().getcancle(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        qiangBeanObservable.subscribe(new Observer<QiangBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(QiangBean qiangBean) {

                getdetaildata(itemid);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    public void getqiang(int id){

        Observable<QiangBean> objectObservable = new NetRequestUtils().bucuo().getbaseretrofit().getqiang(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        objectObservable.subscribe(new Observer<QiangBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(QiangBean o) {

                //成功之后弹出框
                popupkuang();


            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    public void popupkuang(){

        View inflate = LayoutInflater.from(MyHomeActivity.this).inflate(R.layout.kuang_layout, null, false);
        //获取屏幕的高度和宽度
        int height = getWindowManager().getDefaultDisplay().getHeight();
        int width = getWindowManager().getDefaultDisplay().getWidth();
        popupWindow = new PopupWindow(inflate, width,height,true);
        light(0.8f);
        popupWindow.showAtLocation(layout, Gravity.CENTER,0,0);

        handler.sendEmptyMessageDelayed(0,3000);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                light(1.0f);
                getdetaildata(itemid);
            }
        });

    }


    //popuwindow关闭后背景变亮
    public void light(float t){
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = t;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }

    public void getdetaildata(int id){

        Observable<MyDetailBean> objectObservable = new NetRequestUtils().bucuo().getbaseretrofit().getdetails(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        objectObservable.subscribe(new Observer<MyDetailBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MyDetailBean o) {

                MyDetailBean.RstBean rst = o.rst;
                name.setText(rst.publisher_nick);
                if(rst.publisher_avatar.equals("")){
                    icon.setImageResource(R.mipmap.nameicon);
                }else {
                    Glide.with(MyHomeActivity.this).load(rst.publisher_avatar).into(icon);
                }
        time.setText(rst.time);
        price.setText(rst.remuneration+"元");
        address.setText(rst.place);
        text.setText(rst.remark);
        man.setText(rst.contacts);
        phone.setText(rst.contact_number);
        title.setText(rst.title);



        if(rst.recipient_id==0){
            over.setVisibility(View.GONE);
            isqiang=false;

        }else {
            isqiang=true;
            over.setVisibility(View.VISIBLE);
            if(rst.recipient_avatar.equals("")){
              qiangimg.setImageResource(R.mipmap.nameicon);
            }else {
                Glide.with(MyHomeActivity.this).load(rst.recipient_avatar).into(qiangimg);
            }
            qiangname.setText(rst.recipient_nick);
            qiangphone.setText(rst.recipient_phone);

        }

        if(rst.is_publisher.equals("0")){

            if(rst.recipient_id==0){
                qiang.setText("抢单");
            }else {
                qiang.setText("取消抢单");
            }
            qiang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(liulang){

                        if(isqiang){
                            //取消抢单
                            getDialog();
                        }else {
                            //进行抢单操作
                            getqiang(itemid);
                        }

                    }else {
                        Intent intent = new Intent(MyHomeActivity.this, AccountActivity.class);
                        startActivityForResult(intent, Constant.Code.REQUEST_CODE);
                    }

                }
            });
            ismy=false;
        }else {
            if(rst.recipient_id==0){
                qiang.setVisibility(View.VISIBLE);
            }else {
                qiang.setVisibility(View.GONE);
            }
            qiang.setText("取消发布");
            success.setVisibility(View.VISIBLE);
            ismy=true;
            qiang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    getdialogtwo();

                }
            });
        }


        if(rst.status.equals("已发布")){
            success.setVisibility(View.GONE);
            fabuimg.setImageResource(R.mipmap.fabu);
            fabutext.setText("已发布");
            fabutext.setTextColor(Color.parseColor("#5D64FF"));
            jinjian.setImageResource(R.mipmap.jiantou);

            jinimg.setImageResource(R.mipmap.nojinxing);
            jintext.setTextColor(Color.parseColor("#666666"));
            wanjian.setImageResource(R.mipmap.nojiantou);
        }else if(rst.status.equals("进行中")){
            fabuimg.setImageResource(R.mipmap.fabu);
            fabutext.setText("已发布");
            fabutext.setTextColor(Color.parseColor("#5D64FF"));
            jinjian.setImageResource(R.mipmap.jiantou);
            jinimg.setImageResource(R.mipmap.jinxing);
            jintext.setTextColor(Color.parseColor("#5D64FF"));
            wanjian.setImageResource(R.mipmap.jiantou);
        }else if(rst.status.equals("已完成")){
            qiang.setVisibility(View.GONE);
            success.setVisibility(View.GONE);
            fabuimg.setImageResource(R.mipmap.fabu);
            fabutext.setText("已发布");
            fabutext.setTextColor(Color.parseColor("#5D64FF"));
            jinjian.setImageResource(R.mipmap.jiantou);
            jinimg.setImageResource(R.mipmap.jinxing);
            jintext.setTextColor(Color.parseColor("#5D64FF"));
            wanjian.setImageResource(R.mipmap.jiantou);

            wanimg.setImageResource(R.mipmap.wanok);
            wantext.setTextColor(Color.parseColor("#5D64FF"));
            qiang.setFocusable(false);


        }else {
            qiang.setText("已取消");
            success.setVisibility(View.GONE);
            qiang.setEnabled(false);
            qiang.setBackgroundResource(R.drawable.over_get3);

            fabuimg.setImageResource(R.mipmap.nofabu);
            fabutext.setTextColor(Color.parseColor("#666666"));
            jinjian.setImageResource(R.mipmap.nojiantou);

            jinimg.setImageResource(R.mipmap.nojinxing);
            jintext.setTextColor(Color.parseColor("#666666"));
            wanjian.setImageResource(R.mipmap.nojiantou);

            wanimg.setImageResource(R.mipmap.nowanok);
            wantext.setTextColor(Color.parseColor("#666666"));

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
    protected int getContentLayoutId() {
        return R.layout.activity_my_home;
    }

    @Override
    protected BaseContract.Presenter initPresenter() {
        return null;
    }

    public void initview(){

        iv_mine = (ImageView) findViewById(R.id.iv_mine);
        iv_add = (ImageView) findViewById(R.id.iv_add);
        tv_title = (TextView) findViewById(R.id.tv_title);
        name = (TextView) findViewById(R.id.detailname);
        time = (TextView) findViewById(R.id.detailtime);
        price = (TextView) findViewById(R.id.detailprice);
        address = (TextView) findViewById(R.id.detailaddress);
        text = (TextView) findViewById(R.id.detailtext);
        man = (TextView) findViewById(R.id.detailman);
        phone = (TextView) findViewById(R.id.detailphone);
        fabuimg = (ImageView) findViewById(R.id.fabuimg);
        fabutext = (TextView) findViewById(R.id.fabutext);
        success = (TextView) findViewById(R.id.success);

        jinimg = (ImageView) findViewById(R.id.jinxingimg);
         jintext = (TextView) findViewById(R.id.jinxingtext);
         wanimg = (ImageView) findViewById(R.id.wanimg);
          wantext = (TextView) findViewById(R.id.wantext);
           wanjian = (ImageView) findViewById(R.id.wanjian);
           jinjian = (ImageView) findViewById(R.id.jinjian);
         qiang = (TextView) findViewById(R.id.qiang);
        icon = (CircleImageView) findViewById(R.id.detailicon);
        layout = (LinearLayout) findViewById(R.id.layout);
        qiangimg = (CircleImageView) findViewById(R.id.qiangimg);
        qiangname = (TextView) findViewById(R.id.qiangname);
        qiangphone = (TextView) findViewById(R.id.qiangphone);
        over = (LinearLayout) findViewById(R.id.qiangdanover);
        title = (TextView) findViewById(R.id.detailtitle);
    }
}
