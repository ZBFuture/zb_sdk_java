package entity.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BillItemResult {
    Long userId;
    Long currencyId;
    Long fundId;
    BigDecimal freezeId;
    Integer type;
    BigDecimal changeAmount;
    BigDecimal feeRate;
    BigDecimal fee;
    Long operatorId;
    BigDecimal beforeAmount;
    BigDecimal beforeFreezeAmount;
    Long marketId;
    String outsideId;
    Long id;
    Integer isIn;
    BigDecimal available;
    String unit;
    Long createTime;
    Long modifyTime;
    String extend;
}
