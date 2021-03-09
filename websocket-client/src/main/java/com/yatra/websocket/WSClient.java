package com.yatra.websocket;

import java.util.Scanner;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;


public class WSClient {

	public static void main(String[] args) throws Exception {
		connectWebsocketForJsonMessage();
	}
	
	public static void connectWebsocketForTextMessage() {
		WebSocketClient client = new StandardWebSocketClient();

		WebSocketStompClient stompClient = new WebSocketStompClient(client);
		stompClient.setMessageConverter(new StringMessageConverter());
		StompSessionHandler sessionHandler = new TextStompSessionHandler();
		String url = "ws://localhost:8080/websocket-demo/ws";
		stompClient.connect(url, new WebSocketHttpHeaders(), sessionHandler);

		new Scanner(System.in).nextLine(); // Don't close immediately.
	}
	
	public static void connectWebsocketForJsonMessage() {
		WebSocketClient client = new StandardWebSocketClient();

		WebSocketStompClient stompClient = new WebSocketStompClient(client);
		stompClient.setMessageConverter(new MappingJackson2MessageConverter());
		StompSessionHandler sessionHandler = new JsonStompSessionHandler();
		String url = "ws://localhost:8080/websocket-demo/ws";
		stompClient.connect(url, new WebSocketHttpHeaders(), sessionHandler);

		new Scanner(System.in).nextLine(); // Don't close immediately.
	}
}
