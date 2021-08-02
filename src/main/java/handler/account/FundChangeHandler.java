package handler.account;

import com.alibaba.fastjson.JSON;
import entity.response.FundChangeResult;
import handler.Handler;

public class FundChangeHandler implements Handler {
    @Override
    public void handleData(String channel, String msg) {
        FundChangeResult result = JSON.parseObject(msg, FundChangeResult.class);
        System.out.println(result);
    }

    @Override
    public void handleError(String channel, Integer code, String desc) {
        System.out.println("error:" + desc);
    }
}
