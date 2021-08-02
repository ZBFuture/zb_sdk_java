package entity.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TopLongShortAccountRatioItem {
    String symbol;
    Long timestamp;
    Integer longAccount;
    Integer shortAccount;
    BigDecimal longShortRatio;
}
