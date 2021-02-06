package tk.patsite.Patserverdiscordbot;
/*
MIT License

Copyright (c) 2021 PatrickMSM-Chertes

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */

import tk.patsite.Patserverdiscordbot.Multithreading.DaemonThreadFactory;

import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class Settings {

    // Channels
    public static final class Channels{
        public static final String RULE_CHANNEL = "<#768729254200082432>";
        public static final String BOTS_CHANNEL = "<#787953253803491378>";
        public static final long BOTS_CHANNEL_ID = 787953253803491378L;
        public static final long WELCOME_CHANNEL = 770372109037338714L;
        public static final String HELP_CHANNEL = "<#787662278098550794>";
    }

    public static final class Misc{
        public static final String IP = "play.patsite.tk";
        public static final String PREFIX = "p!";

        public static final long PATRICK_ID = 300559445988081664L;
        public static final long GUILD_ID = 768515187988103169L;

        public static final String JOIN_LINK = "https://discord.gg/MPeeX3a";
        public static final String ROLE_PURGE_MESSAGE = "{mention}, {purger} mass purged everyone with the role {role}. To rejoin, use link " + JOIN_LINK;
        public static final int MESSAGE_REACTION_CHANCE = 20; // one in 20


    }

    public static final class NonSettings{
        static final String[] WELCOME_MESSAGES = new String[]{
                "{user} just joined!", "{user} slid into the server",
                "Welcome {user}! We hope you have a great time!",
                "{user} joined the game."
        };
        public static final String[] RANDOM_REACTS = new String[] {
                "🤔"
        };
        public static final Random RANDOM = new SecureRandom();
        public static final ExecutorService executor = Executors.newFixedThreadPool(2, DaemonThreadFactory.instance);
    }



    public static String RANDOM_WELCOME() {
        return NonSettings.WELCOME_MESSAGES[NonSettings.RANDOM.nextInt(NonSettings.WELCOME_MESSAGES.length)];
    }
    public static String RANDOM_REACT() {
        return NonSettings.RANDOM_REACTS[NonSettings.RANDOM.nextInt(NonSettings.RANDOM_REACTS.length)];
    }






    public static final String NEWLINE = System.getProperty("line.separator");

    // Auth settings
    public static final class AuthSettings{
        public static final long MEMBER_ROLE = 770356512391823372L; // The role given to the user after they finished all auth
        public static final long PAUTH_ROLE = 790880837658214410L; // The role given to the user after they finished all but PAuth
        public static final long VERIFY_MESSAGE = 790890271641567272L;
        public static final long VERIFY_CHANNEL = 768729254200082432L;
        public static final String VERIFY_EMOJI = "✅";
    }
}
