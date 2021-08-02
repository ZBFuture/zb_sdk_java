package entity.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MarginInfoResult {
    BigDecimal maxAdd;
    BigDecimal maxSub;
    BigDecimal liquidatePrice;
}
