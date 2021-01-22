package tk.patsite.Patserverdiscordbot.Server;

public class PingException extends RuntimeException {
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
