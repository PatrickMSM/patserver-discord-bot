package tk.patsite.Patserverdiscordbot.Command;
/*
MIT License

Copyright (c) 2021 PatrickMSM

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import tk.patsite.Patserverdiscordbot.Command.Commands.*;
import tk.patsite.Patserverdiscordbot.Settings;

import java.util.Map;
import java.util.Objects;

import static java.util.Map.entry;
import static tk.patsite.Patserverdiscordbot.Settings.Misc.PREFIX;

public class CommandManager {


    private final StatusCommand status;

    {
        status = new StatusCommand();
    }

    private final Map<String, Command> commands = Map.ofEntries(
            entry("ip", new IpCommand()),
            entry("status", status),
            entry("rules", new RulesCommand()),
            entry("help", new HelpCommand()),
            entry("ping", new PingCommand()),
            entry("botchannel", new BotsCommand()),
            entry("embed", new EmbedCommand(this)),
            entry("ghostping", new GhostpingCommand()),
            entry("clear", new ClearCommand()),
            entry("purge", new PurgeCommand())
    );

    public final void init() {

        // Initialize status cache

        status.refreshStatusCache();
    }

    public final Map<String, Command> getCommands() {
        return commands;
    }



    public void fire(String command, String[] args, Message message) {
        if (commands.containsKey(command)) {
            if (!message.getChannel().getId().equals(Settings.Channels.BOTS_CHANNEL_ID) || !command.equals("botchannel")) {
                if(!Objects.requireNonNull(message.getMember()).hasPermission(Permission.MANAGE_CHANNEL, Permission.MANAGE_SERVER, Permission.MESSAGE_MANAGE)) {
                    message.getAuthor().openPrivateChannel().complete().sendMessage("Hey! You can only send messages in " + Settings.Channels.BOTS_CHANNEL).queue();
                    message.delete().queue();
                    return;
                }
            }
            commands.get(command).perform(message, args);
        } else {
            message.getAuthor().openPrivateChannel().complete().sendMessage("Invalid command `" + PREFIX + command + "`! Type `" + PREFIX + "help` in "+ Settings.Channels.BOTS_CHANNEL + " for help.").queue();
            message.delete().queue();
        }
    }
}
