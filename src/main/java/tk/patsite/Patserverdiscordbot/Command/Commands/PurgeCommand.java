package tk.patsite.Patserverdiscordbot.Command.Commands;
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

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import tk.patsite.Patserverdiscordbot.Command.Command;
import tk.patsite.Patserverdiscordbot.Settings;


public class PurgeCommand extends Command {
    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void perform(Message message, String[] args) {
        // Check admin
        if (!message.getAuthor().getId().equals(Settings.Misc.PATRICK_ID))
            return;


        // Check args
        if (args.length < 1) {
            message.getAuthor().openPrivateChannel().complete().sendMessage("No arguments!").queue();
            message.delete().queue();
            return;
        }

        Role roleToPurge = message.getGuild().getRoleById(args[0]);

        if (roleToPurge == null) {
            message.getAuthor().openPrivateChannel().complete().sendMessage("Invalid role!").queue();
            message.delete().queue();
            return;
        }

        final String text = Settings.Misc.ROLE_PURGE_MESSAGE
                .replace("\\{name}", message.getAuthor().getName())
                .replace("\\{role}", roleToPurge.getName());

        for (Member member : message.getGuild().getMembers()) {
            if (member.getRoles().stream().anyMatch(roleToPurge::equals)) {
                String m = text.replace("\\{mention}", member.getAsMention());
                member.getUser().openPrivateChannel().complete().sendMessage(m).queue();
                member.kick(m).queue();
            }
        }
    }
}
