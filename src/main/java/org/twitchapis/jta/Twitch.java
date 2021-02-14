package org.twitchapis.jta;

import io.socket.client.IO;
import io.socket.client.Socket;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.net.URI;

/**
 * @author LoboMetalurgico
 * @since 2021-02-14
 */

@Log4j2
public class Twitch {
    private final String filePath;
    private final String dataPath;
    private final String pluginPath;
    private final URI uri;

    Twitch(final String filePath, String dataPath, String pluginPath) {
        this.filePath = filePath;
        this.dataPath = dataPath;
        this.pluginPath = pluginPath;

        if (!new File(this.dataPath + "config.yml").exists()) {
            log.info(TextFormat.GREEN + "Welcome! Please choose a language first!");
        }


        this.uri = URI.create("");


        Socket socket = IO.socket(this.uri);
    }
}
