package money.kuxuan.platform.moneyplatfrom;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import money.kuxuan.platform.common.widget.BaseAutoScrollTextView;
import money.kuxuan.platform.factory.presenter.notice.Notice;
import money.kuxuan.platform.moneyplatfrom.activities.DetailActivity;
import money.kuxuan.platform.moneyplatfrom.helper.VerticalLampView;
import money.kuxuan.platform.moneyplatfrom.web.WebActivity;

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
                        DetailActivity.show(getContext(),list.get(position).getProduct_id(),"notice");
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
        Log.e(TAG,list.get(0).getContent());
    }

}  