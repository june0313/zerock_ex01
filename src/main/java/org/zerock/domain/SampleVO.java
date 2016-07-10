package org.zerock.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * RestController 테스트용 도메인
 * @author wayne
 * @version 1.0
 */
@Getter
@Setter
@ToString
public class SampleVO {
	private Integer mno;
	private String firstName;
	private String lastName;
}
