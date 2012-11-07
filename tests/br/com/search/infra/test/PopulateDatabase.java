package br.com.search.infra.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Test;

import br.com.search.infra.JPAUtil;
import br.com.search.model.Developer;
import br.com.search.model.Project;

public class PopulateDatabase {

	private EntityManager entityManager;

	@Test
	public void populateDatabase() throws Exception {
		entityManager = new JPAUtil().getEntityManager();
		entityManager.getTransaction().begin();

		removeProjects(entityManager);
		
		TypedQuery<Developer> query = entityManager.createQuery("select d from Developer d", Developer.class);
		removeDevelopers(entityManager, query);
		
		facebookProject();
		
		twitterProject();
		
		linkedinProject();
		
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	private void linkedinProject() {
		Project linkedin = new Project("Linkedin");
		linkedin.setCategory("social job networking friends job service grups");
		linkedin.addDeveloper(new Developer("Peterpan Sanderzin"));
		linkedin.addDeveloper(new Developer("Peterson August"));
		entityManager.persist(linkedin);
	}

	private void twitterProject() {
		Project twitter = new Project("Twitter");
		twitter.setCategory("socialnetworking friends serivce jobs follow follower following entertainm recreat");
		twitter.addDeveloper(new Developer("Karl Chuow"));
		twitter.addDeveloper(new Developer("Karla Sandez"));
		twitter.addDeveloper(new Developer("Peter Sandz"));
		entityManager.persist(twitter);
	}

	private void facebookProject() {
		Project facebook = new Project("Facebook");
		facebook.setCategory("socialnetworking friends service entertainment diversion recreation groups");
		facebook.addDeveloper(new Developer("Josh Saverin"));
		facebook.addDeveloper(new Developer("Johnnathan Spyke"));
		facebook.addDeveloper(new Developer("Carl Fox"));
		facebook.addDeveloper(new Developer("Carlos Santana"));
		entityManager.persist(facebook);
	}

	private void removeDevelopers(EntityManager entityManager,
			TypedQuery<Developer> query) {
		List<Developer> developers = query.getResultList();
		for (Developer developer : developers) {
			entityManager.remove(developer);
		}
	}

	private void removeProjects(EntityManager entityManager) {
		TypedQuery<Project> query = entityManager.createQuery("select p from Project p", Project.class);
		List<Project> projects = query.getResultList();
		for (Project project : projects) {
			entityManager.remove(project);
		}
	}
}
