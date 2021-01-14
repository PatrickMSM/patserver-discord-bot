package tk.patsite.Patserverdiscordbot.Command.Commands;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import tk.patsite.Patserverdiscordbot.Command.Command;
import tk.patsite.Patserverdiscordbot.Settings;

public class GhostpingCommand extends Command {
    @Override
    public void perform(Message message, String[] args) {
        if (!message.getAuthor().getId().equals(Settings.Misc.PATRICK_ID))
            return;


        if (args.length < 1) {
            message.getAuthor().openPrivateChannel().complete().sendMessage("No arguments!").queue();
            message.delete().queue();
            return;
        }

        Member userToGhostping = message.getGuild().getMemberById(args[0].trim());

        if (userToGhostping == null ) {
            message.getAuthor().openPrivateChannel().complete().sendMessage(args[0].trim() + " is not a valid user id!").queue();
            message.delete().queue();
            return;
        }


        message.delete().queue();
        message.getChannel().sendMessage(userToGhostping.getAsMention()).flatMap(Message::delete).queue();
    }

    @Override
    public String getDescription() {
        return null;
    }
}
