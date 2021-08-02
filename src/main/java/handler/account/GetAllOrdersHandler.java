package handler.account;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import entity.response.OrderResult;
import entity.response.PageVo;
import handler.Handler;

public class GetAllOrdersHandler implements Handler {
    @Override
    public void handleData(String channel, String msg) {
        PageVo<OrderResult> result = JSON.parseObject(msg, new TypeReference<>() {
        });
        System.out.println(result);
    }

    @Override
    public void handleError(String channel, Integer code, String desc) {

    }
}
