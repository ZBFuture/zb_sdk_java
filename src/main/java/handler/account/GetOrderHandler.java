package handler.account;

import com.alibaba.fastjson.JSON;
import entity.response.OrderResult;
import handler.Handler;

public class GetOrderHandler implements Handler {
    @Override
    public void handleData(String channel, String msg) {
        OrderResult result = JSON.parseObject(msg, OrderResult.class);
        System.out.println(result);
    }

    @Override
    public void handleError(String channel, Integer code, String desc) {
        System.out.println("error:" + desc);
    }
}
