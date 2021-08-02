package client.http;

import com.alibaba.fastjson.TypeReference;
import entity.HttpResponse;
import entity.response.*;
import okhttp3.Request;

import java.util.List;
import java.util.Map;


public class MarketClient extends HttpClient {
    public MarketClient(String endpoint) {
        super(endpoint);
    }

    public List<MarketConfig> marketList(Integer futuresAccountType) {
        UrlParamsBuilder build = UrlParamsBuilder.build();
        if (futuresAccountType != null) {
            build.putToUrl("futuresAccountType", futuresAccountType);
        }

        Request.Builder builder = new Request.Builder();
        builder.url(endpoint + "/Server/api/v2/config/marketList" + build.buildUrl());
        Request request = builder.build();

        HttpResponse<List<MarketConfig>> response = call(request, new TypeReference<>() {
        });
        return response.checkAndReturn();
    }

    public Depth WholeDepth(String symbol, Integer size) {
        UrlParamsBuilder build = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol)
                .putToUrl("size", size);

        Request.Builder builder = new Request.Builder();
        builder.url(endpoint + "/api/public/v1/depth" + build.buildUrl());
        Request request = builder.build();

        HttpResponse<Depth> response = call(request, new TypeReference<>() {
        });
        return response.checkAndReturn();
    }

    public List<List> kline(String symbol, String period, Integer size) {
        return kline("kline", symbol, period, size);
    }

    public List<List> markKline(String symbol, String period, Integer size) {
        return kline("markKline", symbol, period, size);
    }

    public List<List> indexKline(String symbol, String period, Integer size) {
        return kline("indexKline", symbol, period, size);
    }

    public List<List> trade(String symbol, Integer size) {
        UrlParamsBuilder build = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol)
                .putToUrl("size", size);

        Request.Builder builder = new Request.Builder();
        builder.url(endpoint + "/api/public/v1/trade" + build.buildUrl());
        Request request = builder.build();

        HttpResponse<List<List>> response = call(request, new TypeReference<>() {
        });
        return response.checkAndReturn();
    }

    public Map ticker(String symbol) {
        UrlParamsBuilder build = UrlParamsBuilder.build();
        if (symbol != null) {
            build.putToUrl("symbol", symbol);
        }

        Request.Builder builder = new Request.Builder();
        builder.url(endpoint + "/api/public/v1/ticker" + build.buildUrl());
        Request request = builder.build();

        HttpResponse<Map> response = call(request, new TypeReference<>() {
        });
        return response.checkAndReturn();
    }

    public String markPrice(String symbol) {
        return price("markPrice", symbol);
    }

    public String indexPrice(String symbol) {
        return price("indexPrice", symbol);
    }

    public FundingRate fundingRate(String symbol) {
        UrlParamsBuilder build = UrlParamsBuilder.build().putToUrl("symbol", symbol);

        Request.Builder builder = new Request.Builder();
        builder.url(endpoint + "/api/public/v1/fundingRate" + build.buildUrl());
        Request request = builder.build();

        HttpResponse<FundingRate> response = call(request, new TypeReference<>() {
        });
        return response.checkAndReturn();
    }

    public String price(String type, String symbol) {
        UrlParamsBuilder build = UrlParamsBuilder.build().putToUrl("symbol", symbol);

        Request.Builder builder = new Request.Builder();
        builder.url(endpoint + "/api/public/v1/" + type + build.buildUrl());
        Request request = builder.build();

        HttpResponse<String> response = call(request, new TypeReference<>() {
        });
        return response.checkAndReturn();
    }

    public List<List> kline(String type, String symbol, String period, Integer size) {
        UrlParamsBuilder build = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol)
                .putToUrl("period", period)
                .putToUrl("size", size.toString());

        Request.Builder builder = new Request.Builder();
        builder.url(endpoint + "/api/public/v1/" + type + build.buildUrl());
        Request request = builder.build();

        HttpResponse<List<List>> response = call(request, new TypeReference<>() {
        });
        return response.checkAndReturn();
    }


    public List<PremiumIndexItem> premiumIndex(String symbol) {
        UrlParamsBuilder build = UrlParamsBuilder.build();
        if (symbol != null) {
            build.putToUrl("symbol", symbol);
        }

        Request.Builder builder = new Request.Builder();
        builder.url(endpoint + "/Server/api/v2/premiumIndex" + build.buildUrl());
        Request request = builder.build();

        HttpResponse<List<PremiumIndexItem>> response = call(request, new TypeReference<>() {
        });
        return response.checkAndReturn();
    }

    public List<FundingRateItem> fundingRateHistory(String symbol, Long startTime, Long endTime, Integer limit) {
        UrlParamsBuilder build = UrlParamsBuilder.build();
        if (symbol != null) {
            build.putToUrl("symbol", symbol);
        }
        if (startTime != null) {
            build.putToUrl("startTime", startTime);
        }
        if (endTime != null) {
            build.putToUrl("endTime", endTime);
        }
        if (limit != null) {
            build.putToUrl("limit", limit);
        }

        Request.Builder builder = new Request.Builder();
        builder.url(endpoint + "/api/public/v1/fundingRate" + build.buildUrl());
        Request request = builder.build();

        HttpResponse<List<FundingRateItem>> response = call(request, new TypeReference<>() {
        });
        return response.checkAndReturn();
    }

    public List<AllForceOrdersItem> allForceOrders(String symbol, Long startTime, Long endTime, Integer limit) {
        UrlParamsBuilder build = UrlParamsBuilder.build();
        build.putToUrl("symbol", symbol);

        if (startTime != null) {
            build.putToUrl("startTime", startTime);
        }
        if (endTime != null) {
            build.putToUrl("endTime", endTime);
        }
        if (limit != null) {
            build.putToUrl("limit", limit);
        }

        Request.Builder builder = new Request.Builder();
        builder.url(endpoint + "/Server/api/v2/allForceOrders" + build.buildUrl());
        Request request = builder.build();

        HttpResponse<List<AllForceOrdersItem>> response = call(request, new TypeReference<>() {
        });
        return response.checkAndReturn();
    }

    //大户账户数多空比
    //period: "5m","15m","30m","1h","2h","4h","6h","12h","1d"
    public List<TopLongShortAccountRatioItem> topLongShortAccountRatio(String symbol, String period, Long startTime, Long endTime, Integer limit) {
        UrlParamsBuilder build = UrlParamsBuilder.build();
        build.putToUrl("symbol", symbol);
        build.putToUrl("period", period);

        if (startTime != null) {
            build.putToUrl("startTime", startTime);
        }
        if (endTime != null) {
            build.putToUrl("endTime", endTime);
        }
        if (limit != null) {
            build.putToUrl("limit", limit);
        }

        Request.Builder builder = new Request.Builder();
        builder.url(endpoint + "/Server/api/v2/data/topLongShortAccountRatio" + build.buildUrl());
        Request request = builder.build();

        HttpResponse<List<TopLongShortAccountRatioItem>> response = call(request, new TypeReference<>() {
        });
        return response.checkAndReturn();
    }

    //大户持仓量多空比
    //period: "5m","15m","30m","1h","2h","4h","6h","12h","1d"
    public List<TopLongShortPositionRatioItem> topLongShortPositionRatio(String symbol, String period, Long startTime, Long endTime, Integer limit) {
        UrlParamsBuilder build = UrlParamsBuilder.build();
        build.putToUrl("symbol", symbol);
        build.putToUrl("period", period);

        if (startTime != null) {
            build.putToUrl("startTime", startTime);
        }
        if (endTime != null) {
            build.putToUrl("endTime", endTime);
        }
        if (limit != null) {
            build.putToUrl("limit", limit);
        }

        Request.Builder builder = new Request.Builder();
        builder.url(endpoint + "/Server/api/v2/data/topLongShortPositionRatio" + build.buildUrl());
        Request request = builder.build();

        HttpResponse<List<TopLongShortPositionRatioItem>> response = call(request, new TypeReference<>() {
        });
        return response.checkAndReturn();
    }

    //zb现货兑换价格
    public String spotPrice(String side, String symbol) {
        UrlParamsBuilder build = UrlParamsBuilder.build();
        build.putToUrl("symbol", symbol);
        build.putToUrl("side", side);

        Request.Builder builder = new Request.Builder();
        builder.url(endpoint + "/api/public/v1/spotPrice" + build.buildUrl());
        Request request = builder.build();

        HttpResponse<String> response = call(request, new TypeReference<>() {
        });
        return response.checkAndReturn();
    }
}
