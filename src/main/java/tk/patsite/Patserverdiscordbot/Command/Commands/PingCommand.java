package tk.patsite.Patserverdiscordbot.Command.Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import tk.patsite.Patserverdiscordbot.Command.Command;

public class PingCommand extends Command  {
    @Override
    public void perform(Message message, String[] args) {
        final EmbedBuilder embed = new EmbedBuilder();
        embed.addField("API Ping", ""+message.getJDA().getGatewayPing(), false);
        embed.addField("REST (Internet) Ping", ""+message.getJDA().getRestPing(), false);

        final long before = System.currentTimeMillis();

        message.getChannel().sendMessage("dummyMsg").queue((msg)->{

            final long after = System.currentTimeMillis();
            msg.delete().queue();


            embed.addField("Message Ping", ""+ (after - before), false);

            message.getChannel().sendMessage(embed.build()).queue();
        });
    }

    @Override
    public String getDescription() {
        return "Gets discord bot ping";
    }
}
