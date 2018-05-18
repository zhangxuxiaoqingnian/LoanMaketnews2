package money.kuxuan.platform.factory.model.api.account;


import money.kuxuan.platform.factory.model.db.User;

/**
 * @author qiujuer Email:qiujuer@live.cn
 * @version 1.0.0
 */
public class AccountRspModel {
    // 用户基本信息
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
