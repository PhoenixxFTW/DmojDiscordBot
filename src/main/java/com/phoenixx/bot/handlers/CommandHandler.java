package com.phoenixx.bot.handlers;

import com.phoenixx.bot.commands.Command;
import com.phoenixx.bot.commands.CommandParser;

import java.util.HashMap;

/**
 * @author Junaid Talpur
 * - DaemonBot
 * - 2019-01-10
 * - 12:45 PM
 **/
public class CommandHandler
{
    public static final CommandParser parse = new CommandParser();
    public static HashMap<String, Command> commands = new HashMap<>();

    public static void handleCommand(CommandParser.CommandContainer cmd)
    {
        if(commands.containsKey(cmd.invoke))
        {
            boolean safe = commands.get(cmd.invoke).called(cmd.args, cmd.event);

            if(!safe)
            {
                commands.get(cmd.invoke).action(cmd.args, cmd.event);
                commands.get(cmd.invoke).executed(safe, cmd.event);
            } else {
                commands.get(cmd.invoke).executed(safe, cmd.event);
            }
        }
    }
}
