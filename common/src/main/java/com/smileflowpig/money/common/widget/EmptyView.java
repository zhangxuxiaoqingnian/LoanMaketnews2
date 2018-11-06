package com.smileflowpig.money.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smileflowpig.money.common.R;
import com.smileflowpig.money.common.app.Application;
import com.smileflowpig.money.common.widget.convention.PlaceHolderView;

import net.qiujuer.genius.ui.widget.Loading;


/**
 *
 * @author HFRX
 * @version 1.0
 * 简单的占位控件，
 * 实现了显示一个空的图片显示，
 * 可以和MVP配合显示没有数据，正在加载等状态
 */
@SuppressWarnings("unused")
public class EmptyView extends LinearLayout implements PlaceHolderView {

    private ImageView mEmptyImg;
    private TextView mStatusText;
    private Loading mLoading;
    private Button mButton;
    private TextView mTextGo;

    private int[] mDrawableIds = new int[]{0, 0};
    private int[] mTextIds = new int[]{0, 0, 0};

    private View[] mBindViews;

    public EmptyView(Context context) {
        super(context);
        init(null, 0);
    }

    public EmptyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public EmptyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        inflate(getContext(), R.layout.lay_empty, this);
        mEmptyImg = (ImageView) findViewById(R.id.im_empty);
        mStatusText = (TextView) findViewById(R.id.txt_empty);
        mLoading = (Loading) findViewById(R.id.loading);
        mButton = (Button)findViewById(R.id.bu_re);
        mTextGo = (TextView) findViewById(R.id.txt_go);


        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.EmptyView, defStyle, 0);

        mDrawableIds[0] = a.getInt(R.styleable.EmptyView_comEmptyDrawable, R.drawable.status_empty);
        mDrawableIds[1] = a.getInt(R.styleable.EmptyView_comErrorDrawable, R.drawable.status_empty);
        mTextIds[0] = a.getInt(R.styleable.EmptyView_comEmptyText, R.string.prompt_error);
        mTextIds[1] = a.getInt(R.styleable.EmptyView_comErrorText, R.string.prompt_empty);
        mTextIds[2] = a.getInt(R.styleable.EmptyView_comLoadingText, R.string.prompt_loading);

        a.recycle();
    }

    /**
     * 绑定一系列数据显示的布局
     * 当前布局隐藏时（有数据时）自动显示绑定的数据布局
     * 而当数据加载时，自动显示Loading，并隐藏数据布局
     *
     * @param views 数据显示的布局
     */
    public void bind(View... views) {
        this.mBindViews = views;
    }

    /**
     * 更改绑定布局的显示状态
     *
     * @param visible 显示的状态
     */
    private void changeBindViewVisibility(int visible) {
        final View[] views = mBindViews;
        if (views == null || views.length == 0)
            return;

        for (View view : views) {
            view.setVisibility(visible);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void triggerEmpty() {
        mLoading.setVisibility(GONE);
        mLoading.stop();
        mButton.setVisibility(View.GONE);
        mEmptyImg.setVisibility(View.INVISIBLE);
        mTextGo.setVisibility(View.GONE);
        mStatusText.setText(mTextIds[1]);
        setVisibility(VISIBLE);
        changeBindViewVisibility(GONE);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void triggerEmpty(String text) {
        mLoading.setVisibility(GONE);
        mLoading.stop();
        mButton.setVisibility(View.GONE);
        mEmptyImg.setVisibility(View.INVISIBLE);
        mTextGo.setVisibility(View.GONE);
        mStatusText.setText(text);
        setVisibility(VISIBLE);
        changeBindViewVisibility(GONE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void triggerNetError() {
        mLoading.setVisibility(GONE);
        mLoading.stop();
        mEmptyImg.setVisibility(View.VISIBLE);
        mStatusText.setText(mTextIds[0]);
        mButton.setVisibility(View.VISIBLE);
        mTextGo.setVisibility(View.VISIBLE);
        setVisibility(VISIBLE);
        changeBindViewVisibility(GONE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void triggerError(@StringRes int strRes) {
        Application.showToast(strRes);
        setVisibility(VISIBLE);
        mButton.setVisibility(View.GONE);
        mEmptyImg.setVisibility(View.INVISIBLE);
        mTextGo.setVisibility(View.GONE);
        changeBindViewVisibility(GONE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void triggerLoading() {
        mStatusText.setText(mTextIds[2]);
        setVisibility(VISIBLE);
        mButton.setVisibility(View.GONE);
        mEmptyImg.setVisibility(View.INVISIBLE);
        mTextGo.setVisibility(View.GONE);
        mLoading.setVisibility(VISIBLE);
        mLoading.start();
        changeBindViewVisibility(GONE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void triggerOk() {
        setVisibility(GONE);
        changeBindViewVisibility(VISIBLE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void triggerOkOrEmpty(boolean isOk) {
        if (isOk)
            triggerOk();
        else
            triggerEmpty();
    }

}