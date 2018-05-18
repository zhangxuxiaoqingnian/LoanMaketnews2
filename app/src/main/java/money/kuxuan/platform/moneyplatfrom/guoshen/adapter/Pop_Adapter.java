package money.kuxuan.platform.moneyplatfrom.guoshen.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import money.kuxuan.platform.factory.model.api.guoshen.PopModel;
import money.kuxuan.platform.factory.model.api.guoshen.PutStats;
import money.kuxuan.platform.moneyplatfrom.guoshen.activity.HomePresenter;
import money.kuxuan.platform.moneyplatfrom.guoshen.viewholder.guoshen_PopViewHolder;

/**
 * Created by Allence on 2018/5/8 0008.
 */

public class Pop_Adapter extends BaseQuickAdapter<PopModel,guoshen_PopViewHolder> {


    public Pop_Adapter(int layoutResId, @Nullable List<PopModel> data) {
        super(layoutResId, data);
    }

    public Pop_Adapter(@Nullable List<PopModel> data) {
        super(data);
    }

    public Pop_Adapter(int layoutResId) {
        super(layoutResId);
    }
    TextView update0;
    TextView update1;
    HomePresenter mPresenter;


    public Pop_Adapter(int layoutResId, TextView t0, TextView t1, HomePresenter mPresenter,@Nullable List<PopModel> data) {
        super(layoutResId,data);
        update0 = t0;
        update1 = t1;
        this.mPresenter = mPresenter;
        init();
    }

    public Pop_Adapter(int layoutResId, TextView t0, TextView t1, HomePresenter mPresenter) {
        super(layoutResId);
        update0 = t0;
        update1 = t1;
        this.mPresenter = mPresenter;
        init();
    }


    Gson gson = new Gson();
    private void init() {
        update1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<PutStats> putStats = new ArrayList<>();

                  if(intlist!=null&&intlist.size()>0){

                      for (int i=0;i<intlist.size();i++){
                          if(mData.get(intlist.get(i)).getStatus()==1){
                              continue;
                          }
                          putStats.add(new PutStats(mData.get(intlist.get(i)).getId(),1));
                      }

                      if(putStats.size()==0){
                          return;
                      }

                     String json =  gson.toJson(putStats);
                      mPresenter.putStatus(json);
                  }
            }
        });




        update0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<PutStats> putStats = new ArrayList<>();
                for (int i=0;i<intlist.size();i++){

                    if(mData.get(intlist.get(i)).getStatus()==0){
                        continue;
                    }

                    putStats.add(new PutStats(mData.get(intlist.get(i)).getId(),0));
                }
                if(putStats.size()==0){
                    return;
                }
                String json =  gson.toJson(putStats);
                mPresenter.putStatus(json);

            }
        });

    }

    List<Integer> intlist = new ArrayList<>();

    @Override
    protected void convert(final guoshen_PopViewHolder helper, PopModel item) {

        if(intlist!=null&&intlist.size()>0){
            if(intlist.contains(helper.getLayoutPosition())){
                helper.guoshen_checkbox.setChecked(true);
            }
        }else {
            helper.guoshen_checkbox.setChecked(false);
        }

        helper.guoshen_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    intlist.add(helper.getLayoutPosition());
                }

            }
        });

        helper.tv_qs.setText(item.getPeriods());
        String date = item.getDate();
        String[] dateArr = date.split(" ");
        helper.tv_hkrnum.setText(dateArr[0]);
        helper.tv_hkjenum.setText(item.getAmount());

        if(item.getStatus()==0){
            helper.tv_status.setText("待还款");
        }else {
            helper.tv_status.setText("已结清");
        }


    }
}

