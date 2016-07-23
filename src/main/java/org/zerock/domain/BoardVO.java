package org.zerock.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by wayne on 2016. 7. 1..
 *
 */

@Getter
@Setter
public class BoardVO {
	private Integer bno;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private int viewcnt;
	private int replycnt;
}
