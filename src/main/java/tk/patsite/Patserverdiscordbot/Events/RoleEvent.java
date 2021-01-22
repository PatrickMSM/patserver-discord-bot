package tk.patsite.Patserverdiscordbot.Events;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import tk.patsite.Patserverdiscordbot.Settings;
import tk.patsite.Patserverdiscordbot.UserAuthenticator;

public class RoleEvent extends ListenerAdapter {

    private final UserAuthenticator auth;

    public RoleEvent(UserAuthenticator auth) {
        this.auth = auth;
    }

    @Override
    public void onGuildMemberRoleAdd(GuildMemberRoleAddEvent event) {
        auth.roleAuth(event.getMember(), event.getRoles());

        auth.gotPauthRole(event.getMember()).thenAccept(aBoolean -> {
            if (aBoolean) {
                final User user = event.getUser();
                user.openPrivateChannel().complete().sendMessage(user.getAsMention() + ", Finish the verification by agreeing to the rules in "+ Settings.Channels.RULE_CHANNEL + "!").queue();

            }
        });
    }
}
