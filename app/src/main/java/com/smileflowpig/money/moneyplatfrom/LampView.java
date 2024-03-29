package com.smileflowpig.money.moneyplatfrom;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

import com.smileflowpig.money.moneyplatfrom.activities.DetailActivity;
import com.smileflowpig.money.moneyplatfrom.helper.VerticalLampView;
import com.smileflowpig.money.moneyplatfrom.web.WebActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.smileflowpig.money.R;
import com.smileflowpig.money.common.widget.BaseAutoScrollTextView;
import com.smileflowpig.money.factory.presenter.notice.Notice;
import com.umeng.analytics.MobclickAgent;

import static com.raizlabs.android.dbflow.config.FlowLog.TAG;

public class LampView extends FrameLayout {
  
    @BindView(R.id.lamp_view)
    VerticalLampView lampView;
  
    private Context mContext = null;
    private List<Notice> list=new ArrayList<>();
  
    public LampView(Context context, AttributeSet attrs) {
        super(context, attrs);  
        this.mContext = context;  
        init();
    }

    private void init() {  
        setView();  
    }  
  
    private void setView() {  
        inflate(getContext(), R.layout.lamp_layout, this);  
        ButterKnife.bind(this, this);

    }  
  
    private void initView() {

        lampView.setData((ArrayList<Notice>) list);
        lampView.setTextSize(12);
        lampView.setTimer(3000);
        lampView.start();

        lampView.setOnItemClickListener(new BaseAutoScrollTextView.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if(TextUtils.isEmpty(list.get(position).getLink())){
                    return;
                }
                if(list.get(position).getSkip_type().equals("0")){
                    if(list.get(position).getProduct_id().equals("0")){
                        WebActivity.show(getContext(),null,
                                list.get(position).getLink(),list.get(position).getProduct_id(),
                                list.get(position).getSkip_type());
                    }else{
                        MobclickAgent.onEvent(getContext(), "homeNoticeClick");
                        DetailActivity.show(getContext(),list.get(position).getProduct_id(),"notice",0,4);
//                        Intent intent=new Intent(getContext(),NewDetailActivity.class);
//                        intent.putExtra("typeid",list.get(position).getProduct_id());
//                        getContext().startActivity(intent);
                    }
                }else{
                    WebActivity.show(getContext(),null,list.get(position).getLink(),
                            list.get(position).getProduct_id(),
                            list.get(position).getSkip_type());
                }
            }
        });

    }

    public void setList(List<Notice> list){
        this.list = list;
        initView();
    }

}  