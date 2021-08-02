package client.websocket;

import handler.market.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;


public class WsMarketClientTest {
    @Test
    void WsMarket() throws InterruptedException {
        //online env
//        String endpoint = "wss://futures.zb.land/ws/public/v1";

        //test env
        String endpoint = "ws://ttfutures.zb.com/ws/public/v1";

        WsMarketClient wsMarketClient = new WsMarketClient(endpoint);
        String symbol = "ETH_USDT";
        wsMarketClient.SubscribeDepthWhole(symbol, BigDecimal.valueOf(0.01), 10, new DepthHandler());
        wsMarketClient.SubscribeDepth(symbol, BigDecimal.valueOf(0.01), 10, new DepthHandler());
        wsMarketClient.SubscribeKline(symbol, "1M", 10, new DListHandler());
        wsMarketClient.SubscribeTrade(symbol, 10, new DListHandler());
        wsMarketClient.SubscribeTicker(symbol, new TickerHandler());
        wsMarketClient.SubscribeAllTicker(new AllTickerHandler());
        wsMarketClient.SubscribeMarkPrice(symbol, new PriceHandler());
        wsMarketClient.SubscribeIndexPrice(symbol, new PriceHandler());

        wsMarketClient.SubscribeAllMarkPrice(new AllPriceHandler());
        wsMarketClient.SubscribeAllIndexPrice(new AllPriceHandler());
        wsMarketClient.SubscribeMarkKline(symbol, "1M", new DListHandler());
        wsMarketClient.SubscribeIndexKline(symbol, "1M", new DListHandler());
        wsMarketClient.SubscribeFundingRate(symbol, new FundingRateHandler());
        wsMarketClient.SubscribeAsksSpotPrice(symbol, new PriceHandler());
        wsMarketClient.SubscribeBidsSpotPrice(symbol, new PriceHandler());

        Thread.sleep(3600 * 1000);
    }
}
