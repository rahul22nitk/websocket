package com.yatra.websocket;

import java.lang.reflect.Type;

import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;

public class JsonStompSessionHandler implements StompSessionHandler {

	@Override
	public Type getPayloadType(StompHeaders headers) {
		return Greeting.class;
	}

	@Override
	public void handleFrame(StompHeaders headers, Object payload) {
		System.out.println("JsonStompSessionHandler : handleFrame");
		System.out.println(payload);
	}

	@Override
	public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
		
		System.out.println("JsonStompSessionHandler : afterConnected");
		System.out.println("JsonStompSessionHandler : New session established - " + session.getSessionId());
		
		session.subscribe("/topic/json", this);
		System.out.println("JsonStompSessionHandler : Subscribed to /topic/json");
		
		HelloMessage message = new HelloMessage("Hello from json client");
		session.send("/app/json", message);
		System.out.println("JsonStompSessionHandler : Message sent to websocket server");
	}

	@Override
	public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
		System.out.println("JsonStompSessionHandler : handleException");
		System.out.println(payload);
		if (exception != null) {
			exception.printStackTrace();
		}
	}

	@Override
	public void handleTransportError(StompSession session, Throwable exception) {
		System.out.println("JsonStompSessionHandler : handleTransportError");

		if (exception != null) {
			exception.printStackTrace();
		}
	}

}
