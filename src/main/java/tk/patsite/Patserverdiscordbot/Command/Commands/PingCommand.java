package tk.patsite.Patserverdiscordbot.Command.Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import tk.patsite.Patserverdiscordbot.Command.Command;
import tk.patsite.Patserverdiscordbot.Settings;

public class PingCommand extends Command  {
    @Override
    public void perform(Message message, String[] args) {
        final EmbedBuilder embed = new EmbedBuilder();
        embed.addField("API Ping", ""+message.getJDA().getGatewayPing(), false);
        embed.addField("REST (Bot Internet) Ping", ""+message.getJDA().getRestPing().complete(), false);

        // this before the ping so it does not influence the time
        // generating an int ofc takes some time
        String dummyMessageRand = Settings.Misc.RANDOM.nextInt(9999) +"";


        final long before = System.currentTimeMillis();

        message.getChannel().sendMessage("dummy-message-"+dummyMessageRand).flatMap(msg -> {

            final long after = System.currentTimeMillis();
            msg.delete().queue();


            embed.addField("Message Ping", ""+ (after - before), false);

            return message.getChannel().sendMessage(embed.build());
        }).queue();
    }

    @Override
    public String getDescription() {
        return "Gets discord bot ping";
    }
}
