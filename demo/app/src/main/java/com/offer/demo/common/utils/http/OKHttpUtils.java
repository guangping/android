package com.offer.demo.common.utils.http;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by lance on 2016/4/22.
 */
public class OKHttpUtils {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final MediaType HTML = MediaType.parse("text/html; charset=utf-8");

    /**
     * http get请求
     */
    public static HttpResponse get(String url) {
        HttpResponse resultResponse = new HttpResponse();
        if (StringUtils.isBlank(url)) {
            resultResponse.setHttpMsg(HttpMsg.URL_ERROR);
            return resultResponse;
        }
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                String result = getMsg(response.body().byteStream());
                resultResponse.setMsg(result);
                resultResponse.setHttpMsg(HttpMsg.SUCCESS);
            }
        } catch (Exception e) {
            resultResponse.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return resultResponse;
    }

    /**
     * 异步请求
     */
    public static void getAsyn(String url, Callback callback) {
        if (StringUtils.isBlank(url)) {
            new IOException("url is error");
        }
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            client.newCall(request).enqueue(callback);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * post form 提交
     */
    public static HttpResponse postForm(String url, Map<String, String> param) {
        HttpResponse resultResponse = new HttpResponse();
        if (StringUtils.isBlank(url)) {
            resultResponse.setHttpMsg(HttpMsg.URL_ERROR);
            return resultResponse;
        }
        try {
            OkHttpClient client = new OkHttpClient();
            FormBody.Builder builder = new FormBody.Builder();
            if (null != param) {
                for (Map.Entry<String, String> entry : param.entrySet()) {
                    builder.addEncoded(entry.getKey(), entry.getValue());
                }
            }
            RequestBody body = builder.build();
            Request request = new Request.Builder().url(url).post(body).build();
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                String result = getMsg(response.body().byteStream());
                resultResponse.setMsg(result);
                resultResponse.setHttpMsg(HttpMsg.SUCCESS);
            }
        } catch (Exception e) {
            resultResponse.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return resultResponse;
    }

    private static String getMsg(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        StringBuffer buffer = new StringBuffer(3000);
        String line = reader.readLine();
        while (line != null) {
            buffer.append(line);
            line = reader.readLine();
        }
        reader.close();
        return buffer.toString();
    }
}
