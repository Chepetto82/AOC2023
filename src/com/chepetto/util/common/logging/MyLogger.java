package com.chepetto.util.common.logging;

import java.util.MissingResourceException;
import java.util.logging.Level;

public class MyLogger extends java.util.logging.Logger {
    /**
     * Protected method to construct a logger for a named subsystem.
     * <p>
     * The logger will be initially configured with a null Level
     * and with useParentHandlers set to true.
     *
     * @param name               A name for the logger.  This should
     *                           be a dot-separated name and should normally
     *                           be based on the package name or class name
     *                           of the subsystem, such as java.net
     *                           or javax.swing.  It may be null for anonymous Loggers.
     * @param resourceBundleName name of ResourceBundle to be used for localizing
     *                           messages for this logger.  May be null if none
     *                           of the messages require localization.
     * @throws MissingResourceException if the resourceBundleName is non-null and
     *                                  no corresponding resource can be found.
     */
    protected MyLogger(String name, String resourceBundleName) {
        super(name, resourceBundleName);
        this.setLevel(Level.INFO);
        this.addHandler(new MyConsoleHandler());

    }

    public MyLogger() {
        this(null, null);
    }

    public MyLogger(Level level) {
        this();
        this.setLevel(level);
    }

    @Override
    public void entering(String sourceClass, String sourceMethod) {
        super.entering(sourceClass, sourceMethod);
    }

    @Override
    public void entering(String sourceClass, String sourceMethod, Object param1) {
        super.entering(sourceClass, sourceMethod, param1);
    }

    @Override
    public void entering(String sourceClass, String sourceMethod, Object[] params) {
        super.entering(sourceClass, sourceMethod, params);
    }

    @Override
    public void exiting(String sourceClass, String sourceMethod) {
        super.exiting(sourceClass, sourceMethod);
    }

    @Override
    public void exiting(String sourceClass, String sourceMethod, Object result) {
        super.exiting(sourceClass, sourceMethod, result);
    }

    @Override
    public void throwing(String sourceClass, String sourceMethod, Throwable thrown) {
        super.throwing(sourceClass, sourceMethod, thrown);
    }

    @Override
    public void severe(String msg) {
        super.severe(msg);
    }
}
