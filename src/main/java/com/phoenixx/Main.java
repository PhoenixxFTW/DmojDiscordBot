package com.phoenixx;

import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import com.phoenixx.bot.commands.admin.RestartCommand;
import com.phoenixx.bot.commands.dev.DeleteChannelCommand;
import com.phoenixx.bot.commands.dmoj.DmojCommand;
import com.phoenixx.bot.commands.dmoj.GetUserInfoCommand;
import com.phoenixx.bot.commands.general.HelpCommand;
import com.phoenixx.bot.commands.general.PingCommand;
import com.phoenixx.bot.commands.general.StatsCommand;
import com.phoenixx.bot.commands.general.UptimeCommand;
import com.phoenixx.bot.handlers.CommandHandler;
import com.phoenixx.bot.handlers.ConfigHandler;
import com.phoenixx.bot.listeners.MessageListener;
import com.phoenixx.bot.logging.LogHandlerBot;
import com.phoenixx.bot.utils.References;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Junaid Talpur
 * - DaemonBot
 * - 2019-01-10
 * - 12:45 PM
 **/
public class Main
{
    private static LogHandlerBot logHandlerBot;

    public static JDA jda;
    public static JDABuilder builder;
    public static String token = "";

    public static ConfigHandler configHandler;
    // define an event waiter, dont forget to add this to the JDABuilder!
    private static EventWaiter waiter;

    public static void main(String args[])
    {
        startBot();
    }

    public static void startBot()
    {

        builder = new JDABuilder(AccountType.BOT);
        setupLogging();
        getLogHandlerBot().info("Starting Daemon Bot...");

        configHandler = new ConfigHandler();
        token = configHandler.discordBotToken;

        waiter = new EventWaiter();

        builder.setToken(token);
        builder.setAudioEnabled(true);
        builder.setGame(Game.playing("Daemon Bot | >help"));

        builder.addEventListener(waiter);

        addListeners();
        addCommands();

        References.lastRestart = new Date();

        try
        {
            jda = builder.buildBlocking();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static void addCommands()
    {
        // General
        CommandHandler.commands.put("help", new HelpCommand());
        CommandHandler.commands.put("ping", new PingCommand());
        CommandHandler.commands.put("uptime", new UptimeCommand());
        CommandHandler.commands.put("stats", new StatsCommand());

        // Dmoj
        CommandHandler.commands.put("dmoj", new DmojCommand());
        CommandHandler.commands.put("getUser", new GetUserInfoCommand());

        // Administration
        CommandHandler.commands.put("restart", new RestartCommand());
        CommandHandler.commands.put("deleteChannel", new DeleteChannelCommand(waiter));

    }

    private static void addListeners()
    {
        builder.addEventListener(new MessageListener());
    }

    private static void setupLogging()
    {
        new File("Daemon Discord-Bot/Bot Logs").mkdirs();
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date localDate = new Date();
        String date = localSimpleDateFormat.format(localDate);
        int i = 0;
        File localFile = new File( "Daemon Discord-Bot/Bot Logs/botLog-" + date + "-" + i + ".txt");
        int j = 1;
        while (j != 0)
        {
            i++;
            localFile = new File("Daemon Discord-Bot/Bot Logs/botLog-" + date + "-" + i + ".txt");
            if (!localFile.exists()) {
                j = 0;
            }
        }
        try
        {
            localFile.createNewFile();
        }
        catch (IOException localIOException)
        {
            localIOException.printStackTrace();
        }
        logHandlerBot = new LogHandlerBot("Daemon", localFile.getAbsolutePath());
        logHandlerBot.setup();
    }

    public static LogHandlerBot getLogHandlerBot()
    {
        return logHandlerBot;
    }
}
