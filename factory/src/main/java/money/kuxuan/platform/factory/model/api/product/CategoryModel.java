package money.kuxuan.platform.factory.model.api.product;

import java.util.List;

import money.kuxuan.platform.factory.model.db.Category;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class CategoryModel {
    private List<Category> rst;
    private String errno;

    public List<Category> getRst() {
        return rst;
    }

    public void setRst(List<Category> rst) {
        this.rst = rst;
    }

    public String getErrno() {
        return errno;
    }

    public void setErrno(String errno) {
        this.errno = errno;
    }
}
