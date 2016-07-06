package org.zerock.persistence;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

import java.util.List;

/**
 * Created by wayne on 2016. 7. 1..
 *
 */
public interface BoardDAO {
	void create(BoardVO vo) throws Exception;

	BoardVO read(Integer bno) throws Exception;

	void update(BoardVO vo) throws Exception;

	void delete(Integer bno) throws Exception;

	List<BoardVO> listAll() throws Exception;

	List<BoardVO> listPage(int page) throws Exception;

	List<BoardVO> listCriteria(Criteria criteria) throws Exception;
}
