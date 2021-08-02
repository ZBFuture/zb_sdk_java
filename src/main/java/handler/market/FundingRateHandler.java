package handler.market;

import com.alibaba.fastjson.JSON;
import entity.response.FundingRate;
import handler.Handler;

public class FundingRateHandler implements Handler {
    @Override
    public void handleData(String channel, String msg) {
        FundingRate result = JSON.parseObject(msg, FundingRate.class);
        System.out.println(channel + ":" + result.getFundingRate() + " " + result.getNextCalculateTime());

    }

    @Override
    public void handleError(String channel, Integer code, String desc) {
        System.out.println("error:" + desc);
    }
}
