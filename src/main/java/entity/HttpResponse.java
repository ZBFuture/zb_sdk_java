package entity;

import exception.SystemCode;
import exception.ZbException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HttpResponse<T> {
    private int code;
    private T data;

    private String desc;
//    private String cnDesc;
//    private String enDesc;

    public T checkAndReturn() {
        if (code == SystemCode.SUCCESS.getCode()) {
            return data;
        }
        throw new ZbException(code, desc);
    }
}
