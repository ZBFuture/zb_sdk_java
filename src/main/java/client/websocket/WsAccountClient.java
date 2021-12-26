package client.websocket;

import com.alibaba.fastjson.JSON;
import entity.WSResponse;
import entity.request.OrderRequest;
import handler.Handler;
import okhttp3.*;
import sign.DateLocalUtil;
import sign.EncryDigestUtil;
import sign.HmacSHA256Base64Utils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WsAccountClient extends WebSocketListener {
    private final String LOGIN = "login";
    private final String PONG = "pong";
    private final String Subscribe = "subscribe";

    /**
     * fund channel
     */
    private final String CH_FundChange = "Fund.change";
    private final String CH_FundBalance = "Fund.balance";
    private final String CH_FundGetAccount = "Fund.getAccount";
    private final String CH_FundGetBill = "Fund.getBill";
    private final String CH_FundAssetChange = "Fund.assetChange";
    private final String CH_FundAssetInfo = "Fund.assetInfo";

    /**
     * position channel
     */
    private final String CH_PositionsChange = "Positions.change";
    private final String CH_getPositions = "Positions.getPositions";
    private final String CH_marginInfo = "Positions.marginInfo";
    private final String CH_updateMargin = "Positions.updateMargin";
    private final String CH_setLeverage = "Positions.setLeverage";
    private final String CH_setPositionsMode = "Positions.setPositionsMode";
    private final String CH_setMarginMode = "Positions.setMarginMode";
    private final String CH_getNominalValue = "Positions.getNominalValue";

    /**
     * trade channel
     */
    private final String CH_getOrder = "Trade.getOrder";
    private final String CH_getUndoneOrders = "trade.getUndoneOrders";
    private final String CH_getAllOrders = "trade.getAllOrders";
    private final String CH_getTradeList = "trade.getTradeList";
    private final String CH_tradeHistory = "trade.tradeHistory";

    private final String CH_orderChange = "Trade.orderChange";
    private final String CH_order = "Trade.order";
    private final String CH_batchOrder = "Trade.batchOrder";
    private final String CH_cancelOrder = "Trade.cancelOrder";
    private final String CH_batchCancelOrder = "Trade.batchCancelOrder";
    private final String CH_cancelAllOrders = "trade.cancelAllOrders";

    private String ApiKey;
    private String SecretKey;   //网站中的原始SecretKey字符串
    private String SecretKeySha1;//对SecretKey进行sha1加密后的字符串

    private OkHttpClient httpClient = new OkHttpClient();
    private WebSocket webSocket;

    private HashMap<String, Handler> Handlers = new HashMap<>();

    //SecretKey:网站中的原始SecretKey字符串
    public WsAccountClient(String endpoint, String apiKey, String secretKey) {
        this.ApiKey = apiKey;
        this.SecretKey = secretKey;
        this.SecretKeySha1 = EncryDigestUtil.digest(this.SecretKey);

        this.webSocket = httpClient.newWebSocket(new Request.Builder().url(endpoint).build(), this);
    }


    public void login() {
        String ts = DateLocalUtil.toUTC();
        String sign = null;
        try {
            sign = HmacSHA256Base64Utils.sign(ts, "GET", LOGIN, null, this.ApiKey, this.SecretKeySha1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        HashMap<String, String> map = new HashMap<>();
        map.put("action", LOGIN);
        map.put("ZB-APIKEY", this.ApiKey);
        map.put("ZB-TIMESTAMP", ts);
        map.put("ZB-SIGN", sign);
        this.webSocket.send(JSON.toJSONString(map));
    }

    public void SubscribeFundChange(String currency, Integer futuresAccountType, Handler handler) {
        String channel = CH_FundChange;

        HashMap<String, Object> map = new HashMap<>();
        if (currency != null) {
            map.put("currency", currency);
        }
        subscribe(channel, futuresAccountType, map, handler);
    }

    public void getAccount(String convertUnit, Integer futuresAccountType, Handler handler) {
        String channel = CH_FundGetAccount;

        HashMap<String, Object> map = new HashMap<>();
        map.put("convertUnit", convertUnit);
        subscribe(channel, futuresAccountType, map, handler);
    }

    public void balance(String currency, Integer futuresAccountType, Handler handler) {
        String channel = CH_FundBalance;

        HashMap<String, Object> map = new HashMap<>();
        if (currency != null) {
            map.put("currency", currency);
        }
        subscribe(channel, futuresAccountType, map, handler);
    }

    public void getBill(String currency, Integer futuresAccountType, Integer type, Integer pageNum, Integer pageSize, Long startTime, Long endTime, Handler handler) {
        String channel = CH_FundGetBill;

        HashMap<String, Object> map = new HashMap<>();
        if (currency != null) {
            map.put("currency", currency);
        }
        if (type != null) {
            map.put("billType", type);
        }
        if (pageNum != null) {
            map.put("pageNum", pageNum);
        }
        if (pageSize != null) {
            map.put("pageSize", pageSize);
        }
        if (startTime != null) {
            map.put("startTime", startTime);
        }
        if (endTime != null) {
            map.put("endTime", endTime);
        }
        subscribe(channel, futuresAccountType, map, handler);
    }

    public void SubscribeAssetChange(String convertUnit, Integer futuresAccountType, Handler handler) {
        String channel = CH_FundAssetChange;

        HashMap<String, Object> map = new HashMap<>();
        if (convertUnit != null) {
            map.put("convertUnit", convertUnit);
        }
        subscribe(channel, futuresAccountType, map, handler);
    }

    public void assetInfo(String convertUnit, Integer futuresAccountType, Handler handler) {
        String channel = CH_FundAssetInfo;

        HashMap<String, Object> map = new HashMap<>();
        if (convertUnit != null) {
            map.put("convertUnit", convertUnit);
        }
        subscribe(channel, futuresAccountType, map, handler);
    }

    public void SubscribePositionsChange(String symbol, Integer futuresAccountType, Handler handler) {
        String channel = CH_PositionsChange;

        HashMap<String, Object> map = new HashMap<>();
        if (symbol != null) {
            map.put("symbol", symbol);
        }
        subscribe(channel, futuresAccountType, map, handler);
    }

    public void getPositions(String symbol, Integer futuresAccountType, Handler handler) {
        String channel = CH_getPositions;

        HashMap<String, Object> map = new HashMap<>();
        if (symbol != null) {
            map.put("symbol", symbol);
        }
        subscribe(channel, futuresAccountType, map, handler);
    }

    public void marginInfo(Long positionsId, Integer futuresAccountType, Handler handler) {
        String channel = CH_marginInfo;

        HashMap<String, Object> map = new HashMap<>();
        map.put("positionsId", positionsId);
        subscribe(channel, futuresAccountType, map, handler);
    }

    public void updateMargin(Long positionsId, Integer futuresAccountType, BigDecimal amount, Integer type, Handler handler) {
        String channel = CH_updateMargin;

        HashMap<String, Object> map = new HashMap<>();
        map.put("positionsId", positionsId);
        map.put("amount", amount);
        map.put("type", type);
        subscribe(channel, futuresAccountType, map, handler);
    }

    public void setLeverage(String symbol, Integer futuresAccountType, Integer leverage, Handler handler) {
        String channel = CH_setLeverage;

        HashMap<String, Object> map = new HashMap<>();
        map.put("symbol", symbol);
        map.put("leverage", leverage);
        subscribe(channel, futuresAccountType, map, handler);
    }

    public void setPositionsMode(String symbol, Integer futuresAccountType, Integer positionsMode, Handler handler) {
        String channel = CH_setPositionsMode;

        HashMap<String, Object> map = new HashMap<>();
        map.put("symbol", symbol);
        map.put("positionsMode", positionsMode);
        subscribe(channel, futuresAccountType, map, handler);
    }

    public void setMarginMode(String symbol, Integer futuresAccountType, Integer marginMode, Handler handler) {
        String channel = CH_setMarginMode;

        HashMap<String, Object> map = new HashMap<>();
        map.put("symbol", symbol);
        map.put("marginMode", marginMode);
        subscribe(channel, futuresAccountType, map, handler);
    }

    public void getNominalValue(String symbol, Integer futuresAccountType, Integer side, Handler handler) {
        String channel = CH_getNominalValue;

        HashMap<String, Object> map = new HashMap<>();
        map.put("symbol", symbol);
        map.put("side", side);
        subscribe(channel, futuresAccountType, map, handler);
    }

    public void getOrder(String symbol, Long orderId, String clientOrderId, Handler handler) {
        String channel = CH_getOrder;

        HashMap<String, Object> map = new HashMap<>();
        map.put("symbol", symbol);
        if (orderId != null) {
            map.put("orderId", orderId);
        }
        if (clientOrderId != null) {
            map.put("clientOrderId", clientOrderId);
        }

        subscribe(channel, map, handler);
    }

    public void getUndoneOrders(String symbol, Integer pageNum, Integer pageSize, Handler handler) {
        String channel = CH_getUndoneOrders;

        HashMap<String, Object> map = new HashMap<>();
        map.put("symbol", symbol);
        if (pageNum != null) {
            map.put("pageNum", pageNum);
        }
        if (pageSize != null) {
            map.put("pageSize", pageSize);
        }

        subscribe(channel, map, handler);
    }

    public void getAllOrders(String symbol, Integer pageNum, Integer pageSize, Long startTime, Long endTime, Handler handler) {
        String channel = CH_getAllOrders;

        HashMap<String, Object> map = new HashMap<>();
        map.put("symbol", symbol);
        if (pageNum != null) {
            map.put("pageNum", pageNum);
        }
        if (pageSize != null) {
            map.put("pageSize", pageSize);
        }
        if (startTime != null) {
            map.put("startTime", startTime);
        }
        if (endTime != null) {
            map.put("endTime", endTime);
        }

        subscribe(channel, map, handler);
    }

    public void getTradeList(String symbol, Long orderId, Handler handler) {
        String channel = CH_getTradeList;

        HashMap<String, Object> map = new HashMap<>();
        map.put("symbol", symbol);
        map.put("orderId", orderId);

        subscribe(channel, map, handler);
    }

    public void tradeHistory(String symbol, Handler handler) {
        String channel = CH_tradeHistory;

        HashMap<String, Object> map = new HashMap<>();
        map.put("symbol", symbol);

        subscribe(channel, map, handler);
    }

    public void tradeHistory(String symbol, Integer pageNum, Integer pageSize, Long startTime, Long endTime, Handler handler) {
        String channel = CH_tradeHistory;

        HashMap<String, Object> map = new HashMap<>();
        map.put("symbol", symbol);
        if (pageNum != null) {
            map.put("pageNum", pageNum);
        }
        if (pageSize != null) {
            map.put("pageSize", pageSize);
        }
        if (startTime != null) {
            map.put("startTime", startTime);
        }
        if (endTime != null) {
            map.put("endTime", endTime);
        }

        subscribe(channel, map, handler);
    }

    //action: 1 限价, 2 市价, 3 IOC, 4 只做 maker, 5 FOK
    //side: 1开多（买入），2开空（卖出），3平多（卖出），4平空（买入）
    public void order(String symbol, BigDecimal price, BigDecimal amount, Integer action, Integer entrustType, Integer side, Handler handler) {
        String channel = CH_order;

        HashMap<String, Object> map = new HashMap<>();
        map.put("symbol", symbol);
        map.put("price", price);
        map.put("amount", amount);
        map.put("action", action);
        map.put("entrustType", entrustType);
        map.put("side", side);

        subscribe(channel, map, handler);
    }

    public void order(OrderRequest order, Handler handler) {
        String channel = CH_order;

        HashMap<String, Object> map = new HashMap<>();
        map.put("symbol", order.getSymbol());
        map.put("price", order.getPrice());
        map.put("amount", order.getAmount());
        map.put("action", order.getAction());
        map.put("entrustType", order.getEntrustType());
        map.put("side", order.getSide());

        subscribe(channel, map, handler);
    }

    public void batchOrder(List<OrderRequest> orders, Handler handler) {
        String channel = CH_batchOrder;

        HashMap<String, Object> map = new HashMap<>();
        map.put("orderDatas", JSON.toJSONString(orders));

        subscribe(channel, map, handler);
    }

    public void cancelOrder(String symbol, Long orderId, String clientOrderId, Handler handler) {
        String channel = CH_cancelOrder;

        HashMap<String, Object> map = new HashMap<>();
        map.put("symbol", symbol);
        if (orderId != null) {
            map.put("orderId", orderId);
        }
        if (clientOrderId != null) {
            map.put("clientOrderId", clientOrderId);
        }

        subscribe(channel, map, handler);
    }

    public void batchCancelOrder(String symbol, List<Long> orderIds, List<String> clientOrderIds, Handler handler) {
        String channel = CH_batchCancelOrder;

        HashMap<String, Object> map = new HashMap<>();
        map.put("symbol", symbol);
        if (orderIds != null) {
            map.put("orderId", orderIds);
        }
        if (clientOrderIds != null) {
            map.put("clientOrderId", clientOrderIds);
        }

        subscribe(channel, map, handler);
    }

    public void cancelAllOrders(String symbol, Handler handler) {
        String channel = CH_cancelAllOrders;

        HashMap<String, Object> map = new HashMap<>();
        map.put("symbol", symbol);

        subscribe(channel, map, handler);
    }

    public void orderChange(String symbol, Integer futuresAccountType, Handler handler) {
        String channel = CH_orderChange;

        HashMap<String, Object> map = new HashMap<>();
        map.put("symbol", symbol);
        map.put("futuresAccountType", futuresAccountType);

        subscribe(channel, map, handler);
    }

    private void subscribe(String channel, Map map, Handler handler) {
        subscribe(channel, null, map, handler);
    }

    private void subscribe(String channel, Integer futuresAccountType, Map map, Handler handler) {
        map.put("action", Subscribe);
        map.put("channel", channel);
        if (futuresAccountType != null) {
            map.put("futuresAccountType", futuresAccountType);
        }
//{"action":"subscribe","channel":"Positions.change","futuresAccountType":1,"symbol":"ZETH_ZUSD"}
        System.out.println("SUB REQ:" + JSON.toJSONString(map));
        this.webSocket.send(JSON.toJSONString(map));
        this.Handlers.put(channel, handler);
    }

    public void unSubscribe(String channel, Integer futuresAccountType) {
        HashMap<String, String> map = new HashMap<>();
        map.put("action", "unSubscribe");
        map.put("channel", channel);
        map.put("futuresAccountType", futuresAccountType.toString());

        this.webSocket.send(JSON.toJSONString(map));
        this.Handlers.remove(channel);
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        super.onOpen(webSocket, response);
        System.out.println("onOpen:" + response.toString());
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        super.onMessage(webSocket, text);

        if (text.contains(PONG)) {
            //do nothing
            return;
        }
        if (text.contains(LOGIN)) {
            System.out.println(text);
            return;
        }
        handle(text);
    }

    private void handle(String text) {
        System.out.println("onMessage:" + text);
//{"channel":"Positions.change",
// "data":{"userId":"6837964014404825088","marketId":"108","marketName":"ZETH_ZUSD",
// "side":0,"leverage":15,"amount":"0.1","avgPrice":"3937.12","liquidatePrice":"4197.05",
// "margin":"28.09151251","marginMode":1,"positionsMode":2,"status":1,"unrealizedPnl":"-15.431",
// "marginBalance":"12.66051251","maintainMargin":"2.045715","marginRate":"0.161582","liquidateLevel":1,
// "nominalValue":"393.753","freezeAmount":"0","freezeList":[{"currencyName":"zusd","currencyId":"9","amount":"28.09151251",
// "modifyTime":"1640271949792"}],"autoLightenRatio":"0","originAppendAmount":"0","appendAmount":"0","marginAppendCount":0,
// "lastAppendAmount":"0","returnRate":"-0.5879","originId":"6879799200100591651","bankruptcyPrice":"4218.03",
// "modifyTime":"1640325002507","refreshType":"Timer","contractType":1,"id":"6838307095813761089","createTime":"1640271949792",
// "extend":{}}}
        WSResponse wsResponse = JSON.parseObject(text, WSResponse.class);
        String channel = wsResponse.getChannel();
        Handler handler = this.Handlers.get(channel);
        if (handler == null) {
            return;
        }

        if (wsResponse.getErrorMsg() != null) {
            handler.handleError(channel, wsResponse.getErrorCode(), wsResponse.getErrorMsg());
        } else {
            handler.handleData(channel, wsResponse.getData().toString());
        }
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        super.onClosing(webSocket, code, reason);
        System.out.println("onClosing" + code + reason);
    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {
        super.onClosed(webSocket, code, reason);
        System.out.println("onClosed" + code + reason);
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        super.onFailure(webSocket, t, response);
        System.out.println("onFailure" + response);
        System.out.println(t);
        System.out.println(t.toString());
    }
}
