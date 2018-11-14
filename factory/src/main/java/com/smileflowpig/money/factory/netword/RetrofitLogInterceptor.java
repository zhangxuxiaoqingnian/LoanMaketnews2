package com.smileflowpig.money.factory.netword;

import android.util.Log;

import com.smileflowpig.money.factory.util.LoginUtil;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.Buffer;

import static okhttp3.internal.Util.UTF_8;

/**
 * Created by 小狼 on 2018/11/13.
 */

public class RetrofitLogInterceptor implements Interceptor {
    public static String TAG = "RetrofitLogInterceptor";

    @Override
    public synchronized okhttp3.Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        long startTime = System.currentTimeMillis();
        okhttp3.Response response = chain.proceed(chain.request());
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        okhttp3.MediaType mediaType = response.body().contentType();
        String content = response.body().string();
        Log.i(TAG, "请求地址：| " + request.toString());
        printParams(request.body());
        Log.i(TAG, "请求体返回：| Response:" + content);
        Log.i(TAG, "----------请求耗时:" + duration + "毫秒----------");
        return response.newBuilder().body(okhttp3.ResponseBody.create(mediaType, content)).build();
    }


    private void printParams(RequestBody body) {
        Buffer buffer = new Buffer();
        try {
            body.writeTo(buffer);
            Charset charset = Charset.forName("UTF-8");
            MediaType contentType = body.contentType();
            if (contentType != null) {
                charset = contentType.charset(UTF_8);
            }
            String params = buffer.readString(charset);
            Log.i(TAG, "请求参数： | " + params);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

