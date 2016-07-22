package org.zerock.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author wayne
 * @version 1.0
 */
@Getter
@Setter
@ToString
public class MessageVO {
	private Integer mid;
	private String targetid;
	private String sender;
	private String message;
	private Date opendate;
	private Date senddate;
}
