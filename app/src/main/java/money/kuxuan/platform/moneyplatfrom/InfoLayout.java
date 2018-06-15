package money.kuxuan.platform.moneyplatfrom;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import money.kuxuan.platform.factory.model.api.examine.Banner;
import money.kuxuan.platform.factory.model.api.examine.InfoBanner;
import money.kuxuan.platform.moneyplatfrom.activities.Activity_Average_Capital_Calc;
import money.kuxuan.platform.moneyplatfrom.activities.Activity_Average_Capital_Interest_Calc;
import money.kuxuan.platform.moneyplatfrom.activities.Activity_CreditCard_ByStages;
import money.kuxuan.platform.moneyplatfrom.activities.Activity_HouseCalc;
import money.kuxuan.platform.moneyplatfrom.activities.Activity_Income_Tax_Calc;
import money.kuxuan.platform.moneyplatfrom.activities.Activity_loanCarCalc;
import money.kuxuan.platform.moneyplatfrom.activities.InfoActivity;


/**
 * Created by Administrator on 2017/6/29 0029.
 */

public class InfoLayout extends LinearLayout implements View.OnClickListener{
    private Context mContext;
    /**
     * UI
     */
    private LinearLayout mRootView;
    /**
     * Data
     */
    private Banner infoBannerTwo;
    private InfoBanner infoBanner;
    ImageView imageview1;
    ImageView imageview2;
    ImageView imageview3;
    TabLayout tabLayout;
    TextView name;
    TextView people;
//    TextView comment;
    TextView title;
    List<String> tabList;

    LinearLayout click;
    public InfoLayout(Context context, Banner infoBanner) {
        this(context, null, infoBanner,null);
    }

    public InfoLayout(Context context, Banner infoBanner, List<String> list) {
        this(context, null, infoBanner,list);
    }

    public InfoLayout(Context context, InfoBanner infoBanner) {
        this(context, null, infoBanner);
    }
    public InfoLayout(Context context, AttributeSet attrs, Banner infoBanner,List<String> list) {
        super(context, attrs);
        tabList = list;
        mContext = context;
        this.infoBannerTwo = infoBanner;
        initView(infoBanner);
    }
    public InfoLayout(Context context, AttributeSet attrs, InfoBanner infoBanner) {
        super(context, attrs);
        mContext = context;
        this.infoBanner = infoBanner;
        initView(infoBanner);
    }
    private void initView(final Banner infoBanner) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        mRootView = (LinearLayout) inflater.inflate(R.layout.item_info_head_layout, this);
        tabLayout = (TabLayout) mRootView.findViewById(R.id.tablayout);

       for(int i=0;i<5;i++){
           tabLayout.addTab(tabLayout.newTab().setText(tabList.get(i)).setTag(i));
       }

       tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
           @Override
           public void onTabSelected(TabLayout.Tab tab) {


               switch ((Integer)tab.getTag()){

                   case 0:
                       EventBus.getDefault().post("0");
                       break;
                   case 1:
                       EventBus.getDefault().post("1");
                       break;
                   case 2:
                       EventBus.getDefault().post("2");
                       break;
                   case 3:
                       EventBus.getDefault().post("3");
                       break;
                   case 4:
                       EventBus.getDefault().post("4");
                       break;
               }
           }
           @Override
           public void onTabUnselected(TabLayout.Tab tab) {
           }

           @Override
           public void onTabReselected(TabLayout.Tab tab) {
           }
       });

        click = (LinearLayout) findViewById(R.id.click);
        title = (TextView) findViewById(R.id.title);

        LinearLayout lin_housecalc = (LinearLayout) mRootView.findViewById(R.id.lin_housecalc);
        lin_housecalc.setOnClickListener(this);

       LinearLayout lin_averagecapital = (LinearLayout) mRootView.findViewById(R.id.lin_averagecapital);
       lin_averagecapital.setOnClickListener(this);

       LinearLayout lin_averageinterest = (LinearLayout) mRootView.findViewById(R.id.lin_averageinterest);
       lin_averageinterest.setOnClickListener(this);

       LinearLayout lin_lin_loancar = (LinearLayout) mRootView.findViewById(R.id.lin_loancar);
       lin_lin_loancar.setOnClickListener(this);

       LinearLayout lin_creditcardbystages = (LinearLayout) mRootView.findViewById(R.id.lin_creditcardbystages);
       lin_creditcardbystages.setOnClickListener(this);

       LinearLayout lin_incometaxcalc = (LinearLayout) mRootView.findViewById(R.id.lin_incometaxcalc);
       lin_incometaxcalc.setOnClickListener(this);


        imageview1 = (ImageView) mRootView.findViewById(R.id.imageview1);
        click.setOnClickListener(this);



        if(infoBanner!=null) {
            String url = infoBanner.getThumbnail();
            Glide.with(getContext()).load(url).into(imageview1);
        }


    }

    private void initView(final InfoBanner infoBanner) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        mRootView = (LinearLayout) inflater.inflate(R.layout.info_head_layout, this);
        name = (TextView) mRootView.findViewById(R.id.name);
        people = (TextView) mRootView.findViewById(R.id.people);
//        comment = (TextView) mRootView.findViewById(R.id.comment);
        click = (LinearLayout) findViewById(R.id.click);
        title = (TextView) findViewById(R.id.title);
        imageview1 = (ImageView) mRootView.findViewById(R.id.imageview1);
        imageview2 = (ImageView) mRootView.findViewById(R.id.imageview2);
        imageview3 = (ImageView) mRootView.findViewById(R.id.imageview3);
        title.setText(infoBanner.getTitle());
        name.setText(infoBanner.getSource());
        people.setText(infoBanner.getPage_views());
//        comment.setText(infoBanner.getCreate_time());
        click.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                InfoActivity.show(getContext(),infoBanner.getId());

            }
        });
        if(infoBanner.getThumbnail().length==0){
            return;
        }else if(infoBanner.getThumbnail().length==1){
          Glide.with(getContext()).load(infoBanner.getThumbnail()[0]).into(imageview1);
        }else if(infoBanner.getThumbnail().length==2){
            Glide.with(getContext()).load(infoBanner.getThumbnail()[0]).into(imageview1);
            Glide.with(getContext()).load(infoBanner.getThumbnail()[1]).into(imageview2);
        }else if(infoBanner.getThumbnail().length==3){
            Glide.with(getContext()).load(infoBanner.getThumbnail()[0]).into(imageview1);
            Glide.with(getContext()).load(infoBanner.getThumbnail()[1]).into(imageview2);
            Glide.with(getContext()).load(infoBanner.getThumbnail()[2]).into(imageview3);
        }

    }


    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){

            case R.id.lin_averagecapital:
                intent = new Intent(mContext, Activity_Average_Capital_Calc.class);
                mContext.startActivity(intent);
                break;

            case R.id.lin_housecalc:
                intent = new Intent(getContext(), Activity_HouseCalc.class);
                getContext().startActivity(intent);
                break;

            case R.id.click:
            if(infoBannerTwo!=null) {
                InfoActivity.show(getContext(), infoBannerTwo.getId());
            }
                break;

            case R.id.lin_averageinterest:

                intent = new Intent(getContext(), Activity_Average_Capital_Interest_Calc.class);
                getContext().startActivity(intent);

                break;

            case R.id.lin_loancar:

                intent =new Intent(getContext(), Activity_loanCarCalc.class);
                getContext().startActivity(intent);

                break;

            case R.id.lin_creditcardbystages:

                intent = new Intent(getContext(), Activity_CreditCard_ByStages.class);
                getContext().startActivity(intent);

                break;

            case R.id.lin_incometaxcalc:

                intent = new Intent(getContext(), Activity_Income_Tax_Calc.class);
                getContext().startActivity(intent);

                break;

        }

    }
}




