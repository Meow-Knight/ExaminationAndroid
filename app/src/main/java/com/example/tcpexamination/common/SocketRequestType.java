package com.example.tcpexamination.common;

public enum SocketRequestType {
	GET_ACCOUNT("get_account"),
	REGISTER("register"),
	GET_LIST_QUESTION("get_list_question");
	
	private String name;
	
	private SocketRequestType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
