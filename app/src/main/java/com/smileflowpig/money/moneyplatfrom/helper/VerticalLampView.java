package com.smileflowpig.money.moneyplatfrom.helper;

import android.content.Context;
import android.util.AttributeSet;

import com.smileflowpig.money.common.widget.BaseAutoScrollTextView;
import com.smileflowpig.money.factory.presenter.notice.Notice;

public class VerticalLampView extends
        BaseAutoScrollTextView<Notice> {
  
    public VerticalLampView(Context context, AttributeSet attrs,
                            int defStyle) {  
        super(context, attrs, defStyle);  
    }  
  
    public VerticalLampView(Context context, AttributeSet attrs) {  
        super(context, attrs);  
    }  
  
    public VerticalLampView(Context context) {  
        super(context);  
    }  

  
    @Override  
    public String getTextInfo(Notice data) {
        return data.getContent();
    }  
  
    /**  
     * 这里面的高度应该和你的xml里设置的高度一致  
     */  
    @Override  
    protected int getAdertisementHeight() {  
        return 24;
    }  
  
}  