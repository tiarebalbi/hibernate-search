package br.com.search.infra;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("hibernatesearch");
	
	public EntityManager getEntityManager() {
		return factory.createEntityManager();
	}

}
