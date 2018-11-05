package money.kuxuan.platform.factory.model.api.bill;

public class AddBillModel {

    private String platform_id;
    private String amount;
    private String due_date;
    private String remind_time;

    public AddBillModel(String platform_id, String amount, String due_date, String remind_time) {
        this.platform_id = platform_id;
        this.amount = amount;
        this.due_date = due_date;
        this.remind_time = remind_time;
    }
}
