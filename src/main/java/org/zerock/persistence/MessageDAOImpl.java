package org.zerock.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zerock.domain.MessageVO;

/**
 * @author wayne
 * @version 1.0
 */
@Repository
public class MessageDAOImpl implements MessageDAO {

	@Autowired
	private SqlSession session;

	private static final String NAMESPACE = "org.zerock.mapper.MessageMapper";

	@Override
	public void create(MessageVO vo) throws Exception {
		session.insert(NAMESPACE + ".create", vo);
	}

	@Override
	public MessageVO readMessage(Integer mid) throws Exception {
		return session.selectOne(NAMESPACE + ".readMessage", mid);
	}

	@Override
	public void updateState(Integer mid) throws Exception {
		session.update(NAMESPACE + ".updateState", mid);
	}

}
