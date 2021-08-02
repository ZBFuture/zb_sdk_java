package entity.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FundingRateItem {
    String symbol;
    BigDecimal fundingRate;
    Long fundingTime;
}
