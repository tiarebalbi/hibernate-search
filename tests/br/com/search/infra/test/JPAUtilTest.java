package br.com.search.infra.test;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.Test;

import br.com.search.infra.JPAUtil;

public class JPAUtilTest {

	@Test
	public void shouldReturnAnEntityManager() throws Exception {
		EntityManager entityManager = new JPAUtil().getEntityManager();
		
		assertNotNull(entityManager);
	}
}
