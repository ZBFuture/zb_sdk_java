package client.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import entity.HttpResponse;
import entity.response.*;
import okhttp3.Request;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
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
        String contractType = "";
        if (futuresAccountType == 2)
            contractType = "/qc";
        builder.url(endpoint + contractType + "/Server/api/v2/config/marketList" + build.buildUrl());
        Request request = builder.build();

        HttpResponse<List<MarketConfig>> response = call(request, new TypeReference<>() {
        });
        return response.checkAndReturn();
    }

    public Depth WholeDepth(String symbol, Integer size) {
        Map<String, Object> param = new HashMap<>();
        param.put("symbol", symbol);
        param.put("size", size);

        Request request = buildRequest("/api/public/v1/depth", param);

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
        Map<String, Object> param = new HashMap<>();
        param.put("symbol", symbol);
        param.put("size", size);

        Request request = buildRequest("/api/public/v1/trade", param);

        HttpResponse<List<List>> response = call(request, new TypeReference<>() {
        });
        return response.checkAndReturn();
    }

    public Map ticker(String symbol) {
        Map<String, Object> param = new HashMap<>();
        if (symbol != null) {
            param.put("symbol", symbol);
        }

        Request request = buildRequest("/api/public/v1/ticker", param);

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
        Map<String, Object> param = new HashMap<>();
        param.put("symbol", symbol);

        Request request = buildRequest("/api/public/v1/fundingRate", param);

        HttpResponse<FundingRate> response = call(request, new TypeReference<>() {
        });
        return response.checkAndReturn();
    }

    public String price(String type, String symbol) {
        Map<String, Object> param = new HashMap<>();
        param.put("symbol", symbol);

        Request request = buildRequest("/api/public/v1/" + type, param);

        HttpResponse<String> response = call(request, new TypeReference<>() {
        });
        return response.checkAndReturn();
    }

    public List<List> kline(String type, String symbol, String period, Integer size) {
        Map<String, Object> param = new HashMap<>();
        param.put("symbol", symbol);
        param.put("period", period);
        param.put("size", size);

        Request request = buildRequest("/api/public/v1/" + type, param);

        HttpResponse<List<List>> response = call(request, new TypeReference<>() {
        });
        return response.checkAndReturn();
    }


    public List<PremiumIndexItem> premiumIndex(String symbol) {
        Map<String, Object> param = new HashMap<>();
        if (symbol != null) {
            param.put("symbol", symbol);
        }

        Request request = buildRequest("/Server/api/v2/premiumIndex", param);

        HttpResponse<List<PremiumIndexItem>> response = call(request, new TypeReference<>() {
        });
        return response.checkAndReturn();
    }

    public List<FundingRateItem> fundingRateHistory(String symbol, Long startTime, Long endTime, Integer limit) {
        Map<String, Object> param = new HashMap<>();
        if (symbol != null) {
            param.put("symbol", symbol);
        }
        if (startTime != null) {
            param.put("startTime", startTime);
        }
        if (endTime != null) {
            param.put("endTime", endTime);
        }
        if (limit != null) {
            param.put("limit", limit);
        }

        Request request = buildRequest("/api/public/v1/fundingRate", param);

        HttpResponse<List<FundingRateItem>> response = call(request, new TypeReference<>() {
        });
        return response.checkAndReturn();
    }

    public List<AllForceOrdersItem> allForceOrders(String symbol, Long startTime, Long endTime, Integer limit) {
        Map<String, Object> param = new HashMap<>();
        param.put("symbol", symbol);

        if (startTime != null) {
            param.put("startTime", startTime);
        }
        if (endTime != null) {
            param.put("endTime", endTime);
        }
        if (limit != null) {
            param.put("limit", limit);
        }

        Request request = buildRequest("/Server/api/v2/allForceOrders", param);

        HttpResponse<List<AllForceOrdersItem>> response = call(request, new TypeReference<>() {
        });
        return response.checkAndReturn();
    }

    //大户账户数多空比
    //period: "5m","15m","30m","1h","2h","4h","6h","12h","1d"
    public List<TopLongShortAccountRatioItem> topLongShortAccountRatio(String symbol, String period, Long startTime, Long endTime, Integer limit) {
        Map<String, Object> param = new HashMap<>();
        param.put("symbol", symbol);
        param.put("symbol", symbol);
        param.put("period", period);

        if (startTime != null) {
            param.put("startTime", startTime);
        }
        if (endTime != null) {
            param.put("endTime", endTime);
        }
        if (limit != null) {
            param.put("limit", limit);
        }

        Request request = buildRequest("/Server/api/v2/data/topLongShortAccountRatio", param);

        HttpResponse<List<TopLongShortAccountRatioItem>> response = call(request, new TypeReference<>() {
        });
        return response.checkAndReturn();
    }

    //大户持仓量多空比
    //period: "5m","15m","30m","1h","2h","4h","6h","12h","1d"
    public List<TopLongShortPositionRatioItem> topLongShortPositionRatio(String symbol, String period, Long startTime, Long endTime, Integer limit) {
        Map<String, Object> param = new HashMap<>();
        param.put("symbol", symbol);
        param.put("period", period);

        if (startTime != null) {
            param.put("startTime", startTime);
        }
        if (endTime != null) {
            param.put("endTime", endTime);
        }
        if (limit != null) {
            param.put("limit", limit);
        }

        Request request = buildRequest("/Server/api/v2/data/topLongShortPositionRatio", param);

        HttpResponse<List<TopLongShortPositionRatioItem>> response = call(request, new TypeReference<>() {
        });
        return response.checkAndReturn();
    }

    //zb现货兑换价格
    public String spotPrice(String side, String symbol) {
        Map<String, Object> param = new HashMap<>();
        param.put("symbol", symbol);
        param.put("side", side);

        Request request = buildRequest("/api/public/v1/spotPrice", param);

        HttpResponse<String> response = call(request, new TypeReference<>() {
        });
        return response.checkAndReturn();
    }

    private Request buildRequest(String path, Map<String, Object> param) {
        Request.Builder builder = new Request.Builder();
        UrlParamsBuilder urlParamsBuilder = UrlParamsBuilder.build();

        String contractType = "";
        String symbol = (String) param.get("symbol");
        if (StringUtils.isNoneBlank(symbol)) {
            String[] currencies = symbol.split("_");
            if (!currencies[1].equalsIgnoreCase("USDT")) {
                contractType = "/" + currencies[1].toLowerCase();
            }
        }

        Map<String, String> map = mapObject2String(param);
        map.forEach((k, v) -> urlParamsBuilder.putToUrl(k, v));
        builder.url(endpoint + contractType + path + urlParamsBuilder.buildUrl());
        return builder.build();
    }

    private Map<String, String> mapObject2String(Map<String, Object> para) {
        Map<String, String> map = new HashMap();
        para.forEach((k, v) -> {
            if (v instanceof String) {
                map.put(k, v.toString());
            } else {
                map.put(k, JSON.toJSONString(v));
            }
        });
        return map;
    }
}
