package tk.patsite.Patserverdiscordbot;

import java.util.Random;

public final class Settings {

    // Channels
    public static final class Channels{
        public static final String RULE_CHANNEL = "<#768729254200082432>";
        public static final String BOTS_CHANNEL = "<#787953253803491378>";
        public static final String BOTS_CHANNEL_ID = "787953253803491378";
        public static final String WELCOME_CHANNEL = "770372109037338714";
        public static final String HELP_CHANNEL = "<#787662278098550794>";
    }

    public static final class Misc{
        public static final String IP = "play.patsite.tk";
        public static final String PREFIX = "p!";
        public static final String PATRICK_ID = "300559445988081664";
        public static final Random RANDOM = new Random();
        public static final String GUILD_ID = "768515187988103169";
    }

    public static final String NEWLINE = System.getProperty("line.separator");

    private static final String[] WELCOME_MESSAGES = new String[]{
            "{user} just joined!", "{user} slid into the server",
            "Welcome {user}! We hope you have a great time!",
            "{user} joined the game."
    }; public static String RANDOM_WELCOME() {
        return WELCOME_MESSAGES[Misc.RANDOM.nextInt(WELCOME_MESSAGES.length)];
    }



    // Auth settings
    public static final class AuthSettings{
        public static final String MEMBER_ROLE = "770356512391823372"; // The role given to the user after they finished all auth
        public static final String PAUTH_ROLE = "790880837658214410"; // The role given to the user after they finished all but PAuth
        public static final String VERIFY_MESSAGE = "790890271641567272";
        public static final String VERIFY_CHANNEL = "768729254200082432";
        public static final String VERIFY_ICON = "✅";
    }
}
