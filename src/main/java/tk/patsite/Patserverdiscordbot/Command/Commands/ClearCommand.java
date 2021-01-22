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

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import tk.patsite.Patserverdiscordbot.Command.Command;
import tk.patsite.Patserverdiscordbot.Libs.TimedRunnableQueue;
import tk.patsite.Patserverdiscordbot.Settings;
import tk.patsite.Patserverdiscordbot.Util.JDAUtil;

public class ClearCommand extends Command {

    TimedRunnableQueue bulkDeleteQueue = new TimedRunnableQueue(1001);

    @Override
    public String getDescription() {
        return null;
    }

    private static final byte AMOUNT = 100;

    @Override
    public void perform(Message message, String[] args) {
        if (!message.getMember().hasPermission(Permission.MANAGE_CHANNEL, Permission.MESSAGE_MANAGE, Permission.MANAGE_SERVER)){
            return;
        }

        if (args.length < 1) {
            message.getAuthor().openPrivateChannel().complete().sendMessage("You did not provide the amount of messages to be cleared!").queue();
            message.delete().queue();
        }

        // Count of messages to delete
        long msgToDelete = Long.parseLong(args[0]);

        if (msgToDelete < 0) {
            message.getAuthor().openPrivateChannel().complete().sendMessage(msgToDelete + " is not a positive number!").queue();
            message.delete().queue();
        }

        final MessageChannel chan = message.getChannel();


        if (msgToDelete > 0) {
            if (msgToDelete / 100L > 0L) {
                for (long i = 0L; i < msgToDelete / 100L; i++) {
                    // Enqueue message delete
                    bulkDeleteQueue.add(() -> JDAUtil.bulkDeleteMessages(chan, AMOUNT));
                }
            }
            bulkDeleteQueue.add(() -> JDAUtil.bulkDeleteMessages(chan, (byte) (msgToDelete%100)));
        }


        message.delete().queue();

        new Thread(()->{
            Message message1 = chan.sendMessage("Deleted " + (msgToDelete + 1L) + " messages \\;) !").complete();

            // Wait seconds
            try {Thread.sleep(1200);
            } catch (InterruptedException e) {e.printStackTrace();}

            message1.delete().queue();
        }, "BulkDeleteThread-"+ Settings.NonSettings.RANDOM.nextInt(99)).start();


    }
}
