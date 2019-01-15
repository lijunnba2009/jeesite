package com.thinkgem.jeesite.modules.websocket;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;  
import org.springframework.web.socket.TextMessage;  
import org.springframework.web.socket.WebSocketHandler;  
import org.springframework.web.socket.WebSocketMessage;  
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.alibaba.fastjson.JSONObject;

  
/**
 *   
 * @author jokerLee
 * 二维码websocket实现
 */
@Service
public class QRCodeWebSocketHandler extends TextWebSocketHandler {
	
       
    private Logger log = LoggerFactory.getLogger(QRCodeWebSocketHandler.class);  
      
    //private static final ArrayList<WebSocketSession> users = new ArrayList<WebSocketSession>();;  
    private static final ConcurrentHashMap<String,WebSocketSession> users = new ConcurrentHashMap<String,WebSocketSession>();
    //定时关闭类
    class SessionCloseTimerTask extends TimerTask {
        private WebSocketSession session;
        public SessionCloseTimerTask(WebSocketSession session) {
            this.session = session;
        }

        @Override
        public void run() {
            try {
                this.session.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
   
   
    @Override  
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {  
        log.debug("ConnectionEstablished");  
        //users.add(session);
        users.put(session.getId(), session);
        Timer timer = new Timer();
        //三分钟后自动关闭
        timer.schedule(new SessionCloseTimerTask(session), 180000);
           
          
    }
    
    @Override 
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws  IOException {
       
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
    
    public void sendMessageToUser(String uuid, TextMessage message) {
        Iterator<Map.Entry<String, WebSocketSession>> it = userIterator();
        while (it.hasNext()) {
            WebSocketSession session = it.next().getValue();
            if (uuid.equals(session.getAttributes().get("uuid"))) {
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