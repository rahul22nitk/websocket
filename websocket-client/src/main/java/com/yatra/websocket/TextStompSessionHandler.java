package com.yatra.websocket;

import java.lang.reflect.Type;

import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;

public class TextStompSessionHandler implements StompSessionHandler {

	@Override
	public Type getPayloadType(StompHeaders headers) {
		return String.class;
	}

	@Override
	public void handleFrame(StompHeaders headers, Object payload) {
		System.out.println("TextStompSessionHandler : handleFrame");
		System.out.println(payload);
	}

	@Override
	public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
		
		System.out.println("TextStompSessionHandler : afterConnected");
		System.out.println("TextStompSessionHandler : New session established : " + session.getSessionId());
		
		session.subscribe("/topic/text", this);
		System.out.println("TextStompSessionHandler : Subscribed to /topic/text");
		
		session.send("/app/text", "Hello from text client");
		System.out.println("TextStompSessionHandler : Message sent to websocket server");
	}

	@Override
	public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
		System.out.println("TextStompSessionHandler : handleException");
		System.out.println(payload);
		if (exception != null) {
			exception.printStackTrace();
		}
	}

	@Override
	public void handleTransportError(StompSession session, Throwable exception) {
		System.out.println("TextStompSessionHandler : handleTransportError");

		if (exception != null) {
			exception.printStackTrace();
		}
	}

}
