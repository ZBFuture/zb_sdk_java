package handler;

public interface Handler {
    void handleData(String channel, String msg);

    void handleError(String channel, Integer code, String desc);
}
