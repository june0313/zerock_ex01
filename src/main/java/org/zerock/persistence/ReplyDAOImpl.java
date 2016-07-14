package org.zerock.persistence;

import com.google.common.collect.ImmutableMap;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

import java.util.List;

/**
 * @author wayne
 * @version 1.0
 */
@Repository
public class ReplyDAOImpl implements ReplyDAO {

	@Autowired
	private SqlSession session;

	private static final String NAMESPACE = "org.zerock.mapper.ReplyMapper";

	@Override
	public List<ReplyVO> list(Integer bno) throws Exception {
		return session.selectList(NAMESPACE + ".list", bno);
	}

	@Override
	public void create(ReplyVO vo) throws Exception {
		session.insert(NAMESPACE + ".create", vo);
	}

	@Override
	public void update(ReplyVO vo) throws Exception {
		session.update(NAMESPACE + ".update", vo);
	}

	@Override
	public void delete(Integer rno) throws Exception {
		session.delete(NAMESPACE + ".delete", rno);
	}

	@Override
	public List<ReplyVO> listPage(Integer bno, Criteria cri) throws Exception {
		return session.selectList(NAMESPACE + ".listPage", ImmutableMap.of("bno", bno, "cri", cri));
	}

	@Override
	public int count(Integer bno) throws Exception {
		return session.selectOne(NAMESPACE + ".count", bno);
	}
}
