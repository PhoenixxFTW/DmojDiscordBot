package com.phoenixx.bot.commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/**
 * @author Junaid Talpur
 * - DaemonBot
 * - 2019-01-10
 * - 12:45 PM
 **/
public interface Command
{
    boolean called(String[] args, MessageReceivedEvent event);
    void action(String[] args, MessageReceivedEvent event);
    void executed(boolean success, MessageReceivedEvent event);
    String help();
    String description();
    String commandType();
    int permission();
}
