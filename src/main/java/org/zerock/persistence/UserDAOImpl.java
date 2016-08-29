package org.zerock.persistence;

import com.google.common.collect.Maps;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zerock.domain.UserVO;
import org.zerock.dto.LoginDTO;

import java.util.Date;
import java.util.Map;

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

	@Override
	public void keepLogin(String uid, String sessionId, Date next) {
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("uid", uid);
		paramMap.put("sessionId", sessionId);
		paramMap.put("next", next);

		session.update(NAMESPACE + ".keepLogin", paramMap);
	}

	@Override
	public UserVO checkUserWithSessionKey(String value) {
		return session.selectOne(NAMESPACE + ".checkUserWithSessionKey", value);
	}
}
