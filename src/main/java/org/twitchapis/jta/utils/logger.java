package org.twitchapis.jta.utils;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

/**
 * @author LoboMetalurgico
 * @since 2021-02-20
 */

public class logger {
    public static void info(String string) {
        System.out.println(TextFormat.RESET + string + TextFormat.RESET);
    }

    public static void warn(String string) {
        System.out.println(TextFormat.RESET + TextFormat.YELLOW + string + TextFormat.RESET);
    }

    public static void error(String string) {
        System.out.println(TextFormat.RESET + TextFormat.RED + string + TextFormat.RESET);
    }

    public static void fatal(String string) {
        System.out.println(TextFormat.RESET + TextFormat.BLACK_BACKGROUND_BRIGHT + TextFormat.RED + string + TextFormat.RESET);

        System.exit(-1);
    }

    public static void fatal(String string, Integer exitCode) {
        System.out.println(TextFormat.RESET + TextFormat.BLACK_BACKGROUND_BRIGHT + TextFormat.RED + string + TextFormat.RESET);

        System.exit(exitCode);
    }
}
