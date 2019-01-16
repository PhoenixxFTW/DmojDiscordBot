package com.phoenixx.bot.listeners;

import com.phoenixx.bot.handlers.CommandHandler;
import com.phoenixx.bot.handlers.ConfigHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;

/**
 * @author Junaid Talpur
 * - DaemonBot
 * - 2019-01-10
 * - 12:45 PM
 **/
public class MessageListener extends ListenerAdapter
{

    public static EmbedBuilder success() {
        return new EmbedBuilder().setColor(Color.green);
    }

    public static EmbedBuilder error() {
        return new EmbedBuilder().setColor(Color.red);
    }

    public static void sendToChat(String message, TextChannel givenChannel)
    {
        givenChannel.sendMessage(message).complete();
    }

    public void onMessageReceived(final MessageReceivedEvent event)
    {
        Message message = event.getMessage();
        TextChannel messageChannel = message.getTextChannel();

        if(message.getContentDisplay().startsWith(ConfigHandler.botPrefix) && !event.getAuthor().isBot() && !(event.getChannel() instanceof PrivateChannel))
        {
            CommandHandler.handleCommand(CommandHandler.parse.parser(message.getContentRaw(), event));
        }
    }
}
