package entity.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class NominalValueResult {
    Integer side;
    BigDecimal openOrderNominalValue;
    BigDecimal nominalValue;
    Integer marketId;
//    String symbol;
}
