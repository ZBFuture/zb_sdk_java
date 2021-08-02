package handler.market;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import handler.Handler;

import java.util.List;

//Double List
public class DListHandler implements Handler {
    @Override
    public void handleData(String channel, String msg) {
        List<List> result = JSON.parseObject(msg, new TypeReference<>() {
        });
        System.out.println(channel + ":" + result);
    }

    @Override
    public void handleError(String channel, Integer code, String desc) {
        System.out.println("error:" + desc);
    }
}
