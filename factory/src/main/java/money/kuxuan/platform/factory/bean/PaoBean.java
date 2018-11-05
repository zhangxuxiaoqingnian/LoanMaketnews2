package money.kuxuan.platform.factory.bean;

/**
 * Created by 小狼 on 2018/11/4.
 */

public class PaoBean {


    /**
     * rst : {"content":"新口子","icon":"http://182.92.118.1/Uploads/20170526/1495793017309932.png"}
     * errno : 0
     * err : 成功
     * timestamp : 1541299962
     */

    public RstBean rst;
    public String errno;
    public String err;
    public String timestamp;

    public static class RstBean {
        /**
         * content : 新口子
         * icon : http://182.92.118.1/Uploads/20170526/1495793017309932.png
         */

        public String content;
        public String icon;
    }
}
