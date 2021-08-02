package entity.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author panda
 * @date 2021-01-23 15:43
 */
@Data
public class TradeResult {
    /**
     * 订单id
     */
    private Long orderId;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 成交价格
     */
    private BigDecimal price;
    /**
     * 成交数量
     */
    private BigDecimal amount;
    /**
     * 手续费
     */
    private BigDecimal feeAmount;
    /**
     * 手续费币种
     */
    private String feeCurrency;
    /**
     * 已实现盈亏
     */
    private BigDecimal relizedPnl;
    /**
     * 方向：1开多（买入），2开空（卖出），3平多（卖出），4平空（买入）
     */
    private Integer side;
    /**
     * 成交时间戳
     */
    private Long createTime;

}
