package com.bible.xml.models;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;

import com.bible.models.Person;

@XmlRootElement(name="person")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonXml implements Content {
	
	@XmlAttribute
	private Long id;
	
	@XmlElement
	private String name;
	
	@XmlElement
	private String main_name;
	
	@XmlAttribute
	private Long main_name_id;
	
	@XmlElement
	private String disambiguation;
	
	@XmlElement
	private String biography;
	
	
	public PersonXml(Person person){
		this.id=person.getId();
		this.name=person.getName();
		if (person.getMain_name()!=null) {
			this.main_name=person.getMain_name().getName();
			this.main_name_id=person.getMain_name().getId();
		}
				
		this.disambiguation=person.getDisambiguation();
		this.biography=person.getBiography();
	}
	
	public PersonXml(){
		super();
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
	public void setId(Long id) {
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
	 * @return the main_name
	 */
	public String getMain_name() {
		return main_name;
	}

	/**
	 * @param main_name the main_name to set
	 */
	public void setMain_name(String main_name) {
		this.main_name = main_name;
	}

	/**
	 * @return the main_name_id
	 */
	public Long getMain_name_id() {
		return main_name_id;
	}

	/**
	 * @param main_name_id the main_name_id to set
	 */
	public void setMain_name_id(Long main_name_id) {
		this.main_name_id = main_name_id;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((biography == null) ? 0 : biography.hashCode());
		result = prime * result
				+ ((disambiguation == null) ? 0 : disambiguation.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((main_name == null) ? 0 : main_name.hashCode());
		result = prime * result
				+ ((main_name_id == null) ? 0 : main_name_id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		PersonXml other = (PersonXml) obj;
		if (biography == null) {
			if (other.biography != null)
				return false;
		} else if (!biography.equals(other.biography))
			return false;
		if (disambiguation == null) {
			if (other.disambiguation != null)
				return false;
		} else if (!disambiguation.equals(other.disambiguation))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (main_name == null) {
			if (other.main_name != null)
				return false;
		} else if (!main_name.equals(other.main_name))
			return false;
		if (main_name_id == null) {
			if (other.main_name_id != null)
				return false;
		} else if (!main_name_id.equals(other.main_name_id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PersonXml [getId()=" + getId() + ", getName()=" + getName()
				+ ", getMain_name()=" + getMain_name() + ", getMain_name_id()="
				+ getMain_name_id() + ", getDisambiguation()="
				+ getDisambiguation() + ", getBiography()=" + getBiography()
				+ "]";
	}
	
	
}
