package com.thinkgem.jeesite.modules.websocket;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;  
import org.springframework.web.socket.TextMessage;  
import org.springframework.web.socket.WebSocketHandler;  
import org.springframework.web.socket.WebSocketMessage;  
import org.springframework.web.socket.WebSocketSession;
@Service
public class SystemWebSocketHandler implements WebSocketHandler {  
       
    private Logger log = LoggerFactory.getLogger(SystemWebSocketHandler.class);  
      
    //private static final ArrayList<WebSocketSession> users = new ArrayList<WebSocketSession>();;  
    private static final ConcurrentHashMap<String,WebSocketSession> users = new ConcurrentHashMap<String,WebSocketSession>();
   
   
    @Override  
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {  
        System.out.println("ConnectionEstablished");  
        log.debug("ConnectionEstablished");  
        //users.add(session);
        users.put(session.getId(), session);
          
    }  
   
    @Override  
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {  
        System.out.println("handleMessage" + message.toString());  
        log.debug("handleMessage" + message.toString());  
        //sendMessageToUsers();  
        session.sendMessage(new TextMessage(new Date() + ""));  
    }  
   
    @Override  
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {  
        if(session.isOpen()){  
            session.close();  
        }  
        users.remove(session.getId());  
          
        log.debug("handleTransportError" + exception.getMessage());  
    }  
   
    @Override  
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {  
        users.remove(session.getId());  
        log.debug("afterConnectionClosed" + closeStatus.getReason());  
          
    }  
   
    @Override  
    public boolean supportsPartialMessages() {  
        return false;  
    }  
   
    /** 
     * 给所有在线用户发送消息 
     * 
     * @param message 
     */  
    public void sendMessageToUsers(TextMessage message) {
        Iterator<Map.Entry<String, WebSocketSession>> it = userIterator();
        while (it.hasNext()) {
            WebSocketSession session = it.next().getValue();
            try {
                if (session.isOpen())
                    session.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    
    public void sendMessageToUser(String username, TextMessage message) {
        Iterator<Map.Entry<String, WebSocketSession>> it = userIterator();
        while (it.hasNext()) {
            WebSocketSession session = it.next().getValue();
            if (username.equals(session.getAttributes().get("username"))) {
                try {
                    if (session.isOpen())
                        session.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    private Iterator<Map.Entry<String, WebSocketSession>> userIterator() {
        Set<Map.Entry<String, WebSocketSession>> entrys = users.entrySet();
        return entrys.iterator();
    }
   
}  