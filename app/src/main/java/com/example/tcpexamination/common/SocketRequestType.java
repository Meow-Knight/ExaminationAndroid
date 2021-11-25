package com.example.tcpexamination.common;

public enum SocketRequestType {
	ASYNC_ACCOUNT("create_if_not_existed_account"),
	GET_EXAMINATIONS("get_all_examinations"),
	GET_LIST_QUESTIONS_BY_EXAM_ID("get_list_questions_by_exam_id"),
	GET_HISTORY_OF_ACCOUNT("get_history_of_account"),
	SAVE_HISTORY_RECORD("save_a_new_history");
	
	private final String name;
	
	SocketRequestType(String name) {
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
