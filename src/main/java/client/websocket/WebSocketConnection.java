package client.websocket;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebSocketConnection extends WebSocketListener {
    private static final Logger log = LoggerFactory.getLogger(WebSocketConnection.class);

    private OkHttpClient httpClient = new OkHttpClient();
    private WebSocket webSocket;
    private Request okhttpRequest;
    private boolean autoClose;

    private volatile long lastReceivedTime = 0;

    public WebSocketConnection(String url, boolean autoClose) {
        this.okhttpRequest = new Request.Builder().url(url).build();
        this.autoClose = autoClose;
    }

    void connect() {
        this.webSocket = httpClient.newWebSocket(this.okhttpRequest, this);
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        super.onOpen(webSocket, response);
        log.debug("onOpen", response.toString(), webSocket.toString());
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        super.onMessage(webSocket, text);
        lastReceivedTime = System.currentTimeMillis();
        log.debug(text);
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        super.onClosing(webSocket, code, reason);
    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {
        super.onClosed(webSocket, code, reason);
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        super.onFailure(webSocket, t, response);
    }
}
