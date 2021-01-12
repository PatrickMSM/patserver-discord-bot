package tk.patsite.Patserverdiscordbot;

import net.dv8tion.jda.api.entities.*;

public final class UserAuthenticator {
    public final void reactAuth(Member user) {
        if (onReactCheck(user)) {
             accept(user);
        }
    }
    public final void roleAuth(Member user) {
        if (onRoleCheck(user)) {
            accept(user);
        }
    }




    private void accept(Member user) {
        Guild userG = user.getGuild();

        userG.addRoleToMember(user, userG.getRoleById(Settings.AuthSettings.MEMBER_ROLE)).queue();
        userG.removeRoleFromMember(user, userG.getRoleById(Settings.AuthSettings.PAUTH_ROLE)).queue();
    }






    private boolean onReactCheck(Member user) {
        // The user already reacted.
        // Check if he actually has the role,

        for(Role role : user.getRoles()) {
            if (role.getId().equals(Settings.AuthSettings.PAUTH_ROLE)) {
                return true;
            }
        }
        return false;
    }


    private Message verifMessage;
    private boolean onRoleCheck(Member user) {
        // The user already has the role.
        // Check if he actually has reacted.

        if (verifMessage == null) {
            verifMessage = user.getGuild().getTextChannelById(Settings.AuthSettings.VERIFY_CHANNEL).retrieveMessageById(Settings.AuthSettings.VERIFY_MESSAGE).complete();
        }


        for (MessageReaction reaction : verifMessage.getReactions()) {
            for (User user1 : reaction.retrieveUsers()) {
                if (user.getUser().getId().equals(user1.getId()))
                    return true;
            }
        }

        return false;
    }

}
