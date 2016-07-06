package org.zerock.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.zerock.configuration.RootContextConfiguration;
import org.zerock.configuration.servlet.ServletContextConfiguration;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by wayne on 2016. 7. 1..
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootContextConfiguration.class, ServletContextConfiguration.class})
@WebAppConfiguration
public class BoardDAOImplTest {

	@Autowired
	private BoardDAO dao;

	private static final Logger logger = LoggerFactory.getLogger(BoardDAOImplTest.class);

	@Test
	public void testCreate() throws Exception {
		BoardVO board = new BoardVO();
		board.setTitle("새 글을 넣습니다");
		board.setContent("새 글을 넣습니다");
		board.setWriter("user00");
		dao.create(board);
	}

	@Test
	public void testRead() throws Exception {
		logger.info(dao.read(11).getTitle());
		logger.info(dao.read(11).getContent());
		logger.info(dao.read(11).getWriter());
	}

	@Test
	public void testListAll() throws Exception {
		List<BoardVO> boardVOList = dao.listAll();
		boardVOList.forEach(boardVO -> System.out.println(boardVO.getBno() + " : " + boardVO.getTitle()));
	}

	@Test
	public void testListPage() throws Exception {
		int page = 3;

		List<BoardVO> list = dao.listPage(page);

		list.forEach(boardVO -> System.out.println(boardVO.getBno() + " : " + boardVO.getTitle()));

		assertThat(list.size(), is(10));
	}

	@Test
	public void testListCriteria() throws Exception {
		Criteria criteria = new Criteria();
		criteria.setPage(2);
		criteria.setPerPageNum(20);

		List<BoardVO> boardVOList = dao.listCriteria(criteria);

		boardVOList.forEach(boardVO -> System.out.println(boardVO.getBno() + " : " + boardVO.getTitle()));

	}
}