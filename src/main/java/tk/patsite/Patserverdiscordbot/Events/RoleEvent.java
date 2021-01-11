package tk.patsite.Patserverdiscordbot.Events;

import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import tk.patsite.Patserverdiscordbot.UserAuthenticator;

public class RoleEvent extends ListenerAdapter {

    private final UserAuthenticator auth;

    public RoleEvent(UserAuthenticator auth) {
        this.auth = auth;
    }

    @Override
    public void onGuildMemberRoleAdd(GuildMemberRoleAddEvent event) {
        auth.roleAuth(event.getMember());
    }
}
