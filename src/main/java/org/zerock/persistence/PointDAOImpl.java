package org.zerock.persistence;

import com.google.common.collect.Maps;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author wayne
 * @version 1.0
 */
@Repository
public class PointDAOImpl implements PointDAO {

	@Autowired
	private SqlSession session;

	private static final String NAMESPACE = "org.zerock.mapper.PointMapper";

	@Override
	public void updatePoint(String uid, int point) throws Exception {
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("uid", uid);
		paramMap.put("point", point);

		session.update(NAMESPACE + ".updatePoint", paramMap);
	}
}
