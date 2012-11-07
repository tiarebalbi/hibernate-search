package br.com.search.infra.test;

import static org.junit.Assert.*;

import java.io.File;

import javax.persistence.EntityManager;

import org.junit.Test;

import br.com.search.infra.Indexing;
import br.com.search.infra.JPAUtil;

public class IndexingTest {

	private String filePath;

	@Test
	public void shouldCreateIndexingFile() throws Exception {
		removeFolder();
		
		EntityManager entityManager = new JPAUtil().getEntityManager();
		Indexing indexing = new Indexing(entityManager);
		
		indexing.indexAll();
		
		File file = new File(filePath);
		
		assertTrue(file.exists());
	}
	

	private void removeFolder() {
		filePath = "/home/alexandre/lucene/br.com.search.model.Project";
		File file2 = new File(filePath);
		
		file2.deleteOnExit();
	}
}
