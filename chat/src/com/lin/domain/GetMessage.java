package com.lin.domain;

public class GetMessage {
	private int port;
	private int from;
	private int to;
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public int getFrom() {
		return from;
	}
	public void setFrom(int from) {
		this.from = from;
	}
	public int getTo() {
		return to;
	}
	public void setTo(int to) {
		this.to = to;
	}
	public GetMessage(int port, int from, int to) {
		super();
		this.port = port;
		this.from = from;
		this.to = to;
	}
	public GetMessage() {
		
	}
	
}
