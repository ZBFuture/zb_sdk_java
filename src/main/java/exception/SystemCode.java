package exception;

public enum SystemCode {
    SUCCESS(10000, "操作成功"),
    FAILED(10001, "操作失败"),
    

    CALL_ERROR(99998, "call error"),
    RT_ERROR(99999, "Runtime Error");

    private int code;
    private String desc;

    SystemCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    SystemCode(int code, String desc, String cnDesc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static SystemCode getSystemCode(int code) {
        for (SystemCode e : SystemCode.values()) {
            if (e.getCode() == code) {
                return e;
            }
        }
        return null;
    }
}