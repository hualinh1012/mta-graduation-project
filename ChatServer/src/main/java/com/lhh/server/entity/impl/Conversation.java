/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lhh.server.entity.impl;

import com.lhh.server.entity.IEntity;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Linh
 */
public class Conversation implements IEntity {

    public static final String ID = "_id";
    public static final String CONVERSATION_ID = "conversation_id";
    public String conversationId;

    public static final String CONVERSATION_NAME = "conversation_name";
    public String conversationName;

    public static final String CONVERSATION_TYPE = "conversation_type";
    public Integer conversationType;

    public static final String CREATE_TIME = "create_time";
    public String createTime;

    public static final String AVATAR_ID = "avatar_id";
    public String avatarId;

    public static final String AVATAR_URL = "avatar_url";
    public String avatarUrl;

    public static final String LAST_MESSAGE_FROM = "last_message_from";
    public String lastMessageFrom;

    public static final String LAST_MESSAGE_TYPE = "last_message_type";
    public String lastMessageType;

    public static final String LAST_MESSAGE_VALUE = "last_message_value";
    public String lastMessageValue;

    public static final String LAST_MESSAGE_TIME = "last_message_time";
    public String lastMessageTime;

    public static final String USER_ID = "user_id";
    public String userId;

    public static final String USER_LIST = "lst_user";
    public List<User> lstUser;
    public List<String> lstUserId;

    public static final String IS_EXISTED = "is_existed";
    public Boolean isExisted;

    public static final String UNREAD_NUMBER = "unread_number";
    public Integer unreadNumber;

    public Conversation() {
    }

    public Conversation(String conversationId, String conversationName) {
        this.conversationId = conversationId;
        this.conversationName = conversationName;
    }

    public Conversation(String conversationId, String avatarId, String avatarUrl) {
        this.conversationId = conversationId;
        this.avatarId = avatarId;
        this.avatarUrl = avatarUrl;
    }

    @Override
    public JSONObject toJsonObject() {
        JSONObject jo = new JSONObject();
        if (conversationId != null) {
            jo.put(CONVERSATION_ID, conversationId);
        }
        if (conversationName != null) {
            jo.put(CONVERSATION_NAME, conversationName);
        }
        if (conversationType != null) {
            jo.put(CONVERSATION_TYPE, conversationType);
        }
        if (avatarUrl != null) {
            jo.put(AVATAR_URL, avatarUrl);
        }
        if (lastMessageFrom != null) {
            jo.put(LAST_MESSAGE_FROM, lastMessageFrom);
        }
        if (lastMessageType != null) {
            jo.put(LAST_MESSAGE_TYPE, lastMessageType);
        }
        if (lastMessageValue != null) {
            jo.put(LAST_MESSAGE_VALUE, lastMessageValue);
        }
        if (lastMessageTime != null) {
            jo.put(LAST_MESSAGE_TIME, lastMessageTime);
        }
        if (isExisted != null) {
            jo.put(IS_EXISTED, isExisted);
        }
        if (lstUser != null) {
            JSONArray arr = new JSONArray();
            for (User u : lstUser){
                arr.add(u.toJsonObject());
            }
            jo.put(USER_LIST, arr);
        }
        if (userId != null) {
            jo.put(USER_ID, userId);
        }
        if (unreadNumber != null) {
            jo.put(UNREAD_NUMBER, unreadNumber);
        }
        return jo;
    }

    public static Conversation fromDBObject(Document obj) {
        Conversation conversation = new Conversation();
        conversation.conversationId = obj.get(Conversation.ID).toString();
        conversation.conversationName = (String) obj.get(Conversation.CONVERSATION_NAME);
        conversation.conversationType = (Integer) obj.get(Conversation.CONVERSATION_TYPE);
        conversation.avatarId = (String) obj.get(Conversation.AVATAR_ID);
        conversation.lastMessageFrom = (String) obj.get(Conversation.LAST_MESSAGE_FROM);
        conversation.lastMessageType = (String) obj.get(Conversation.LAST_MESSAGE_TYPE);
        conversation.lastMessageValue = (String) obj.get(Conversation.LAST_MESSAGE_VALUE);
        conversation.lastMessageTime = (String) obj.get(Conversation.LAST_MESSAGE_TIME);
        conversation.lstUserId = new ArrayList<>();
        ArrayList friends = (ArrayList) obj.get(Conversation.USER_LIST);
        for (Object o : friends) {
            if (o instanceof BasicDBObject) {
                BasicDBObject friend = (BasicDBObject) o;
                String friendId = (String) friend.get(Conversation.USER_ID);
                conversation.lstUserId.add(friendId);
            } else {
                Document friend = (Document) o;
                String friendId = (String) friend.get(Conversation.USER_ID);
                conversation.lstUserId.add(friendId);
            }
        }
        return conversation;

    }
}
