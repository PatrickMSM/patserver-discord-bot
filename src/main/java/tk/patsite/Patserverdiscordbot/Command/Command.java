package tk.patsite.Patserverdiscordbot.Command;

import net.dv8tion.jda.api.entities.Message;

public abstract class Command {
    public abstract void perform(Message message, String[] args);
    public abstract String getDescription();
}
