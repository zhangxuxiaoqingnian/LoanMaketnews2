package com.smileflowpig.money.moneyplatfrom.activities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
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
import com.smileflowpig.money.moneyplatfrom.util.CropUtils;
import com.smileflowpig.money.moneyplatfrom.util.DialogPermission;
import com.smileflowpig.money.moneyplatfrom.util.FileUtil;
import com.smileflowpig.money.moneyplatfrom.util.PermissionUtil;
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
import com.smileflowpig.money.moneyplatfrom.util.SharedPreferenceMark;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.smileflowpig.money.moneyplatfrom.util.FileUtil.getRealFilePathFromUri;

public class MySetTwoActivity extends PresenterActivity implements View.OnClickListener {

    @BindView(R.id.myset_back)
    LinearLayout back;
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

    private static final int REQUEST_CODE_TAKE_PHOTO = 1;
    private static final int REQUEST_CODE_ALBUM = 2;
    private static final int REQUEST_CODE_CROUP_PHOTO = 3;
    private File file;
    private Uri uri;
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
        init();

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


    private void init() {
        file = new File(FileUtil.getCachePath(this), "user-avatar.jpg");
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            uri = Uri.fromFile(file);
        } else {
            //通过FileProvider创建一个content类型的Uri(android 7.0需要这样的方法跨应用访问)
            uri = FileProvider.getUriForFile(MySetTwoActivity.this, "com.yf.useravatar", file);
        }
    }

    private void gotoCamera() {

        if (PermissionUtil.hasCameraPermission(MySetTwoActivity.this)) {
            uploadAvatarFromPhotoRequest();
        }
    }

    private void gotoPhoto() {
        uploadAvatarFromAlbumRequest();
    }

    private void uploadAvatarFromPhotoRequest() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, REQUEST_CODE_TAKE_PHOTO);
    }
    private void uploadAvatarFromAlbumRequest() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, REQUEST_CODE_ALBUM);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {

            case PermissionUtil.REQUEST_SHOWCAMERA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    uploadAvatarFromPhotoRequest();

                } else {
                    if (!SharedPreferenceMark.getHasShowCamera()) {
                        SharedPreferenceMark.setHasShowCamera(true);
                        new DialogPermission(this, "关闭摄像头权限影相机功能");

                    } else {
                        Toast.makeText(this, "未获取摄像头权限", Toast.LENGTH_SHORT)
                                .show();
                    }
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != -1) {
            return;
        }
        if (requestCode == REQUEST_CODE_ALBUM && data != null) {
            Uri newUri;
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                newUri = Uri.parse("file:///" + CropUtils.getPath(this, data.getData()));
            } else {
                newUri = data.getData();
            }
            if (newUri != null) {
                startPhotoZoom(newUri);
            } else {
                Toast.makeText(this, "没有得到相册图片", Toast.LENGTH_LONG).show();
            }
        } else if (requestCode == REQUEST_CODE_TAKE_PHOTO) {
            startPhotoZoom(uri);
        } else if (requestCode == REQUEST_CODE_CROUP_PHOTO) {
            //uploadAvatarFromPhoto();
            fileover = FileUtil.getSmallBitmap(this, file.getPath());
            Uri uri = Uri.fromFile(fileover);
            icon.setImageURI(uri);
        }
    }

    /**
     * 裁剪拍照裁剪
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra("crop", "true");// crop=true 有这句才能出来最后的裁剪页面.
        intent.putExtra("aspectX", 1);// 这两项为裁剪框的比例.
        intent.putExtra("aspectY", 1);// x:y=1:1
//        intent.putExtra("outputX", 400);//图片输出大小
//        intent.putExtra("outputY", 400);
        intent.putExtra("output", Uri.fromFile(file));
        intent.putExtra("outputFormat", "JPEG");// 返回格式
        startActivityForResult(intent, REQUEST_CODE_CROUP_PHOTO);
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
