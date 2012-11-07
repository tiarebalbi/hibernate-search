package br.com.search.infra;

import javax.persistence.EntityManager; 

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;

public class Indexing {

	private EntityManager entityManager;

	public Indexing(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void indexAll() throws InterruptedException {
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
		fullTextEntityManager.createIndexer().startAndWait();
	}

}
