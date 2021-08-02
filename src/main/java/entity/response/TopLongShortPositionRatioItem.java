package entity.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TopLongShortPositionRatioItem {
    String symbol;
    Long timestamp;
    BigDecimal longPosition;
    BigDecimal shortPosition;
    BigDecimal longShortRatio;
}
