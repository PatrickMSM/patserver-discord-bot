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

import net.dv8tion.jda.api.entities.Message;
import tk.patsite.Patserverdiscordbot.Cache.Cache;
import tk.patsite.Patserverdiscordbot.Cache.TimeoutCache;
import tk.patsite.Patserverdiscordbot.Command.Command;
import tk.patsite.Patserverdiscordbot.Server.CheckServerOnline;
import tk.patsite.Patserverdiscordbot.Settings;

public class StatusCommand extends Command {

    private final Cache<Boolean> onlineCache = new TimeoutCache<>(120000L, 3000L, () -> CheckServerOnline.IsServerOnline(Settings.Misc.IP).join(), true);

    public final void refreshStatusCache( ) {
        onlineCache.get();
    }

    @Override
    public void perform(Message message, String[] args) {
        if(onlineCache.get()) {
            // It's online.
            message.getChannel().sendMessage("The server is currently online.").queue();
        } else {
            // It's offline.
            message.getChannel().sendMessage("The server is currently offline.").queue();
        }
    }

    @Override
    public String getDescription() {
        return null;
    }
}
