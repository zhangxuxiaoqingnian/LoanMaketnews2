package money.kuxuan.platform.factory.bean;

/**
 * Created by 小狼 on 2018/10/15.
 */

public class MyiconBean {


    /**
     * rst : {"avatar_url":"http://invoicing.oss-cn-beijing.aliyuncs.com/avatar/2018-10-15/2018101512453792820271.jpg"}
     * errno : 0
     * err : 成功
     * timestamp : 1539578738
     */

    public RstBean rst;
    public String errno;
    public String err;
    public String timestamp;

    public static class RstBean {
        /**
         * avatar_url : http://invoicing.oss-cn-beijing.aliyuncs.com/avatar/2018-10-15/2018101512453792820271.jpg
         */

        public String avatar_url;
    }
}
