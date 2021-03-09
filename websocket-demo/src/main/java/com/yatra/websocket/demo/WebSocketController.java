package com.yatra.websocket.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@MessageMapping("/text")
	@SendTo("/topic/text")
	public void broadcastNews(@Payload String message) throws Exception {
		Thread.sleep(5000); // simulated delay
		this.simpMessagingTemplate.convertAndSend("/topic/news", message);
	}

	@MessageMapping("/json")
	@SendTo("/topic/json")
	public Greeting greeting(HelloMessage message) throws Exception {
		Thread.sleep(5000); // simulated delay
		return new Greeting("Hello, " + message.getName() + "!");
	}
}
