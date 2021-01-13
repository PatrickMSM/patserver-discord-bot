package tk.patsite.Patserverdiscordbot.Util;

import net.dv8tion.jda.api.entities.MessageChannel;

public final class JDAUtil {
    public static void bulkDeleteMessages(MessageChannel chan, byte amount) {
        if (amount > 100) {
            throw new IllegalArgumentException("Amount can not be larger than 100!");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("Amount can not be negative!");
        }
        chan.getIterableHistory().takeAsync(amount).thenApply(chan::purgeMessages);
    }
}
