package money.kuxuan.platform.common.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.bumptech.glide.RequestManager;

import money.kuxuan.platform.common.R;
import money.kuxuan.platform.common.factory.model.Products;


/**
 * 头像控件
 *
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class PortraitView extends ImageView {
    private Context context;
    public PortraitView(Context context) {
        super(context);
        this.context = context;
    }

    public PortraitView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public PortraitView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }


    public void setup(RequestManager manager, Products product) {
        if (product == null)
            return;
        // 进行显示
        setup(manager, product.getIcon());
    }


    public void setup(RequestManager manager, String url) {
        setup(manager, R.drawable.default_portrait, url);
    }


    public void setup(RequestManager manager, int resourceId, String url) {
        if (url == null)
            url = "";
        manager.load(url)
                .placeholder(resourceId)
                .centerCrop().transform(new GlideRoundTransform(context, 8))
                .dontAnimate() // CircleImageView 控件中不能使用渐变动画，会导致显示延迟
                .into(this);

    }

}
