package org.zerock.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 댓글 도메인 객체
 *
 * @author wayne
 * @version 1.0
 */
@Getter
@Setter
@ToString
public class ReplyVO {
	private Integer rno;
	private Integer bno;
	private String replytext;
	private String replyer;
	private Date regdate;
	private Date updatedate;
}
