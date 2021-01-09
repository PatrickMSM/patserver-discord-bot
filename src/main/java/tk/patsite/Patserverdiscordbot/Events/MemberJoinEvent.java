package tk.patsite.Patserverdiscordbot.Events;

import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import tk.patsite.Patserverdiscordbot.Settings;

public class MemberJoinEvent extends ListenerAdapter {
    @Override
    public void onGuildMemberRoleAdd(final GuildMemberRoleAddEvent event) {
        if (event.getRoles().contains(event.getGuild().getRoleById(Settings.MEMBER_ROLE))) {
            // Welcome user
            event.getGuild().getTextChannelById(Settings.WELCOME_CHANNEL).sendMessage(Settings.RANDOM_WELCOME().replaceFirst("\\{user}", event.getMember().getAsMention())).queue();
        }
    }
}
