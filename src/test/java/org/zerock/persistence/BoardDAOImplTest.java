package org.zerock.persistence;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
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
@Slf4j
public class BoardDAOImplTest {

	@Autowired
	private BoardDAO dao;


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
		log.info(dao.read(11).getTitle());
		log.info(dao.read(11).getContent());
		log.info(dao.read(11).getWriter());
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

	@Test
	public void testURI() throws Exception {
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
			.path("/board/read")
			.queryParam("bno", 12)
			.queryParam("perPageNum", 20)
			.build();

		log.info("/board/read?bno=12&perPageNum=20");
		log.info(uriComponents.toString());

		assertThat(uriComponents.toString(), is("/board/read?bno=12&perPageNum=20"));
	}

	@Test
	public void testURI2() throws Exception {
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
			.path("/{module}/{page}")
			.queryParam("bno", 12)
			.queryParam("perPageNum", 20)
			.build()
			.expand("board", "read")
			.encode();

		log.info(uriComponents.toString());

		assertThat(uriComponents.toString(), is("/board/read?bno=12&perPageNum=20"));

	}
}