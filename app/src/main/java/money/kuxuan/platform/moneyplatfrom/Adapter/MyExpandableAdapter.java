package money.kuxuan.platform.moneyplatfrom.Adapter;

import android.animation.ValueAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import money.kuxuan.platform.moneyplatfrom.R;
import money.kuxuan.platform.moneyplatfrom.helper.DensityUtil;


/**
 * @author: BigFaceCat
 * @function:
 * @date: 17/6/11
 */
public class MyExpandableAdapter extends BaseAdapter {
    /**
     * Common
     */


    private LayoutInflater mInflate;
    private Context mContext;
    private List<String> parentList;
    private List<String> childList;
    public static HashMap<Integer, Boolean> isSelected;
    TextView parenta;
    int a ;



    public MyExpandableAdapter(Context context, List<String> parentList, List<String> childList) {
        mContext = context;
        this.parentList = parentList;
        this.childList = childList;
        mInflate = LayoutInflater.from(mContext);
        isSelected = new HashMap<Integer, Boolean>();
        for (int i = 0; i < parentList.size(); i++) {
            isSelected.put(i, false);
        }
    }

    @Override
    public int getCount() {
        return parentList.size();
    }

    @Override
    public Object getItem(int position) {
        return parentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //获取数据的type类型

        final String value = (String) getItem(position);
        //无tag时
        convertView = mInflate.inflate(R.layout.item_anim_layout, parent, false);
        parenta = (TextView) convertView.findViewById(R.id.parent);

        final TextView child = (TextView) convertView.findViewById(R.id.child);
        final ImageView point3 = (ImageView) convertView.findViewById(R.id.point3);

        LinearLayout click = (LinearLayout) convertView.findViewById(R.id.click);
        parenta.setText(parentList.get(position));
        child.setText(childList.get(position));

        if(isSelected.get(position)){
            if(position == 0 ){
                child.getLayoutParams().height = DensityUtil.dip2px(mContext,70);
            }if(position ==1){
                child.getLayoutParams().height = DensityUtil.dip2px(mContext,60);
            }if(position==2){
                child.getLayoutParams().height = DensityUtil.dip2px(mContext,80);
            }if(position==3){
                child.getLayoutParams().height = DensityUtil.dip2px(mContext,100);
            }if(position==4){
                child.getLayoutParams().height = DensityUtil.dip2px(mContext,120);
            }if(position==5){
                child.getLayoutParams().height = DensityUtil.dip2px(mContext,80);
            }
            animaStart(isSelected.get(position), point3);

        }
        click.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                isSelected.put(position, !isSelected.get(position));
                performAnim2(isSelected.get(position), child,position);
                anima(isSelected.get(position), point3);
            }
        });


        return convertView;
    }

    private void performAnim2(boolean show, final View view,int position) {
        //View是否显示的标志
        show = !show;
        //属性动画对象
        ValueAnimator va;
        if(position == 0 ){
            a= DensityUtil.dip2px(mContext,70);
        }if(position ==1){
            a= DensityUtil.dip2px(mContext,60);
        }if(position==2){
            a= DensityUtil.dip2px(mContext,80);
        }if(position==3){
            a= DensityUtil.dip2px(mContext,150);
        }if(position==4){
            a= DensityUtil.dip2px(mContext,130);
        }if(position==5){
            a= DensityUtil.dip2px(mContext,80);
        }
        if (!show) {
            //显示view，高度从0变到height值

            va = ValueAnimator.ofInt(0, a);
        } else {
            //隐藏view，高度从height变为0

            va = ValueAnimator.ofInt(a, 0);
        }
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                //获取当前的height值
                int h = (Integer) valueAnimator.getAnimatedValue();
                //动态更新view的高度
                view.getLayoutParams().height = h;
                view.requestLayout();
            }
        });

        final boolean finalShow = show;

        va.setDuration(500);
        //开始动画
        va.start();
    }

    private void anima(final boolean flag, final View view) {
        if (flag) {
            Animation animation = new RotateAnimation(0f, 90f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setDuration(500);
            animation.setFillAfter(true);//设置为true，动画转化结束后被应用
            view.startAnimation(animation);//开始动画

        } else {
            Animation animation = new RotateAnimation(90f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setDuration(500);
            animation.setFillAfter(true);//设置为true，动画转化结束后被应用
            view.startAnimation(animation);//开始动画
        }


    }


    private void animaStart(final boolean flag, final View view) {
        if (flag) {
            Animation animation = new RotateAnimation(0f, 90f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setDuration(10);
            animation.setFillAfter(true);//设置为true，动画转化结束后被应用
            view.startAnimation(animation);//开始动画

        } else {
            Animation animation = new RotateAnimation(90f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setDuration(10);
            animation.setFillAfter(true);//设置为true，动画转化结束后被应用
            view.startAnimation(animation);//开始动画
        }


    }

    public static HashMap<Integer, Boolean> getIsSelected() {
        return isSelected;
    }

    /**
     * 通知副布局占用的
     * @param view
     */
    private void measureView(View view){
        ViewGroup.LayoutParams p = view.getLayoutParams();
        if(p==null){
            p=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int width = ViewGroup.getChildMeasureSpec(0,0,p.width);
        int height;
        int tempHeight = p.height;
        if(tempHeight>0){
            height = View.MeasureSpec.makeMeasureSpec(tempHeight, View.MeasureSpec.EXACTLY);
        }else{
            height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.EXACTLY);
        }
        view.measure(width,height);
    }
}
