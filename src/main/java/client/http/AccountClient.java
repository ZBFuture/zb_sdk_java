package client.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import entity.HttpResponse;
import entity.request.OrderRequest;
import entity.response.*;
import exception.SystemCode;
import exception.ZbException;
import okhttp3.Headers;
import okhttp3.Request;
import org.apache.commons.lang3.StringUtils;
import sign.DateLocalUtil;
import sign.EncryDigestUtil;
import sign.HmacSHA256Base64Utils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountClient extends HttpClient {
    private String ApiKey;
    private String SecretKey;   //网站中的原始SecretKey字符串
    private String SecretKeySha1;//对SecretKey进行sha1加密后的字符串

    private String basePath = "/Server/api/v2";
//    private String gwUrl = endpoint + basePath;

    Headers.Builder headersBuilder = new Headers.Builder();

    public AccountClient(String endpoint, String apiKey, String secretKey) {
        super(endpoint);
        this.ApiKey = apiKey;
        this.SecretKey = secretKey;
        this.SecretKeySha1 = EncryDigestUtil.digest(this.SecretKey);

        headersBuilder.add("ZB-APIKEY", this.ApiKey);
    }

    /**
     * 仅用于用户私有数据
     */
    private Request buildRequest(String path, HashMap<String, Object> queryPara, HashMap<String, Object> bodyPara) {
        Request.Builder builder = new Request.Builder();

        UrlParamsBuilder urlParamsBuilder = UrlParamsBuilder.build();
        Map<String, String> para;
        if (queryPara != null) {
            para = mapObject2String(queryPara);
            Integer futuresAccountType = Integer.valueOf(para.get("futuresAccountType"));
            path = basePath + path;
            if (futuresAccountType != null) {
                if (futuresAccountType == 2)// Q本位合约
                    path = "/qc" + path;
            } else {
                String symbol = para.get("symbol");
                if (StringUtils.isNotBlank(symbol)) {
                    String[] currencies = symbol.split("_");
                    if (!currencies[1].equalsIgnoreCase("USDT") && !currencies[1].equalsIgnoreCase("ZUSD")) {
                        path = "/" + currencies[1].toLowerCase() + path;
                    }
                }
            }

            buildHeader("GET", path, para);
            para.forEach((k, v) -> urlParamsBuilder.putToUrl(k, v));
            builder.url(endpoint + path + urlParamsBuilder.buildUrl());
        }

        if (bodyPara != null) {
            para = mapObject2String(bodyPara);
            Integer futuresAccountType = Integer.valueOf(para.get("futuresAccountType"));
            path = basePath + path;
            if (futuresAccountType != null) {
                if (futuresAccountType == 2)// Q本位合约
                    path = "/qc" + path;
            } else {
                String symbol = para.get("symbol");
                if (StringUtils.isNotBlank(symbol)) {
                    String[] currencies = symbol.split("_");
                    if (!currencies[1].equalsIgnoreCase("USDT") && !currencies[1].equalsIgnoreCase("ZUSD")) {
                        path = "/" + currencies[1].toLowerCase() + path;
                    }
                }
            }

            buildHeader("POST", path, para);
            headersBuilder.add("Content-Type", "application/json");

            para.forEach((k, v) -> urlParamsBuilder.putToPost(k, v));
            builder.url(endpoint + path);
            builder.post(urlParamsBuilder.buildPostBody());
        }
        if (queryPara == null && bodyPara == null) {
            path = basePath + path;
            buildHeader("GET", path, null);
            builder.url(endpoint + path);
        }
        builder.headers(headersBuilder.build());

        return builder.build();
    }

    private Map<String, String> mapObject2String(HashMap<String, Object> para) {
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

    private void buildHeader(String method, String path, Map para) {
        try {
            String ts = DateLocalUtil.toUTC();
            String sign = HmacSHA256Base64Utils.sign(ts, method, path, para, this.ApiKey, this.SecretKeySha1);
            headersBuilder.set("ZB-TIMESTAMP", ts);
            headersBuilder.set("ZB-SIGN", sign);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ZbException(SystemCode.RT_ERROR.getCode(), e.toString());
        }
    }

    public GetAccountResult getAccount(String convertUnit, Integer futuresAccountType) {
        HashMap para = new HashMap();
        para.put("convertUnit", convertUnit);
        para.put("futuresAccountType", futuresAccountType);

        String path = "/Fund/getAccount";
        Request request = buildRequest(path, para, null);
        System.out.println(request.toString());

        HttpResponse<GetAccountResult> response = call(request, new TypeReference<>() {
        });

        return response.checkAndReturn();
    }

    public List<BalanceItem> balance(String currency, Integer futuresAccountType) {
        HashMap para = new HashMap();
        if (currency != null) {
            para.put("currency", currency);
        }
        para.put("futuresAccountType", futuresAccountType);

        String path = "/Fund/balance";
        Request request = buildRequest(path, para, null);
        System.out.println(request.toString());

        HttpResponse<List<BalanceItem>> response = call(request, new TypeReference<>() {
        });

        return response.checkAndReturn();
    }

    public PageVo<BillItemResult> getBill(String currency, Integer futuresAccountType, Integer type, Integer pageNum, Integer pageSize, Long startTime, Long endTime) {
        HashMap para = new HashMap();
        if (currency != null) {
            para.put("currency", currency);
        }
        if (type != null) {
            para.put("billType", type);
        }
        if (pageNum != null) {
            para.put("pageNum", pageNum);
        }
        if (pageSize != null) {
            para.put("pageSize", pageSize);
        }
        if (startTime != null) {
            para.put("startTime", startTime);
        }
        if (endTime != null) {
            para.put("endTime", endTime);
        }
        para.put("futuresAccountType", futuresAccountType);

        String path = "/Fund/getBill";
        Request request = buildRequest(path, para, null);
        System.out.println(request.toString());

        HttpResponse<PageVo<BillItemResult>> response = call(request, new TypeReference<>() {
        });

        return response.checkAndReturn();
    }

    public List<BillTypeListItem> getBillTypeList() {
        String path = "/Fund/getBillTypeList";
        Request request = buildRequest(path, null, null);
        System.out.println(request.toString());

        HttpResponse<List<BillTypeListItem>> response = call(request, new TypeReference<>() {
        });

        return response.checkAndReturn();
    }

    public List<PositionResult> getPositions(String symbol, Integer side, Integer futuresAccountType) {
        HashMap para = new HashMap();
        para.put("symbol", symbol);
        para.put("futuresAccountType", futuresAccountType);
        if (side != null) {
            para.put("side", side);
        }

        String path = "/Positions/getPositions";
        Request request = buildRequest(path, para, null);
        System.out.println(request.toString());

        HttpResponse<List<PositionResult>> response = call(request, new TypeReference<>() {
        });

        return response.checkAndReturn();
    }

    public MarginInfoResult marginInfo(Long positionsId, Integer futuresAccountType) {
        HashMap para = new HashMap();
        para.put("futuresAccountType", futuresAccountType);
        if (positionsId != null) {
            para.put("positionsId", positionsId);
        }

        String path = "/Positions/marginInfo";
        Request request = buildRequest(path, para, null);
        System.out.println(request.toString());

        HttpResponse<MarginInfoResult> response = call(request, new TypeReference<>() {
        });

        return response.checkAndReturn();
    }

    public NominalValueResult getNominalValue(String symbol, Integer side, Integer futuresAccountType) {
        HashMap para = new HashMap();
        para.put("symbol", symbol);
        para.put("futuresAccountType", futuresAccountType);
        para.put("side", side);

        String path = "/Positions/getNominalValue";
        Request request = buildRequest(path, para, null);
        System.out.println(request.toString());

        HttpResponse<NominalValueResult> response = call(request, new TypeReference<>() {
        });

        return response.checkAndReturn();
    }

    public PositionsSettingResult setLeverage(String symbol, Integer futuresAccountType, Integer leverage) {
        HashMap bodyPara = new HashMap();
        bodyPara.put("symbol", symbol);
        bodyPara.put("futuresAccountType", futuresAccountType);
        bodyPara.put("leverage", leverage);

        String path = "/setting/setLeverage";
        Request request = buildRequest(path, null, bodyPara);
        System.out.println(request.toString());

        HttpResponse<String> response = call(request, new TypeReference<>() {
        });

        String data = response.checkAndReturn();
        return JSON.parseObject(data, PositionsSettingResult.class);
    }

    public PositionsSettingResult setPositionsMode(String symbol, Integer futuresAccountType, Integer positionsMode) {
        HashMap bodyPara = new HashMap();
        bodyPara.put("symbol", symbol);
        bodyPara.put("futuresAccountType", futuresAccountType);
        bodyPara.put("positionsMode", positionsMode);

        String path = "/setting/setPositionsMode";
        Request request = buildRequest(path, null, bodyPara);
        System.out.println(request.toString());

        HttpResponse<PositionsSettingResult> response = call(request, new TypeReference<>() {
        });

        return response.checkAndReturn();
    }

    public PositionsSettingResult setMarginMode(String symbol, Integer futuresAccountType, Integer marginMode) {
        HashMap bodyPara = new HashMap();
        bodyPara.put("symbol", symbol);
        bodyPara.put("futuresAccountType", futuresAccountType);
        bodyPara.put("marginMode", marginMode);

        String path = "/setting/setMarginMode";
        Request request = buildRequest(path, null, bodyPara);
        System.out.println(request.toString());

        HttpResponse<PositionsSettingResult> response = call(request, new TypeReference<>() {
        });

        return response.checkAndReturn();
    }

    public UpdateMarginResult updateMargin(Long positionsId, Integer futuresAccountType, BigDecimal amount, Integer type) {
        HashMap bodyPara = new HashMap();
        bodyPara.put("positionsId", positionsId);
        bodyPara.put("futuresAccountType", futuresAccountType);
        bodyPara.put("amount", amount);
        bodyPara.put("type", type);

        String path = "/Positions/updateMargin";
        Request request = buildRequest(path, null, bodyPara);
        System.out.println(request.toString());

        HttpResponse<UpdateMarginResult> response = call(request, new TypeReference<>() {
        });

        return response.checkAndReturn();
    }

    public PageVo<OrderResult> getUndoneOrders(String symbol, String pageNum, String pageSize) {
        HashMap para = new HashMap();
        para.put("symbol", symbol);
        if (pageNum != null) {
            para.put("pageNum", pageNum);
        }
        if (pageSize != null) {
            para.put("pageSize", pageSize);
        }

        String path = "/trade/getUndoneOrders";
        Request request = buildRequest(path, para, null);
        System.out.println(request.toString());

        HttpResponse<PageVo<OrderResult>> response = call(request, new TypeReference<>() {
        });

        return response.checkAndReturn();
    }

    public PageVo<OrderResult> getAllOrders(String symbol, Integer pageNum, Integer pageSize, Integer startTime, Integer endTime) {
        HashMap para = new HashMap();
        para.put("symbol", symbol);
        if (pageNum != null) {
            para.put("pageNum", pageNum);
        }
        if (pageSize != null) {
            para.put("pageSize", pageSize);
        }
        if (startTime != null) {
            para.put("startTime", startTime);
        }
        if (endTime != null) {
            para.put("endTime", endTime);
        }

        String path = "/trade/getAllOrders";
        Request request = buildRequest(path, para, null);
        System.out.println(request.toString());

        HttpResponse<PageVo<OrderResult>> response = call(request, new TypeReference<>() {
        });

        return response.checkAndReturn();
    }

    public OrderResult getOrder(String symbol, Long orderId, String clientOrderId) {
        HashMap para = new HashMap();
        para.put("symbol", symbol);
        if (orderId != null) {
            para.put("orderId", orderId);
        }
        if (clientOrderId != null) {
            para.put("clientOrderId", clientOrderId);
        }

        String path = "/trade/getOrder";
        Request request = buildRequest(path, para, null);
        System.out.println(request.toString());

        HttpResponse<OrderResult> response = call(request, new TypeReference<>() {
        });

        return response.checkAndReturn();
    }

    public List<TradeResult> getTradeList(String symbol, Long orderId, Integer pageNum, Integer pageSize) {
        HashMap para = new HashMap();
        para.put("symbol", symbol);
        para.put("orderId", orderId);
        if (pageNum != null) {
            para.put("pageNum", pageNum);
        }
        if (pageSize != null) {
            para.put("pageSize", pageSize);
        }

        String path = "/trade/getTradeList";
        Request request = buildRequest(path, para, null);
        System.out.println(request.toString());

        HttpResponse<List<TradeResult>> response = call(request, new TypeReference<>() {
        });

        return response.checkAndReturn();
    }

    public PageVo<TradeResult> tradeHistory(String symbol, Integer pageNum, Integer pageSize, Long startTime, Long endTime) {
        HashMap para = new HashMap();
        para.put("symbol", symbol);
        if (pageNum != null) {
            para.put("pageNum", pageNum);
        }
        if (pageSize != null) {
            para.put("pageSize", pageSize);
        }
        if (startTime != null) {
            para.put("startTime", startTime);
        }
        if (endTime != null) {
            para.put("endTime", endTime);
        }

        String path = "/trade/tradeHistory";
        Request request = buildRequest(path, para, null);
        System.out.println(request.toString());

        HttpResponse<PageVo<TradeResult>> response = call(request, new TypeReference<>() {
        });

        return response.checkAndReturn();
    }

    public Long order(String symbol, BigDecimal price, BigDecimal amount, Integer action, Integer entrustType, Integer side) {
        HashMap bodyPara = new HashMap();
        bodyPara.put("symbol", symbol);
        bodyPara.put("price", price);
        bodyPara.put("amount", amount);
        bodyPara.put("action", action);
        bodyPara.put("entrustType", entrustType);
        bodyPara.put("side", side);

        String path = "/trade/order";
        Request request = buildRequest(path, null, bodyPara);
        System.out.println(request.toString());

        HttpResponse<Long> response = call(request, new TypeReference<>() {
        });

        return response.checkAndReturn();
    }

    public List<BatchOrderItem> batchOrder(List<OrderRequest> list) {
        HashMap bodyPara = new HashMap();
        bodyPara.put("orderDatas", JSON.toJSONString(list));

        String path = "/trade/batchOrder";
        Request request = buildRequest(path, null, bodyPara);
        System.out.println(request.toString());

        HttpResponse<List<BatchOrderItem>> response = call(request, new TypeReference<>() {
        });

        return response.checkAndReturn();
    }

    public String cancelOrder(String symbol, Long orderId, String clientOrderId) {
        HashMap bodyPara = new HashMap();
        bodyPara.put("symbol", symbol);
        if (orderId != null) {
            bodyPara.put("orderId", orderId);
        }
        if (clientOrderId != null) {
            bodyPara.put("clientOrderId", clientOrderId);
        }

        String path = "/trade/cancelOrder";
        Request request = buildRequest(path, null, bodyPara);
        System.out.println(request.toString());

        HttpResponse<String> response = call(request, new TypeReference<>() {
        });

        return response.checkAndReturn();
    }

    public BatchCancelOrderItem batchCancelOrder(String symbol, List<Long> orderIds, List<String> clientOrderIds) {
        HashMap bodyPara = new HashMap();
        bodyPara.put("symbol", symbol);
        if (orderIds != null) {
            bodyPara.put("orderIds", orderIds);
        }
        if (clientOrderIds != null) {
            bodyPara.put("clientOrderIds", clientOrderIds);
        }

        String path = "/trade/batchCancelOrder";
        Request request = buildRequest(path, null, bodyPara);
        System.out.println(request.toString());

        HttpResponse<BatchCancelOrderItem> response = call(request, new TypeReference<>() {
        });

        return response.checkAndReturn();
    }

    public List<String> cancelAllOrders(String symbol) {
        HashMap bodyPara = new HashMap();
        bodyPara.put("symbol", symbol);

        String path = "/trade/cancelAllOrders";
        Request request = buildRequest(path, null, bodyPara);
        System.out.println(request.toString());

        HttpResponse<List<String>> response = call(request, new TypeReference<>() {
        });

        return response.checkAndReturn();
    }

    public String orderAlgo(String symbol, Integer side, Integer orderType, BigDecimal amount, BigDecimal triggerPrice, BigDecimal algoPrice, Integer priceType, Integer bizType) {
        HashMap bodyPara = new HashMap();
        bodyPara.put("symbol", symbol);
        bodyPara.put("side", side);
        bodyPara.put("orderType", orderType);
        bodyPara.put("amount", amount);
        if (triggerPrice != null) {
            bodyPara.put("triggerPrice", triggerPrice);
        }
        if (algoPrice != null) {
            bodyPara.put("algoPrice", algoPrice);
        }
        if (priceType != null) {
            bodyPara.put("priceType", priceType);
        }
        if (bizType != null) {
            bodyPara.put("bizType", bizType);
        }
        bodyPara.put("sourceType", 4);//4:api reset

        String path = "/trade/orderAlgo";
        Request request = buildRequest(path, null, bodyPara);
        System.out.println(request.toString());

        HttpResponse<String> response = call(request, new TypeReference<>() {
        });

        return response.checkAndReturn();
    }

    public List<BatchCancelOrderItem> cancelAlgos(String symbol, List<String> ids) {
        HashMap bodyPara = new HashMap();
        bodyPara.put("symbol", symbol);
        bodyPara.put("ids", ids);

        String path = "/trade/cancelAlgos";
        Request request = buildRequest(path, null, bodyPara);
        System.out.println(request.toString());

        HttpResponse<List<BatchCancelOrderItem>> response = call(request, new TypeReference<>() {
        });

        return response.checkAndReturn();
    }

    /**
     * @param side      1:开多(计划委托) 2:开空(计划委托) 3:平多(止盈止损) 4:平空(止盈止损)
     * @param orderType 1：计划委托 2：止盈止损
     * @param bizType   针对止盈止损 1:止盈 2:止损
     */
    public PageVo<OrderAlgo> getOrderAlgos(String symbol, Integer side, Integer orderType, Integer bizType, Integer status, Integer pageNum, Integer pageSize, Long startTime, Long endTime) {
        HashMap para = new HashMap();
        para.put("symbol", symbol);
        if (side != null) {
            para.put("side", side);
        }
        if (orderType != null) {
            para.put("orderType", orderType);
        }
        if (bizType != null) {
            para.put("bizType", bizType);
        }
        if (status != null) {
            para.put("status", status);
        }
        if (pageNum != null) {
            para.put("pageNum", pageNum);
        }
        if (pageSize != null) {
            para.put("pageSize", pageSize);
        }
        if (startTime != null) {
            para.put("startTime", startTime);
        }
        if (endTime != null) {
            para.put("endTime", endTime);
        }

        String path = "/trade/getOrderAlgos";
        Request request = buildRequest(path, para, null);
        System.out.println(request.toString());

        HttpResponse<PageVo<OrderAlgo>> response = call(request, new TypeReference<>() {
        });

        return response.checkAndReturn();
    }

    public String updateAppendUSDValue(Long positionsId, BigDecimal maxAdditionalUSDValue, Integer futuresAccountType) {
        HashMap bodyPara = new HashMap();
        if (positionsId != null) {
            bodyPara.put("positionsId", positionsId);
        }
        bodyPara.put("maxAdditionalUSDValue", maxAdditionalUSDValue);
        bodyPara.put("futuresAccountType", futuresAccountType);

        String path = "/Positions/updateAppendUSDValue";
        Request request = buildRequest(path, null, bodyPara);
        System.out.println(request.toString());

        HttpResponse<String> response = call(request, new TypeReference<>() {
        });

        return response.checkAndReturn();
    }

    /**
     * @param marginCoins 配置的按顺序冻结的保证金，如 eth,usdt,qc
     */
    public PositionsSettingResult setMarginCoins(String symbol, String marginCoins, Integer futuresAccountType) {
        HashMap bodyPara = new HashMap();
        bodyPara.put("symbol", symbol);
        bodyPara.put("marginCoins", marginCoins);
        bodyPara.put("futuresAccountType", futuresAccountType);

        String path = "/Positions/setMarginCoins";
        Request request = buildRequest(path, null, bodyPara);
        System.out.println(request.toString());

        HttpResponse<PositionsSettingResult> response = call(request, new TypeReference<>() {
        });

        return response.checkAndReturn();
    }
}
