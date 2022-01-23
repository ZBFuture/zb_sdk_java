package client.http;

import constant.constant;
import entity.request.OrderRequest;
import entity.response.BatchOrderItem;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.List;

class AccountClientTest {

    public static void main(String[] args) {
        System.out.println(11);
    }

    @Test
    void Account() throws UnsupportedEncodingException, CloneNotSupportedException, InvalidKeyException {
        //online env
        String endpoint = "http://fapi.zb.com";
        String apiKey = "9807581e-992e-41ca-8fa4-639fbf1c939f";
        String secretKey = "a7a15b46-eb08-431e-81e4-096bd12e2a48";

        //test env
        /*String endpoint = "http://ttfutures.zb.com";
        String apiKey = "8da1a454-2f07-48e7-b268-72582fb72794";
        String secretKey = "d67355ca-3e20-41fc-8e14-bfdf506f72fc";*/

        String symbol = "ETH_USDT";
        String currency = constant.CURRENCY_USDT;
        Integer futuresAccountType = constant.BASE_USDT;


        AccountClient accountClient = new AccountClient(endpoint, apiKey, secretKey);

        //------fund-------
        System.out.println(accountClient.getAccount("usdt", constant.BASE_USDT).toString());
        System.out.println(accountClient.getBill(currency, constant.BASE_USDT, null, null, null, null, null).toString());
        System.out.println(accountClient.balance(currency, constant.BASE_USDT).toString());
        System.out.println(accountClient.getBillTypeList().toString());

        //------Position-------
        System.out.println(accountClient.getPositions(symbol, constant.SIDE_OPEN_LONG, constant.BASE_USDT).toString());
//        System.out.println(accountClient.marginInfo(6788681764186368005L, constant.BASE_USDT).toString());
        System.out.println(accountClient.getNominalValue(symbol, constant.SIDE_OPEN_LONG, constant.BASE_USDT).toString());
//        System.out.println(accountClient.updateMargin(6788681764186368005L, futuresAccountType, BigDecimal.ONE, constant.ADD).toString());
        System.out.println(accountClient.setLeverage(symbol, futuresAccountType, 11).toString());
//        System.out.println(accountClient.setPositionsMode(symbol, futuresAccountType, constant.OneDirection).toString());
//        System.out.println(accountClient.setMarginMode(symbol, futuresAccountType, constant.Isolated).toString());

        //------trade-------
        Long orderId = accountClient.order(symbol, BigDecimal.valueOf(2022.1), BigDecimal.ONE, constant.LIMIT, constant.LIMIT, constant.SIDE_OPEN_LONG);
        System.out.println(orderId);
        orderId = accountClient.order(symbol, BigDecimal.valueOf(2022.1), BigDecimal.ONE, constant.LIMIT, constant.LIMIT, constant.SIDE_OPEN_LONG);
        System.out.println(orderId);

        System.out.println(accountClient.getUndoneOrders(symbol, null, null).toString());
        System.out.println(accountClient.getAllOrders(symbol, null, null, null, null).toString());
        System.out.println(accountClient.getOrder(symbol, orderId, null).toString());
        System.out.println(accountClient.getTradeList(symbol, orderId, null, null).toString());
        System.out.println(accountClient.tradeHistory(symbol, null, null, null, null).toString());

        System.out.println(accountClient.cancelOrder(symbol, orderId, null));

        List<BatchOrderItem> batchOrderItems = batchOrder(accountClient, symbol);
        ArrayList<Long> orders = new ArrayList<>();
        batchOrderItems.forEach((item) -> {
            orders.add(item.getOrderId());
        });
        System.out.println(accountClient.batchCancelOrder(symbol, orders, null));
        System.out.println(accountClient.cancelAllOrders(symbol).toString());
        System.out.println(accountClient.setMarginCoins(symbol, "usdt,eth", futuresAccountType));
    }

    List<BatchOrderItem> batchOrder(AccountClient client, String symbol) {
        OrderRequest order1 = new OrderRequest();
        order1.setSymbol(symbol);
        order1.setPrice(BigDecimal.valueOf(2022.1));
        order1.setAmount(BigDecimal.ONE);
        order1.setAction(constant.LIMIT);
        order1.setEntrustType(constant.LIMIT);
        order1.setSide(constant.SIDE_OPEN_LONG);

        OrderRequest order2 = new OrderRequest();
        order2.setSymbol(symbol);
        order2.setPrice(BigDecimal.valueOf(2022.1));
        order2.setAmount(BigDecimal.ONE);
        order2.setAction(constant.LIMIT);
        order2.setEntrustType(constant.LIMIT);
        order2.setSide(constant.SIDE_OPEN_LONG);

        ArrayList<OrderRequest> list = new ArrayList<>();
        list.add(order1);
        list.add(order2);
        List<BatchOrderItem> batchOrderItems = client.batchOrder(list);
        System.out.println(batchOrderItems);
        return batchOrderItems;
    }
}