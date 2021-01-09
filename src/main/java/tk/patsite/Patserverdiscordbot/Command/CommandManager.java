package tk.patsite.Patserverdiscordbot.Command;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import tk.patsite.Patserverdiscordbot.Command.Commands.*;
import tk.patsite.Patserverdiscordbot.Settings;

import java.util.Map;
import java.util.Objects;

import static tk.patsite.Patserverdiscordbot.Settings.PREFIX;

public class CommandManager {


    private final StatusCommand status;

    {
        status = new StatusCommand();
    }

    private final Map<String, Command> commands = Map.of(
            "ip", new IpCommand(),
            "status", status,
            "rules", new RulesCommand(),
            "help", new HelpCommand(),
            "ping", new PingCommand(),
            "botchannel", new BotsCommand(),
            "embed", new EmbedCommand(this),
            "ghostping", new GhostpingCommand()
    );

    public final void init() {

        // Initialize status cache

        status.refreshStatusCache();

        commands.ge
    }

    public final Map<String, Command> getCommands() {
        return commands;
    }

    public void fire(String command, String[] args, Message message) {
        if (commands.containsKey(command)) {
            if (!message.getChannel().getId().equals(Settings.BOTS_CHANNEL_ID) || !command.equals("botchannel")) {
                if(!Objects.requireNonNull(message.getMember()).hasPermission(Permission.MANAGE_CHANNEL, Permission.MANAGE_SERVER, Permission.MESSAGE_MANAGE)) {
                    message.getAuthor().openPrivateChannel().complete().sendMessage("Hey! You can only send messages in " + Settings.BOTS_CHANNEL).queue();
                    message.delete().queue();
                    return;
                }
            }
            commands.get(command).perform(message, args);
        } else {
            message.getAuthor().openPrivateChannel().complete().sendMessage("Invalid command `" + PREFIX + command + "`! Type `" + PREFIX + "help` in patserver for help.").queue();
            message.delete().queue();
        }
    }
}
