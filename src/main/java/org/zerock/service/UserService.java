package org.zerock.service;

import org.zerock.domain.UserVO;
import org.zerock.dto.LoginDTO;

/**
 * @author wayne
 * @version 1.0
 */
public interface UserService {
	UserVO login(LoginDTO dto) throws Exception;
}
