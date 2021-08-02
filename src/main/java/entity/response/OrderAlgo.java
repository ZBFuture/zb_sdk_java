package entity.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderAlgo {
    private Long id;
    private Long marketId;                          //市场 ID
    private Long userId;                            //用户 ID
    private int side;                               //类型/方向，1:开多，2:开空，3:平多，4:平空
    private int orderType;                         //交易类型，1:计划委托，2:止盈止损
    private BigDecimal amount;                     //委托量
    private BigDecimal triggerPrice;               //触发价格
    private BigDecimal algoPrice;                  //委托价格
    private Long triggerTime;                       //触发时间
    private BigDecimal tradedAmount;                 //成交数量
    private int priceType;                          //1:标记价格，2:最新价格,针对止盈止损
    private int bizType;                            //1:止盈，2:止损,针对止盈止损
    private Integer leverage;                       //杠杆倍数
    private Integer status;                         //状态：1等待委托/未触发，2:已取消，3已委托/触发成功，4委托失败/触发失败
    private int sourceType;                         // 下单来源，见SourceType
    private BigDecimal submitPrice;                 // 提交时价格
    private String clientOrderId;                   // 自定义订单号
}
