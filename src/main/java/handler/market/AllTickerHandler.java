package handler.market;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import handler.Handler;

import java.util.List;
import java.util.Map;

public class AllTickerHandler implements Handler {
    @Override
    public void handleData(String channel, String msg) {
        Map<String, List> result = JSON.parseObject(msg, new TypeReference<>() {
        });
        System.out.println(channel + ":" + result);
    }

    @Override
    public void handleError(String channel, Integer code, String desc) {
        System.out.println("error:" + desc);
    }
}
