package tk.patsite.Patserverdiscordbot.Command.Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import tk.patsite.Patserverdiscordbot.Command.Command;
import tk.patsite.Patserverdiscordbot.Command.CommandManager;
import tk.patsite.Patserverdiscordbot.Settings;

import java.util.Map;

public class EmbedCommand extends Command {

    private final Map<String, Command> commands;

    private final CommandManager manager;

    public EmbedCommand(CommandManager manager) {
        this.manager = manager;
        commands = manager.getCommands();
    }

    @Override
    public void perform(Message message, String[] args) {
        if (!message.getAuthor().getId().equals(Settings.PATRICK_ID)) {
            return;
        }
        if (args.length >= 1) {
            switch (args[0]) {
                case "rules" -> {
                    message.getChannel().sendMessage(new EmbedBuilder()
                            .setDescription("As seen here, there are lots of rules to follow." + Settings.NEWLINE +
                                    "Be sure to follow all of the rules listed here!" + Settings.NEWLINE +
                                    "There are this much rules to keep our community safe." + Settings.NEWLINE +
                                    ":warning: The staff team decides on what action will be acted on you.")
                            .setColor(0x14baba).setTitle("Patserver Discord Rules").setFooter("Last updated: 8h 22d 12m 20y UTC").build()).queue();
                    message.getChannel().sendMessage(new EmbedBuilder()
                            .setDescription("> Do not mention the staff for no reason! " + Settings.NEWLINE +
                                    "> Do not ask for roles / ranks! " + Settings.NEWLINE +
                                    "> Do not @mention spam! " + Settings.NEWLINE +
                                    "> Do not send NSFW content! " + Settings.NEWLINE +
                                    "> Do not send illegal / pirated content! " + Settings.NEWLINE +
                                    "> Do not publish personal info! " + Settings.NEWLINE +
                                    "> No hate speech, offensive language, sexism, racism, homophobia! " + Settings.NEWLINE +
                                    "> No political / religious discussions! " + Settings.NEWLINE +
                                    "> No [flaming](https://en.wikipedia.org/wiki/Flaming_(Internet))! " + Settings.NEWLINE +
                                    "> No trolling. " + Settings.NEWLINE +
                                    "> No spamming. " + Settings.NEWLINE +
                                    "> No personal attacks. " + Settings.NEWLINE +
                                    "> No doxxing people. " + Settings.NEWLINE +
                                    "> Use spaces and do not send each word on each line! " + Settings.NEWLINE +
                                    "> No walls of text. " + Settings.NEWLINE +
                                    "> Do not use excessive caps lock. " + Settings.NEWLINE +
                                    "> Do not talk other languages other than english." + Settings.NEWLINE)
                            .setColor(0x14baba).setTitle("Primary chat rules: ").build()).queue();
                    message.getChannel().sendMessage(new EmbedBuilder()
                            .setDescription("> No loud noises! " + Settings.NEWLINE +
                                    "> No high-pitch noises! " + Settings.NEWLINE +
                                    "> Do not annoy people! " + Settings.NEWLINE +
                                    "> Chat rules apply here too!")
                            .setColor(0x14baba).setTitle("Voice chat rules: ").build()).queue();
                    message.getChannel().sendMessage(new EmbedBuilder()
                            .setDescription("> No loud noises! " + Settings.NEWLINE +
                                    "> No high-pitch noises! " + Settings.NEWLINE +
                                    "> Do not annoy people! " + Settings.NEWLINE +
                                    "> Chat rules apply here too!")
                            .setColor(0x14baba).setTitle("Voice chat rules: ").build()).queue();
                }
                case "commands" -> {
                    final EmbedBuilder embed = (new EmbedBuilder()).setTitle("Bot Commands: ").setColor(0x14baba);

                    commands.forEach((name, command) -> {
                        final String commandDesc = command.getDescription();
                        if (commandDesc != null)
                            embed.addField(Settings.PREFIX + name, commandDesc, false);
                    });

                    message.getChannel().sendMessage(embed.build()).queue();

                }
            }
        }
    }

    @Override
    public String getDescription() {
        return null;
    }
}
