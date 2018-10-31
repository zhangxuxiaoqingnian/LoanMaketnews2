package money.kuxuan.platform.moneyplatfrom.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;

import butterknife.BindView;
import money.kuxuan.platform.common.app.PresenterActivity;
import money.kuxuan.platform.common.factory.presenter.BaseContract;
import money.kuxuan.platform.moneyplatfrom.Adapter.DaiAdapter2;
import money.kuxuan.platform.moneyplatfrom.Adapter.DaiAdapter3;
import money.kuxuan.platform.moneyplatfrom.Bean.NoteEntity;
import money.kuxuan.platform.moneyplatfrom.R;
import money.kuxuan.platform.moneyplatfrom.sqlite.DatabaseAdapter;
import money.kuxuan.platform.moneyplatfrom.util.DisplayUtils3;

public class RecordActivity extends PresenterActivity implements View.OnClickListener,OnRefreshLoadmoreListener {

    @BindView(R.id.record_back)
    ImageView back;
    @BindView(R.id.record_edit)
    TextView edit;
    @BindView(R.id.record_rv)
    RecyclerView rv;
    @BindView(R.id.ll_empty)
    LinearLayout ll_empty;
    @BindView(R.id.refreshlayout)
    SmartRefreshLayout refreshlayout;
    private int pageSize=5;
    private int pageNum=0;
    private DatabaseAdapter adapter;
    private ArrayList<NoteEntity> limit;
    private ArrayList<NoteEntity> limits=new ArrayList<>();
    private DaiAdapter3 daiAdapter;
    public static final String POSITION="POSITION";
    private boolean liulang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initview();

    }
    public void initview(){
        SharedPreferences sp = getSharedPreferences("Deng", Context.MODE_PRIVATE);
        liulang = sp.getBoolean("liulang", false);
        edit.setOnClickListener(this);
        back.setOnClickListener(this);
        refreshlayout.setOnRefreshLoadmoreListener(this).
                setEnableLoadmore(true)
                .setEnableRefresh(true);
        adapter = new DatabaseAdapter(this);
        limit = adapter.findLimit(pageSize, pageNum);
        if (limit!=null&&limit.size()>0){
            limits.addAll(limit);
            daiAdapter = new DaiAdapter3(this, limits,liulang);
            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.addItemDecoration(new DisplayUtils3.SpacesItemDecoration());
            rv.setAdapter(daiAdapter);

            daiAdapter.setItemposition(new DaiAdapter3.getItemposition() {
                @Override
                public void success(int pos) {
                    DetailActivity.show(RecordActivity.this, limits.get(pos).productid+"","notice",0);
                }
            });
            edit.setClickable(true);
        }else{
            edit.setClickable(false);
            showView();
        }

    }

    private void showView() {
        ll_empty.setVisibility(View.VISIBLE);
        refreshlayout.setVisibility(View.GONE);
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_record;
    }

    @Override
    protected BaseContract.Presenter initPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.record_back:
                finish();
                break;
            case R.id.record_edit:
                pageNum=0;
                adapter.deleteAll();
                limits.clear();
                daiAdapter.notifyDataSetChanged();
                ArrayList<NoteEntity> all = adapter.findAll();
                Log.e("", "onClick: "+all.size());
                showView();
                break;

        }
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        pageNum+=5;
        limit = adapter.findLimit(pageSize, pageNum);
        if (limit.size()<=0){
            refreshlayout.setLoadmoreFinished(true);
        }else{
            refreshlayout.setLoadmoreFinished(false);
            limits.addAll(limit);
            daiAdapter.notifyDataSetChanged();
        }
        refreshlayout.finishLoadmore(1000);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        limits.clear();
        pageNum=0;
        limit = adapter.findLimit(pageSize, pageNum);
        limits.addAll(limit);
        daiAdapter.notifyDataSetChanged();
        refreshlayout.finishRefresh(1000);
    }

    public void gotoShopping(View view) {
       /* Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra(POSITION,1);
        startActivity(intent);*/
       setResult(2);
       finish();
    }
}
