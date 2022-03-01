package net.bruhitsalex.tauq.misc;

import org.apache.log4j.Priority;

public class Logger {

    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getRootLogger();

    public static void log(LoggerType type, String text) {
        LOGGER.log(Priority.INFO, "[" + type.name().toUpperCase() + "] " + text);
    }

    public static void warn(LoggerType type, String text) {
        LOGGER.log(Priority.WARN, "[" + type.name().toUpperCase() + "] " + text);
    }

    public static void error(LoggerType type, String text) {
        LOGGER.log(Priority.ERROR, "[" + type.name().toUpperCase() + "] " + text);
    }

    public static void debug(LoggerType type, String text) {
        LOGGER.log(Priority.DEBUG, "[" + type.name().toUpperCase() + "] " + text);
    }

    public static void fatal(LoggerType type, String text) {
        LOGGER.log(Priority.FATAL, "[" + type.name().toUpperCase() + "] " + text);
    }

}
