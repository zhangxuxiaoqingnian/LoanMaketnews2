package money.kuxuan.platform.factory.presenter.statehome;

import android.support.annotation.StringRes;

import java.util.List;

import money.kuxuan.platform.common.factory.data.DataSource;
import money.kuxuan.platform.common.factory.presenter.BasePresenter;
import money.kuxuan.platform.factory.data.helper.ExaimeHelper;
import money.kuxuan.platform.factory.model.api.examine.Banner;
import money.kuxuan.platform.factory.model.api.examine.BannerModel;
import money.kuxuan.platform.factory.model.api.examine.Examine1;
import money.kuxuan.platform.factory.model.api.examine.RspExamHomeModel;
import money.kuxuan.platform.factory.model.api.examine.TabListParamter;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */

public class ExaminePresenter extends BasePresenter<ExamineContract.View>
        implements ExamineContract.Presenter {

    private Banner mbanner;
    private int page = 1;
    private boolean hasNext = false;
    private String pageid = "package_3";
    private String classification_id="推荐";
    private List<String> tabList;
    public ExaminePresenter(ExamineContract.View view,String pageid) {
        super(view);

        this.pageid = pageid;
    }
    public void hasNet(boolean flag){
        hasNext = flag;
    }
    public void setPage(int page){
        this.page = page;
    }

    @Override
    public void refreshData() {
        if(hasNext==false)
            return;
        if(classification_id==null){
            return;
        }
       Examine1 model = new Examine1(pageid,classification_id,page);

        ExaimeHelper.refreshExamine(model, new DataSource.Callback<RspExamHomeModel>() {

            @Override
            public void onDataNotAvailable(@StringRes int strRes) {
                if(getView()!=null)
                getView().showError(strRes);
            }

            @Override
            public void onDataLoaded(RspExamHomeModel rspExamHomeModel) {
                if(getView()!=null)
                getView().getRefreshData(rspExamHomeModel);
                if(rspExamHomeModel.getListdata().getPageinfo().isHasNext()){
                    hasNext =true;
                    page++;
                }else{
                    hasNext=false;
                    getView().NoData();
                }
            }
        });
    }



    @Override
    public void start() {
        super.start();


        ExaimeHelper.getBanner(new DataSource.Callback<BannerModel>(){
            @Override
            public void onDataNotAvailable(@StringRes int strRes) {
                getView().showError(strRes);
            }

            @Override
            public void onDataLoaded(BannerModel banner) {
                if(getView()!=null){
                    mbanner = banner.getBanner();
                    getListData();
                }
            }
        });


//        Examine examine = new Examine(pageid,page);
//        ExaimeHelper.refreshExamine(examine, new DataSource.Callback<RspExamHomeModel>() {
//
//            @Override
//            public void onDataNotAvailable(@StringRes int strRes) {
//                getView().showError(strRes);
//            }
//
//            @Override
//            public void onDataLoaded(RspExamHomeModel rspExamHomeModel) {
//                if (getView()!=null){
//                    getView().getHomeData(rspExamHomeModel);
//                    if(rspExamHomeModel.getListdata().getPageinfo().isHasNext()){
//                        hasNext = true;
//                        page++;
//                    }else{
//                        hasNext = false;
//                        getView().NoData();
//
//                    }
//                }
//
//            }
//        });
    }


    private void getListData(){

        TabListParamter tabListParamter = new TabListParamter(pageid);
        ExaimeHelper.getTabList(tabListParamter,new DataSource.Callback<List<String>>(){
            @Override
            public void onDataNotAvailable(@StringRes int strRes) {
                getView().showError(strRes);

            }

            @Override
            public void onDataLoaded(List<String> strings) {
                if(getView()!=null){
                    tabList = strings;
                    getView().tabList(mbanner,tabList);
                    getInfoList(0);
                }
            }
        });

    };


    public void getInfoList(int index){

        if(tabList==null){
            return;
        }

        classification_id = tabList.get(index);
        page = 1;
        Examine1 examine = new Examine1(pageid,classification_id,page);
                ExaimeHelper.refreshExamine(examine, new DataSource.Callback<RspExamHomeModel>() {

                    @Override
                    public void onDataNotAvailable(@StringRes int strRes) {
                        getView().showError(strRes);
                    }

                    @Override
                    public void onDataLoaded(RspExamHomeModel rspExamHomeModel) {
                        if (getView()!=null){
                            getView().getHomeData(rspExamHomeModel);
                            if(rspExamHomeModel.getListdata().getPageinfo().isHasNext()){
                                hasNext = true;
                                page++;
                            }else{
                                hasNext = false;
                                getView().NoData();

                            }
                        }

                    }
                });
    }

}
