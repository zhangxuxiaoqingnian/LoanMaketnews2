package money.kuxuan.platform.factory.model.api.guoshen;

/**
 * Created by Allence on 2018/5/10 0010.
 */

public class PopModel {

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
