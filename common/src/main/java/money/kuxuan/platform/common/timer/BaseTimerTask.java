package money.kuxuan.platform.common.timer;

import java.util.TimerTask;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class BaseTimerTask extends TimerTask{
    private ITimerListener mITimerListener = null;

    public BaseTimerTask(ITimerListener mITimerListener) {
        this.mITimerListener = mITimerListener;
    }

    @Override
    public void run() {
        if(mITimerListener!=null){
            mITimerListener.onTimer();
        }
    }
}
