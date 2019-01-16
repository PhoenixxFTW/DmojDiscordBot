package com.phoenixx.bot.objects;

import net.dv8tion.jda.core.entities.Member;

import java.util.Date;

/**
 * @author Junaid Talpur
 * - DaemonBot
 * - 2019-01-10
 * - 12:45 PM
 **/
public class MessageObject
{
    private Member messageOwner;
    private String message;
    private Date createdOn;
    private boolean isEdited;

    public MessageObject(Member messageOwner, String message, Date createdOn, boolean isEdited) {
        this.messageOwner = messageOwner;
        this.message = message;
        this.createdOn = createdOn;
        this.isEdited = isEdited;
    }

    public Member getMessageOwner() {
        return messageOwner;
    }

    public String getMessage() {
        return message;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public boolean isEdited() {
        return isEdited;
    }
}
