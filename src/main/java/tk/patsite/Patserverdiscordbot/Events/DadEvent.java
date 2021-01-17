package tk.patsite.Patserverdiscordbot.Events;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class DadEvent extends ListenerAdapter {

    private String getT(String str, String conds, String condDelimiter) {
        final String[] checks = conds.split(condDelimiter);

        for (String check : checks) {
            String test = str.substring(str.lastIndexOf(check) + 1);
            if (!test.equals(str)) {
                return test;
            }
        }
        return null;
    }

    @Override
    public void onMessageReceived(final MessageReceivedEvent event) {
        if (event.getAuthor().isBot())
            return;

        final String message, result;
        message = event.getMessage().getContentRaw().trim();
        result = getT(message, "im|i'm|i am", "|");

        if (result == null)
            return;

        event.getMessage().reply("Hey " + result + ", im dad!").queue();
    }
}
