package org.zerock.dto;

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
public class LoginDTO {
	private String uid;
	private String upw;
	private boolean useCookie;
}
