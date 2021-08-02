package constant;

public class constant {
    public static final int SUCCESS = 10000;

    public static final int BASE_USDT = 1;//U本位
    public static final int BASE_COIN = 2;//币本位

    //convertUnit
    public static final String UNIT_CNY = "cny";
    public static final String UNIT_USD = "usd";
    public static final String UNIT_BTC = "btc";

    public static final String CURRENCY_USDT = "USDT";
    public static final String CURRENCY_BTC = "BTC";

    //1开多（买入），2开空（卖出），3平多（卖出），4平空（买入）
    public static final int SIDE_OPEN_LONG = 1;
    public static final int SIDE_OPEN_SHORT = 2;
    public static final int SIDE_CLOSE_LONG = 3;
    public static final int SIDE_CLOSE_SHORT = 4;

    //1 限价, 2 市价, 3 IOC, 4 只做 maker, 5 FOK
    public static final int ACTION_LIMIT = 1;
    public static final int ACTION_MARKET = 2;//多
    public static final int ACTION_IOC = 3;//多
    public static final int ACTION_MAKER = 4;//多
    public static final int ACTION_FOK = 5;//多

    public static final int OneDirection = 1;//单向
    public static final int BiDirection = 2;//双向

    public static final int Isolated = 1;//逐仓
    public static final int Cross = 2;//全仓

    public static final int ADD = 1;//增加
    public static final int SUBTRACT = 0;//减少

    public static final int LIMIT = 1;
    public static final int IOC = 3;
}
