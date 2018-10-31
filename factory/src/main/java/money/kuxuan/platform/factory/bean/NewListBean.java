package money.kuxuan.platform.factory.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 小狼 on 2018/9/19.
 */

public class NewListBean {


    /**
     * rst : [{"id":119,"user_id":2716360,"platform":"母亲","periods":5,"amount":"416.00","monthly_rate":"0.80","first_time":"2018-09-01 00:00:00","status":"0","total_amount":"2000.00","surplus":"5","detail":[{"id":1368,"status":0,"periods":"1/5","amount":"416.00","date":"2018-08-01 00:00:00","repayment_id":119},{"id":1369,"status":0,"periods":"2/5","amount":"416.00","date":"2018-09-01 00:00:00","repayment_id":119},{"id":1370,"status":0,"periods":"3/5","amount":"416.00","date":"2018-10-01 00:00:00","repayment_id":119},{"id":1371,"status":0,"periods":"4/5","amount":"416.00","date":"2018-11-01 00:00:00","repayment_id":119},{"id":1372,"status":0,"periods":"5/5","amount":"416.00","date":"2018-12-01 00:00:00","repayment_id":119}]}]
     * errno : 0
     * err : 成功
     * timestamp : 1537348436
     */

    public String errno;
    public String err;
    public String timestamp;
    public List<RstBean> rst;

    public static class RstBean {
        /**
         * id : 119
         * user_id : 2716360
         * platform : 母亲
         * periods : 5
         * amount : 416.00
         * monthly_rate : 0.80
         * first_time : 2018-09-01 00:00:00
         * status : 0
         * total_amount : 2000.00
         * surplus : 5
         * detail : [{"id":1368,"status":0,"periods":"1/5","amount":"416.00","date":"2018-08-01 00:00:00","repayment_id":119},{"id":1369,"status":0,"periods":"2/5","amount":"416.00","date":"2018-09-01 00:00:00","repayment_id":119},{"id":1370,"status":0,"periods":"3/5","amount":"416.00","date":"2018-10-01 00:00:00","repayment_id":119},{"id":1371,"status":0,"periods":"4/5","amount":"416.00","date":"2018-11-01 00:00:00","repayment_id":119},{"id":1372,"status":0,"periods":"5/5","amount":"416.00","date":"2018-12-01 00:00:00","repayment_id":119}]
         */

        public int id;
        public int user_id;
        public String platform;
        public int periods;
        public String amount;
        public String monthly_rate;
        public String first_time;
        public String status;
        public String total_amount;
        public String surplus;
        public List<DetailBean> detail;

        public static class DetailBean implements Serializable{
            /**
             * id : 1368
             * status : 0
             * periods : 1/5
             * amount : 416.00
             * date : 2018-08-01 00:00:00
             * repayment_id : 119
             */

            public int id;
            public int status;
            public String periods;
            public String amount;
            public String date;
            public int repayment_id;

            public DetailBean(int id, int status, String periods, String amount, String date, int repayment_id) {
                this.id = id;
                this.status = status;
                this.periods = periods;
                this.amount = amount;
                this.date = date;
                this.repayment_id = repayment_id;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getPeriods() {
                return periods;
            }

            public void setPeriods(String periods) {
                this.periods = periods;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public int getRepayment_id() {
                return repayment_id;
            }

            public void setRepayment_id(int repayment_id) {
                this.repayment_id = repayment_id;
            }
        }
    }
}
