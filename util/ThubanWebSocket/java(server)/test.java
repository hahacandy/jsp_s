package com.thuban.socket;

import java.util.Set;

import org.java_websocket.WebSocket;

public class test {
	public static void main(String[] args) {
		SocketServer socket = new SocketServer();
		Set<WebSocket> conns = socket.getConns();
		
		socket.start();
		
	

	}
}
