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
import java.util.Scanner;

/**
 * @author LoboMetalurgico
 * @since 2021-02-14
 */

public class Twitch {
    private final String filePath;
    private final String dataPath;
    private final String pluginPath;

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

            Scanner in = new Scanner(System.in);

            String token = in.nextLine();

            logger.debug("Your Token Is: " + token);

            logger.warn("Please wait while we verify your token...");

            TokenVerify.get("https://id.twitch.tv/oauth2/validate", token);

            WebSocketClient twitchws = new WebSocket(new URI("wss://irc-ws.chat.twitch.tv"), token);
            twitchws.connect();

            // logger.fatal("Not Implemented!", 0);
        }
    }
}
