package tk.patsite.Patserverdiscordbot;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;

public final class UserAuthenticator {

    public void reactAuth(Member user) {
        if (onReactCheck(user)) {
            Guild userG = user.getGuild();

            userG.addRoleToMember(user, userG.getRoleById(Settings.AuthSettings.MEMBER_ROLE)).queue();
            userG.removeRoleFromMember(user, userG.getRoleById(Settings.AuthSettings.PAUTH_ROLE)).queue();
        }
    }

    public boolean onReactCheck(Member user) {
        // The user already reacted.
        // Check if he actually has the role,

        for(Role role : user.getRoles()) {
            if (role.getId().equals(Settings.AuthSettings.PAUTH_ROLE)) {
                return true;
            }
        }
        return false;
    }

    public boolean onRoleCheck(Member user) {
        return false;
    }

}
