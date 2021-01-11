package tk.patsite.Patserverdiscordbot.Events;

import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import tk.patsite.Patserverdiscordbot.UserAuthenticator;

public class ReactionEvent extends ListenerAdapter {

    private final UserAuthenticator auth;

    public ReactionEvent(UserAuthenticator auth) {
        this.auth = auth;
    }

    @Override
    public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent event) {
        auth.reactAuth(event.getMember());


        System.out.println(event.getMember().getAsMention() + " reacted");
    }

}
