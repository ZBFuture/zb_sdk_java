package entity.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PositionsSettingResult {
    String userId;
    String marketId;
    Integer leverage;
    Integer marginMode;
    Integer positionsMode;
    BigDecimal maxAppendAmount;
    Integer enableAutoAppend;
    String marginCoins;

    String id;
    String createTime;
    String modifyTime;
}