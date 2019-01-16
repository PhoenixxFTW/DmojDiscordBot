package com.phoenixx.bot.commands.dmoj;

import com.phoenixx.bot.commands.Command;
import com.phoenixx.bot.handlers.APIHandler;
import com.phoenixx.bot.handlers.ConfigHandler;
import com.phoenixx.bot.utils.References;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/**
 * @author Junaid Talpur
 * - DaemonBot
 * - 2019-01-10
 * - 1:08 PM
 **/
public class DmojCommand implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event)
    {
        //TODO Parse / format all data
        event.getTextChannel().sendMessage(APIHandler.sendGet(args[0])).queue();
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return "USAGE: " + ConfigHandler.botPrefix + "dmoj <url>";
    }

    @Override
    public String description() {
        return "Gets data from Dmoj Api";
    }

    @Override
    public String commandType() {
        return References.CMDTYPE.general;
    }

    @Override
    public int permission() {
        return 0;
    }
}
