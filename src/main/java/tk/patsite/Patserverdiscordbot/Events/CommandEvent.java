package tk.patsite.Patserverdiscordbot.Events;
/*
MIT License

Copyright (c) 2021 PatrickMSM-Chertes

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
        final String command = rawCommand.split(" ")[0].toLowerCase();

        // Get the args
        String[] args = rawCommand.split(" ");

        // Drop the command itself
        args = Arrays.copyOfRange(args, 1, args.length);


        // Call the command manager
        man.fire(command, args, event.getMessage());
    }
}
