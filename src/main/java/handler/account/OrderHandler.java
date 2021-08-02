package handler.account;

import com.alibaba.fastjson.JSON;
import handler.Handler;

public class OrderHandler implements Handler {
    @Override
    public void handleData(String channel, String msg) {
        Long result = JSON.parseObject(msg, Long.class);
        System.out.println(result);
    }

    @Override
    public void handleError(String channel, Integer code, String desc) {
        System.out.println("error:" + desc);
    }
}
