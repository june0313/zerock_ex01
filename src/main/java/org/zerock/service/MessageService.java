package org.zerock.service;

import org.zerock.domain.MessageVO;

/**
 * @author wayne
 * @version 1.0
 */
public interface MessageService {

	void addMessage(MessageVO vo) throws Exception;

	MessageVO readMessage(String uid, Integer mid) throws Exception;

}
