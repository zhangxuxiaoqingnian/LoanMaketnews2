package money.kuxuan.platform.factory.presenter.message;

import android.support.annotation.StringRes;

import money.kuxuan.platform.common.factory.data.DataSource;
import money.kuxuan.platform.common.factory.presenter.BasePresenter;
import money.kuxuan.platform.factory.data.helper.MessageHelper;
import money.kuxuan.platform.factory.model.api.message.MessageModel;
import money.kuxuan.platform.factory.model.api.message.MessageRspModel;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class MessagePresenter extends BasePresenter<MessageContract.View>
        implements MessageContract.Presenter {
    private  int messgaePage = 1;
    private boolean hasNext = false;
    public MessagePresenter(MessageContract.View view) {
        super(view);
    }

    @Override
    public void relodeMessage() {
        if(hasNext==false)
            return;
        MessageModel model = new MessageModel(messgaePage);
        MessageHelper.getMessageData(model, new DataSource.Callback<MessageRspModel>() {

            @Override
            public void onDataNotAvailable(@StringRes int strRes) {
                if(getView()!=null)
                getView().showError(strRes);
            }

            @Override
            public void onDataLoaded(MessageRspModel messageRspModel) {
                if(getView()!=null)
                getView().refresh(messageRspModel.getList());
                if(messageRspModel.getPageInfo().isHasNext()){
                    hasNext =true;
                    messgaePage++;
                }else{
                    hasNext=false;
                    getView().noData();
                }
            }
        });
    }

    @Override
    public void start() {
        super.start();
        MessageModel messageModel = new MessageModel(messgaePage);
        MessageHelper.getMessageData(messageModel, new DataSource.Callback<MessageRspModel>() {
            @Override
            public void onDataLoaded(MessageRspModel rspModel) {
                if(getView()!=null)
                 getView().setMessage(rspModel.getList());
                if(rspModel.getPageInfo().isHasNext()){
                    hasNext = true;
                    messgaePage++;
                }else{
                    hasNext = false;
                    getView().noData();

                }
            }

            @Override
            public void onDataNotAvailable(@StringRes int strRes) {

            }


        });
    }
}
