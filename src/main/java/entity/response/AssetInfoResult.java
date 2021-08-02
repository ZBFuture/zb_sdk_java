package entity.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AssetInfoResult {
    BigDecimal accountBalanceConvert;
    BigDecimal allMarginConvert;
    BigDecimal availableConvert;
    BigDecimal available;
    BigDecimal allUnrealizedPnlConvert;
    String percent;
    Long userId;
    BigDecimal allMargin;
    BigDecimal allUnrealizedPnl;
    String unit;
    String convertUnit;
    BigDecimal freeze;
    BigDecimal freezeConvert;
    BigDecimal accountBalance;
}
