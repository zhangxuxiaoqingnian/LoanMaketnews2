package money.kuxuan.platform.factory.model.api.product;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class ChildModel {
    private String id;
    private int page;

    public ChildModel(String id, int page) {
        this.id = id;
        this.page = page;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
