package money.kuxuan.platform.factory.net;

import android.text.TextUtils;
import android.util.Log;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import money.kuxuan.platform.common.Common;
import money.kuxuan.platform.factory.Factory;
import money.kuxuan.platform.factory.util.LoginInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求的封装
 *
 * @author HFRX
 * @version 1.0.0
 */
public class Network {
    private static Network instance;

    private Retrofit retrofit;
    private OkHttpClient client;
    private static ClearableCookieJar cookieJar;

    static {
        instance = new Network();
        cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(Factory.app()));
    }

    public static String channelId;


    private Network() {
    }


    public static OkHttpClient getClient() {
        if (instance.client != null)
            return instance.client;

        // 存储起来
        LoginInterceptor interceptor = new LoginInterceptor();
        try {
            instance.client = new OkHttpClient.Builder()
                    // 给所有的请求添加一个拦截器
    //                .addInterceptor(new Interceptor() {
    //                    @Override
    //                    public Response intercept(Chain chain) throws IOException {
    //                        // 拿到我们的请求
    //                        Request original = chain.request();
    //                        // 重新进行build
    //                        Request.Builder builder = original.newBuilder();
    ////                        if (!TextUtils.isEmpty(Account.getToken())) {
    ////                            // 注入一个token
    ////                            builder.addHeader("token", Account.getToken());
    ////                        }
    //                        long time = System.currentTimeMillis() / 1000;
    //                        try {
    //                            builder.addHeader("Content-Type", "application/json");
    //                            builder.addHeader("User-Agent", "version=" + Factory.getVersionName(Factory.app()) +
    //                                    "phone=" + android.os.Build.VERSION.RELEASE +
    //                                    android.os.Build.MODEL + "channel=" + channelId);
    //                            String a = "ttl=" + time + "&version=2.0&cid=" + channelId + "&device=Android&sign_key=91f8edf4792c8f63593266f75493a5f5";
    //                            builder.addHeader("ttl", time + "");
    //                            builder.addHeader("version", "2.0");
    //                            builder.addHeader("cid", channelId);
    //                            builder.addHeader("sign", md5(a));
    //                            builder.addHeader("device", "Android");
    //                            Log.e("header", channelId);
    //                        } catch (Exception e) {
    //                            e.printStackTrace();
    //                        }
    //                        Request newRequest = builder.build();
    //                        // 返回
    //                        return chain.proceed(newRequest);
    //                    }
//                    .sslSocketFactory(getSSLSocketFactory()).hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
                    .addInterceptor(interceptor)
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .cookieJar(cookieJar)
                    .build();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance.client;
    }



    public static SSLSocketFactory getSSLSocketFactory() throws Exception {
        //创建一个不验证证书链的证书信任管理器。
        final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] chain,
                    String authType) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] chain,
                    String authType) throws CertificateException {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[0];
            }
        }};

        // Install the all-trusting trust manager
        final SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustAllCerts,
                new java.security.SecureRandom());
        // Create an ssl socket factory with our all-trusting manager
        return sslContext
                .getSocketFactory();
    }






    // 构建一个Retrofit
    public static Retrofit getRetrofit() {
        if (instance.retrofit != null)
            return instance.retrofit;

        // 得到一个OK Client
        OkHttpClient client = getClient();



        Log.e("cookie", client.cookieJar().toString());
        // Retrofit
        Retrofit.Builder builder = new Retrofit.Builder();


        // 设置电脑链接
        instance.retrofit = builder.baseUrl(Common.Constance.API_URL)
                // 设置client
                .client(client)
                // 设置Json解析器
                .addConverterFactory(GsonConverterFactory.create(Factory.getGson()))
                .build();

        return instance.retrofit;

    }


    /**
     * 返回一个请求代理
     *
     * @return RemoteService
     */
    public static RemoteService remote() {
        return Network.getRetrofit().create(RemoteService.class);
    }

    public static String md5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}
