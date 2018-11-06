package com.smileflowpig.money.wxapi;

import android.util.Log;

import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.umeng.socialize.weixin.view.WXCallbackActivity;

/**
 * Created by 小狼 on 2018/11/6.
 */

public class WXEntryActivity extends WXCallbackActivity {


    @Override
    public void onResp(BaseResp resp) {
        super.onResp(resp);
        Log.e("resp",resp.toString());
    }
}
