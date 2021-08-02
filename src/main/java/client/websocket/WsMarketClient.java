package client.websocket;

import com.alibaba.fastjson.JSON;
import entity.WSResponse;
import handler.Handler;
import okhttp3.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class WsMarketClient extends WebSocketListener {
    private final String PONG = "pong";
    private final String Subscribe = "subscribe";

    /**
     * fund channel
     */
    private final String CH_DepthWhole = "DepthWhole";
    private final String CH_Depth = "Depth";
    private final String CH_KLine = "KLine";
    private final String CH_Trade = "Trade";
    private final String CH_Ticker = "Ticker";
    private final String CH_Mark = "mark";
    private final String CH_Index = "index";
    private final String CH_FundingRate = "FundingRate";
    private final String CH_BidsSpotPrice = "bidsSpotPrice";
    private final String CH_AsksSpotPrice = "asksSpotPrice";

    private OkHttpClient httpClient = new OkHttpClient();
    private WebSocket webSocket;

    private HashMap<String, Handler> Handlers = new HashMap<>();

    //SecretKey:网站中的原始SecretKey字符串
    public WsMarketClient(String endpoint) {
        this.webSocket = httpClient.newWebSocket(new Request.Builder().url(endpoint).build(), this);
    }


    public void SubscribeDepthWhole(String symbol, BigDecimal scale, Integer size, Handler handler) {
        HashMap<String, Object> map = new HashMap<>();

        String channel = symbol + "." + CH_DepthWhole;
        if (scale != null) {
            channel = channel + "@" + scale;
        }
        map.put("channel", channel);

        if (size != null) {
            map.put("size", size);
        }

        subscribe(channel, map, handler);
    }

    public void SubscribeDepth(String symbol, BigDecimal scale, Integer size, Handler handler) {
        HashMap<String, Object> map = new HashMap<>();

        String channel = symbol + "." + CH_Depth;
        if (scale != null) {
            channel = channel + "@" + scale;
        }
        map.put("channel", channel);

        if (size != null) {
            map.put("size", size);
        }

        subscribe(channel, map, handler);
    }

    public void SubscribeKline(String symbol, String period, Integer size, Handler handler) {
        HashMap<String, Object> map = new HashMap<>();

        String channel = symbol + "." + CH_KLine + "_" + period;
        map.put("channel", channel);

        if (size != null) {
            map.put("size", size);
        }

        subscribe(channel, map, handler);
    }

    public void SubscribeTrade(String symbol, Integer size, Handler handler) {
        HashMap<String, Object> map = new HashMap<>();

        String channel = symbol + "." + CH_Trade;
        map.put("channel", channel);

        if (size != null) {
            map.put("size", size);
        }

        subscribe(channel, map, handler);
    }

    public void SubscribeTicker(String symbol, Handler handler) {
        HashMap<String, Object> map = new HashMap<>();

        String channel = symbol + "." + CH_Ticker;
        map.put("channel", channel);

        subscribe(channel, map, handler);
    }

    public void SubscribeAllTicker(Handler handler) {
        HashMap<String, Object> map = new HashMap<>();

        String channel = "All" + "." + CH_Ticker;
        map.put("channel", channel);

        subscribe(channel, map, handler);
    }

    public void SubscribeMarkPrice(String symbol, Handler handler) {
        SubscribeMarkIndexPrice(symbol, CH_Mark, handler);
    }

    public void SubscribeIndexPrice(String symbol, Handler handler) {
        SubscribeMarkIndexPrice(symbol, CH_Index, handler);
    }

    public void SubscribeAllMarkPrice(Handler handler) {
        SubscribeMarkIndexPrice("All", CH_Mark, handler);
    }

    public void SubscribeAllIndexPrice(Handler handler) {
        SubscribeMarkIndexPrice("All", CH_Index, handler);
    }

    private void SubscribeMarkIndexPrice(String symbol, String type, Handler handler) {
        HashMap<String, Object> map = new HashMap<>();

        String channel = symbol + "." + type;
        map.put("channel", channel);

        subscribe(channel, map, handler);
    }

    public void SubscribeMarkKline(String symbol, String period, Handler handler) {
        SubscribeMarkIndexKLine(symbol, CH_Mark, period, handler);
    }

    public void SubscribeIndexKline(String symbol, String period, Handler handler) {
        SubscribeMarkIndexKLine(symbol, CH_Index, period, handler);
    }

    private void SubscribeMarkIndexKLine(String symbol, String type, String period, Handler handler) {
        HashMap<String, Object> map = new HashMap<>();

        String channel = symbol + "." + type + "_" + period;
        map.put("channel", channel);

        subscribe(channel, map, handler);
    }

    public void SubscribeFundingRate(String symbol, Handler handler) {
        HashMap<String, Object> map = new HashMap<>();

        String channel = symbol + "." + CH_FundingRate;
        map.put("channel", channel);

        subscribe(channel, map, handler);
    }

    public void SubscribeAsksSpotPrice(String symbol, Handler handler) {
        HashMap<String, Object> map = new HashMap<>();

        String channel = symbol + "." + CH_AsksSpotPrice;
        map.put("channel", channel);

        subscribe(channel, map, handler);
    }

    public void SubscribeBidsSpotPrice(String symbol, Handler handler) {
        HashMap<String, Object> map = new HashMap<>();

        String channel = symbol + "." + CH_BidsSpotPrice;
        map.put("channel", channel);

        subscribe(channel, map, handler);
    }

    private void subscribe(String channel, Map map, Handler handler) {
        map.put("action", Subscribe);

        this.webSocket.send(JSON.toJSONString(map));
        this.Handlers.put(channel, handler);
    }

    public void unSubscribe(String channel) {
        HashMap<String, String> map = new HashMap<>();
        map.put("action", "unSubscribe");
        map.put("channel", channel);

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
        handle(text);
    }

    private void handle(String text) {
        System.out.println(text);

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
        System.out.println("onFailure");
        System.out.println(t.toString());
    }
}
