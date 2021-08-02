package handler.account;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import entity.response.PageVo;
import entity.response.TradeResult;
import handler.Handler;

public class TradeHistoryHandler implements Handler {
    @Override
    public void handleData(String channel, String msg) {
        PageVo<TradeResult> result = JSON.parseObject(msg, new TypeReference<>() {
        });
        System.out.println(result);
    }

    @Override
    public void handleError(String channel, Integer code, String desc) {
        System.out.println("error:" + desc);
    }
}
