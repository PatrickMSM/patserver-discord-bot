package tk.patsite.Patserverdiscordbot.Events;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DadEvent extends ListenerAdapter {

    private final Pattern DadPattern = Pattern.compile("/(?<=im|i'm|i am)/gmi");

    @Override
    public void onMessageReceived(final MessageReceivedEvent event) {
        if (event.getAuthor().isBot())
            return;

        String message = event.getMessage().getContentRaw().trim();

        final Matcher match = DadPattern.matcher(message);
        if (!match.matches() || !match.find()) {
            return;
        }

        message = match.group(0);

        event.getMessage().reply("Hey " + message + ", im dad!").queue();
    }
}
