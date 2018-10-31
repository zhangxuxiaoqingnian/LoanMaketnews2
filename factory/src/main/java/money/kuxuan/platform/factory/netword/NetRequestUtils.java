package money.kuxuan.platform.factory.netword;


import android.os.Environment;
import android.util.Log;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import money.kuxuan.platform.factory.Factory;
import okhttp3.Cache;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;



/**
 * Created by 小狼 on 2017/11/28.
 */

public class NetRequestUtils {

    private NetRequestUtils Instance;
    private Baseretrofit baseretrofit;
    private static ClearableCookieJar cookieJar;

    public NetRequestUtils(Baseretrofit baseretrofit) {

        this.baseretrofit=baseretrofit;
    }

    static {
        cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(Factory.app()));
    }
    public NetRequestUtils() {

    }

    public Baseretrofit getbaseretrofit(){

        return baseretrofit;
    }



    public NetRequestUtils bucuo(){

        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {

                Log.i("RetrofitLog","retrofitBack = "+message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder okHttpClient= new OkHttpClient.Builder();
        int maxcache=10*1024*1024;
        Cache cache=new Cache(Environment.getDataDirectory(),maxcache);
        okHttpClient.cache(cache);
        okHttpClient.addInterceptor(loggingInterceptor);
        okHttpClient.cookieJar(cookieJar);
       okHttpClient.connectTimeout(30, TimeUnit.SECONDS);
               okHttpClient.readTimeout(30, TimeUnit.SECONDS);
                okHttpClient.writeTimeout(30, TimeUnit.SECONDS);
        //https://newapi.henhaojie.com/user/
        //http://bw.quyaqu.com/user/
        Retrofit.Builder retrofit=new Retrofit.Builder().baseUrl("https://newapi.henhaojie.com/user/").client(okHttpClient.build());
        retrofit.addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create());

        Baseretrofit baseretrofit = retrofit.build().create(Baseretrofit.class);

        Instance=new NetRequestUtils(baseretrofit);

        return Instance;


    }

}
