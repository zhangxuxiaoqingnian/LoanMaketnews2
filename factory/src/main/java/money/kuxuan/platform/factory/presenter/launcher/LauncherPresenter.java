package money.kuxuan.platform.factory.presenter.launcher;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.StringRes;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.target.Target;

import java.io.File;

import money.kuxuan.platform.common.factory.data.DataSource;
import money.kuxuan.platform.common.factory.presenter.BasePresenter;
import money.kuxuan.platform.factory.Factory;
import money.kuxuan.platform.factory.R;
import money.kuxuan.platform.factory.data.helper.LauncherHelper;
import money.kuxuan.platform.factory.model.api.RspModel;
import money.kuxuan.platform.factory.model.api.launcher.LaunchRspModel;
import money.kuxuan.platform.factory.model.api.launcher.RspAdModel;
import money.kuxuan.platform.factory.net.Network;
import money.kuxuan.platform.factory.presenter.home.HomePresenter;




/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class LauncherPresenter extends BasePresenter<LauncherContract.View>
        implements LauncherContract.Presenter{


    public static final String NEW_PICTURE = "NEW_PICTURE";

    public static final String NEW_DIALOG = "NEW_DIALOG";

    public static final String FILEPATH = "FILEPATH";
    public static final String SKIPTYPE = "SKIPTYPE";
    public static final String LINK = "LINK";
    public static final String PRODUCTID = "PRODUCTID";


    private static final String CHANNEL = "CHANNEL";


    private static final String CHANNELOKORNOTOK = "CHANNELOKORNOTOK";

    private static final String TIME = "TIME";
    private static final String FILE_END = ".apk"; //文件后缀名

    private String mPatchFileDir; //patch要保存的文件夹
    private String mFilePtch; //patch文件保存路径


    private AdPictureListener adPictureListener;

    private static final String KEY_IMAGE_FILE = "KEY_IMAGE_FILE";


    private static final String TAG = "LauncherPresenter";
    public LauncherPresenter(LauncherContract.View view,AdPictureListener adPictureListener) {
        super(view);
        this.adPictureListener = adPictureListener;
    }

    @Override
    public void start() {
        // 获取弹框图
        LauncherHelper.getshowDialog(new DataSource.Callback<RspModel>() {

            @Override
            public void onDataNotAvailable(@StringRes int strRes) {

            }

            @Override
            public void onDataLoaded(RspModel rspModel) {
                RspAdModel rspAdModel = (RspAdModel) rspModel.getRst();
                downDialog(rspAdModel);

            }
        });

        LauncherHelper.getChannel(Network.channelId, new DataSource.Callback<LaunchRspModel>() {
            @Override
            public void onDataLoaded(LaunchRspModel launcherModel) {

                save(launcherModel.getAndroid(),launcherModel.getVersion());
//                Intent intent = new Intent(Factory.app(),DownService.class);
//                intent.putExtra("url",launcherModel.getUrl());
//                Factory.app().startService(intent);



            }

            @Override
            public void onDataNotAvailable(@StringRes int strRes) {
                if(getView()!=null)
                  getView().showError(R.string.data_network_error);
            }


        });

        LauncherHelper.getAdPicture(new DataSource.Callback<RspModel>() {

            @Override
            public void onDataNotAvailable(@StringRes int strRes) {

            }

            @Override
            public void onDataLoaded(RspModel rspModel) {
                RspAdModel rspAdModel = (RspAdModel) rspModel.getRst();
                Log.e(TAG, rspAdModel.getData().getImage_url());


                if(getView()!=null){
                    getView().setImage(rspAdModel.getData().getImage_url());
                }
                downloadImage(rspAdModel);

            }
        });


    }



    /**
     * 开启线程下载广告图片
     * @param rspAdModel
     */
    public void downloadImage(final RspAdModel rspAdModel) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String url = rspAdModel.getData().getImage_url();
                    final Context context = Factory.app();
                    FutureTarget<File> target = Glide.with(context)
                            .load(url)
                            .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
                    final File imageFile = target.get();
                    if(adPictureListener!=null){
                        adPictureListener.loadTry(imageFile.getPath(),rspAdModel);
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        }).start();
    }

    /**
     * 开启线程下载弹框图片
     * @param rspAdModel
     */
    public void downDialog(final RspAdModel rspAdModel) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String url = rspAdModel.getData().getImage_url();
                    final Context context = Factory.app();
                    FutureTarget<File> target = Glide.with(context)
                            .load(url)
                            .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
                    final File imageFile = target.get();
                    save(imageFile.getPath(),rspAdModel);
                    Log.e(TAG,"弹框图的地址是"+imageFile.getPath());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void getAdPicture() {

    }

    @Override
    public void getshowDialog() {

    }

    @Override
    public void sendImei(String imei) {

    }

    public interface AdPictureListener{
       void loadTry(String filePath,RspAdModel rspAdModel);
   }


    /**
     * 根据版本号存储当前版本状态
     * @param channelOk 线上channel 状态
     * @param version  本地version
     */

    private void save(String channelOk,String version){
        int versionLocal = Integer.parseInt(HomePresenter.getVersionCode(Factory.app()));
        int versionNet;
                try{
                    versionNet =   Integer.parseInt(version);
                }catch (Exception e){
                    versionNet = 0;
                }

        if(versionLocal<versionNet){
            SharedPreferences sp = Factory.app().getSharedPreferences(CHANNEL,
                    Context.MODE_PRIVATE);
            sp.edit()
                    .putString(CHANNELOKORNOTOK,"0")
                    .apply();
        }else{
            SharedPreferences sp = Factory.app().getSharedPreferences(CHANNEL,
                    Context.MODE_PRIVATE);
            sp.edit()
                    .putString(CHANNELOKORNOTOK,channelOk)
                    .apply();
        }

    }

    /**
     * 存储弹框图
     * @param file   地址
     * @param rspModel  弹框图对象
     */
    private void save(String file,RspAdModel rspModel){

        SharedPreferences sp = Factory.app().getSharedPreferences(NEW_DIALOG,
                Context.MODE_PRIVATE);
        sp.edit()
                .putString(FILEPATH,file)
                .putString(SKIPTYPE,rspModel.getData().getSkip_type())
                .putString(LINK,rspModel.getData().getLink())
                .putString(PRODUCTID,rspModel.getData().getProduct_id())
                .apply();
    }






}
