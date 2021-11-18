package com.example.tcpexamination.common;

public enum SocketRequestType {
	GET_ACCOUNT("get_account"),
	ASYNC_ACCOUNT("create_if_not_existed_account"),
	GET_EXAMINATIONS("get_all_examinations"),
	REGISTER("register"),
	GET_LIST_QUESTION("get_list_question");
	
	private final String name;
	
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
