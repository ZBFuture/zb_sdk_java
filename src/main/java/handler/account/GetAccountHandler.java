package handler.account;

import com.alibaba.fastjson.JSON;
import entity.response.GetAccountResult;
import handler.Handler;

public class GetAccountHandler implements Handler {
    @Override
    public void handleData(String channel, String msg) {
        GetAccountResult result = JSON.parseObject(msg, GetAccountResult.class);
        System.out.println(result);
    }

    @Override
    public void handleError(String channel, Integer code, String desc) {
        System.out.println("error:" + desc);
    }
}
