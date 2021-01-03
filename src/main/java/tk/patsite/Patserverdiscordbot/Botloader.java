package tk.patsite.Patserverdiscordbot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import tk.patsite.Patserverdiscordbot.Command.CommandManager;
import tk.patsite.Patserverdiscordbot.Events.CommandEvent;
import tk.patsite.Patserverdiscordbot.MyLibs.Log;

import javax.security.auth.login.LoginException;

public final class Botloader {
    public Botloader(final Log log, String token) throws LoginException, InterruptedException {


        final CommandManager commandManager = new CommandManager();

        log.info("[WORKING] Init");



        JDA api = JDABuilder.createDefault(token)
                // .addEventListener(new EventListener() extends ListenerAdapter or implements EventListener) to add an event listener
                .addEventListeners(new CommandEvent(commandManager))
                .build();

        commandManager.init();

        log.fine("[DONE] Init");



        log.info("[Wait] Starting...");
        api.awaitReady();
        log.info("[Ready] Started!");


        log.info("\n \n Bot started! Ping: " + api.getGatewayPing());


        // Send the token to garbage

        token = null;
        System.gc();
    }
}
