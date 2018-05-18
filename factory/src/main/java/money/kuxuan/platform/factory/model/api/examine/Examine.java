package money.kuxuan.platform.factory.model.api.examine;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */

public class Examine {
    private String package_id;
    private int page;

    public Examine(String package_id,int page) {
        this.package_id = package_id;
        this.page = page;
    }
}
