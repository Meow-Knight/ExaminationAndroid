package com.example.tcpexamination.common;

public enum SocketRequestType {
	GET_ACCOUNT("get_account"),
	ASYNC_ACCOUNT("create_if_not_existed_account"),
	GET_EXAMINATIONS("get_all_examinations"),
	REGISTER("register"),
	GET_LIST_QUESTIONS_BY_EXAM_ID("get_list_questions_by_exam_id"),
	GET_LIST_CHOICES_BY_QUESTION_ID("get_list_choices_by_exam_id");
	
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
