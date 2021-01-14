package tk.patsite.Patserverdiscordbot.Command.Commands;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import tk.patsite.Patserverdiscordbot.Command.Command;
import tk.patsite.Patserverdiscordbot.Settings;


public class PurgeCommand extends Command {
    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void perform(Message message, String[] args) {
        // Check admin
        if (!message.getAuthor().getId().equals(Settings.Misc.PATRICK_ID))
            return;


        // Check args
        if (args.length < 1) {
            message.getAuthor().openPrivateChannel().complete().sendMessage("No arguments!").queue();
            message.delete().queue();
            return;
        }

        Role roleToPurge = message.getGuild().getRoleById(args[0]);

        if (roleToPurge == null) {
            message.getAuthor().openPrivateChannel().complete().sendMessage("Invalid role!").queue();
            message.delete().queue();
            return;
        }

        final String text = Settings.Misc.ROLE_PURGE_MESSAGE
                .replace("\\{name}", message.getAuthor().getName())
                .replace("\\{role}", roleToPurge.getName());

        ol:for (Member member : message.getGuild().getMembers()) {
            System.out.println("m " + member.getUser());
            for (Role role :  member.getRoles()) {
                System.out.println("r " + role.getName());
                if (roleToPurge.equals(role)) {
                    System.out.println("f");
                    String m = text.replace("\\{mention}", member.getAsMention());
                    member.getUser().openPrivateChannel().complete().sendMessage(m).queue();
                    member.kick(m).queue();
                    continue ol;
                }
            }
        }
    }
}
