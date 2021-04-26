package org.twitchapis.jta;

import org.json.JSONObject;
import org.twitchapis.jta.twitch.api.actions.TokenVerify;
import org.twitchapis.jta.twitch.ws.WebSocket;
import org.twitchapis.jta.utils.TextFormat;
import org.twitchapis.jta.utils.logger;

import java.io.File;
import java.net.URI;
import java.net.http.HttpResponse;
import java.util.Map;
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
    private String channel;
    private String username;
    private String[] channels;
    private Boolean tokenIsValid = false;
    private Boolean validChannels = false;
    private JSONObject tokenResponse;

    Twitch(final String filePath, String dataPath, String pluginPath) throws Exception {
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
                if (System.getenv("TOKEN") == null) {
                    Scanner in = new Scanner(System.in);

                    token = in.nextLine();
                } else {
                    token = System.getenv("TOKEN");
                    logger.warn("Detected token in env!");
                }
                logger.warn("Please wait while we verify your token...");

                tokenResponse = TokenVerify.get("https://id.twitch.tv/oauth2/validate", token);

                if (tokenResponse.getInt("statusCode") != 200) {
                    logger.error("Invalid Token! Please insert a valid token.");
                } else {
                    this.tokenIsValid = true;
                }
            } while (!tokenIsValid);

            WebSocket twitchws = new WebSocket(new URI("wss://irc-ws.chat.twitch.tv"), token, tokenResponse.getString("login"));

            try {
                twitchws.connect();
            } catch (Exception e) {
                throw new Exception(e);
            }

            try { Thread.sleep (4000); } catch (InterruptedException ignored) {}

            logger.warn("Enter the name(s) of the channel(s) you want to connect (use space for more than 1 channel).");

            do {
                if (System.getenv("CHANNELS") == null) {
                    Scanner in = new Scanner(System.in);

                    channel = in.nextLine();
                } else {
                    channel = System.getenv("CHANNELS")
                            .replace("[", "")
                            .replace(",", "")
                            .replace("]", "");
                }
                if (channel.equals(" ")) channel = "";

                logger.warn("Please wait while we connect with these channels...");

                channels = channel.split(" ");

                if (channels[0] == null || channels[0].equals(" ") || channels[0].equals("")) {
                    logger.error("Invalid Channel! Please, insert a valid channel!");
                } else {
                    this.validChannels = true;
                }
            } while (!validChannels);
            logger.warn("Joining in channels...");

            twitchws.joinChannel(channels);
        }
    }
}
