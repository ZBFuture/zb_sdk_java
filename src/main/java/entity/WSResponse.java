package entity;

import lombok.Data;

@Data
public class WSResponse {
    String channel;
    Object data;

    int errorCode;
    String errorMsg;
}
