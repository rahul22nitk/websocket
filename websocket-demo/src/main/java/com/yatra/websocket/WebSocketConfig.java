package com.yatra.websocket;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.converter.SimpleMessageConverter;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@ComponentScan(basePackages = {"com.yatra"})
@EnableWebSocketMessageBroker
@EnableWebSocket
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/ws").setAllowedOrigins("*");
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
//		config.enableSimpleBroker("/topic");
		config.enableStompBrokerRelay("/topic", "/queue").setRelayHost("192.168.171.73").setRelayPort(15887).setClientLogin("searchrmquser").setSystemLogin("searchrmquser").
			setClientPasscode("searchrmquser@195").setSystemPasscode("searchrmquser@195").setSystemHeartbeatSendInterval(5000).setSystemHeartbeatReceiveInterval(5000);
		config.setApplicationDestinationPrefixes("/app");
	}
	
	@Override
	public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
		if (messageConverters == null) {
			messageConverters = new ArrayList<>();
		}
		
		messageConverters.add(new MappingJackson2MessageConverter());
		messageConverters.add(new StringMessageConverter());
		messageConverters.add(new SimpleMessageConverter());
		return WebSocketMessageBrokerConfigurer.super.configureMessageConverters(messageConverters);
	}
}
