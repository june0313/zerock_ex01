package org.zerock.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Wayne on 2016. 7. 6..
 */
public class CriteriaTest {
	private Criteria criteria;

	@Before
	public void setUp() throws Exception {
		this.criteria = new Criteria();

	}

	@Test
	public void testToString() throws Exception {
		System.out.println(criteria.toString());
	}

}