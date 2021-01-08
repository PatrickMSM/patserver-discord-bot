package tk.patsite.Patserverdiscordbot;

import java.util.Random;

public final class  Settings {
    public static final String IP = "play.patsite.tk";
    public static final String PREFIX = "p!";
    public static final String HELP_CHANNEL = "<#787662278098550794>";
    public static final Random random = new Random();
    public static final String RULE_CHANNEL = "<#768729254200082432>";
    public static final String BOTS_CHANNEL = "<#787953253803491378>";
    public static final String BOTS_CHANNEL_ID = "787953253803491378";
    public static final String WELCOME_CHANNEL = "770372109037338714";

    private static final String[] WELCOME_MESSAGES = new String[]{
            "{user} just joined!", "{user} slid into the server",
            "Welcome {user}! We hope you have a great time!",
            "{user} joined the game."
    }; public static String RANDOM_WELCOME() {
        return WELCOME_MESSAGES[random.nextInt(WELCOME_MESSAGES.length)];
    }
    public static final String MEMBER_ROLE = "770356512391823372";
    public static final String NEWLINE = System.getProperty("line.separator");
    // public static final int PING_TIMEOUT = 200; // The longer, the more accurate the status command will be, but the users will have to wait longer
    public static final String PATRICK_ID = "300559445988081664";
}
