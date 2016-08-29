package org.zerock.persistence;

import org.zerock.domain.UserVO;
import org.zerock.dto.LoginDTO;

import java.util.Date;

/**
 * @author wayne
 * @version 1.0
 */
public interface UserDAO {
	UserVO login(LoginDTO dto) throws Exception;

	void keepLogin(String uid, String sessionId, Date next);

	UserVO checkUserWithSessionKey(String value);
}
