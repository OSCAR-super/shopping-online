package com.gj.sendone;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@ServerEndpoint("/test-one")
@Component
@Slf4j
public class MyOneToOneServer {
    /**
     * 用于存放所有在线客户端
     */
    private static Map<String, Session> clients = new ConcurrentHashMap<>();

    private Gson gson = new Gson();




    @OnOpen
    public void onOpen(Session session) {

        clients.put(session.getId(), session);
    }

    @OnClose
    public void onClose(Session session) {
        String sessionId = session.getId();

        clients.remove(sessionId);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        String sessionId = session.getId();
        if (clients.get(sessionId) != null) {

            clients.remove(sessionId);
        }
        throwable.printStackTrace();
    }

    @OnMessage
    public void onMessage(String message) {

        this.sendTo(gson.fromJson(message, Message.class));
    }

    /**
     * 发送消息
     *
     * @param message 消息对象
     */
    private void sendTo(Message message) {
        Session s = clients.get(message.getUserId());
        if (s != null) {
            try {
                s.getBasicRemote().sendText(message.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}
