package org.zerock.domain;

import lombok.Data;

@Data
public class Ticket {
	//요청이 json으로 들어왔을 때 java에서 값을 받아 먹는 형태
	private int tno;
	private String owner;
	private String grade;
}
