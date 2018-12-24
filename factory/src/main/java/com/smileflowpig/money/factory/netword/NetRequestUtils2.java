package com.smileflowpig.money.factory.netword;


import android.os.Environment;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.smileflowpig.money.common.Common;
import com.smileflowpig.money.factory.Factory;
import com.smileflowpig.money.factory.util.LoginInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class NetRequestUtils2 {

    private NetRequestUtils2 Instance;
    private Baseretrofit baseretrofit;
    private static ClearableCookieJar cookieJar;

    public NetRequestUtils2(Baseretrofit baseretrofit) {

        this.baseretrofit = baseretrofit;
    }

    static {
        cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(Factory.app()));
    }

    public NetRequestUtils2() {

    }

    public Baseretrofit getbaseretrofit() {

        return baseretrofit;
    }


    public NetRequestUtils2 bucuo() {

        // 存储起来
        LoginInterceptor interceptor = new LoginInterceptor();
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        int maxcache = 10 * 1024 * 1024;
        Cache cache = new Cache(Environment.getDataDirectory(), maxcache);
        okHttpClient.cache(cache);
        //okHttpClient.addInterceptor(new RetrofitLogInterceptor());
        okHttpClient.cookieJar(cookieJar);

        okHttpClient.addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        okHttpClient.addInterceptor(interceptor);
        okHttpClient.connectTimeout(30, TimeUnit.SECONDS);
        okHttpClient.readTimeout(30, TimeUnit.SECONDS);
        okHttpClient.writeTimeout(30, TimeUnit.SECONDS);
        okHttpClient.addInterceptor(new Retry(10));//多次重连

        //https://newapi.henhaojie.com/user/
        //http://bw.quyaqu.com/user/
                Retrofit.Builder retrofit = new Retrofit.Builder().baseUrl("http://182.92.118.1:8070/api/").client(okHttpClient.build());
        retrofit.addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create());

//        Baseretrofit baseretrofit =
//                Network.getRetrofit().create(Baseretrofit.class)

        Baseretrofit baseretrofit = retrofit.build().create(Baseretrofit.class);

        Instance = new NetRequestUtils2(baseretrofit);

        return Instance;


    }

}
