package br.com.search.dao.test;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.search.infra.JPAUtil;
import br.com.search.model.Project;

public class ProjectDAOTest {

	private static EntityManager entityManager;

	@BeforeClass
	public static void setUp() {
		entityManager = new JPAUtil().getEntityManager();
	}
	
	@Test
	public void shouldReturnProjectsWithSomeCategory() throws Exception {
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
		entityManager.getTransaction().begin();
		
		QueryBuilder builder = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Project.class).get();
		Query query = builder.keyword().onFields("category").matching("job").createQuery();
		javax.persistence.Query queryJpa = fullTextEntityManager.createFullTextQuery(query, Project.class);
		
		@SuppressWarnings("unchecked")
		List<Project> projects = queryJpa.getResultList();
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
		assertNotNull(projects);
		assertEquals(1, projects.size());
	}
}
