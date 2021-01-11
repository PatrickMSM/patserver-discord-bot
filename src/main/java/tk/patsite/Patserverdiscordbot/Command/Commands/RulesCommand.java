package tk.patsite.Patserverdiscordbot.Command.Commands;

import net.dv8tion.jda.api.entities.Message;
import tk.patsite.Patserverdiscordbot.Command.Command;
import tk.patsite.Patserverdiscordbot.Settings;

public class RulesCommand extends Command {

    @Override
    public void perform(Message message, String[] args) {
        message.reply("Rules channel: " + Settings.Channels.RULE_CHANNEL).queue();
    }

    @Override
    public String getDescription() {
        return "Sends server ip.";
    }
}
