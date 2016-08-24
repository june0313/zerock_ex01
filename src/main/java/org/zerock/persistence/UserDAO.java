package org.zerock.persistence;

import org.zerock.domain.UserVO;
import org.zerock.dto.LoginDTO;

/**
 * @author wayne
 * @version 1.0
 */
public interface UserDAO {
	UserVO login(LoginDTO dto) throws Exception;
}
