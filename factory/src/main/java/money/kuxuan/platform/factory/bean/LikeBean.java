package money.kuxuan.platform.factory.bean;

import java.util.List;

/**
 * Created by 小狼 on 2018/10/25.
 */

public class LikeBean {


    /**
     * rst : {"data":[{"id":"132","name":"快贷","icon":"http://182.92.118.1/Uploads/20170321/1490098018504057.png"},{"id":"135","name":"花无忧","icon":"http://182.92.118.1/Uploads/20180510/1525931545955705.png"},{"id":"133","name":"卡卡贷","icon":"http://182.92.118.1/Uploads/20170428/1493353112302349.png"},{"id":"389","name":"立即贷","icon":"http://182.92.118.1/Uploads/20180831/1535698284608498.png"},{"id":"401","name":"讯秒","icon":"http://182.92.118.1/Uploads/20180831/1535698613451129.png"},{"id":"122","name":"快贷","icon":"http://182.92.118.1/Uploads/20170720/1500539515283654.jpg"},{"id":"111","name":"钱多金服信贷家-大额贷","icon":"http://182.92.118.1/Uploads/20170321/1490093765607781.png"},{"id":"151","name":"金牛贷","icon":"http://182.92.118.1/Uploads/20180831/1535698409438346.jpg"},{"id":"336","name":"盈盈有钱","icon":"http://182.92.118.1/Uploads/20180831/1535698494751538.jpg"},{"id":"387","name":"备胎信用","icon":"http://182.92.118.1/Uploads/20180831/1535698791652630.png"}]}
     * errno : 0
     * err : 成功
     * timestamp : 1540462658
     */

    public RstBean rst;
    public String errno;
    public String err;
    public String timestamp;

    public static class RstBean {
        public List<DataBean> data;

        public static class DataBean {
            /**
             * id : 132
             * name : 快贷
             * icon : http://182.92.118.1/Uploads/20170321/1490098018504057.png
             */

            public String id;
            public String name;
            public String icon;
        }
    }
}
