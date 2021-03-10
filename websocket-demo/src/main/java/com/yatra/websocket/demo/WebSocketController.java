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

	/*@MessageMapping("/text")
	@SendTo("/topic/text")
	public String broadcastText(@Payload String message) throws Exception {
		Thread.sleep(2000); // simulated delay
		return "Response : " + message;
	}

	@MessageMapping("/json")
	@SendTo("/topic/json")
	public Greeting broadcastJson(HelloMessage message) throws Exception {
		Thread.sleep(2000); // simulated delay
		return new Greeting("Response : " + message.getName() + "!");
	}
	
	@MessageMapping("/text2json")
	@SendTo("/topic/text2json")
	public String broadcastText(HelloMessage message) throws Exception {
		Thread.sleep(2000); // simulated delay
		return "Response : " + message.getName() + "!";
	}*/
	
	@MessageMapping("/text")
	public void broadcastText(@Payload String message) throws Exception {
		Thread.sleep(2000); // simulated delay
		this.simpMessagingTemplate.convertAndSend("/topic/text", "Response : " + message);
	}

	@MessageMapping("/json")
	public void broadcastJson(HelloMessage message) throws Exception {
		Thread.sleep(2000); // simulated delay
		this.simpMessagingTemplate.convertAndSend("/topic/json", new Greeting("Response : " + message.getName() + "!"));
	}
	
	@MessageMapping("/json2text")
	public void broadcastText(HelloMessage message) throws Exception {
		Thread.sleep(2000); // simulated delay
		this.simpMessagingTemplate.convertAndSend("/topic/json2text", "Response : " + message.getName() + "!");
	}
}
