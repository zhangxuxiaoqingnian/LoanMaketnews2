package money.kuxuan.platform.factory.model.api.examine;

import java.util.List;

/**
 * Created by 小狼 on 2018/8/29.
 */

public class HomeBean {


    /**
     * banner : {"id":"330","thumbnail":"http://img.henhaojie.com/Uploads/20180827/1535346578589723.jpg"}
     * ios_banner : [{"id":"109","thumbnail":"http://bwadmin.quyaqu.com/Uploads/20180420/1524193182185120.jpg","url":"http://m.henhaojie.com/details.html?id=109"},{"id":"100","thumbnail":"http://bwadmin.quyaqu.com/Uploads/20171120/1511149233450449.jpg","url":"http://m.henhaojie.com/details.html?id=100"}]
     * ios_teach_banner : [{"id":"229","thumbnail":"http://ldaso.quyaqu.com/Uploads/20171101/1509509423637388.png","url":"http://m.henhaojie.com/details.html?id=229"},{"id":"228","thumbnail":"http://ldaso.quyaqu.com/Uploads/20171101/1509509297408260.png","url":"http://m.henhaojie.com/details.html?id=228"},{"id":"227","thumbnail":"http://ldaso.quyaqu.com/Uploads/20171101/1509509211944025.png","url":"http://m.henhaojie.com/details.html?id=227"},{"id":"226","thumbnail":"http://ldaso.quyaqu.com/Uploads/20171101/1509509139849168.png","url":"http://m.henhaojie.com/details.html?id=226"}]
     * listdata : {"data":[{"id":"334","title":"这4种信用卡建议注销，越早越好","sub_title":"","page_views":"8379","source":"信用卡","create_time":"2018-08-27","thumbnail":"http://img.henhaojie.com/Uploads/20180827/1535347405609911.jpg"},{"id":"333","title":"假如有一天你突然还不起房贷了，该怎么办","sub_title":"","page_views":"15825","source":"房贷","create_time":"2018-08-27","thumbnail":"http://img.henhaojie.com/Uploads/20180827/1535346857793004.jpg"},{"id":"331","title":"个税法草案将迎二审 起征点能否超5000元引期待","sub_title":"","page_views":"10635","source":"个税","create_time":"2018-08-27","thumbnail":"http://img.henhaojie.com/Uploads/20180827/1535346758588935.jpg"},{"id":"329","title":"\u201c鸡蛋不要放在一个篮子里\u201d的下半句，你知道吗","sub_title":"","page_views":"3546","source":"理财资讯","create_time":"2018-08-27","thumbnail":"http://img.henhaojie.com/Uploads/20180827/1535346303800152.jpg"},{"id":"328","title":"如何查询自己名下的所有银行卡","sub_title":"","page_views":"7384","source":"银行卡","create_time":"2018-08-27","thumbnail":"http://img.henhaojie.com/Uploads/20180827/1535346158646959.jpg"},{"id":"327","title":"犹太人的两种神思维","sub_title":"","page_views":"3434","source":"理财资讯","create_time":"2018-08-27","thumbnail":"http://img.henhaojie.com/Uploads/20180827/1535337750961626.jpg"},{"id":"326","title":"滴滴最忌惮的对手：短短几年拿下6亿用户，目前估值已超过小米","sub_title":"","page_views":"9946","source":"互联网财经","create_time":"2018-08-27","thumbnail":"http://img.henhaojie.com/Uploads/20180827/1535337613238512.jpg"}],"pageinfo":{"totalNum":"7","page":"1","maxPage":"1","hasNext":false,"hasPrev":false,"num":"7"}}
     */

    public BannerBean banner;
    public ListdataBean listdata;
    public List<IosBannerBean> ios_banner;
    public List<IosTeachBannerBean> ios_teach_banner;

    public static class BannerBean {
        /**
         * id : 330
         * thumbnail : http://img.henhaojie.com/Uploads/20180827/1535346578589723.jpg
         */

        public String id;
        public String thumbnail;
    }

    public static class ListdataBean {
        /**
         * data : [{"id":"334","title":"这4种信用卡建议注销，越早越好","sub_title":"","page_views":"8379","source":"信用卡","create_time":"2018-08-27","thumbnail":"http://img.henhaojie.com/Uploads/20180827/1535347405609911.jpg"},{"id":"333","title":"假如有一天你突然还不起房贷了，该怎么办","sub_title":"","page_views":"15825","source":"房贷","create_time":"2018-08-27","thumbnail":"http://img.henhaojie.com/Uploads/20180827/1535346857793004.jpg"},{"id":"331","title":"个税法草案将迎二审 起征点能否超5000元引期待","sub_title":"","page_views":"10635","source":"个税","create_time":"2018-08-27","thumbnail":"http://img.henhaojie.com/Uploads/20180827/1535346758588935.jpg"},{"id":"329","title":"\u201c鸡蛋不要放在一个篮子里\u201d的下半句，你知道吗","sub_title":"","page_views":"3546","source":"理财资讯","create_time":"2018-08-27","thumbnail":"http://img.henhaojie.com/Uploads/20180827/1535346303800152.jpg"},{"id":"328","title":"如何查询自己名下的所有银行卡","sub_title":"","page_views":"7384","source":"银行卡","create_time":"2018-08-27","thumbnail":"http://img.henhaojie.com/Uploads/20180827/1535346158646959.jpg"},{"id":"327","title":"犹太人的两种神思维","sub_title":"","page_views":"3434","source":"理财资讯","create_time":"2018-08-27","thumbnail":"http://img.henhaojie.com/Uploads/20180827/1535337750961626.jpg"},{"id":"326","title":"滴滴最忌惮的对手：短短几年拿下6亿用户，目前估值已超过小米","sub_title":"","page_views":"9946","source":"互联网财经","create_time":"2018-08-27","thumbnail":"http://img.henhaojie.com/Uploads/20180827/1535337613238512.jpg"}]
         * pageinfo : {"totalNum":"7","page":"1","maxPage":"1","hasNext":false,"hasPrev":false,"num":"7"}
         */

        public PageinfoBean pageinfo;
        public List<DataBean> data;

        public static class PageinfoBean {
            /**
             * totalNum : 7
             * page : 1
             * maxPage : 1
             * hasNext : false
             * hasPrev : false
             * num : 7
             */

            public String totalNum;
            public String page;
            public String maxPage;
            public boolean hasNext;
            public boolean hasPrev;
            public String num;
        }

        public static class DataBean {
            /**
             * id : 334
             * title : 这4种信用卡建议注销，越早越好
             * sub_title :
             * page_views : 8379
             * source : 信用卡
             * create_time : 2018-08-27
             * thumbnail : http://img.henhaojie.com/Uploads/20180827/1535347405609911.jpg
             */

            public String id;
            public String title;
            public String sub_title;
            public String page_views;
            public String source;
            public String create_time;
            public String thumbnail;
        }
    }

    public static class IosBannerBean {
        /**
         * id : 109
         * thumbnail : http://bwadmin.quyaqu.com/Uploads/20180420/1524193182185120.jpg
         * url : http://m.henhaojie.com/details.html?id=109
         */

        public String id;
        public String thumbnail;
        public String url;
    }

    public static class IosTeachBannerBean {
        /**
         * id : 229
         * thumbnail : http://ldaso.quyaqu.com/Uploads/20171101/1509509423637388.png
         * url : http://m.henhaojie.com/details.html?id=229
         */

        public String id;
        public String thumbnail;
        public String url;
    }

    public BannerBean getBanner() {
        return banner;
    }
}
