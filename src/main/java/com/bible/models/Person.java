package com.bible.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.bible.xml.PersonAdapter;
import com.bible.xml.models.Content;

@XmlRootElement(name="person")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Person implements Serializable, Content {

	/**
	 * 
	 */
	@XmlElement
	@Transient
	private static final long serialVersionUID = 6343175094069748832L;
	
	@XmlTransient
	@OneToMany(mappedBy="content")
	private Set<Books_Annotation_Person> annotations=new HashSet<Books_Annotation_Person>();
	
	@XmlAttribute
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@XmlElement
	@Basic(optional=false, fetch=FetchType.EAGER)
	private String name;
	
	@XmlElement
	@Basic(optional=true, fetch=FetchType.EAGER)
	private String other_names;
	
	@XmlJavaTypeAdapter(PersonAdapter.class)
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="main_name_id")
	@Basic(optional=true, fetch=FetchType.EAGER)
	private Person main_name;
	
	@XmlElement
	@Basic(optional=true, fetch=FetchType.LAZY)
	private String disambiguation;
	
	@XmlElement
	@Basic(optional=true, fetch=FetchType.LAZY)
	private String cross_references;
	
	@XmlElement
	@Basic(optional=true, fetch=FetchType.EAGER)
	private String biography;
	
	@XmlTransient
	@Basic(optional=false, fetch=FetchType.EAGER)
	private boolean processed=false;
	
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the persons
	 */
	public Set<Books_Annotation_Person> getAnnotations() {
		return annotations;
	}

	/**
	 * @param persons the persons to set
	 */
	public void setAnnotations(Set<Books_Annotation_Person> persons) {
		this.annotations = persons;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the other_names
	 */
	public String getOther_names() {
		return other_names;
	}

	/**
	 * @param other_names the other_names to set
	 */
	public void setOther_names(String other_names) {
		this.other_names = other_names;
	}

	/**
	 * @return the main_name
	 */
	public Person getMain_name() {
		return main_name;
	}

	/**
	 * @param main_name the main_name to set
	 */
	public void setMain_name(Person main_name) {
		this.main_name = main_name;
	}

	/**
	 * @return the disambiguation
	 */
	public String getDisambiguation() {
		return disambiguation;
	}

	/**
	 * @param disambiguation the disambiguation to set
	 */
	public void setDisambiguation(String disambiguation) {
		this.disambiguation = disambiguation;
	}

	/**
	 * @return the cross_references
	 */
	public String getCross_references() {
		return cross_references;
	}

	/**
	 * @param cross_references the cross_references to set
	 */
	public void setCross_references(String cross_references) {
		this.cross_references = cross_references;
	}

	/**
	 * @return the biography
	 */
	public String getBiography() {
		return biography;
	}

	/**
	 * @param biography the biography to set
	 */
	public void setBiography(String biography) {
		this.biography = biography;
	}

	/**
	 * @return the processed
	 */
	public boolean isProcessed() {
		return processed;
	}

	/**
	 * @param processed the processed to set
	 */
	public void setProcessed(boolean processed) {
		this.processed = processed;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((disambiguation == null) ? 0 : disambiguation.hashCode());
		result = prime * result
				+ ((main_name == null) ? 0 : main_name.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((other_names == null) ? 0 : other_names.hashCode());
		result = prime * result + (processed ? 1231 : 1237);
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (disambiguation == null) {
			if (other.disambiguation != null)
				return false;
		} else if (!disambiguation.equals(other.disambiguation))
			return false;
		if (main_name == null) {
			if (other.main_name != null)
				return false;
		} else if (!main_name.equals(other.main_name))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (other_names == null) {
			if (other.other_names != null)
				return false;
		} else if (!other_names.equals(other.other_names))
			return false;
		if (processed != other.processed)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Person [getId()="
				+ getId() + ", getName()=" + getName() + ", getOther_names()="
				+ getOther_names() 
				+ ", getDisambiguation()=" + getDisambiguation()
				+ ", getCross_references()=" + getCross_references()
				+ ", getBiography()=" + getBiography() + ", isProcessed()="
				+ isProcessed() + "]";
	}
	
	
	
}