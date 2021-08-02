package entity.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AllForceOrdersItem {
    String symbol;
    BigDecimal price;
    BigDecimal amount;
    BigDecimal tradeAmount;
    BigDecimal tradeAvgPrice;
    String side;
    String status;
    Long time;
}
