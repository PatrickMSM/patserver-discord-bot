package tk.patsite.Patserverdiscordbot;

import tk.patsite.Patserverdiscordbot.MyLibs.Log;

import javax.security.auth.login.LoginException;


public final class Starter {

    public static void main(String[] args) {

        // Get token
        String token = System.getenv("BOT_TOKEN");

        // Launch
        try {
            new Botloader(new Log("bot"), token);
        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
        }

        OnTheFlyCompiler compiler = new OnTheFlyCompiler("testapp", "class X{"  + Settings.NEWLINE +
                                                                                 "public static void main(String[] args) {" + Settings.NEWLINE +
                                                                                 "System.out.println(\"it work!\")}}");
    }
}
