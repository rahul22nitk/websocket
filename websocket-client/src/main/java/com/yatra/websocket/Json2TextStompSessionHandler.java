package com.yatra.websocket;

import java.lang.reflect.Type;

import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;

public class Json2TextStompSessionHandler implements StompSessionHandler {

	@Override
	public Type getPayloadType(StompHeaders headers) {
		return String.class;
	}

	@Override
	public void handleFrame(StompHeaders headers, Object payload) {
		System.out.println("Json2TextStompSessionHandler : handleFrame");
		System.out.println(payload);
	}

	@Override
	public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
		
		System.out.println("Json2TextStompSessionHandler : afterConnected");
		System.out.println("Json2TextStompSessionHandler : New session established : " + session.getSessionId());
		
		session.subscribe("/topic/json2text", this);
		System.out.println("Json2TextStompSessionHandler : Subscribed to /topic/json2text");
		
		HelloMessage message = new HelloMessage("Hello from json2text client");
		session.send("/app/json2text", message);
		System.out.println("Json2TextStompSessionHandler : Message sent to websocket server");
	}

	@Override
	public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
		System.out.println("Json2TextStompSessionHandler : handleException");
		System.out.println(payload);
		if (exception != null) {
			exception.printStackTrace();
		}
	}

	@Override
	public void handleTransportError(StompSession session, Throwable exception) {
		System.out.println("Json2TextStompSessionHandler : handleTransportError");

		if (exception != null) {
			exception.printStackTrace();
		}
	}

}
