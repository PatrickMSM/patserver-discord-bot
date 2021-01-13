package tk.patsite.Patserverdiscordbot.Util;

import net.dv8tion.jda.api.entities.MessageChannel;

public final class JDAUtil {
    public static void bulkDeleteMessages(MessageChannel chan, short amount) {
        if (amount > 100) {
            throw new IllegalArgumentException("Amount can not be larger than 100!");
        }
        chan.getIterableHistory().takeAsync(amount).thenApply(chan::purgeMessages);
    }
}
