package money.kuxuan.platform.common.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import money.kuxuan.platform.common.R;


/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */

public class AdDialog extends Dialog {

    private Button no;//关闭按钮

    private ImageView imageView;
    private String url;//从外界设置的title文本
    @StringRes
    int message;
    //从外界设置的消息文本
    //确定文本和取消文本的显示内容
    private String noStr;

    private AdDialog.onNoOnclickListener noOnclickListener;//取消按钮被点击了的监听器

    private onImageOnclickListener onImageClickListener;//取消按钮被点击了的监听器
    /**
     * 设置取消按钮的显示内容和监听
     *
     * @param str
     * @param onNoOnclickListener
     */
    public void setNoOnclickListener(String str, AdDialog.onNoOnclickListener onNoOnclickListener) {
        if (str != null) {
            noStr = str;
        }
        this.noOnclickListener = onNoOnclickListener;
    }


    /**
     * 设置取消按钮的显示内容和监听
     *
     * @param onImageClickListener
     */
    public void setImageClickListener(onImageOnclickListener onImageClickListener) {

        this.onImageClickListener = onImageClickListener;
    }

    public AdDialog(Context context) {
        super(context, R.style.MyDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ad_layout);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);
        //初始化界面控件
        initView();
        //初始化界面数据
        initData();
        //初始化界面控件的事件
        initEvent();

    }

    /**
     * 初始化界面的确定和取消监听器
     */
    private void initEvent() {
        //设置取消按钮被点击后，向外界提供监听
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noOnclickListener != null) {
                    noOnclickListener.onNoClick();
                }
            }
        });

        //设置取消按钮被点击后，向外界提供监听
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onImageClickListener != null) {
                    onImageClickListener.onImageClick();
                }
            }
        });

    }

    /**
     * 初始化界面控件的显示数据
     */
    private void initData() {
        //如果用户自定了title和message
        if (url != null) {
            Glide.with(getContext()).load(url).into(imageView);
        }

    }

    /**
     * 初始化界面控件
     */
    private void initView() {
        no = (Button) findViewById(R.id.close_button);

        imageView = (ImageView) findViewById(R.id.ad_im);

    }

    /**
     * 从外界Activity为Dialog设置标题
     */
    public void setImageView(String url) {
        this.url = url;
    }



    public interface onNoOnclickListener {
        public void onNoClick();

    }

    public interface onImageOnclickListener {
        public void onImageClick();

    }
}