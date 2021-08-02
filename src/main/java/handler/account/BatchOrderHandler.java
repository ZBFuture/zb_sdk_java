package handler.account;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import entity.response.BatchOrderItem;
import handler.Handler;

import java.util.List;

public class BatchOrderHandler implements Handler {
    @Override
    public void handleData(String channel, String msg) {
        List<BatchOrderItem> result = JSON.parseObject(msg, new TypeReference<>() {
        });
        System.out.println(result);
    }

    @Override
    public void handleError(String channel, Integer code, String desc) {
        System.out.println("error:" + desc);
    }
}
