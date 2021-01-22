package tk.patsite.Patserverdiscordbot.Libs;

import java.util.logging.Logger;

public final class Log {
    private transient final Logger logger;

    public Log(String name) {
        logger = Logger.getLogger(name);
    }


    public void info(String s) {
        logger.info( s );
    }

    public void fine(String s) {
        logger.fine( s );
    }

    public void warn(String s) {
        logger.warning( s );
    }

    public void error(String s) {
        logger.severe( s );
    }

}