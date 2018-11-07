package com.smileflowpig.money.moneyplatfrom.Bean;

import android.content.Context;
import android.webkit.JavascriptInterface;

import com.google.gson.Gson;
import com.smileflowpig.money.moneyplatfrom.activities.DetailActivity;

/**
 * Created by 小狼 on 2018/10/30.
 */

public class CaseClase {

    private Context context;

    public CaseClase(Context context) {
        this.context = context;
    }

    @JavascriptInterface
    public void skipNativeDetail(String json){

        Gson gson=new Gson();
        ItemBean itemBean = gson.fromJson(json, ItemBean.class);
        String productId = itemBean.productId;

        DetailActivity.show(context, productId,"notice",0,20);

    }
    @JavascriptInterface
    public void censucEventCount(String json){
        Gson gson=new Gson();
        UrlBean urlBean = gson.fromJson(json, UrlBean.class);

    }
}
