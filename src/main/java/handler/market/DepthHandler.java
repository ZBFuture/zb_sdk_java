package handler.market;

import com.alibaba.fastjson.JSON;
import entity.response.Depth;
import handler.Handler;

public class DepthHandler implements Handler {
    @Override
    public void handleData(String channel, String msg) {
        Depth result = JSON.parseObject(msg, Depth.class);
        System.out.println(channel + " asks:" + result.getAsks() + " bids:" + result.getBids());
    }

    @Override
    public void handleError(String channel, Integer code, String desc) {
        System.out.println("error:" + desc);
    }
}
