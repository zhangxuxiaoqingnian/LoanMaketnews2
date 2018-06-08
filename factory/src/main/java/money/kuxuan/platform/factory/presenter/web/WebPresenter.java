package money.kuxuan.platform.factory.presenter.web;

import money.kuxuan.platform.common.factory.presenter.BasePresenter;
import money.kuxuan.platform.factory.data.helper.WebHelper;
import money.kuxuan.platform.factory.model.api.web.WebModel;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class WebPresenter extends BasePresenter<WebContract.View>
        implements WebContract.Presenter{


    public WebPresenter(WebContract.View view) {
        super(view);
    }

    @Override
    public void pushData(String product_apply_id, String status, String skip_type, String product_id) {
        WebModel webModel = new WebModel(product_apply_id,status,skip_type,product_id);
        //WebHelper.pushData(webModel);

    }
}
