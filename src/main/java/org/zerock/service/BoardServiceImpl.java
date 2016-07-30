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
import java.util.function.Consumer;

/**
 * Created by wayne on 2016. 7. 4..
 *
 */
@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDAO dao;

	@Override
	@Transactional
	public void regist(BoardVO board) throws Exception {
		dao.create(board);

		List<String> files = board.getFiles();

		if (files == null) {
			return;
		}

		for(String fullName : files) {
			dao.addAttach(fullName);
		}
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public BoardVO read(Integer bno) throws Exception {
		dao.updateViewCnt(bno);
		return dao.read(bno);
	}

	@Override
	@Transactional
	public void modify(BoardVO board) throws Exception {
		dao.update(board);

		Integer bno = board.getBno();

		// 첨부파일에 대한 수정사항이 발생하지 않아도... 수정하는 로직?
		dao.deleteAttach(bno);

		List<String> files = board.getFiles();

		if (files == null) {
			return;
		}

		for (String fileName : files) {
			dao.replaceAttach(fileName, bno);
		}
	}

	@Override
	@Transactional
	public void remove(Integer bno) throws Exception {
		// 삭제의 경우 tbl_attach 가 tbl_board 를 참조하기 때문에 반드시 첨부파일과 관련된 정보부터 삭제한다.
		dao.deleteAttach(bno);
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

	@Override
	public List<String> getAttach(Integer bno) throws Exception {
		return dao.getAttach(bno);
	}
}
