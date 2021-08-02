package handler.account;

import com.alibaba.fastjson.JSON;
import handler.Handler;

public class CancelOrderHandler implements Handler {
    @Override
    public void handleData(String channel, String msg) {
        String result = JSON.parseObject(msg, String.class);
        System.out.println(result);
    }

    @Override
    public void handleError(String channel, Integer code, String desc) {
        System.out.println("error:" + desc);
    }
}
