package net.bruhitsalex.tauq.misc;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class Log {

    private static final Logger log = Logger.getLogger(Log.class);

    public static void log(LoggerType type, String text) {
        log.info("[" + type.name().toUpperCase() + "] " + text);
    }

    public static void warn(LoggerType type, String text) {
        log.warn("[" + type.name().toUpperCase() + "] " + text);
    }

    public static void error(LoggerType type, String text) {
        log.error("[" + type.name().toUpperCase() + "] " + text);
    }

    public static void debug(String text) {
        log.debug(text);
    }

    static {
        BasicConfigurator.configure(new ConsoleAppender(new PatternLayout("[%t] %p %x- %m%n")));
    }

}
