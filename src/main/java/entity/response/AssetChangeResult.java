package entity.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AssetChangeResult {
    BigDecimal accountBalance;
    BigDecimal accountBalanceConvert;
    BigDecimal allMargin;
    BigDecimal allMarginConvert;
    BigDecimal allUnrealizedPnl;
    BigDecimal allUnrealizedPnlConvert;
    BigDecimal available;
    BigDecimal availableConvert;
    String convertUnit;
    BigDecimal freeze;
    BigDecimal freezeConvert;
    Integer futuresAccountType;
    String percent;
    String unit;
    Long userId;
}
