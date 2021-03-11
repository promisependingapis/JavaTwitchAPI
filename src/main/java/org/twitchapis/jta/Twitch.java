package org.twitchapis.jta;

import org.java_websocket.client.WebSocketClient;
import org.twitchapis.jta.twitch.api.actions.TokenVerify;
import org.twitchapis.jta.twitch.ws.WebSocket;
import org.twitchapis.jta.utils.TextFormat;
import org.twitchapis.jta.utils.logger;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.util.Scanner;

/**
 * @author LoboMetalurgico
 * @since 2021-02-14
 */

public class Twitch {
    private final String filePath;
    private final String dataPath;
    private final String pluginPath;
    private String token;
    private Boolean tokenIsValid = false;

    Twitch(final String filePath, String dataPath, String pluginPath) throws InterruptedException, IOException, URISyntaxException {
        this.filePath = filePath;
        this.dataPath = dataPath;
        this.pluginPath = pluginPath;

        if (!new File(this.dataPath + "config.yml").exists()) {
            logger.info(TextFormat.GREEN + "Welcome! This small guide will help you with the initial settings, but remember: you can change everything later in the " +
                    TextFormat.RESET +
                    TextFormat.GREEN_BOLD +
                    TextFormat.GREEN_UNDERLINED +
                    "config.yml" +
                    TextFormat.RESET +
                    TextFormat.GREEN +
                    " file.");
            logger.info("Please put your twitch token first!");

            do {
                Scanner in = new Scanner(System.in);

                token = in.nextLine();

                logger.debug("Your Token Is: " + token);

                logger.warn("Please wait while we verify your token...");

                HttpResponse<String> tokenV = TokenVerify.get("https://id.twitch.tv/oauth2/validate", token);

                if (tokenV.statusCode() != 200) {
                    logger.error("Invalid Token! Please insert a valid token.");
                } else {
                    this.tokenIsValid = true;
                }
            } while (!tokenIsValid);

            WebSocketClient twitchws = new WebSocket(new URI("wss://irc-ws.chat.twitch.tv"), token);

            twitchws.connect();
        }
    }
}
