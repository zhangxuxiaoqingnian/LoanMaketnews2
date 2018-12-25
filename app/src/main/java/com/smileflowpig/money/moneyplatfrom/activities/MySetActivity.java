package com.smileflowpig.money.moneyplatfrom.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.smileflowpig.money.factory.util.SPUtil;
import com.smileflowpig.money.moneyplatfrom.util.SelfDialog2;

import net.qiujuer.genius.ui.widget.Button;

import java.io.File;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.PresenterActivity;
import com.smileflowpig.money.factory.Constant;
import com.smileflowpig.money.factory.Factory;
import com.smileflowpig.money.factory.bean.MyiconBean;
import com.smileflowpig.money.factory.bean.MynameBean;
import com.smileflowpig.money.factory.netword.NetRequestUtils;
import com.smileflowpig.money.factory.presenter.account.ExistContract;
import com.smileflowpig.money.factory.presenter.account.ExistPresenter;
import com.umeng.analytics.MobclickAgent;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.smileflowpig.money.moneyplatfrom.util.FileUtil.getRealFilePathFromUri;

public class MySetActivity extends PresenterActivity<ExistContract.Presenter>
        implements ExistContract.View,View.OnClickListener{

    private Button exist;
    private ImageView back;
    private TextView setphone;
    private TextView setname;
    private CircleImageView icon;
    private RelativeLayout pass;
    private RelativeLayout upname;
    private RelativeLayout upicon;
    private LinearLayout main;
    //请求相机
    private static final int REQUEST_CAPTURE = 100;
    //请求相册
    private static final int REQUEST_PICK = 101;

    //请求访问外部存储
    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 103;
    //请求写入外部存储
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 104;
    private Bitmap bitMap;

    private static ClearableCookieJar cookieJar;
    private File tempFile;
    private String photofileName;
    @Override
    protected boolean isNeedNotch() {
        return true;
    }
    String permissionName = "android.permission.WRITE_EXTERNAL_STORAGE";
    private String imgUrl;


    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initview();
        exist.setOnClickListener(this);
        back.setOnClickListener(this);
        pass.setOnClickListener(this);
        upicon.setOnClickListener(this);
        upname.setOnClickListener(this);

        Intent intent = getIntent();
        String xiang = intent.getStringExtra("myicon");
        String name = intent.getStringExtra("myname");
        String phone = intent.getStringExtra("myphone");

        if(xiang!=null) {
            if (xiang.equals("")) {
                icon.setImageResource(R.mipmap.loginicon);
            } else {
                Glide.with(MySetActivity.this).load(xiang).into(icon);
            }
        }
        setname.setText(name);
        setphone.setText(phone);

    }

    static {
        cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(Factory.app()));
    }
    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_my_set;
    }

    public void initview(){

        exist = (Button) findViewById(R.id.my_exist_button);
        back = (ImageView) findViewById(R.id.back);
        icon = (CircleImageView) findViewById(R.id.myseticon);
        setname = (TextView) findViewById(R.id.setname);
        setphone = (TextView) findViewById(R.id.setphone);
        pass = (RelativeLayout) findViewById(R.id.updatapass);
        upicon = (RelativeLayout) findViewById(R.id.updataicon);
        upname = (RelativeLayout) findViewById(R.id.updataname);
        main = (LinearLayout) findViewById(R.id.setmain);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.my_exist_button:
                mPresenter.exist();
                SharedPreferences sp = getSharedPreferences("Deng",MODE_PRIVATE);
                sp.edit().clear().commit();
                break;
            case R.id.back:
                finish();
                break;
            case R.id.updatapass:
                UpdateActivity.show(this);
                break;
            case R.id.updataicon:
                getpopuwindow();
                break;
            case R.id.updataname:
                //修改昵称
                final SelfDialog2 selfDialogt=new SelfDialog2(MySetActivity.this);
                selfDialogt.setTitle("修改昵称");
                selfDialogt.setYesOnclickListener("确定", new SelfDialog2.onYesOnclickListener() {
                    @Override
                    public void onYesClick(String text) {
                        if(text.equals("")){
                            Toast.makeText(MySetActivity.this,"昵称不能为空",Toast.LENGTH_SHORT).show();
                        }else {
                            //请求修改昵称接口
                            getnamedata(text);
                            selfDialogt.dismiss();
                        }

                    }
                });
                selfDialogt.setNoOnclickListener("取消", new SelfDialog2.onNoOnclickListener() {
                    @Override
                    public void onNoClick() {

                        selfDialogt.dismiss();
                    }
                });
                selfDialogt.show();
                break;
        }
    }

    public void getnamedata(String text){

        Observable<MynameBean> objectObservable = new NetRequestUtils().bucuo().getbaseretrofit().getname(text).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        objectObservable.subscribe(new Observer<MynameBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MynameBean o) {

                setname.setText(o.rst.nick);
                Toast.makeText(MySetActivity.this,"修改成功",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void getpopuwindow(){

        View inflate = LayoutInflater.from(MySetActivity.this).inflate(R.layout.phone_layout, null, false);
        final PopupWindow popupWindow=new PopupWindow(inflate, WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setAnimationStyle(R.style.pop_anim);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(main, Gravity.BOTTOM,0,0);
        light(0.8f);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                light(1.0f);
            }
        });

        TextView bt1 = (TextView) inflate.findViewById(R.id.bt1);
        TextView bt2 = (TextView) inflate.findViewById(R.id.bt2);
        TextView bt3 = (TextView) inflate.findViewById(R.id.bt3);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //权限判断
                if (ContextCompat.checkSelfPermission(MySetActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请WRITE_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(MySetActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            WRITE_EXTERNAL_STORAGE_REQUEST_CODE);

                }
                    gotoCamera();
                   popupWindow.dismiss();
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //权限判断
                if (ContextCompat.checkSelfPermission(MySetActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请READ_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(MySetActivity.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                            READ_EXTERNAL_STORAGE_REQUEST_CODE);
                }
                gotoPhoto();
                popupWindow.dismiss();
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupWindow.dismiss();
            }
        });

    }

    /**
     * 外部存储权限申请返回
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted

            }
        } else if (requestCode == READ_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
            }
        }
    }


    /**
     * 跳转到照相机
     */
    private void gotoCamera() {
        Log.d("evan", "*****************打开相机********************");

        if(cameraIsCanUse()){
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            photofileName = "IMG_" + new Date() + ".jpg";
            //必须使用已经存在的文件夹tempWhy
            imgUrl = Environment.getExternalStorageDirectory() + File.separator + "tempWhy"+ File.separator + photofileName;
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(imgUrl)));
            startActivityForResult(intent, REQUEST_CAPTURE);
        }else {
            Toast.makeText(MySetActivity.this,"请去设置中开启相机权限",Toast.LENGTH_SHORT).show();

        }

    }

    public boolean cameraIsCanUse() {
        boolean isCanUse = true;
        Camera mCamera = null;
        try {
            mCamera = Camera.open();
            Camera.Parameters mParameters = mCamera.getParameters(); //针对魅族手机
            mCamera.setParameters(mParameters);
        } catch (Exception e) {
            isCanUse = false;
        }

        if (mCamera != null) {
            try {
                mCamera.release();
            } catch (Exception e) {
                e.printStackTrace();
                return isCanUse;
            }
        }
        return isCanUse;
    }
    //判断是否有某个权限
    public static boolean hasPermission(Context context, String permission){
        int perm = context.checkCallingOrSelfPermission(permission);
        return perm == PackageManager.PERMISSION_GRANTED;
    }


    /**
     * 跳转到相册
     */
    private void gotoPhoto() {
        Log.d("evan", "*****************打开图库********************");
        if(hasPermission(MySetActivity.this,permissionName)){
            //跳转到调用系统图库
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(Intent.createChooser(intent, "请选择图片"), REQUEST_PICK);
        }else {
            Toast.makeText(MySetActivity.this,"请去设置中开启存储权限",Toast.LENGTH_SHORT).show();
        }

    }
    public void light(float t){
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = t;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }


    @Override
    public void ExistSuccess() {

//        SPUtil.clear(this);
//        com.smileflowpig.money.factory.util.SPUtil.clear(this);
     SPUtil.putAndApply(this, Constant.UserInfo.ISEXITE, true);
        SPUtil.putAndApply(this, Constant.UserInfo.SESSIONID, "");
        finish();
    }

    @Override
    protected ExistContract.Presenter initPresenter() {
        return new ExistPresenter(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CAPTURE: //调用系统相机返回
                if (resultCode == RESULT_OK) {

                    File file = new File(imgUrl);
                    if(file.exists()){
                        Bitmap bm = BitmapFactory.decodeFile(imgUrl);
                        icon.setImageBitmap(bm);
                    }

                    shangchuan(file);


                }
                break;
            case REQUEST_PICK:  //调用系统相册返回
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    if (uri == null) {
                        return;
                    }
                    String cropImagePath = getRealFilePathFromUri(getApplicationContext(), uri);
                    bitMap = BitmapFactory.decodeFile(cropImagePath);

                    icon.setImageBitmap(bitMap);

                    //可以上传服务器了
                    shangchuan(new File(cropImagePath));
                }
                break;
    }}


    private void shangchuan(File file) {

        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part avatar = MultipartBody.Part.createFormData("avatar", file.getName(), requestFile);
        Observable<MyiconBean> objectObservable = new NetRequestUtils().bucuo().getbaseretrofit().geticon(avatar).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        objectObservable.subscribe(new Observer<MyiconBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MyiconBean o) {

//                String err = o.err;
//                String avatar_url = o.rst.avatar_url;
//                System.out.println("成功了"+err+"网址"+avatar_url);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });



  }


}
