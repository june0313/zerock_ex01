package org.zerock.service;

import org.zerock.domain.UserVO;
import org.zerock.dto.LoginDTO;

import java.util.Date;

/**
 * @author wayne
 * @version 1.0
 */
public interface UserService {
	UserVO login(LoginDTO dto) throws Exception;
	void keepLogin(String uid, String sessionId, Date next) throws Exception;
	UserVO checkLoginBefore(String value);
}
