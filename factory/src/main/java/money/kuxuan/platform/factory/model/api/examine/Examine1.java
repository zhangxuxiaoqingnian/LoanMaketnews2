package money.kuxuan.platform.factory.model.api.examine;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */

public class Examine1 {
    private String package_id;
    private String classification_id;
    private int page;

    public Examine1(String package_id, String classification_id, int page) {
        this.package_id = package_id;
        this.classification_id = classification_id;
        this.page = page;
    }
}
