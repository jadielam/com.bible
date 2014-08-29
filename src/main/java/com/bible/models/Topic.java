package com.bible.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAccessType;

import com.bible.xml.models.Content;

@XmlRootElement(name="topic")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Topic implements Serializable, Content {

	/**
	 * 
	 */
	@XmlTransient
	@Transient
	private static final long serialVersionUID = 1446627173005641598L;
	
	@XmlTransient
	@OneToMany(mappedBy="content")
	private Set<Books_Annotation_Topic> annotations=new HashSet<Books_Annotation_Topic>();
	
	@XmlAttribute
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@XmlElement
	@Basic(optional=false, fetch=FetchType.EAGER)
	private String name;
	
	@XmlElement
	@Basic(optional=true, fetch=FetchType.EAGER)
	private String description;
	
	@XmlElement
	@Basic(optional=true, fetch=FetchType.LAZY)
	private String cross_references;
	
	@XmlTransient
	@Basic(optional=false, fetch=FetchType.EAGER)
	private boolean processed=false;
	
	public Topic() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the topics
	 */
	public Set<Books_Annotation_Topic> getAnnotations() {
		return annotations;
	}

	/**
	 * @param topics the topics to set
	 */
	public void setAnnotations(Set<Books_Annotation_Topic> annotations) {
		this.annotations = annotations;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
				+ ((description == null) ? 0 : description.hashCode());
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
		Topic other = (Topic) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
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
		return "Topic [getAnnotations()=" + getAnnotations() + ", getId()="
				+ getId() + ", getName()=" + getName() + ", getDescription()="
				+ getDescription() + ", getCross_references()="
				+ getCross_references() + ", isProcessed()=" + isProcessed()
				+ "]";
	}

	
	
}
