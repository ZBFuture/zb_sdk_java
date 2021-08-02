package handler.account;

import com.alibaba.fastjson.JSON;
import entity.response.NominalValueResult;
import handler.Handler;

public class GetNominalValueHandler implements Handler {
    @Override
    public void handleData(String channel, String msg) {
        NominalValueResult result = JSON.parseObject(msg, NominalValueResult.class);
        System.out.println(result);
    }

    @Override
    public void handleError(String channel, Integer code, String desc) {
        System.out.println("error:" + desc);
    }
}
