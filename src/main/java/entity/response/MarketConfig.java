package entity.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MarketConfig {
    private Long id;

    private String marketName;             //市场名称

    private String symbol;                 //唯一标识，自动生成，区分永续、当季、次季、当周、次周

    private Integer marketType;            //市场类型: 0: 现货,1: 杠杆,2: 期货

    private Long buyerCurrencyId;       //买方币种

    private String buyerCurrencyName;      //买方币种名称

    private Long sellerCurrencyId;      //卖方币种

    private String sellerCurrencyName;     //卖方币种名称

    private Long marginCurrencyId;       // 保证金币种

    private String marginCurrencyName;      // 保证金币种名称

    private Integer amountDecimal;          //委托量精度

    private Integer priceDecimal;           //委托价格精度

    private Integer feeDecimal = 8;         //手续费精度

    private Integer marginDecimal = 8;      //保证金已实现精度

    private BigDecimal minAmount;           //最小委托量

    private BigDecimal maxAmount;           //最大委托量

    private BigDecimal minTradeMoney;       //最小交易额

    private BigDecimal maxTradeMoney;       //最大交易额

    private BigDecimal minFundingRate;      //最小资金费率

    private BigDecimal maxFundingRate;      //最大资金费率

    private Integer maxLeverage;            //最大杠杆倍数

    private BigDecimal riskWarnRatio;       //风险提醒比例

    private BigDecimal defaultFeeRate;      //默认费率

    private Integer contractType;       //合约类型，1:usdt合约（默认），2:币本位合约

    private Integer duration;   //合约时长，1:永续合约（默认），2:交割合约-当周，3:交割合约-次周，4:交割合约-当季，5:交割合约-次季

    private int status;                     //状态: 1 运行, 0 停止（默认）

    private Long createTime;                //创建时间

    private Long enableTime;                //开盘时间

    private BigDecimal lastTradePrice;              //该市场最新成交价

    private Integer defaultLeverage = 20;                //默认杠杆倍数

    private Integer defaultMarginMode = 1;          //默认保证金模式：1逐仓（默认），2全仓

    private Integer defaultPositionsMode = 2;       //默认仓位模式，1:单向持仓，2: 双向持仓（默认）
}
