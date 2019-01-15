package com.thinkgem.jeesite.modules.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration  
@EnableWebMvc  
@EnableWebSocket  
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer{
	@Autowired
	private SystemWebSocketHandler systemWebSocketHandler;
	@Autowired
	private QRCodeWebSocketHandler qrCodeWebSocketHandler;
  
    @Override  
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {  
        registry.addHandler(systemWebSocketHandler,"/webSocketServer").setAllowedOrigins("*");  
        registry.addHandler(systemWebSocketHandler,"/webSocketServer/sockjs").setAllowedOrigins("*").withSockJS();
        registry.addHandler(qrCodeWebSocketHandler,"/webSocketServer/qrcode").setAllowedOrigins("*");  
        registry.addHandler(qrCodeWebSocketHandler,"/webSocketServer/sockjs/qrcode").setAllowedOrigins("*").withSockJS();  
         //registry.addHandler(systemwWebSocketHandler(),"/webSocketServer").awhiteList=/a/*|/webSocketServer/*|/app/qrcode/*whiteList=/a/*|/webSocketServer/*|/app/qrcode/*whiteList=/a/*|/webSocketServer/*|/app/qrcode/*whiteList=/a/*|/webSocketServer/*|/app/qrcode/*ddInterceptors(new WebSocketHandshakeInterceptor());  
         //registry.addHandler(systemWebSocketHandler(), "/sockjs/webSocketServer").addInterceptors(new WebSocketHandshakeInterceptor()).withSockJS();  
         //registry.addHandler(systemWebSocketHandler(), "/webSocketServer/sockjs").withSockJS();  
         /*registry.addHandler(systemWebSocketHandler(),"/ws").addInterceptors(new WebSocketHandshakeInterceptor()); 
            registry.addHandler(systemWebSocketHandler(), "/ws/sockjs").addInterceptors(new WebSocketHandshakeInterceptor()) 
                    .withSockJS();*/  
    }  
      
  /*  @Bean  
    public WebSocketHandler systemWebSocketHandler(){  
        return new SystemWebSocketHandler();  
    }
    
    @Bean  
    public WebSocketHandler qrcodeSocketHandler(){  
        return new QRCodeWebSocketHandler();  
    }*/
}