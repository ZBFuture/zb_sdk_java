package entity.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author panda
 * @date 2021-01-23 15:33
 */
@Data
public class OrderResult {
    private Long id;
    /**
     * 自定义订单ID
     */
    private String orderCode;
    /**
     * 市场 ID
     */
    private Long marketId;
    /**
     * 用户 ID
     */
    private Long userId;
    /**
     * 委托价格
     */
    private volatile BigDecimal price;
    /**
     * 委托数量
     */
    private volatile BigDecimal amount;
    /**
     * 委托价值
     */
    private volatile BigDecimal value;
    /**
     * 可用委托数量
     */
    private volatile BigDecimal availableAmount;
    /**
     * 可用委托价值
     */
    private volatile BigDecimal availableValue;
    /**
     * 成交完成量, 每次成交都会增加
     */
    private volatile BigDecimal tradeAmount;
    /**
     * 成交完成价值, 每次成交都会增加
     */
    private volatile BigDecimal tradeValue;
    /**
     * 委托类型: -1 卖, 1 买  0 取消
     */
    private Integer type;
    /**
     * 委托动作: 1 限价, 2 市价, 3 IOC, 4 只做 maker, 5 FOK
     */
    private Integer action;
    /**
     * 委托类型：1限价委托，2强平委托，3限价止盈，4限价止损
     */
    private int entrustType;
    /**
     * 方向：1开多（买入），2开空（卖出），3平多（卖出），4平空（买入）
     */
    private int side;
    /**
     * 下单来源
     */
    private int sourceType;
    /**
     * 保证金
     */
    private BigDecimal margin;
    /**
     * 杠杆倍数
     */
    private Integer leverage;
}
