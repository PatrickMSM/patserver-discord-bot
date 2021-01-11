package tk.patsite.Patserverdiscordbot.Command.Commands;

import net.dv8tion.jda.api.entities.Message;
import tk.patsite.Patserverdiscordbot.Command.Command;
import tk.patsite.Patserverdiscordbot.Settings;

public class BotsCommand extends Command {
    @Override
    public void perform(Message message, String[] args) {
        message.getAuthor().openPrivateChannel().complete().sendMessage(message.getAuthor().getAsMention() + "The bot command channel is: " + Settings.Channels.BOTS_CHANNEL).queue();
        message.delete().queue();
    }

    @Override
    public String getDescription() {
        return "Sends bot channel";
    }
}
