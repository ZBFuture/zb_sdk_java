package handler.account;

import com.alibaba.fastjson.JSON;
import entity.response.MarginInfoResult;
import handler.Handler;

public class MarginInfoHandler implements Handler {
    @Override
    public void handleData(String channel, String msg) {
        MarginInfoResult result = JSON.parseObject(msg, MarginInfoResult.class);
        System.out.println(result);
    }

    @Override
    public void handleError(String channel, Integer code, String desc) {
        System.out.println("error:" + desc);
    }
}
