package tk.patsite.Patserverdiscordbot.Events;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.regex.Pattern;

public class DadEvent extends ListenerAdapter {

    private final Pattern DadPattern = Pattern.compile("(im|i'm|i am)*");
    private final String DadPatternIm = "(im|i'm|i am)";

    @Override
    public void onMessageReceived(final MessageReceivedEvent event) {
        if (event.getAuthor().isBot())
            return;

        String message = event.getMessage().getContentRaw().trim();

        event.getMessage().reply(message).queue();
        /*
        final Matcher match = DadPattern.matcher(message);
        if (!match.matches())
            return;
        message = match.group(0).replace(DadPatternIm, "");

        event.getMessage().reply("Hey " + message + ", im dad!").queue(); */
    }
}
