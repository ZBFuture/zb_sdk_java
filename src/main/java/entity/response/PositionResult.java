package entity.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PositionResult {
    /**
     * 仓位id
     */
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 市场id
     */
    private Long marketId;
    /**
     * 市场名称
     */
    private String marketName;
    /**
     * 开仓方向,开多：1 开空：0
     */
    private Integer side;
    /**
     * 杠杆倍数
     */
    private Integer leverage;
    /**
     * 持有仓位数量
     */
    private BigDecimal amount;
    /**
     * 下单冻结仓位数量
     */
    private BigDecimal freezeAmount;
    /**
     * 开仓均价
     */
    private BigDecimal avgPrice;
    /**
     * 强平价格
     */
    private BigDecimal liquidatePrice;
    /**
     * 保证金
     */
    private BigDecimal margin;
    /**
     * 保证金模式：1逐仓（默认），2全仓
     */
    private Integer marginMode;
    /**
     * 1:单向持仓，2: 双向持仓
     */
    private Integer positionsMode;
    /**
     * 状态: 1 可用、2:锁定、3:冻结、4：不显示
     */
    private Integer status;
    /**
     * 未实现盈亏
     */
    private BigDecimal unrealizedPnl;
    /**
     * 保证金余额
     */
    private BigDecimal marginBalance;
    /**
     * 维持保证金
     */
    private BigDecimal maintainMargin;
    /**
     * 保证金率
     */
    private BigDecimal marginRate;
    /**
     * 头寸的名义价值
     */
    private BigDecimal nominalValue;
    /**
     * 强平档位，即头寸对应的维持保证金档位
     */
    private Integer liquidateLevel;
    /**
     * 自动减仓比例，范围0～1，数字越大自动减仓风险越高
     */
    private BigDecimal autoLightenRatio;
    /**
     * 回报率
     */
    private BigDecimal returnRate;
    /**
     * 创建时间
     */
    private Long createTime;
    /**
     * 修改时间
     */
    private Long modifyTime;
    /**
     * 备用字段
     */
    private Object extend;
}