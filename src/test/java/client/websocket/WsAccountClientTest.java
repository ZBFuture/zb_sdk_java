package client.websocket;

import constant.constant;
import handler.account.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

class WsAccountClientTest {
    @Test
    void Account() throws InterruptedException {
        //online env
//        String endpoint = "wss://futures.zb.land/ws/private/api/v2";
//        String apiKey = "3576549f-e1ce-4317-b83e-48f192cf9e23";
//        String secretKey = "6ee95f06-0365-4e09-a9db-b38d2d7111b0";

        //test env
        String endpoint = "ws://fapi.zb.com/ws/private/api/v2";
        String apiKey = "9807581e-992e-41ca-8fa4-639fbf1c939f";
        String secretKey = "a7a15b46-eb08-431e-81e4-096bd12e2a48";

        WsAccountClient wsAccountClient = new WsAccountClient(endpoint, apiKey, secretKey);

        String symbol = "ZETH_ZUSD";
        String convertUnit = constant.UNIT_USD;
        String currency = constant.CURRENCY_USDT;
        Integer futuresAccountType = constant.BASE_USDT;

        wsAccountClient.login();

        //fund
        /*wsAccountClient.getAccount(convertUnit, futuresAccountType, new GetAccountHandler());
        wsAccountClient.getBill(currency, futuresAccountType, null, null, null, null, null, new GetBillHandler());
        wsAccountClient.balance(currency, futuresAccountType, new BalanceHandler());
        wsAccountClient.assetInfo(convertUnit, futuresAccountType, new AssetInfoHandler());
        wsAccountClient.SubscribeFundChange(currency, futuresAccountType, new FundChangeHandler());
        wsAccountClient.SubscribeAssetChange(convertUnit, futuresAccountType, new AssetChangeHandler());

        //positions
        wsAccountClient.getPositions(symbol, futuresAccountType, new GetPositionsHandler());
        //wsAccountClient.marginInfo(6788681764186368005L, futuresAccountType, new MarginInfoHandler());
        wsAccountClient.assetInfo(convertUnit, futuresAccountType, new AssetInfoHandler());
        wsAccountClient.getNominalValue(symbol, futuresAccountType, constant.SIDE_OPEN_LONG, new GetNominalValueHandler());
        wsAccountClient.setLeverage(symbol, futuresAccountType, 10, new SetLeverageHandler());
        wsAccountClient.setMarginMode(symbol, futuresAccountType, constant.Isolated, new SetMarginModeHandler());
        wsAccountClient.setPositionsMode(symbol, futuresAccountType, constant.OneDirection, new SetPositionsModeHandler());*/
        wsAccountClient.SubscribePositionsChange(symbol, futuresAccountType, new PositionsChangeHandler());

        //trade
       /* wsAccountClient.order(symbol, BigDecimal.valueOf(2022.1), BigDecimal.ONE, constant.LIMIT, constant.LIMIT, constant.SIDE_OPEN_LONG, new OrderHandler());
        wsAccountClient.getUndoneOrders(symbol, null, null, new GetUndoneOrdersHandler());
        wsAccountClient.getAllOrders(symbol, null, null, null, null, new GetAllOrdersHandler());
        Long orderId = 6791270145898586112L;
        wsAccountClient.getOrder(symbol, orderId, null, new GetOrderHandler());
        wsAccountClient.getTradeList(symbol, orderId, new GetTradeListHandler());
        wsAccountClient.tradeHistory(symbol, new TradeHistoryHandler());
        wsAccountClient.cancelOrder(symbol, orderId, null, new CancelOrderHandler());

        ArrayList<Long> orders = new ArrayList<>();
        orders.add(orderId);
        wsAccountClient.batchCancelOrder(symbol, orders, null, new BatchCancelOrderHandler());

        wsAccountClient.cancelAllOrders(symbol, new CancelAllOrderHandler());
*/
        Thread.sleep(3600 * 1000);
    }
}