package money.kuxuan.platform.factory.bean;

import java.util.List;

/**
 * Created by 小狼 on 2018/11/4.
 */

public class MessTypeBean {


    /**
     * rst : [{"id":0,"name":"全部"},{"id":1,"name":"贷款指导"},{"id":2,"name":"信用卡"},{"id":3,"name":"口子推荐"}]
     * errno : 0
     * err : 成功
     * timestamp : 1540970350
     */

    public String errno;
    public String err;
    public String timestamp;
    public List<RstBean> rst;

    public static class RstBean {
        /**
         * id : 0
         * name : 全部
         */

        public int id;
        public String name;
    }
}
