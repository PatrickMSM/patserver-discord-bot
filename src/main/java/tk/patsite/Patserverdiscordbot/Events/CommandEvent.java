package tk.patsite.Patserverdiscordbot.Events;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import tk.patsite.Patserverdiscordbot.Command.CommandManager;
import tk.patsite.Patserverdiscordbot.Settings;

import java.util.Arrays;

public class CommandEvent extends ListenerAdapter {

    private final CommandManager man;

    public CommandEvent(CommandManager man) {
        this.man = man;
    }

    @Override
    public void onMessageReceived(final MessageReceivedEvent event)
    {
        if (event.isFromType(ChannelType.PRIVATE))
            return;

        final String message = event.getMessage().getContentRaw().trim();

        // Check if the commands start with the prefix
        if (!message.startsWith(Settings.Misc.PREFIX))
            return;


        // Remove prefix
        final String rawCommand = message.replaceFirst(Settings.Misc.PREFIX, "");

        // Get the main command
        final String command = rawCommand.split(" ")[0];

        // Get the args
        String[] args = rawCommand.split(" ");

        // Drop the command itself
        args = Arrays.copyOfRange(args, 1, args.length);

        // Call the command manager
        man.fire(command, args, event.getMessage());
    }
}
