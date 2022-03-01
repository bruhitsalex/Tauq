package net.bruhitsalex.tauq.misc;

public class Logger {

    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(Logger.class);

    public static void log(LoggerType type, String text) {
        LOGGER.info("[" + type.name().toUpperCase() + "] " + text);
    }

    public static void warn(LoggerType type, String text) {
        LOGGER.warn("[" + type.name().toUpperCase() + "] " + text);
    }

    public static void error(LoggerType type, String text) {
        LOGGER.error("[" + type.name().toUpperCase() + "] " + text);
    }

    public static void debug(LoggerType type, String text) {
        LOGGER.debug("[" + type.name().toUpperCase() + "] " + text);
    }

    public static void fatal(LoggerType type, String text) {
        LOGGER.fatal("[" + type.name().toUpperCase() + "] " + text);
    }

}
