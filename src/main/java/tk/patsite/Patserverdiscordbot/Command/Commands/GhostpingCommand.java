package tk.patsite.Patserverdiscordbot.Command.Commands;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import tk.patsite.Patserverdiscordbot.Command.Command;
import tk.patsite.Patserverdiscordbot.Settings;

public class GhostpingCommand extends Command {
    @Override
    public void perform(Message message, String[] args) {
        if (args.length < 1 || !message.getAuthor().getId().equals(Settings.PATRICK_ID))
            return;

        Member userToGhostping = message.getGuild().getMemberById(args[1]);

        if (userToGhostping == null) {
            message.getAuthor().openPrivateChannel().complete().sendMessage(message.getContentRaw().trim() + " is not a valid user id!").queue();
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
