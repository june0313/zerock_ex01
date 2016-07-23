package org.zerock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.MessageVO;
import org.zerock.persistence.MessageDAO;
import org.zerock.persistence.PointDAO;

/**
 * @author wayne
 * @version 1.0
 */
@Service
@Transactional
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDAO messageDAO;

	@Autowired
	private PointDAO pointDAO;

	@Override
	public void addMessage(MessageVO vo) throws Exception {
		// 두 개의 작업이 같이 이뤄지고 있다
		// 트랜잭션 처리가 필요하다.
		messageDAO.create(vo);
		pointDAO.updatePoint(vo.getSender(), 10);
	}

	@Override
	public MessageVO readMessage(String uid, Integer mid) throws Exception {
		messageDAO.updateState(mid);
		pointDAO.updatePoint(uid, 5);

		return messageDAO.readMessage(mid);
	}
}
