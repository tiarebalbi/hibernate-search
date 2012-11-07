package br.com.search.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Resolution;
import org.hibernate.search.annotations.Store;

@Entity
@Indexed
public class Project {

	@Id
	@GeneratedValue
	private Long id;

	@Field(store=Store.NO, analyze=Analyze.YES, index=Index.YES)
	private String name;

	@Field(store=Store.NO, analyze=Analyze.YES, index=Index.YES)
	private String category;
	
	@Field(store=Store.YES, analyze=Analyze.NO, index=Index.YES)
	@DateBridge(resolution=Resolution.DAY)
	private Calendar startDate;
	
	@ManyToMany(cascade=CascadeType.PERSIST)
	@IndexedEmbedded
	private List<Developer> developers = new ArrayList<Developer>();

	public Project() {
	}
	
	public Project(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Developer> getDevelopers() {
		return developers;
	}

	public void setDevelopers(List<Developer> developers) {
		this.developers = developers;
	}

	public Calendar getStartDate() {
		return startDate;
	}

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void addDeveloper(Developer developer) {
		this.developers.add(developer);
	}
}
