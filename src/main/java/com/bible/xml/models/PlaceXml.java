package com.bible.xml.models;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;
import com.bible.models.Place;

@XmlRootElement(name="place")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlaceXml implements Content{

	@XmlAttribute
	private Long id;
	
	@XmlElement
	private String name;
	
	@XmlElement
	private String main_name;
	
	@XmlElement
	private Long main_name_id;
	
	@XmlElement
	private String coordinates;
	
	@XmlElement
	private String source_of_coordinates;
	
	@XmlElement
	private String disambiguation;
	
	@XmlElement
	private String wikipedia_url;
	
	@XmlElement
	private String classification;
	
	public PlaceXml(Place place){
		this.id=place.getId();
		this.name=place.getName();
		if (place.getMain_name()!=null){
			this.main_name=place.getMain_name().getName();
			this.main_name_id=place.getMain_name().getId();
		}
		this.coordinates=place.getCoordinates();
		this.source_of_coordinates=place.getSource_of_coordinates();
		this.disambiguation=place.getDisambiguation();
		this.wikipedia_url=place.getWikipedia_url();
		this.classification=place.getClassification();
		
	}
	
	public PlaceXml(){
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((main_name == null) ? 0 : main_name.hashCode());
		result = prime * result
				+ ((main_name_id == null) ? 0 : main_name_id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime
				* result
				+ ((source_of_coordinates == null) ? 0 : source_of_coordinates
						.hashCode());
		result = prime * result
				+ ((wikipedia_url == null) ? 0 : wikipedia_url.hashCode());
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
		PlaceXml other = (PlaceXml) obj;
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
		if (source_of_coordinates == null) {
			if (other.source_of_coordinates != null)
				return false;
		} else if (!source_of_coordinates.equals(other.source_of_coordinates))
			return false;
		if (wikipedia_url == null) {
			if (other.wikipedia_url != null)
				return false;
		} else if (!wikipedia_url.equals(other.wikipedia_url))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PlaceXml [getId()=" + getId() + ", getName()=" + getName()
				+ ", getMain_name()=" + getMain_name() + ", getMain_name_id()="
				+ getMain_name_id() + ", getCoordinates()=" + getCoordinates()
				+ ", getSource_of_coordinates()=" + getSource_of_coordinates()
				+ ", getDisambiguation()=" + getDisambiguation()
				+ ", getWikipedia_url()=" + getWikipedia_url()
				+ ", getClassification()=" + getClassification() + "]";
	}
	
	
}
