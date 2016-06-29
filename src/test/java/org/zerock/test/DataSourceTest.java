package org.zerock.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.configuration.RootContextConfiguration;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * Created by wayne on 2016. 6. 30..
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootContextConfiguration.class)
public class DataSourceTest {
	@Autowired
	private DataSource ds;

	@Test
	public void testConnection() throws Exception {
		try(Connection con = ds.getConnection()) {
			System.out.println(con);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
