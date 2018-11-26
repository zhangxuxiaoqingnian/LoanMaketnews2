package com.smileflowpig.money.moneyplatfrom.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.PresenterActivity;
import com.smileflowpig.money.common.factory.presenter.BaseContract;
import com.smileflowpig.money.factory.netword.NetRequestUtils;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class IdentityCardActivity extends PresenterActivity implements View.OnClickListener{

    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.overget)
    TextView overget;
    @BindView(R.id.name_edit)
    EditText name;
    @BindView(R.id.cord_edit)
    EditText cord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        back.setOnClickListener(this);
        overget.setOnClickListener(this);

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                changedata();
            }
        });
        cord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                changedata();
            }
        });

    }
    @Override
    protected boolean isNeedNotch() {
        return true;
    }
    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_identity_card;
    }

    @Override
    protected BaseContract.Presenter initPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                finish();
                break;
            case R.id.overget:
                String s = name.getText().toString();
                String s1 = cord.getText().toString();
                if(!TextUtils.isEmpty(s)&&!TextUtils.isEmpty(s1)){
                    if(isIDCard(s1)){
                        //进行提交信息
                        getdata(s1,s);
                    }else {
                        Toast.makeText(IdentityCardActivity.this,"身份证号码有误",Toast.LENGTH_SHORT).show();
                    }
                }

                break;

        }
    }

    private void changedata() {

        if (TextUtils.isEmpty(name.getText().toString()) || TextUtils.isEmpty(cord.getText().toString())) {
            //不可点击确认
            overget.setClickable(false);
            overget.setBackground(getResources().getDrawable(R.drawable.buttonnoshap));
        } else {
            overget.setClickable(true);
            overget.setBackground(getResources().getDrawable(R.drawable.buttonshap));
        }

    }
    // 判断是否符合身份证号码的规范
    public static boolean isIDCard(String IDCard) {
        if (IDCard != null) {
            String IDCardRegex = "(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x|Y|y)$)";
            return IDCard.matches(IDCardRegex);
        }
        return false;
    }

    public void getdata(String card,String name){

        Observable<Object> objectObservable = new NetRequestUtils().bucuo().getbaseretrofit().getalltext(card,name).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        objectObservable.subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {
                finish();

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

}
