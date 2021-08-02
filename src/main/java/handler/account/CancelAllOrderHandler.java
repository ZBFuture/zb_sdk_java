package handler.account;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import handler.Handler;

import java.util.List;

public class CancelAllOrderHandler implements Handler {
    @Override
    public void handleData(String channel, String msg) {
        List<String> result = JSON.parseObject(msg, new TypeReference<>() {
        });
        System.out.println(result);
    }

    @Override
    public void handleError(String channel, Integer code, String desc) {
        System.out.println("error:" + desc);
    }
}
