package com.phoenixx.bot.commands.dmoj;

import com.phoenixx.bot.commands.Command;
import com.phoenixx.bot.handlers.APIHandler;
import com.phoenixx.bot.handlers.ConfigHandler;
import com.phoenixx.bot.utils.References;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.*;

/**
 * @author Junaid Talpur
 * - DaemonBot
 * - 2019-01-15
 * - 6:30 PM
 **/
public class GetUserInfoCommand implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event)
    {

        JSONParser parser = new JSONParser();
        try {
            //TODO Add check to see if character limit exceed 2k, if so, create multiple messages for all data
            String allData = APIHandler.sendGet("user/info/"+args[0]);
            JSONObject json = (JSONObject) parser.parse(allData);
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setColor(new Color(219, 218, 0));
            embedBuilder.setDescription("Dmoj Stats about the user: " + args[0]);
            embedBuilder.addField("Display name", String.valueOf(json.get("display_name")), false);
            embedBuilder.addField("Rank", String.valueOf(json.get("rank")), false);
            embedBuilder.addField("Points", String.valueOf(json.get("points")), false);
            if(allData.length() < 2000)
            {
                embedBuilder.addField("Solve problems", "- "+String.valueOf(json.get("solved_problems"))+"\n", false);
            }
            embedBuilder.addField("Organizations", String.valueOf(json.get("organizations")), false);
            event.getTextChannel().sendMessage(embedBuilder.build()).queue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return "USAGE: " + ConfigHandler.botPrefix + "getUser <username>";
    }

    @Override
    public String description() {
        return "Gets user data from Dmoj Api";
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
