package net.bruhitsalex.tauq.misc;

public class Log {

    public static void log(LoggerType type, String text) {
        System.out.println("[LOG] [" + type.name().toUpperCase() + "] " + text);
    }

    public static void warn(LoggerType type, String text) {
        System.err.println("[WARN] [" + type.name().toUpperCase() + "] " + text);
    }

    public static void error(LoggerType type, String text) {
        System.err.println("[ERROR] [" + type.name().toUpperCase() + "] " + text);
    }

    public static void debug(String text) {
        System.out.println("[DEBUG] " + text);
    }

}
