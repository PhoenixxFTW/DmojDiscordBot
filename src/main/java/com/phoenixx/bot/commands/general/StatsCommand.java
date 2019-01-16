package com.phoenixx.bot.commands.general;

import com.phoenixx.bot.commands.Command;
import com.phoenixx.bot.handlers.ConfigHandler;
import com.phoenixx.bot.utils.References;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.Date;

/**
 * @author Junaid Talpur
 * - DaemonBot
 * - 2019-01-10
 * - 1:08 PM
 **/
public class StatsCommand implements Command
{

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        Date now = new Date();
        long secs = (now.getTime() - References.lastRestart.getTime()) / 1000;
        long hours = secs / 3600;
        secs = secs % 3600;
        long mins = secs / 60;
        secs = secs % 60;

        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setColor(new Color(219, 218, 0));
        embedBuilder.setDescription("Stats about the Daemon Bot");
        embedBuilder.addField("Server members", String.valueOf(event.getGuild().getMembers().size()), false);
        embedBuilder.addField("Tickets created since last restart", String.valueOf(References.amountOfTicketsMade), false);
        embedBuilder.addField("Bot Version", "1.0", false);
        embedBuilder.addField("Java version", System.getProperty("java.version"), false);
        embedBuilder.addField("JDA version", "3.8.0_427", false);
        embedBuilder.addField("Github", "https://github.com/JunaidTalpur/DmojDiscordBot", false);
        embedBuilder.setFooter("Bot Created by Phoenix#5518  |  GitHub: https://github.com/JunaidTalpur", "https://i.imgur.com/rLTnjje.png");

        event.getTextChannel().sendMessage(embedBuilder.build()).queue();
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return "USAGE: " + ConfigHandler.botPrefix + "stats";
    }

    @Override
    public String description() {
        return "Displays stats about the bot as well as server";
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
