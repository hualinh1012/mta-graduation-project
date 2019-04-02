/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lhh.server.chatserver.messageio;

import com.lhh.dao.impl.user.ConversationDAO;
import com.lhh.dao.impl.user.UnreadConversationDAO;
import com.lhh.server.chatserver.connection.UserConnection;
import com.lhh.server.chatserver.connection.UserConnectionStorage;
import com.lhh.server.chatserver.logger.MessageLogger;
import com.lhh.server.entity.impl.Message;
import com.lhh.util.DateFormat;
import com.lhh.util.Util;
import java.util.List;

/**
 *
 * @author Linh Hua
 */
public class WebSocketWorker implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                UserConnection uc = UserConnectionStorage.poll();
                if (uc != null) {
                    processInBox(uc);
                    processOutBox(uc);
                    UserConnectionStorage.putConnection(uc);
                } else {
                    sleep();
                }
            } catch (Exception e) {
                Util.addErrorLog(e);
            }
        }
    }

    private void processInBox(UserConnection uc) {
        if (uc.inbox.isEmpty()) {
            return;
        }
        for (int i = 0; i < uc.inbox.size(); i++) {
            Message msg = uc.inbox.poll();
            uc.session.getAsyncRemote().sendText(msg.toJsonObject().toJSONString());
            
            UnreadConversationDAO.updateUnreadMessage(uc.userId, msg.to);
        }
    }

    private void processOutBox(UserConnection uc) {
        if (uc.outbox.isEmpty()) {
            return;
        }
        for (int i = 0; i < uc.outbox.size(); i++) {
            Message msg = uc.outbox.poll();
            if (msg == null) {
                continue;
            }
            if (msg.type == Message.MessageType.TEXT
                || msg.type == Message.MessageType.FILE
                || msg.type == Message.MessageType.EMOJI) {

                msg.time = DateFormat.format(Util.currentTime());

                List<String> lstUserId = ConversationDAO.getMember(msg.to);
                if (lstUserId == null || lstUserId.isEmpty()) {
                    continue;
                }

                for (String toUserId : lstUserId) {
                    List<UserConnection> lstConnection = UserConnectionStorage.getUserConnections(toUserId);
                    for (UserConnection to : lstConnection){
                        to.inbox.add(msg);
                    } 
                }
                
                MessageLogger.log(msg);
            }
        }
    }

    private void sleep() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            Util.addErrorLog(ex);
        }
    }

}
