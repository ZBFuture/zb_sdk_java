package entity.response;

import lombok.Data;

@Data
public class BatchOrderItem {
    Integer sCode;
    String sMsg;
    Long orderId;
    String clientOrderId;
}