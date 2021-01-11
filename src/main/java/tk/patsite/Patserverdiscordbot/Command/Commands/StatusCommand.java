package tk.patsite.Patserverdiscordbot.Command.Commands;

import net.dv8tion.jda.api.entities.Message;
import tk.patsite.Patserverdiscordbot.Cache.Cache;
import tk.patsite.Patserverdiscordbot.Cache.TimeoutCache;
import tk.patsite.Patserverdiscordbot.Command.Command;
import tk.patsite.Patserverdiscordbot.ServerPing.CheckServerOnline;
import tk.patsite.Patserverdiscordbot.Settings;

public class StatusCommand extends Command {

    private final Cache<Boolean> onlineCache = new TimeoutCache<>(120000L, 3000L, () -> new CheckServerOnline().IsServerOnline(Settings.Misc.IP).join(), true);

    public final void refreshStatusCache( ) {
        onlineCache.get();
    }

    @Override
    public void perform(Message message, String[] args) {
        if(onlineCache.get()) {
            // It's online
            message.getChannel().sendMessage("The server is currently online.").queue();
        } else {
            // It's offline
            message.getChannel().sendMessage("The server is currently offline.").queue();
        }
    }

    @Override
    public String getDescription() {
        return null;
    }
}
