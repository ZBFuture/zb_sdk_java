package client.http;


import com.alibaba.fastjson.JSON;
import entity.response.Depth;
import entity.response.FundingRate;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

class MarketClientTest {

    @Test
    void market() {
        String endpoint = "https://futures.zb.land";
//        String endpoint = "http://ttfutures.zb.com";
        MarketClient marketClient = new MarketClient(endpoint);
        String symbol = "ETH_USDT";
        Integer size = 10;

        Depth depth = marketClient.WholeDepth(symbol, size);
        System.out.println(JSON.toJSONString(depth));

        List<List> trade = marketClient.trade(symbol, size);
        System.out.println(JSON.toJSONString(trade));

        List<List> kline = marketClient.kline(symbol, "1M", size);
        System.out.println(JSON.toJSONString(kline));

        List<List> markKline = marketClient.markKline(symbol, "1M", size);
        System.out.println(JSON.toJSONString(markKline));

        List<List> indexKline = marketClient.indexKline(symbol, "1M", size);
        System.out.println(JSON.toJSONString(indexKline));

        Map ticker = marketClient.ticker(symbol);
        System.out.println(JSON.toJSONString(ticker));
        ticker = marketClient.ticker(null);
        System.out.println(JSON.toJSONString(ticker));

        FundingRate fundingRate = marketClient.fundingRate(symbol);
        System.out.println(JSON.toJSONString(fundingRate));
    }
}