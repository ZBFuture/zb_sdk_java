package entity.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
public class Depth {
    List<List<BigDecimal>> asks;
    List<List<BigDecimal>> bids;
}
