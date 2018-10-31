package money.kuxuan.platform.moneyplatfrom.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import money.kuxuan.platform.common.app.PresenterActivity;
import money.kuxuan.platform.common.factory.presenter.BaseContract;
import money.kuxuan.platform.moneyplatfrom.Adapter.DaiAdapter;
import money.kuxuan.platform.moneyplatfrom.Adapter.DaiAdapter2;
import money.kuxuan.platform.moneyplatfrom.R;
import money.kuxuan.platform.moneyplatfrom.util.DisplayUtils2;
import money.kuxuan.platform.moneyplatfrom.util.DisplayUtils3;

public class MyDaiActivity extends PresenterActivity implements View.OnClickListener{

    private TextView title;
    private ImageView back;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initview();
        back.setOnClickListener(this);
        Intent intent = getIntent();
        String daitype = intent.getStringExtra("daitype");

        title.setText(daitype);

        List<String> list=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("");
        }
        DaiAdapter2 daiAdapter=new DaiAdapter2(MyDaiActivity.this,list,null);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.addItemDecoration(new DisplayUtils3.SpacesItemDecoration());
        rv.setAdapter(daiAdapter);


    }
    public void initview(){

        back = (ImageView) findViewById(R.id.dai_back);
        title = (TextView) findViewById(R.id.dai_title);
        rv = (RecyclerView) findViewById(R.id.dairv);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_my_dai;
    }

    @Override
    protected BaseContract.Presenter initPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dai_back:
                finish();
                break;

        }
    }
}
