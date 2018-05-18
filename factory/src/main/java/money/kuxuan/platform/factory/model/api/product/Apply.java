package money.kuxuan.platform.factory.model.api.product;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class Apply {
    private String product_id;
    private String periods;
    private String money;
    private String product_apply_id;
    private String status;
    private String skip_type;



    public Apply(String product_id, String periods, String money) {
        this.product_id = product_id;
        this.periods = periods;
        this.money = money;
    }

    public Apply(String product_apply_id, String status, String skip_type,String product_id) {
        this.product_apply_id = product_apply_id;
        this.status = status;
        this.skip_type = skip_type;
        this.product_id  = product_id;
    }
}
