package money.kuxuan.platform.factory.bean;

import java.util.List;

/**
 * Created by 小狼 on 2018/10/9.
 */

public class MyHomeFragmentBean {


    /**
     * rst : {"banner":[{"id":"352","title":"极光大数据：线上跑腿抢单服务已成刚需，用户规模达513万","page_views":"24429","source":"抢单","create_time":"2018-09-29","thumbnail":"http://img.henhaojie.com/Uploads/20180929/1538207662725248.png"},{"id":"351","title":"当跑腿抢单成为一门生意","page_views":"34238","source":"跑腿","create_time":"2018-09-29","thumbnail":"http://img.henhaojie.com/Uploads/20180929/1538207581561993.png"}],"classification":["资讯"]}
     * errno : 0
     * err : 成功
     * timestamp : 1539065581
     */

    public RstBean rst;
    public String errno;
    public String err;
    public String timestamp;

    public static class RstBean {
        public List<BannerBean> banner;
        public List<String> classification;

        public static class BannerBean {
            /**
             * id : 352
             * title : 极光大数据：线上跑腿抢单服务已成刚需，用户规模达513万
             * page_views : 24429
             * source : 抢单
             * create_time : 2018-09-29
             * thumbnail : http://img.henhaojie.com/Uploads/20180929/1538207662725248.png
             */

            public String id;
            public String title;
            public String page_views;
            public String source;
            public String create_time;
            public String thumbnail;
        }
    }
}
