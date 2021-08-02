package entity.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PremiumIndexItem {
    String symbol;
    BigDecimal markPrice;
    BigDecimal indexPrice;
    BigDecimal lastFundingRate;
}
