package org.zerock.service;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

import java.util.List;

/**
 * Created by wayne on 2016. 7. 4..
 *
 */
public interface BoardService {
	void regist(BoardVO board) throws Exception;

	BoardVO read(Integer bno) throws Exception;

	void modify(BoardVO board) throws Exception;

	void remove(Integer bno) throws Exception;

	List<BoardVO> listAll() throws Exception;

	List<BoardVO> listCriteria(Criteria criteria) throws Exception;
}
