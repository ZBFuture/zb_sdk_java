package handler.account;

import com.alibaba.fastjson.JSON;
import entity.response.PositionsSettingResult;
import handler.Handler;

public class SetMarginModeHandler implements Handler {
    @Override
    public void handleData(String channel, String msg) {
        PositionsSettingResult result = JSON.parseObject(msg, PositionsSettingResult.class);
        System.out.println(result);
    }

    @Override
    public void handleError(String channel, Integer code, String desc) {
        System.out.println("error:" + desc);
    }
}
