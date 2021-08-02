package entity.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BalanceItem {
    BigDecimal allUnrealizedPnl;
    BigDecimal allMargin;
    BigDecimal amount;
    BigDecimal freezeAmount;
    String currencyName;
    Long createTime;
    Long id;
    Long currencyId;
    BigDecimal accountBalance;
    Long userId;
}
