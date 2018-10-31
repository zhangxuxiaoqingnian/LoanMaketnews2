package money.kuxuan.platform.factory.model.api.Bean;

/**
 * Created by 小狼 on 2018/9/6.
 */

public class HomeBase {


    /**
     * rst : {"data":{"link":"https://s.click.taobao.com/5ZqN8Qw","image_url":"http://img.henhaojie.com/Uploads/20180601/1527844645690867.png","product_id":"0","skip_type":"0"}}
     * errno : 0
     * err : 成功
     * timestamp : 1536209229
     */

    public RstBean rst;
    public String errno;
    public String err;
    public String timestamp;

    public static class RstBean {
        /**
         * data : {"link":"https://s.click.taobao.com/5ZqN8Qw","image_url":"http://img.henhaojie.com/Uploads/20180601/1527844645690867.png","product_id":"0","skip_type":"0"}
         */

        public DataBean data;

        public static class DataBean {
            /**
             * link : https://s.click.taobao.com/5ZqN8Qw
             * image_url : http://img.henhaojie.com/Uploads/20180601/1527844645690867.png
             * product_id : 0
             * skip_type : 0
             */

            public String link;
            public String image_url;
            public String product_id;
            public String skip_type;
        }
    }
}
