package client.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import exception.SystemCode;
import exception.ZbException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class HttpClient {
    public String endpoint;
    OkHttpClient client;

    public HttpClient(String endpoint) {
        this.endpoint = endpoint;
        this.client = new OkHttpClient();
    }

    public <T> T call(Request request, TypeReference<T> type) {
        try {
            Response response = client.newCall(request).execute();
            if (response.code() != 200) {
                throw new ZbException(SystemCode.CALL_ERROR.getCode(), "[call] Response Status Error : " + response.code() + " message:" + response.message());
            }
            if (response != null && response.body() != null) {
                String str = response.body().string();
                response.close();
                System.out.println(str);
                return JSON.parseObject(str, type);
            } else {
                throw new ZbException(SystemCode.CALL_ERROR.getCode(), "[call] Cannot get the response from server");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ZbException(e);
        }
    }
}
