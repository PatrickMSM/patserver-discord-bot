package tk.patsite.Patserverdiscordbot;

import tk.patsite.Patserverdiscordbot.MyLibs.Log;
import tk.patsite.Patserverdiscordbot.MyLibs.TimedActionQueue;

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


        TimedActionQueue q = new TimedActionQueue();
        q.add(()->{System.out.println(System.currentTimeMillis());});
        q.add(()->{System.out.println(System.currentTimeMillis());});
        q.add(()->{System.out.println(System.currentTimeMillis());});
    }
}
