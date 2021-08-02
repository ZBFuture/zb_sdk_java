package entity.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderRequest {

    private String symbol;

    private BigDecimal price;

    private BigDecimal amount;

    private Integer action;

    private Integer entrustType;

    /**
     * 方向：1开多（买入），2开空（卖出），3平多（卖出），4平空（买入）
     */
    private Integer side;
}
