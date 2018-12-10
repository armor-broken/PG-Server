package com.example.core.socket;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;



/***
 * Created by liuquan on 2018/10/30.
 * Author
 ***/

//@Configuration
//@EnableWebSocketMessageBroker//注解使用STOMP协议传输基于代理消息
//public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
//    //注册STOMP协议节点，同时指定使用SockJS协议。
//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        registry.addEndpoint("/endpoint").setAllowedOrigins("*").withSockJS();
//    }
//
//    //由于我们是实现推送功能，这里的消息代理是/topic
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry config) {
//    //启动STOMP 代理中继功能，并将其代理目的地前缀设置为 "/queue"
//        config.enableSimpleBroker("/topic");
//    //应用程序开头
//    //config.setApplicationDestinationPrefixes("/app");
//
//    }
//}

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * 注入ServerEndpointExporter，
     * 这个bean会自动注册使用了@ServerEndpoint注解声明的Websocket endpoint
     *
     * @return
     */
//    @Bean
//    public ServerEndpointExporter serverEndpointExporter() {
//        return new ServerEndpointExporter();
//    }
    //注册STOMP协议节点，同时指定使用SockJS协议。
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        System.out.println("注册");
        registry.addEndpoint("/endpoint").setAllowedOrigins("*").withSockJS();
//        registry.addEndpoint("/endpoint").withSockJS();
//        registry.addEndpoint("/endpoint").setAllowedOrigins("*").withSockJS();
    }
    //由于我们是实现推送功能，这里的消息代理是/topic
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
    //启动STOMP 代理中继功能，并将其代理目的地前缀设置为 "/queue"
        registry.enableSimpleBroker("/topic");
//    //应用程序开头
//        registry.setApplicationDestinationPrefixes("/app");
    }


}