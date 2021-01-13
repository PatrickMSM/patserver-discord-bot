package tk.patsite.Patserverdiscordbot;

import tk.patsite.Patserverdiscordbot.MyLibs.Log;

import javax.security.auth.login.LoginException;
import java.io.IOException;


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

        try {
            OnTheFlyCompiler compiler = new OnTheFlyCompiler("testapp", "class X{"  + Settings.NEWLINE +
                    "public static void main(String[] args) {" + Settings.NEWLINE +
                    "System.out.println(\"it work!\")}}");
            System.out.println("com");
            compiler.compile().thenAccept(aBoolean -> {
                System.out.println("compile");
                if (aBoolean) {
                    System.out.println("if");
                    try {
                        System.out.println("run");
                        compiler.run().thenAccept(s -> {
                            System.out.println("end");
                            System.out.println(s);
                            System.gc();
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
