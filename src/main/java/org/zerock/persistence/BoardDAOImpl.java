package org.zerock.persistence;

import com.google.common.collect.Maps;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.SearchCriteria;

import java.util.List;
import java.util.Map;

import static com.google.common.collect.ImmutableMap.of;

/**
 * Created by wayne on 2016. 7. 1..
 */
@Repository
public class BoardDAOImpl implements BoardDAO {

	@Autowired
	private SqlSession session;

	// boardMapper.xml 의 namespace를 지정함
	private static final String NAMESPACE = "org.zerock.mapper.BoardMapper";

	@Override
	public void create(BoardVO vo) throws Exception {
		session.insert(NAMESPACE + ".create", vo);
	}

	@Override
	public BoardVO read(Integer bno) throws Exception {
		return session.selectOne(NAMESPACE + ".read", bno);
	}

	@Override
	public void update(BoardVO vo) throws Exception {
		session.update(NAMESPACE + ".update", vo);
	}

	@Override
	public void delete(Integer bno) throws Exception {
		session.delete(NAMESPACE + ".delete", bno);
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		return session.selectList(NAMESPACE + ".listAll");
	}

	@Override
	public List<BoardVO> listPage(int page) throws Exception {
		if (page <= 0) {
			page = 1;
		}

		page = (page - 1) * 10;

		return session.selectList(NAMESPACE + ".listPage", page);
	}

	@Override
	public List<BoardVO> listCriteria(Criteria criteria) throws Exception {
		return session.selectList(NAMESPACE + ".listCriteria", criteria);
	}

	@Override
	public int countPaging(Criteria criteria) throws Exception {
		return session.selectOne(NAMESPACE + ".countPaging", criteria);
	}

	@Override
	public List<BoardVO> listSearch(SearchCriteria cri) throws Exception {
		return session.selectList(NAMESPACE + ".listSearch", cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		return session.selectOne(NAMESPACE + ".listSearchCount", cri);
	}

	@Override
	public void updateReplyCnt(Integer bno, int amount) throws Exception {
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("bno", bno);
		paramMap.put("amount", amount);

		session.update(NAMESPACE + ".updateReplyCnt", paramMap);
	}

	@Override
	public void updateViewCnt(Integer bno) throws Exception {
		session.update(NAMESPACE + ".updateViewCnt", bno);
	}

	@Override
	public void addAttach(String fullName) throws Exception {
		session.insert(NAMESPACE + ".addAttach", fullName);
	}

	@Override
	public List<String> getAttach(Integer bno) throws Exception {
		return session.selectList(NAMESPACE + ".getAttach", bno);
	}

	@Override
	public void deleteAttach(Integer bno) throws Exception {
		session.delete(NAMESPACE + ".deleteAttach", bno);
	}

	@Override
	public void replaceAttach(String fullName, Integer bno) throws Exception {
		session.insert(NAMESPACE + ".replaceAttach", of("bno", bno, "fullName", fullName));
	}

}
