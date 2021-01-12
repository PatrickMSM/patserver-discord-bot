package tk.patsite.Patserverdiscordbot;

import net.dv8tion.jda.api.entities.*;

import java.util.List;

public final class UserAuthenticator {
    public final void reactAuth(Member user, MessageReaction reaction) {
        if (onReactCheck(user, reaction)) {
             accept(user);
        }
    }
    public final void roleAuth(Member user, List<Role> roles) {
        if (onRoleCheck(user, roles)) {
            accept(user);
        }
    }




    private void accept(Member user) {
        Guild userG = user.getGuild();

        userG.addRoleToMember(user, userG.getRoleById(Settings.AuthSettings.MEMBER_ROLE)).queue();
        userG.removeRoleFromMember(user, userG.getRoleById(Settings.AuthSettings.PAUTH_ROLE)).queue();
    }





    private Message verifMessage;
    private boolean onReactCheck(Member user, MessageReaction reaction) {
        if (verifMessage == null) {
            verifMessage = user.getGuild().getTextChannelById(Settings.AuthSettings.VERIFY_CHANNEL).retrieveMessageById(Settings.AuthSettings.VERIFY_MESSAGE).complete();
        }
        // Check if the user reacted to the correct message
        for (MessageReaction reaction2 : verifMessage.getReactions()) {
            if (!reaction.equals(reaction2)) {
                return false;
            }
        }


        // The user already reacted.
        // Check if he actually has the role,

        for(Role role : user.getRoles()) {
            if (role.getId().equals(Settings.AuthSettings.PAUTH_ROLE)) {
                return true;
            }
        }
        return false;
    }



    private boolean onRoleCheck(Member user, List<Role> roles) {
        if (verifMessage == null) {
            verifMessage = user.getGuild().getTextChannelById(Settings.AuthSettings.VERIFY_CHANNEL).retrieveMessageById(Settings.AuthSettings.VERIFY_MESSAGE).complete();
        }
        // Check if the user got the correct role.


        for(Role role2 : roles) {
            if (!user.getGuild().getRoleById(Settings.AuthSettings.PAUTH_ROLE).getId().equals(role2.getId())) {
                return false;
            }
        }


        // The user already has the role.
        // Check if he actually has reacted.


        for (MessageReaction reaction : verifMessage.getReactions()) {
            for (User user1 : reaction.retrieveUsers()) {
                if (user.getUser().getId().equals(user1.getId()))
                    return true;
            }
        }

        return false;
    }

}
