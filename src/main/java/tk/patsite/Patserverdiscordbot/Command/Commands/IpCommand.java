package tk.patsite.Patserverdiscordbot.Command.Commands;

import net.dv8tion.jda.api.entities.Message;
import tk.patsite.Patserverdiscordbot.Command.Command;
import tk.patsite.Patserverdiscordbot.Settings;

public class IpCommand extends Command {
    @Override
    public void perform(Message message, String[] args) {
        message.reply("The ip for the server is: " + Settings.Misc.IP).queue();
    }

    @Override
    public String getDescription() {
        return "Sends server ip.";
    }
}
