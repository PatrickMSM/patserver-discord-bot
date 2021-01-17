package tk.patsite.Patserverdiscordbot.Events;

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
