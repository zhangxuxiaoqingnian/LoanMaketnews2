package money.kuxuan.platform.factory.bean;

/**
 * Created by 小狼 on 2018/10/15.
 */

public class MynameBean {


    /**
     * rst : {"nick":"噢耶"}
     * errno : 0
     * err : 成功
     * timestamp : 1539588205
     */

    public RstBean rst;
    public String errno;
    public String err;
    public String timestamp;

    public static class RstBean {
        /**
         * nick : 噢耶
         */

        public String nick;
    }
}
