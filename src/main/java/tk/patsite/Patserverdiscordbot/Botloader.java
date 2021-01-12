package tk.patsite.Patserverdiscordbot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import tk.patsite.Patserverdiscordbot.Command.CommandManager;
import tk.patsite.Patserverdiscordbot.Events.CommandEvent;
import tk.patsite.Patserverdiscordbot.Events.MemberJoinEvent;
import tk.patsite.Patserverdiscordbot.Events.ReactionEvent;
import tk.patsite.Patserverdiscordbot.Events.RoleEvent;
import tk.patsite.Patserverdiscordbot.MyLibs.Log;

import javax.security.auth.login.LoginException;

public final class Botloader {


    public Botloader(final Log log, String token) throws LoginException, InterruptedException {
        final UserAuthenticator userAuth = new UserAuthenticator();
        final CommandManager commandManager = new CommandManager();


        log.info("[WORKING] Loading...");

        final JDA api = JDABuilder.createDefault(token)
                // Intent so I CAN TRACK MEMBERS
                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                .enableIntents(GatewayIntent.GUILD_MESSAGE_REACTIONS)

                // .addEventListener(new EventListener() extends ListenerAdapter or implements EventListener) to add an event listener
                .addEventListeners(new CommandEvent(commandManager))
                .addEventListeners(new MemberJoinEvent())
                .addEventListeners(new ReactionEvent(userAuth))
                .addEventListeners(new RoleEvent(userAuth))
                .build();

        commandManager.init();

        log.fine("[DONE] Loaded");



        log.info("[Wait] Starting...");
        api.awaitReady();
        log.info("[Ready] Started!");


        log.info("\n \n Bot started! Ping: " + api.getGatewayPing());


        // Send the token to garbage
        token = null;


        // Clear up after initialization
        System.gc();
        System.runFinalization();
    }
}
