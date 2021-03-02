package org.twitchapis.jta.twitch.ws;

import java.net.URI;
import java.nio.ByteBuffer;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;
import org.twitchapis.jta.utils.logger;

/**
 * @author LoboMetalurgico
 * @since 2021-01-17
 */

public class WebSocket extends WebSocketClient {
    private String token;

    public WebSocket(URI serverUri, Draft draft) {
        super(serverUri, draft);
    }

    public WebSocket(URI serverUri, String token) {
        super(serverUri);
        this.token = token;
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        if (!this.isOpen()) {
            logger.error("The connection wasn't open yet!");
            return;
        }
        logger.debug("Connection Started, Sending auth information...");
        send("CAP REQ :twitch.tv/tags twitch.tv/commands twitch.tv/membership");
        send("PASS " + this.token);
        send("NICK JavaTwitchAPI");
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        logger.error("closed with exit code " + code + " additional info: " + reason);
    }

    @Override
    public void onMessage(String message) {
        System.out.println("received message: " + message);
    }

    @Override
    public void onMessage(ByteBuffer message) {
        System.out.println("received ByteBuffer");
    }

    @Override
    public void onError(Exception ex) {
        System.err.println("an error occurred:" + ex);
    }

    public void emitWSMessage(String message) {
        if (!this.isOpen()) {
            logger.error("The connection wasn't open yet!");
            return;
        }
        send(message);
    }
}
