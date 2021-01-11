package tk.patsite.Patserverdiscordbot.Command.Commands;

import net.dv8tion.jda.api.entities.Message;
import tk.patsite.Patserverdiscordbot.Command.Command;
import tk.patsite.Patserverdiscordbot.Settings;

public class HelpCommand extends Command {
    @Override
    public void perform(Message message, String[] args) {
        message.reply("Command help channel: " + Settings.Channels.HELP_CHANNEL).queue();
    }

    @Override
    public String getDescription() {
        return "Links to this channel.";
    }
}
