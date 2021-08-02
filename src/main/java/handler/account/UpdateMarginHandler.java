package handler.account;

import com.alibaba.fastjson.JSON;
import entity.response.UpdateMarginResult;
import handler.Handler;

public class UpdateMarginHandler implements Handler {
    @Override
    public void handleData(String channel, String msg) {
        UpdateMarginResult result = JSON.parseObject(msg, UpdateMarginResult.class);
        System.out.println(result);
    }

    @Override
    public void handleError(String channel, Integer code, String desc) {
        System.out.println("error:" + desc);
    }
}
