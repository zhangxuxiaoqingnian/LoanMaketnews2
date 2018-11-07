package com.smileflowpig.money.moneyplatfrom.activities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.smileflowpig.money.moneyplatfrom.util.SelfDialog2;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.PresenterActivity;
import com.smileflowpig.money.common.factory.presenter.BaseContract;
import com.smileflowpig.money.factory.netword.NetRequestUtils;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.smileflowpig.money.moneyplatfrom.util.FileUtil.getRealFilePathFromUri;

public class MySetTwoActivity extends PresenterActivity implements View.OnClickListener {

    @BindView(R.id.myset_back)
    ImageView back;
    @BindView(R.id.newset_icon)
    RelativeLayout seticon;
    @BindView(R.id.newname)
    RelativeLayout newname;
    @BindView(R.id.newsetname)
    TextView name;
    @BindView(R.id.newicon)
    CircleImageView icon;
    @BindView(R.id.newsetsex)
    RelativeLayout setsex;
    @BindView(R.id.newsex)
    TextView sex;
    @BindView(R.id.newtype)
    RelativeLayout newtype;
    @BindView(R.id.newtypetext)
    TextView typetext;
    @BindView(R.id.newsetok)
    TextView setok;
    @BindView(R.id.newmysetlayout)
    RelativeLayout layout;

    //请求相机
    private static final int REQUEST_CAPTURE = 100;
    //请求相册
    private static final int REQUEST_PICK = 101;

    //请求访问外部存储
    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 103;
    //请求写入外部存储
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 104;
    private String photofileName;

    String permissionName = "android.permission.WRITE_EXTERNAL_STORAGE";
    private String imgUrl;
    private Bitmap bitMap;
    private File fileover = null;
    private MultipartBody.Part avatar;
    private String sexid;
    private String identid;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initview();

        Intent intent = getIntent();
        String loginicon = intent.getStringExtra("loginicon");
        String loginname = intent.getStringExtra("loginname");
        String loginsex = intent.getStringExtra("loginsex");
        String loginindent = intent.getStringExtra("loginindent");

        Glide.with(MySetTwoActivity.this).load(loginicon).error(R.mipmap.loginicon).into(icon);
        name.setText(loginname);
        sex.setText(loginsex);
        typetext.setText(loginindent);

    }

    public void initview() {
        back.setOnClickListener(this);
        seticon.setOnClickListener(this);
        newname.setOnClickListener(this);
        setsex.setOnClickListener(this);
        newtype.setOnClickListener(this);
        setok.setOnClickListener(this);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_my_set_two;
    }

    @Override
    protected BaseContract.Presenter initPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.myset_back:
                finish();
                break;
            //修改头像
            case R.id.newset_icon:
                getpopuwindow();
                break;
            //修改昵称
            case R.id.newname:
                getsetname();
                break;
            //选择性别
            case R.id.newsetsex:
                getsetsex();
                break;
            //选择职业
            case R.id.newtype:
                getsettype();
                break;
            //保存信息
            case R.id.newsetok:
                getpopwindow();
                String s = name.getText().toString();
                String s1 = sex.getText().toString();
                String s2 = typetext.getText().toString();
                if (s1.equals("男")) {
                    sexid = "man";
                } else {
                    sexid = "woman";
                }
                if (s2.equals("上班族")) {
                    identid = "1";
                } else if (s2.equals("个体户")) {
                    identid = "2";
                } else {
                    identid = "3";
                }
                shangchuan(s, fileover, sexid, identid);
                break;
        }
    }

    public void getsetsex() {

        View inflate = LayoutInflater.from(MySetTwoActivity.this).inflate(R.layout.newsex_layout, null, false);
        final PopupWindow popupWindow = new PopupWindow(inflate, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setAnimationStyle(R.style.pop_anim);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(layout, Gravity.BOTTOM, 0, 0);
        light(0.8f);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                light(1.0f);
            }
        });
        TextView bt4 = (TextView) inflate.findViewById(R.id.bt4);
        TextView bt5 = (TextView) inflate.findViewById(R.id.bt5);

        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sex.setText("男");
                popupWindow.dismiss();
            }
        });
        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sex.setText("女");
                popupWindow.dismiss();
            }
        });
    }

    public void getsettype() {

        View inflate = LayoutInflater.from(MySetTwoActivity.this).inflate(R.layout.newtype_layout, null, false);
        final PopupWindow popupWindow = new PopupWindow(inflate, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setAnimationStyle(R.style.pop_anim);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(layout, Gravity.BOTTOM, 0, 0);
        light(0.8f);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                light(1.0f);
            }
        });
        TextView bt6 = (TextView) inflate.findViewById(R.id.bt6);
        TextView bt7 = (TextView) inflate.findViewById(R.id.bt7);
        TextView bt8 = (TextView) inflate.findViewById(R.id.bt8);

        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                typetext.setText("上班族");
                popupWindow.dismiss();
            }
        });
        bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                typetext.setText("个体户");
                popupWindow.dismiss();
            }
        });
        bt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                typetext.setText("企业主");
                popupWindow.dismiss();
            }
        });
    }

    public void getsetname() {
        //修改昵称
        final SelfDialog2 selfDialogt = new SelfDialog2(MySetTwoActivity.this);
        selfDialogt.setTitle("修改昵称");
        selfDialogt.setYesOnclickListener("确定", new SelfDialog2.onYesOnclickListener() {
            @Override
            public void onYesClick(String text) {
                if (text.equals("")) {
                    Toast.makeText(MySetTwoActivity.this, "昵称不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    name.setText(text);
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
    }

    public void getpopuwindow() {

        View inflate = LayoutInflater.from(MySetTwoActivity.this).inflate(R.layout.newphoto_layout, null, false);
        final PopupWindow popupWindow = new PopupWindow(inflate, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setAnimationStyle(R.style.pop_anim);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(layout, Gravity.BOTTOM, 0, 0);
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

                gotoCamera();
                popupWindow.dismiss();
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

    public void light(float t) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = t;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }


    private Uri fileUri;

    private void gotoCamera() {
        Log.d("evan", "*****************打开相机********************");

        if (cameraIsCanUse()) {
//            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            photofileName = "IMG_" + new Date() + ".jpg";
//            //必须使用已经存在的文件夹tempWhy
//            imgUrl = Environment.getExternalStorageDirectory() + File.separator + "tempWhy" + File.separator + photofileName;
//            File file = new File(imgUrl);
//            judeFileExists(file);
//            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
//            startActivityForResult(intent, REQUEST_CAPTURE);
            Intent takeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            Uri photoUri = getMediaFileUri(REQUEST_CAPTURE);
            fileUri = photoUri;
            takeIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            startActivityForResult(takeIntent, REQUEST_CAPTURE);
        } else {
            Toast.makeText(MySetTwoActivity.this, "请去设置中开启相机权限", Toast.LENGTH_SHORT).show();

        }

    }

    // 判断文件是否存在
    public static void judeFileExists(File file) {

        if (file.exists()) {
            System.out.println("file exists");
        } else {
            System.out.println("file not exists, create it ...");
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void gotoPhoto() {
        Log.d("evan", "*****************打开图库********************");
        if (hasPermission(MySetTwoActivity.this, permissionName)) {
            //跳转到调用系统图库
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(Intent.createChooser(intent, "请选择图片"), REQUEST_PICK);
        } else {
            Toast.makeText(MySetTwoActivity.this, "请去设置中开启存储权限", Toast.LENGTH_SHORT).show();
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

    public Uri getMediaFileUri(int type) {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "相册名字");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
        //创建Media File
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == REQUEST_CAPTURE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
        } else {
            return null;
        }
        return Uri.fromFile(mediaFile);
    }

    //判断是否有某个权限
    public static boolean hasPermission(Context context, String permission) {
        int perm = context.checkCallingOrSelfPermission(permission);
        return perm == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CAPTURE: //调用系统相机返回
                if (resultCode == RESULT_OK) {
//
//                    File file = new File(imgUrl);
//                    if (file.exists()) {
//                        Bitmap bm = BitmapFactory.decodeFile(imgUrl);
////                        icon.setImageBitmap(bm);
//                        Glide.with(MySetTwoActivity.this).load(imgUrl).error(R.mipmap.loginicon).into(icon);
//
//                    }
//                    fileover = file;
//                    //shangchuan(file);
///
//                }
                    if (data != null) {
                        if (data.hasExtra("data")) {
                            Log.i("URI", "data is not null");
                            Bitmap bitmap = data.getParcelableExtra("data");
                            icon.setImageBitmap(bitmap);//imageView即为当前页面需要展示照片的控件，可替换
                        }
                    } else {
                        Log.i("URI", "Data is null");
//                        Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath());
                        Glide.with(MySetTwoActivity.this).load(fileUri.getPath()).error(R.mipmap.loginicon).into(icon);
//                        icon.setImageBitmap(bitmap);//imageView即为当前页面需要展示照片的控件，可替换
                        fileover = new File(fileUri.getPath());
                    }

                }
                break;
            case REQUEST_PICK:  //调用系统相册返回
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    if (uri == null) {
                        return;
                    }
                    String cropImagePath = getRealFilePathFromUri(getApplicationContext(), uri);
//                    bitMap = BitmapFactory.decodeFile(cropImagePath);
//                    icon.setImageBitmap(bitMap);
                    Glide.with(MySetTwoActivity.this).load(cropImagePath).error(R.mipmap.loginicon).into(icon);

                    fileover = new File(cropImagePath);
                    //可以上传服务器了
                    //shangchuan(new File(cropImagePath));
                }
                break;
        }
    }

    private void shangchuan(String nick, File file, String gendel, String ident) {
        if (file == null) {
            avatar = MultipartBody.Part.createFormData("", "");
            ;
        } else {
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            avatar = MultipartBody.Part.createFormData("avatar", "avatar", requestFile);
        }
        MultipartBody.Part no = MultipartBody.Part.createFormData("nick", nick);
        MultipartBody.Part no2 = MultipartBody.Part.createFormData("gender", gendel);
        MultipartBody.Part no3 = MultipartBody.Part.createFormData("identity", ident);
        Observable<Object> objectObservable = new NetRequestUtils().bucuo().getbaseretrofit().getmylisticon(no, avatar, no2, no3).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        objectObservable.subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {
                popupWindow.dismiss();
                finish();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("TAG", e.getMessage());

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void getpopwindow() {
        light(0.5f);
        int width = getWindowManager().getDefaultDisplay().getWidth();
        View inflate = LayoutInflater.from(MySetTwoActivity.this).inflate(R.layout.loading_layout, null, false);
        TextView loading = (TextView) inflate.findViewById(R.id.loadingtext);
        loading.setText("保存中...");
        popupWindow = new PopupWindow(inflate, width / 3, width / 3);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                light(1.0f);
            }
        });
    }
}
