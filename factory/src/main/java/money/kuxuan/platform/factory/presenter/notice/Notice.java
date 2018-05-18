package money.kuxuan.platform.factory.presenter.notice;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class Notice {
    private String product_id;
    private String content;
    private String link;
    private String skip_type;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getSkip_type() {
        return skip_type;
    }

    public void setSkip_type(String skip_type) {
        this.skip_type = skip_type;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
