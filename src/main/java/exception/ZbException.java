package exception;

public class ZbException extends RuntimeException {
    final Integer errCode;

    public ZbException(Integer errCode, String message) {
        super(message);
        this.errCode = errCode;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public ZbException(Exception e) {
        super(e);
        this.errCode = -1;
    }
}