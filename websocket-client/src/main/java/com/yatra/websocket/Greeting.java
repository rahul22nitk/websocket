package com.yatra.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Greeting {

	private String content;

	public Greeting() {
	}

	public Greeting(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
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
