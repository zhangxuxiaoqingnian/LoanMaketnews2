package com.kuxuan.push;

public class PushResultManager {


    private static PushResultManager mInstance;


    public PushResultListener mlis;

    private PushResultManager() {

    }

    public static PushResultManager getinstance() {
        synchronized (PushResultManager.class) {
            if (mInstance == null)
                mInstance = new PushResultManager();
        }
        return mInstance;
    }

    public void setPushResultListener(PushResultListener mlis) {
        this.mlis = mlis;
    }

    public void setPushData(){

    }


    /**
     * 把接收到的数据转为自己想要的
     */
    private void dealPushData(){

    }
}
