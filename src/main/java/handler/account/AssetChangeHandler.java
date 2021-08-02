package handler.account;

import com.alibaba.fastjson.JSON;
import entity.response.AssetChangeResult;
import handler.Handler;

public class AssetChangeHandler implements Handler {
    @Override
    public void handleData(String channel, String msg) {
        AssetChangeResult result = JSON.parseObject(msg, AssetChangeResult.class);
        System.out.println(result);
    }

    @Override
    public void handleError(String channel, Integer code, String desc) {
        System.out.println("error:" + desc);
    }
}
