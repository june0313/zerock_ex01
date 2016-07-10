package org.zerock.service;

import org.zerock.domain.ReplyVO;

import java.util.List;

/**
 * @author wayne
 * @version 1.0
 */
public interface ReplyService {

	void addReply(ReplyVO vo) throws Exception;

	List<ReplyVO> listReply(Integer bno) throws Exception;

	void modifyReply(ReplyVO vo) throws Exception;

	void removeReply(Integer rno) throws Exception;
}
