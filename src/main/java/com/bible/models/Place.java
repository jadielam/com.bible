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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.bible.xml.PlaceAdapter;
import com.bible.xml.models.Content;


@XmlRootElement(name="place")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Place implements Serializable, Content {

	
	/**
	 * 
	 */
	@XmlTransient
	@Transient
	private static final long serialVersionUID = -5687200822733835657L;
	
	@XmlTransient
	@OneToMany(mappedBy="content")
	private Set<Books_Annotation_Place> annotations=new HashSet<Books_Annotation_Place>();
	
	@XmlAttribute
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@XmlElement
	@Basic(optional=false, fetch=FetchType.EAGER)
	private String name;
	
	@XmlElement
	@XmlJavaTypeAdapter(PlaceAdapter.class)
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="main_name_id")
	@Basic(optional=true, fetch=FetchType.EAGER)
	private Place main_name;
	
	@XmlElement
	@Basic(optional=true, fetch=FetchType.EAGER)
	private String coordinates;
	
	@XmlElement
	@Basic(optional=true, fetch=FetchType.EAGER)
	private String source_of_coordinates;
	
	@XmlElement
	@Basic(optional=true, fetch=FetchType.EAGER)
	private String disambiguation;
	
	@XmlElement
	@Basic(optional=true, fetch=FetchType.EAGER)
	private String cross_references;
	
	@XmlElement
	@Basic(optional=true, fetch=FetchType.EAGER)
	private String wikipedia_url;
	
	@XmlElement
	@Basic(optional=false, fetch=FetchType.EAGER)
	private String classification;
	
	@XmlTransient
	@Basic(optional=false, fetch=FetchType.EAGER)
	private boolean processed=false;
	
	public Place() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the id
	 */

	/**
	 * @return the annotations
	 */
	public Set<Books_Annotation_Place> getAnnotations() {
		return annotations;
	}

	/**
	 * @param annotations the annotations to set
	 */
	public void setAnnotations(Set<Books_Annotation_Place> annotations) {
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
	 * @return the main_name
	 */
	public Place getMain_name() {
		return main_name;
	}

	/**
	 * @param main_name the main_name to set
	 */
	public void setMain_name(Place main_name) {
		this.main_name = main_name;
	}

	/**
	 * @return the coordinates
	 */
	public String getCoordinates() {
		return coordinates;
	}

	/**
	 * @param coordinates the coordinates to set
	 */
	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}

	/**
	 * @return the source_of_coordinates
	 */
	public String getSource_of_coordinates() {
		return source_of_coordinates;
	}

	/**
	 * @param source_of_coordinates the source_of_coordinates to set
	 */
	public void setSource_of_coordinates(String source_of_coordinates) {
		this.source_of_coordinates = source_of_coordinates;
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
	 * @return the wikipedia_url
	 */
	public String getWikipedia_url() {
		return wikipedia_url;
	}

	/**
	 * @param wikipedia_url the wikipedia_url to set
	 */
	public void setWikipedia_url(String wikipedia_url) {
		this.wikipedia_url = wikipedia_url;
	}

	/**
	 * @return the classification
	 */
	public String getClassification() {
		return classification;
	}

	/**
	 * @param classification the classification to set
	 */
	public void setClassification(String classification) {
		this.classification = classification;
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
				+ ((classification == null) ? 0 : classification.hashCode());
		result = prime * result
				+ ((coordinates == null) ? 0 : coordinates.hashCode());
		result = prime * result
				+ ((disambiguation == null) ? 0 : disambiguation.hashCode());
		result = prime * result
				+ ((main_name == null) ? 0 : main_name.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Place other = (Place) obj;
		if (classification == null) {
			if (other.classification != null)
				return false;
		} else if (!classification.equals(other.classification))
			return false;
		if (coordinates == null) {
			if (other.coordinates != null)
				return false;
		} else if (!coordinates.equals(other.coordinates))
			return false;
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
		if (processed != other.processed)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Place [getAnnotations()=" + getAnnotations() + ", getId()="
				+ getId() + ", getName()=" + getName() + ", getMain_name()="
				+ getMain_name().getName() + ", getCoordinates()=" + getCoordinates()
				+ ", getSource_of_coordinates()=" + getSource_of_coordinates()
				+ ", getDisambiguation()=" + getDisambiguation()
				+ ", getCross_references()=" + getCross_references()
				+ ", getWikipedia_url()=" + getWikipedia_url()
				+ ", getClassification()=" + getClassification()
				+ ", isProcessed()=" + isProcessed() + "]";
	}
	
		
}
