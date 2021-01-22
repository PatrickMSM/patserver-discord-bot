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

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import tk.patsite.Patserverdiscordbot.Command.CommandManager;
import tk.patsite.Patserverdiscordbot.Events.*;
import tk.patsite.Patserverdiscordbot.Libs.Log;

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

                // Load all members at startup
                .setMemberCachePolicy(MemberCachePolicy.ALL)

                // .addEventListener(new EventListener() extends ListenerAdapter or implements EventListener) to add an event listener
                .addEventListeners(new CommandEvent(commandManager))
                .addEventListeners(new MemberJoinEvent())
                .addEventListeners(new ReactionEvent(userAuth))
                .addEventListeners(new RoleEvent(userAuth))
                .addEventListeners(new DadEvent())
                .build();

        commandManager.init();

        log.fine("[DONE] Loaded");



        log.info("[Wait] Starting...");
        api.awaitReady();
        api.getGuildById(Settings.Misc.GUILD_ID).loadMembers().get();
        log.info("[Ready] Started!");


        log.info("\n \n Bot started! Ping: " + api.getGatewayPing());


        // Send the token to garbage
        token = null;


        // Clear up after initialization
        System.gc();
        System.runFinalization();
    }
}
