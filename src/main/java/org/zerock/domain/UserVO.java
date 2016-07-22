package org.zerock.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author wayne
 * @version 1.0
 */
@Getter
@Setter
@ToString
public class UserVO {
	private String uid;
	private String upw;
	private String uname;
	private int upoint;
}
