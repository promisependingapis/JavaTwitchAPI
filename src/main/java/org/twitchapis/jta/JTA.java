package org.twitchapis.jta;

/**
 * @author LoboMetalurgico
 * @since 2021-01-17
 */

public class JTA {
    public final static String PATH = System.getProperty("user.dir") + "/";
    public final static String DATA_PATH = System.getProperty("user.dir") + "/";
    public final static String PLUGIN_PATH = DATA_PATH + "plugins";

    public static void main(String[] args) throws Exception {
        new Twitch(PATH, DATA_PATH, PLUGIN_PATH);
    }
}
