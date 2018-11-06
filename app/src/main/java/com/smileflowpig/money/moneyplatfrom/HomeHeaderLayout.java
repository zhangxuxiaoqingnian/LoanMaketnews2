package com.smileflowpig.money.moneyplatfrom;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.smileflowpig.money.moneyplatfrom.activities.ChildProductActivity;
import com.smileflowpig.money.moneyplatfrom.activities.DetailActivity;
import com.smileflowpig.money.moneyplatfrom.activities.MessageActivity;
import com.smileflowpig.money.moneyplatfrom.web.WebActivity;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import com.smileflowpig.money.common.R;
import com.smileflowpig.money.factory.model.db.BannerData;
import com.smileflowpig.money.factory.model.db.Category;
import com.smileflowpig.money.factory.presenter.home.HomePresenter;
import com.smileflowpig.money.factory.presenter.notice.Notice;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;


/**
 * @author: BigFaceCat
 * @function:
 * @date: 16/9/2
 */
public class HomeHeaderLayout extends RelativeLayout implements OnBannerListener {

    private Context mContext;

    private List<BannerData> bannerList;

    public static final String NEWPLATFORM =  "新品区";

    public static final String QUICKPLATFORM =  "1"; //极速贷

    public static final String HOTPLATFORM =  "2"; //热门贷

    public static final String CREDITPLATFORM =  "3"; //信用贷

    public static final String BIGPLATFORM =  "4"; //大额贷


    private List<Category> categoryList;

    private static final Integer[] text = {R.id.new_two, R.id.new_three, R.id.new_four, R.id.new_five};

    private static final String TAG = "HomeHeaderLayout";

    /**
     * UI
     */
    private RelativeLayout mRootView;

    private RelativeLayout quick_rel;

    RelativeLayout new_rel;

    RelativeLayout hot_rel;

//    RelativeLayout credit_rel;

    RelativeLayout big_rel;

    ImageView im_message;




    private int max_id;

    private Badge bage;

    private LampView lampView;

    public HomeHeaderLayout(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    private void initView() {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        mRootView = (RelativeLayout) inflater.inflate(R.layout.listview_header, this);
        new_rel = (RelativeLayout) mRootView.findViewById(R.id.new_rel);
        quick_rel = (RelativeLayout) mRootView.findViewById(R.id.quick_rel);
        hot_rel = (RelativeLayout) mRootView.findViewById(R.id.hot_rel);
//        credit_rel = (RelativeLayout) mRootView.findViewById(R.id.credit_rel);
        big_rel = (RelativeLayout) mRootView.findViewById(R.id.big_rel);
        im_message = (ImageView) mRootView.findViewById(R.id.im_message);

        bage = new QBadgeView(mContext).bindTarget(im_message).setBadgeNumber(-1).
                setBadgeGravity(Gravity.END | Gravity.TOP).setBadgePadding(4,true).setShowShadow(false);
        im_message.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                HomePresenter.save(mContext,max_id);
                bage.hide(false);

                MessageActivity.show(mContext);

            }
        });
        lampView = (LampView) findViewById(R.id.lampview);
        new_rel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ChildProductActivity.show(getContext(),NEWPLATFORM);
            }
        });
        quick_rel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ChildProductActivity.show(getContext(),QUICKPLATFORM);
                TextView new_two = (TextView) findViewById(R.id.new_two);
                new_two.setVisibility(View.GONE);
            }
        });

        hot_rel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ChildProductActivity.show(getContext(),HOTPLATFORM);
                TextView new_three = (TextView) findViewById(R.id.new_three);
                new_three.setVisibility(View.GONE);
            }
        });


//        credit_rel.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ChildProductActivity.show(getContext(),CREDITPLATFORM);
//                TextView new_four = (TextView) findViewById(R.id.new_four);
//                new_four.setVisibility(View.GONE);
//            }
//        });
        big_rel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ChildProductActivity.show(getContext(),BIGPLATFORM);
                TextView new_five = (TextView) findViewById(R.id.new_five);
                new_five.setVisibility(View.GONE);
            }
        });


    }

    public void setNotice(List<Notice> noticeList){
        lampView.setList(noticeList);

    }
    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
        for (int i = 0; i < categoryList.size(); i++) {

            if (!TextUtils.isEmpty(categoryList.get(i).getLabel_name())) {
                TextView textView = (TextView) mRootView.findViewById(text[i]);
                textView.setVisibility(View.VISIBLE);

                textView.setText(categoryList.get(i).getLabel_name());

            }
        }

    }

    public void setMessage(boolean flag,int max_id){

        showOrHideBage(flag);
        this.max_id = max_id;

    }


    public void setBannerList(List<BannerData> bannerList) {
        this.bannerList = bannerList;

        List<String> photoList = new ArrayList<String>();
        for (int i = 0; i < bannerList.size(); i++) {
            photoList.add(bannerList.get(i).getPhoto());
        }
        Banner banner = (Banner) findViewById(R.id.banner);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setOnBannerListener(this);
        banner.setImages(photoList);
        banner.setDelayTime(5000);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

    }

    @Override
    public void OnBannerClick(int position) {
        if(TextUtils.isEmpty(bannerList.get(position).getLink())){
            DetailActivity.show(mContext,bannerList.get(position).getId(),"banner",0);
        }else{
            WebActivity.show(mContext,null,
                    bannerList.get(position).getLink(),bannerList.get(position).getId(),bannerList.get(position).getSkip_type());
        }
    }

    public void showOrHideBage(boolean flag){
        if(flag){
            bage = new QBadgeView(mContext).bindTarget(im_message).setBadgeNumber(-1).
                    setBadgeGravity(Gravity.END | Gravity.TOP).setBadgePadding(4,true).setShowShadow(false);
        }else{
            bage.hide(false);
        }

    }


}

class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        //Glide 加载图片简单用法
        Glide.with(context).load(path).into(imageView);
    }


}

