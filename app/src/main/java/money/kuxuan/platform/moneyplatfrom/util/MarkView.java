package money.kuxuan.platform.moneyplatfrom.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.ButterKnife;
import money.kuxuan.platform.moneyplatfrom.R;

/**
 * Created by Alen on 2017/12/29.
 * 评分视图
 * 一共十分   5个星星
 */

public class MarkView extends LinearLayout implements View.OnClickListener {

    ImageView[] imageViews = new ImageView[5];
    private double mValue = 0;
    private boolean disable = false;// 默认可以点击
    private boolean editMode = false;// 默认可以点击
    private int contentSize;
    private int paddingLeft;
    public MarkView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_mark, this);

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.MarkViewStyle);
        contentSize = typedArray.getDimensionPixelSize(R.styleable.MarkViewStyle_contentSize, sp2px(getContext(), 19));
        paddingLeft = typedArray.getDimensionPixelSize(R.styleable.MarkViewStyle_leftPadding, sp2px(getContext(), 4));
        disable = typedArray.getBoolean(R.styleable.MarkViewStyle_disabled, false);

        initView();
    }

    private void initView() {
        imageViews[0] = ButterKnife.findById(this, R.id.m_view_mark_start_1);
        imageViews[1] = ButterKnife.findById(this, R.id.m_view_mark_start_2);
        imageViews[2] = ButterKnife.findById(this, R.id.m_view_mark_start_3);
        imageViews[3] = ButterKnife.findById(this, R.id.m_view_mark_start_4);
        imageViews[4] = ButterKnife.findById(this, R.id.m_view_mark_start_5);
        for (int i = 0; i < 5; i++) {
            ViewGroup.LayoutParams paramses = imageViews[i].getLayoutParams();
            paramses.width = contentSize;
            paramses.height = contentSize;
            imageViews[i].setPadding(paddingLeft,0,0,0);
            imageViews[i].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        if(editMode){
            return;
        }
        if(disable)return;
        int id = v.getId();
        if(id == R.id.m_view_mark_start_1) {
            mValue = 2.0f;
            changeViewState();
        } else if(id == R.id.m_view_mark_start_2) {
            mValue = 4.0f;
            changeViewState();
        } else if(id == R.id.m_view_mark_start_3) {
            mValue = 6.0f;
            changeViewState();
        } else if(id == R.id.m_view_mark_start_4) {
            mValue = 8.0f;
            changeViewState();
        } else if(id == R.id.m_view_mark_start_5) {
            mValue = 10.0f;
            changeViewState();
        }
    }

    private void changeViewState(){
        for (int i = 0; i < 5; i++) {
            if((i + 1) * 2 <= mValue){
                imageViews[i].setImageResource(R.mipmap.xing_bai);
            } else if(i * 2 < mValue) {
                imageViews[i].setImageResource(R.mipmap.img_star_halffull);
            } else {
                imageViews[i].setImageResource(R.mipmap.xiang_yellow);
            }
        }
    }

    public void setEditMode() {
        editMode = true;
        setDisable(true);
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public void setValue(double score) {
        mValue = (int)score;
        changeViewState();
    }

    public double getValue(){
        return mValue;
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

}
