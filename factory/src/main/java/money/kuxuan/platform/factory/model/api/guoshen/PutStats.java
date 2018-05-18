package money.kuxuan.platform.factory.model.api.guoshen;

/**
 * Created by Allence on 2018/5/10 0010.
 */

public class PutStats {

    int id;
    int status;

    public PutStats(int id, int status) {
        this.id = id;
        this.status = status;
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
}
