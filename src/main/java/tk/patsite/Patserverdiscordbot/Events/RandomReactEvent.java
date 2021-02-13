package tk.patsite.Patserverdiscordbot.Events;
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

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import tk.patsite.Patserverdiscordbot.Chance;
import tk.patsite.Patserverdiscordbot.Settings;

public class RandomReactEvent extends ListenerAdapter {
    @Override
    public void onMessageReceived(final MessageReceivedEvent event) {
        // Random
        if (event.getMessage().getAuthor().isBot())
            return;


        if (Chance.rand(Settings.Misc.MESSAGE_REACTION_CHANCE)) {
            // random react because why not

            event.getMessage().addReaction(Settings.RANDOM_REACT()).queue();
        }
    }
}
