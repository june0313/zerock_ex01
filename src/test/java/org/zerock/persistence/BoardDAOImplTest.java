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

import javax.servlet.ServletContext;

import java.util.List;
import java.util.function.Consumer;

import static org.junit.Assert.*;

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
		logger.info(dao.read(1).getTitle());
		logger.info(dao.read(1).getContent());
		logger.info(dao.read(1).getWriter());
	}

	@Test
	public void testListAll() throws Exception {
		List<BoardVO> boardVOList = dao.listAll();
		boardVOList.forEach(boardVO -> System.out.println(boardVO.getBno() + " : " + boardVO.getTitle()));
	}
}