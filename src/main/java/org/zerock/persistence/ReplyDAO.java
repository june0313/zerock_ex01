package org.zerock.persistence;

import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

import java.util.List;

/**
 * Created by wayne on 2016. 7. 10..
 */
public interface ReplyDAO {

	List<ReplyVO> list(Integer bno) throws Exception;

	void create(ReplyVO vo) throws Exception;

	void update(ReplyVO vo) throws Exception;

	void delete(Integer rno) throws Exception;
	
	List<ReplyVO> listPage(Integer bno, Criteria cri) throws Exception;
	
	public int count(Integer bno) throws Exception;
}
