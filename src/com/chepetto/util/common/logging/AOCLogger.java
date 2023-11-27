package com.chepetto.util.common.logging;

import java.io.IOException;
import java.util.logging.*;

public class AOCLogger {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static FileHandler fileHandler;
    private static SimpleFormatter formatterTxt;

    private static ConsoleHandler consoleHandler;

    public static void setup() throws IOException {

        // get the global logger to configure it
        //Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

        // suppress the logging output to the console
        /*Logger rootLogger = Logger.getLogger("");
        Handler[] handlers = rootLogger.getHandlers();
        if (handlers[0] instanceof ConsoleHandler) {
            rootLogger.removeHandler(handlers[0]);
        }*/

        LOGGER.setLevel(Level.INFO);
        /*fileHandler = new FileHandler("Logging.txt");

        // create a TXT formatter
        formatterTxt = new SimpleFormatter();
        fileHandler.setFormatter(formatterTxt);
        LOGGER.addHandler(fileHandler);*/

        consoleHandler = new ConsoleHandler();
        //consoleHandler.setOutputStreamPrivileged(System.out);
        consoleHandler.setFormatter(formatterTxt);
        LOGGER.addHandler(consoleHandler);
    }

    public static void log(String message) {
        Level level = LOGGER.getLevel();
        if (Level.INFO.equals(level)) {
            LOGGER.info(message);
        } else if (Level.SEVERE.equals(level)) {
            LOGGER.severe(message);
        } else if (Level.WARNING.equals(level)) {
            LOGGER.warning(message);
        } else if (Level.FINE.equals(level)) {
            LOGGER.fine(message);
        } else if (Level.FINER.equals(level)) {
            LOGGER.finer(message);
        } else if (Level.FINEST.equals(level)) {
            LOGGER.finest(message);
        }
    }

    public static void info(String message) {
        Level level = LOGGER.getLevel();
        if (Level.INFO.equals(level)) {
            LOGGER.info(message);
        }
    }
}
