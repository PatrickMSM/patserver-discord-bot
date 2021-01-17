package tk.patsite.Patserverdiscordbot.Events;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DadEvent extends ListenerAdapter {


    Pattern dadtern = Pattern.compile("^(.*)(im|i'm|i am|am|iam) (.*)$", Pattern.CASE_INSENSITIVE);

    @Override
    public void onMessageReceived(final MessageReceivedEvent event) {
        if (event.getAuthor().isBot())
            return;


        final String message = event.getMessage().getContentRaw();
        if (!dadtern.matcher(message).matches())
            return;

        final Matcher matcher = dadtern.matcher(message);


        event.getMessage().reply("Hey " + matcher.group(3) + ", im dad!").queue();
    }
}
