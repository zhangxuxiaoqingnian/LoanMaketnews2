package money.kuxuan.platform.factory.bean;

/**
 * Created by 小狼 on 2018/10/26.
 */

public class MemoShowBean {


    /**
     * rst : {"id":"22","user_id":"1560","platform_id":null,"how_to_use":null,"how_much":null,"how_long":null,"due_date":null,"gross_interest":null,"create_time":"1540544268","platform_name":null,"platform_icon":"http://182.92.118.1"}
     * errno : 0
     * err : 成功
     * timestamp : 1540549815
     */

    public RstBean rst;
    public String errno;
    public String err;
    public String timestamp;

    public static class RstBean {
        /**
         * id : 22
         * user_id : 1560
         * platform_id : null
         * how_to_use : null
         * how_much : null
         * how_long : null
         * due_date : null
         * gross_interest : null
         * create_time : 1540544268
         * platform_name : null
         * platform_icon : http://182.92.118.1
         */

        public String id;
        public String user_id;
        public String platform_id;
        public String how_to_use;
        public String how_much;
        public String how_long;
        public String due_date;
        public String gross_interest;
        public String create_time;
        public String platform_name;
        public String platform_icon;
    }
}
