package org.zerock.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zerock.domain.UserVO;
import org.zerock.dto.LoginDTO;

/**
 * @author wayne
 * @version 1.0
 */
@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SqlSession session;

	private static final String NAMESPACE = "org.zerock.mapper.UserMapper";

	@Override
	public UserVO login(LoginDTO dto) throws Exception {
		return session.selectOne(NAMESPACE + ".login", dto);
	}
}
