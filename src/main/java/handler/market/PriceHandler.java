package handler.market;

import com.alibaba.fastjson.JSON;
import handler.Handler;

import java.math.BigDecimal;

public class PriceHandler implements Handler {
    @Override
    public void handleData(String channel, String msg) {
        String str = JSON.parseObject(msg, String.class);
        BigDecimal result = new BigDecimal(str);
        System.out.println(channel + ":" + result);
    }

    @Override
    public void handleError(String channel, Integer code, String desc) {
        System.out.println("error:" + desc);
    }
}
