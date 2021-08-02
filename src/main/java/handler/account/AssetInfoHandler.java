package handler.account;

import com.alibaba.fastjson.JSON;
import entity.response.AssetInfoResult;
import handler.Handler;

public class AssetInfoHandler implements Handler {
    @Override
    public void handleData(String channel, String msg) {
        AssetInfoResult result = JSON.parseObject(msg, AssetInfoResult.class);
        System.out.println(result);
    }

    @Override
    public void handleError(String channel, Integer code, String desc) {
        System.out.println("error:" + desc);
    }
}
