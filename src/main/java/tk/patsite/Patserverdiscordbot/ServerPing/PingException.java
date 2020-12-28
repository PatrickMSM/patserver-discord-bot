package tk.patsite.Patserverdiscordbot.ServerPing;

public class PingException extends Exception {
    public PingException() {
        super();
    }

    public PingException(String message) {
        super(message);
    }

    public PingException(String message, Throwable cause) {
        super(message, cause);
    }

    public PingException(Throwable cause) {
        super(cause);
    }
}
