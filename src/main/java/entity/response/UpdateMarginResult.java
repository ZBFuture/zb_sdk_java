package entity.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateMarginResult {
    Integer leverage;
    BigDecimal avgPrice;
    BigDecimal bankruptcyPrice;
    Integer marginMode;
    Long marketId;
    BigDecimal marginRate;
    BigDecimal freezeAmount;
    Long modifyTime;
    Long originId;
    Long id;
    BigDecimal marginBalance;
    BigDecimal amount;
    BigDecimal margin;
    Integer side;
    BigDecimal liquidatePrice;
    String keyMark;
    Long userId;
    String marketName;
    Long createTime;
    BigDecimal unrealizedPnl;
    Integer liquidateLevel;
    Integer positionsMode;
    BigDecimal maintainMargin;
    BigDecimal nominalValue;
    Boolean open;
    Integer status;
}
