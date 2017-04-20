package com.example.waean.zhiwen.utils;

import com.example.waean.zhiwen.application.MyApplication;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by waean on 2017/04/06.
 */

public class HttpUtil {
    private static final OkHttpClient mOkHttpClient = new OkHttpClient();

    public static void sendOkHttpRequest(String type, okhttp3.Callback callback) {
        RequestBody requestBody = new FormBody.Builder()
                .add("type", type)
                .add("key", MyApplication.APPKEY)
                .build();

        Request request = new Request.Builder()
                .url(MyApplication.url)
                .post(requestBody)
                .build();
        mOkHttpClient.newCall(request).enqueue(callback);
    }
}
