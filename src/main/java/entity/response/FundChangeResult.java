package entity.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FundChangeResult {
    BigDecimal amount;
    BigDecimal balance;
    Long createTime;
    Long currencyId;
    BigDecimal freezeAmount;
    Long id;
    Long modifyTime;
    Long userId;
}
