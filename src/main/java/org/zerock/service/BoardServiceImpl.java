package org.zerock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.SearchCriteria;
import org.zerock.persistence.BoardDAO;

import java.util.List;

/**
 * Created by wayne on 2016. 7. 4..
 *
 */
@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDAO dao;

	@Override
	public void regist(BoardVO board) throws Exception {
		dao.create(board);
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public BoardVO read(Integer bno) throws Exception {
		dao.updateViewCnt(bno);
		return dao.read(bno);
	}

	@Override
	public void modify(BoardVO board) throws Exception {
		dao.update(board);
	}

	@Override
	public void remove(Integer bno) throws Exception {
		dao.delete(bno);
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		return dao.listAll();
	}

	@Override public List<BoardVO> listCriteria(Criteria criteria) throws Exception {
		return dao.listCriteria(criteria);
	}

	@Override public int listCountCriteria(Criteria criteria) throws Exception {
		return dao.countPaging(criteria);
	}

	@Override
	public List<BoardVO> listSearchCriteria(SearchCriteria cri) throws Exception {
		return dao.listSearch(cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		return dao.listSearchCount(cri);
	}
}
