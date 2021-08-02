package entity.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class GetAccountResult {

    Account account;
    List<Asset> assets;

    @Data
    public class Account {
        /**
         * 账户余额：可用+冻结+所以仓位未实现盈亏
         */
        private BigDecimal accountBalance;
        /**
         * 所有仓位保证金
         */
        private BigDecimal allMargin;
        /**
         * 可用资金
         */
        private BigDecimal available;
        /**
         * 冻结资金
         */
        private BigDecimal freeze;
        /**
         * 所有仓位未实现盈亏
         */
        private BigDecimal allUnrealizedPnl;
        /**
         * 固定返回，如果是u本位，返回usdt，如果是币本位返回btc，如果是qc合约返回qc，统计数据的单位
         */
        private String unit;
        private BigDecimal accountBalanceConvert;
        private BigDecimal allMarginConvert;
        private BigDecimal availableConvert;
        private BigDecimal freezeConvert;
        private BigDecimal allUnrealizedPnlConvert;
        /**
         * 折合单位，页面显示上"≈"号后面的数字单位，如：cny，usd,btc
         */
        private String convertUnit;
        /**
         * 盈亏比例，未实现盈亏/所有仓位保证金*100%
         */
        private String percent;
    }

    @Data
    public class Asset {
        private Long id;
        private Long currencyId;
        private String currencyName;
        private BigDecimal amount;
        private BigDecimal freezeAmount;
    }
}