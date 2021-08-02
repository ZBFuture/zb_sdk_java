package handler.account;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import entity.response.BillItemResult;
import entity.response.PageVo;
import handler.Handler;

public class GetBillHandler implements Handler {
    @Override
    public void handleData(String channel, String msg) {
        PageVo<BillItemResult> result = JSON.parseObject(msg, new TypeReference<>() {
        });
        System.out.println(result);
    }

    @Override
    public void handleError(String channel, Integer code, String desc) {
        System.out.println("error:" + desc);
    }
}
