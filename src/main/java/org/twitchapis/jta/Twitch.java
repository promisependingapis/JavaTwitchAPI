package org.twitchapis.jta;

import io.socket.client.IO;
import io.socket.client.Socket;
import org.twitchapis.jta.utils.TextFormat;
import org.twitchapis.jta.utils.logger;

import java.io.File;
import java.net.URI;

/**
 * @author LoboMetalurgico
 * @since 2021-02-14
 */

public class Twitch {
    private final String filePath;
    private final String dataPath;
    private final String pluginPath;
    // private final URI uri;

    Twitch(final String filePath, String dataPath, String pluginPath) {
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
            logger.fatal("Not Implemented!", 0);
        }


        /* this.uri = URI.create("");
           Socket socket = IO.socket(this.uri);
         */
    }
}
