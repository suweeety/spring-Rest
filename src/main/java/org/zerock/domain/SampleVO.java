package org.zerock.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //getter, setter, toString, equal 등...
@AllArgsConstructor //필드값을 모두 사용하는 생성자를 위한 어노테이션 = new SampleVO(mno, firstName, lastName)
@NoArgsConstructor //기본생성자를 만들기 위해 = new SampleVO()
public class SampleVO {
	private Integer mno;
	private String firstName;
	private String lastName;
}
