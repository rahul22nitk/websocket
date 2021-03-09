package com.yatra.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HelloMessage {

	private String name;

	public HelloMessage() {
	}

	public HelloMessage(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		try {
			return new ObjectMapper().writeValueAsString(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
