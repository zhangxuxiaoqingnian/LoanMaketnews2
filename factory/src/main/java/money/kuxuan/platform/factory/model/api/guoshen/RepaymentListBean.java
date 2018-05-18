package money.kuxuan.platform.factory.model.api.guoshen;

import java.util.List;

/**
 * Created by Allence on 2018/5/9 0009.
 */

public class RepaymentListBean {

    int id;
    String date;
    int user_id;
    String platform;
    int periods;
    String amount;
    String monthly_rate;
    String first_time;
    int status;
    String surplus;
    List<Detail> detail;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public int getPeriods() {
        return periods;
    }

    public void setPeriods(int periods) {
        this.periods = periods;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getMonthly_rate() {
        return monthly_rate;
    }

    public void setMonthly_rate(String monthly_rate) {
        this.monthly_rate = monthly_rate;
    }

    public String getFirst_time() {
        return first_time;
    }

    public void setFirst_time(String first_time) {
        this.first_time = first_time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSurplus() {
        return surplus;
    }

    public void setSurplus(String surplus) {
        this.surplus = surplus;
    }

    public List<Detail> getDetail() {
        return detail;
    }

    public void setDetail(List<Detail> detail) {
        this.detail = detail;
    }

    public static class Detail{


        int id;
        int status;
        String periods;
        String amount;
        String date;
        int repayment_id;


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
